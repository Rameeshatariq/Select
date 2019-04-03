package com.example.cv.select;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class InprogressParticipantsFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private InProgRecyclerViewAdapter recyclerViewAdapter;
    private SQLiteDatabase mDatabase;
    private DatabaseHelperRP mDatabaseHelper;


    public InprogressParticipantsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mDatabaseHelper=new DatabaseHelperRP(getActivity());
        mDatabase=mDatabaseHelper.getWritableDatabase();

        v =inflater.inflate(R.layout.fragment_completed_participants,container,false);
        recyclerView=(RecyclerView) v.findViewById(R.id.rvcompparticipants);
        recyclerViewAdapter=new InProgRecyclerViewAdapter(getContext(),getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;

    }

    private Cursor getAll(){

        return mDatabase.query(
                InprogParticipantsInfo.participantsInfo.TABLE_NAME,
                null, null, null, null,null, InprogParticipantsInfo.participantsInfo.COLUMN_Contact);
    }


}
