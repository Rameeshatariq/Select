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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowToolsDetails extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private Toolbar toolbar;
    private EditText et_tl1_Q1, et_tl1_Q2, et_tl1_Q3, et_tl1_Q4, et_tl1_Q5, et_tl1_Q6, et_tl1_Q7, et_tl1_Q8;
    private TextView tv_tl1_contact, tv_tl1_Q1, tv_tl1_Q2, tv_tl1_Q3, tv_tl1_Q4, tv_tl1_Q5,tv_tl1_Q6, tv_tl1_Q7,tv_tl1_Q8;
    Button next;
    private  String ContactNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tools_details);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
      //  Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        mDatabaseHeperRP=new DatabaseHelperRP(this);

     //   tv_tl1_contact=(TextView)findViewById(R.id.pcontact);
        et_tl1_Q1=(EditText) findViewById(R.id.tv_rt_Q1);
        et_tl1_Q2=(EditText) findViewById(R.id.tv_rt_Q2);
        et_tl1_Q3=(EditText) findViewById(R.id.tv_rt_Q3);
        et_tl1_Q4=(EditText)findViewById(R.id.tv_rt_Q4);
        et_tl1_Q5=(EditText)findViewById(R.id.tv_rt_Q5);
        et_tl1_Q6=(EditText)findViewById(R.id.tv_rt_Q6);
        et_tl1_Q7=(EditText)findViewById(R.id.tv_rt_Q7);
        et_tl1_Q8=(EditText)findViewById(R.id.tv_rt_Q8);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        //toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        viewAllTool1Data();
    }

    private void viewAllTool1Data() {
            Cursor data=mDatabaseHeperRP.getPartiTool1Data(ContactNo);
            if(data.getCount() == 0){
                return;
            }

            while(data.moveToNext()){
           //     tv_tl1_contact.setText(data.getString(0));
                et_tl1_Q1.setText(data.getString(1));
                et_tl1_Q2.setText(data.getString(2));
                // tv_tl1_Q2.setText(data.getString(2));
                et_tl1_Q3.setText(data.getString(3));
                et_tl1_Q4.setText(data.getString(4));
                et_tl1_Q5.setText(data.getString(5));
                et_tl1_Q6.setText(data.getString(6));
                et_tl1_Q7.setText(data.getString(7));
                et_tl1_Q8.setText(data.getString(8));

            }
    }
}
