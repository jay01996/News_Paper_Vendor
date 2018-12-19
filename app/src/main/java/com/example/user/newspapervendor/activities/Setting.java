package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Setting extends AppCompatActivity {
    ListView lv_settings;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        lv_settings = findViewById(R.id.lv_settings);
        mAuth = FirebaseAuth.getInstance();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Setting.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.settings));
        lv_settings.setAdapter(adapter);
        lv_settings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(Setting.this, UserProfile.class));
                        break;
                    case 1:
                        startActivity(new Intent(Setting.this, NewspaperMagazine.class));
                        break;
                    case 2:
                        startActivity(new Intent(Setting.this, BankDetails.class));
                        break;
                    case 3:
                        startActivity(new Intent(Setting.this, Area.class));
                        break;
                    case 4:
                        startActivity(new Intent(Setting.this, ManageHolidays.class));
                        break;
                    case 5:
                        //code for logout
                        mAuth.signOut();
                        Toast.makeText(Setting.this, "User Signing out...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Setting.this, StartPage.class));
                        break;
                    case 6:
                        //code for Delete user
                        FirebaseUser user = mAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            user.delete();
                            Toast.makeText(Setting.this, "Account Deleted.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Setting.this, StartPage.class));
                            finish();
                        } else {
                            Toast.makeText(Setting.this, "There is No User", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }
}
