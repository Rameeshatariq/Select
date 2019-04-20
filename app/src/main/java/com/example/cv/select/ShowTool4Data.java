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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShowTool4Data extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    TextView tv_tl4_contact, tv_tl4_Q1;
    Button next;
    private Toolbar toolbar;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool4_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);

        tv_tl4_contact=(TextView)findViewById(R.id.pcontact);
        tv_tl4_Q1=(TextView)findViewById(R.id.tv_IDRS_Q1);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tool 4 Details");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ShowTool4Data.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        viewAllTool4Data();
    }

    private void viewAllTool4Data() {
        Cursor data = mDatabaseHeperRP.getPartiTool4Data(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {

            tv_tl4_contact.setText(data.getString(0));
            tv_tl4_Q1.setText(data.getString(1));
        }

    }

    }

