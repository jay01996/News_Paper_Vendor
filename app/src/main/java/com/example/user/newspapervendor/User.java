package com.example.user.newspapervendor;

public class User {

    private String user_name, user_mail, mobile, newspaper_agency_name, pin_code, address;

    public User() {
    }

    public User(String user_name, String user_mail, String mobile, String newspaper_agency_name, String pin_code, String address) {
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.mobile = mobile;
        this.newspaper_agency_name = newspaper_agency_name;
        this.pin_code = pin_code;
        this.address = address;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNewspaper_agency_name() {
        return newspaper_agency_name;
    }

    public void setNewspaper_agency_name(String newspaper_agency_name) {
        this.newspaper_agency_name = newspaper_agency_name;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
