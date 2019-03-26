package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Modules extends AppCompatActivity {
    private Button btn_risktriage, btn_IDRSmodified, btn_physicalActivity, btn_tobaccoSmoking, btn_dietLifestyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        btn_risktriage=(Button)findViewById(R.id.btn_risktriage);
        btn_IDRSmodified=(Button)findViewById(R.id.btn_IDRSmodified);
        btn_physicalActivity=(Button)findViewById(R.id.btn_physicalActivity);
        btn_tobaccoSmoking=(Button)findViewById(R.id.btn_tobaccoSmoking);
        btn_dietLifestyle=(Button)findViewById(R.id.btn_dietLifestyle);

        btn_risktriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Modules.this, RiskAndTriageCVA.class);
                startActivity(intent);
            }
        });

    }
}
