package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class DietLifestyle extends AppCompatActivity {
    RadioButton rd_tl7_Q1_yes, rd_tl7_Q2_yes, rd_tl7_Q1_no, rd_tl7_Q2_no;
    RadioGroup rd_tl7_Q1, rd_tl7_Q2;
    RadioButton radiovalueQ1, radiovalueQ2;
    String tl7_Q1, tl7_Q2, ContactNo;
    Spinner sp_tl7_Q3, sp_tl7_Q4, sp_tl7_Q5, sp_tl7_Q6, sp_tl7_Q7;
    Button btn_submit_DL, btn_next;
    int tl7_Q3, tl7_Q4, tl7_Q5, tl7_Q6, tl7_Q7;
    DatabaseHelperRP mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_lifestyle);

        mDatabaseHelper=new DatabaseHelperRP(this);

        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");

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
                mDatabaseHelper.updateTool7Status(ContactNo,tool7);
                Toast.makeText(DietLifestyle.this, "Tool7 Completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DietLifestyle.this, Modules.class);
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });
    }

    private void addtool7Data() {
        radiovalueQ1 = (RadioButton) this.findViewById(rd_tl7_Q1.getCheckedRadioButtonId());
        tl7_Q1 = radiovalueQ1.getText().toString();

        radiovalueQ2 = (RadioButton) this.findViewById(rd_tl7_Q2.getCheckedRadioButtonId());
        tl7_Q2 = radiovalueQ2.getText().toString();

        boolean isInserted = mDatabaseHelper.addTool7Data(ContactNo, tl7_Q1, tl7_Q2, tl7_Q3, tl7_Q4, tl7_Q5, tl7_Q6, tl7_Q7);
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
        }
        else{
            Toast.makeText(this, "1. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        if(rd_tl7_Q2_yes.isChecked() == true){
            Toast.makeText(this, "2. High Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "2. Normal Risk", Toast.LENGTH_SHORT).show();
        }

        if(tl7_Q3 < 5){
            Toast.makeText(this, "3. High Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "3. Normal Risk", Toast.LENGTH_SHORT).show();

        }
        if(tl7_Q4 < 3){
            Toast.makeText(this, "4. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "4. High Risk", Toast.LENGTH_SHORT).show();

        }
        if(tl7_Q5 < 3){
            Toast.makeText(this, "5. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "5. High Risk", Toast.LENGTH_SHORT).show();

        }
        if(tl7_Q6 < 3){
            Toast.makeText(this, "6. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "6. High Risk", Toast.LENGTH_SHORT).show();

        }
        if(tl7_Q7 > 2){
            Toast.makeText(this, "7. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "7. High Risk", Toast.LENGTH_SHORT).show();

        }
    }
}
