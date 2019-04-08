package com.example.cv.select;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
    private boolean switchState;
    Toolbar toolbar;
    String tl7_Q1, tl7_Q2, ContactNo,tool1,tool2,tool3,Q1,Q2, Q3,Q4,Q5,Q6,Q7;
    Spinner sp_tl7_Q3, sp_tl7_Q4, sp_tl7_Q5, sp_tl7_Q6, sp_tl7_Q7;
    Button btn_submit_DL, btn_next;
    int tl7_Q3, tl7_Q4, tl7_Q5, tl7_Q6, tl7_Q7;
    private Switch syncData;
    DatabaseHelperRP mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_lifestyle);

        mDatabaseHelper=new DatabaseHelperRP(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tool 7");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DietLifestyle.this, Modules.class);
                startActivity(intent);
            }
        });

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

        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");
        tool1=intent.getStringExtra("tool1");
        tool2=intent.getStringExtra("tool2");
        tool3=intent.getStringExtra("tool3");

        rd_tl7_Q1_yes=(RadioButton)findViewById(R.id.rd_DL_Q1_yes);
        rd_tl7_Q1_no=(RadioButton)findViewById(R.id.rd_DL_Q1_no);

        rd_tl7_Q2_yes=(RadioButton)findViewById(R.id.rd_DL_Q2_yes);
        rd_tl7_Q2_no=(RadioButton)findViewById(R.id.rd_DL_Q2_no);

        rd_tl7_Q1=(RadioGroup)findViewById(R.id.rd_DL_Q1);
        rd_tl7_Q2=(RadioGroup)findViewById(R.id.rd_DL_Q2);

        sp_tl7_Q3=(Spinner)findViewById(R.id.sp_DL_Q3);
        sp_tl7_Q4=(Spinner)findViewById(R.id.sp_DL_Q4);
        sp_tl7_Q5=(Spinner)findViewById(R.id.sp_DL_Q5);
        sp_tl7_Q6=(Spinner)findViewById(R.id.sp_DL_Q6);
        sp_tl7_Q7=(Spinner)findViewById(R.id.sp_DL_Q7);

        btn_next=(Button)findViewById(R.id.btn_next);
        btn_submit_DL=(Button)findViewById(R.id.btn_DL_submit);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DietLifestyle.this, HighRisk.class);
                startActivity(intent);
            }
        });
        btn_submit_DL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
                addtool7Data();
                String tool7="Completed";
                String tool7Result= Q1+ ", " + Q2+ ", " + Q3+ ", " +Q4+ ", " +Q5+ ", " +Q6+ ", " +Q7;
                mDatabaseHelper.updateTool7Status(ContactNo,tool7);
                Toast.makeText(DietLifestyle.this, "Tool7 Completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DietLifestyle.this, Modules.class);
                intent.putExtra("ContactNo", ContactNo);
                intent.putExtra("tool1", tool1);
                intent.putExtra("tool2", tool2);
                intent.putExtra("tool3", tool3);
                intent.putExtra("tool7",tool7Result);
                startActivity(intent);
            }
        });
    }

    private void addtool7Data() {
        radiovalueQ1 = (RadioButton) this.findViewById(rd_tl7_Q1.getCheckedRadioButtonId());
        tl7_Q1 = radiovalueQ1.getText().toString();

        radiovalueQ2 = (RadioButton) this.findViewById(rd_tl7_Q2.getCheckedRadioButtonId());
        tl7_Q2 = radiovalueQ2.getText().toString();

        boolean isInserted = mDatabaseHelper.addTool7Data(ContactNo, tl7_Q1, tl7_Q2, tl7_Q3, tl7_Q4, tl7_Q5, tl7_Q6, tl7_Q7,switchState);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    private void checkData() {
        tl7_Q3 = Integer.parseInt(sp_tl7_Q3.getSelectedItem().toString());
        tl7_Q4 = Integer.parseInt(sp_tl7_Q4.getSelectedItem().toString());
        tl7_Q5 = Integer.parseInt(sp_tl7_Q5.getSelectedItem().toString());
        tl7_Q6 = Integer.parseInt(sp_tl7_Q6.getSelectedItem().toString());
        tl7_Q7 = Integer.parseInt(sp_tl7_Q7.getSelectedItem().toString());

        if(rd_tl7_Q1_yes.isChecked() == true){
            Toast.makeText(this, "1. High Risk", Toast.LENGTH_SHORT).show();
            Q1="Q1 High Risk";
        }
        else{
            Toast.makeText(this, "1. Normal Risk", Toast.LENGTH_SHORT).show();
            Q1="Q1 Normal Risk";

        }
        if(rd_tl7_Q2_yes.isChecked() == true){
            Toast.makeText(this, "2. High Risk", Toast.LENGTH_SHORT).show();
            Q2="Q2 High Risk";

        }
        else{
            Toast.makeText(this, "2. Normal Risk", Toast.LENGTH_SHORT).show();
            Q2="Q2 Normal Risk";

        }

        if(tl7_Q3 < 5){
            Toast.makeText(this, "3. High Risk", Toast.LENGTH_SHORT).show();
            Q3="Q3 High Risk";

        }
        else{
            Toast.makeText(this, "3. Normal Risk", Toast.LENGTH_SHORT).show();
            Q3="Q3 Normal Risk";
        }
        if(tl7_Q4 < 3){
            Toast.makeText(this, "4. Normal Risk", Toast.LENGTH_SHORT).show();
            Q4="Q4 Normal Risk";
        }
        else{
            Toast.makeText(this, "4. High Risk", Toast.LENGTH_SHORT).show();
            Q4="Q4 High Risk";
        }
        if(tl7_Q5 < 3){
            Toast.makeText(this, "5. Normal Risk", Toast.LENGTH_SHORT).show();
            Q5="Q5 Normal Risk";
        }
        else{
            Toast.makeText(this, "5. High Risk", Toast.LENGTH_SHORT).show();
            Q5="Q5 High Risk";
        }
        if(tl7_Q6 < 3){
            Toast.makeText(this, "6. Normal Risk", Toast.LENGTH_SHORT).show();
            Q6="Q6 Normal Risk";
        }
        else{
            Toast.makeText(this, "6. High Risk", Toast.LENGTH_SHORT).show();
            Q6="Q6 High Risk";
        }
        if(tl7_Q7 > 2){
            Toast.makeText(this, "7. Normal Risk", Toast.LENGTH_SHORT).show();
            Q7="Q7 Normal Risk";
        }
        else{
            Toast.makeText(this, "7. High Risk", Toast.LENGTH_SHORT).show();
            Q7="Q7 High Risk";
        }
    }
}
