package com.example.cv.select;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowTool7Data extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private ListView tool7ListView;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool7_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);
        tool7ListView = (ListView) findViewById(R.id.tool7_data);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        viewAllTool7Data();
    }

    private void viewAllTool7Data() {
        Cursor data = mDatabaseHeperRP.getPartiTool7Data(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {

            listData.add("Contact : " + data.getString(0));
            listData.add("Tool7_Q1 : " + data.getString(1));
            listData.add("Tool7_Q2 : " + data.getString(2));
            listData.add("Tool7_Q3 : " + data.getString(3));
            listData.add("Tool7_Q4 : " + data.getString(4));
            listData.add("Tool7_Q5 : " + data.getString(5));
            listData.add("Tool7_Q6 : " + data.getString(6));
            listData.add("Tool7_Q7 : " + data.getString(7));


            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            tool7ListView.setAdapter(adapter);
        }
    }
}
