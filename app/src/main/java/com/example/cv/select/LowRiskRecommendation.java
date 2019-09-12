package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LowRiskRecommendation extends AppCompatActivity {
    private Toolbar toolbar;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_risk_recommendation);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        // toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        home=(Button)findViewById(R.id.lowrisk_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LowRiskRecommendation.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
