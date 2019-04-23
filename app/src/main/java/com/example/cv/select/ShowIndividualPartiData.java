package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowIndividualPartiData extends AppCompatActivity {

    private DatabaseHelperRP mDatabaseHeperRP;
    Context ctx = ShowIndividualPartiData.this;
    Button btn_rgParti, btn_ptool1, btn_ptool2,btn_ptool3,btn_ptool4,btn_ptool5,btn_ptool6a,btn_ptool6b,btn_ptool7, btn_pDetailTool1,btn_pDetailTool2,
            btn_pDetailTool3,btn_pDetailTool4,btn_pDetailTool5,btn_pDetailTool6a,btn_pDetailTool6b,btn_pDetailTool7, btn_psummarydetail;
    private String ContactNo;
    private Toolbar toolbar;
    String tool1, Tool1, Tool2, Tool3, Tool7;
    LinearLayout linear_ptool1,linear_ptool2,linear_ptool3,linear_ptool4,linear_ptool5,linear_ptool6a,linear_ptool6b,linear_ptool7;
    private TextView tv_pname, tv_pdob, tv_page, tv_pgender, tv_pcontact, tv_paltSim, tv_paddress, tv_plives, tv_pnotmoving, tv_psmartphone, tv_pparticipate,
    tv_pinformedconsent, tv_pReason, tv_ptool1, tv_ptool2, tv_ptool3, tv_ptool4, tv_ptool5, tv_ptool6a, tv_ptool6b, tv_ptool7, tv_pEnroll, tv_pSyncData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_individual_parti_data);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Patient Detail");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDatabaseHeperRP = new DatabaseHelperRP(this);

        tv_pname=(TextView)findViewById(R.id.tv_pname);
        tv_pdob=(TextView)findViewById(R.id.tv_pdob);
        tv_page=(TextView)findViewById(R.id.tv_page);
        tv_pgender=(TextView)findViewById(R.id.tv_pgender);
        tv_pcontact=(TextView)findViewById(R.id.tv_pcontact);
        tv_paltSim=(TextView)findViewById(R.id.tv_p_altsim);
        tv_paddress=(TextView)findViewById(R.id.tv_paddress);
        tv_plives=(TextView)findViewById(R.id.tv_plives);
        tv_pnotmoving=(TextView)findViewById(R.id.tv_p_notmoving);
        tv_psmartphone=(TextView)findViewById(R.id.tv_psmartphone);
        tv_pparticipate=(TextView)findViewById(R.id.tv_participate);
        tv_pinformedconsent=(TextView)findViewById(R.id.tv_p_informedConsent);
        tv_pReason=(TextView)findViewById(R.id.tv_pReason);
        tv_ptool1=(TextView)findViewById(R.id.tv_ptool1);
        tv_ptool2=(TextView)findViewById(R.id.tv_ptool2);
        tv_ptool3=(TextView)findViewById(R.id.tv_ptool3);
        tv_ptool4=(TextView)findViewById(R.id.tv_ptool4);
        tv_ptool5=(TextView)findViewById(R.id.tv_ptool5);
        tv_ptool6a=(TextView)findViewById(R.id.tv_ptool6a);
        tv_ptool6b=(TextView)findViewById(R.id.tv_ptool6b);
        tv_ptool7=(TextView)findViewById(R.id.tv_ptool7);
        tv_pEnroll=(TextView)findViewById(R.id.tv_pEnroll);
        tv_pSyncData=(TextView)findViewById(R.id.tv_pSyncData);


        btn_pDetailTool1=(Button)findViewById(R.id.btn_ptool1detail);
        btn_pDetailTool2=(Button)findViewById(R.id.btn_ptool2detail);
        btn_pDetailTool3=(Button)findViewById(R.id.btn_ptool3detail);
        btn_pDetailTool4=(Button)findViewById(R.id.btn_ptool4detail);
        btn_pDetailTool5=(Button)findViewById(R.id.btn_ptool5detail);
        btn_pDetailTool6a=(Button)findViewById(R.id.btn_ptool6adetail);
        btn_pDetailTool6b=(Button)findViewById(R.id.btn_ptool6bdetail);
        btn_pDetailTool7=(Button)findViewById(R.id.btn_ptool7detail);
        btn_psummarydetail=(Button)findViewById(R.id.btn_psummarydetail);

        btn_rgParti=(Button)findViewById(R.id.rgParti);
        btn_rgParti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ShowIndividualPartiData.this, RegisterParticipant.class);
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });
        viewAllPatientData();

        linear_ptool1=(LinearLayout)findViewById(R.id.linear_Tools);

        if(tv_pEnroll.getText().toString().trim().equals("0")) {

            linear_ptool1.setVisibility(View.GONE);
        }


        btn_pDetailTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool1.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, RiskAndTriageCVA.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool1 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowToolsDetails.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });


        btn_pDetailTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool2.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, RiskAndTriageMI.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool2 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowTool1bData.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        btn_pDetailTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool3.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, RiskAndTriageDiabetic.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool3 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowTool2Data.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        btn_pDetailTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool4.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, IDRSModified.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool4 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowTool4Data.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        btn_pDetailTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool5.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, PhysicalActivity.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool5 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowTool5Data.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        btn_pDetailTool6a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool6a.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, TobbaccoSmoking.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool6a is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowTool6aData.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        btn_pDetailTool6b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool6b.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, TobbaccoSmokingSL.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool6b is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowTool6bData.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        btn_pDetailTool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool7.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, DietLifestyle.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool7 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowTool7Data.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        btn_psummarydetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, Summary.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
        });


       /* btn_toolDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool1.getText().toString()).equals("Incomplete")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, RiskAndTriageCVA.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool1 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
               else if ((tv_ptool2.getText().toString()).equals("Incomplete")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, RiskAndTriageMI.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool2 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
               else if ((tv_ptool3.getText().toString()).equals("Incomplete")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, RiskAndTriageDiabetic.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool3 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
                else if ((tv_ptool4.getText().toString()).equals("Incomplete")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, IDRSModified.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool4 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
                else if ((tv_ptool5.getText().toString()).equals("Incomplete")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, PhysicalActivity.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool5 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
                else if ((tv_ptool6a.getText().toString()).equals("Incomplete")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, TobbaccoSmoking.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool6a is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
                else if ((tv_ptool6b.getText().toString()).equals("Incomplete")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, TobbaccoSmokingSL.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool6b is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
                else if ((tv_ptool7.getText().toString()).equals("Incomplete")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, DietLifestyle.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool7 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowToolsDetails.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });*/
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


            tv_pname.setText(data.getString(0));
            tv_pdob.setText(data.getString(1));
            tv_page.setText(data.getString(2));
            tv_pgender.setText(data.getString(3));
            tv_pcontact.setText(data.getString(4));
            tv_paltSim.setText(data.getString(5));
            tv_paddress.setText(data.getString(6));
            tv_plives.setText(data.getString(7));
            tv_pnotmoving.setText(data.getString(8));
            tv_psmartphone.setText(data.getString(9));
            tv_pparticipate.setText(data.getString(10));
            tv_pinformedconsent.setText(data.getString(11));
            tv_pReason.setText(data.getString(12));
            tv_ptool1.setText(data.getString(13));
            tv_ptool2.setText(data.getString(14));
            tv_ptool3.setText(data.getString(15));
            tv_ptool4.setText(data.getString(16));
            tv_ptool5.setText(data.getString(17));
            tv_ptool6a.setText(data.getString(18));
            tv_ptool6b.setText(data.getString(19));
            tv_ptool7.setText(data.getString(20));
            tv_pEnroll.setText(data.getString(21));


        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        Log.d("000222", "onResume");
        viewAllPatientData();

        if ((tv_ptool1.getText().toString()).equals("0")) {
            btn_pDetailTool1.setText("Edit Tool 1");
        }
        else{
            btn_pDetailTool1.setText("Tool1 Detail");
        }
        if ((tv_ptool2.getText().toString()).equals("0")) {
            btn_pDetailTool2.setText("Edit Tool 2");
        }
        else{
            btn_pDetailTool2.setText("Tool2 Detail");
        }
        if ((tv_ptool3.getText().toString()).equals("0")) {
            btn_pDetailTool3.setText("Edit Tool 3");
        }
        else{
            btn_pDetailTool3.setText("Tool3 Detail");
        }
        if ((tv_ptool4.getText().toString()).equals("0")) {
            btn_pDetailTool4.setText("Edit Tool 4");
        }
        else{
            btn_pDetailTool4.setText("Tool4 Detail");
        }
        if ((tv_ptool5.getText().toString()).equals("0")) {
            btn_pDetailTool5.setText("Edit Tool 5");
        }
        else{
            btn_pDetailTool5.setText("Tool5 Detail");
        }
        if ((tv_ptool6a.getText().toString()).equals("0")) {
            btn_pDetailTool6a.setText("Edit Tool 6a");
        }
        else{
            btn_pDetailTool6a.setText("Tool6a Detail");
        }
        if ((tv_ptool6b.getText().toString()).equals("0")) {
            btn_pDetailTool6b.setText("Edit Tool 6b");
        }
        else{
            btn_pDetailTool6b.setText("Tool6b Detail");
        }
        if ((tv_ptool7.getText().toString()).equals("0")) {
            btn_pDetailTool7.setText("Edit Tool 7");
        }
        else{
            btn_pDetailTool7.setText("Tool7 Detail");
        }

    }
}
