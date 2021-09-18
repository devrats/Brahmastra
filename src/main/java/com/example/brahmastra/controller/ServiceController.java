/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 11:59 AM
 *   File: ServiceController.java
 */

package com.example.brahmastra.controller;

import com.example.brahmastra.entity.*;
import com.example.brahmastra.repository.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ServiceController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ClientProjectRepository clientProjectRepository;

    @Autowired
    private PricingRepository pricingRepository;

    @RequestMapping("/user/home")
    public  String userHome(Model model, Principal principal){
        Client clientByUsername = clientRepository.findClientByUsername(principal.getName());
        if(!clientByUsername.isMail()){
            return "redirect:/user/email";
        } else {
            model.addAttribute("name",clientByUsername);
            model.addAttribute("title","Brahmastra");
            model.addAttribute("loginAvailable",true);
            return "home";
        }
    }

    @RequestMapping("/user/checkout/{type}/")
    public String customProject(@PathVariable("type") String type,@RequestParam("page") int page,
                                @RequestParam("functionality") int functionality,  Model model, Principal principal){
        Project project =  new Project("Customized project","  •Very good\n  •Bht achha","Web",10);
        int price = page*249 + functionality*349;
        float tax = 0.18f*price;
        float discounter = 0.1f*price;
        int discount = (int) (price-discounter);
        float total = discount + tax;
        project.setPrice(price);
        project.setTax(tax);
        project.setDiscount(discount);
        project.setTotal(total);
        project.setProjectType(type);
        Project save = projectRepository.save(project);
        model.addAttribute("id",save.getId());
        model.addAttribute("type",type);
        model.addAttribute("title","Billing");
        model.addAttribute("loginAvailable",true);
        Client clientByUsername = clientRepository.findClientByUsername(principal.getName());
        model.addAttribute("client",clientByUsername);
        if(clientByUsername.getAddress()==null){
            model.addAttribute("address",new Address());
        }else{
            model.addAttribute("address",clientByUsername.getAddress());
        }
        return "checkout";
    }

    @RequestMapping("/user/pay")
    @ResponseBody
    public String pay(@RequestBody Map<String, Object> data, Principal principal) throws RazorpayException {
        var client = new RazorpayClient("rzp_test_4sgOUR0hFBHwtn", "buf7y58LORq7JKsFuExL7xXS");
        JSONObject object = new JSONObject();
        float amount = Float.parseFloat(data.get("amount").toString());
        object.put("amount", amount * 100);
        object.put("currency", "INR");
        object.put("receipt", "txn_1234");
        Order order = client.Orders.create(object);
        Client clientHome = clientRepository.findClientByUsername(principal.getName());
        String type = (String) data.get("type");
        int id = Integer.parseInt((String) data.get("id"));
        if (type.equals("project") || type.equals("customProject")){
            Project projectById = projectRepository.findProjectById(id);
            ClientProject convert = ClientProject.convert(projectById);
            List<ClientProject> projects = clientHome.getProjects();
            projects.add(convert);
            convert.setClient(clientHome);
            clientProjectRepository.save(convert);
        } else if (type.equals("pricing")){
            Pricing pricingById = pricingRepository.findPricingById(id);
            ClientProject convert = ClientProject.convert(pricingById);
            List<ClientProject> projects = clientHome.getProjects();
            projects.add(convert);
            convert.setClient(clientHome);
            clientProjectRepository.save(convert);
        }
        List<Payment> payment1 = clientHome.getPayment();
        Payment payment = new Payment(order.get("id"), amount, order.get("receipt"), "created", (Date) order.get("created_at"));
        payment1.add(payment);
        payment.setClient(clientHome);
        paymentRepository.save(payment);
        clientRepository.save(clientHome);
        return order.toString();
    }

    @RequestMapping("/user/paySuccess")
    @ResponseBody
    public ResponseEntity<?> paySuccess(@RequestBody Map<String, Object> data, Principal principal) {
        Payment payment = paymentRepository.findPaymentByPaymentId((String) data.get("razorpay_order_id"));
        payment.setStatus((String) data.get("status"));
        payment.setTransactionId((String) data.get("razorpay_payment_id"));
        paymentRepository.save(payment);
        return ResponseEntity.ok(Map.of("msg", "updated"));
    }
}