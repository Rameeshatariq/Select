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
import android.widget.Toast;

import java.util.ArrayList;

public class ShowTool6bData extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private ListView tool6bListView;
    Button next;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool6b_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);
        tool6bListView = (ListView) findViewById(R.id.tool6b_data);

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

        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {

            listData.add("Contact : " + data.getString(0));
            listData.add("Tool6b_Q1 : " + data.getString(1));
            listData.add("Tool6b_Q2 : " + data.getString(2));

            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            tool6bListView.setAdapter(adapter);
        }

    }
    }

