/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 16-Sep-21
 *   Time: 10:18 PM
 *   File: Pricing.java
 */

package com.example.brahmastra.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String highlights;
    private int price;
    private String type;
    private int discount;
    private float discountPercentage;
    private float total;
    private float tax;


    @Override
    public String toString() {
        return "Pricing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", highlights='" + highlights + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", discount=" + discount +
                ", discountPercentage=" + discountPercentage +
                ", total=" + total +
                ", tax=" + tax +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pricing)) return false;
        Pricing pricing = (Pricing) o;
        return getId() == pricing.getId() && getPrice() == pricing.getPrice() && getDiscount() == pricing.getDiscount() && Float.compare(pricing.getDiscountPercentage(), getDiscountPercentage()) == 0 && Float.compare(pricing.getTotal(), getTotal()) == 0 && Float.compare(pricing.getTax(), getTax()) == 0 && Objects.equals(getName(), pricing.getName()) && Objects.equals(getHighlights(), pricing.getHighlights()) && Objects.equals(getType(), pricing.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getHighlights(), getPrice(), getType(), getDiscount(), getDiscountPercentage(), getTotal(), getTax());
    }

    public Pricing() {
    }

    public Pricing(int id) {
        this.id = id;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public Pricing(String name, String highlights, int price, String type, int discount, float discountPercentage, float total, float tax) {
        this.name = name;
        this.highlights = highlights;
        this.price = price;
        this.type = type;
        this.discount = discount;
        this.discountPercentage = discountPercentage;
        this.total = total;
        this.tax = tax;
    }

    public Pricing(int id, String name, String highlights, int price, String type, int discount, float discountPercentage, float total, float tax) {
        this.id = id;
        this.name = name;
        this.highlights = highlights;
        this.price = price;
        this.type = type;
        this.discount = discount;
        this.discountPercentage = discountPercentage;
        this.total = total;
        this.tax = tax;
    }
}