package com.example.user.newspapervendor.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.user.newspapervendor.Adapters.BillStatusAdapter;
import com.example.user.newspapervendor.Bill_Status;
import com.example.user.newspapervendor.UploadCustomerData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Bill_Collection extends AppCompatActivity {

    List<Bill_Status> billStatusesDetails;
    RecyclerView recyclerView;
    public int pending_amount;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseDatabase firebaseDatabase;
    ArrayList<String> cust_name_List;
    Bill_Status bill_status;
    ArrayList<String> mobileList;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill__collection);
        //getting recycler View from XML
        recyclerView = findViewById(R.id.recylerView);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference("Users");


        billStatusesDetails = new ArrayList<>();

      /*  mRef.child(mAuth.getUid()).child("Customers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        bill_status = ds.getValue(Bill_Status.class);
//                    cust_name_List.add(bill_status.getCust_name());
//                    mobileList.add(bill_status.getMobile());
                        billStatusesDetails.add(bill_status);
                    }
                    setAdapter();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Bill_Collection.this, "failed :" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //  loadingBar.dismiss();
            }
        });
*/
        loadingBar.setMessage("Please Wait..");
        loadingBar.show();
        mRef.child(mAuth.getUid()).child("Customers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        bill_status = ds.getValue(Bill_Status.class);

                        billStatusesDetails.add(bill_status);
                    }
                    setAdapter();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Bill_Collection.this, "failed :" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        });


        /*********************************Adding Recycler View and Adapter*******************************************************/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        billStatusesDetails = new ArrayList<>();
//        billStatusesDetails.add(new Bill_Status("Sachin", "8630795121", 30, 30));
//        setAdapter();
        new Intent(getApplicationContext(), HomePage.class).putExtra("pending_amt", pending_amount);

/*********************************CALCULATE NO OF DAY BETWEEN TWO DATES*******************************************************/
        String dateStr = "11/11/2018";
        String dateEnd = "30/11/2018";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        try {
            Date date1 = sdf.parse(dateStr);
            Date date2 = sdf.parse(dateEnd);
            long diff = date2.getTime() - date1.getTime();
            long number = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            System.out.println("Days: " + number);
            // Toast.makeText(this, "Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS), Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
/*********************************************************************************************************/

        String date = "11/11/2018";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(convertedDate);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        int last_day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int last_month = c.getActualMaximum(Calendar.MONTH);
        int l_year = c.getActualMaximum(Calendar.DATE);
        Toast.makeText(this, "" + String.valueOf(l_year), Toast.LENGTH_LONG).show();
        String last_date = String.valueOf(last_day) + "/" + String.valueOf(last_month) + "/" + String.valueOf(l_year);
        // Toast.makeText(this, "" + last_date, Toast.LENGTH_SHORT).show();
    }

    private void setAdapter() {
        BillStatusAdapter adapter = new BillStatusAdapter(this, billStatusesDetails);
        recyclerView.setAdapter(adapter);

    }
}
