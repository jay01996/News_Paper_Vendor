package com.example.user.newspapervendor;

public class UploadCustomerData {
    private String customer_name, customer_mobile, customer_email, delivery_charge, customer_address, customer_flat_building;
    private String paper, date, quantity, price;
    private String send_bill_via,days_num,delivery_days;

    public UploadCustomerData() {
    }

    public UploadCustomerData(String customer_name, String customer_mobile, String customer_email, String delivery_charge, String customer_address, String customer_flat_building, String paper, String date, String quantity, String price, String send_bill_via, String days_num, String delivery_days) {
        this.customer_name = customer_name;
        this.customer_mobile = customer_mobile;
        this.customer_email = customer_email;
        this.delivery_charge = delivery_charge;
        this.customer_address = customer_address;
        this.customer_flat_building = customer_flat_building;
        this.paper = paper;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.send_bill_via = send_bill_via;
        this.days_num = days_num;
        this.delivery_days = delivery_days;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_mobile() {
        return customer_mobile;
    }

    public void setCustomer_mobile(String customer_mobile) {
        this.customer_mobile = customer_mobile;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(String delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_flat_building() {
        return customer_flat_building;
    }

    public void setCustomer_flat_building(String customer_flat_building) {
        this.customer_flat_building = customer_flat_building;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSend_bill_via() {
        return send_bill_via;
    }

    public void setSend_bill_via(String send_bill_via) {
        this.send_bill_via = send_bill_via;
    }

    public String getDays_num() {
        return days_num;
    }

    public void setDays_num(String days_num) {
        this.days_num = days_num;
    }

    public String getDelivery_days() {
        return delivery_days;
    }

    public void setDelivery_days(String delivery_days) {
        this.delivery_days = delivery_days;
    }
}
