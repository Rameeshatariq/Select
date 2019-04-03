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

public class ShowTool4Data extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private ListView tool4ListView;
    Button next;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool4_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);
        tool4ListView = (ListView) findViewById(R.id.tool4_data);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        next=(Button)findViewById(R.id.nextTool);
        next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(ShowTool4Data.this, ShowTool5Data.class );
            intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });
        viewAllTool4Data();
    }

    private void viewAllTool4Data() {
        Cursor data = mDatabaseHeperRP.getPartiTool4Data(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {

            listData.add("Contact : " + data.getString(0));
            listData.add("Tool4 Q1 : " + data.getString(1));



            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            tool4ListView.setAdapter(adapter);
        }

    }

    }

