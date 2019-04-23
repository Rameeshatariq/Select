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

public class ShowTool6bData extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private Toolbar toolbar;
    private TextView tv_tl6b_contact, tv_tl6b_Q1, tv_tl6b_Q2;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool6b_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);

        tv_tl6b_contact=(TextView)findViewById(R.id.pcontact);
        tv_tl6b_Q1=(TextView)findViewById(R.id.tv_TS_SL_Q1);
        tv_tl6b_Q2=(TextView)findViewById(R.id.tv_TS_SL_Q2);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tool 6b Details");
        toolbar.setTitleTextColor(Color.WHITE);
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
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        viewAllTool6bData();
    }

    private void viewAllTool6bData() {
        Cursor data = mDatabaseHeperRP.getPartiTool6bData(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {

            tv_tl6b_contact.setText(data.getString(0));
            tv_tl6b_Q1.setText(data.getString(1));
            tv_tl6b_Q2.setText(data.getString(2));
        }

    }
    }

