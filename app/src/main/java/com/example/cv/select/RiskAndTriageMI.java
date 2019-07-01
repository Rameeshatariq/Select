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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

public class RiskAndTriageMI extends AppCompatActivity {

    private RadioButton radiovalueQ1, radiovalueQ2, radiovalueQ3;
    String tool1bQ1, tool1bQ2, tool1bQ3;
    private Toolbar toolbar;
    static String Angina, AnginaorMyocardialInfarction, MyocardialInfarction, result;
    String ContactNo;
    String tool1;
    private RadioGroup rd_tl1b_Q1, rd_tl1b_Q2, rd_tl1b_Q3;
    private RadioButton rd_tl1b_Q1_yes, rd_tl1b_Q1_no, rd_tl1b_Q2_yes, rd_tl1b_Q2_no, rd_tl1b_Q3_yes, rd_tl1b_Q3_no;
    private Button submit_RTMI, btn_rtMI_saveExit;
    private DatabaseHelperRP mDatabaseHelper;
    Context ctx = this;
    boolean isInserted;
    Lister ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_and_triage_mi);

        mDatabaseHelper= new DatabaseHelperRP(this);
        ls = new Lister(ctx);


        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");
         tool1= intent.getStringExtra("tool1");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
      //  toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       // Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        rd_tl1b_Q1_yes = (RadioButton) findViewById(R.id.rd_rtMI_Q1_yes);
        rd_tl1b_Q1_no = (RadioButton) findViewById(R.id.rd_rtMI_Q1_no);

        rd_tl1b_Q2_yes = (RadioButton) findViewById(R.id.rd_rtMI_Q2_yes);
        rd_tl1b_Q2_no = (RadioButton) findViewById(R.id.rd_rtMI_Q2_no);

        rd_tl1b_Q3_yes = (RadioButton) findViewById(R.id.rd_rtMI_Q3_yes);
        rd_tl1b_Q3_no = (RadioButton) findViewById(R.id.rd_rtMI_Q3_no);

        rd_tl1b_Q1=(RadioGroup)findViewById(R.id.rd_rtMI_Q1);
        rd_tl1b_Q2=(RadioGroup)findViewById(R.id.rd_rtMI_Q2);
        rd_tl1b_Q3=(RadioGroup)findViewById(R.id.rd_rtMI_Q3);

        submit_RTMI = (Button) findViewById(R.id.btn_rtMI_submit);
        submit_RTMI.setVisibility(View.GONE);


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

        btn_rtMI_saveExit=(Button)findViewById(R.id.btn_rtMI_saveExit);
        btn_rtMI_saveExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
                addTool1bData();
                String tool2=null;
                String tool2Result=Angina + ", " + AnginaorMyocardialInfarction + ", " + MyocardialInfarction;
                mDatabaseHelper.updateTool2Status(ContactNo,tool2);
                Toast.makeText(RiskAndTriageMI.this, "Saving Answers", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        submit_RTMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
                addTool1bData();
                if (isInserted == true) {
                //    Toast.makeText(ctx, "Result Tool 2:"+result, Toast.LENGTH_SHORT).show();
                //    Toast.makeText(RiskAndTriageMI.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                  //  Toast.makeText(RiskAndTriageMI.this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
                String tool2="1";
                String tool2Result=Angina + ", " + AnginaorMyocardialInfarction + ", " + MyocardialInfarction;
                mDatabaseHelper.updateTool2Status(ContactNo,tool2);
              //  Toast.makeText(RiskAndTriageMI.this, "Tool2 Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        rd_tl1b_Q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_tl1b_Q1.getCheckedRadioButtonId() != -1 && rd_tl1b_Q2.getCheckedRadioButtonId() != -1 && rd_tl1b_Q3.getCheckedRadioButtonId() != -1){
                    submit_RTMI.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_tl1b_Q2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_tl1b_Q1.getCheckedRadioButtonId() != -1 && rd_tl1b_Q2.getCheckedRadioButtonId() != -1 && rd_tl1b_Q3.getCheckedRadioButtonId() != -1){
                    submit_RTMI.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_tl1b_Q3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_tl1b_Q1.getCheckedRadioButtonId() != -1 && rd_tl1b_Q2.getCheckedRadioButtonId() != -1 && rd_tl1b_Q3.getCheckedRadioButtonId() != -1){
                    submit_RTMI.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public static String getInstance(){
        return result ;
    }



    private void checkData() {
        if (rd_tl1b_Q1_yes.isChecked() == true) {
            Angina="Angina is Present";
            result="MI present";
        }
        else {
            Angina="Angina not Present";
            result="MI not present";
        }
        if (rd_tl1b_Q2_yes.isChecked() == true) {
                AnginaorMyocardialInfarction="Angina or Myocardial Infarction is present";
            result="MI present";
        }
        else{
            AnginaorMyocardialInfarction="Angina or Myocardial Infarction not Present";
            result="MI not present";
        }
         if (rd_tl1b_Q3_yes.isChecked() == true) {
            MyocardialInfarction="Myocardial Infarction is present";
             result="MI present";
         }
        else{
             MyocardialInfarction="Myocardial Infarction not Present";
             result="MI not present";
         }
         }

    private void addTool1bData() {
        if (rd_tl1b_Q1.getCheckedRadioButtonId() != -1) {
            radiovalueQ1 = (RadioButton) this.findViewById(rd_tl1b_Q1.getCheckedRadioButtonId());
            tool1bQ1 = radiovalueQ1.getText().toString();
        }

        if (rd_tl1b_Q2.getCheckedRadioButtonId() != -1) {
            radiovalueQ2 = (RadioButton) this.findViewById(rd_tl1b_Q2.getCheckedRadioButtonId());
            tool1bQ2 = radiovalueQ2.getText().toString();
        }

        if (rd_tl1b_Q3.getCheckedRadioButtonId() != -1) {
            radiovalueQ3 = (RadioButton) this.findViewById(rd_tl1b_Q3.getCheckedRadioButtonId());
            tool1bQ3 = radiovalueQ3.getText().toString();
        }

        try {
            Log.d("000333", "save and exit");

            String[][] mData = ls.executeReader("Select * from tool2 where ContactSim  = '" + ContactNo + "'");

            if (mData != null) {

              //  Toast.makeText(ctx, "Updating Tool2 Data", Toast.LENGTH_SHORT).show();

                isInserted = ls.executeNonQuery("Update tool2 set " +
                        "tool2_Q1 = '" + tool1bQ1 + "', " +
                        "tool2_Q2 = '" + tool1bQ2 + "', " +
                        "tool2_Q3 = '" + tool1bQ3 + "', " +
                        "result = '" + result + "' " +
                        " where ContactSim  = '" + ContactNo + "'");
                if (isInserted == true) {
                } else {
                   // Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
                }
            }
            else {

                    isInserted = mDatabaseHelper.addTool2Data(ContactNo, tool1bQ1, tool1bQ2, tool1bQ3, result);

            }
            finish();
        }
            catch(Exception e){
                Toast.makeText(ctx, "=-=-=-=Exception  " + e, Toast.LENGTH_SHORT).show();
                Log.e("000333", "Exception " + e);
            }
    }

    public boolean isCompleted(String mPhone) {

        Lister lister = new Lister(ctx);

        String[][] mData = lister.executeReader("Select * From tool2 where ContactSim = '" + mPhone + "'");

        try {

            if (mData.length > 0){
                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);
                Log.d("000111", "mData[0][3] =  " + mData[0][3]);
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

        String[][] mData = lister.executeReader("Select * From tool2 where ContactSim = '" + contactNo + "'");

        try {

            if (mData.length > 0) {

                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);
                Log.d("000111", "mData[0][3] =  " + mData[0][3]);


                if (mData[0][1].equalsIgnoreCase("Yes")) {
                    rd_tl1b_Q1.check(R.id.rd_rtMI_Q1_yes);
                } else {
                    rd_tl1b_Q1.check(R.id.rd_rtMI_Q1_no);
                }
                if (mData[0][2].equalsIgnoreCase("Yes")) {
                    rd_tl1b_Q2.check(R.id.rd_rtMI_Q2_yes);
                } else {
                    rd_tl1b_Q2.check(R.id.rd_rtMI_Q2_no);
                }

                if (mData[0][3].equalsIgnoreCase("Yes")) {
                    rd_tl1b_Q3.check(R.id.rd_rtMI_Q3_yes);
                } else {
                    rd_tl1b_Q3.check(R.id.rd_rtMI_Q3_no);
                }

            } else {

            }

        } catch (Exception e) {
            Log.d("111", e.getMessage());

        }
    }
}