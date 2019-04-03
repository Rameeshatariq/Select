package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class RiskAndTriageCVA extends AppCompatActivity {
    private RadioButton rd_tl1a_Q1_yes, rd_tl1a_Q1_no, rd_tl1a_Q2_yes, rd_tl1a_Q2_no, rd_tl1a_Q3_yes, rd_tl1a_Q3_no, rd_tl1a_Q4_yes, rd_tl1a_Q4_no,
            rd_tl1a_Q5_yes, rd_tl1a_Q5_no, rd_tl1a_Q6_yes, rd_tl1a_Q6_no, rd_tl1a_Q7_yes, rd_tl1a_Q7_no, rd_tl1a_Q8_yes, rd_tl1a_Q8_no;
    private Button submitRT_CVA;
    private String tool1_Q1, tool1_Q2,tool1_Q3,tool1_Q4,tool1_Q5,tool1_Q6,tool1_Q7,tool1_Q8;
    static String CVAEvent = null;
    private RadioGroup rd_tl1a_Q1, rd_tl1a_Q2, rd_tl1a_Q3,rd_tl1a_Q4, rd_tl1a_Q5,rd_tl1a_Q6,rd_tl1a_Q7,rd_tl1a_Q8;
    private RadioButton radiovalueQ1, radiovalueQ2, radiovalueQ3, radiovalueQ4, radiovalueQ5, radiovalueQ6, radiovalueQ7, radiovalueQ8;
    private DatabaseHelperRP mDatabaseHelper;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_and_triage_cv);

        mDatabaseHelper=new DatabaseHelperRP(this);

       Intent intent=getIntent();
       ContactNo=intent.getStringExtra("ContactNo");

        rd_tl1a_Q1_yes=(RadioButton)findViewById(R.id.rd_rt_Q1_yes);
        rd_tl1a_Q1_no=(RadioButton)findViewById(R.id.rd_rt_Q1_no);

        rd_tl1a_Q2_yes=(RadioButton)findViewById(R.id.rd_rt_Q2_yes);
        rd_tl1a_Q2_no=(RadioButton)findViewById(R.id.rd_rt_Q2_no);

        rd_tl1a_Q3_yes=(RadioButton)findViewById(R.id.rd_rt_Q3_yes);
        rd_tl1a_Q3_no=(RadioButton)findViewById(R.id.rd_rt_Q3_no);

        rd_tl1a_Q4_yes=(RadioButton)findViewById(R.id.rd_rt_Q4_yes);
        rd_tl1a_Q4_no=(RadioButton)findViewById(R.id.rd_rt_Q4_no);

        rd_tl1a_Q5_yes=(RadioButton)findViewById(R.id.rd_rt_Q5_yes);
        rd_tl1a_Q5_no=(RadioButton)findViewById(R.id.rd_rt_Q5_no);

        rd_tl1a_Q6_yes=(RadioButton)findViewById(R.id.rd_rt_Q6_yes);
        rd_tl1a_Q6_no=(RadioButton)findViewById(R.id.rd_rt_Q6_no);

        rd_tl1a_Q7_yes=(RadioButton)findViewById(R.id.rd_rt_Q7_yes);
        rd_tl1a_Q7_no=(RadioButton)findViewById(R.id.rd_rt_Q7_no);

        rd_tl1a_Q8_yes=(RadioButton)findViewById(R.id.rd_rt_Q8_yes);
        rd_tl1a_Q8_no=(RadioButton)findViewById(R.id.rd_rt_Q8_no);

        rd_tl1a_Q1=(RadioGroup)findViewById(R.id.rd_rt_Q1);
        rd_tl1a_Q2=(RadioGroup)findViewById(R.id.rd_rt_Q2);
        rd_tl1a_Q3=(RadioGroup)findViewById(R.id.rd_rt_Q3);
        rd_tl1a_Q4=(RadioGroup)findViewById(R.id.rd_rt_Q4);
        rd_tl1a_Q5=(RadioGroup)findViewById(R.id.rd_rt_Q5);
        rd_tl1a_Q6=(RadioGroup)findViewById(R.id.rd_rt_Q6);
        rd_tl1a_Q7=(RadioGroup)findViewById(R.id.rd_rt_Q7);
        rd_tl1a_Q8=(RadioGroup)findViewById(R.id.rd_rt_Q8);

        submitRT_CVA=(Button)findViewById(R.id.btn_rt_register);

        submitRT_CVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();

            }
        });

    }

    public void checkData() {
        if(rd_tl1a_Q1_yes.isChecked() == true || rd_tl1a_Q2_yes.isChecked() == true || rd_tl1a_Q3_yes.isChecked() == true || rd_tl1a_Q4_yes.isChecked() == true
                || rd_tl1a_Q5_yes.isChecked() == true || rd_tl1a_Q6_yes.isChecked() == true || rd_tl1a_Q7_yes.isChecked() == true || rd_tl1a_Q8_yes.isChecked() == true){

            Toast.makeText(this, "CVA Event", Toast.LENGTH_SHORT).show();
            addTool1Data();
            String tool1="Completed";
            mDatabaseHelper.updateTool1Status(ContactNo,tool1);
            Toast.makeText(this, "Tool1 Completed", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(RiskAndTriageCVA.this, Modules.class);
            intent.putExtra("ContactNo", ContactNo);
            startActivity(intent);
        }
        else{
            addTool1Data();
            Intent intent= new Intent(RiskAndTriageCVA.this, Modules.class);
            startActivity(intent);
        }
    }

    public void addTool1Data() {

        radiovalueQ1 = (RadioButton) this.findViewById(rd_tl1a_Q1.getCheckedRadioButtonId());
        tool1_Q1 = radiovalueQ1.getText().toString();

        radiovalueQ2 = (RadioButton) this.findViewById(rd_tl1a_Q2.getCheckedRadioButtonId());
        tool1_Q2 = radiovalueQ2.getText().toString();

        radiovalueQ3 = (RadioButton) this.findViewById(rd_tl1a_Q3.getCheckedRadioButtonId());
        tool1_Q3 = radiovalueQ3.getText().toString();

        radiovalueQ4 = (RadioButton) this.findViewById(rd_tl1a_Q4.getCheckedRadioButtonId());
        tool1_Q4 = radiovalueQ4.getText().toString();

        radiovalueQ5 = (RadioButton) this.findViewById(rd_tl1a_Q5.getCheckedRadioButtonId());
        tool1_Q5 = radiovalueQ5.getText().toString();

        radiovalueQ6 = (RadioButton) this.findViewById(rd_tl1a_Q6.getCheckedRadioButtonId());
        tool1_Q6 = radiovalueQ6.getText().toString();

        radiovalueQ7 = (RadioButton) this.findViewById(rd_tl1a_Q7.getCheckedRadioButtonId());
        tool1_Q7 = radiovalueQ7.getText().toString();

        radiovalueQ8 = (RadioButton) this.findViewById(rd_tl1a_Q8.getCheckedRadioButtonId());
        tool1_Q8 = radiovalueQ8.getText().toString();

        boolean isInserted = mDatabaseHelper.addTool1Data(ContactNo, tool1_Q1, tool1_Q2, tool1_Q3, tool1_Q4, tool1_Q5, tool1_Q6, tool1_Q7, tool1_Q8);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}
