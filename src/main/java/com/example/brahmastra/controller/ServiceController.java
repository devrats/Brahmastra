/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 11:59 AM
 *   File: ServiceController.java
 */

package com.example.brahmastra.controller;

import com.example.brahmastra.entity.Client;
import com.example.brahmastra.entity.Payment;
import com.example.brahmastra.repository.ClientRepository;
import com.example.brahmastra.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/user/pay")
    @ResponseBody
    public String pay(@RequestBody Map<String, Object> data, Principal principal) throws RazorpayException {
        var client = new RazorpayClient("rzp_test_4sgOUR0hFBHwtn", "buf7y58LORq7JKsFuExL7xXS");
        JSONObject object = new JSONObject();
        int amount = Integer.parseInt(data.get("amount").toString());
        object.put("amount", amount * 100);
        object.put("currency", "INR");
        object.put("receipt", "txn_1234");
        Order order = client.Orders.create(object);
        Client clientHome = clientRepository.findClientByUsername(principal.getName());
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