package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class TobbaccoSmoking extends AppCompatActivity {

    private DatabaseHelperRP mDatabaseHelper;
    private RadioButton radiovalueQ1, radiovalueQ2, radiovalueQ3, radiovalueQ4;
    private Toolbar toolbar;
    private String ContactNo, tool6a_Q1, tool6a_Q2, tool6b_Q1, tool6b_Q2, tool1, tool2, tool3;
    private RadioGroup rd_TS_Q1, rd_TS_Q2, rd_TS_SL_Q1, rd_TS_SL_Q2;
    private Button btn_TS_submit, btn_TS_saveExit;
    Context ctx = this;
    int tool6a_syncData = 0;
    boolean isInserted;
    Lister ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobbacco_smoking);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        //toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        tool1 = intent.getStringExtra("tool1");
        tool2 = intent.getStringExtra("tool2");
        tool3 = intent.getStringExtra("tool3");
        //Toast.makeText(this, "" + ContactNo, Toast.LENGTH_SHORT).show();

        mDatabaseHelper = new DatabaseHelperRP(this);
        ls = new Lister(ctx);

        rd_TS_Q1 = (RadioGroup) findViewById(R.id.rd_TS_Q1);
        rd_TS_Q2 = (RadioGroup) findViewById(R.id.rd_TS_Q2);
        rd_TS_SL_Q1 = (RadioGroup) findViewById(R.id.rd_TS_SL_Q1);
        rd_TS_SL_Q2 = (RadioGroup) findViewById(R.id.rd_TS_SL_Q2);

        btn_TS_submit = (Button) findViewById(R.id.btn_TS_submit);
        btn_TS_submit.setVisibility(View.GONE);

        try {
            boolean mflag = isCompleted(ContactNo);
            setData(ContactNo);
            if (mflag == true) {
                // Toast.makeText(this, "Tool1 Completed", Toast.LENGTH_SHORT).show();
            } else {
                // Toast.makeText(this, "Tool1 not Completed", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
        }

        btn_TS_saveExit = (Button) findViewById(R.id.btn_TS_saveExit);
        btn_TS_saveExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool6aData();
                String tool6a = "2";
                mDatabaseHelper.updateTool6aStatus(ContactNo, tool6a);
                Toast.makeText(TobbaccoSmoking.this, "Saving Answers", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn_TS_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool6aData();
                if (isInserted == true) {
              //      Toast.makeText(TobbaccoSmoking.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                //    Toast.makeText(TobbaccoSmoking.this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
                String tool6a = "1";
                mDatabaseHelper.updateTool6aStatus(ContactNo, tool6a);
               // Toast.makeText(TobbaccoSmoking.this, "Tool6a Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        rd_TS_Q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_TS_Q1.getCheckedRadioButtonId() != -1 && rd_TS_Q2.getCheckedRadioButtonId() != -1 && rd_TS_SL_Q1.getCheckedRadioButtonId() != -1 && rd_TS_SL_Q2.getCheckedRadioButtonId() !=-1) {
                    btn_TS_submit.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_TS_Q2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_TS_Q1.getCheckedRadioButtonId() != -1 && rd_TS_Q2.getCheckedRadioButtonId() != -1 && rd_TS_SL_Q1.getCheckedRadioButtonId() != -1 && rd_TS_SL_Q2.getCheckedRadioButtonId() !=-1) {
                    btn_TS_submit.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_TS_SL_Q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_TS_Q1.getCheckedRadioButtonId() != -1 && rd_TS_Q2.getCheckedRadioButtonId() != -1 && rd_TS_SL_Q1.getCheckedRadioButtonId() != -1 && rd_TS_SL_Q2.getCheckedRadioButtonId() != -1) {
                    btn_TS_submit.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_TS_SL_Q2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_TS_Q1.getCheckedRadioButtonId() != -1 && rd_TS_Q2.getCheckedRadioButtonId() != -1 && rd_TS_SL_Q1.getCheckedRadioButtonId() != -1 && rd_TS_SL_Q2.getCheckedRadioButtonId() != -1) {
                    btn_TS_submit.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void addTool6aData() {
        if (rd_TS_Q1.getCheckedRadioButtonId() != -1) {
            radiovalueQ1 = (RadioButton) this.findViewById(rd_TS_Q1.getCheckedRadioButtonId());
            tool6a_Q1 = radiovalueQ1.getText().toString();
        }

        if (rd_TS_Q2.getCheckedRadioButtonId() != -1) {
            radiovalueQ2 = (RadioButton) this.findViewById(rd_TS_Q2.getCheckedRadioButtonId());
            tool6a_Q2 = radiovalueQ2.getText().toString();
        }
        if (rd_TS_SL_Q1.getCheckedRadioButtonId() != -1) {
            radiovalueQ3 = (RadioButton) this.findViewById(rd_TS_SL_Q1.getCheckedRadioButtonId());
            tool6b_Q1 = radiovalueQ3.getText().toString();
        }

        if (rd_TS_SL_Q2.getCheckedRadioButtonId() != -1) {
            radiovalueQ4 = (RadioButton) this.findViewById(rd_TS_SL_Q2.getCheckedRadioButtonId());
            tool6b_Q2 = radiovalueQ4.getText().toString();
        }

        try {
            Log.d("000333", "save and exit");

            String[][] mData = ls.executeReader("Select *from tool6a where ContactSim  = '" + ContactNo + "'");

            if (mData != null) {
                isInserted = ls.executeNonQuery("Update tool6a set " +
                        "tool6a_Q1 = '" + tool6a_Q1 + "', " +
                        "tool6a_Q2 = '" + tool6a_Q2 + "', " +
                        "tool6a_Q3 = '" + tool6b_Q1 + "', " +
                        "tool6a_Q4 = '" + tool6b_Q2 + "' " +
                        " where ContactSim  = '" + ContactNo + "'");
                if (isInserted == true) {
                 //   Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                   // Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
                }

            } else {
                isInserted = mDatabaseHelper.addTool6aData(ContactNo, tool6a_Q1, tool6a_Q2, tool6b_Q1,tool6b_Q2,tool6a_syncData);
            }
            finish();
        } catch (Exception e) {

            Toast.makeText(ctx, "=-=-=-=Exception  " + e, Toast.LENGTH_SHORT).show();
            Log.e("000333", "Exception " + e);
        }
    }

    public boolean isCompleted(String mPhone) {

        Lister lister = new Lister(ctx);

        String[][] mData = lister.executeReader("Select * From tool6a where ContactSim = '" + mPhone + "'");

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

        String[][] mData = lister.executeReader("Select * From tool6a where ContactSim = '" + contactNo + "'");

        try {

            if (mData.length > 0) {

                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);


                if (mData[0][1].equalsIgnoreCase("Daily")) {
                    rd_TS_Q1.check(R.id.rd_TS_Q1_daily);
                }
                if(mData[0][1].equalsIgnoreCase("Less than Daily")){
                    rd_TS_Q1.check(R.id.rd_TS_Q1_less);
                }
                if(mData[0][1].equalsIgnoreCase("Not At All")){
                    rd_TS_Q1.check(R.id.rd_TS_Q1_not);
                }
                if(mData[0][1].equalsIgnoreCase("Dont Know")){
                    rd_TS_Q1.check(R.id.rd_TS_Q1_dntknw);
                }
                if(mData[0][1].equalsIgnoreCase("Refused")){
                    rd_TS_Q1.check(R.id.rd_TS_Q1_refused);
                }

                if (mData[0][2].equalsIgnoreCase("Daily")) {
                    rd_TS_Q2.check(R.id.rd_TS_Q2_daily);
                }

                if(mData[0][2].equalsIgnoreCase("Less than Daily")){
                    rd_TS_Q2.check(R.id.rd_TS_Q2_less);
                }
                if(mData[0][2].equalsIgnoreCase("Not At All")){
                    rd_TS_Q2.check(R.id.rd_TS_Q2_not);
                }
                if(mData[0][2].equalsIgnoreCase("Dont Know")){
                    rd_TS_Q2.check(R.id.rd_TS_Q2_dntknw);
                }
                if(mData[0][2].equalsIgnoreCase("Refused")){
                    rd_TS_Q2.check(R.id.rd_TS_Q2_refused);
                }

                if (mData[0][3].equalsIgnoreCase("Daily")) {
                    rd_TS_SL_Q1.check(R.id.rd_TS_SL_Q1_daily);
                }
                if(mData[0][3].equalsIgnoreCase("Less than Daily")){
                    rd_TS_SL_Q1.check(R.id.rd_TS_SL_Q1_less);
                }
                if(mData[0][3].equalsIgnoreCase("Not At All")){
                    rd_TS_SL_Q1.check(R.id.rd_TS_SL_Q1_not);
                }
                if(mData[0][3].equalsIgnoreCase("Dont Know")){
                    rd_TS_SL_Q1.check(R.id.rd_TS_SL_Q1_dntknw);
                }
                if(mData[0][3].equalsIgnoreCase("Refused")){
                    rd_TS_SL_Q1.check(R.id.rd_TS_SL_Q1_refused);
                }

                if (mData[0][4].equalsIgnoreCase("Daily")) {
                    rd_TS_SL_Q2.check(R.id.rd_TS_SL_Q2_daily);
                }
                if(mData[0][4].equalsIgnoreCase("Less than Daily")){
                    rd_TS_SL_Q2.check(R.id.rd_TS_SL_Q2_less);
                }
                if(mData[0][4].equalsIgnoreCase("Not At All")){
                    rd_TS_SL_Q2.check(R.id.rd_TS_SL_Q2_not);
                }
                if(mData[0][4].equalsIgnoreCase("Dont Know")){
                    rd_TS_SL_Q2.check(R.id.rd_TS_SL_Q2_dntknw);
                }
                if(mData[0][4].equalsIgnoreCase("Refused")){
                    rd_TS_SL_Q2.check(R.id.rd_TS_SL_Q2_refused);
                }
            } else {

            }

        } catch (Exception e) {
            Log.d("111", e.getMessage());

        }
    }
}