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
    private List<CompParticipantsInfo> lstInCompParticipants;
    private DatabaseHelperRP mDatabaseHelper;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        v =inflater.inflate(R.layout.fragment_completed_participants,container,false);

        lstInCompParticipants=new ArrayList<CompParticipantsInfo>();
        mDatabaseHelper=new DatabaseHelperRP(getContext());
        lstInCompParticipants=mDatabaseHelper.getIncompPartidata();

        recyclerView=(RecyclerView) v.findViewById(R.id.rvcompparticipants);
        recyclerViewAdapter=new InProgRecyclerViewAdapter(getContext(),lstInCompParticipants);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;

    }



}
