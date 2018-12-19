package com.example.user.newspapervendor;

public class Bill_Status {
    private String cust_name;
    private String mobile;
    private int cash_collected;
    private int pending_cash;

    public Bill_Status() {
    }

    public Bill_Status(String cust_name, String mobile, Integer cash_collected, Integer pending_cash) {
        this.cust_name = cust_name;
        this.mobile = mobile;
        this.cash_collected = cash_collected;
        this.pending_cash = pending_cash;
    }

    public String getCust_name() {
        return cust_name;
    }

    public String getMobile() {
        return mobile;
    }

    public int getCash_collected() {
        return cash_collected;
    }

    public int getPending_cash() {
        return pending_cash;
    }
}
