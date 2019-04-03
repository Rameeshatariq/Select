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

public class ShowTool5Data extends AppCompatActivity {

    private DatabaseHelperRP mDatabaseHeperRP;
    private ListView tool5ListView;
    Button next;
    String ContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tool5_data);

        mDatabaseHeperRP = new DatabaseHelperRP(this);
        tool5ListView = (ListView) findViewById(R.id.tool5_data);

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

        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {

            listData.add("Contact : " + data.getString(0));
            listData.add("VigorousExercise : " + data.getString(1));
            listData.add("VigorousExerciseDays : " + data.getString(2));
            listData.add("VigorousExerciseHoursMins : " + data.getString(3));
            listData.add("ModerateExercise : " + data.getString(4));
            listData.add("ModerateExerciseDays : " + data.getString(5));
            listData.add("ModerateExerciseHoursMins : " + data.getString(6));
            listData.add("Walk : " + data.getString(7));
            listData.add("WalkDays : " + data.getString(8));
            listData.add("WalkHoursMins : " + data.getString(9));

            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            tool5ListView.setAdapter(adapter);
        }

    }

}

