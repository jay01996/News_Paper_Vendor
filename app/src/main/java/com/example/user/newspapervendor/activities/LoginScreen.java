package com.example.user.newspapervendor.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button btn_login, btn_google, btn_fb;
    private LinearLayout linear_layout_login;
    TextView tv_forgot_password;
    EditText edt_login_email, edt_password;
    Switch switch_show_password;
    String email, password;
    private ProgressDialog loadingBar;

    public LoginScreen() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();
        btn_google = findViewById(R.id.btn_google_sign_in);
        btn_fb = findViewById(R.id.btn_fb_sign_in);
        btn_login = findViewById(R.id.btn_signin);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        switch_show_password = findViewById(R.id.switch_show_password);
        edt_login_email = findViewById(R.id.edt_login_email);
        linear_layout_login = findViewById(R.id.linear_layout_login);
        edt_password = findViewById(R.id.edt_login_password);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        loadingBar = new ProgressDialog(this);
        edt_login_email.clearFocus();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                loginAccount();
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

        switch_show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_show_password.isChecked()) {
                    edt_password.setTransformationMethod(null);
                } else {
                    edt_password.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(linear_layout_login, "Google Clicked", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        btn_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(linear_layout_login, "Facebook Clicked", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

    }

    private void loginAccount() {
        // clearFocus();
        email = edt_login_email.getText().toString().trim();
        password = edt_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            edt_login_email.setError("Please Enter Mobile No.");
            edt_login_email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            edt_password.setError("Please Enter Password");
            edt_password.requestFocus();
            return;
        }

        loadingBar.setMessage("Please Wait...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        edt_login_email.clearFocus();
        edt_password.clearFocus();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loadingBar.dismiss();
                    startActivity(new Intent(LoginScreen.this, HomePage.class));
                    finish();
                } else {
                    Snackbar snackbar = Snackbar.make(linear_layout_login, task.getException().getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            //Toast.makeText(this, "user already login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginScreen.this, HomePage.class));
            finish();
        }
    }

    public void clearFocus() {
        edt_login_email.clearFocus();
        edt_password.clearFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
