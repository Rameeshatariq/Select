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

public class ShowTool1bData extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private TextView tv_tl2_contact, tv_tl2_Q1, tv_tl2_Q2, tv_tl2_Q3;
    private Button next;
    private Toolbar toolbar;
    private String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool1b_data);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        mDatabaseHeperRP = new DatabaseHelperRP(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tool 2 Details");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ShowTool1bData.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tv_tl2_contact=(TextView)findViewById(R.id.pcontact);
        tv_tl2_Q1=(TextView)findViewById(R.id.tv_rtMI_Q1);
        tv_tl2_Q2=(TextView)findViewById(R.id.tv_rtMI_Q2);
        tv_tl2_Q3=(TextView)findViewById(R.id.tv_rtMI_Q3);

        viewAllTool1bData();
    }

    private void viewAllTool1bData() {
        Cursor data = mDatabaseHeperRP.getPartiTool2Data(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {

            tv_tl2_contact.setText(data.getString(0));
            tv_tl2_Q1.setText(data.getString(1));
            tv_tl2_Q2.setText(data.getString(2));
            tv_tl2_Q3.setText(data.getString(3));
        }

    }
}
