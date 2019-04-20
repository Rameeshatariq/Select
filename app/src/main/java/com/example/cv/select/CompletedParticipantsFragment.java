package com.example.cv.select;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CompletedParticipantsFragment extends Fragment {
        View v;
        private RecyclerView recyclerView;
        private List<CompParticipantsInfo> lstCompParticipants;
        private RecyclerViewAdapter recyclerViewAdapter;
        private DatabaseHelperRP databaseHelperRP;




    public CompletedParticipantsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_completed_participants,container,false);

        lstCompParticipants=new ArrayList<CompParticipantsInfo>();
        databaseHelperRP=new DatabaseHelperRP(getContext());
        lstCompParticipants=databaseHelperRP.getCompPartidata();

        recyclerView=(RecyclerView) v.findViewById(R.id.rvcompparticipants);
        recyclerViewAdapter=new RecyclerViewAdapter(getContext(),lstCompParticipants);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
