package com.example.cv.select;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {
    AlertDialog alertDialog;
    AlertDialog.Builder dialogBuilder;
    View dialogView;
    Button search;
    EditText searchName;
    private List<CompParticipantsInfo> searchPatient;
    private DatabaseHelperRP mDatabaseHelper;
    private searchRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    String username, UserID;
    Context ctx = this;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mDatabaseHelper = new DatabaseHelperRP(this);

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

         //   Toast.makeText(ctx, "" + UserID, Toast.LENGTH_SHORT).show();


       /* try {

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
            }*/
        } catch (Exception e) {
           /* Toast.makeText(getActivity(), "Please Login Again", Toast.LENGTH_SHORT).show();
            SharedPreferences blockSession = getActivity().getSharedPreferences("loginref",MODE_PRIVATE);
            SharedPreferences.Editor blockEdit = blockSession.edit();
            blockEdit.putBoolean("savelogin", false);
            blockEdit.commit();*/

        }

        dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        dialogView = inflater.inflate(R.layout.search_dialog, null);
        search = (Button) dialogView.findViewById(R.id.nSearch);
        searchName = (EditText) dialogView.findViewById(R.id.nameSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

                String name = searchName.getText().toString();
                Log.d("abc", "onClick: "+name);
                searchPatient = new ArrayList<CompParticipantsInfo>();
                searchPatient = mDatabaseHelper.SearchIncompPartidata(UserID, name);
                recyclerView = (RecyclerView) findViewById(R.id.search);
                recyclerViewAdapter = new searchRecyclerViewAdapter(ctx, searchPatient);
                recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                recyclerView.setAdapter(recyclerViewAdapter);

                Log.d("abc", "onCreate: " + recyclerView);

            }


        });

        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.show();

    }
}
