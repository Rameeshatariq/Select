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
import android.widget.Switch;
import android.widget.Toast;

public class TobbaccoSmoking extends AppCompatActivity {

    private DatabaseHelperRP mDatabaseHelper;
    private RadioButton radiovalueQ1, radiovalueQ2;
    private Toolbar toolbar;
    private String ContactNo, tool6a_Q1, tool6a_Q2,tool1,tool2,tool3;
    private RadioGroup rd_TS_Q1, rd_TS_Q2;
    private boolean switchState;
    private Switch syncData;
    private Button btn_TS_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobbacco_smoking);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tobacco Smoking");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TobbaccoSmoking.this, Modules.class);
                startActivity(intent);
            }
        });


        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");
        tool1=intent.getStringExtra("tool1");
        tool2=intent.getStringExtra("tool2");
        tool3=intent.getStringExtra("tool3");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        mDatabaseHelper=new DatabaseHelperRP(this);

        rd_TS_Q1=(RadioGroup)findViewById(R.id.rd_TS_Q1);
        rd_TS_Q2=(RadioGroup)findViewById(R.id.rd_TS_Q2);

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

        btn_TS_submit=(Button)findViewById(R.id.btn_TS_submit);
        btn_TS_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool6aData();
                String tool6a="Completed";
                mDatabaseHelper.updateTool6aStatus(ContactNo,tool6a);
                Toast.makeText(TobbaccoSmoking.this, "Tool6a Completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TobbaccoSmoking.this, Modules.class);
                intent.putExtra("ContactNo", ContactNo);
                intent.putExtra("ContactNo", ContactNo);
                intent.putExtra("tool1", tool1);
                intent.putExtra("tool2", tool2);
                intent.putExtra("tool3", tool3);
                startActivity(intent);
            }
        });



    }

    private void addTool6aData() {
        radiovalueQ1 = (RadioButton) this.findViewById(rd_TS_Q1.getCheckedRadioButtonId());
        tool6a_Q1 = radiovalueQ1.getText().toString();

        radiovalueQ2 = (RadioButton) this.findViewById(rd_TS_Q2.getCheckedRadioButtonId());
        tool6a_Q2 = radiovalueQ2.getText().toString();

        boolean isInserted = mDatabaseHelper.addTool6aData(ContactNo, tool6a_Q1, tool6a_Q2,switchState);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }


    }

