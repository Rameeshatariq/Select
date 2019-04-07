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

public class ShowTool5Data extends AppCompatActivity {

    private DatabaseHelperRP mDatabaseHeperRP;
    private ListView tool5ListView;
    private TextView tv_tl5_contact, tv_tl5_Q1, tv_tl5_Q1_days, tv_tl5_Q2, tv_tl5_Q3, tv_tl5_Q3_days, tv_tl5_Q4, tv_tl5_Q5,tv_tl5_Q5_days, tv_tl5_Q6;
    Button next;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool5_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);

        tv_tl5_contact=(TextView)findViewById(R.id.pcontact);
        tv_tl5_Q1=(TextView)findViewById(R.id.tv_PA_Q1);
        tv_tl5_Q1_days=(TextView)findViewById(R.id.tv_PA_Q1_days);
        tv_tl5_Q2=(TextView)findViewById(R.id.tv_PA_Q2);
        tv_tl5_Q3=(TextView)findViewById(R.id.tv_PA_Q3);
        tv_tl5_Q3_days=(TextView)findViewById(R.id.tv_PA_Q3_days);
        tv_tl5_Q4=(TextView)findViewById(R.id.tv_PA_Q4);
        tv_tl5_Q5=(TextView)findViewById(R.id.tv_PA_Q5);
        tv_tl5_Q5_days=(TextView)findViewById(R.id.tv_PA_Q5_days);
        tv_tl5_Q6=(TextView)findViewById(R.id.tv_PA_Q6);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        next=(Button)findViewById(R.id.nextTool);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowTool5Data.this, ShowTool6aData.class );
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });
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
            tv_tl5_Q2.setText(data.getString(3));
            tv_tl5_Q3.setText(data.getString(4));
            tv_tl5_Q3_days.setText(data.getString(5));
            tv_tl5_Q4.setText(data.getString(6));
            tv_tl5_Q5.setText(data.getString(7));
            tv_tl5_Q5_days.setText(data.getString(8));
            tv_tl5_Q6.setText(data.getString(9));

        }

    }

}

