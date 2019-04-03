package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RiskAndTriageMI extends AppCompatActivity {

    private RadioButton radiovalueQ1, radiovalueQ2, radiovalueQ3;
    String tool1bQ1, tool1bQ2, tool1bQ3;
    String Angina, AnginaorMyocardialInfarction, MyocardialInfarction, ContactNo;
    private RadioGroup rd_tl1b_Q1, rd_tl1b_Q2, rd_tl1b_Q3;
    private RadioButton rd_tl1b_Q1_yes, rd_tl1b_Q1_no, rd_tl1b_Q2_yes, rd_tl1b_Q2_no, rd_tl1b_Q3_yes, rd_tl1b_Q3_no;
    private Button submit_RTMI;
    private DatabaseHelperRP mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_and_triage_mi);

        mDatabaseHelper= new DatabaseHelperRP(this);
        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");

        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();
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
        submit_RTMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });

    }

    private void checkData() {
        if (rd_tl1b_Q1_yes.isChecked() == true) {
            Toast.makeText(this, "Angina or Myocardial Infarction", Toast.LENGTH_SHORT).show();
        } if (rd_tl1b_Q2_yes.isChecked() == true) {
            Toast.makeText(this, "Angina or Myocardial Infarction", Toast.LENGTH_SHORT).show();
            AnginaorMyocardialInfarction="Yes";
        } if (rd_tl1b_Q3_yes.isChecked() == true) {
            Toast.makeText(this, " Myocardial Infarction", Toast.LENGTH_SHORT).show();
        }
        addTool1bData();
        String tool2="Completed";
        mDatabaseHelper.updateTool2Status(ContactNo,tool2);
        Toast.makeText(this, "Tool2 Completed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RiskAndTriageMI.this, Modules.class);
        intent.putExtra("ContactNo", ContactNo);
        startActivity(intent);

    }

    private void addTool1bData() {
        radiovalueQ1 = (RadioButton) this.findViewById(rd_tl1b_Q1.getCheckedRadioButtonId());
        tool1bQ1 = radiovalueQ1.getText().toString();

        radiovalueQ2 = (RadioButton) this.findViewById(rd_tl1b_Q2.getCheckedRadioButtonId());
        tool1bQ2 = radiovalueQ2.getText().toString();

        radiovalueQ3 = (RadioButton) this.findViewById(rd_tl1b_Q3.getCheckedRadioButtonId());
        tool1bQ3 = radiovalueQ3.getText().toString();


        boolean isInserted = mDatabaseHelper.addTool2Data(ContactNo, tool1bQ1, tool1bQ2, tool1bQ3);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}