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

public class ShowTool1bData extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private ListView tool1bListView;
    private Button next;
    private String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool1b_data);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        mDatabaseHeperRP = new DatabaseHelperRP(this);
        tool1bListView = (ListView) findViewById(R.id.tool1b_data);
        next=(Button)findViewById(R.id.nextTool);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowTool1bData.this, ShowTool2Data.class );
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });
        viewAllTool1bData();
    }

    private void viewAllTool1bData() {
        Cursor data = mDatabaseHeperRP.getPartiTool2Data(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {

            listData.add("Contact : " + data.getString(0));
            listData.add("tool2_Q1 : " + data.getString(1));
            listData.add("tool2_Q2 : " + data.getString(2));
            listData.add("tool2_Q3 : " + data.getString(3));


            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            tool1bListView.setAdapter(adapter);
        }

    }
}
