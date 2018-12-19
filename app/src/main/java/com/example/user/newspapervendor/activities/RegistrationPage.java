package com.example.user.newspapervendor.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.newspapervendor.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_user_name, edt_newspaper_agency_name, edt_mobile_number, edt_user_mail, edt_password, edt_pin_code, edt_address;
    private Button btn_SignUp, btn_google, btn_fb;
    String user_name, newspaper_agency_name, mobile, user_mail, password, pin_code, address;
    FirebaseAuth mAuth;
    LinearLayout linearlayout_signup;
    private ProgressDialog loadingBar;
    private LinearLayout linear_layout_signup;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        btn_google = findViewById(R.id.btn_google_sign_up);
        btn_fb = findViewById(R.id.btn_fb_sign_up);
        btn_SignUp = findViewById(R.id.btn_signup_user);
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_newspaper_agency_name = findViewById(R.id.edt_newspaper_agency_name);
        edt_mobile_number = findViewById(R.id.edt_mobile_number);
        edt_user_mail = findViewById(R.id.edt_user_mail);
        edt_password = findViewById(R.id.edt_password);
        edt_pin_code = findViewById(R.id.edt_pin_code);
        edt_address = findViewById(R.id.edt_address);
        linearlayout_signup = findViewById(R.id.linearlayout_signup);
        btn_SignUp.setOnClickListener(this);
        btn_fb.setOnClickListener(this);
        btn_google.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        linear_layout_signup = findViewById(R.id.linear_layout_signup);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        loadingBar = new ProgressDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            // Handle the already login user
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup_user:
                registerUser();
                break;
            case R.id.btn_google_sign_up:
                createGoogleAccount();
                break;
            case R.id.btn_fb_sign_up:
                createFacebookAccount();
                break;
        }
    }

    private void createFacebookAccount() {
        Snackbar snackbar = Snackbar.make(linear_layout_signup, "FaceBook Clicked", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private void createGoogleAccount() {
        Snackbar snackbar = Snackbar.make(linear_layout_signup, "Google Clicked", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }


    private void registerUser() {

        user_name = edt_user_name.getText().toString().trim();
        newspaper_agency_name = edt_newspaper_agency_name.getText().toString().trim();
        mobile = edt_mobile_number.getText().toString().trim();
        user_mail = edt_user_mail.getText().toString().trim();
        password = edt_password.getText().toString().trim();
        pin_code = edt_pin_code.getText().toString().trim();
        address = edt_address.getText().toString().trim();

        if (TextUtils.isEmpty(user_name)) {
            edt_user_name.setError("Fill Name");
            edt_user_name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(newspaper_agency_name)) {
            edt_newspaper_agency_name.setError("Fill Agency Name");
            edt_newspaper_agency_name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mobile)) {
            edt_mobile_number.setError("Fill Mobile No");
            edt_mobile_number.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(user_mail)) {
            edt_user_mail.setError("Fill User Mail");
            edt_user_mail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user_mail).matches()) {
            edt_user_mail.setError("Please enter a valid email");
            edt_user_mail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edt_password.setError("Fill Password");
            edt_password.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(pin_code)) {
            edt_pin_code.setError("Fill Pin Code");
            edt_pin_code.requestFocus();
            return;
        }

        loadingBar.setMessage("Please Wait...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        mAuth.createUserWithEmailAndPassword(user_mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(user_name, user_mail, mobile, newspaper_agency_name, pin_code, address);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            loadingBar.dismiss();
                            if (task.isSuccessful()) {
                                Snackbar snackbar = Snackbar.make(linearlayout_signup, getString(R.string.registration_success), Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                startActivity(new Intent(RegistrationPage.this, Registration_Successful_page.class));
                                finish();
                            } else {
                                Snackbar snackbar = Snackbar.make(linearlayout_signup, "Details Couldn't Save, try again!!", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                loadingBar.dismiss();
                            }
                        }
                    });

                } else {
                    Snackbar snackbar = Snackbar.make(linearlayout_signup, task.getException().getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                    loadingBar.dismiss();
                }
            }
        });
    }
}
