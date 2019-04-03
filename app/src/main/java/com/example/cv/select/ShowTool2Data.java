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

public class ShowTool2Data extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHeperRP;
    private ListView tool3ListView;
    Button next;
    private String ContactNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool2_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);
        tool3ListView = (ListView) findViewById(R.id.tool3_data);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

       next=(Button)findViewById(R.id.nextTool);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowTool2Data.this, ShowTool4Data.class );
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });
        viewAllTool3Data();
    }

    private void viewAllTool3Data() {
        Cursor data = mDatabaseHeperRP.getPartiTool3Data(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {

            listData.add("Contact : " + data.getString(0));
            listData.add("Diabetic : " + data.getString(1));
            listData.add("DiabeticControlByMed : " + data.getString(2));
            listData.add("DiabeticControlByInsulin : " + data.getString(3));
            listData.add("DiabeticControlByDiet : " + data.getString(4));
            listData.add("DiabeticControlByPnrMed : " + data.getString(5));
            listData.add("DiabeticControlByAltMed : " + data.getString(6));
            listData.add("Hypertension : " + data.getString(7));
            listData.add("HypertensionControlByNotMed : " + data.getString(8));
            listData.add("HypertensionControlByMed : " + data.getString(9));
            listData.add("HypertensionControlByDiet/Med : " + data.getString(10));
            listData.add("HypertensionControlByPnrMed : " + data.getString(11));
            listData.add("HypertensionControlByAltMed : " + data.getString(12));



            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            tool3ListView.setAdapter(adapter);
        }

    }
    }

