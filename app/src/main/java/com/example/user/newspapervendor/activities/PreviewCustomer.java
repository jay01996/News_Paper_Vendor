package com.example.user.newspapervendor.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.newspapervendor.UploadCustomerData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PreviewCustomer extends AppCompatActivity {
    private EditText pre_customer_name, pre_customer_mobile, pre_customer_email, pre_customer_delivery_charges, pre_customer_flat;
    private EditText pre_customer_address, pre_customer_paper, pre_customer_paper_price, pre_customer_paper_qty, pre_customer_start_date;
    private EditText pre_customer_bills_via, pre_customer_delivery_days, pre_customer_days_num;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ProgressDialog loadingBar;
    private LinearLayout linearlayout_preview_details;
    Button btn_update;
    TextView edit_detail, delete_detail;
    String key = Customer.getData();
    AlertDialog.Builder builder;
    AlertDialog dialog;
    String name, mobile, mail, charges, flat_no, address, paper, price, qty, start_date, bill_via, delivery_days, days_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_customer);
        pre_customer_days_num = findViewById(R.id.pre_customer_days_num);
        pre_customer_name = findViewById(R.id.pre_customer_name);
        pre_customer_mobile = findViewById(R.id.pre_customer_mobile);
        pre_customer_email = findViewById(R.id.pre_customer_email);
        pre_customer_delivery_charges = findViewById(R.id.pre_customer_delivery_charges);
        pre_customer_flat = findViewById(R.id.pre_customer_flat);
        pre_customer_address = findViewById(R.id.pre_customer_address);
        pre_customer_paper = findViewById(R.id.pre_customer_paper);
        pre_customer_paper_price = findViewById(R.id.pre_customer_paper_price);
        pre_customer_paper_qty = findViewById(R.id.pre_customer_paper_qty);
        pre_customer_start_date = findViewById(R.id.pre_customer_start_date);
        pre_customer_bills_via = findViewById(R.id.pre_customer_bills_via);
        pre_customer_delivery_days = findViewById(R.id.pre_customer_delivery_days);
        linearlayout_preview_details = findViewById(R.id.linearlayout_preview_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        builder = new AlertDialog.Builder(this);
        edit_detail = findViewById(R.id.edit_detail);
        btn_update = findViewById(R.id.btn_update);
        delete_detail = findViewById(R.id.delete_detail);
        setEnabledFalseView();
        loadingBar = new ProgressDialog(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        Toast.makeText(this, "" + key, Toast.LENGTH_SHORT).show();
        loadCustomerInfo();
        edit_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnabledTrueView();
                // Toast.makeText(PreviewCustomer.this, "Edit details", Toast.LENGTH_SHORT).show();
            }
        });

        delete_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure to delete record?");
                builder.setCancelable(false);

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        loadingBar.setTitle("Please Wait..");
//                        loadingBar.setMessage("We're removing Customer");
//                        loadingBar.show();
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("Customers").child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), Customer.class));
                                    Toast.makeText(PreviewCustomer.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                                    // loadingBar.dismiss();
                                } else {
                                    //loadingBar.dismiss();
                                    Snackbar snackbar = Snackbar.make(linearlayout_preview_details, "Something Went Wrong", Snackbar.LENGTH_SHORT);
                                    snackbar.show();
                                }
                            }
                        });
                    }
                });

                builder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDetail();
            }
        });
    }

    private void updateDetail() {
        days_num = pre_customer_days_num.getText().toString();
        name = pre_customer_name.getText().toString();
        mobile = pre_customer_mobile.getText().toString();
        mail = pre_customer_email.getText().toString();
        charges = pre_customer_delivery_charges.getText().toString();
        flat_no = pre_customer_flat.getText().toString();
        address = pre_customer_address.getText().toString();
        paper = pre_customer_paper.getText().toString();
        price = pre_customer_paper_price.getText().toString();
        qty = pre_customer_paper_qty.getText().toString();
        start_date = pre_customer_start_date.getText().toString();
        bill_via = pre_customer_bills_via.getText().toString();
        delivery_days = pre_customer_delivery_days.getText().toString();

        if (name.isEmpty()) {
            pre_customer_name.setError("Required Field");
            pre_customer_name.requestFocus();
            return;
        }
        if (mobile.isEmpty()) {
            pre_customer_mobile.setError("Required Field");
            pre_customer_mobile.requestFocus();
            return;
        }
        if (mail.isEmpty()) {
            pre_customer_email.setError("Required Field");
            pre_customer_email.requestFocus();
            return;
        }
        if (charges.isEmpty()) {
            pre_customer_delivery_charges.setError("Required Field");
            pre_customer_delivery_charges.requestFocus();
            return;
        }
        if (flat_no.isEmpty()) {
            pre_customer_flat.setError("Required Field");
            pre_customer_flat.requestFocus();
            return;
        }
        if (address.isEmpty()) {
            pre_customer_address.setError("Required Field");
            pre_customer_address.requestFocus();
            return;
        }
        if (paper.isEmpty()) {
            pre_customer_paper.setError("Required Field");
            pre_customer_paper.requestFocus();
            return;
        }
        if (price.isEmpty()) {
            pre_customer_paper_price.setError("Required Field");
            pre_customer_paper_price.requestFocus();
            return;
        }
        if (qty.isEmpty()) {
            pre_customer_paper_qty.setError("Required Field");
            pre_customer_paper_qty.requestFocus();
            return;
        }
        if (bill_via.isEmpty()) {
            pre_customer_bills_via.setError("Required Field");
            pre_customer_bills_via.requestFocus();
            return;
        }
        if (start_date.isEmpty()) {
            pre_customer_start_date.setError("Required Field");
            pre_customer_start_date.requestFocus();
            return;
        }
        if (delivery_days.isEmpty()) {
            pre_customer_delivery_days.setError("Required Field");
            pre_customer_delivery_days.requestFocus();
            return;
        }
        loadingBar.setMessage("Please wait, while we are updating your details...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        UploadCustomerData uploadCustomerData = new UploadCustomerData(address, mail, flat_no, mobile, name, start_date, charges, paper, price, qty, bill_via, days_num, delivery_days);
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).child("Customers").child(key).setValue(uploadCustomerData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    loadingBar.dismiss();
                    Snackbar snackbar = Snackbar.make(linearlayout_preview_details, "Detail Updated", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    setEnabledFalseView();
                } else {
                    Snackbar snackbar = Snackbar.make(linearlayout_preview_details, task.getException().getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void loadCustomerInfo() {
        loadingBar.setTitle("Please Wait...");
        loadingBar.setMessage("We're getting details.");
        loadingBar.setCancelable(false);
        loadingBar.show();
        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("Customers").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String address = dataSnapshot.child("customer_address").getValue().toString();
                String email = dataSnapshot.child("customer_email").getValue().toString();
                String flat_building = dataSnapshot.child("customer_flat_building").getValue().toString();
                String mobile = dataSnapshot.child("customer_mobile").getValue().toString();
                String name = dataSnapshot.child("customer_name").getValue().toString();
                String date = dataSnapshot.child("date").getValue().toString();
                String delivery_charge = dataSnapshot.child("delivery_charge").getValue().toString();
                String paper = dataSnapshot.child("paper").getValue().toString();
                String price = dataSnapshot.child("price").getValue().toString();
                String qty = dataSnapshot.child("quantity").getValue().toString();
                String bill_method = dataSnapshot.child("send_bill_via").getValue().toString();
                String days_of_delivery = dataSnapshot.child("delivery_days").getValue().toString();
                String No_of_days = dataSnapshot.child("days_num").getValue().toString();
                pre_customer_name.setText(name);
                pre_customer_mobile.setText(mobile);
                pre_customer_email.setText(email);
                pre_customer_delivery_charges.setText(delivery_charge);
                pre_customer_flat.setText(flat_building);
                pre_customer_address.setText(address);
                pre_customer_paper.setText(paper);
                pre_customer_paper_price.setText(price);
                pre_customer_paper_qty.setText(qty);
                pre_customer_start_date.setText(date);
                pre_customer_bills_via.setText(bill_method);
                pre_customer_delivery_days.setText(days_of_delivery);
                pre_customer_days_num.setText(No_of_days);
                loadingBar.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Snackbar snackbar = Snackbar.make(linearlayout_preview_details, databaseError.getMessage(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }

    private void setEnabledFalseView() {
        pre_customer_name.setEnabled(false);
        pre_customer_mobile.setEnabled(false);
        pre_customer_email.setEnabled(false);
        pre_customer_delivery_charges.setEnabled(false);
        pre_customer_flat.setEnabled(false);
        pre_customer_address.setEnabled(false);
        pre_customer_paper.setEnabled(false);
        pre_customer_paper_price.setEnabled(false);
        pre_customer_paper_qty.setEnabled(false);
        pre_customer_start_date.setEnabled(false);
        pre_customer_bills_via.setEnabled(false);
        pre_customer_delivery_days.setEnabled(false);
        pre_customer_days_num.setEnabled(false);
        btn_update.setVisibility(View.GONE);
    }

    private void setEnabledTrueView() {
        pre_customer_name.setEnabled(true);
        pre_customer_mobile.setEnabled(true);
        pre_customer_email.setEnabled(true);
        pre_customer_delivery_charges.setEnabled(true);
        pre_customer_flat.setEnabled(true);
        pre_customer_address.setEnabled(true);
        pre_customer_paper.setEnabled(true);
        pre_customer_paper_price.setEnabled(true);
        pre_customer_paper_qty.setEnabled(true);
        pre_customer_start_date.setEnabled(true);
        pre_customer_bills_via.setEnabled(true);
        pre_customer_delivery_days.setEnabled(true);
        btn_update.setVisibility(View.VISIBLE);
        pre_customer_name.requestFocus();
    }
}
