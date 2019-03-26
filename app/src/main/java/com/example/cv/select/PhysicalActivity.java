package com.example.cv.select;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.rey.material.widget.CheckBox;

public class PhysicalActivity extends AppCompatActivity {
    private CheckBox ch_PA_Q1, ch_PA_Q3;
    private LinearLayout linear_PA_Q2, linear_PA_Q4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);

        ch_PA_Q1=(CheckBox)findViewById(R.id.ch_PA_Q1);
        ch_PA_Q3=(CheckBox)findViewById(R.id.ch_PA_Q3);

        linear_PA_Q2=(LinearLayout)findViewById(R.id.linear_PA_Q2);
        linear_PA_Q4=(LinearLayout)findViewById(R.id.linear_PA_Q4);

        ch_PA_Q1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ch_PA_Q1.isChecked()==true){
                    linear_PA_Q2.setVisibility(View.INVISIBLE);
                }
                else{
                    linear_PA_Q2.setVisibility(View.VISIBLE);
                }
            }
        });
        ch_PA_Q3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ch_PA_Q3.isChecked()==true){
                    linear_PA_Q4.setVisibility(View.INVISIBLE);
                }
                else{
                    linear_PA_Q4.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
