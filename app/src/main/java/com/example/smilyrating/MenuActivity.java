package com.example.smilyrating;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hsalf.smileyrating.SmileyRating;

public class MenuActivity extends AppCompatActivity {

    private SmileyRating smiley1;
    private SmileyRating smiley2;
    private SmileyRating smiley3;
    private SmileyRating smiley4;
    private SmileyRating smiley5;
    private SmileyRating smiley6;
    private SmileyRating smiley7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        smiley1 = findViewById(R.id.smiley1);
        smiley2 = findViewById(R.id.smiley2);
        smiley3 = findViewById(R.id.smiley3);
        smiley4 = findViewById(R.id.smiley4);
        smiley5 = findViewById(R.id.smiley5);
        smiley6 = findViewById(R.id.smiley6);
        smiley7 = findViewById(R.id.smiley7);

        smiley1.setSmileySelectedListener(new SmileyRating.OnSmileySelectedListener() {
            @Override
            public void onSmileySelected(SmileyRating.Type type, boolean fromUser) {
                Toast.makeText(MenuActivity.this, type.getRating() + " fU:" + fromUser, Toast.LENGTH_SHORT).show();
            }
        });

        //noinspection deprecation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                smiley1.setRating(1, true);
                smiley2.setRating(2, true);
                smiley3.setRating(3, true);
                smiley4.setRating(4, true);
                smiley5.setRating(5, true);
                smiley6.setRating(6, true);
                smiley7.setRating(7, true);
            }
        }, 2000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                smiley1.setRating(1, true);
            }
        }, 10000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                smiley1.setRating(1, true);
            }
        }, 14000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                smiley1.setRating(1, true);
            }
        }, 16000);
    }
}
