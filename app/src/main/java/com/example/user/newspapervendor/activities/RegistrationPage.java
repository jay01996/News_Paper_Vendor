package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.newspapervendor.R;

public class RegistrationPage extends AppCompatActivity {
    private EditText edt_user_name, edt_newspaper_agency_name, edt_mobile_number, edt_user_mail, edt_password, edt_pin_code, edt_address;
    private Button btn_cancel, btn_SignUp;
    private ImageView iv_back_button;

    String user_name, newspaper_agency_name, mobile, user_mail, password, pin_code, address;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        btn_SignUp = findViewById(R.id.btn_SignUp);
        btn_cancel = findViewById(R.id.btn_cancel);
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_newspaper_agency_name = findViewById(R.id.edt_newspaper_agency_name);
        edt_mobile_number = findViewById(R.id.edt_mobile_number);
        edt_user_mail = findViewById(R.id.edt_user_mail);
        edt_password = findViewById(R.id.edt_password);
        edt_pin_code = findViewById(R.id.edt_pin_code);
        edt_address = findViewById(R.id.edt_address);
        iv_back_button = findViewById(R.id.iv_back_button);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationPage.this, StartPage.class);
                startActivity(intent);
                finish();
            }
        });

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                user_name = edt_user_name.getText().toString().trim();
                newspaper_agency_name = edt_newspaper_agency_name.getText().toString().trim();
                mobile = edt_mobile_number.getText().toString().trim();
                user_mail = edt_user_mail.getText().toString().trim();
                password = edt_password.getText().toString().trim();
                pin_code = edt_pin_code.getText().toString().trim();
                address = edt_address.getText().toString().trim();


                if (TextUtils.isEmpty(user_name)) {
                    edt_user_name.setError("Fill Name");
                    return;
                }
                if (TextUtils.isEmpty(newspaper_agency_name)) {
                    edt_newspaper_agency_name.setError("Fill Agency Name");
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    edt_mobile_number.setError("Fill Mobile No");
                    return;
                }
                if (TextUtils.isEmpty(user_mail)) {
                    edt_user_mail.setError("Fill User Mail");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    edt_password.setError("Fill Password");
                    return;
                }

                if (TextUtils.isEmpty(pin_code)) {
                    edt_pin_code.setError("Fill Pin Code");
                    return;
                }

                Intent intent = new Intent(RegistrationPage.this, Registration_Successful_page.class);
                startActivity(intent);
                finish();
            }
        });

        iv_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationPage.this, StartPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
