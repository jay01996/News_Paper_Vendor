
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

import java.util.Calendar;

public class HomePage extends AppCompatActivity {

    CardView cv_cash_collection, cv_pending_amount, cv_online_payment, cv_bank_transfer, cv_customers, cv_bill_collection;
    CardView cv_bank_transfer_states, cv_stop_delivery, cv_coupon_expiry, cv_monthly_statement, cv_settings, cv_loan, cv_testimonial;
    TextView tv_support, tv_contact, tv_current_date;
    private TextView bank_transfer, online_payment, cash_collection, pending_amt;
    ImageView iv_notification;
    LinearLayout linearlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        linearlayout = findViewById(R.id.linearlayout);
        tv_current_date = findViewById(R.id.tv_current_date);
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
        cv_loan = findViewById(R.id.cv_loan);
        cv_testimonial = findViewById(R.id.cv_testimonial);
        tv_support = findViewById(R.id.tv_support);
        tv_contact = findViewById(R.id.tv_contact);
        pending_amt = findViewById(R.id.tv_pending_amount);
        online_payment = findViewById(R.id.tv_online_payment);
        cash_collection = findViewById(R.id.tv_cash_collected);
        bank_transfer = findViewById(R.id.tv_bank_transfer_amt);
        pending_amt.setText("₹" + String.valueOf(2546));
        online_payment.setText("₹" + String.valueOf(2546));
        bank_transfer.setText("₹" + String.valueOf(963));
        cash_collection.setText("₹" + String.valueOf(8786));
        Calendar cal = Calendar.getInstance();
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String month = monthName[cal.get(Calendar.MONTH)];
        int year = cal.get(Calendar.YEAR);
        tv_current_date.setText(month + "\n" + year);


        iv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                startActivity(intent);
                // Snackbar snackbar = Snackbar.make(linearlayout, "Notification is clicked by you", Snackbar.LENGTH_SHORT);
                // snackbar.show();
            }
        });

        cv_cash_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(getApplicationContext(), Notification.class);
                //   startActivity(intent);
            }
        });

        cv_pending_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(getApplicationContext(), Notification.class);
                //  startActivity(intent);


            }
        });

        cv_online_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    Intent intent = new Intent(getApplicationContext(), Notification.class);
                //   startActivity(intent);
            }
        });

        cv_bank_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(getApplicationContext(), Notification.class);
                //   startActivity(intent);


            }
        });
        cv_customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Customer.class);
                startActivity(intent);


            }
        });
        cv_bill_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Bill_Collection.class);
                startActivity(intent);


            }
        });
        cv_bank_transfer_states.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Bank_Transfer_States.class);
                startActivity(intent);

            }
        });
        cv_stop_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Stop_delivery.class);
                startActivity(intent);

            }
        });
        cv_coupon_expiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Coupon_Expiry.class);
                startActivity(intent);

            }
        });
        cv_monthly_statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Monthly_Statement.class);
                startActivity(intent);

            }
        });
        cv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);
            }
        });


        cv_testimonial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Testimonial.class);
                startActivity(intent);
                //  Toast.makeText(HomePage.this, "Testimonial", Toast.LENGTH_SHORT).show();
            }
        });

        cv_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Loan.class);
                startActivity(intent);
                // Toast.makeText(HomePage.this, "Loan", Toast.LENGTH_SHORT).show();
            }
        });
        tv_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Support.class);
                startActivity(intent);
                // Toast.makeText(HomePage.this, "Support", Toast.LENGTH_SHORT).show();
            }
        });

        tv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Contact.class);
                startActivity(intent);
                //   Toast.makeText(HomePage.this, "Contact", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
