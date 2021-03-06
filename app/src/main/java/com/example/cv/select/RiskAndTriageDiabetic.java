package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;


public class RiskAndTriageDiabetic extends AppCompatActivity {

    private LinearLayout linear_rtD_Q1_DM_otions, linear_rtD_Q1_ht_otions;
    private String diabetic,hypertension, diabeticControlByMedicines, diabeticControlByDiet, diabeticControlByIsulin, diabeticControlByPnrMed, diabeticControlByAlternateMed, hypertenControlByNotMedicines,
            hypertenControlByMedicines, hypertenControlByDietOrtMed, hypertenControlByPnrMed, hypertenControlByAlternateMed, ContactNo,
            Diabetic, Hypertension,tool1,tool2;

    private Toolbar toolbar;
    private CheckBox ck_rtD_Q1_DM_med, ck_rtD_Q1_DM_diet,ck_rtD_Q1_DM_insulin, ck_rtD_Q1_DM_pnrMed, ck_rtD_Q1_DM_altMed,ck_rtD_Q1_ht_notMedicines, ck_rtD_Q1_ht_med, ck_rtD_Q1_ht_dietOrMed,
            ck_rtD_Q1_ht_altMed, ck_rtD_Q1_ht_pnrMed;
    CheckBox ck_rtD_Q1_DM, ck_rtD_Q1_Hyp;
    private DatabaseHelperRP mDatabaseHelper;
    static String result;
    String Hyper_result;
    int tool3_syncData;
    String Diabetic_result;
    Button btn_RTD_submit, btnRTD_saveExit;
    Context ctx = this;
    boolean isInserted;
    Lister ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_and_triage_diabetic);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");
        tool1=intent.getStringExtra("tool1");
        tool2=intent.getStringExtra("tool2");

        ls = new Lister(ctx);

     //   Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        linear_rtD_Q1_DM_otions = (LinearLayout) findViewById(R.id.linear_rtD_Q1_DM_options);
        linear_rtD_Q1_ht_otions = (LinearLayout) findViewById(R.id.linear_rtD_Q1_ht_otions);

        linear_rtD_Q1_DM_otions.setVisibility(View.GONE);
        linear_rtD_Q1_ht_otions.setVisibility(View.GONE);

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

        try {
            boolean mflag= isCompleted(ContactNo);
            setData(ContactNo);
            if(mflag == true){
                linear_rtD_Q1_DM_otions.setVisibility(View.VISIBLE);
                linear_rtD_Q1_ht_otions.setVisibility(View.VISIBLE);

                // Toast.makeText(this, "Tool1 Completed", Toast.LENGTH_SHORT).show();
            }
            else{
                // Toast.makeText(this, "Tool1 not Completed", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
        }

        btn_RTD_submit=(Button)findViewById(R.id.btn_rtD_submit);

        btnRTD_saveExit=(Button)findViewById(R.id.btn_rtD_saveExit);
        btnRTD_saveExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool3data();
                String tool3="2";
                mDatabaseHelper.updateTool3Status(ContactNo,tool3);
                Toast.makeText(RiskAndTriageDiabetic.this, "Saving Answers", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn_RTD_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool3data();
                if (isInserted == true) {
               //     Toast.makeText(ctx, "Result Tool 3: "+result, Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(RiskAndTriageDiabetic.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                 //   Toast.makeText(RiskAndTriageDiabetic.this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
                String tool3="1";
                mDatabaseHelper.updateTool3Status(ContactNo,tool3);
               // Toast.makeText(RiskAndTriageDiabetic.this, "Tool3 Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        mDatabaseHelper=new DatabaseHelperRP(this);

        ck_rtD_Q1_DM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ck_rtD_Q1_DM.isChecked() == true) {
                    linear_rtD_Q1_DM_otions.setVisibility(View.VISIBLE);
                } else {
                    linear_rtD_Q1_DM_otions.setVisibility(View.GONE);
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
                    linear_rtD_Q1_ht_otions.setVisibility(View.GONE);
                    ck_rtD_Q1_ht_notMedicines.setChecked(false);
                    ck_rtD_Q1_ht_med.setChecked(false);
                    ck_rtD_Q1_ht_dietOrMed.setChecked(false);
                    ck_rtD_Q1_ht_altMed.setChecked(false);
                    ck_rtD_Q1_ht_pnrMed.setChecked(false);
                }
            }
        });
    }

    public static String getInstance(){
        return result;
    }

    private void addTool3data() {
        Diabetic_result = "Not Present";
        diabeticControlByMedicines = "Not Present";
        diabeticControlByDiet = "Not Present";
        diabeticControlByIsulin = "Not Present";
        diabeticControlByAlternateMed = "Not Present";
        diabeticControlByPnrMed = "Not Present";
        hypertension = "Not Present";
        hypertenControlByNotMedicines = "Not Present";
        hypertenControlByMedicines = "Not Present";
        hypertenControlByDietOrtMed = "Not Present";
        hypertenControlByPnrMed = "Not Present";
        hypertenControlByAlternateMed = "Not Present";

        if (ck_rtD_Q1_DM.isChecked()) {
           // diabetic = "Yes";
            //Diabetic = ck_rtD_Q1_DM.getText().toString();
            Diabetic_result= "Diabetic is present";
        }
        else{
            Diabetic_result="-";
        }

        if (ck_rtD_Q1_DM_med.isChecked()) {
            diabeticControlByMedicines = "Medicines";
        }
        else{
            diabeticControlByMedicines="-";
        }
        if (ck_rtD_Q1_DM_insulin.isChecked()) {
            diabeticControlByIsulin = "Insulin";
        }
        else{
            diabeticControlByIsulin="-";
        }
        if (ck_rtD_Q1_DM_diet.isChecked()) {
            diabeticControlByDiet = "Diet";
        }
        else{
            diabeticControlByDiet="-";
        }
        if (ck_rtD_Q1_DM_altMed.isChecked()) {
            diabeticControlByAlternateMed = "Alernative Medicines";
        }
        else{
            diabeticControlByAlternateMed="-";
        }
        if (ck_rtD_Q1_DM_pnrMed.isChecked()) {
            diabeticControlByPnrMed = "PNR Medicines";
        }
        else {
            diabeticControlByPnrMed="-";
        }
        if (ck_rtD_Q1_Hyp.isChecked()) {
          //  hypertension = "Yes";
           // Hypertension = ck_rtD_Q1_Hyp.getText().toString();
            Hyper_result="Hypertension is present";
        }
        else{
            Hyper_result="-";
        }
        if (ck_rtD_Q1_ht_notMedicines.isChecked()) {
            hypertenControlByNotMedicines = "Not by Medicines";
        }
        else{
            hypertenControlByNotMedicines="-";
        }
        if (ck_rtD_Q1_ht_med.isChecked()) {
            hypertenControlByMedicines = "Medicines";
        }
        else{
            hypertenControlByMedicines="-";
        }
        if (ck_rtD_Q1_ht_dietOrMed.isChecked()) {
            hypertenControlByDietOrtMed = "Diet Or Medicines";
        }
        else{
            hypertenControlByDietOrtMed="-";
        }
        if (ck_rtD_Q1_ht_pnrMed.isChecked()) {
            hypertenControlByPnrMed = "PNR Medicines";
        }
        else{
            hypertenControlByPnrMed="-";
        }
        if (ck_rtD_Q1_ht_altMed.isChecked()) {
            hypertenControlByAlternateMed = "Alternate Medicines";
        }
        else{
            hypertenControlByAlternateMed="-";
        }

        result = Diabetic_result + "," +Hyper_result;

        try {
            Log.d("000333", "save and exit");

            String[][] mData = ls.executeReader("Select *from tool3 where ContactSim  = '" + ContactNo + "'");

            if (mData != null) {
                linear_rtD_Q1_DM_otions.setVisibility(View.VISIBLE);
                linear_rtD_Q1_ht_otions.setVisibility(View.VISIBLE);

                isInserted = ls.executeNonQuery("Update tool3 set " +
                        "Diabetic = '" + Diabetic_result + "', " +
                        "DiabeticControlByMedicines = '" + diabeticControlByMedicines + "', " +
                        "DiabeticControlByInsulin = '" + diabeticControlByIsulin + "', " +
                        "DiabeticControlByDiet = '" + diabeticControlByDiet + "', " +
                        "DiabeticControlByPNRMedication = '" + diabeticControlByPnrMed + "', " +
                        "DiabeticControlByAlternateMedication = '" + diabeticControlByAlternateMed + "', " +
                        "Hypertension = '" + Hyper_result + "', " +
                        "HypertensionNotControlByMedicines = '" + hypertenControlByNotMedicines + "', " +
                        "HypertensionControlByMedicines = '" + hypertenControlByMedicines + "', " +
                        "HypertensionControlByDietOrMedicines = '" + hypertenControlByDietOrtMed + "', " +
                        "HypertensionNotControlByPNRMedication = '" + hypertenControlByPnrMed + "', " +
                        "HypertensionControlByAlternateMedication = '" + hypertenControlByAlternateMed + "' " +
                        " where ContactSim  = '" + ContactNo + "'");

                if (isInserted == true) {
                    //Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
                }

            } else {

                isInserted = mDatabaseHelper.addTool3Data(ContactNo, Diabetic_result, diabeticControlByMedicines, diabeticControlByIsulin, diabeticControlByDiet,
                        diabeticControlByPnrMed, diabeticControlByAlternateMed, Hyper_result, hypertenControlByNotMedicines, hypertenControlByMedicines, hypertenControlByDietOrtMed,
                        hypertenControlByPnrMed, hypertenControlByAlternateMed, tool3_syncData);
            }
            finish();
        }catch (Exception e){
            Toast.makeText(ctx, "=-=-=-=Exception  "+e, Toast.LENGTH_SHORT).show();
            Log.e("000333", "Exception "+e);
        }
    }
    public boolean isCompleted(String mPhone) {

        Lister lister = new Lister(ctx);

        String[][] mData = lister.executeReader("Select * From tool3 where ContactSim = '" + mPhone + "'");

        try {

            if (mData.length > 0){
                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);
                Log.d("000111", "mData[0][3] =  " + mData[0][3]);
                Log.d("000111", "mData[0][4] =  " + mData[0][4]);
                Log.d("000111", "mData[0][5] =  " + mData[0][5]);
                Log.d("000111", "mData[0][6] =  " + mData[0][6]);
                Log.d("000111", "mData[0][7] =  " + mData[0][7]);
                Log.d("000111", "mData[0][8] =  " + mData[0][8]);
                return  true;

            }else {
                return false;
            }

        }

        catch (Exception e) {
            Log.d("111", e.getMessage());
            return false;
        }


    }

    private void setData(String contactNo) {

        Lister lister = new Lister(ctx);

        String[][] mData = lister.executeReader("Select * From tool3 where ContactSim = '" + contactNo + "'");

        try {

            if (mData.length > 0) {

                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);
                Log.d("000111", "mData[0][3] =  " + mData[0][3]);
                Log.d("000111", "mData[0][4] =  " + mData[0][4]);
                Log.d("000111", "mData[0][5] =  " + mData[0][5]);
                Log.d("000111", "mData[0][6] =  " + mData[0][6]);
                Log.d("000111", "mData[0][7] =  " + mData[0][7]);
                Log.d("000111", "mData[0][8] =  " + mData[0][8]);
                Log.d("000111", "mData[0][9] =  " + mData[0][8]);
                Log.d("000111", "mData[0][10] =  " + mData[0][8]);
                Log.d("000111", "mData[0][11] =  " + mData[0][8]);
                Log.d("000111", "mData[0][12] =  " + mData[0][8]);

                if (mData[0][1].equalsIgnoreCase("Diabetic is present")) {
                    ck_rtD_Q1_DM.setChecked(true);
                } else {
                    ck_rtD_Q1_DM.setChecked(false);
                }

                if (mData[0][2].equalsIgnoreCase("Medicines")) {
                    ck_rtD_Q1_DM_med.setChecked(true);
                } else {
                    ck_rtD_Q1_DM_med.setChecked(false);
                }
                if (mData[0][3].equalsIgnoreCase("Insulin")) {
                    ck_rtD_Q1_DM_insulin.setChecked(true);
                } else {
                    ck_rtD_Q1_DM_insulin.setChecked(false);
                }
                if (mData[0][4].equalsIgnoreCase("Diet")) {
                    ck_rtD_Q1_DM_diet.setChecked(true);
                } else {
                    ck_rtD_Q1_DM_diet.setChecked(false);
                }
                if (mData[0][5].equalsIgnoreCase("PNR Medicines")) {
                    ck_rtD_Q1_DM_pnrMed.setChecked(true);
                } else {
                    ck_rtD_Q1_DM_pnrMed.setChecked(false);
                }
                if (mData[0][6].equalsIgnoreCase("Alernative Medicines")) {
                    ck_rtD_Q1_DM_altMed.setChecked(true);
                } else {
                    ck_rtD_Q1_DM_altMed.setChecked(false);
                }
                if (mData[0][7].equalsIgnoreCase("Hypertension is present")) {
                    ck_rtD_Q1_Hyp.setChecked(true);
                } else {
                    ck_rtD_Q1_Hyp.setChecked(false);
                }
                if (mData[0][8].equalsIgnoreCase("Not by Medicines")) {
                    ck_rtD_Q1_ht_notMedicines.setChecked(true);
                } else {
                    ck_rtD_Q1_ht_notMedicines.setChecked(false);
                }
                if (mData[0][9].equalsIgnoreCase("Medicines")) {
                    ck_rtD_Q1_ht_med.setChecked(true);
                } else {
                    ck_rtD_Q1_ht_med.setChecked(false);
                }
                if (mData[0][10].equalsIgnoreCase("Diet Or Medicines")) {
                    ck_rtD_Q1_ht_dietOrMed.setChecked(true);
                } else {
                    ck_rtD_Q1_ht_dietOrMed.setChecked(false);
                }
                if (mData[0][11].equalsIgnoreCase("PNR Medicines")) {
                    ck_rtD_Q1_ht_pnrMed.setChecked(true);
                } else {
                    ck_rtD_Q1_ht_pnrMed.setChecked(false);
                }
                if (mData[0][12].equalsIgnoreCase("Alternate Medicines")) {
                    ck_rtD_Q1_ht_altMed.setChecked(true);
                } else {
                    ck_rtD_Q1_ht_altMed.setChecked(false);
                }
            } else {

            }

        } catch (Exception e) {
            Log.d("111", e.getMessage());

        }
    }
}
