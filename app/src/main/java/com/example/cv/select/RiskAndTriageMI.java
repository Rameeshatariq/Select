package com.example.cv.select;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.rey.material.widget.CheckBox;

public class RiskAndTriageMI extends AppCompatActivity {
    private RelativeLayout relative_rtMI_Q4_DM_otions, relative_rtMI_Q4_ht_otions;
    private CheckBox ck_rtMI_Q4_DM, ck_rtMI_Q4_hypertension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_and_triage_mi);

        ck_rtMI_Q4_DM=(CheckBox)findViewById(R.id.ck_rtMI_Q4_DM);
        ck_rtMI_Q4_hypertension=(CheckBox)findViewById(R.id.ck_rtMI_Q4_hypertension);
        relative_rtMI_Q4_DM_otions=(RelativeLayout)findViewById(R.id.relative_rtMI_Q4_DM_options);
        relative_rtMI_Q4_ht_otions=(RelativeLayout)findViewById(R.id.relative_rtMI_Q4_ht_otions);

        ck_rtMI_Q4_DM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ck_rtMI_Q4_DM.isChecked() == true){
                    relative_rtMI_Q4_DM_otions.setVisibility(View.VISIBLE);
                }
                else{
                    relative_rtMI_Q4_DM_otions.setVisibility(View.INVISIBLE);
                }
            }
        });

        ck_rtMI_Q4_hypertension.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ck_rtMI_Q4_hypertension.isChecked() == true){
                    relative_rtMI_Q4_ht_otions.setVisibility(View.VISIBLE);
                }
                else{
                    relative_rtMI_Q4_ht_otions.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
