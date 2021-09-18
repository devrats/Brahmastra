/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 11-Sep-21
 *   Time: 10:11 AM
 *   File: Project.java
 */

package com.example.brahmastra.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String highlights;
    private int price;
    private String type;
    private int discount;
    private float discountPercentage;
    private String projectType;

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Project(String name, String highlights, String type, float discountPercentage) {
        this.name = name;
        this.highlights = highlights;
        this.type = type;
        this.discountPercentage = discountPercentage;
    }

    private float total;
    private float tax;

    public Project(String name, String highlights, int price, String type, int discount, float discountPercentage, float total, float tax) {
        this.name = name;
        this.highlights = highlights;
        this.price = price;
        this.type = type;
        this.discount = discount;
        this.discountPercentage = discountPercentage;
        this.total = total;
        this.tax = tax;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", highlights='" + highlights + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return getId() == project.getId() && getPrice() == project.getPrice() && Objects.equals(getName(), project.getName()) && Objects.equals(getHighlights(), project.getHighlights()) && Objects.equals(getType(), project.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getHighlights(), getPrice(), getType());
    }

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Project(String name, String highlights, int price, String type) {
        this.name = name;
        this.highlights = highlights;
        this.price = price;
        this.type = type;
    }

    public Project(int id, String name, String highlights, int price, String type) {
        this.id = id;
        this.name = name;
        this.highlights = highlights;
        this.price = price;
        this.type = type;
    }
}