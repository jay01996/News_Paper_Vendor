package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.newspapervendor.Adapters.BillDetailAdapter;
import com.example.user.newspapervendor.Adapters.BillStatusAdapter;
import com.example.user.newspapervendor.BillDetail;
import com.example.user.newspapervendor.Bill_Status;

import java.util.ArrayList;
import java.util.List;

public class Bill_Details extends AppCompatActivity {
    List<BillDetail> billDetailList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill__details);
        recyclerView = findViewById(R.id.recylerView_bill_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataSet();
        BillDetailAdapter adapter = new BillDetailAdapter(this, billDetailList);
        recyclerView.setAdapter(adapter);
    }

    public void dataSet() {
        billDetailList = new ArrayList<>();
        billDetailList.add(new BillDetail("Vivek", "The Hindu", "8630795121", "234", BillDetail.BILL_GENERATE));
        billDetailList.add(new BillDetail("Sachin", "Amar Ujala, Dainik Jagran", 30, 30, BillDetail.BILL_COMPLETED));
        billDetailList.add(new BillDetail("Raaj", "Times of India, Dainik Bhaskar", "NOV 2018", "2050", "2300", BillDetail.BILL_PENDING));
    }
}
