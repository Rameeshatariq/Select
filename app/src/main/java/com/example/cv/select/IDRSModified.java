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

public class IDRSModified extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHelper;
    private RadioButton radiovalueQ1;
    private String ContactNo, tool4_Q1,tool1,tool2,tool3;
    private RadioGroup rd_IDRS_Q1;
    private Toolbar toolbar;
    private Button btn_IDRS_submit, btn_IDRS_saveExit;
    Context ctx = this;
    Lister ls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idrsmodified);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tool 4");
        toolbar.setTitleTextColor(Color.WHITE);
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
        tool3=intent.getStringExtra("tool3");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        mDatabaseHelper=new DatabaseHelperRP(this);
        ls = new Lister(ctx);

        btn_IDRS_submit=(Button)findViewById(R.id.btn_IDRS_submit);
        btn_IDRS_submit.setVisibility(View.GONE);

        btn_IDRS_saveExit=(Button)findViewById(R.id.btn_IDRS_saveExit);
        btn_IDRS_saveExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool4Data();
                String tool4="0";
                mDatabaseHelper.updateTool4Status(ContactNo,tool4);
                Toast.makeText(IDRSModified.this, "Tool4 is not Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        rd_IDRS_Q1=(RadioGroup)findViewById(R.id.rd_IDRS_Q1);
        btn_IDRS_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool4Data();
                String tool4="1";
                mDatabaseHelper.updateTool4Status(ContactNo,tool4);
                Toast.makeText(IDRSModified.this, "Tool4 Completed", Toast.LENGTH_SHORT).show();
               finish();
            }
        });

        try {
            boolean mflag= isCompleted(ContactNo);
            setData(ContactNo);
            if(mflag == true){
                // Toast.makeText(this, "Tool1 Completed", Toast.LENGTH_SHORT).show();
            }
            else{
                // Toast.makeText(this, "Tool1 not Completed", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
        }

        rd_IDRS_Q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_IDRS_Q1.getCheckedRadioButtonId() != -1){
                    btn_IDRS_submit.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void addTool4Data() {
        if(rd_IDRS_Q1.getCheckedRadioButtonId() != -1) {
            radiovalueQ1 = (RadioButton) this.findViewById(rd_IDRS_Q1.getCheckedRadioButtonId());
            tool4_Q1 = radiovalueQ1.getText().toString();
        }

        try {
            Log.d("000333", "save and exit");

            String[][] mData = ls.executeReader("Select *from tool4 where ContactSim  = '" + ContactNo + "'");

            if (mData != null) {
                boolean mFlag = ls.executeNonQuery("Update tool4 set " +
                        "tool4_Q1 = '" + tool4_Q1 + "' " +
                        " where ContactSim  = '" + ContactNo + "'");

                if (mFlag == true) {
                    Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
                }
            } else {

                boolean isInserted = mDatabaseHelper.addTool4Data(ContactNo, tool4_Q1);
                if (isInserted == true) {
                    Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

                }
            }
            finish();
        }catch (Exception e){

            Toast.makeText(ctx, "=-=-=-=Exception  "+e, Toast.LENGTH_SHORT).show();
            Log.e("000333", "Exception "+e);
        }
        }

    public boolean isCompleted(String mPhone) {

        Lister lister = new Lister(ctx);

        String[][] mData = lister.executeReader("Select * From tool4 where ContactSim = '" + mPhone + "'");

        try {

            if (mData.length > 0){
                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
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

        String[][] mData = lister.executeReader("Select * From tool4 where ContactSim = '" + contactNo + "'");

        try {

            if (mData.length > 0) {

                Log.d("000111", "mData[0][1] =  " + mData[0][1]);


                if (mData[0][1].equalsIgnoreCase("None")) {
                    rd_IDRS_Q1.check(R.id.rd_IDRS_Q1_none);
                }
                if (mData[0][1].equalsIgnoreCase("Either Parents")){
                    rd_IDRS_Q1.check(R.id.rd_IDRS_Q1_ep);
                }
                if (mData[0][1].equalsIgnoreCase("Both Parents")){
                    rd_IDRS_Q1.check(R.id.rd_IDRS_Q1_bp);
                }
            } else {

            }

        } catch (Exception e) {
            Log.d("111", e.getMessage());

        }
    }
}
