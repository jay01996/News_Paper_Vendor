package com.example.user.newspapervendor;

public class BankDetail {
    String account_no, acc_holder_name, bank_name, branch, ifsc;

    public BankDetail() {
    }

    public BankDetail(String account_no, String acc_holder_name, String bank_name, String branch, String ifsc) {
        this.account_no = account_no;
        this.acc_holder_name = acc_holder_name;
        this.bank_name = bank_name;
        this.branch = branch;
        this.ifsc = ifsc;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getAcc_holder_name() {
        return acc_holder_name;
    }

    public void setAcc_holder_name(String acc_holder_name) {
        this.acc_holder_name = acc_holder_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }
}
