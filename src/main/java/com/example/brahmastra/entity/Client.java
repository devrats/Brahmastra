/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 10-Sep-21
 *   Time: 12:00 PM
 *   File: Client.java
 */

package com.example.brahmastra.entity;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne(mappedBy = "client")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClientProject> getProjects() {
        return projects;
    }

    public void setProjects(List<ClientProject> projects) {
        this.projects = projects;
    }

    @NotBlank(message = "Email cannot be blank")
    private String username;
    @NotBlank(message = "Password Cannot be blank")
    @Size(min = 8, message = "Password must be greater then 8 character")
    private String password;
    @NotBlank
    private String mobile;
    private String role;
    private boolean mail;
    @AssertTrue(message = "Please agree to terms and condition")
    private boolean agree;
    @OneToMany(mappedBy = "client")
    private List<Payment> payment;
    @OneToMany(mappedBy = "client")
    private List<ClientProject> projects;

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public boolean isMail() {
        return mail;
    }

    public void setMail(boolean mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getId() == client.getId() && Objects.equals(getUsername(), client.getUsername()) && Objects.equals(getPassword(), client.getPassword()) && Objects.equals(getMobile(), client.getMobile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getMobile());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Client() {
    }

    public Client(String username, String password, String mobile) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    public Client(int id, String username, String password, String mobile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }
}