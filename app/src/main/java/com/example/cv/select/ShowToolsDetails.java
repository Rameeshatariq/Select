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
import java.util.List;

public class ShowToolsDetails extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private ListView tool1ListView;
    Button next;
    private  String ContactNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tools_details);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();


        mDatabaseHeperRP=new DatabaseHelperRP(this);
        tool1ListView=(ListView)findViewById(R.id.tool1_Data);
        next=(Button)findViewById(R.id.nextTool);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowToolsDetails.this, ShowTool1bData.class);
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });
        viewAllTool1Data();
    }

    private void viewAllTool1Data() {
            Cursor data=mDatabaseHeperRP.getPartiTool1Data(ContactNo);
            if(data.getCount() == 0){
                return;
            }

            ArrayList<String> listData= new ArrayList<>();
            while(data.moveToNext()){

                listData.add("ContactNo : "+ data.getString(0));
                listData.add("tool1_Q1 : "+ data.getString(1));
                listData.add("tool1_Q2 : "+ data.getString(2));
                listData.add("tool1_Q3 : "+ data.getString(3));
                listData.add("tool1_Q4 : "+ data.getString(4));
                listData.add("tool1_Q5 : "+ data.getString(5));
                listData.add("tool1_Q6 : "+ data.getString(6));
                listData.add("tool1_Q7 : "+ data.getString(7));
                listData.add("tool1_Q8 : "+ data.getString(8));

                ListAdapter adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
                tool1ListView.setAdapter(adapter);
            }
    }
}
