package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.cv.select.R.*;

public class Tools extends AppCompatActivity {


    private DatabaseHelperRP mDatabaseHeperRP;
    Context ctx = Tools.this;
    private String ContactNo;
    private Toolbar toolbar;
    String tool1, Tool1, Tool2, Tool3, Tool7;
    LinearLayout linear_ptool1, linear_reason, linear_ptool2, linear_ptool3, linear_ptool4, linear_ptool5, linear_ptool6a, linear_ptool6b, linear_ptool7;
    RelativeLayout relative_tool1, relative_tool2, relative_tool3, relative_tool4, relative_tool5, relative_tool6a, relative_tool6b, relative_tool7, relative_summary;
    Button rl;
    TextView tool1text, tool2text, tool3text, tool4text, tool5text, tool6atext, tool6btext, tool7text;
    ImageView tool1_icon, tool2_icon, tool3_icon, tool4_icon, tool5_icon, tool6a_icon, tool6b_icon, tool7_icon;
    private TextView tv_ptool1, tv_ptool2, tv_ptool3, tv_ptool4, tv_ptool5, tv_ptool6a, tv_ptool6b, tv_ptool7, tv_pEnroll, tv_pSyncData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_tools);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");

        toolbar = (Toolbar) findViewById(id.toolbar);
        toolbar.setTitle("");
        // toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDatabaseHeperRP = new DatabaseHelperRP(this);
        tv_ptool1=(TextView)findViewById(id.tv_ptool1);
        tv_ptool2=(TextView)findViewById(id.tv_ptool2);
        tv_ptool3=(TextView)findViewById(id.tv_ptool3);
        tv_ptool4=(TextView)findViewById(id.tv_ptool4);
        tv_ptool5=(TextView)findViewById(id.tv_ptool5);
        tv_ptool6a=(TextView)findViewById(id.tv_ptool6a);
        tv_ptool7=(TextView)findViewById(id.tv_ptool7);

        tool1_icon=(ImageView)findViewById(id.tool1icon);
        tool2_icon=(ImageView)findViewById(id.tool2icon);
        tool3_icon=(ImageView)findViewById(id.tool3icon);
        tool4_icon=(ImageView)findViewById(id.tool4icon);
        tool5_icon=(ImageView)findViewById(id.tool5icon);
        tool6a_icon=(ImageView)findViewById(id.tool6aicon);
        tool7_icon=(ImageView)findViewById(id.tool7icon);

        tool1text=(TextView)findViewById(id.tool1text);
        tool2text=(TextView)findViewById(id.tool2text);
        tool3text=(TextView)findViewById(id.tool3text);
        tool4text=(TextView)findViewById(id.tool4text);
        tool5text=(TextView)findViewById(id.tool5text);
        tool6atext=(TextView)findViewById(id.tool6atext);
        tool7text=(TextView)findViewById(id.tool7text);

        relative_tool1 = findViewById(id.relative_tool1);


        relative_tool2=(RelativeLayout)findViewById(id.relative_tool2);
        relative_tool3=(RelativeLayout)findViewById(id.relative_tool3);
        relative_tool4=(RelativeLayout)findViewById(id.relative_tool4);
        relative_tool5=(RelativeLayout)findViewById(id.relative_tool5);
        relative_tool6a=(RelativeLayout)findViewById(id.relative_tool6a);
        relative_tool7=(RelativeLayout)findViewById(id.relative_tool7);

       /* btn_pDetailTool1=(Button)findViewById(R.id.btn_ptool1detail);
        btn_pDetailTool2=(Button)findViewById(R.id.btn_ptool2detail);
        btn_pDetailTool3=(Button)findViewById(R.id.btn_ptool3detail);
        btn_pDetailTool4=(Button)findViewById(R.id.btn_ptool4detail);
        btn_pDetailTool5=(Button)findViewById(R.id.btn_ptool5detail);
        btn_pDetailTool6a=(Button)findViewById(R.id.btn_ptool6adetail);
        btn_pDetailTool6b=(Button)findViewById(R.id.btn_ptool6bdetail);
        btn_pDetailTool7=(Button)findViewById(R.id.btn_ptool7detail);
        btn_psummarydetail=(Button)findViewById(R.id.btn_psummarydetail);*/


       Log.d("000000", "oncreate");
        viewAllPatientData();

        relative_summary=(RelativeLayout) findViewById(id.relative_summary);
        relative_summary.setVisibility(View.GONE);

        relative_tool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool1.getText().toString()).equals("0") || (tv_ptool1.getText().toString()).equals("2")) {
                    //    tool1_icon.setImageDrawable(res2);
                    Intent intent = new Intent(Tools.this, RiskAndTriageCVA.class);
                //    Toast.makeText(Tools.this, "Tool1 is not Completed", Tow -*/////ast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    //    tool1_icon.setImageDrawable(res);
                    Intent intent = new Intent(Tools.this, ShowToolsDetails.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        relative_tool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool2.getText().toString()).equals("0") || (tv_ptool2.getText().toString()).equals("2")) {
                    //     tool2_icon.setImageDrawable(res2);
                    Intent intent = new Intent(Tools.this, RiskAndTriageMI.class);
                  //  Toast.makeText(Tools.this, "Tool2 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    //       tool2_icon.setImageDrawable(res);
                    Intent intent = new Intent(Tools.this, ShowTool1bData.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        relative_tool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool3.getText().toString()).equals("0") || (tv_ptool3.getText().toString()).equals("2")) {
                    //     tool3_icon.setImageDrawable(res2);
                    Intent intent = new Intent(Tools.this, RiskAndTriageDiabetic.class);
                //    Toast.makeText(Tools.this, "Tool3 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    //   tool3_icon.setImageDrawable(res);
                    Intent intent = new Intent(Tools.this, ShowTool2Data.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        relative_tool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool4.getText().toString()).equals("0") || (tv_ptool4.getText().toString()).equals("2")) {
                    //    tool4_icon.setImageDrawable(res2);
                    Intent intent = new Intent(Tools.this, IDRSModified.class);
              //      Toast.makeText(Tools.this, "Tool4 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    //    tool4_icon.setImageDrawable(res);
                    Intent intent = new Intent(Tools.this, ShowTool4Data.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        relative_tool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   tool5_icon.setImageDrawable(res2);
                if ((tv_ptool5.getText().toString()).equals("0") || (tv_ptool5.getText().toString()).equals("2")) {
                    Intent intent = new Intent(Tools.this, PhysicalActivity.class);
                 //   Toast.makeText(Tools.this, "Tool5 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    //     tool5_icon.setImageDrawable(res);
                    Intent intent = new Intent(Tools.this, ShowTool5Data.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        relative_tool6a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool6a.getText().toString()).equals("0") || (tv_ptool6a.getText().toString()).equals("2")) {
                    //   tool6a_icon.setImageDrawable(res2);
                    Intent intent = new Intent(Tools.this, TobbaccoSmoking.class);
                 //   Toast.makeText(Tools.this, "Tool6a is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    // tool6a_icon.setImageDrawable(res);
                    Intent intent = new Intent(Tools.this, ShowTool6aData.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });
/*
        relative_tool6b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool6b.getText().toString()).equals("0") || (tv_ptool6b.getText().toString()).equals("")) {
                    // tool6b_icon.setImageDrawable(res2);
                    Intent intent = new Intent(Tools.this, TobbaccoSmokingSL.class);
                //    Toast.makeText(Tools.this, "Tool6b is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    // tool6b_icon.setImageDrawable(res);
                    Intent intent = new Intent(Tools.this, ShowTool6bData.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });*/

        relative_tool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tool7_icon.setImageDrawable(res2);
                if ((tv_ptool7.getText().toString()).equals("0") || (tv_ptool7.getText().toString()).equals("2")) {
                    Intent intent = new Intent(Tools.this, DietLifestyle.class);
                   // Toast.makeText(Tools.this, "Tool7 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    //  tool7_icon.setImageDrawable(res);
                    Intent intent = new Intent(Tools.this, ShowTool7Data.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        relative_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tools.this, Summary.class);
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });
        Log.d("00000011", "oncreate000");

    }

    private void viewAllPatientData() {

        Log.d("000222", "viewAllPatientData");





        Cursor data = mDatabaseHeperRP.getPartiData(ContactNo);

        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {

            Log.d("000222", "viewAllPatientData   "+data.getString(0));
            Log.d("000222", "viewAllPatientData   "+data.getString(1));
            Log.d("000222", "viewAllPatientData   "+data.getString(2));
            Log.d("000222", "viewAllPatientData   "+data.getString(3));
            Log.d("000222", "viewAllPatientData   "+data.getString(4));
            Log.d("000222", "viewAllPatientData   "+data.getString(5));
            Log.d("000222", "viewAllPatientData   "+data.getString(6));
            Log.d("000222", "viewAllPatientData   "+data.getString(7));
            Log.d("000222", "viewAllPatientData   "+data.getString(8));
            Log.d("000222", "viewAllPatientData   "+data.getString(9));
            Log.d("000222", "viewAllPatientData   "+data.getString(10));
            Log.d("000222", "viewAllPatientData   "+data.getString(11));

            tv_ptool1.setText(data.getString(16));
            tv_ptool2.setText(data.getString(17));
            tv_ptool3.setText(data.getString(18));
            tv_ptool4.setText(data.getString(19));
            tv_ptool5.setText(data.getString(20));
            tv_ptool6a.setText(data.getString(21));
            tv_ptool7.setText(data.getString(22));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        Log.d("000222", "onResume");
        viewAllPatientData();

        String incomp = "@drawable/inprocess";  // where myresource (without the extension) is the file
        int imageResource2 = getResources().getIdentifier(incomp, null, getPackageName());
        final Drawable res2 = getResources().getDrawable(imageResource2);

        String comp = "@drawable/completed";  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(comp, null, getPackageName());
        final Drawable res= getResources().getDrawable(imageResource);

        final Drawable white = getResources().getDrawable(drawable.input_design);
        final Drawable green = getResources().getDrawable(drawable.background_green);
        final Drawable red = getResources().getDrawable(drawable.background);

        tv_ptool1.setVisibility(View.INVISIBLE);
        tv_ptool2.setVisibility(View.INVISIBLE);
        tv_ptool3.setVisibility(View.INVISIBLE);
        tv_ptool4.setVisibility(View.INVISIBLE);
        tv_ptool5.setVisibility(View.INVISIBLE);
        tv_ptool6a.setVisibility(View.INVISIBLE);
        tv_ptool7.setVisibility(View.INVISIBLE);


        // Now get a handle to any View contained
        // within the main layout you are using

        // Find the root view


        if ((tv_ptool1.getText().toString()).equals("0")) {
          //  btn_pDetailTool1.setText("Edit");
           // relative_tool1.setBackgroundDrawable(white);
        }
        else if((tv_ptool1.getText().toString()).equals("1")){
            tool1_icon.setImageDrawable(res);
        //    relative_tool1.setBackgroundDrawable(green);
          //  tool1text.setTextColor(Color.WHITE);
        }
        else {
            tool1_icon.setImageDrawable(res2);
            //relative_tool1.setBackgroundDrawable(red);
            //tool1text.setTextColor(Color.WHITE);
        }

        if ((tv_ptool2.getText().toString()).equals("0")) {
            //tool2_icon.setImageDrawable(res2);
            //btn_pDetailTool2.setText("Edit");
           // relative_tool2.setBackgroundDrawable(white);
        }
        else if((tv_ptool2.getText().toString()).equals("1")){
            tool2_icon.setImageDrawable(res);
            //relative_tool2.setBackgroundDrawable(green);
            //tool2text.setTextColor(Color.WHITE);
        }
        else{
            //btn_pDetailTool2.setText("Detail");
            tool2_icon.setImageDrawable(res2);
            //relative_tool2.setBackgroundDrawable(red);
            //tool2text.setTextColor(Color.WHITE);
        }

        if ((tv_ptool3.getText().toString()).equals("0")) {
            //tool3_icon.setImageDrawable(res2);
            //btn_pDetailTool3.setText("Edit");
            //relative_tool3.setBackgroundDrawable(white);
        }
        else if((tv_ptool3.getText().toString()).equals("1")){
            //relative_tool3.setBackgroundDrawable(green);
            tool3_icon.setImageDrawable(res);
            //tool3text.setTextColor(Color.WHITE);
        }
        else{
            //btn_pDetailTool3.setText("Detail");
            tool3_icon.setImageDrawable(res2);
            //relative_tool3.setBackgroundDrawable(red);
           // tool3text.setTextColor(Color.WHITE);
        }

        if ((tv_ptool4.getText().toString()).equals("0")) {
            //tool4_icon.setImageDrawable(res2);
            //btn_pDetailTool4.setText("Edit");
         //   relative_tool4.setBackgroundDrawable(white);
        }
        else if((tv_ptool4.getText().toString()).equals("1")){
            // relative_tool4.setBackgroundDrawable(green);
            //tool4text.setTextColor(Color.WHITE);
            tool4_icon.setImageDrawable(res);
        }
        else{
            //btn_pDetailTool4.setText("Detail");
            tool4_icon.setImageDrawable(res2);
            //relative_tool4.setBackgroundDrawable(red);
            //tool4text.setTextColor(Color.WHITE);
        }
        if ((tv_ptool5.getText().toString()).equals("0")) {
            //tool5_icon.setImageDrawable(res2);
            //btn_pDetailTool5.setText("Edit");
            //relative_tool5.setBackgroundDrawable(white);
        }
        else if((tv_ptool5.getText().toString()).equals("1")){
           //relative_tool5.setBackgroundDrawable(green);
            //tool5text.setTextColor(Color.WHITE);
            tool5_icon.setImageDrawable(res);
        }
        else{
            //btn_pDetailTool5.setText("Detail");
            tool5_icon.setImageDrawable(res2);
            //relative_tool5.setBackgroundDrawable(red);
            //tool5text.setTextColor(Color.WHITE);
        }
        if ((tv_ptool6a.getText().toString()).equals("0")) {
            //tool6a_icon.setImageDrawable(res2);
            //btn_pDetailTool6a.setText("Edit");
            //relative_tool6a.setBackgroundDrawable(white);
        }
        else if((tv_ptool6a.getText().toString()).equals("1")){
            //relative_tool6a.setBackgroundDrawable(green);
            //tool6atext.setTextColor(Color.WHITE);
            tool6a_icon.setImageDrawable(res);
        }
        else{
            //btn_pDetailTool6a.setText("Detail");
            tool6a_icon.setImageDrawable(res2);
            //relative_tool6a.setBackgroundDrawable(red);
            //tool6atext.setTextColor(Color.WHITE);
        }
       /* if ((tv_ptool6b.getText().toString()).equals("0")) {
            //tool6b_icon.setImageDrawable(res2);
            //btn_pDetailTool6b.setText("Edit");
            //relative_tool6b.setBackgroundDrawable(white);
        }
        else if((tv_ptool6b.getText().toString()).equals("1")){
            //  relative_tool6b.setBackgroundDrawable(green);
        //    tool6btext.setTextColor(Color.WHITE);
            tool6b_icon.setImageDrawable(res);
        }
        else{
            //btn_pDetailTool6b.setText("Detail");
            tool6b_icon.setImageDrawable(res2);
            //relative_tool6b.setBackgroundDrawable(red);
            //tool6btext.setTextColor(Color.WHITE);
        }*/
        if ((tv_ptool7.getText().toString()).equals("0")) {
            //tool7_icon.setImageDrawable(res2);
            //btn_pDetailTool7.setText("Edit");
        //    relative_tool7.setBackgroundDrawable(white);
        }
        else if((tv_ptool7.getText().toString()).equals("1")){
            //relative_tool7.setBackgroundDrawable(green);
          ///  tool7text.setTextColor(Color.WHITE);
            tool7_icon.setImageDrawable(res);
        }
        else{
            //btn_pDetailTool7.setText("Detail");
            tool7_icon.setImageDrawable(res2);
            //relative_tool7.setBackgroundDrawable(red);
            //tool7text.setTextColor(Color.WHITE);
        }
        if((tv_ptool1.getText().toString()).equals("1") && (tv_ptool2.getText().toString()).equals("1") && (tv_ptool3.getText().toString()).equals("1")
                && (tv_ptool4.getText().toString()).equals("1") && (tv_ptool5.getText().toString()).equals("1") && (tv_ptool6a.getText().toString()).equals("1")
                && (tv_ptool7.getText().toString()).equals("1")){
            relative_summary.setVisibility(View.VISIBLE);
        }


    }
}