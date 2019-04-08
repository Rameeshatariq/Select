package com.example.cv.select;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Summary extends AppCompatActivity {
    String contact, tool1, tool2, tool3, tool7;
    private Toolbar toolbar;
    TextView s_contact, tv_s_tool1, tv_s_tool2, tv_s_tool3, tv_s_tool7;
    DatabaseHelperRP databaseHelperRP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        databaseHelperRP=new DatabaseHelperRP(this);

        s_contact=(TextView)findViewById(R.id.pcontact);
        tv_s_tool1=(TextView)findViewById(R.id.tool1);
        tv_s_tool2=(TextView)findViewById(R.id.tool2);
        tv_s_tool3=(TextView)findViewById(R.id.tool3);
        tv_s_tool7=(TextView)findViewById(R.id.tool7);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Summary");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Summary.this, Modules.class);
                startActivity(intent);
            }
        });



        Intent intent=getIntent();
        contact=intent.getStringExtra("ContactNo");
        tool1=intent.getStringExtra("tool1");
        tool2=intent.getStringExtra("tool2");
        tool3=intent.getStringExtra("tool3");
        tool7=intent.getStringExtra("tool7");

        s_contact.setText(contact);
        tv_s_tool1.setText(tool1);
        tv_s_tool2.setText(tool2);
        tv_s_tool3.setText(tool3);
        tv_s_tool7.setText(tool7);

        addToolsSummary();
    }

    private void addToolsSummary() {
        boolean isInserted = databaseHelperRP.addToolsSummary(contact, tool1, tool2, tool3,tool7);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }

    }
}
