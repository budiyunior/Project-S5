package com.ifcodedeveloper.cakwangcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.ifcodedeveloper.cakwangcafe.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linear);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        linearLayout.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent open = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(open);
                finish();
            }
        }, 3000);
    }
}
