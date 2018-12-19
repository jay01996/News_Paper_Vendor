package com.example.user.newspapervendor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.newspapervendor.Adapters.BillDetailAdapter;
import com.example.user.newspapervendor.Adapters.MonthlyStatementAdapter;
import com.example.user.newspapervendor.BillDetail;
import com.example.user.newspapervendor.MonthlyStatementModel;

import java.util.ArrayList;
import java.util.List;

public class Monthly_Statement extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<MonthlyStatementModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly__statement);
        recyclerView = findViewById(R.id.recylerView_monthlyStatement);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataSet();
        MonthlyStatementAdapter adapter = new MonthlyStatementAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }


    public void dataSet() {
        list = new ArrayList<>();
        list.add(new MonthlyStatementModel("DEC 2018"));
        list.add(new MonthlyStatementModel("JAN 2019"));
        list.add(new MonthlyStatementModel("FEB 2019"));
        list.add(new MonthlyStatementModel("MARCH 2019"));
        list.add(new MonthlyStatementModel("APR 2019"));
        list.add(new MonthlyStatementModel("MAY 2019"));
        list.add(new MonthlyStatementModel("JUNE 2019"));
        list.add(new MonthlyStatementModel("AUG 2019"));
        list.add(new MonthlyStatementModel("SEP 2019"));
        list.add(new MonthlyStatementModel("OCT 2019"));
        list.add(new MonthlyStatementModel("NOV 2019"));
        list.add(new MonthlyStatementModel("DEC 2019"));

    }
}

