package com.example.cv.select;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowTool5Data extends AppCompatActivity {

    private DatabaseHelperRP mDatabaseHeperRP;
    private Toolbar toolbar;
    private TextView tv_tl5_contact, tv_tl5_Q1, tv_tl5_Q1_days, tv_tl5_Q2_hours,tv_tl5_Q2_mins, tv_tl5_Q3, tv_tl5_Q3_days, tv_tl5_Q4_hours,tv_tl5_Q4_mins, tv_tl5_Q5,tv_tl5_Q5_days, tv_tl5_Q6_hours, tv_tl5_Q6_mins;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool5_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);

        tv_tl5_contact=(TextView)findViewById(R.id.pcontact);
        tv_tl5_Q1=(TextView)findViewById(R.id.tv_PA_Q1);
        tv_tl5_Q1_days=(TextView)findViewById(R.id.tv_PA_Q1_days);
        tv_tl5_Q2_hours=(TextView)findViewById(R.id.tv_PA_Q2_hours);
        tv_tl5_Q2_mins=(TextView)findViewById(R.id.tv_PA_Q2_mins);
        tv_tl5_Q3=(TextView)findViewById(R.id.tv_PA_Q3);
        tv_tl5_Q3_days=(TextView)findViewById(R.id.tv_PA_Q3_days);
        tv_tl5_Q4_hours=(TextView)findViewById(R.id.tv_PA_Q4_hours);
        tv_tl5_Q4_mins=(TextView)findViewById(R.id.tv_PA_Q4_mins);
        tv_tl5_Q5=(TextView)findViewById(R.id.tv_PA_Q5);
        tv_tl5_Q5_days=(TextView)findViewById(R.id.tv_PA_Q5_days);
        tv_tl5_Q6_hours=(TextView)findViewById(R.id.tv_PA_Q6_hours);
        tv_tl5_Q6_mins=(TextView)findViewById(R.id.tv_PA_Q6_mins);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tool 5 Details");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ShowTool5Data.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();
        viewAllTool5Data();
    }

    private void viewAllTool5Data() {
        Cursor data = mDatabaseHeperRP.getPartiTool5Data(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {

            tv_tl5_contact.setText(data.getString(0));
            tv_tl5_Q1.setText(data.getString(1));
            tv_tl5_Q1_days.setText(data.getString(2));
            tv_tl5_Q2_hours.setText(data.getString(3));
            tv_tl5_Q2_mins.setText(data.getString(4));
            tv_tl5_Q3.setText(data.getString(5));
            tv_tl5_Q3_days.setText(data.getString(6));
            tv_tl5_Q4_hours.setText(data.getString(7));
            tv_tl5_Q4_mins.setText(data.getString(8));
            tv_tl5_Q5.setText(data.getString(9));
            tv_tl5_Q5_days.setText(data.getString(10));
            tv_tl5_Q6_hours.setText(data.getString(11));
            tv_tl5_Q6_mins.setText(data.getString(12));

        }

    }

}

