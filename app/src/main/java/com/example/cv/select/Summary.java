package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Summary extends AppCompatActivity {
    String contact, tool1, tool2, tool3, tool7;
    private Toolbar toolbar;
    TextView s_contact, tv_s_tool1, tv_s_tool2, tv_s_tool3, tv_s_tool7;
    DatabaseHelperRP databaseHelperRP;
    String riskAndTriageCVA,riskAndTriageMI,riskAndTriageDiabetic,dietLifestyle;
    Context ctx = this;
    Button submit;
    Lister ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        databaseHelperRP=new DatabaseHelperRP(this);
        ls = new Lister(ctx);

        riskAndTriageCVA= RiskAndTriageCVA.getInstance();

        riskAndTriageMI= RiskAndTriageMI.getInstance();

        riskAndTriageDiabetic= RiskAndTriageDiabetic.getInstance();

        dietLifestyle= DietLifestyle.getInstance();

        s_contact=(TextView)findViewById(R.id.pcontact);
        tv_s_tool1=(TextView)findViewById(R.id.tool1);
        tv_s_tool2=(TextView)findViewById(R.id.tool2);
        tv_s_tool3=(TextView)findViewById(R.id.tool3);
        tv_s_tool7=(TextView)findViewById(R.id.tool7);
        submit=(Button)findViewById(R.id.submitSummary);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Summary");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        contact=intent.getStringExtra("ContactNo");

       s_contact.setText(contact);
        tv_s_tool1.setText(riskAndTriageCVA);
        tv_s_tool2.setText(riskAndTriageMI);
        tv_s_tool3.setText(riskAndTriageDiabetic);
        tv_s_tool7.setText(dietLifestyle);

        try {
            boolean mflag= isCompleted(contact);

            setData(contact);

            if(mflag == true){
                // Toast.makeText(this, "Tool1 Completed", Toast.LENGTH_SHORT).show();
            }
            else{
                // Toast.makeText(this, "Tool1 not Completed", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToolsSummary();
            }
        });

    }

    private void addToolsSummary() {

        try {
            String[][] mData = ls.executeReader("Select *from summary where ContactSim  = '" + contact + "'");

            if (mData != null) {
                boolean mFlag = ls.executeNonQuery("Update summary set " +
                        "ContactSim = '" + contact + "', " +
                        "tool1 = '" + riskAndTriageCVA + "', " +
                        "tool2 = '" + riskAndTriageMI + "', " +
                        "tool3 = '" + riskAndTriageDiabetic + "', " +
                        "tool7 = '" + dietLifestyle + "' " +
                        " where ContactSim  = '" + contact + "'");
                if (mFlag == true) {
                    Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
                }

            } else {

                boolean isInserted = databaseHelperRP.addToolsSummary(contact, riskAndTriageCVA, riskAndTriageMI, riskAndTriageDiabetic, dietLifestyle);
                if (isInserted == true) {
                    Toast.makeText(this, "Summary Saved In Database Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Summary not Saved In Database Successfully", Toast.LENGTH_SHORT).show();
                }
            }
            finish();
        } catch (Exception e) {

            Toast.makeText(ctx, "=-=-=-=Exception  " + e, Toast.LENGTH_SHORT).show();
            Log.e("000333", "Exception " + e);
        }
    }

    public boolean isCompleted(String mPhone) {

        Lister lister = new Lister(Summary.this);

        String[][] mData = lister.executeReader("Select * From summary where ContactSim = '" + mPhone + "'");

        try {

            if (mData.length > 0){
                Log.d("000111", "mData[0][0] =  " + mData[0][0]);
                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);
                Log.d("000111", "mData[0][3] =  " + mData[0][3]);
                Log.d("000111", "mData[0][4] =  " + mData[0][4]);
                return  true;

            }else {
                return false;
            }

        }

        catch (Exception e) {
            Log.d("111", e.getMessage());
            return false;
        }


    }

    private void setData(String contactNo) {

        Lister lister = new Lister(Summary.this);

        String[][] mData = lister.executeReader("Select * From summary where ContactSim = '" + contactNo + "'");

        try {

            if (mData.length > 0) {

                Log.d("000111", "mData[0][0] =  " + mData[0][0]);
                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);
                Log.d("000111", "mData[0][3] =  " + mData[0][3]);
                Log.d("000111", "mData[0][4] =  " + mData[0][4]);

                s_contact.setText(mData[0][0]);
                tv_s_tool1.setText(mData[0][1]);
                tv_s_tool2.setText(mData[0][2]);
                tv_s_tool3.setText(mData[0][3]);
                tv_s_tool7.setText(mData[0][4]);

            } else {

            }

        } catch (Exception e) {
            Log.d("111", e.getMessage());

        }
    }
}
