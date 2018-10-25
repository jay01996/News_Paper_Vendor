
package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.newspapervendor.activities.R;

public class HomePage extends AppCompatActivity {

    CardView cv_cash_collection, cv_pending_amount, cv_online_payment, cv_bank_transfer, cv_customers, cv_bill_collection;
    CardView cv_bank_transfer_states, cv_stop_delivery, cv_coupon_expiry, cv_monthly_statement, cv_settings, cv_training, cv_testimonial;
    TextView tv_support, tv_contact;
    ImageView iv_notification;
    LinearLayout linearlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        linearlayout=findViewById(R.id.linearlayout);

        iv_notification = findViewById(R.id.iv_notification);
        cv_cash_collection = findViewById(R.id.cv_cash_collection);
        cv_pending_amount = findViewById(R.id.cv_pending_amount);
        cv_online_payment = findViewById(R.id.cv_online_payment);
        cv_bank_transfer = findViewById(R.id.cv_bank_transfer);
        cv_customers = findViewById(R.id.cv_customers);
        cv_bill_collection = findViewById(R.id.cv_bill_collection);
        cv_bank_transfer_states = findViewById(R.id.cv_bank_transfer_states);
        cv_stop_delivery = findViewById(R.id.cv_stop_delivery);
        cv_coupon_expiry = findViewById(R.id.cv_coupon_expiry);
        cv_monthly_statement = findViewById(R.id.cv_monthly_statement);
        cv_settings = findViewById(R.id.cv_settings);
        cv_training = findViewById(R.id.cv_training);
        cv_testimonial = findViewById(R.id.cv_testimonial);
        tv_support = findViewById(R.id.tv_support);
        tv_contact = findViewById(R.id.tv_contact);

        iv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Notification.class);
                startActivity(intent);
                Snackbar snackbar = Snackbar.make(linearlayout, "Notification is clicked by you", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
        cv_cash_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Notification.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Cash Collection is working fine", Toast.LENGTH_SHORT).show();

            }
        });
        cv_pending_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Notification.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Pending amount is working fine", Toast.LENGTH_SHORT).show();


            }
        });
        cv_online_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Notification.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Online Collection is working fine", Toast.LENGTH_SHORT).show();

            }
        });

        cv_bank_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Notification.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Bank transfer is working fine", Toast.LENGTH_SHORT).show();

            }
        });
        cv_customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Customers.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Customers", Toast.LENGTH_SHORT).show();

            }
        });
        cv_bill_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Bill_Collection.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Bill Collection ", Toast.LENGTH_SHORT).show();

            }
        });
        cv_bank_transfer_states.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Bank_Transfer_States.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Bank transfer", Toast.LENGTH_SHORT).show();
            }
        });
        cv_stop_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Stop_delivery.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Stop Delivery", Toast.LENGTH_SHORT).show();
            }
        });
        cv_coupon_expiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Coupon_Expiry.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Coupon Expiry", Toast.LENGTH_SHORT).show();
            }
        });
        cv_monthly_statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Monthly_Statement.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Monthly Statement", Toast.LENGTH_SHORT).show();
            }
        });
        cv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Setting.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Settings", Toast.LENGTH_SHORT).show();
            }
        });
        cv_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Notification.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Training", Toast.LENGTH_SHORT).show();
            }
        });
        cv_testimonial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Testimonial.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Testimonial", Toast.LENGTH_SHORT).show();
            }
        });
        tv_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Support.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Support", Toast.LENGTH_SHORT).show();
            }
        });
        tv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Contact.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Contact", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
