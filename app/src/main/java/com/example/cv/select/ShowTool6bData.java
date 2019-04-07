package com.example.cv.select;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private TextView tv_tl6b_contact, tv_tl6b_Q1, tv_tl6b_Q2;
    Button next;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool6b_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);

        tv_tl6b_contact=(TextView)findViewById(R.id.pcontact);
        tv_tl6b_Q1=(TextView)findViewById(R.id.tv_TS_SL_Q1);
        tv_tl6b_Q2=(TextView)findViewById(R.id.tv_TS_SL_Q2);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        next=(Button)findViewById(R.id.nextTool);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowTool6bData.this, ShowTool7Data.class );
                intent.putExtra("ContactNo",ContactNo);
                startActivity(intent);
            }
        });
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

