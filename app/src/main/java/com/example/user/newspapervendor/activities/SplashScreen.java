package com.example.user.newspapervendor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashScreen extends AwesomeSplash {


    @Override
    public void initSplash(ConfigSplash configSplash) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // getSupportActionBar().hide();
        //background
        configSplash.setOriginalHeight(WindowManager.LayoutParams.MATCH_PARENT);
        configSplash.setOriginalWidth(WindowManager.LayoutParams.MATCH_PARENT);
        configSplash.setBackgroundColor(R.color.bg_splash);
        configSplash.setAnimCircularRevealDuration(3000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);


        // Logo animation
        configSplash.setLogoSplash(R.drawable.paperdele_logo2);
        configSplash.setAnimLogoSplashDuration(2000);
        configSplash.setAnimLogoSplashTechnique(Techniques.RollIn);
        //configSplash.setAnimLogoSplashTechnique(Techniques.Flash);


        // title

        configSplash.setTitleSplash("PaperDele");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(30f);
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.DropOut);
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(SplashScreen.this, StartPage.class));
        finish();
    }
}
