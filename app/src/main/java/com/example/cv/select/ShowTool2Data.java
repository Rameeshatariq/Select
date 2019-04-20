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

public class ShowTool2Data extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private Toolbar toolbar;
    private TextView tv_tl3_contact, tv_tl3_DM, tv_tl3_DM_med, tv_tl3_DM_insulin, tv_tl3_DM_diet, tv_tl3_DM_pnrMed, tv_tl3_DM_altMed, tv_tl3_Hyp, tv_tl3_Hyp_notMed,
            tv_tl3_Hyp_med, tv_tl3_Hyp_medOrDiet, tv_tl3_Hyp_pnrMed, tv_tl3_Hyp_altMed;
    Button next;
    private String ContactNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool2_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);

        tv_tl3_contact=(TextView)findViewById(R.id.pcontact);
        tv_tl3_DM=(TextView)findViewById(R.id.tv_rtD_Q1_DM);
        tv_tl3_DM_med=(TextView)findViewById(R.id.tv_rtD_Q1_DM_med);
        tv_tl3_DM_insulin=(TextView)findViewById(R.id.tv_rtD_Q1_DM_insulin);
        tv_tl3_DM_diet=(TextView)findViewById(R.id.tv_rtD_Q1_DM_diet);
        tv_tl3_DM_pnrMed=(TextView)findViewById(R.id.tv_rtD_Q1_DM_pnrmed);
        tv_tl3_DM_altMed=(TextView)findViewById(R.id.tv_rtD_Q1_DM_altmed);
        tv_tl3_Hyp=(TextView)findViewById(R.id.tv_rtD_Q1_hypertension);
        tv_tl3_Hyp_notMed=(TextView)findViewById(R.id.tv_rtD_Q1_ht_notmed);
        tv_tl3_Hyp_med=(TextView)findViewById(R.id.tv_rtD_Q1_ht_med);
        tv_tl3_Hyp_medOrDiet=(TextView)findViewById(R.id.tv_rtD_Q1_ht_dietmed);
        tv_tl3_Hyp_pnrMed=(TextView)findViewById(R.id.tv_rtD_Q1_ht_pnrmed);
        tv_tl3_Hyp_altMed=(TextView)findViewById(R.id.tv_rtD_Q1_ht_altmed);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tool 3 Details");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ShowTool2Data.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        viewAllTool3Data();
    }

    private void viewAllTool3Data() {
        Cursor data = mDatabaseHeperRP.getPartiTool3Data(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {

            tv_tl3_contact.setText(data.getString(0));
            tv_tl3_DM.setText(data.getString(1));
            tv_tl3_DM_med.setText(data.getString(2));
            tv_tl3_DM_insulin.setText(data.getString(3));
            tv_tl3_DM_diet.setText(data.getString(4));
            tv_tl3_DM_pnrMed.setText(data.getString(5));
            tv_tl3_DM_altMed.setText(data.getString(6));
            tv_tl3_Hyp.setText(data.getString(7));
            tv_tl3_Hyp_notMed.setText(data.getString(8));
            tv_tl3_Hyp_med.setText(data.getString(9));
            tv_tl3_Hyp_medOrDiet.setText(data.getString(10));
            tv_tl3_Hyp_pnrMed.setText(data.getString(11));
            tv_tl3_Hyp_altMed.setText(data.getString(12));

        }

    }
    }

