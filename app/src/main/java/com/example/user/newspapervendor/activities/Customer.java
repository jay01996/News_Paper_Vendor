package com.example.user.newspapervendor.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StateSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.newspapervendor.UploadCustomerData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Customer extends AppCompatActivity {
    ImageView add_customer;
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> customerList;
    ProgressDialog loadingBar;
    ArrayAdapter<String> customerAdapter;
    UploadCustomerData uploadCustomerData;
    String uid;
    String name;
    private static String key;
    SearchView searchView;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        add_customer = findViewById(R.id.add_new_customer);
        listView = findViewById(R.id.customer_list);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        uploadCustomerData = new UploadCustomerData();
        firebaseDatabase = FirebaseDatabase.getInstance();
        uid = FirebaseAuth.getInstance().getUid();
        databaseReference = firebaseDatabase.getReference("Users");
        customerList = new ArrayList<>();
        loadingBar = new ProgressDialog(this);
        searchView = findViewById(R.id.search_cust_name);

//        loadingBar.setTitle("Please Wait..");
//        loadingBar.setMessage("We're Getting Customer Details..");
//        loadingBar.show();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> templist = new ArrayList<>();
                for (String temp : customerList) {
                    if (temp.toLowerCase().contains(newText.toLowerCase())) {
                        templist.add(temp);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Customer.this, android.R.layout.simple_list_item_1, templist);
                listView.setAdapter(adapter);
                return true;
            }
        });
        databaseReference.child(uid).child("Customers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    uploadCustomerData = ds.getValue(UploadCustomerData.class);
                    customerList.add(uploadCustomerData.getCustomer_name());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Customer.this, "failed :" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //  loadingBar.dismiss();
            }
        });

        customerAdapter = new ArrayAdapter<>(this, R.layout.cutomer_info, R.id.customer_Info, customerList);
        listView.setAdapter(customerAdapter);

        //Add new Customer
        add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Customer.this, AddCustomer.class));
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listView.getItemAtPosition(position).toString();
                Query query = databaseReference.child(uid).child("Customers").orderByChild("customer_name").equalTo(name);
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            key = ds.getKey();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Customer.this, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                query.addListenerForSingleValueEvent(valueEventListener);
                Intent intent = new Intent(Customer.this, PreviewCustomer.class);
                startActivity(intent);
            }
        });
    }

    public static String getData() {
        return key;
    }
}
