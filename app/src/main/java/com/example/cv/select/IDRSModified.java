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

public class IDRSModified extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHelper;
    private RadioButton radiovalueQ1;
    private String ContactNo, tool4_Q1,tool1,tool2,tool3;
    private RadioGroup rd_IDRS_Q1;
    private Toolbar toolbar;
    private boolean switchState;
    private Switch syncData;
    private Button btn_IDRS_submit;


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
                Intent intent= new Intent(IDRSModified.this, Modules.class);
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

        syncData=(Switch)findViewById(R.id.syncData);
       if(syncData.isChecked()){
           switchState=true;
       }
       else{
           switchState=false;
       }

        rd_IDRS_Q1=(RadioGroup)findViewById(R.id.rd_IDRS_Q1);
        btn_IDRS_submit=(Button)findViewById(R.id.btn_IDRS_submit);
        btn_IDRS_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool4Data();
                String tool4="Completed";
                mDatabaseHelper.updateTool4Status(ContactNo,tool4);
                Toast.makeText(IDRSModified.this, "Tool4 Completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(IDRSModified.this, Modules.class);
                intent.putExtra("ContactNo", ContactNo);
                intent.putExtra("tool1", tool1);
                intent.putExtra("tool2", tool2);
                intent.putExtra("tool3", tool3);
                startActivity(intent);
            }
        });



    }

    private void addTool4Data() {
        radiovalueQ1 = (RadioButton) this.findViewById(rd_IDRS_Q1.getCheckedRadioButtonId());
        tool4_Q1 = radiovalueQ1.getText().toString();

        boolean isInserted = mDatabaseHelper.addTool4Data(ContactNo, tool4_Q1,switchState);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}
