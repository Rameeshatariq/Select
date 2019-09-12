package com.example.cv.select;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class syncTools extends AppCompatActivity {

    public static List<syncedPatients> syncPatient;
    public static DatabaseHelperRP mDatabaseHelper;
    public static SyncedPatientsRecyclerViewAdapter recyclerViewAdapter;
    public static RecyclerView recyclerView;
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

            Cursor cursor = mDatabaseHelper.getUserID(username);
            if (cursor.getCount() == 0) {
                return;
            }

            while (cursor.moveToNext()) {

                UserID = cursor.getString(0);
            }

           // Toast.makeText(syncTools.this, "" + UserID, Toast.LENGTH_SHORT).show();
        }

     /*   try {

            if (username.equals("raheel.allana")) {
                UserID = "4";
            } else if (username.equals("zainab.kazim")) {
                UserID = "5";
            } else if (username.equals("maheen.fazal")) {
                UserID = "6";
            } else if (username.equals("sehar.gillani")) {
                UserID = "7";
            } else if (username.equals("gulnayab.khan")) {
                UserID = "8";
            }else if (username.equals("sultan.nasim")) {
                UserID = "9";
            }else if (username.equals("hina.khan")) {
                UserID = "10";
            }else if (username.equals("nadia.mushtaq")) {
                UserID = "11";
            }else if (username.equals("user.one")) {
                UserID = "12";
            }else if (username.equals("user.two")) {
                UserID = "13";
            }
        }*/
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
        recyclerViewAdapter=new SyncedPatientsRecyclerViewAdapter(this,syncPatient,UserID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        Log.d("abc", "onCreate: "+syncPatient);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void update(View view){
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
