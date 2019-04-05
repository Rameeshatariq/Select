package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowIndividualPartiData extends AppCompatActivity {

    private DatabaseHelperRP mDatabaseHeperRP;
    Context ctx = ShowIndividualPartiData.this;
    Button btn_toolDetails;
    private String ContactNo;
    private TextView tv_pname, tv_page, tv_pgender, tv_pcontact, tv_paltSim, tv_paddress, tv_plives, tv_pnotmoving, tv_psmartphone, tv_pparticipate,
    tv_pinformedconsent, tv_pReason, tv_ptool1, tv_ptool2, tv_ptool3, tv_ptool4, tv_ptool5, tv_ptool6a, tv_ptool6b, tv_ptool7, tv_pEnroll, tv_pSyncData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_individual_parti_data);

        Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");

        mDatabaseHeperRP = new DatabaseHelperRP(this);

        tv_pname=(TextView)findViewById(R.id.tv_pname);
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


        btn_toolDetails=(Button) findViewById(R.id.showTool1Data);
        viewAllPatientData();
        btn_toolDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ShowIndividualPartiData.this, ShowToolsDetails.class);
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });
    }


    private void viewAllPatientData() {

        Cursor data = mDatabaseHeperRP.getPartiData(ContactNo.trim());

        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {
            tv_pname.setText(data.getString(0));
            tv_page.setText(data.getString(1));
            tv_pgender.setText(data.getString(2));
            tv_pcontact.setText(data.getString(3));
            tv_paltSim.setText(data.getString(4));
            tv_paddress.setText(data.getString(5));
            tv_plives.setText(data.getString(6));
            tv_pnotmoving.setText(data.getString(7));
            tv_psmartphone.setText(data.getString(8));
            tv_pparticipate.setText(data.getString(9));
            tv_pinformedconsent.setText(data.getString(10));
            tv_pReason.setText(data.getString(11));
            tv_ptool1.setText(data.getString(12));
            tv_ptool2.setText(data.getString(13));
            tv_ptool3.setText(data.getString(14));
            tv_ptool4.setText(data.getString(15));
            tv_ptool5.setText(data.getString(16));
            tv_ptool6a.setText(data.getString(17));
            tv_ptool6b.setText(data.getString(18));
            tv_ptool7.setText(data.getString(19));
            tv_pEnroll.setText(data.getString(20));
            tv_pSyncData.setText(data.getString(21));

        }
    }
}
