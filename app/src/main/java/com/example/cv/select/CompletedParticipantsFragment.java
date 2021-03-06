package com.example.cv.select;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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


            databaseHelperRP=new DatabaseHelperRP(getContext());

            try {

                Cursor cursor = databaseHelperRP.getUserID(username);
                if (cursor.getCount() == 0) {
                    return;
                }

                while (cursor.moveToNext()) {

                    UserID = cursor.getString(0);
                }

            //    Toast.makeText(getContext(), ""+UserID, Toast.LENGTH_SHORT).show();

        /*    if (username.equals("raheel.allana")) {
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
