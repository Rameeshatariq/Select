package com.example.cv.select;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShowSummary extends AppCompatActivity {
    String contact, tool1, tool2, tool3, tool7;
    private Toolbar toolbar;
    TextView s_contact, tv_s_tool1, tv_s_tool2, tv_s_tool3, tv_s_tool7;
    DatabaseHelperRP databaseHelperRP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_summary);

        Intent intent = getIntent();
        contact = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+contact, Toast.LENGTH_SHORT).show();

        databaseHelperRP = new DatabaseHelperRP(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Summary");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ShowSummary.this, MainActivity.class);
                startActivity(intent);
            }
        });

        s_contact=(TextView)findViewById(R.id.pcontact);
        tv_s_tool1=(TextView)findViewById(R.id.tool1);
        tv_s_tool2=(TextView)findViewById(R.id.tool2);
        tv_s_tool3=(TextView)findViewById(R.id.tool3);
        tv_s_tool7=(TextView)findViewById(R.id.tool7);

        viewSummary();

    }

    private void viewSummary() {
        Cursor data = databaseHelperRP.getPartiToolsSummary(contact);
        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {

            s_contact.setText(data.getString(0));
            tv_s_tool1.setText(data.getString(1));
            tv_s_tool2.setText(data.getString(2));
            tv_s_tool3.setText(data.getString(3));
            tv_s_tool7.setText(data.getString(4));

        }
        }
}
