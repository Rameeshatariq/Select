package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class DietLifestyle extends AppCompatActivity {
    RadioButton rd_tl5_Q1_yes, rd_tl5_Q2_yes, rd_tl5_Q1_no, rd_tl5_Q2_no;
    Spinner sp_tl5_Q3, sp_tl5_Q4, sp_tl5_Q5, sp_tl5_Q6, sp_tl5_Q7;
    Button btn_submit_DL, btn_next;
    int tl5_Q3, tl5_Q4, tl5_Q5, tl5_Q6, tl5_Q7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_lifestyle);

        rd_tl5_Q1_yes=(RadioButton)findViewById(R.id.rd_DL_Q1_yes);
        rd_tl5_Q1_no=(RadioButton)findViewById(R.id.rd_DL_Q1_no);

        rd_tl5_Q2_yes=(RadioButton)findViewById(R.id.rd_DL_Q2_yes);
        rd_tl5_Q2_no=(RadioButton)findViewById(R.id.rd_DL_Q2_no);

        sp_tl5_Q3=(Spinner)findViewById(R.id.sp_DL_Q3);
        sp_tl5_Q4=(Spinner)findViewById(R.id.sp_DL_Q4);
        sp_tl5_Q5=(Spinner)findViewById(R.id.sp_DL_Q5);
        sp_tl5_Q6=(Spinner)findViewById(R.id.sp_DL_Q6);
        sp_tl5_Q7=(Spinner)findViewById(R.id.sp_DL_Q7);

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

            }
        });
    }

    private void checkData() {
        tl5_Q3 = Integer.parseInt(sp_tl5_Q3.getSelectedItem().toString());
        tl5_Q4 = Integer.parseInt(sp_tl5_Q4.getSelectedItem().toString());
        tl5_Q5 = Integer.parseInt(sp_tl5_Q5.getSelectedItem().toString());
        tl5_Q6 = Integer.parseInt(sp_tl5_Q6.getSelectedItem().toString());
        tl5_Q7 = Integer.parseInt(sp_tl5_Q7.getSelectedItem().toString());

        if(rd_tl5_Q1_yes.isChecked() == true){
            Toast.makeText(this, "1. High Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "1. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        if(rd_tl5_Q2_yes.isChecked() == true){
            Toast.makeText(this, "2. High Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "2. Normal Risk", Toast.LENGTH_SHORT).show();
        }

        if(tl5_Q3 < 5){
            Toast.makeText(this, "3. High Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "3. Normal Risk", Toast.LENGTH_SHORT).show();

        }
        if(tl5_Q4 < 3){
            Toast.makeText(this, "4. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "4. High Risk", Toast.LENGTH_SHORT).show();

        }
        if(tl5_Q5 < 3){
            Toast.makeText(this, "5. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "5. High Risk", Toast.LENGTH_SHORT).show();

        }
        if(tl5_Q6 < 3){
            Toast.makeText(this, "6. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "6. High Risk", Toast.LENGTH_SHORT).show();

        }
        if(tl5_Q7 > 2){
            Toast.makeText(this, "7. Normal Risk", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "7. High Risk", Toast.LENGTH_SHORT).show();

        }
    }
}
