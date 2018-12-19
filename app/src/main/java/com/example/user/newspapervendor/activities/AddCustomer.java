package com.example.user.newspapervendor.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.newspapervendor.UploadCustomerData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;


public class AddCustomer extends AppCompatActivity {

    AutoCompleteTextView add_paper;
    Button btn_next, btn_save_user;
    LinearLayout linearlayout_customer_basic_details, linearlayout_customer_paper_details, linearlayout_specific_days;
    EditText start_paper_date;
    Calendar myCalendar = Calendar.getInstance();
    ImageView iv_minus, iv_plus;
    EditText edt_quantity, edt_paper_price;
    RadioGroup radioBox_days;
    RadioButton rb_alldays, rb_somedays;
    EditText txt_customer_name, txt_mobile_no, txt_email, txt_delivery_charges, txt_address, txt_flat_building;
    CheckBox cb_message, cb_email;
    String qty;
    TextView tv_go_back;
    String customer_name, customer_mobile, customer_email, delivery_charge, customer_address, customer_flat_building;
    String paper, date, quantity;
    int price;
    private int day = 0;
    private String day_name_mon = "";
    private String day_name_tues = "";
    private String day_name_wed = "";
    private String day_name_thurs = "";
    private String day_name_fri = "";
    private String day_name_sat = "";
    private String day_name_sun = "";
    private String bill_via_sms = "";
    private String bill_via_mail = "";
    private String delivery_days;
    private String send_bill_via;

    CheckBox day_mon, day_tues, day_wed, day_thurs, day_fri, day_sat, day_sun;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    int qty_no;
    private ProgressDialog loadingBar;
    FirebaseAuth mAuth;
    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    private static final AtomicInteger count = new AtomicInteger(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);


