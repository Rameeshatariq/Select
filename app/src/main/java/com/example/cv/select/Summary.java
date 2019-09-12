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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Summary extends AppCompatActivity {
    String contact, tool1, tool2, tool3, tool7;
    private Toolbar toolbar;
    String ContactNo;
    TextView s_contact, tv_s_tool1, tv_s_tool2, tv_s_tool3, tv_s_tool4, tv_s_tool5, tv_s_tool6a, tv_s_tool6b, tv_s_tool6c, tv_s_tool6d,
            tv_s_tool7a, tv_s_tool7b,tv_s_tool7c,tv_s_tool7d,tv_s_tool7e, tv_s_tool7f,tv_s_tool7g, heading;
    DatabaseHelperRP databaseHelperRP;
    String riskAndTriageCVA,riskAndTriageMI,riskAndTriageDiabetic, IDRSModified, physicalActivity, tobaccoSmokingQ1, tobaccoSmokingQ2, dietLifestyleQ1, dietLifestyleQ2,
            tobaccoSmokingQ3, tobaccoSmokingQ4, dietLifestyleQ3,dietLifestyleQ4,dietLifestyleQ5,dietLifestyleQ6,dietLifestyleQ7;
    String diabetic, hypertension, tobaccoSmoking1,tobaccoSmoking2,tobaccoSmoking3,tobaccoSmoking4,dietLifestyle1,dietLifestyle2,dietLifestyle3,dietLifestyle4,dietLifestyle5,dietLifestyle6,dietLifestyle7;
    Context ctx = this;
    Button recommendation;
    Lister ls;
    int summarySync=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        databaseHelperRP = new DatabaseHelperRP(this);
        ls = new Lister(ctx);
        final Intent intent = getIntent();
        contact = intent.getStringExtra("ContactNo");



       // s_contact = (TextView) findViewById(R.id.pcontact);
        heading=(TextView)findViewById(R.id.summaryHeading);
        tv_s_tool1 = (TextView) findViewById(R.id.tool1);
        tv_s_tool2 = (TextView) findViewById(R.id.tool2);
        tv_s_tool3 =(TextView) findViewById(R.id.tool3);
        tv_s_tool4 = (TextView) findViewById(R.id.tool4);
        tv_s_tool5 = (TextView) findViewById(R.id.tool5);
        tv_s_tool6a =(TextView)findViewById(R.id.tool6a);
        tv_s_tool6b =(TextView)findViewById(R.id.tool6b);
        tv_s_tool6c =(TextView)findViewById(R.id.tool6c);
        tv_s_tool6d =(TextView)findViewById(R.id.tool6d);
        tv_s_tool7a = (TextView) findViewById(R.id.tool7a);
        tv_s_tool7b = (TextView) findViewById(R.id.tool7b);
        tv_s_tool7c = (TextView) findViewById(R.id.tool7c);
        tv_s_tool7d = (TextView) findViewById(R.id.tool7d);
        tv_s_tool7e = (TextView) findViewById(R.id.tool7e);
        tv_s_tool7f = (TextView) findViewById(R.id.tool7f);
        tv_s_tool7g = (TextView) findViewById(R.id.tool7g);

        recommendation=(Button)findViewById(R.id.next);
        // submit=(Button)findViewById(R.id.submitSummary);

        // riskAndTriageCVA= RiskAndTriageCVA.getInstance();

        //  riskAndTriageMI= RiskAndTriageMI.getInstance();

     //   physicalActivity=ShowTool5Data.getInstance();
      //  Toast.makeText(ctx, ""+physicalActivity, Toast.LENGTH_SHORT).show();


        //    dietLifestyle= DietLifestyle.getInstance();

        Cursor Tool1data = databaseHelperRP.getPartiTool1Data(contact);
        if (Tool1data.getCount() == 0) {
            return;
        }

        while (Tool1data.moveToNext()) {

            riskAndTriageCVA = Tool1data.getString(9);
        }

        Cursor Tool2data = databaseHelperRP.getPartiTool2Data(contact);
        if (Tool2data.getCount() == 0) {
            return;
        }

        while (Tool2data.moveToNext()) {

            riskAndTriageMI = Tool2data.getString(4);
        }

        Cursor Tool3data = databaseHelperRP.getPartiTool3Data(contact);
        if (Tool3data.getCount() == 0) {
            return;
        }

        while (Tool3data.moveToNext()) {

            diabetic = Tool3data.getString(1);
            hypertension=Tool3data.getString(7);

            if(diabetic.equals("-")){
                diabetic="Diabetic not Present";
            }
            if(hypertension.equals("-")){
                hypertension="Hypertension not Present";
            }
            riskAndTriageDiabetic= diabetic+", "+hypertension;
        }


        Cursor Tool5data = databaseHelperRP.getPartiTool5Data(contact);
        if (Tool5data.getCount() == 0) {
            return;
        }

        while (Tool5data.moveToNext()) {

            physicalActivity = Tool5data.getString(13);
            // Toast.makeText(ctx, ""+physicalActivity, Toast.LENGTH_SHORT).show();
        }
        Cursor Tool4data = databaseHelperRP.getPartiTool4Data(contact);
        if (Tool4data.getCount() == 0) {
            return;
        }

        while (Tool4data.moveToNext()) {

            IDRSModified = Tool4data.getString(2);
           // Toast.makeText(ctx, ""+IDRSModified, Toast.LENGTH_SHORT).show();
        }

        Cursor Tool6adata = databaseHelperRP.getPartiTool6aData(contact);
        if (Tool6adata.getCount() == 0) {
            return;
        }

        while (Tool6adata.moveToNext()) {

            tobaccoSmoking1 = Tool6adata.getString(1);
            tobaccoSmoking2 = Tool6adata.getString(2);
            tobaccoSmoking3 = Tool6adata.getString(3);
            tobaccoSmoking4 = Tool6adata.getString(4);
        }

        if(tobaccoSmoking1.equals("Daily") || tobaccoSmoking1.equals("Less than Daily")){
            tobaccoSmokingQ1="Current Smoker (smoke tobacco)";
        }
        else{
            tobaccoSmokingQ1="Not a Smoker";
        }


        if(tobaccoSmoking2.equals("Daily") || tobaccoSmoking2.equals("Less than Daily")){
            tobaccoSmokingQ2="Past Smoker (smoke tobacco)";
        }
        else{
            tobaccoSmokingQ2="Not a Smoker";
        }

        if(tobaccoSmoking3.equals("Daily") || tobaccoSmoking3.equals("Less than Daily")){
            tobaccoSmokingQ3="Current Smoker(smokeless tobacco)";
        }
        else{
            tobaccoSmokingQ3="Not a Smoker";
        }

        if(tobaccoSmoking4.equals("Daily") || tobaccoSmoking4.equals("Less than Daily")){
            tobaccoSmokingQ4="Past Smoker (smokeless tobacco)";
        }
        else{
            tobaccoSmokingQ4="Not a Smoker";
        }

        Cursor Tool7data = databaseHelperRP.getPartiTool7Data(contact);
        if (Tool7data.getCount() == 0) {
            return;
        }

        while (Tool7data.moveToNext()) {

            dietLifestyle1 = Tool7data.getString(1);
            dietLifestyle2 = Tool7data.getString(2);
            dietLifestyle3 = Tool7data.getString(3);
            dietLifestyle4 = Tool7data.getString(4);
            dietLifestyle5 = Tool7data.getString(5);
            dietLifestyle6 = Tool7data.getString(6);
            dietLifestyle7 = Tool7data.getString(7);
        }

        if(dietLifestyle1.equals("Yes")){
            dietLifestyleQ1="High Risk";
        }
        else{
            dietLifestyleQ1="Normal";
        }
        if(dietLifestyle2.equals("Yes")){
            dietLifestyleQ2="High Risk";
        }
        else {
            dietLifestyleQ2="Normal";
        }
        if(Integer.parseInt(dietLifestyle3) <5 ){
            dietLifestyleQ3="High Risk";
        }
        else{
            dietLifestyleQ3="Normal";
        }

        if(Integer.parseInt(dietLifestyle4) >= 3 ){
            dietLifestyleQ4="High Risk";
        }
        else{
            dietLifestyleQ4="Normal";
        }

        if(Integer.parseInt(dietLifestyle5) >= 3 ){
            dietLifestyleQ5="High Risk";
        }
        else{
            dietLifestyleQ5="Normal";
        }

        if(Integer.parseInt(dietLifestyle6) >= 3 ){
            dietLifestyleQ6="High Risk";
        }
        else{
            dietLifestyleQ6="Normal";
        }
        if(Integer.parseInt(dietLifestyle7) <= 2 ){
            dietLifestyleQ7="High Risk";
        }
        else{
            dietLifestyleQ7="Normal";
        }




        // submit.setOnClickListener(new View.OnClickListener() {


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


       // s_contact.setText(contact);
        tv_s_tool1.setText(riskAndTriageCVA);
        tv_s_tool2.setText(riskAndTriageMI);
        tv_s_tool3.setText(riskAndTriageDiabetic);
        tv_s_tool4.setText(physicalActivity);
        tv_s_tool5.setText(IDRSModified);
        tv_s_tool6a.setText(tobaccoSmokingQ1);
        tv_s_tool6b.setText(tobaccoSmokingQ2);
        tv_s_tool6c.setText(tobaccoSmokingQ3);
        tv_s_tool6d.setText(tobaccoSmokingQ4);
        tv_s_tool7a.setText(dietLifestyleQ1);
        tv_s_tool7b.setText(dietLifestyleQ2);
        tv_s_tool7c.setText(dietLifestyleQ3);
        tv_s_tool7d.setText(dietLifestyleQ4);
        tv_s_tool7e.setText(dietLifestyleQ5);
        tv_s_tool7f.setText(dietLifestyleQ6);
        tv_s_tool7g.setText(dietLifestyleQ7);

        recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(heading.getText().toString().equals("High Risk")){
                    Intent intent1=new Intent(Summary.this,HighRecommendation.class);
                    intent1.putExtra("ContactNo",contact);
                    startActivity(intent1);
                }
                if(heading.getText().toString().equals("Moderate Risk")){
                    Intent intent1=new Intent(Summary.this,ModerateRiskRecommendation.class);
                    intent1.putExtra("ContactNo",contact);
                    startActivity(intent1);
                }
                if(heading.getText().toString().equals("Low Risk")){
                    Intent intent1=new Intent(Summary.this,LowRiskRecommendation.class);
                    intent1.putExtra("ContactNo",contact);
                    startActivity(intent1);
                }
            }
        });

        try {
            boolean mflag= isCompleted(contact);

            //  setData(contact);

            if(mflag == true){
                // Toast.makeText(this, "Tool1 Completed", Toast.LENGTH_SHORT).show();
            }
            else{
                addToolsSummary();
                // Toast.makeText(this, "Tool1 not Completed", Toast.LENGTH_SHORT).show();

            }

        }catch (Exception e){
        }


    }


    public void addToolsSummary() {

        try {
            String[][] mData = ls.executeReader("Select *from summary where ContactSim  = '" + contact + "'");

            if (mData != null) {
               /* boolean mFlag = ls.executeNonQuery("Update summary set " +
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
                }*/

            } else {

                boolean isInserted = databaseHelperRP.addToolsSummary(contact, riskAndTriageCVA, riskAndTriageMI, riskAndTriageDiabetic, physicalActivity,IDRSModified,tobaccoSmokingQ1,
                        tobaccoSmokingQ2,tobaccoSmokingQ3,tobaccoSmokingQ4,dietLifestyleQ1,dietLifestyleQ2,dietLifestyleQ3,dietLifestyleQ4,dietLifestyleQ5,dietLifestyleQ6,dietLifestyleQ7,
                        summarySync);
                if (isInserted == true) {
                    Toast.makeText(this, "Summary Saved In Database Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Summary not Saved In Database Successfully", Toast.LENGTH_SHORT).show();
                }
            }
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
                Log.d("000111", "mData[0][4] =  " + mData[0][5]);
                Log.d("000111", "mData[0][4] =  " + mData[0][6]);
                Log.d("000111", "mData[0][4] =  " + mData[0][7]);
                Log.d("000111", "mData[0][4] =  " + mData[0][8]);
                Log.d("000111", "mData[0][4] =  " + mData[0][9]);
                Log.d("000111", "mData[0][4] =  " + mData[0][10]);
                Log.d("000111", "mData[0][4] =  " + mData[0][11]);
                Log.d("000111", "mData[0][4] =  " + mData[0][12]);
                Log.d("000111", "mData[0][4] =  " + mData[0][13]);
                Log.d("000111", "mData[0][4] =  " + mData[0][14]);
                Log.d("000111", "mData[0][4] =  " + mData[0][15]);
                Log.d("000111", "mData[0][4] =  " + mData[0][16]);

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

/*    private void setData(String contactNo) {

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

        }*/


    @Override
    protected void onResume() {
        super.onResume();

        if(riskAndTriageCVA.equals("CVA Event Present") || riskAndTriageMI.equals("MI present")){
            heading.setText("High Risk");
        }
        else if (diabetic.equals("Diabetic is present") || hypertension.equals("Hypertension is present")){
            heading.setText("Moderate Risk");
        }
        else{
            heading.setText("Low Risk");
        }
    }
}
