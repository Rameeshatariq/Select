package com.example.cv.select;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class CompletedParticipantsFragment extends Fragment {
        View v;
        private RecyclerView recyclerView;
        private List<CompParticipantsInfo> lstCompParticipants;
        private RecyclerViewAdapter recyclerViewAdapter;
        private DatabaseHelperRP databaseHelperRP;
        String username, UserID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_completed_participants,container,false);

        lstCompParticipants=new ArrayList<CompParticipantsInfo>();
        databaseHelperRP=new DatabaseHelperRP(getContext());
        lstCompParticipants=databaseHelperRP.getCompPartidata(UserID);

        recyclerView=(RecyclerView) v.findViewById(R.id.rvcompparticipants);
        recyclerViewAdapter=new RecyclerViewAdapter(getContext(),lstCompParticipants);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

    }
