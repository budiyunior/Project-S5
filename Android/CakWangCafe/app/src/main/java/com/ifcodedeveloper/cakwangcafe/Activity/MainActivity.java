package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.linear);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        constraintLayout.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent open = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(open);
                finish();
            }
        }, 3000);
        TimeSet();
    }

    void TimeSet() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 8 && timeOfDay < 17) {
            Toast.makeText(this, "Shift Pagi", Toast.LENGTH_SHORT).show();
        } else if (timeOfDay >= 17) {
            Toast.makeText(this, "Shift Sore", Toast.LENGTH_SHORT).show();
        } else if (timeOfDay == 0) {
            Toast.makeText(this, "Shift Sore", Toast.LENGTH_SHORT).show();
        } else  {
            Toast.makeText(this, "Cafe Tutup", Toast.LENGTH_SHORT).show();
        }
    }
}
