package com.example.cv.select;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    private Switch syncData;
    private boolean switchState;
    Button btn_PAsubmit;
    String tool5_Q2_hours, tool5_Q2_mins, tool5_Q4_hours, tool5_Q4_mins, tool5_Q6_hours, tool5_Q6_mins,tool1,tool2,tool3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tool 5");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PhysicalActivity.this, Modules.class);
                startActivity(intent);
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

        syncData=(Switch)findViewById(R.id.syncData);
        syncData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(syncData.isChecked()){
                    switchState=true;
                }
                else{
                    switchState=false;
                }
            }
        });

        rd_PA_Q1_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rd_PA_Q1_yes.isChecked()==true){
                    linear_PA_Q2.setVisibility(View.VISIBLE);
                    sp_PA_Q1.setVisibility(View.VISIBLE);
                    sp_Q1=sp_PA_Q1.getSelectedItem().toString();
                }
                else{
                    linear_PA_Q2.setVisibility(View.INVISIBLE);
                    sp_PA_Q1.setVisibility(View.INVISIBLE);
                    sp_Q1="";
                    tool5_Q2_hoursMins="";

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
                    linear_PA_Q4.setVisibility(View.INVISIBLE);
                    sp_PA_Q3.setVisibility(View.INVISIBLE);
                    sp_Q3="";
                    tool5_Q4_hoursMins="";
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
                    sp_Q5="";


                }
                else{
                    linear_PA_Q6.setVisibility(View.INVISIBLE);
                    sp_PA_Q5.setVisibility(View.INVISIBLE);
                    sp_Q5="";
                    tool5_Q6_hoursMins="";
                }
            }
        });
        btn_PAsubmit=(Button)findViewById(R.id.btn_PA_submit);
        btn_PAsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool5Data();
                String tool5="Completed";
                databaseHelperRP.updateTool5Status(ContactNo,tool5);
                Toast.makeText(PhysicalActivity.this, "Tool5 Completed", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(PhysicalActivity.this, Modules.class);
                intent.putExtra("ContactNo", ContactNo);
                intent.putExtra("tool1", tool1);
                intent.putExtra("tool2", tool2);
                intent.putExtra("tool3", tool3);
                startActivity(intent);
            }
        });
    }

    private void addTool5Data() {
        radiovalueQ1 = (RadioButton) this.findViewById(rd_PA_Q1.getCheckedRadioButtonId());
        tool5_Q1 = radiovalueQ1.getText().toString();

        radiovalueQ3 = (RadioButton) this.findViewById(rd_PA_Q3.getCheckedRadioButtonId());
        tool5_Q3 = radiovalueQ3.getText().toString();

        radiovalueQ5 = (RadioButton) this.findViewById(rd_PA_Q5.getCheckedRadioButtonId());
        tool5_Q5 = radiovalueQ5.getText().toString();

        tool5_Q2_hours= et_PA_Q2_hours.getText().toString();
        tool5_Q2_mins=et_PA_Q2_mins.getText().toString();

        tool5_Q4_hours= et_PA_Q4_hours.getText().toString();
        tool5_Q4_mins=et_PA_Q4_mins.getText().toString();

        tool5_Q6_hours= et_PA_Q6_hours.getText().toString();
        tool5_Q6_mins=et_PA_Q6_mins.getText().toString();

        tool5_Q2_hoursMins= tool5_Q2_hours + " Hours " + tool5_Q2_mins + " Mins";
        tool5_Q4_hoursMins= tool5_Q4_hours + " Hours " + tool5_Q4_mins + " Mins";
        tool5_Q6_hoursMins= tool5_Q6_hours + " Hours " + tool5_Q6_mins + " Mins";

        if(ck_PA_Q2.isChecked()) {
            tool5_Q2 = ck_PA_Q2.getText().toString();
            tool5_Q2_hoursMins= tool5_Q2;
        }
        if(ck_PA_Q4.isChecked()) {
            tool5_Q4 = ck_PA_Q4.getText().toString();
            tool5_Q4_hoursMins=tool5_Q4;
        }
        if(ck_PA_Q6.isChecked()) {
            tool5_Q6 = ck_PA_Q6.getText().toString();
            tool5_Q6_hoursMins=tool5_Q6;
        }
        boolean isInserted = databaseHelperRP.addTool5Data(ContactNo, tool5_Q1, sp_Q1, tool5_Q2_hoursMins, tool5_Q3, sp_Q3, tool5_Q4_hoursMins,
                tool5_Q5, sp_Q5, tool5_Q6_hoursMins,switchState);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}
