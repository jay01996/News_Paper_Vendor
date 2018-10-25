package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.user.newspapervendor.R;

public class StartPage extends AppCompatActivity {
private Button btn_Login,btn_SignUp;
private ImageView start_page_image;
int state =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        btn_Login=findViewById(R.id.btn_Login);
        btn_SignUp=findViewById(R.id.btn_SignUp);
        start_page_image=findViewById(R.id.start_page_image);
        Animation aninImage=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
        start_page_image.setAnimation(aninImage);

        /*if (state==0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation aninImage=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
                    start_page_image.setAnimation(aninImage);
                }
            }, 3000);
            state++;
        }
        if (state==1)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation aninImage=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
                    start_page_image.setAnimation(aninImage);
                }
            }, 3000);
            state--;
        }*/


        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation=AnimationUtils.loadAnimation(StartPage.this,R.anim.bounce);
                btn_Login.startAnimation(animation);
                Intent intent=new Intent(StartPage.this,LoginScreen.class);
                startActivity(intent);

            }
        });

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation=AnimationUtils.loadAnimation(StartPage.this,R.anim.fadein);
                btn_SignUp.startAnimation(animation);
                Intent intent=new Intent(StartPage.this,RegistrationPage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
       // Animation aninImage=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
       // start_page_image.setAnimation(aninImage);
    }
}
