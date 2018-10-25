package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.newspapervendor.R;

public class Registration_Successful_page extends AppCompatActivity {
private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__successful_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration_Successful_page.this,LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
