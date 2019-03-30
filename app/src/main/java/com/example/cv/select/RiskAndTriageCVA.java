package com.example.cv.select;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class RiskAndTriageCVA extends AppCompatActivity {
    private RadioButton rd_tl1a_Q1_yes, rd_tl1a_Q1_no, rd_tl1a_Q2_yes, rd_tl1a_Q2_no, rd_tl1a_Q3_yes, rd_tl1a_Q3_no, rd_tl1a_Q4_yes, rd_tl1a_Q4_no,
            rd_tl1a_Q5_yes, rd_tl1a_Q5_no, rd_tl1a_Q6_yes, rd_tl1a_Q6_no, rd_tl1a_Q7_yes, rd_tl1a_Q7_no, rd_tl1a_Q8_yes, rd_tl1a_Q8_no;
    private Button submitRT_CVA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_and_triage_cv);

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

        submitRT_CVA=(Button)findViewById(R.id.btn_rt_register);

        submitRT_CVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();

            }
        });

    }

    private void checkData() {
        if(rd_tl1a_Q1_yes.isChecked() == true || rd_tl1a_Q2_yes.isChecked() == true || rd_tl1a_Q3_yes.isChecked() == true || rd_tl1a_Q4_yes.isChecked() == true
                || rd_tl1a_Q5_yes.isChecked() == true || rd_tl1a_Q6_yes.isChecked() == true || rd_tl1a_Q7_yes.isChecked() == true || rd_tl1a_Q8_yes.isChecked() == true){
            Toast.makeText(this, "CVA Event", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(RiskAndTriageCVA.this, Modules.class);
            startActivity(intent);
        }
        else{
            Intent intent= new Intent(RiskAndTriageCVA.this, Modules.class);
            startActivity(intent);
        }
    }
}
