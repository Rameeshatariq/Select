package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class PhysicalActivity extends AppCompatActivity {
    private RadioGroup rd_PA_Q1, rd_PA_Q3, rd_PA_Q5;
    private RadioButton rd_PA_Q1_yes, rd_PA_Q1_no, rd_PA_Q3_yes, rd_PA_Q3_no, rd_PA_Q5_yes, rd_PA_Q5_no;
    RadioButton radiovalueQ1, radiovalueQ3, radiovalueQ5;
    String tool5_Q1, tool5_Q2, tool5_Q3, tool5_Q4, tool5_Q5, tool5_Q6, ContactNo, sp_Q1, sp_Q3, sp_Q5, tool5_Q2_hoursMins, tool5_Q4_hoursMins, tool5_Q6_hoursMins;
    private LinearLayout linear_PA_Q2, linear_PA_Q4, linear_PA_Q6;
    private Spinner sp_PA_Q1, sp_PA_Q3, sp_PA_Q5;
    private EditText  et_PA_Q2_hours, et_PA_Q2_mins, et_PA_Q4_hours, et_PA_Q4_mins, et_PA_Q6_hours, et_PA_Q6_mins;
    CheckBox ck_PA_Q2, ck_PA_Q4, ck_PA_Q6;
    private DatabaseHelperRP databaseHelperRP;
    private Toolbar toolbar;
    Button btn_PAsubmit,btn_PA_saveExit;
    String reqxStrHour, regxStrMin;
    String tool5_Q2_hours, tool5_Q2_mins, tool5_Q4_hours, tool5_Q4_mins, tool5_Q6_hours, tool5_Q6_mins,tool1,tool2,tool3;
    Context ctx = this;
    Lister ls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);

        reqxStrHour="[0-2][0-4]$";
        regxStrMin="[0-5][0-9]$";

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tool 5");
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


        rd_PA_Q1=(RadioGroup) findViewById(R.id.rd_PA_Q1);
        rd_PA_Q3=(RadioGroup) findViewById(R.id.rd_PA_Q3);
        rd_PA_Q5=(RadioGroup) findViewById(R.id.rd_PA_Q5);

        ck_PA_Q2=(CheckBox)findViewById(R.id.ck_PA_Q2);
        ck_PA_Q4=(CheckBox)findViewById(R.id.ck_PA_Q4);
        ck_PA_Q6=(CheckBox)findViewById(R.id.ck_PA_Q6);

        databaseHelperRP=new DatabaseHelperRP(this);
        ls = new Lister(ctx);

        sp_PA_Q1=(Spinner)findViewById(R.id.sp_PA_Q1);
        sp_PA_Q3=(Spinner)findViewById(R.id.sp_PA_Q3);
        sp_PA_Q5=(Spinner)findViewById(R.id.sp_PA_Q5);

        rd_PA_Q1_yes=(RadioButton)findViewById(R.id.rd_PA_Q1_yes);
        rd_PA_Q3_yes=(RadioButton)findViewById(R.id.rd_PA_Q3_yes);
        rd_PA_Q5_yes=(RadioButton)findViewById(R.id.rd_PA_Q5_yes);

        et_PA_Q2_hours=(EditText)findViewById(R.id.ed_PA_Q2_hours);
        et_PA_Q2_mins=(EditText)findViewById(R.id.ed_PA_Q2_days);
        et_PA_Q4_hours=(EditText)findViewById(R.id.ed_PA_Q4_hours);
        et_PA_Q4_mins=(EditText)findViewById(R.id.ed_PA_Q4_days);
        et_PA_Q6_hours=(EditText)findViewById(R.id.ed_PA_Q6_hours);
        et_PA_Q6_mins=(EditText)findViewById(R.id.ed_PA_Q6_days);

        linear_PA_Q2=(LinearLayout)findViewById(R.id.linear_PA_Q2);
        linear_PA_Q4=(LinearLayout)findViewById(R.id.linear_PA_Q4);
        linear_PA_Q6=(LinearLayout)findViewById(R.id.linear_PA_Q6);

        linear_PA_Q2.setVisibility(View.GONE);
        linear_PA_Q4.setVisibility(View.GONE);
        linear_PA_Q6.setVisibility(View.GONE);

        rd_PA_Q1_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rd_PA_Q1_yes.isChecked()==true){
                    linear_PA_Q2.setVisibility(View.VISIBLE);
                    sp_PA_Q1.setVisibility(View.VISIBLE);
                    sp_Q1=sp_PA_Q1.getSelectedItem().toString();
                }
                else{
                    linear_PA_Q2.setVisibility(View.GONE);
                    sp_PA_Q1.setVisibility(View.GONE);
                    ck_PA_Q2.setChecked(false);
                    sp_Q1="";
                    tool5_Q2_hours="";
                    tool5_Q2_mins="";

                }
            }
        });
        rd_PA_Q3_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rd_PA_Q3_yes.isChecked()==true){
                    linear_PA_Q4.setVisibility(View.VISIBLE);
                    sp_PA_Q3.setVisibility(View.VISIBLE);
                    sp_Q3=sp_PA_Q3.getSelectedItem().toString();
                }
                else{
                    linear_PA_Q4.setVisibility(View.GONE);
                    sp_PA_Q3.setVisibility(View.GONE);
                    ck_PA_Q4.setChecked(false);
                    sp_Q3="";
                    tool5_Q4_hours="";
                    tool5_Q4_mins="";
                }
            }
        });
        rd_PA_Q5_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rd_PA_Q5_yes.isChecked()==true){
                    linear_PA_Q6.setVisibility(View.VISIBLE);
                    sp_PA_Q5.setVisibility(View.VISIBLE);
                    sp_Q5=sp_PA_Q5.getSelectedItem().toString();
                }
                else{
                    linear_PA_Q6.setVisibility(View.GONE);
                    sp_PA_Q5.setVisibility(View.GONE);
                    ck_PA_Q6.setChecked(false);
                    sp_Q5="";
                    tool5_Q6_hours="";
                    tool5_Q6_mins="";
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

        btn_PAsubmit=(Button)findViewById(R.id.btn_PA_submit);
        btn_PAsubmit.setVisibility(View.GONE);

        btn_PA_saveExit=(Button)findViewById(R.id.btn_PA_saveExit);
        btn_PA_saveExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
                String tool5="0";
                databaseHelperRP.updateTool5Status(ContactNo,tool5);
                Toast.makeText(PhysicalActivity.this, "Tool5 is not Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btn_PAsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
                String tool5="1";
                databaseHelperRP.updateTool5Status(ContactNo,tool5);
                Toast.makeText(PhysicalActivity.this, "Tool5 Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        rd_PA_Q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_PA_Q1.getCheckedRadioButtonId() != -1 && rd_PA_Q3.getCheckedRadioButtonId() != -1 && rd_PA_Q5.getCheckedRadioButtonId() != -1){
                    btn_PAsubmit.setVisibility(View.VISIBLE);
                }

            }
        });
        rd_PA_Q3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_PA_Q1.getCheckedRadioButtonId() != -1 && rd_PA_Q3.getCheckedRadioButtonId() != -1 && rd_PA_Q5.getCheckedRadioButtonId() != -1) {
                    btn_PAsubmit.setVisibility(View.VISIBLE);
                }
            }
        });
        rd_PA_Q5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rd_PA_Q1.getCheckedRadioButtonId() != -1 && rd_PA_Q3.getCheckedRadioButtonId() != -1 && rd_PA_Q5.getCheckedRadioButtonId() != -1) {
                    btn_PAsubmit.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void validateData() {

        tool5_Q2_hours = et_PA_Q2_hours.getText().toString();
        tool5_Q2_mins = et_PA_Q2_mins.getText().toString();

        tool5_Q4_hours = et_PA_Q4_hours.getText().toString();
        tool5_Q4_mins = et_PA_Q4_mins.getText().toString();

        tool5_Q6_hours = et_PA_Q6_hours.getText().toString();
        tool5_Q6_mins = et_PA_Q6_mins.getText().toString();

        if (!TextUtils.isEmpty(tool5_Q2_hours)) {
            if (tool5_Q2_hours.matches(reqxStrHour) == false) {
                Toast.makeText(PhysicalActivity.this, "Invalid Hours" + tool5_Q2_hours, Toast.LENGTH_SHORT).show();
            }
        }
        else if (!TextUtils.isEmpty(tool5_Q2_mins)) {
            if (tool5_Q2_mins.matches(regxStrMin) == false) {
                Toast.makeText(PhysicalActivity.this, "Invalid Mins" + tool5_Q2_mins, Toast.LENGTH_SHORT).show();
            }
        }
        else if (!TextUtils.isEmpty(tool5_Q4_hours)) {
            if (tool5_Q4_hours.matches(reqxStrHour) == false) {
                Toast.makeText(PhysicalActivity.this, "Invalid Hours" + tool5_Q4_hours, Toast.LENGTH_SHORT).show();
            }
        }
        else if (!TextUtils.isEmpty(tool5_Q4_mins)) {
            if (tool5_Q4_mins.matches(regxStrMin) == false) {
                Toast.makeText(PhysicalActivity.this, "Invalid Mins" + tool5_Q4_mins, Toast.LENGTH_SHORT).show();
            }
        }
        else if (!TextUtils.isEmpty(tool5_Q6_hours)) {
            if (tool5_Q6_hours.matches(reqxStrHour) == false) {
                Toast.makeText(PhysicalActivity.this, "Invalid Hours" + tool5_Q6_hours, Toast.LENGTH_SHORT).show();
            }
        }
        else if (!TextUtils.isEmpty(tool5_Q6_mins)) {
            if (tool5_Q6_mins.matches(regxStrMin) == false) {
                Toast.makeText(PhysicalActivity.this, "Invalid Mins" + tool5_Q6_mins, Toast.LENGTH_SHORT).show();
            }
        }

            addTool5Data();


    }

    private void addTool5Data() {

        if (rd_PA_Q1.getCheckedRadioButtonId() != -1) {
            radiovalueQ1 = (RadioButton) this.findViewById(rd_PA_Q1.getCheckedRadioButtonId());
            tool5_Q1 = radiovalueQ1.getText().toString();
        }

        if (rd_PA_Q3.getCheckedRadioButtonId() != -1) {
            radiovalueQ3 = (RadioButton) this.findViewById(rd_PA_Q3.getCheckedRadioButtonId());
            tool5_Q3 = radiovalueQ3.getText().toString();
        }

        if (rd_PA_Q5.getCheckedRadioButtonId() != -1) {
            radiovalueQ5 = (RadioButton) this.findViewById(rd_PA_Q5.getCheckedRadioButtonId());
            tool5_Q5 = radiovalueQ5.getText().toString();
        }

        tool5_Q2_hours = et_PA_Q2_hours.getText().toString();
        tool5_Q2_mins = et_PA_Q2_mins.getText().toString();

        tool5_Q4_hours = et_PA_Q4_hours.getText().toString();
        tool5_Q4_mins = et_PA_Q4_mins.getText().toString();

        tool5_Q6_hours = et_PA_Q6_hours.getText().toString();
        tool5_Q6_mins = et_PA_Q6_mins.getText().toString();

        sp_Q1 = sp_PA_Q1.getSelectedItem().toString();
        sp_Q3 = sp_PA_Q3.getSelectedItem().toString();
        sp_Q5 = sp_PA_Q5.getSelectedItem().toString();

        if (ck_PA_Q2.isChecked()) {
            tool5_Q2 = ck_PA_Q2.getText().toString();
            tool5_Q2_hours = tool5_Q2;
            tool5_Q2_mins = tool5_Q2;
        }
        if (ck_PA_Q4.isChecked()) {
            tool5_Q4 = ck_PA_Q4.getText().toString();
            tool5_Q4_hours = tool5_Q4;
            tool5_Q4_mins = tool5_Q4;

        }
        if (ck_PA_Q6.isChecked()) {
            tool5_Q6 = ck_PA_Q6.getText().toString();
            tool5_Q6_hours = tool5_Q6;
            tool5_Q6_mins = tool5_Q6;
        }

        try {
            Log.d("000333", "save and exit");

            String[][] mData = ls.executeReader("Select *from tool5 where ContactSim  = '" + ContactNo + "'");

            if (mData != null) {
                boolean mFlag = ls.executeNonQuery("Update tool5 set " +
                        "VigourousExercise = '" + tool5_Q1 + "', " +
                        "DaysOfVigourous = '" + sp_Q1 + "', " +
                        "HoursOfVigorous = '" + tool5_Q2_hours + "', " +
                        "MinsOfVigorous = '" + tool5_Q2_mins + "', " +
                        "ModerateExercise = '" + tool5_Q3 + "', " +
                        "DaysOfModerate = '" + sp_Q3 + "', " +
                        "HoursOfModerate = '" + tool5_Q4_hours + "', " +
                        "MinsOfModerate = '" + tool5_Q4_mins + "' , " +
                        "Walk = '" + tool5_Q5 + "' , " +
                        "DaysOfWalk = '" + sp_Q5 + "' , " +
                        "HoursOfWalk = '" + tool5_Q6_hours + "' , " +
                        "MinsOfWalk = '" + tool5_Q6_mins + "'  " +
                        " where ContactSim  = '" + ContactNo + "'");
                if (mFlag == true) {
                    Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
                }

            } else {

                boolean isInserted = databaseHelperRP.addTool5Data(ContactNo, tool5_Q1, sp_Q1, tool5_Q2_hours, tool5_Q2_mins, tool5_Q3, sp_Q3, tool5_Q4_hours, tool5_Q4_mins,
                        tool5_Q5, sp_Q5, tool5_Q6_hours, tool5_Q6_mins);
                if (isInserted == true) {
                    Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

                }
            }
            finish();
        } catch (Exception e) {

            Toast.makeText(ctx, "=-=-=-=Exception  " + e, Toast.LENGTH_SHORT).show();
            Log.e("000333", "Exception " + e);
        }
    }
    public boolean isCompleted(String mPhone) {

        Lister lister = new Lister(ctx);

        String[][] mData = lister.executeReader("Select * From tool5 where ContactSim = '" + mPhone + "'");

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

        String[][] mData = lister.executeReader("Select * From tool5 where ContactSim = '" + contactNo + "'");

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


                if (mData[0][1].equalsIgnoreCase("Yes Vigorous Exercise")) {
                    rd_PA_Q1.check(R.id.rd_PA_Q1_yes);
                } else {
                    rd_PA_Q1.check(R.id.rd_PA_Q1_no);
                }

                if (mData[0][2].equalsIgnoreCase("1 day")) {
                    sp_PA_Q1.setSelection(0);
                } else if (mData[0][2].equalsIgnoreCase("2 days")) {
                    sp_PA_Q1.setSelection(1);
                } else if (mData[0][2].equalsIgnoreCase("3 days")) {
                    sp_PA_Q1.setSelection(2);
                } else if (mData[0][2].equalsIgnoreCase("4 days")) {
                    sp_PA_Q1.setSelection(3);
                } else if (mData[0][2].equalsIgnoreCase("5 days")) {
                    sp_PA_Q1.setSelection(4);
                } else if (mData[0][2].equalsIgnoreCase("6 days")) {
                    sp_PA_Q1.setSelection(5);
                } else if (mData[0][2].equalsIgnoreCase("1 week")) {
                    sp_PA_Q1.setSelection(6);
                }

                if (mData[0][3].equalsIgnoreCase("Dont know")) {
                    ck_PA_Q2.setChecked(true);
                } else {
                    et_PA_Q2_hours.setText(mData[0][3]);
                    et_PA_Q2_mins.setText(mData[0][4]);
                }
                if (mData[0][4].equalsIgnoreCase("Dont know")) {
                    ck_PA_Q2.setChecked(true);
                }
            } else {
                et_PA_Q2_hours.setText(mData[0][3]);
                et_PA_Q2_mins.setText(mData[0][4]);
            }


            if (mData[0][5].equalsIgnoreCase("Yes Moderate Exercise")) {
                rd_PA_Q3.check(R.id.rd_PA_Q3_yes);
            } else {
                rd_PA_Q3.check(R.id.rd_PA_Q3_no);
            }

            if (mData[0][6].equalsIgnoreCase("1 day")) {
                sp_PA_Q3.setSelection(0);
            } else if (mData[0][6].equalsIgnoreCase("2 days")) {
                sp_PA_Q3.setSelection(1);
            } else if (mData[0][6].equalsIgnoreCase("3 days")) {
                sp_PA_Q3.setSelection(2);
            } else if (mData[0][6].equalsIgnoreCase("4 days")) {
                sp_PA_Q3.setSelection(3);
            } else if (mData[0][6].equalsIgnoreCase("5 days")) {
                sp_PA_Q3.setSelection(4);
            } else if (mData[0][6].equalsIgnoreCase("6 days")) {
                sp_PA_Q3.setSelection(5);
            } else if (mData[0][6].equalsIgnoreCase("1 week")) {
                sp_PA_Q3.setSelection(6);
            }

            if (mData[0][7].equalsIgnoreCase("Dont know")) {
                ck_PA_Q4.setChecked(true);
            } else {
                et_PA_Q4_hours.setText(mData[0][7]);
                et_PA_Q4_mins.setText(mData[0][8]);
            }
            if (mData[0][8].equalsIgnoreCase("Dont know")) {
                ck_PA_Q4.setChecked(true);
            } else{
                et_PA_Q4_hours.setText(mData[0][7]);
                et_PA_Q4_mins.setText(mData[0][8]);
        }

            if (mData[0][9].equalsIgnoreCase("Yes Walk")) {
                rd_PA_Q5.check(R.id.rd_PA_Q5_yes);
            } else {
                rd_PA_Q5.check(R.id.rd_PA_Q5_no);
            }

            if(mData[0][10].equalsIgnoreCase("1 day")){
                sp_PA_Q5.setSelection(0);
            }
            else if(mData[0][10].equalsIgnoreCase("2 days")){
                sp_PA_Q5.setSelection(1);
            }
            else if(mData[0][10].equalsIgnoreCase("3 days")){
                sp_PA_Q5.setSelection(2);
            }
            else if(mData[0][10].equalsIgnoreCase("4 days")){
                sp_PA_Q5.setSelection(3);
            }
            else if(mData[0][10].equalsIgnoreCase("5 days")){
                sp_PA_Q5.setSelection(4);
            }
            else if(mData[0][10].equalsIgnoreCase("6 days")){
                sp_PA_Q5.setSelection(5);
            }
            else if(mData[0][10].equalsIgnoreCase("1 week")){
                sp_PA_Q5.setSelection(6);
            }

            if(mData[0][11].equalsIgnoreCase("Dont know")){
                ck_PA_Q6.setChecked(true);
            }else{
                et_PA_Q6_hours.setText(mData[0][11]);
                et_PA_Q6_mins.setText(mData[0][12]);
            }
            if(mData[0][12].equalsIgnoreCase("Dont know")){
                ck_PA_Q6.setChecked(true);
            } else {
            et_PA_Q6_hours.setText(mData[0][11]);
            et_PA_Q6_mins.setText(mData[0][12]);
        }

        } catch (Exception e) {
            Log.d("111", e.getMessage());

        }
    }
}
