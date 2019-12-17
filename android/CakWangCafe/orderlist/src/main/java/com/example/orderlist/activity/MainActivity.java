package com.example.orderlist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.orderlist.R;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.linear);


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
