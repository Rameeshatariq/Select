package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class InprogressParticipantsFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private InProgRecyclerViewAdapter recyclerViewAdapter;
    private SQLiteDatabase mDatabase;
    private List<CompParticipantsInfo> lstInCompParticipants;
    private DatabaseHelperRP mDatabaseHelper;
    SharedPreferences sharedPreferences;
    String username;
    String UserID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getActivity().getSharedPreferences("loginref",MODE_PRIVATE);
        username = pref.getString("username", null);

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


        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        v =inflater.inflate(R.layout.fragment_completed_participants,container,false);

        lstInCompParticipants=new ArrayList<CompParticipantsInfo>();
        mDatabaseHelper=new DatabaseHelperRP(getContext());
        lstInCompParticipants=mDatabaseHelper.getIncompPartidata(UserID);


        recyclerView=(RecyclerView) v.findViewById(R.id.rvcompparticipants);
        recyclerViewAdapter=new InProgRecyclerViewAdapter(getContext(),lstInCompParticipants);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;

    }



}
