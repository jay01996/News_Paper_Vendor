package com.example.user.newspapervendor;

public class MonthlyReportDetail {
    String name,bill,collection,papers;

    public MonthlyReportDetail() {
    }

    public MonthlyReportDetail(String name, String bill, String collection, String papers) {
        this.name = name;
        this.bill = bill;
        this.collection = collection;
        this.papers = papers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getPapers() {
        return papers;
    }

    public void setPapers(String papers) {
        this.papers = papers;
    }
}
