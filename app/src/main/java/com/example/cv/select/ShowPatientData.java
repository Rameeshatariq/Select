package com.example.cv.select;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearSmoothScroller;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowPatientData extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private ListView listView;
    Button btn_ShowDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_patient_data);
        
        mDatabaseHeperRP=new DatabaseHelperRP(this);
        listView=(ListView)findViewById(R.id.patient_Data);
        btn_ShowDetails=(Button)findViewById(R.id.tools_details);

        btn_ShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent (ShowPatientData.this, ShowToolsDetails.class);
                startActivity(i);
            }
        });


        
        viewAllPatientData();

        
    }

    private void viewAllPatientData() {
        Cursor data=mDatabaseHeperRP.getData();
        if(data.getCount() == 0){
            return;
        }

        ArrayList<String> listData= new ArrayList<>();
        while(data.moveToNext()){

            listData.add("Name : "+ data.getString(0)+"\n");
            listData.add("Age : "+ data.getString(1)+"\n");
            listData.add("Gender : "+ data.getString(2)+"\n");
            listData.add("ContactSim : "+ data.getString(3)+"\n");
            listData.add("AlternateSim : "+ data.getString(4)+"\n");
            listData.add("Address : "+ data.getString(5)+"\n");
            listData.add("LivesInMalir : "+ data.getString(6)+"\n");
            listData.add("NotMovingFor6Months : "+ data.getString(7)+"\n");
            listData.add("SmartPhone : "+ data.getString(8)+"\n");
            listData.add("ParticipateFor6Months : "+ data.getString(9)+"\n");
            listData.add("InformedConsentTaken : "+ data.getString(10)+"\n");
            listData.add("Reason : "+ data.getString(11)+"\n");
            listData.add("Tool 1 : "+ data.getString(12)+"\n");
            listData.add("Tool 2 : "+ data.getString(13)+"\n");
            listData.add("Tool 3 : "+ data.getString(14)+"\n");
            listData.add("Tool 4 : "+ data.getString(15)+"\n");
            listData.add("Tool 5 : "+ data.getString(16)+"\n");
            listData.add("Tool 6a : "+ data.getString(17)+"\n");
            listData.add("Tool 6b : "+ data.getString(18)+"\n");
            listData.add("Tool 7 : "+ data.getString(19)+"\n");
            listData.add("Enroll : "+ data.getString(20)+"\n");
            listData.add("IsSync : "+ data.getString(21)+"\n");


            ListAdapter adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            listView.setAdapter(adapter);
        }

    }
}
