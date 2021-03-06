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

public class ShowTool7Data extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private Toolbar toolbar;
    private TextView tv_tl7_contact;
    EditText tv_tl7_Q1, tv_tl7_Q2,tv_tl7_Q3,tv_tl7_Q4,tv_tl7_Q5,tv_tl7_Q6,tv_tl7_Q7;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool7_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);

     //   tv_tl7_contact=(TextView)findViewById(R.id.pcontact);
        tv_tl7_Q1=(EditText)findViewById(R.id.tv_DL_Q1);
        tv_tl7_Q2=(EditText)findViewById(R.id.tv_DL_Q2);
        tv_tl7_Q3=(EditText)findViewById(R.id.tv_DL_Q3);
        tv_tl7_Q4=(EditText)findViewById(R.id.tv_DL_Q4);
        tv_tl7_Q5=(EditText)findViewById(R.id.tv_DL_Q5);
        tv_tl7_Q6=(EditText)findViewById(R.id.tv_DL_Q6);
        tv_tl7_Q7=(EditText)findViewById(R.id.tv_DL_Q7);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
     //   toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
     //   Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        viewAllTool7Data();
    }

    private void viewAllTool7Data() {
        Cursor data = mDatabaseHeperRP.getPartiTool7Data(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {

           // tv_tl7_contact.setText(data.getString(0));
            tv_tl7_Q1.setText(data.getString(1));
            tv_tl7_Q2.setText(data.getString(2));
            tv_tl7_Q3.setText(data.getString(3));
            tv_tl7_Q4.setText(data.getString(4));
            tv_tl7_Q5.setText(data.getString(5));
            tv_tl7_Q6.setText(data.getString(6));
            tv_tl7_Q7.setText(data.getString(7));

        }
    }
}
