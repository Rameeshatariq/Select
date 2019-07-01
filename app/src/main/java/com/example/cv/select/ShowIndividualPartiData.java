package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.cv.select.R.drawable.*;

public class ShowIndividualPartiData extends AppCompatActivity {

    private DatabaseHelperRP mDatabaseHeperRP;
    Context ctx = ShowIndividualPartiData.this;
    Button btn_rgParti, btn_ptool1, btn_ptool2,btn_ptool3,btn_ptool4,btn_ptool5,btn_ptool6a,btn_ptool6b,btn_ptool7, btn_pDetailTool1,btn_pDetailTool2,
            btn_pDetailTool3,btn_pDetailTool4,btn_pDetailTool5,btn_pDetailTool6a,btn_pDetailTool6b,btn_pDetailTool7, btn_psummarydetail;
    private String ContactNo;
    private Toolbar toolbar;
    String tool1, Tool1, Tool2, Tool3, Tool7;
    LinearLayout linear_ptool1, linear_reason, linear_ptool2,linear_ptool3,linear_ptool4,linear_ptool5,linear_ptool6a,linear_ptool6b,linear_ptool7;
    RelativeLayout relative_summary;
    ImageView tool1_icon, tool2_icon, tool3_icon, tool4_icon, tool5_icon, tool6a_icon, tool6b_icon, tool7_icon;
    private View reasonView;
    private EditText tv_UserID, tv_pname, tv_pdob, tv_page, tv_pgender, tv_pcontact, tv_paltSim, tv_paddress;
    private TextView tv_plives, tv_pnotmoving, tv_psmartphone, tv_pparticipate,
    tv_pinformedconsent, tv_pRespondedIVR, tv_pRespondedSMS, tv_pReason, tv_ptool1, tv_ptool2, tv_ptool3, tv_ptool4, tv_ptool5, tv_ptool6a, tv_ptool6b, tv_ptool7, tv_pEnroll, tv_pSyncData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_individual_parti_data);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

      //  tv_UserID=(EditText) findViewById(R.id.ed_UserId);
        tv_pname=(EditText) findViewById(R.id.ed_name);
        tv_pdob=(EditText) findViewById(R.id.ed_dateOfBirth);
        tv_page=(EditText) findViewById(R.id.ed_age);
        tv_pgender=(EditText) findViewById(R.id.ed_Gender);
        tv_pcontact=(EditText) findViewById(R.id.ed_contact);
        tv_paltSim=(EditText) findViewById(R.id.ed_altsim);
        tv_paddress=(EditText) findViewById(R.id.ed_address);

        tv_plives=(TextView)findViewById(R.id.tv_plives);
        tv_pnotmoving=(TextView)findViewById(R.id.tv_p_notmoving);
        tv_psmartphone=(TextView)findViewById(R.id.tv_psmartphone);
        tv_pparticipate=(TextView)findViewById(R.id.tv_participate);
        tv_pinformedconsent=(TextView)findViewById(R.id.tv_p_informedConsent);
        tv_pRespondedIVR=(TextView)findViewById(R.id.tv_pRespondedIVR);
        tv_pRespondedSMS=(TextView)findViewById(R.id.tv_pRespondedSMS);
        tv_pReason=(TextView)findViewById(R.id.tv_pReason);

        reasonView=(View)findViewById(R.id.viewReason);
       /* tv_ptool1=(TextView)findViewById(R.id.tv_ptool1);
        tv_ptool2=(TextView)findViewById(R.id.tv_ptool2);
        tv_ptool3=(TextView)findViewById(R.id.tv_ptool3);
        tv_ptool4=(TextView)findViewById(R.id.tv_ptool4);
        tv_ptool5=(TextView)findViewById(R.id.tv_ptool5);
        tv_ptool6a=(TextView)findViewById(R.id.tv_ptool6a);
        tv_ptool6b=(TextView)findViewById(R.id.tv_ptool6b);
        tv_ptool7=(TextView)findViewById(R.id.tv_ptool7);*/
       //tv_pEnroll=(TextView)findViewById(R.id.tv_pEnroll);
        //tv_pSyncData=(TextView)findViewById(R.id.tv_pSyncData);


        /*btn_pDetailTool1=(Button)findViewById(R.id.btn_ptool1detail);
        btn_pDetailTool2=(Button)findViewById(R.id.btn_ptool2detail);
        btn_pDetailTool3=(Button)findViewById(R.id.btn_ptool3detail);
        btn_pDetailTool4=(Button)findViewById(R.id.btn_ptool4detail);
        btn_pDetailTool5=(Button)findViewById(R.id.btn_ptool5detail);
        btn_pDetailTool6a=(Button)findViewById(R.id.btn_ptool6adetail);
        btn_pDetailTool6b=(Button)findViewById(R.id.btn_ptool6bdetail);
        btn_pDetailTool7=(Button)findViewById(R.id.btn_ptool7detail);
        btn_psummarydetail=(Button)findViewById(R.id.btn_psummarydetail);*/

       /* tool1_icon=(ImageView)findViewById(R.id.tool1icon);
        tool2_icon=(ImageView)findViewById(R.id.tool2icon);
        tool3_icon=(ImageView)findViewById(R.id.tool3icon);
        tool4_icon=(ImageView)findViewById(R.id.tool4icon);
        tool5_icon=(ImageView)findViewById(R.id.tool5icon);
        tool6a_icon=(ImageView)findViewById(R.id.tool6aicon);
        tool6b_icon=(ImageView)findViewById(R.id.tool6bicon);
        tool7_icon=(ImageView)findViewById(R.id.tool7icon);*/

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
        linear_reason=(LinearLayout)findViewById(R.id.linear_p_reason);
       // reasonView=(View)findViewById(R.id.reason_view);
        if(tv_pReason.getText().toString().equals("0")){
            linear_reason.setVisibility(View.GONE);
            reasonView.setVisibility(View.GONE);
        }

       // linear_ptool1=(LinearLayout)findViewById(R.id.linear_Tools);
       /* relative_summary=(RelativeLayout) findViewById(R.id.linear_summary);
        relative_summary.setVisibility(View.GONE);*/

        String comp = "@drawable/comp";  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(comp, null, getPackageName());
        final Drawable res = getResources().getDrawable(imageResource);

        String incomp = "@drawable/incomp";  // where myresource (without the extension) is the file
        int imageResource2 = getResources().getIdentifier(incomp, null, getPackageName());
        final Drawable res2 = getResources().getDrawable(imageResource2);

       /* if(tv_pEnroll.getText().toString().trim().equals("0")) {

          //  linear_ptool1.setVisibility(View.GONE);
        }
        if((tv_ptool1.getText().toString()).equals("1") && (tv_ptool2.getText().toString()).equals("1") && (tv_ptool3.getText().toString()).equals("1")
                && (tv_ptool4.getText().toString()).equals("1") && (tv_ptool5.getText().toString()).equals("1") && (tv_ptool6a.getText().toString()).equals("1")
                &&(tv_ptool6b.getText().toString()).equals("1") && (tv_ptool7.getText().toString()).equals("1")){
            relative_summary.setVisibility(View.VISIBLE);
        }


        btn_pDetailTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_ptool1.getText().toString()).equals("0")) {
                //    tool1_icon.setImageDrawable(res2);
                    Intent intent = new Intent(ShowIndividualPartiData.this, RiskAndTriageCVA.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool1 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                //    tool1_icon.setImageDrawable(res);
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
               //     tool2_icon.setImageDrawable(res2);
                    Intent intent = new Intent(ShowIndividualPartiData.this, RiskAndTriageMI.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool2 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
             //       tool2_icon.setImageDrawable(res);
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
               //     tool3_icon.setImageDrawable(res2);
                    Intent intent = new Intent(ShowIndividualPartiData.this, RiskAndTriageDiabetic.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool3 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                 //   tool3_icon.setImageDrawable(res);
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
                //    tool4_icon.setImageDrawable(res2);
                    Intent intent = new Intent(ShowIndividualPartiData.this, IDRSModified.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool4 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                //    tool4_icon.setImageDrawable(res);
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowTool4Data.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        btn_pDetailTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   tool5_icon.setImageDrawable(res2);
                if ((tv_ptool5.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, PhysicalActivity.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool5 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
               //     tool5_icon.setImageDrawable(res);
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
                 //   tool6a_icon.setImageDrawable(res2);
                    Intent intent = new Intent(ShowIndividualPartiData.this, TobbaccoSmoking.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool6a is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                   // tool6a_icon.setImageDrawable(res);
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
                   // tool6b_icon.setImageDrawable(res2);
                    Intent intent = new Intent(ShowIndividualPartiData.this, TobbaccoSmokingSL.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool6b is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                   // tool6b_icon.setImageDrawable(res);
                    Intent intent = new Intent(ShowIndividualPartiData.this, ShowTool6bData.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                }
            }
        });

        btn_pDetailTool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tool7_icon.setImageDrawable(res2);
                if ((tv_ptool7.getText().toString()).equals("0")) {
                    Intent intent = new Intent(ShowIndividualPartiData.this, DietLifestyle.class);
                    Toast.makeText(ShowIndividualPartiData.this, "Tool7 is not Completed", Toast.LENGTH_SHORT).show();
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
                } else {
                  //  tool7_icon.setImageDrawable(res);
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
        });*/


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


           // tv_UserID.setText(data.getString(0));
            tv_pname.setText(data.getString(1));
            tv_pdob.setText(data.getString(2));
            tv_page.setText(data.getString(3));
            tv_pgender.setText(data.getString(4));
            tv_pcontact.setText(data.getString(5));
            tv_paltSim.setText(data.getString(6));
            tv_paddress.setText(data.getString(7));
            tv_plives.setText(data.getString(8));
            tv_pnotmoving.setText(data.getString(9));
            tv_psmartphone.setText(data.getString(10));
            tv_pparticipate.setText(data.getString(11));
            tv_pinformedconsent.setText(data.getString(12));
            tv_pRespondedIVR.setText(data.getString(13));
            tv_pRespondedSMS.setText(data.getString(14));
            tv_pReason.setText(data.getString(15));
           /* tv_ptool1.setText(data.getString(14));
            tv_ptool2.setText(data.getString(15));
            tv_ptool3.setText(data.getString(16));
            tv_ptool4.setText(data.getString(17));
            tv_ptool5.setText(data.getString(18));
            tv_ptool6a.setText(data.getString(19));
            tv_ptool6b.setText(data.getString(20));
            tv_ptool7.setText(data.getString(21));*/
         //   tv_pEnroll.setText(data.getString(22));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        Log.d("000222", "onResume");
        viewAllPatientData();

        String incomp = "@drawable/incomp";  // where myresource (without the extension) is the file
        int imageResource2 = getResources().getIdentifier(incomp, null, getPackageName());
        final Drawable res2 = getResources().getDrawable(imageResource2);

        String comp = "@drawable/comp";  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(comp, null, getPackageName());
        final Drawable res = getResources().getDrawable(imageResource);


       /* if ((tv_ptool1.getText().toString()).equals("0")) {
           // tool1_icon.setImageDrawable(res2);
            btn_pDetailTool1.setText("Edit");
        }
        else{
            btn_pDetailTool1.setText("Detail");
            //tool1_icon.setImageDrawable(res);
        }
        if ((tv_ptool2.getText().toString()).equals("0")) {
            //tool2_icon.setImageDrawable(res2);
            btn_pDetailTool2.setText("Edit");
        }
        else{
            btn_pDetailTool2.setText("Detail");
            //tool2_icon.setImageDrawable(res);
        }
        if ((tv_ptool3.getText().toString()).equals("0")) {
            //tool3_icon.setImageDrawable(res2);
            btn_pDetailTool3.setText("Edit");
        }
        else{
            btn_pDetailTool3.setText("Detail");
            //tool3_icon.setImageDrawable(res);
        }
        if ((tv_ptool4.getText().toString()).equals("0")) {
            //tool4_icon.setImageDrawable(res2);
            btn_pDetailTool4.setText("Edit");
        }
        else{
            btn_pDetailTool4.setText("Detail");
            //tool4_icon.setImageDrawable(res);
        }
        if ((tv_ptool5.getText().toString()).equals("0")) {
            //tool5_icon.setImageDrawable(res2);
            btn_pDetailTool5.setText("Edit");
        }
        else{
            btn_pDetailTool5.setText("Detail");
            //tool5_icon.setImageDrawable(res);
        }
        if ((tv_ptool6a.getText().toString()).equals("0")) {
            //tool6a_icon.setImageDrawable(res2);
            btn_pDetailTool6a.setText("Edit");
        }
        else{
            btn_pDetailTool6a.setText("Detail");
            //tool6a_icon.setImageDrawable(res);
        }
        if ((tv_ptool6b.getText().toString()).equals("0")) {
            //tool6b_icon.setImageDrawable(res2);
            btn_pDetailTool6b.setText("Edit");
        }
        else{
            btn_pDetailTool6b.setText("Detail");
            //tool6b_icon.setImageDrawable(res);
        }
        if ((tv_ptool7.getText().toString()).equals("0")) {
            //tool7_icon.setImageDrawable(res2);
            btn_pDetailTool7.setText("Edit");
        }
        else{
            btn_pDetailTool7.setText("Detail");
            //tool7_icon.setImageDrawable(res);
        }
*/
    }
}
