package com.example.cv.select;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class syncTools extends AppCompatActivity {

    private List<syncedPatients> syncPatient;
    private DatabaseHelperRP mDatabaseHelper;
    private SyncedPatientsRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    String username, UserID;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_tools);

        mDatabaseHelper=new DatabaseHelperRP(this);

        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);

        try {

            if (username.equals("user1")) {
                UserID = "1";
            } else if (username.equals("user2")) {
                UserID = "2";
            } else if (username.equals("user3")) {
                UserID = "3";
            } else if (username.equals("user4")) {
                UserID = "4";
            } else if (username.equals("user5")) {
                UserID = "5";
            }
        }
        catch (Exception e){
           /* Toast.makeText(getActivity(), "Please Login Again", Toast.LENGTH_SHORT).show();
            SharedPreferences blockSession = getActivity().getSharedPreferences("loginref",MODE_PRIVATE);
            SharedPreferences.Editor blockEdit = blockSession.edit();
            blockEdit.putBoolean("savelogin", false);
            blockEdit.commit();*/

        }

        syncPatient=new ArrayList<syncedPatients>();
        syncPatient=mDatabaseHelper.syncPatients(UserID);
        recyclerView=(RecyclerView) findViewById(R.id.syncPatients);
        recyclerViewAdapter=new SyncedPatientsRecyclerViewAdapter(this,syncPatient);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        Log.d("abc", "onCreate: "+syncPatient);

    }
}
