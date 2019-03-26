package com.example.cv.select;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (MainActivity.this, RegisterParticipant.class);
                startActivity(intent);
            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter= new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new CompletedParticipantsFragment(), "Completed Participants");
        adapter.AddFragment(new InprogressParticipantsFragment(), "Inprogress Participants");

        viewPager.setAdapter(adapter);

        tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }





}

