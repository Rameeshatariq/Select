package com.example.cv.select;

import android.content.Context;
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
    private List<CompParticipantsInfo> lstCompParticipants;
    private InProgRecyclerViewAdapter recyclerViewAdapter;


    public InprogressParticipantsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstCompParticipants = new ArrayList<>();
        lstCompParticipants.add(new CompParticipantsInfo("Rameesha", "03362451199"));
        lstCompParticipants.add(new CompParticipantsInfo("Maham", "03352431524"));
        lstCompParticipants.add(new CompParticipantsInfo("Maham", "0331724356"));
        lstCompParticipants.add(new CompParticipantsInfo("Nimra", "03348765434"));
        lstCompParticipants.add(new CompParticipantsInfo("Farzana", "03343125437"));
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_completed_participants,container,false);
        recyclerView=(RecyclerView) v.findViewById(R.id.rvcompparticipants);
        recyclerViewAdapter=new InProgRecyclerViewAdapter(getContext(),lstCompParticipants);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }


}