package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.example.user.newspapervendor.Adapters.StopDeliveryAdapter;
import com.example.user.newspapervendor.StopDeliveryModel;

import java.util.ArrayList;
import java.util.List;

public class Stop_delivery extends AppCompatActivity {
    FloatingActionButton add_customer;
    private RecyclerView recyclerView;
    List<StopDeliveryModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_delivery);
        add_customer = findViewById(R.id.add_customer);
        recyclerView = findViewById(R.id.recylerView_sd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataSet();
        StopDeliveryAdapter adapter = new StopDeliveryAdapter(this, list);
        recyclerView.setAdapter(adapter);
        add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Stop_delivery.this, ManageStopDelivery.class));
            }
        });
    }

    private void dataSet() {
        list = new ArrayList<>();
        list.add(new StopDeliveryModel("Sachin", "12/03/2018","9876543210","Ok","",""));
        list.add(new StopDeliveryModel("Raja", "12/03/2018","9876543210","Ok","",""));
        list.add(new StopDeliveryModel("Vivek", "12/03/2018","9876543210","Ok","",""));
        list.add(new StopDeliveryModel("Vikas", "12/03/2018","9876543210","Ok","",""));

    }
}
