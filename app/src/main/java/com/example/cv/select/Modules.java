package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Modules extends AppCompatActivity {
    private Button btn_risktriageCVA, btn_risktriageMI, btn_IDRSmodified, btn_physicalActivity, btn_tobaccoSmoking, btn_dietLifestyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        btn_risktriageCVA=(Button)findViewById(R.id.btn_risktriageCVA);
        btn_risktriageMI=(Button)findViewById(R.id.btn_risktriageMI);
        btn_IDRSmodified=(Button)findViewById(R.id.btn_IDRSmodified);
        btn_physicalActivity=(Button)findViewById(R.id.btn_physicalActivity);
        btn_tobaccoSmoking=(Button)findViewById(R.id.btn_tobaccoSmoking);
        btn_dietLifestyle=(Button)findViewById(R.id.btn_dietLifestyle);

        btn_risktriageCVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Modules.this, RiskAndTriageCVA.class);
                startActivity(intent);
            }
        });

        btn_risktriageMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Modules.this, RiskAndTriageMI.class);
                startActivity(intent);
            }
        });

        btn_IDRSmodified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Modules.this, IDRSModified.class);
                startActivity(intent);
            }
        });
        btn_physicalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Modules.this, PhysicalActivity.class);
                startActivity(intent);
            }
        });
        btn_tobaccoSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Modules.this, TobbaccoSmoking.class);
                startActivity(intent);
            }
        });
        btn_dietLifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Modules.this, DietLifestyle.class);
                startActivity(intent);
            }
        });

    }
}
