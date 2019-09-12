package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class HighRecommendation extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private Button teleconsultation,home;
    private String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_recommendation);


        final Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
         toolbar.setTitle("");
        // toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        teleconsultation=(Button)findViewById(R.id.teleconsultation);
        teleconsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HighRecommendation.this, Teleconsulation.class);
                intent.putExtra("ContactNo",ContactNo);
               startActivity(intent);
            }
        });
        home=(Button)findViewById(R.id.highrisk_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HighRecommendation.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
