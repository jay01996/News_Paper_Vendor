package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.newspapervendor.R;

public class ForgotPasswordPage extends AppCompatActivity {
private EditText edt_mobile_number,edt_otp,edt_new_password;
private Button btn_get_otp,btn_verify,btn_save_password;
private String mobile,new_password,verification_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);

        edt_mobile_number=findViewById(R.id.edt_mobile_number);
        edt_new_password=findViewById(R.id.edt_new_password);
        edt_otp=findViewById(R.id.edt_otp);
        btn_get_otp=findViewById(R.id.btn_get_otp);
        btn_save_password=findViewById(R.id.btn_save_password);
        btn_verify=findViewById(R.id.btn_verify);

        btn_get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile=edt_mobile_number.getText().toString().trim();
                if (TextUtils.isEmpty(mobile))
                {
                    edt_mobile_number.setError("Enter Mobile");
                    return;
                }
                Toast.makeText(ForgotPasswordPage.this, "Enter Mobile"+mobile, Toast.LENGTH_SHORT).show();
            }
        });

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification_code=edt_otp.getText().toString().trim();
                if (TextUtils.isEmpty(verification_code))
                {
                    edt_otp.setError("Enter OTP");
                    return;
                }
                Toast.makeText(ForgotPasswordPage.this, "Enter OTP "+verification_code, Toast.LENGTH_SHORT).show();
            }
        });


        btn_save_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_password=edt_new_password.getText().toString().trim();
                if (TextUtils.isEmpty(mobile))
                {
                    edt_new_password.setError("Enter New Password");
                    return;
                }
                Toast.makeText(ForgotPasswordPage.this, "Enter Mobile"+new_password, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ForgotPasswordPage.this,LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
