package com.example.user.newspapervendor.activities;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contact extends AppCompatActivity {
    EditText user_name, user_mail, mobile_number, user_city, user_message;
    Button btn_submit;
    String name, email, mobile, city, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        user_name = findViewById(R.id.edt_contact_user_name);
        user_mail = findViewById(R.id.edt_contact_user_mail);
        mobile_number = findViewById(R.id.edt_contact_mobile);
        user_city = findViewById(R.id.edt_contact_city);
        user_message = findViewById(R.id.edt_contact_message);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = user_name.getText().toString().trim();
                email = user_mail.getText().toString().trim();
                mobile = mobile_number.getText().toString().trim();
                city = user_city.getText().toString().trim();
                message = user_message.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    user_name.setError("Fill Name");
                    user_name.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(mobile)) {
                    mobile_number.setError("Fill Mobile");
                    mobile_number.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    user_mail.setError("Fill Email");
                    user_mail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(city)) {
                    user_city.setError("Fill City");
                    user_city.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(message)) {
                    user_message.setError("Fill Name");
                    user_message.requestFocus();
                }
                Toast.makeText(Contact.this, "Your Response has been submitted", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
