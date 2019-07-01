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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class RiskAndTriageCVA extends AppCompatActivity {
    private RadioButton rd_tl1a_Q1_yes, rd_tl1a_Q1_no, rd_tl1a_Q2_yes, rd_tl1a_Q2_no, rd_tl1a_Q3_yes, rd_tl1a_Q3_no, rd_tl1a_Q4_yes, rd_tl1a_Q4_no,
            rd_tl1a_Q5_yes, rd_tl1a_Q5_no, rd_tl1a_Q6_yes, rd_tl1a_Q6_no, rd_tl1a_Q7_yes, rd_tl1a_Q7_no, rd_tl1a_Q8_yes, rd_tl1a_Q8_no;
    private Button submitRT_CVA, btn_rt_saveExit;
    private Toolbar toolbar;
    private String tool1_Q1, tool1_Q2, tool1_Q3, tool1_Q4, tool1_Q5, tool1_Q6, tool1_Q7, tool1_Q8;
    static String CVAEvent = null;
    private RadioGroup rd_tl1a_Q1, rd_tl1a_Q2, rd_tl1a_Q3, rd_tl1a_Q4, rd_tl1a_Q5, rd_tl1a_Q6, rd_tl1a_Q7, rd_tl1a_Q8;
    private RadioButton radiovalueQ1, radiovalueQ2, radiovalueQ3, radiovalueQ4, radiovalueQ5, radiovalueQ6, radiovalueQ7, radiovalueQ8;
    private DatabaseHelperRP mDatabaseHelper;
    String ContactNo;
    int tool1_syncData=0;
    Context ctx = this;
    boolean isInserted;
    Lister ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_and_triage_cv);

        mDatabaseHelper = new DatabaseHelperRP(this);
        ls = new Lister(ctx);

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

        rd_tl1a_Q1_yes = (RadioButton) findViewById(R.id.rd_rt_Q1_yes);
        rd_tl1a_Q1_no = (RadioButton) findViewById(R.id.rd_rt_Q1_no);

        rd_tl1a_Q2_yes = (RadioButton) findViewById(R.id.rd_rt_Q2_yes);
        rd_tl1a_Q2_no = (RadioButton) findViewById(R.id.rd_rt_Q2_no);

        rd_tl1a_Q3_yes = (RadioButton) findViewById(R.id.rd_rt_Q3_yes);
        rd_tl1a_Q3_no = (RadioButton) findViewById(R.id.rd_rt_Q3_no);

        rd_tl1a_Q4_yes = (RadioButton) findViewById(R.id.rd_rt_Q4_yes);
        rd_tl1a_Q4_no = (RadioButton) findViewById(R.id.rd_rt_Q4_no);

        rd_tl1a_Q5_yes = (RadioButton) findViewById(R.id.rd_rt_Q5_yes);
        rd_tl1a_Q5_no = (RadioButton) findViewById(R.id.rd_rt_Q5_no);

        rd_tl1a_Q6_yes = (RadioButton) findViewById(R.id.rd_rt_Q6_yes);
        rd_tl1a_Q6_no = (RadioButton) findViewById(R.id.rd_rt_Q6_no);

        rd_tl1a_Q7_yes = (RadioButton) findViewById(R.id.rd_rt_Q7_yes);
        rd_tl1a_Q7_no = (RadioButton) findViewById(R.id.rd_rt_Q7_no);

        rd_tl1a_Q8_yes = (RadioButton) findViewById(R.id.rd_rt_Q8_yes);
        rd_tl1a_Q8_no = (RadioButton) findViewById(R.id.rd_rt_Q8_no);

        rd_tl1a_Q1 = (RadioGroup) findViewById(R.id.rd_rt_Q1);
        rd_tl1a_Q2 = (RadioGroup) findViewById(R.id.rd_rt_Q2);
        rd_tl1a_Q3 = (RadioGroup) findViewById(R.id.rd_rt_Q3);
        rd_tl1a_Q4 = (RadioGroup) findViewById(R.id.rd_rt_Q4);
        rd_tl1a_Q5 = (RadioGroup) findViewById(R.id.rd_rt_Q5);
        rd_tl1a_Q6 = (RadioGroup) findViewById(R.id.rd_rt_Q6);
        rd_tl1a_Q7 = (RadioGroup) findViewById(R.id.rd_rt_Q7);
        rd_tl1a_Q8 = (RadioGroup) findViewById(R.id.rd_rt_Q8);

        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");

        btn_rt_saveExit = (Button) findViewById(R.id.btn_rt_saveExit);

        submitRT_CVA = (Button) findViewById(R.id.btn_rt_register);
        submitRT_CVA.setVisibility(View.GONE);


        btn_rt_saveExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool1Data();
                String tool1 = null;
                mDatabaseHelper.updateTool1Status(ContactNo, tool1);
                Toast.makeText(RiskAndTriageCVA.this, "Saving Answers", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        submitRT_CVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
                if (isInserted == true) {
                  //  Toast.makeText(RiskAndTriageCVA.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                //    Toast.makeText(RiskAndTriageCVA.this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
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

        rd_tl1a_Q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_tl1a_Q1.getCheckedRadioButtonId() != -1 && rd_tl1a_Q2.getCheckedRadioButtonId() != -1 && rd_tl1a_Q3.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q4.getCheckedRadioButtonId() != -1 && rd_tl1a_Q5.getCheckedRadioButtonId() != -1 && rd_tl1a_Q6.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q7.getCheckedRadioButtonId() != -1 && rd_tl1a_Q8.getCheckedRadioButtonId() != -1) {
                    submitRT_CVA.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_tl1a_Q2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_tl1a_Q1.getCheckedRadioButtonId() != -1 && rd_tl1a_Q2.getCheckedRadioButtonId() != -1 && rd_tl1a_Q3.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q4.getCheckedRadioButtonId() != -1 && rd_tl1a_Q5.getCheckedRadioButtonId() != -1 && rd_tl1a_Q6.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q7.getCheckedRadioButtonId() != -1 && rd_tl1a_Q8.getCheckedRadioButtonId() != -1) {
                    submitRT_CVA.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_tl1a_Q3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_tl1a_Q1.getCheckedRadioButtonId() != -1 && rd_tl1a_Q2.getCheckedRadioButtonId() != -1 && rd_tl1a_Q3.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q4.getCheckedRadioButtonId() != -1 && rd_tl1a_Q5.getCheckedRadioButtonId() != -1 && rd_tl1a_Q6.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q7.getCheckedRadioButtonId() != -1 && rd_tl1a_Q8.getCheckedRadioButtonId() != -1) {
                    submitRT_CVA.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_tl1a_Q4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_tl1a_Q1.getCheckedRadioButtonId() != -1 && rd_tl1a_Q2.getCheckedRadioButtonId() != -1 && rd_tl1a_Q3.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q4.getCheckedRadioButtonId() != -1 && rd_tl1a_Q5.getCheckedRadioButtonId() != -1 && rd_tl1a_Q6.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q7.getCheckedRadioButtonId() != -1 && rd_tl1a_Q8.getCheckedRadioButtonId() != -1) {
                    submitRT_CVA.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_tl1a_Q5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_tl1a_Q1.getCheckedRadioButtonId() != -1 && rd_tl1a_Q2.getCheckedRadioButtonId() != -1 && rd_tl1a_Q3.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q4.getCheckedRadioButtonId() != -1 && rd_tl1a_Q5.getCheckedRadioButtonId() != -1 && rd_tl1a_Q6.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q7.getCheckedRadioButtonId() != -1 && rd_tl1a_Q8.getCheckedRadioButtonId() != -1) {
                    submitRT_CVA.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_tl1a_Q6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_tl1a_Q1.getCheckedRadioButtonId() != -1 && rd_tl1a_Q2.getCheckedRadioButtonId() != -1 && rd_tl1a_Q3.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q4.getCheckedRadioButtonId() != -1 && rd_tl1a_Q5.getCheckedRadioButtonId() != -1 && rd_tl1a_Q6.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q7.getCheckedRadioButtonId() != -1 && rd_tl1a_Q8.getCheckedRadioButtonId() != -1) {
                    submitRT_CVA.setVisibility(View.VISIBLE);
                }
            }
        });

        rd_tl1a_Q7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rd_tl1a_Q1.getCheckedRadioButtonId() != -1 && rd_tl1a_Q2.getCheckedRadioButtonId() != -1 && rd_tl1a_Q3.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q4.getCheckedRadioButtonId() != -1 && rd_tl1a_Q5.getCheckedRadioButtonId() != -1 && rd_tl1a_Q6.getCheckedRadioButtonId() != -1 &&
                        rd_tl1a_Q7.getCheckedRadioButtonId() != -1 && rd_tl1a_Q8.getCheckedRadioButtonId() != -1) {
                    submitRT_CVA.setVisibility(View.VISIBLE);
                }
            }
        });

       rd_tl1a_Q8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               if (rd_tl1a_Q1.getCheckedRadioButtonId() != -1 && rd_tl1a_Q2.getCheckedRadioButtonId() != -1 && rd_tl1a_Q3.getCheckedRadioButtonId() != -1 &&
                       rd_tl1a_Q4.getCheckedRadioButtonId() != -1 && rd_tl1a_Q5.getCheckedRadioButtonId() != -1 && rd_tl1a_Q6.getCheckedRadioButtonId() != -1 &&
                       rd_tl1a_Q7.getCheckedRadioButtonId() != -1 && rd_tl1a_Q8.getCheckedRadioButtonId() != -1) {
                   submitRT_CVA.setVisibility(View.VISIBLE);
               }
           }
       });


    }

    public static String getInstance(){
        return CVAEvent;
    }

    public boolean checkData() {
        if (rd_tl1a_Q1_yes.isChecked() == true || rd_tl1a_Q2_yes.isChecked() == true || rd_tl1a_Q3_yes.isChecked() == true || rd_tl1a_Q4_yes.isChecked() == true
                || rd_tl1a_Q5_yes.isChecked() == true || rd_tl1a_Q6_yes.isChecked() == true || rd_tl1a_Q7_yes.isChecked() == true || rd_tl1a_Q8_yes.isChecked() == true) {
            CVAEvent = "CVA Event Present";
            addTool1Data();
            String tool1 = "1";
            mDatabaseHelper.updateTool1Status(ContactNo, tool1);
         //   Toast.makeText(RiskAndTriageCVA.this, "Tool1 Completed", Toast.LENGTH_SHORT).show();
         //   Toast.makeText(RiskAndTriageCVA.this, "Result Tool1: CVA Event Present", Toast.LENGTH_SHORT).show();
            return true;

        } else {
            CVAEvent = "CVA Event not Present";
            addTool1Data();
            String tool1 = "1";
            mDatabaseHelper.updateTool1Status(ContactNo, tool1);
         //   Toast.makeText(RiskAndTriageCVA.this, "Result Tool1: CVA Event not Present", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void addTool1Data() {
        if (rd_tl1a_Q1.getCheckedRadioButtonId() != -1) {
            radiovalueQ1 = (RadioButton) this.findViewById(rd_tl1a_Q1.getCheckedRadioButtonId());
            tool1_Q1 = radiovalueQ1.getText().toString();
        }  if (rd_tl1a_Q2.getCheckedRadioButtonId() != -1) {
            radiovalueQ2 = (RadioButton) this.findViewById(rd_tl1a_Q2.getCheckedRadioButtonId());
            tool1_Q2 = radiovalueQ2.getText().toString();
        }  if (rd_tl1a_Q3.getCheckedRadioButtonId() != -1) {
            radiovalueQ3 = (RadioButton) this.findViewById(rd_tl1a_Q3.getCheckedRadioButtonId());
            tool1_Q3 = radiovalueQ3.getText().toString();
        }  if (rd_tl1a_Q4.getCheckedRadioButtonId() != -1) {
            radiovalueQ4 = (RadioButton) this.findViewById(rd_tl1a_Q4.getCheckedRadioButtonId());
            tool1_Q4 = radiovalueQ4.getText().toString();
        }  if (rd_tl1a_Q5.getCheckedRadioButtonId() != -1) {
            radiovalueQ5 = (RadioButton) this.findViewById(rd_tl1a_Q5.getCheckedRadioButtonId());
            tool1_Q5 = radiovalueQ5.getText().toString();
        }  if (rd_tl1a_Q6.getCheckedRadioButtonId() != -1) {
            radiovalueQ6 = (RadioButton) this.findViewById(rd_tl1a_Q6.getCheckedRadioButtonId());
            tool1_Q6 = radiovalueQ6.getText().toString();
        }  if (rd_tl1a_Q7.getCheckedRadioButtonId() != -1) {
            radiovalueQ7 = (RadioButton) this.findViewById(rd_tl1a_Q7.getCheckedRadioButtonId());
            tool1_Q7 = radiovalueQ7.getText().toString();
        }  if (rd_tl1a_Q8.getCheckedRadioButtonId() != -1) {
            radiovalueQ8 = (RadioButton) this.findViewById(rd_tl1a_Q8.getCheckedRadioButtonId());
            tool1_Q8 = radiovalueQ8.getText().toString();
        }


        Log.d("Q1", "addTool1Data: "+tool1_Q1);
        Log.d("Q2", "addTool1Data: "+tool1_Q2);
        Log.d("Q3", "addTool1Data: "+tool1_Q3);
        Log.d("Q4", "addTool1Data: "+tool1_Q4);
        Log.d("Q5", "addTool1Data: "+tool1_Q5);
        Log.d("Q6", "addTool1Data: "+tool1_Q6);
        Log.d("Q7", "addTool1Data: "+tool1_Q7);
        Log.d("Q8", "addTool1Data: "+tool1_Q8);


        try{
            Log.d("000333", "save and exit");

            String [][] mData = ls.executeReader("Select *from tool1 where ContactSim  = '"+ContactNo+"'");

            if(mData != null){
                 isInserted = ls.executeNonQuery("Update tool1 set " +
                        "tool1_Q1 = '"+tool1_Q1+"', " +
                        "tool1_Q2 = '"+tool1_Q2+"', " +
                        "tool1_Q3 = '"+tool1_Q3+"', " +
                        "tool1_Q4 = '"+tool1_Q4+"', " +
                        "tool1_Q5 = '"+tool1_Q5+"', " +
                        "tool1_Q6 = '"+tool1_Q6+"', " +
                        "tool1_Q7 = '"+tool1_Q7+"', " +
                        "tool1_Q8 = '"+tool1_Q8+"', " +
                        "cvaEvent = '"+CVAEvent+"' "+
                        " where ContactSim  = '"+ContactNo+"'");
                if (isInserted == true) {
                   // Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
                }

            }else {
                isInserted = mDatabaseHelper.addTool1Data(ContactNo, tool1_Q1, tool1_Q2, tool1_Q3, tool1_Q4, tool1_Q5,
                        tool1_Q6, tool1_Q7, tool1_Q8, CVAEvent,tool1_syncData);
               // Toast.makeText(ctx, ""+isInserted, Toast.LENGTH_SHORT).show();
            }
            finish();
        }catch (Exception e){

            Toast.makeText(ctx, "=-=-=-=Exception  "+e, Toast.LENGTH_SHORT).show();
            Log.e("000333", "Exception "+e);
        }
    }

    public boolean isCompleted(String mPhone) {

        Lister lister = new Lister(RiskAndTriageCVA.this);

        String[][] mData = lister.executeReader("Select * From tool1 where ContactSim = '" + mPhone + "'");

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

        Lister lister = new Lister(RiskAndTriageCVA.this);

        String[][] mData = lister.executeReader("Select * From tool1 where ContactSim = '" + contactNo + "'");

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


                if (mData[0][1].equalsIgnoreCase("Yes")) {
                    rd_tl1a_Q1.check(R.id.rd_rt_Q1_yes);
                } else {
                    rd_tl1a_Q1.check(R.id.rd_rt_Q1_no);
                }
                if (mData[0][2].equalsIgnoreCase("Yes")) {
                    rd_tl1a_Q2.check(R.id.rd_rt_Q2_yes);
                } else {
                    rd_tl1a_Q2.check(R.id.rd_rt_Q2_no);
                }

                if (mData[0][3].equalsIgnoreCase("Yes")) {
                    rd_tl1a_Q3.check(R.id.rd_rt_Q3_yes);
                } else {
                    rd_tl1a_Q3.check(R.id.rd_rt_Q3_no);
                }
                if (mData[0][4].equalsIgnoreCase("Yes")) {
                    rd_tl1a_Q4.check(R.id.rd_rt_Q4_yes);
                } else {
                    rd_tl1a_Q4.check(R.id.rd_rt_Q4_no);
                }
                if (mData[0][5].equalsIgnoreCase("Yes")) {
                    rd_tl1a_Q5.check(R.id.rd_rt_Q5_yes);
                } else {
                    rd_tl1a_Q5.check(R.id.rd_rt_Q5_no);
                }
                if (mData[0][6].equalsIgnoreCase("Yes")) {
                    rd_tl1a_Q6.check(R.id.rd_rt_Q6_yes);
                } else {
                    rd_tl1a_Q6.check(R.id.rd_rt_Q6_no);
                }
                if (mData[0][7].equalsIgnoreCase("Yes")) {
                    rd_tl1a_Q7.check(R.id.rd_rt_Q7_yes);
                } else {
                    rd_tl1a_Q7.check(R.id.rd_rt_Q7_no);
                }
                if (mData[0][8].equalsIgnoreCase("Yes")) {
                    rd_tl1a_Q8.check(R.id.rd_rt_Q8_yes);
                } else {
                    rd_tl1a_Q8.check(R.id.rd_rt_Q8_no);
                }

            } else {

            }

        } catch (Exception e) {
            Log.d("111", e.getMessage());

        }
    }
}
