package com.example.user.newspapervendor;

public class BillDetail {
    private String name, papers, mobile, estimate_bill, last_month_name, last_month_bill;
    private String collection_pending_amt;
    private int cash_collected, cash_advance;
    private int type;

    public static final int BILL_COMPLETED = 1;
    public static final int BILL_PENDING = 2;
    public static final int BILL_GENERATE = 3;


//    public BillDetail(String name, String papers, String mobile, String estimate_bill, String last_month_name, String last_month_bill, String collection_pending_amt, int cash_collected, int cash_advance, int type) {
//        this.name = name;
//        this.papers = papers;
//        this.mobile = mobile;
//        this.estimate_bill = estimate_bill;
//        this.last_month_name = last_month_name;
//        this.last_month_bill = last_month_bill;
//        this.collection_pending_amt = collection_pending_amt;
//        this.cash_collected = cash_collected;
//        this.cash_advance = cash_advance;
//        this.type = type;
//    }

    public BillDetail(String name, String papers, String mobile, String estimate_bill, int type) {
        this.name = name;
        this.papers = papers;
        this.mobile = mobile;
        this.estimate_bill = estimate_bill;
        this.type = type;
    }

    public BillDetail(String name, String papers, int cash_collected, int cash_advance, int type) {
        this.name = name;
        this.papers = papers;
        this.cash_advance = cash_advance;
        this.cash_collected = cash_collected;
        this.type = type;
    }

    public BillDetail(String name, String papers, String last_month_name, String last_month_bill, String collection_pending_amt, int type) {
        this.name = name;
        this.papers = papers;
        this.last_month_bill = last_month_bill;
        this.last_month_name = last_month_name;
        this.collection_pending_amt = collection_pending_amt;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPapers() {
        return papers;
    }

    public void setPapers(String papers) {
        this.papers = papers;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEstimate_bill() {
        return estimate_bill;
    }

    public void setEstimate_bill(String estimate_bill) {
        this.estimate_bill = estimate_bill;
    }

    public String getLast_month_name() {
        return last_month_name;
    }

    public void setLast_month_name(String last_month_name) {
        this.last_month_name = last_month_name;
    }

    public String getLast_month_bill() {
        return last_month_bill;
    }

    public void setLast_month_bill(String last_month_bill) {
        this.last_month_bill = last_month_bill;
    }

    public String getCollection_pending_amt() {
        return collection_pending_amt;
    }

    public void setCollection_pending_amt(String collection_pending_amt) {
        this.collection_pending_amt = collection_pending_amt;
    }

    public int getCash_collected() {
        return cash_collected;
    }

    public void setCash_collected(int cash_collected) {
        this.cash_collected = cash_collected;
    }

    public int getCash_advance() {
        return cash_advance;
    }

    public void setCash_advance(int cash_advance) {
        this.cash_advance = cash_advance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static int getBillCompleted() {
        return BILL_COMPLETED;
    }

    public static int getBillPending() {
        return BILL_PENDING;
    }

    public static int getBillGenerate() {
        return BILL_GENERATE;
    }
}
