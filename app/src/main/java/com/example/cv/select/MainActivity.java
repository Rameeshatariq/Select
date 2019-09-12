package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private FloatingActionButton floatingActionButton, floatingModulesBtn, floatingShowPatientDataBtn;
    boolean doubleBackToExitPressedOnce = false;
    Context ctx = this;
    String UserID;
    DatabaseHelperRP databaseHelperRP;
    SharedPreferences sharedPreferences;
    private int[] tabIcons = {
            R.drawable.inprogress2,
            R.drawable.completed1,
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_item1) {
          //  Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, syncTools.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.search) {
            //  Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, search.class);
            startActivity(i);
            return true;
        }
        if(id == R.id.logout){
            SharedPreferences blockSession = this.getSharedPreferences("loginref", MODE_PRIVATE);
            SharedPreferences.Editor blockEdit = blockSession.edit();
            blockEdit.putBoolean("savelogin", false);
            blockEdit.commit();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);;
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       sharedPreferences=getSharedPreferences("loginref",MODE_PRIVATE);
        String username=sharedPreferences.getString("username",null);



        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterParticipant.class);
                startActivity(intent);
            }
        });

     /* floatingModulesBtn = (FloatingActionButton) findViewById(R.id.floatbtn_modules);
        floatingModulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Modules.class);
                startActivity(intent);
            }
        });*/

   /*     floatingShowPatientDataBtn=(FloatingActionButton)findViewById(R.id.floatbtnShowPatientData);
        floatingShowPatientDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (MainActivity.this, ShowPatientData.class);
                startActivity(intent);
            }
        });*/

        SharedPreferences pref = getSharedPreferences("loginref",MODE_PRIVATE);
        username = pref.getString("username", null);

        databaseHelperRP=new DatabaseHelperRP(this);

        try {

            Cursor cursor = databaseHelperRP.getUserID(username);
            if (cursor.getCount() == 0) {
                return;
            }

            while (cursor.moveToNext()) {

                UserID = cursor.getString(0);
            }

        //    Toast.makeText(ctx, ""+UserID, Toast.LENGTH_SHORT).show();

      /*      if (username.equals("raheel.allana")) {
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
            Toast.makeText(MainActivity.this, "Please Login Again", Toast.LENGTH_SHORT).show();
            SharedPreferences blockSession = getSharedPreferences("loginref",MODE_PRIVATE);
            SharedPreferences.Editor blockEdit = blockSession.edit();
            blockEdit.putBoolean("savelogin", false);
            blockEdit.commit();

            Intent intent=new Intent(MainActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);;;
            startActivity(intent);

        }


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    protected void onResume() {
        super.onResume();


        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new InprogressParticipantsFragment(), "In-progress");
        adapter.AddFragment(new CompletedParticipantsFragment(), "Completed");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent= new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}

