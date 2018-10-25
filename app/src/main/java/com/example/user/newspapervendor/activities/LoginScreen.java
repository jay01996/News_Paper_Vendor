package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.newspapervendor.R;

public class LoginScreen extends AppCompatActivity {


    private Button btn_login;
    private TextView tv_forgot_password;
    private ImageView iv_back_button;
    private TextInputEditText edt_mobile_number, edt_password;
    Switch switch_show_password;
    String mobile, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        btn_login = findViewById(R.id.btn_login);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        iv_back_button = findViewById(R.id.iv_back_button);
        // show_password = findViewById(R.id.show_password);
        switch_show_password = findViewById(R.id.switch_show_password);
        edt_mobile_number = findViewById(R.id.edt_mobile_number);
        edt_password = findViewById(R.id.edt_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = edt_mobile_number.getText().toString().trim();
                password = edt_password.getText().toString().trim();

                if (TextUtils.isEmpty(mobile)) {
                    edt_mobile_number.setError("Please Enter Mobile No.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    edt_password.setError("Please Enter Password");
                    return;
                }
                Toast.makeText(LoginScreen.this, "Login SuccessFul", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginScreen.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, ForgotPasswordPage.class);
                startActivity(intent);
                finish();

            }
        });

        iv_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, StartPage.class);
                startActivity(intent);
                finish();
            }
        });


        if (switch_show_password.isChecked()) {
            edt_password.setTransformationMethod(null);
        }
        if (!switch_show_password.isChecked()) {
            edt_password.setTransformationMethod(new PasswordTransformationMethod());
        }
    }
}