        txt_customer_name = findViewById(R.id.txt_customer_name);
        txt_mobile_no = findViewById(R.id.txt_mobile_no);
        txt_email = findViewById(R.id.txt_email);
        txt_delivery_charges = findViewById(R.id.txt_delivery_charges);
        txt_address = findViewById(R.id.txt_address);
        txt_flat_building = findViewById(R.id.txt_flat_building);
        add_paper = findViewById(R.id.add_paper);
        btn_next = findViewById(R.id.btn_next);
        btn_save_user = findViewById(R.id.btn_save_user);
        iv_minus = findViewById(R.id.iv_minus);
        iv_plus = findViewById(R.id.iv_plus);
        linearlayout_customer_basic_details = findViewById(R.id.linearlayout_customer_basic_details);
        linearlayout_customer_paper_details = findViewById(R.id.linearlayout_customer_paper_details);
        linearlayout_specific_days = findViewById(R.id.linearlayout_specific_days);
        start_paper_date = findViewById(R.id.edt_start_date);
        edt_quantity = findViewById(R.id.edt_quantity);
        edt_paper_price = findViewById(R.id.edt_paper_price);
        radioBox_days = findViewById(R.id.radioBox_days);
        rb_alldays = findViewById(R.id.rb_alldays);
        rb_somedays = findViewById(R.id.rb_somedays);
        cb_email = findViewById(R.id.cb_email);
        cb_message = findViewById(R.id.cb_message);
        tv_go_back = findViewById(R.id.tv_go_back);
        day_mon = findViewById(R.id.day_mon);
        day_tues = findViewById(R.id.day_tues);
        day_wed = findViewById(R.id.day_wed);
        day_thurs = findViewById(R.id.day_thurs);
        day_fri = findViewById(R.id.day_fri);
        day_sat = findViewById(R.id.day_sat);
        day_sun = findViewById(R.id.day_sun);
        loadingBar = new ProgressDialog(AddCustomer.this);
        mAuth = FirebaseAuth.getInstance();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        linearlayout_customer_paper_details.setVisibility(View.GONE);
        linearlayout_specific_days.setVisibility(View.GONE);
        edt_quantity.setText("1");
        edt_paper_price.setText("0");
        String papers[] = getResources().getStringArray(R.array.all_papers);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, papers);
        add_paper.setAdapter(adapter);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUser()) {
                    linearlayout_customer_basic_details.setVisibility(View.GONE);
                    linearlayout_customer_paper_details.setVisibility(View.VISIBLE);
                }
            }
        });
        txt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = txt_email.getText().toString();
                if (!email.matches(emailPattern)) txt_email.setError("Invalid Email");
            }
        });

        btn_save_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCustomerInfo();
            }
        });

        tv_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearlayout_customer_basic_details.setVisibility(View.VISIBLE);
                linearlayout_customer_paper_details.setVisibility(View.GONE);
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };


        start_paper_date.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddCustomer.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        iv_minus.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                qty = edt_quantity.getText().toString();
                qty_no = Integer.parseInt(qty);
                if (qty_no > 1) {
                    qty_no--;
                    edt_quantity.setText(String.valueOf(qty_no));
                } else {
                    edt_quantity.setText(String.valueOf(qty_no));
                }
            }
        });

        iv_plus.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                qty = edt_quantity.getText().toString();
                qty_no = Integer.parseInt(qty);
                qty_no++;
                edt_quantity.setText(String.valueOf(qty_no));
            }
        });

        radioBox_days.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_alldays) {
                    linearlayout_specific_days.setVisibility(View.GONE);

                } else if (checkedId == R.id.rb_somedays) {
                    linearlayout_specific_days.setVisibility(View.VISIBLE);
                }
            }
        });

        //*******************SELECT DELIVERY DAYS******************************
        day_mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (day_mon.isChecked()) {
                    day++;
                    day_name_mon = "Mon";
                    Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                } else {
                    if (day > 0) {
                        day--;
                        day_name_mon = "";
                        Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        day_tues.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (day_tues.isChecked()) {
                    day++;
                    day_name_tues = "Tues";
                    Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                } else {
                    if (day > 0) {
                        day--;
                        day_name_tues = "";
                        Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        day_wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (day_wed.isChecked()) {
                    day++;
                    day_name_wed = "Wed";
                    Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                } else {
                    if (day > 0) {
                        day--;
                        day_name_wed = "";
                        Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        day_thurs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (day_thurs.isChecked()) {
                    day++;
                    day_name_thurs = "Thurs";
                    Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                } else {
                    if (day > 0) {
                        day--;
                        day_name_thurs = "";
                        Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        day_fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (day_fri.isChecked()) {
                    day++;
                    day_name_fri = "Fri";
                    Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                } else {
                    if (day > 0) {
                        day--;
                        day_name_fri = "";
                        Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        day_sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (day_sat.isChecked()) {
                    day++;
                    day_name_sat = "Sat";
                    Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                } else {
                    if (day > 0) {
                        day--;
                        day_name_sat = "";
                        Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        day_sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (day_sun.isChecked()) {
                    day++;
                    day_name_sun = "Sun";
                    Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                } else {
                    if (day > 0) {
                        day--;
                        day_name_sun = "";
                        Toast.makeText(AddCustomer.this, "" + day, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cb_email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb_email.isChecked()) {
                    bill_via_mail = "Email";
                } else {
                    bill_via_mail = "";
                }
            }
        });
        cb_message.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cb_message.isChecked()) {
                    bill_via_sms = "SMS";
                } else {
                    bill_via_sms = "";
                }
            }
        });
    }

    private boolean validateUser() {

        customer_name = txt_customer_name.getText().toString();
        customer_mobile = txt_mobile_no.getText().toString();
        customer_email = txt_email.getText().toString();
        customer_address = txt_address.getText().toString();
        customer_flat_building = txt_flat_building.getText().toString();
        delivery_charge = txt_delivery_charges.getText().toString();

        if (TextUtils.isEmpty(customer_name)) {
            txt_customer_name.setError("Required");
            txt_customer_name.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(customer_mobile)) {
            txt_mobile_no.setError("Required");
            txt_mobile_no.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(customer_email)) {
            txt_email.setError("Required");
            txt_email.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(delivery_charge)) {
            txt_delivery_charges.setError("Required");
            txt_delivery_charges.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(customer_flat_building)) {
            txt_flat_building.setError("Required");
            txt_flat_building.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(customer_address)) {
            txt_address.setError("Required");
            txt_address.requestFocus();
            return false;
        }

        if (!cb_email.isChecked() && !cb_message.isChecked()) {
            Toast.makeText(AddCustomer.this, "Choose at least one for send bills", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void saveCustomerInfo() {
        final int id = count.incrementAndGet();
        paper = add_paper.getText().toString();
        price = Integer.parseInt(edt_paper_price.getText().toString());
        date = start_paper_date.getText().toString();
        quantity = edt_quantity.getText().toString();

        if (TextUtils.isEmpty(paper)) {
            add_paper.setError("Select a papers");
            add_paper.requestFocus();
            return;
        }

        if (price <= 0 || TextUtils.isEmpty(String.valueOf(price))) {
            edt_paper_price.setError("Paper Price Invalid");
            edt_paper_price.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(date)) {
            start_paper_date.setError("Select Date");
            start_paper_date.requestFocus();
            return;
        }
        if (rb_somedays.isChecked()) {
            if (!day_mon.isChecked() && !day_tues.isChecked() && !day_wed.isChecked() && !day_thurs.isChecked() && !day_fri.isChecked() && !day_sat.isChecked() && !day_sun.isChecked()) {
                Toast.makeText(AddCustomer.this, "Choose at least one day for delivery", Toast.LENGTH_SHORT).show();
                return;
            } else {
                delivery_days = day_name_mon + " " + day_name_tues + " " + day_name_wed + " " + day_name_thurs + " " + day_name_fri + " " + day_name_sat + " " + day_name_sun;
            }
        }
        if (rb_alldays.isChecked()) {
            delivery_days = "All days";
            day = 7;
            Toast.makeText(AddCustomer.this, "All days", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cb_message.isChecked()) {
            send_bill_via = bill_via_sms;
            return;
        }
        if (cb_email.isChecked()) {
            send_bill_via = bill_via_mail;
            return;
        }
        if (cb_email.isChecked() && cb_message.isChecked()) {
            send_bill_via = bill_via_sms + " " + bill_via_mail;
        }


        loadingBar.setTitle("Please wait");
        loadingBar.setMessage("We're adding Customers");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        UploadCustomerData uploadCustomerData = new UploadCustomerData(customer_name, customer_mobile, customer_email, delivery_charge, customer_address, customer_flat_building, paper, date, quantity, String.valueOf(price), send_bill_via, String.valueOf(day), delivery_days);
        FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid()).child("Customers").child(String.valueOf(id)).setValue(uploadCustomerData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Snackbar snackbar = Snackbar.make(linearlayout_customer_basic_details, "Customer Added Successfully", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    linearlayout_customer_basic_details.setVisibility(View.VISIBLE);
                    linearlayout_customer_paper_details.setVisibility(View.GONE);
                    // startActivity(new Intent(AddCustomer.this, Customer.class));
                    // finish();
                    loadingBar.dismiss();
                } else {
                    loadingBar.dismiss();
                    Snackbar snackbar = Snackbar.make(linearlayout_customer_basic_details, task.getException().getMessage(), Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    linearlayout_customer_basic_details.setVisibility(View.VISIBLE);
                    linearlayout_customer_paper_details.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        start_paper_date.setText(sdf.format(myCalendar.getTime()));
    }
}