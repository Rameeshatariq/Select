package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;


public class RiskAndTriageDiabetic extends AppCompatActivity {

    private LinearLayout linear_rtD_Q1_DM_otions, linear_rtD_Q1_ht_otions;
    private String diabetic,hypertension, diabeticControlByMedicines, diabeticControlByDiet, diabeticControlByIsulin, diabeticControlByPnrMed, diabeticControlByAlternateMed, hypertenControlByNotMedicines,
            hypertenControlByMedicines, hypertenControlByDietOrtMed, hypertenControlByPnrMed, hypertenControlByAlternateMed, ContactNo;

    private CheckBox ck_rtD_Q1_DM_med, ck_rtD_Q1_DM_diet,ck_rtD_Q1_DM_insulin, ck_rtD_Q1_DM_pnrMed, ck_rtD_Q1_DM_altMed,ck_rtD_Q1_ht_notMedicines, ck_rtD_Q1_ht_med, ck_rtD_Q1_ht_dietOrMed,
            ck_rtD_Q1_ht_altMed, ck_rtD_Q1_ht_pnrMed;
    CheckBox ck_rtD_Q1_DM, ck_rtD_Q1_Hyp;
    private DatabaseHelperRP mDatabaseHelper;
    Button btn_RTD_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_and_triage_diabetic);

        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");


        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        linear_rtD_Q1_DM_otions = (LinearLayout) findViewById(R.id.linear_rtD_Q1_DM_options);
        linear_rtD_Q1_ht_otions = (LinearLayout) findViewById(R.id.linear_rtD_Q1_ht_otions);

        ck_rtD_Q1_DM_med = (CheckBox) findViewById(R.id.ck_rtD_Q1_DM_med);
        ck_rtD_Q1_DM_insulin = (CheckBox) findViewById(R.id.ck_rtD_Q1_DM_insulin);
        ck_rtD_Q1_DM_diet = (CheckBox) findViewById(R.id.ck_rtD_Q1_DM_diet);
        ck_rtD_Q1_DM_pnrMed = (CheckBox) findViewById(R.id.ck_rtD_Q1_DM_pnrmed);
        ck_rtD_Q1_DM_altMed = (CheckBox) findViewById(R.id.ck_rtD_Q1_DM_altmed);

        ck_rtD_Q1_ht_notMedicines = (CheckBox) findViewById(R.id.ck_rtD_Q1_ht_notmed);
        ck_rtD_Q1_ht_med = (CheckBox) findViewById(R.id.ck_rtD_Q1_ht_med);
        ck_rtD_Q1_ht_dietOrMed = (CheckBox) findViewById(R.id.ck_rtD_Q1_ht_dietmed);
        ck_rtD_Q1_ht_altMed = (CheckBox) findViewById(R.id.ck_rtD_Q1_ht_altmed);
        ck_rtD_Q1_ht_pnrMed = (CheckBox) findViewById(R.id.ck_rtD_Q1_ht_pnrmed);

        ck_rtD_Q1_DM=(CheckBox)findViewById(R.id.ck_rtD_Q1_DM);
        ck_rtD_Q1_Hyp=(CheckBox)findViewById(R.id.ck_rtD_Q1_hypertension);

        btn_RTD_submit=(Button)findViewById(R.id.btn_rtD_submit);
        btn_RTD_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool3data();
                String tool3="Completed";
                mDatabaseHelper.updateTool3Status(ContactNo,tool3);
                Toast.makeText(RiskAndTriageDiabetic.this, "Tool3 Completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RiskAndTriageDiabetic.this, Modules.class);
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });

        mDatabaseHelper=new DatabaseHelperRP(this);

        ck_rtD_Q1_DM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ck_rtD_Q1_DM.isChecked() == true) {
                    linear_rtD_Q1_DM_otions.setVisibility(View.VISIBLE);
                } else {
                    linear_rtD_Q1_DM_otions.setVisibility(View.INVISIBLE);
                    ck_rtD_Q1_DM_med.setChecked(false);
                    ck_rtD_Q1_DM_altMed.setChecked(false);
                    ck_rtD_Q1_DM_insulin.setChecked(false);
                    ck_rtD_Q1_DM_diet.setChecked(false);
                    ck_rtD_Q1_DM_pnrMed.setChecked(false);
                    ck_rtD_Q1_DM_altMed.setChecked(false);
                }
            }
        });

        ck_rtD_Q1_Hyp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ck_rtD_Q1_Hyp.isChecked() == true) {
                    linear_rtD_Q1_ht_otions.setVisibility(View.VISIBLE);
                } else {
                    linear_rtD_Q1_ht_otions.setVisibility(View.INVISIBLE);
                    ck_rtD_Q1_ht_notMedicines.setChecked(false);
                    ck_rtD_Q1_ht_med.setChecked(false);
                    ck_rtD_Q1_ht_dietOrMed.setChecked(false);
                    ck_rtD_Q1_ht_altMed.setChecked(false);
                    ck_rtD_Q1_ht_pnrMed.setChecked(false);
                }
            }
        });
    }

    private void addTool3data() {
        if(ck_rtD_Q1_DM.isChecked()){
            diabetic=ck_rtD_Q1_DM.getText().toString();
        }

        if(ck_rtD_Q1_DM_med.isChecked()){
            diabeticControlByMedicines=ck_rtD_Q1_DM_med.getText().toString();
        }
        if(ck_rtD_Q1_DM_insulin.isChecked()){
            diabeticControlByIsulin=ck_rtD_Q1_DM_insulin.getText().toString();
        }
        if(ck_rtD_Q1_DM_diet.isChecked()){
            diabeticControlByDiet=ck_rtD_Q1_DM_diet.getText().toString();
        }
        if(ck_rtD_Q1_DM_altMed.isChecked()){
            diabeticControlByAlternateMed=ck_rtD_Q1_DM_altMed.getText().toString();
        }
        if (ck_rtD_Q1_DM_pnrMed.isChecked()){
            diabeticControlByPnrMed=ck_rtD_Q1_DM_pnrMed.getText().toString();
        }
        if (ck_rtD_Q1_Hyp.isChecked()){
            hypertension=ck_rtD_Q1_Hyp.getText().toString();
        }
        if(ck_rtD_Q1_ht_notMedicines.isChecked()){
            hypertenControlByNotMedicines=ck_rtD_Q1_ht_notMedicines.getText().toString();
        }
        if(ck_rtD_Q1_ht_med.isChecked()){
            hypertenControlByMedicines=ck_rtD_Q1_ht_med.getText().toString();
        }
        if(ck_rtD_Q1_ht_dietOrMed.isChecked()){
            hypertenControlByDietOrtMed=ck_rtD_Q1_ht_dietOrMed.getText().toString();
        }
        if(ck_rtD_Q1_ht_pnrMed.isChecked()){
            hypertenControlByPnrMed=ck_rtD_Q1_ht_pnrMed.getText().toString();
        }
        if(ck_rtD_Q1_ht_altMed.isChecked()){
            hypertenControlByAlternateMed=ck_rtD_Q1_ht_altMed.getText().toString();
        }
        boolean isInserted = mDatabaseHelper.addTool3Data(ContactNo, diabetic, diabeticControlByMedicines, diabeticControlByIsulin, diabeticControlByDiet,
                diabeticControlByPnrMed, diabeticControlByAlternateMed, hypertension, hypertenControlByNotMedicines, hypertenControlByMedicines, hypertenControlByDietOrtMed,
                hypertenControlByPnrMed, hypertenControlByAlternateMed);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }


    }
}
