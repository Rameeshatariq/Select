package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class DietLifestyle extends AppCompatActivity {
    RadioButton rd_tl7_Q1_yes, rd_tl7_Q2_yes, rd_tl7_Q1_no, rd_tl7_Q2_no;
    RadioGroup rd_tl7_Q1, rd_tl7_Q2;
    RadioButton radiovalueQ1, radiovalueQ2;
    Toolbar toolbar;
    String tl7_Q1, tl7_Q2, ContactNo,tool1,tool2,tool3,Q1,Q2, Q3,Q4,Q5,Q6,Q7;
    Spinner sp_tl7_Q3, sp_tl7_Q4, sp_tl7_Q5, sp_tl7_Q6, sp_tl7_Q7;
    Button btn_submit_DL, btn_next, btn_DL_saveExit;
    int tl7_Q3, tl7_Q4, tl7_Q5, tl7_Q6, tl7_Q7;
    DatabaseHelperRP mDatabaseHelper;
    Context ctx = this;
    static String result;
    boolean isInserted;
    Lister ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_lifestyle);

        mDatabaseHelper = new DatabaseHelperRP(this);
        ls = new Lister(ctx);

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

        rd_tl7_Q1_yes = (RadioButton) findViewById(R.id.rd_DL_Q1_yes);
        rd_tl7_Q1_no = (RadioButton) findViewById(R.id.rd_DL_Q1_no);

        rd_tl7_Q2_yes = (RadioButton) findViewById(R.id.rd_DL_Q2_yes);
        rd_tl7_Q2_no = (RadioButton) findViewById(R.id.rd_DL_Q2_no);

        rd_tl7_Q1 = (RadioGroup) findViewById(R.id.rd_DL_Q1);
        rd_tl7_Q2 = (RadioGroup) findViewById(R.id.rd_DL_Q2);

        sp_tl7_Q3 = (Spinner) findViewById(R.id.sp_DL_Q3);
        sp_tl7_Q4 = (Spinner) findViewById(R.id.sp_DL_Q4);
        sp_tl7_Q5 = (Spinner) findViewById(R.id.sp_DL_Q5);
        sp_tl7_Q6 = (Spinner) findViewById(R.id.sp_DL_Q6);
        sp_tl7_Q7 = (Spinner) findViewById(R.id.sp_DL_Q7);

     //   btn_next = (Button) findViewById(R.id.btn_next);

        btn_submit_DL = (Button) findViewById(R.id.btn_DL_submit);
        btn_submit_DL.setVisibility(View.GONE);

        btn_DL_saveExit=(Button)findViewById(R.id.btn_DL_saveExit);
        btn_DL_saveExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
                addtool7Data();
                String tool7 = null;
                String tool7Result = Q1 + ", " + Q2 + ", " + Q3 + ", " + Q4 + ", " + Q5 + ", " + Q6 + ", " + Q7;
                mDatabaseHelper.updateTool7Status(ContactNo, tool7);
                Toast.makeText(DietLifestyle.this, "Saving Answers", Toast.LENGTH_SHORT).show();
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

       /* btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietLifestyle.this, HighRisk.class);
                startActivity(intent);
            }
        });*/
        btn_submit_DL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
                addtool7Data();
                if (isInserted == true) {
                //    Toast.makeText(ctx, "Result Tool 7:"+result, Toast.LENGTH_SHORT).show();
                 //   Toast.makeText(DietLifestyle.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                   // Toast.makeText(DietLifestyle.this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
                String tool7 = "1";
                String tool7Result = Q1 + ", " + Q2 + ", " + Q3 + ", " + Q4 + ", " + Q5 + ", " + Q6 + ", " + Q7;
                mDatabaseHelper.updateTool7Status(ContactNo, tool7);
            //    Toast.makeText(DietLifestyle.this, "Tool7 Completed", Toast.LENGTH_SHORT).show();
             finish();
            }
        });

        rd_tl7_Q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_tl7_Q1.getCheckedRadioButtonId() != -1 && rd_tl7_Q2.getCheckedRadioButtonId() != -1 && sp_tl7_Q3.getSelectedItemId() != -1 && sp_tl7_Q4.getSelectedItemId() != -1 &&
                        sp_tl7_Q5.getSelectedItemId() != -1 && sp_tl7_Q6.getSelectedItemId() != -1 && sp_tl7_Q7.getSelectedItemId() != -1){
                    btn_submit_DL.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_tl7_Q2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_tl7_Q1.getCheckedRadioButtonId() != -1 && rd_tl7_Q2.getCheckedRadioButtonId() != -1 && sp_tl7_Q3.getSelectedItemId() != -1 && sp_tl7_Q4.getSelectedItemId() != -1 &&
                        sp_tl7_Q5.getSelectedItemId() != -1 && sp_tl7_Q6.getSelectedItemId() != -1 && sp_tl7_Q7.getSelectedItemId() != -1){
                    btn_submit_DL.setVisibility(View.VISIBLE);
                }
            }
        });
        sp_tl7_Q3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(rd_tl7_Q1.getCheckedRadioButtonId() != -1 && rd_tl7_Q2.getCheckedRadioButtonId() != -1 && sp_tl7_Q3.getSelectedItemId() != -1 && sp_tl7_Q4.getSelectedItemId() != -1 &&
                        sp_tl7_Q5.getSelectedItemId() != -1 && sp_tl7_Q6.getSelectedItemId() != -1 && sp_tl7_Q7.getSelectedItemId() != -1){
                    btn_submit_DL.setVisibility(View.VISIBLE);
                }
                }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_tl7_Q4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(rd_tl7_Q1.getCheckedRadioButtonId() != -1 && rd_tl7_Q2.getCheckedRadioButtonId() != -1 && sp_tl7_Q3.getSelectedItemId() != -1 && sp_tl7_Q4.getSelectedItemId() != -1 &&
                        sp_tl7_Q5.getSelectedItemId() != -1 && sp_tl7_Q6.getSelectedItemId() != -1 && sp_tl7_Q7.getSelectedItemId() != -1){
                    btn_submit_DL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_tl7_Q5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(rd_tl7_Q1.getCheckedRadioButtonId() != -1 && rd_tl7_Q2.getCheckedRadioButtonId() != -1 && sp_tl7_Q3.getSelectedItemId() != -1 && sp_tl7_Q4.getSelectedItemId() != -1 &&
                        sp_tl7_Q5.getSelectedItemId() != -1 && sp_tl7_Q6.getSelectedItemId() != -1 && sp_tl7_Q7.getSelectedItemId() != -1){
                    btn_submit_DL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_tl7_Q6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(rd_tl7_Q1.getCheckedRadioButtonId() != -1 && rd_tl7_Q2.getCheckedRadioButtonId() != -1 && sp_tl7_Q3.getSelectedItemId() != -1 && sp_tl7_Q4.getSelectedItemId() != -1 &&
                        sp_tl7_Q5.getSelectedItemId() != -1 && sp_tl7_Q6.getSelectedItemId() != -1 && sp_tl7_Q7.getSelectedItemId() != -1){
                    btn_submit_DL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_tl7_Q7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(rd_tl7_Q1.getCheckedRadioButtonId() != -1 && rd_tl7_Q2.getCheckedRadioButtonId() != -1 && sp_tl7_Q3.getSelectedItemId() != -1 && sp_tl7_Q4.getSelectedItemId() != -1 &&
                        sp_tl7_Q5.getSelectedItemId() != -1 && sp_tl7_Q6.getSelectedItemId() != -1 && sp_tl7_Q7.getSelectedItemId() != -1){
                    btn_submit_DL.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static String getInstance(){
        return result;
    }

    private void addtool7Data() {
        if (rd_tl7_Q1.getCheckedRadioButtonId() != -1) {
            radiovalueQ1 = (RadioButton) this.findViewById(rd_tl7_Q1.getCheckedRadioButtonId());
            tl7_Q1 = radiovalueQ1.getText().toString();
        }
        if (rd_tl7_Q2.getCheckedRadioButtonId() != -1) {
            radiovalueQ2 = (RadioButton) this.findViewById(rd_tl7_Q2.getCheckedRadioButtonId());
            tl7_Q2 = radiovalueQ2.getText().toString();
        }

        tl7_Q3 = Integer.parseInt(sp_tl7_Q3.getSelectedItem().toString());
        tl7_Q4 = Integer.parseInt(sp_tl7_Q4.getSelectedItem().toString());
        tl7_Q5 = Integer.parseInt(sp_tl7_Q5.getSelectedItem().toString());
        tl7_Q6 = Integer.parseInt(sp_tl7_Q6.getSelectedItem().toString());
        tl7_Q7 = Integer.parseInt(sp_tl7_Q7.getSelectedItem().toString());

        try {
            Log.d("000333", "save and exit");

            String[][] mData = ls.executeReader("Select *from tool7 where ContactSim  = '" + ContactNo + "'");

            if (mData != null) {
                isInserted = ls.executeNonQuery("Update tool7 set " +
                        "tool7_Q1 = '" + tl7_Q1 + "', " +
                        "tool7_Q2 = '" + tl7_Q2 + "', " +
                        "tool7_Q3 = '" + tl7_Q3 + "', " +
                        "tool7_Q4 = '" + tl7_Q4 + "', " +
                        "tool7_Q5 = '" + tl7_Q5 + "', " +
                        "tool7_Q6 = '" + tl7_Q6 + "', " +
                        "tool7_Q7 = '" + tl7_Q7 + "', " +
                        "result = '" + result + "' " +
                        " where ContactSim  = '" + ContactNo + "'");
                if (isInserted == true) {
                  //  Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                   // Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
                }

            } else {

                isInserted = mDatabaseHelper.addTool7Data(ContactNo, tl7_Q1, tl7_Q2, tl7_Q3, tl7_Q4, tl7_Q5, tl7_Q6, tl7_Q7, result);
            }
            finish();
        } catch (Exception e) {

            Toast.makeText(ctx, "=-=-=-=Exception  " + e, Toast.LENGTH_SHORT).show();
            Log.e("000333", "Exception " + e);
        }
    }
    private void checkData() {
        tl7_Q3 = Integer.parseInt(sp_tl7_Q3.getSelectedItem().toString());
        tl7_Q4 = Integer.parseInt(sp_tl7_Q4.getSelectedItem().toString());
        tl7_Q5 = Integer.parseInt(sp_tl7_Q5.getSelectedItem().toString());
        tl7_Q6 = Integer.parseInt(sp_tl7_Q6.getSelectedItem().toString());
        tl7_Q7 = Integer.parseInt(sp_tl7_Q7.getSelectedItem().toString());

        if(rd_tl7_Q1_yes.isChecked() == true){
            //Toast.makeText(this, "1. High Risk", Toast.LENGTH_SHORT).show();
            Q1="Q1 High Risk";
        }
        else{
            //Toast.makeText(this, "1. Normal Risk", Toast.LENGTH_SHORT).show();
            Q1="Q1 Normal Risk";

        }
        if(rd_tl7_Q2_yes.isChecked() == true){
            //Toast.makeText(this, "2. High Risk", Toast.LENGTH_SHORT).show();
            Q2="Q2 High Risk";

        }
        else{
            //Toast.makeText(this, "2. Normal Risk", Toast.LENGTH_SHORT).show();
            Q2="Q2 Normal Risk";

        }

        if(tl7_Q3 < 5){
            //Toast.makeText(this, "3. High Risk", Toast.LENGTH_SHORT).show();
            Q3="Q3 High Risk";

        }
        else{
            //Toast.makeText(this, "3. Normal Risk", Toast.LENGTH_SHORT).show();
            Q3="Q3 Normal Risk";
        }
        if(tl7_Q4 < 3){
            //Toast.makeText(this, "4. Normal Risk", Toast.LENGTH_SHORT).show();
            Q4="Q4 Normal Risk";
        }
        else{
            //Toast.makeText(this, "4. High Risk", Toast.LENGTH_SHORT).show();
            Q4="Q4 High Risk";
        }
        if(tl7_Q5 < 3){
            //Toast.makeText(this, "5. Normal Risk", Toast.LENGTH_SHORT).show();
            Q5="Q5 Normal Risk";
        }
        else{
            //Toast.makeText(this, "5. High Risk", Toast.LENGTH_SHORT).show();
            Q5="Q5 High Risk";
        }
        if(tl7_Q6 < 3){
            //Toast.makeText(this, "6. Normal Risk", Toast.LENGTH_SHORT).show();
            Q6="Q6 Normal Risk";
        }
        else{
            //Toast.makeText(this, "6. High Risk", Toast.LENGTH_SHORT).show();
            Q6="Q6 High Risk";
        }
        if(tl7_Q7 > 2){
           // Toast.makeText(this, "7. Normal Risk", Toast.LENGTH_SHORT).show();
            Q7="Q7 Normal Risk";
        }
        else{
            Q7="Q7 High Risk";
        }
         result = Q1 + ", " + Q2 + ", " + Q3 + ", " + Q4 + ", " + Q5 + ", " + Q6 + ", " + Q7;
    }


    public boolean isCompleted(String mPhone) {

        Lister lister = new Lister(ctx);

        String[][] mData = lister.executeReader("Select * From tool7 where ContactSim = '" + mPhone + "'");

        try {

            if (mData.length > 0){
                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);
                Log.d("000111", "mData[0][3] =  " + mData[0][3]);
                Log.d("000111", "mData[0][4] =  " + mData[0][4]);
                Log.d("000111", "mData[0][5] =  " + mData[0][5]);
                Log.d("000111", "mData[0][6] =  " + mData[0][6]);
                Log.d("000111", "mData[0][7] =  " + mData[0][7]);
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

        String[][] mData = lister.executeReader("Select * From tool7 where ContactSim = '" + contactNo + "'");

        try {

            if (mData.length > 0) {

                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);
                Log.d("000111", "mData[0][3] =  " + mData[0][3]);
                Log.d("000111", "mData[0][4] =  " + mData[0][4]);
                Log.d("000111", "mData[0][5] =  " + mData[0][5]);
                Log.d("000111", "mData[0][6] =  " + mData[0][6]);
                Log.d("000111", "mData[0][7] =  " + mData[0][7]);


                if (mData[0][1].equalsIgnoreCase("Yes")) {
                    rd_tl7_Q1.check(R.id.rd_DL_Q1_yes);
                } else {
                    rd_tl7_Q1.check(R.id.rd_DL_Q1_no);
                }

                if (mData[0][2].equalsIgnoreCase("Yes")) {
                    rd_tl7_Q2.check(R.id.rd_DL_Q2_yes);
                } else {
                    rd_tl7_Q2.check(R.id.rd_DL_Q2_no);
                }


                if (mData[0][3].equalsIgnoreCase("0")) {
                    sp_tl7_Q3.setSelection(0);
                } else if (mData[0][3].equalsIgnoreCase("1")) {
                    sp_tl7_Q3.setSelection(1);
                } else if (mData[0][3].equalsIgnoreCase("2")) {
                    sp_tl7_Q3.setSelection(2);
                } else if (mData[0][3].equalsIgnoreCase("3")) {
                    sp_tl7_Q3.setSelection(3);
                } else if (mData[0][3].equalsIgnoreCase("4")) {
                    sp_tl7_Q3.setSelection(4);
                } else if (mData[0][3].equalsIgnoreCase("5")) {
                    sp_tl7_Q3.setSelection(5);
                } else if (mData[0][3].equalsIgnoreCase("6")) {
                    sp_tl7_Q3.setSelection(6);
                } else if (mData[0][3].equalsIgnoreCase("7")) {
                    sp_tl7_Q3.setSelection(7);
                } else if (mData[0][3].equalsIgnoreCase("8")) {
                    sp_tl7_Q3.setSelection(8);
                } else if (mData[0][3].equalsIgnoreCase("9")) {
                    sp_tl7_Q3.setSelection(9);
                } else if (mData[0][3].equalsIgnoreCase("10")) {
                    sp_tl7_Q3.setSelection(10);
                } else if (mData[0][3].equalsIgnoreCase("11")) {
                    sp_tl7_Q3.setSelection(11);
                } else if (mData[0][3].equalsIgnoreCase("12")) {
                    sp_tl7_Q3.setSelection(12);
                } else if (mData[0][3].equalsIgnoreCase("13")) {
                    sp_tl7_Q3.setSelection(13);
                } else if (mData[0][3].equalsIgnoreCase("14")) {
                    sp_tl7_Q3.setSelection(14);
                } else if (mData[0][3].equalsIgnoreCase("15")) {
                    sp_tl7_Q3.setSelection(15);
                } else if (mData[0][3].equalsIgnoreCase("16")) {
                    sp_tl7_Q3.setSelection(16);
                } else if (mData[0][3].equalsIgnoreCase("17")) {
                    sp_tl7_Q3.setSelection(17);
                } else if (mData[0][3].equalsIgnoreCase("18")) {
                    sp_tl7_Q3.setSelection(18);
                } else if (mData[0][3].equalsIgnoreCase("19")) {
                    sp_tl7_Q3.setSelection(19);
                } else if (mData[0][3].equalsIgnoreCase("20")) {
                    sp_tl7_Q3.setSelection(20);
                }


                if (mData[0][4].equalsIgnoreCase("0")) {
                    sp_tl7_Q4.setSelection(0);
                } else if (mData[0][4].equalsIgnoreCase("1")) {
                    sp_tl7_Q4.setSelection(1);
                } else if (mData[0][4].equalsIgnoreCase("2")) {
                    sp_tl7_Q4.setSelection(2);
                } else if (mData[0][4].equalsIgnoreCase("3")) {
                    sp_tl7_Q4.setSelection(3);
                } else if (mData[0][4].equalsIgnoreCase("4")) {
                    sp_tl7_Q4.setSelection(4);
                } else if (mData[0][4].equalsIgnoreCase("5")) {
                    sp_tl7_Q4.setSelection(5);
                } else if (mData[0][4].equalsIgnoreCase("6")) {
                    sp_tl7_Q4.setSelection(6);
                } else if (mData[0][4].equalsIgnoreCase("7")) {
                    sp_tl7_Q4.setSelection(7);
                } else if (mData[0][4].equalsIgnoreCase("8")) {
                    sp_tl7_Q4.setSelection(8);
                } else if (mData[0][4].equalsIgnoreCase("9")) {
                    sp_tl7_Q4.setSelection(9);
                } else if (mData[0][4].equalsIgnoreCase("10")) {
                    sp_tl7_Q4.setSelection(10);
                } else if (mData[0][4].equalsIgnoreCase("11")) {
                    sp_tl7_Q4.setSelection(11);
                } else if (mData[0][4].equalsIgnoreCase("12")) {
                    sp_tl7_Q4.setSelection(12);
                } else if (mData[0][4].equalsIgnoreCase("13")) {
                    sp_tl7_Q4.setSelection(13);
                } else if (mData[0][4].equalsIgnoreCase("14")) {
                    sp_tl7_Q4.setSelection(14);
                } else if (mData[0][4].equalsIgnoreCase("15")) {
                    sp_tl7_Q4.setSelection(15);
                } else if (mData[0][4].equalsIgnoreCase("16")) {
                    sp_tl7_Q4.setSelection(16);
                } else if (mData[0][4].equalsIgnoreCase("17")) {
                    sp_tl7_Q4.setSelection(17);
                } else if (mData[0][4].equalsIgnoreCase("18")) {
                    sp_tl7_Q4.setSelection(18);
                } else if (mData[0][4].equalsIgnoreCase("19")) {
                    sp_tl7_Q4.setSelection(19);
                }else if (mData[0][4].equalsIgnoreCase("20")) {
                    sp_tl7_Q4.setSelection(20);
                }

                if (mData[0][5].equalsIgnoreCase("0")) {
                    sp_tl7_Q5.setSelection(0);
                } else if (mData[0][5].equalsIgnoreCase("1")) {
                    sp_tl7_Q5.setSelection(1);
                } else if (mData[0][5].equalsIgnoreCase("2")) {
                    sp_tl7_Q5.setSelection(2);
                } else if (mData[0][5].equalsIgnoreCase("3")) {
                    sp_tl7_Q5.setSelection(3);
                } else if (mData[0][5].equalsIgnoreCase("4")) {
                    sp_tl7_Q5.setSelection(4);
                } else if (mData[0][5].equalsIgnoreCase("5")) {
                    sp_tl7_Q5.setSelection(5);
                } else if (mData[0][5].equalsIgnoreCase("6")) {
                    sp_tl7_Q5.setSelection(6);
                } else if (mData[0][5].equalsIgnoreCase("7")) {
                    sp_tl7_Q5.setSelection(7);
                } else if (mData[0][5].equalsIgnoreCase("8")) {
                    sp_tl7_Q5.setSelection(8);
                } else if (mData[0][5].equalsIgnoreCase("9")) {
                    sp_tl7_Q5.setSelection(9);
                } else if (mData[0][5].equalsIgnoreCase("10")) {
                    sp_tl7_Q5.setSelection(10);
                } else if (mData[0][5].equalsIgnoreCase("11")) {
                    sp_tl7_Q5.setSelection(11);
                } else if (mData[0][5].equalsIgnoreCase("12")) {
                    sp_tl7_Q5.setSelection(12);
                } else if (mData[0][5].equalsIgnoreCase("13")) {
                    sp_tl7_Q5.setSelection(13);
                } else if (mData[0][5].equalsIgnoreCase("14")) {
                    sp_tl7_Q5.setSelection(14);
                } else if (mData[0][5].equalsIgnoreCase("15")) {
                    sp_tl7_Q5.setSelection(15);
                } else if (mData[0][5].equalsIgnoreCase("16")) {
                    sp_tl7_Q5.setSelection(16);
                } else if (mData[0][5].equalsIgnoreCase("17")) {
                    sp_tl7_Q5.setSelection(17);
                } else if (mData[0][5].equalsIgnoreCase("18")) {
                    sp_tl7_Q5.setSelection(18);
                } else if (mData[0][5].equalsIgnoreCase("19")) {
                    sp_tl7_Q5.setSelection(19);
                }else if (mData[0][5].equalsIgnoreCase("20")) {
                    sp_tl7_Q5.setSelection(20);
                }

                if (mData[0][6].equalsIgnoreCase("0")) {
                    sp_tl7_Q6.setSelection(0);
                } else if (mData[0][6].equalsIgnoreCase("1")) {
                    sp_tl7_Q6.setSelection(1);
                } else if (mData[0][6].equalsIgnoreCase("2")) {
                    sp_tl7_Q6.setSelection(2);
                } else if (mData[0][6].equalsIgnoreCase("3")) {
                    sp_tl7_Q6.setSelection(3);
                } else if (mData[0][6].equalsIgnoreCase("4")) {
                    sp_tl7_Q6.setSelection(4);
                } else if (mData[0][6].equalsIgnoreCase("5")) {
                    sp_tl7_Q6.setSelection(5);
                } else if (mData[0][6].equalsIgnoreCase("6")) {
                    sp_tl7_Q6.setSelection(6);
                } else if (mData[0][6].equalsIgnoreCase("7")) {
                    sp_tl7_Q6.setSelection(7);
                } else if (mData[0][6].equalsIgnoreCase("8")) {
                    sp_tl7_Q6.setSelection(8);
                } else if (mData[0][6].equalsIgnoreCase("9")) {
                    sp_tl7_Q6.setSelection(9);
                } else if (mData[0][6].equalsIgnoreCase("10")) {
                    sp_tl7_Q6.setSelection(10);
                } else if (mData[0][6].equalsIgnoreCase("11")) {
                    sp_tl7_Q6.setSelection(11);
                } else if (mData[0][6].equalsIgnoreCase("12")) {
                    sp_tl7_Q6.setSelection(12);
                } else if (mData[0][6].equalsIgnoreCase("13")) {
                    sp_tl7_Q6.setSelection(13);
                } else if (mData[0][6].equalsIgnoreCase("14")) {
                    sp_tl7_Q6.setSelection(14);
                } else if (mData[0][6].equalsIgnoreCase("15")) {
                    sp_tl7_Q6.setSelection(15);
                } else if (mData[0][6].equalsIgnoreCase("16")) {
                    sp_tl7_Q6.setSelection(16);
                } else if (mData[0][6].equalsIgnoreCase("17")) {
                    sp_tl7_Q6.setSelection(17);
                } else if (mData[0][6].equalsIgnoreCase("18")) {
                    sp_tl7_Q6.setSelection(18);
                } else if (mData[0][6].equalsIgnoreCase("19")) {
                    sp_tl7_Q6.setSelection(19);
                }else if (mData[0][6].equalsIgnoreCase("20")) {
                    sp_tl7_Q6.setSelection(20);
                }

                if (mData[0][7].equalsIgnoreCase("0")) {
                    sp_tl7_Q7.setSelection(0);
                } else if (mData[0][7].equalsIgnoreCase("1")) {
                    sp_tl7_Q7.setSelection(1);
                } else if (mData[0][7].equalsIgnoreCase("2")) {
                    sp_tl7_Q7.setSelection(2);
                } else if (mData[0][7].equalsIgnoreCase("3")) {
                    sp_tl7_Q7.setSelection(3);
                } else if (mData[0][7].equalsIgnoreCase("4")) {
                    sp_tl7_Q7.setSelection(4);
                } else if (mData[0][7].equalsIgnoreCase("5")) {
                    sp_tl7_Q7.setSelection(5);
                } else if (mData[0][7].equalsIgnoreCase("6")) {
                    sp_tl7_Q7.setSelection(6);
                } else if (mData[0][7].equalsIgnoreCase("7")) {
                    sp_tl7_Q7.setSelection(7);
                } else if (mData[0][7].equalsIgnoreCase("8")) {
                    sp_tl7_Q7.setSelection(8);
                } else if (mData[0][7].equalsIgnoreCase("9")) {
                    sp_tl7_Q7.setSelection(9);
                } else if (mData[0][7].equalsIgnoreCase("10")) {
                    sp_tl7_Q7.setSelection(10);
                } else if (mData[0][7].equalsIgnoreCase("11")) {
                    sp_tl7_Q7.setSelection(11);
                } else if (mData[0][7].equalsIgnoreCase("12")) {
                    sp_tl7_Q7.setSelection(12);
                } else if (mData[0][7].equalsIgnoreCase("13")) {
                    sp_tl7_Q7.setSelection(13);
                } else if (mData[0][7].equalsIgnoreCase("14")) {
                    sp_tl7_Q7.setSelection(14);
                } else if (mData[0][7].equalsIgnoreCase("15")) {
                    sp_tl7_Q7.setSelection(15);
                } else if (mData[0][7].equalsIgnoreCase("16")) {
                    sp_tl7_Q7.setSelection(16);
                } else if (mData[0][7].equalsIgnoreCase("17")) {
                    sp_tl7_Q7.setSelection(17);
                } else if (mData[0][7].equalsIgnoreCase("18")) {
                    sp_tl7_Q7.setSelection(18);
                } else if (mData[0][7].equalsIgnoreCase("19")) {
                    sp_tl7_Q7.setSelection(19);
                }else if (mData[0][7].equalsIgnoreCase("20")) {
                    sp_tl7_Q7.setSelection(20);
                }
            }

            } catch (Exception e) {
            Log.d("111", e.getMessage());

        }
    }
}
