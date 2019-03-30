package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RiskAndTriageMI extends AppCompatActivity {

    //RadioButton t1_q1_cbYes

    private RelativeLayout relative_rtMI_Q4_DM_otions, relative_rtMI_Q4_ht_otions;
    private CheckBox ck_rtMI_Q4_DM, ck_rtMI_Q4_hypertension;
    private RadioButton rd_t1b_Q1_yes, rd_t1b_Q1_no, rd_t1b_Q2_yes, rd_t1b_Q2_no, rd_t1b_Q3_yes, rd_t1b_Q3_no;
    private Button submit_RTMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_and_triage_mi);

        rd_t1b_Q1_yes = (RadioButton) findViewById(R.id.rd_rtMI_Q1_yes);
        rd_t1b_Q1_no = (RadioButton) findViewById(R.id.rd_rtMI_Q1_no);

        rd_t1b_Q2_yes = (RadioButton) findViewById(R.id.rd_rtMI_Q2_yes);
        rd_t1b_Q2_no = (RadioButton) findViewById(R.id.rd_rtMI_Q2_no);

        rd_t1b_Q3_yes = (RadioButton) findViewById(R.id.rd_rtMI_Q3_yes);
        rd_t1b_Q3_no = (RadioButton) findViewById(R.id.rd_rtMI_Q3_no);


        ck_rtMI_Q4_DM = (CheckBox) findViewById(R.id.ck_rtMI_Q4_DM);
        ck_rtMI_Q4_hypertension = (CheckBox) findViewById(R.id.ck_rtMI_Q4_hypertension);

        relative_rtMI_Q4_DM_otions = (RelativeLayout) findViewById(R.id.relative_rtMI_Q4_DM_options);
        relative_rtMI_Q4_ht_otions = (RelativeLayout) findViewById(R.id.relative_rtMI_Q4_ht_otions);

        submit_RTMI = (Button) findViewById(R.id.btn_rtMI_submit);

        submit_RTMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });

        ck_rtMI_Q4_DM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ck_rtMI_Q4_DM.isChecked() == true) {
                    relative_rtMI_Q4_DM_otions.setVisibility(View.VISIBLE);
                } else {
                    relative_rtMI_Q4_DM_otions.setVisibility(View.INVISIBLE);
                }
            }
        });

        ck_rtMI_Q4_hypertension.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ck_rtMI_Q4_hypertension.isChecked() == true) {
                    relative_rtMI_Q4_ht_otions.setVisibility(View.VISIBLE);
                } else {
                    relative_rtMI_Q4_ht_otions.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    private void checkData() {
        if (rd_t1b_Q1_yes.isChecked() == true) {
            Toast.makeText(this, "Angina", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RiskAndTriageMI.this, Modules.class);
            startActivity(intent);
        } if (rd_t1b_Q2_yes.isChecked() == true) {
            Toast.makeText(this, "Angina or Myocardial Infarction", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RiskAndTriageMI.this, Modules.class);
            startActivity(intent);
        } if (rd_t1b_Q3_yes.isChecked() == true) {
            Toast.makeText(this, " Myocardial Infarction", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RiskAndTriageMI.this, Modules.class);
            startActivity(intent);
        } if (ck_rtMI_Q4_DM.isChecked() == true) {
            Toast.makeText(this, "Diabetic", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RiskAndTriageMI.this, Modules.class);
            startActivity(intent);
        } if (ck_rtMI_Q4_hypertension.isChecked() == true) {
            Toast.makeText(this, "Hypertensive", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RiskAndTriageMI.this, Modules.class);
            startActivity(intent);
        }
         else {
            Intent intent = new Intent(RiskAndTriageMI.this, Modules.class);
            startActivity(intent);
        }
    }
}