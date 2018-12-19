package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordPage extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_reset_email;
    //  private Button btn_reset_password;
    private String email;
    private FirebaseAuth mAuth;
    private LinearLayout linearlayout_reset_pass;
    private TextView progress_bar_reset_text;
    private ProgressBar progress_bar_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);

        findViewById(R.id.btn_reset_password).setOnClickListener(this);
        edt_reset_email = findViewById(R.id.edt_reset_email);
        mAuth = FirebaseAuth.getInstance();
        linearlayout_reset_pass = findViewById(R.id.linearlayout_reset_pass);
        progress_bar_reset_text = findViewById(R.id.progress_bar_reset_text);
        progress_bar_reset = findViewById(R.id.progress_bar_reset);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset_password:
                resetPassword();
                break;
        }
    }

    private void resetPassword() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        email = edt_reset_email.getText().toString();
        if (email.isEmpty()) {
            edt_reset_email.setError("Fill Email");
            edt_reset_email.requestFocus();
            return;
        }
        progress_bar_reset.setVisibility(View.VISIBLE);
        progress_bar_reset_text.setVisibility(View.VISIBLE);
        linearlayout_reset_pass.setAlpha((float) 0.2);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progress_bar_reset.setVisibility(View.GONE);
                    progress_bar_reset_text.setVisibility(View.GONE);
                    linearlayout_reset_pass.setAlpha(1);
                    edt_reset_email.setText("");
                    final Snackbar snackbar = Snackbar.make(linearlayout_reset_pass, "A Link for reset Password has been sent to your registered email", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Login", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(ForgotPasswordPage.this, LoginScreen.class));
                            finish();
                            snackbar.dismiss();

                        }
                    });
                    snackbar.show();
                } else {
                    progress_bar_reset.setVisibility(View.GONE);
                    progress_bar_reset_text.setVisibility(View.GONE);
                    linearlayout_reset_pass.setAlpha(1);
                    edt_reset_email.setText("");
                    Snackbar snackbar = Snackbar.make(linearlayout_reset_pass, task.getException().getMessage(), Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });


    }
}
