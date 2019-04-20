package com.example.cv.select;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterParticipant extends AppCompatActivity {

    private Button btn_pregister, btn_submitReason, btn_updateParti;
    private EditText et_pname, et_paddress, et_page, et_StudyID, et_contactSim, et_altSim, et_reason, et_pdateOfBirth;
    public String pName, pDob, pAddress, pAge, pStudyID, pContactSim, pAltSim, pLivesInMalir, pHaveSmartphone, participate, pNotMoving, pgender, pConsentTaken, pReason;
    private RadioGroup rd_pgender, rd_plives, rd_psmartphone, rd_pnotmoving, rd_participate, rd_pconsenttaken;
    private RadioButton rd_pgender_male, rd_pgender_female, rd_plives_yes, rd_plives_no, rd_psmartphone_yes, rd_psmartphone_no, rd_pnotmoving_yes, rd_pnotmoving_no, rd_participate_yes, rd_participate_no, rd_pconsenttaken_yes, rd_pconsenttaken_no;
    private RadioButton radiovalueGender, radiovalueLives, radiovalueNotmoving, radiovalueParticipate, radiovalueSmartphone, radiovalueConsentTaken;
    private DatabaseHelperRP mDatabaseHelperRP;
    String Tool1, Tool2, Tool3, Tool4, Tool5, Tool6a, Tool6b, Tool7, Enroll;
    View dialogView;
    private Toolbar toolbar;
    AlertDialog.Builder dialogBuilder;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    int year, month, day;
    String ContactNo;
    AlertDialog alertDialog;
    Context ctx = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_participant);



        et_pname = (EditText) findViewById(R.id.ed_pname);
        et_paddress = (EditText) findViewById(R.id.ed_paddress);
        et_page = (EditText) findViewById(R.id.ed_page);
        et_contactSim = (EditText) findViewById(R.id.ed_pcontact);
        et_altSim = (EditText) findViewById(R.id.ed_p_altsim);
        et_pdateOfBirth=(EditText)findViewById(R.id.ed_pdateOfBirth);



        rd_psmartphone_yes = (RadioButton) findViewById(R.id.rd_p_smrtph_yes);
        rd_plives_yes = (RadioButton) findViewById(R.id.rd_plives_yes);
        rd_participate_yes = (RadioButton) findViewById(R.id.rd_participate_yes);
        rd_pnotmoving_yes = (RadioButton) findViewById(R.id.rd_p_notmoving_yes);
        rd_pnotmoving_no=(RadioButton)findViewById(R.id.rd_p_notmoving_no);

        rd_pgender=(RadioGroup)findViewById(R.id.rd_pgender);
        rd_plives=(RadioGroup)findViewById(R.id.rd_plives);
        rd_pnotmoving=(RadioGroup)findViewById(R.id.rd_p_notmoving);
        rd_pconsenttaken=(RadioGroup)findViewById(R.id.rd_p_consenttaken);
        rd_psmartphone=(RadioGroup)findViewById(R.id.rd_p_smrtph);
        rd_participate=(RadioGroup)findViewById(R.id.rd_participate);

        try {

            Intent intent=getIntent();
            ContactNo=intent.getStringExtra("ContactNo");
            boolean mflag= isRegistered(ContactNo);
            setData(ContactNo);

            if(mflag == true){
                Toast.makeText(this, "Patient Registered", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Patient Not Registered", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){


        }


        mDatabaseHelperRP = new DatabaseHelperRP(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Register Participant");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        et_pdateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideSoftKeyboard(RegisterParticipant.this);
                Calendar cal= Calendar.getInstance();

                 year=cal.get(Calendar.YEAR);
                 month=cal.get(Calendar.MONTH);
                 day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterParticipant.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("TAG", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                et_pdateOfBirth.setText(date);
                et_page.setText(getAge(year,month,day));

            }
        };

        btn_pregister = (Button) findViewById(R.id.btn_p_register);
        btn_pregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean mflag=isRegistered(ContactNo);

                boolean isValidatedFlag = isValidated();
                if(!isValidatedFlag){
                    Toast.makeText(ctx , "Validate false", Toast.LENGTH_SHORT).show();
                    return;
                }

                isPatEligible();

                if(mflag == true){
                    updatePatient();
                }
                else{
                    insertPatient();
                }
            }
        });

    }

    private void setData(String contactNo) {

        Lister lister = new Lister(RegisterParticipant.this);

        String[][] mData = lister.executeReader("Select * From patient where ContactSim = '" + contactNo + "'");

        try {

            if (mData.length > 0){

                Log.d("000111", "mData[0][0] =  "+mData[0][0]);
                Log.d("000111", "mData[0][1] =  "+mData[0][1]);
                Log.d("000111", "mData[0][2] =  "+mData[0][2]);
                Log.d("000111", "mData[0][3] =  "+mData[0][3]);
                Log.d("000111", "mData[0][4] =  "+mData[0][4]);
                Log.d("000111", "mData[0][5] =  "+mData[0][5]);
                Log.d("000111", "mData[0][6] =  "+mData[0][6]);
                Log.d("000111", "mData[0][7] =  "+mData[0][7]);
                Log.d("000111", "mData[0][8] =  "+mData[0][8]);
                Log.d("000111", "mData[0][9] =  "+mData[0][9]);
                Log.d("000111", "mData[0][10] =  "+mData[0][10]);
                Log.d("000111", "mData[0][11] =  "+mData[0][11]);

                et_pname.setText(mData[0][0]);
                et_pdateOfBirth.setText(mData[0][1]);
                et_page.setText(mData[0][2]);

                if(mData[0][3].equalsIgnoreCase("Male")){
                    rd_pgender.check(R.id.rd_male);
                }else {
                    rd_pgender.check(R.id.rd_female);
                }
                et_contactSim.setText(mData[0][4]);
                et_altSim.setText(mData[0][5]);
                et_paddress.setText(mData[0][6]);

                if(mData[0][7].equalsIgnoreCase("Yes")){

                    rd_plives.check(R.id.rd_plives_yes);
                }else {
                    rd_plives.check(R.id.rd_plives_no);

                }
                if(mData[0][8].equalsIgnoreCase("Yes")){

                    rd_pnotmoving.check(R.id.rd_p_notmoving_yes);
                }else {
                    rd_pnotmoving.check(R.id.rd_p_notmoving_no);

                }
                if(mData[0][9].equalsIgnoreCase("Yes")){

                    rd_psmartphone.check(R.id.rd_p_smrtph_yes);
                }else {
                    rd_psmartphone.check(R.id.rd_p_smrtph_no);

                }
                if(mData[0][10].equalsIgnoreCase("Yes")){

                    rd_participate.check(R.id.rd_participate_yes);
                }else {
                    rd_participate.check(R.id.rd_participate_no);

                }
                if(mData[0][11].equalsIgnoreCase("Yes")){

                    rd_pconsenttaken.check(R.id.rd_p_consenttaken_yes);
                }else {
                    rd_pconsenttaken.check(R.id.rd_p_consenttaken_no);

                }


            }else {

            }

        }

        catch (Exception e) {
            Log.d("111", e.getMessage());

        }

    }

    private boolean isValidated() {

        pName = et_pname.getText().toString();
        pDob = et_pdateOfBirth.getText().toString();
        pAge = et_page.getText().toString();
        pAddress = et_paddress.getText().toString();
        pContactSim = et_contactSim.getText().toString();
        pAltSim = et_altSim.getText().toString();
        String regxStr= "^[0][3][\\d]{2}[\\d]{7}$";
       // pContactSim = "03451212123";

        if (TextUtils.isEmpty(pName)) {
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            return false;

        } else if (TextUtils.isEmpty(pDob)) {
            Toast.makeText(this, "Please Enter Date of Birth", Toast.LENGTH_SHORT).show();
            return false;


        } else if (TextUtils.isEmpty(pAddress)) {
            Toast.makeText(this, "Please Enter Address", Toast.LENGTH_SHORT).show();
            return false;


        } else if (TextUtils.isEmpty(pContactSim) || pContactSim.length() < 11 || pContactSim.length() > 11 || pContactSim.matches(regxStr)==false) {
            Toast.makeText(this, "Invalid Contact Sim Number", Toast.LENGTH_SHORT).show();
            return false;


        } else if (TextUtils.isEmpty(pAltSim) || pAltSim.length() < 11 || pAltSim.length() > 11 || pAltSim.matches(regxStr)==false) {
            Toast.makeText(this, "Invalid Contact Sim Number", Toast.LENGTH_SHORT).show();
            return false;


        } else if(rd_pgender.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;


        } else if(rd_plives.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please Select One Option", Toast.LENGTH_SHORT).show();
            return false;


        }  else if(rd_pnotmoving.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please Select One Option", Toast.LENGTH_SHORT).show();
            return false;


        } else if(rd_psmartphone.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please Select One Option", Toast.LENGTH_SHORT).show();
            return false;


        } else if(rd_participate.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please Select One Option", Toast.LENGTH_SHORT).show();
            return false;


        } else if(rd_pconsenttaken.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please Select One Option", Toast.LENGTH_SHORT).show();
            return false;



        }
        else {
            return true;

        }
    }

    private void insertPatient() {

        pName = et_pname.getText().toString();
        pDob = et_pdateOfBirth.getText().toString();
        pAge = et_page.getText().toString();
        pAddress = et_paddress.getText().toString();
        pContactSim = et_contactSim.getText().toString();
        pAltSim = et_altSim.getText().toString();


        radiovalueGender = (RadioButton) this.findViewById(rd_pgender.getCheckedRadioButtonId());
        pgender = radiovalueGender.getText().toString();

        radiovalueLives = (RadioButton) this.findViewById(rd_plives.getCheckedRadioButtonId());
        pLivesInMalir = radiovalueLives.getText().toString();

        radiovalueParticipate = (RadioButton) this.findViewById(rd_participate.getCheckedRadioButtonId());
        participate = radiovalueParticipate.getText().toString();

        radiovalueNotmoving = (RadioButton) this.findViewById(rd_pnotmoving.getCheckedRadioButtonId());
        pNotMoving = radiovalueNotmoving.getText().toString();

        radiovalueSmartphone = (RadioButton) this.findViewById(rd_psmartphone.getCheckedRadioButtonId());
        pHaveSmartphone = radiovalueSmartphone.getText().toString();

        radiovalueConsentTaken = (RadioButton) this.findViewById(rd_pconsenttaken.getCheckedRadioButtonId());
        pConsentTaken = radiovalueConsentTaken.getText().toString();


        boolean isInserted = mDatabaseHelperRP.addData(pName, pDob, pAge, pgender, pContactSim, pAltSim, pAddress, pLivesInMalir,
                pNotMoving, pHaveSmartphone, participate, pConsentTaken,pReason, Tool1, Tool2, Tool3, Tool4, Tool5, Tool6a, Tool6b, Tool7,
                Enroll);


        Log.d("111", "tool 1: "+Tool1);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void updatePatient() {

        pName = et_pname.getText().toString();
        pDob = et_pdateOfBirth.getText().toString();
        pAge = et_page.getText().toString();
        pAddress = et_paddress.getText().toString();
        pContactSim = et_contactSim.getText().toString();
        pAltSim = et_altSim.getText().toString();

        Log.d("000222", "==========");
        Log.d("000222", "==pAddress=="+pAddress);
        Log.d("000222", "=====et_paddress===="+et_paddress);


        radiovalueGender = (RadioButton) this.findViewById(rd_pgender.getCheckedRadioButtonId());
        pgender = radiovalueGender.getText().toString();

        radiovalueLives = (RadioButton) this.findViewById(rd_plives.getCheckedRadioButtonId());
        pLivesInMalir = radiovalueLives.getText().toString();

        radiovalueParticipate = (RadioButton) this.findViewById(rd_participate.getCheckedRadioButtonId());
        participate = radiovalueParticipate.getText().toString();

        radiovalueNotmoving = (RadioButton) this.findViewById(rd_pnotmoving.getCheckedRadioButtonId());
        pNotMoving = radiovalueNotmoving.getText().toString();

        radiovalueSmartphone = (RadioButton) this.findViewById(rd_psmartphone.getCheckedRadioButtonId());
        pHaveSmartphone = radiovalueSmartphone.getText().toString();

        radiovalueConsentTaken = (RadioButton) this.findViewById(rd_pconsenttaken.getCheckedRadioButtonId());
        pConsentTaken = radiovalueConsentTaken.getText().toString();

//        boolean isUpdate = mDatabaseHelperRP.updatePrtiData(pName, pDob, pAge, pgender, pContactSim, pAltSim, pAddress, pLivesInMalir,
//                pNotMoving, pHaveSmartphone, participate, pConsentTaken,pReason);


        /*
      Lister ls = new Lister(ctx);
        boolean isUpdate = ls.executeNonQuery("update patient set " +
                "Name = '"+pName+"' , " +
                "Dob = '"+pDob+"' , " +
                "Age = '"+pAge+"' , " +
                "Gender = '"+pgender+"' , " +
                "AlternateSim = '"+pAltSim+"' , " +
                "Address = '"+pAddress+"' , " +
                "LivesInMalir = '"+pLivesInMalir+"' , " +
                "NotMovingFor6Months = '"+pNotMoving+"' , " +
                "SmartPhone = '"+pHaveSmartphone+"' , " +
                "ParticipateFor6Months = '"+participate+"' , " +
                "InformedConsentTaken = '"+pConsentTaken+"' , " +
                "Reason = '"+pReason+"'  " +
                " where ContactSim = '"+pContactSim+"'" +
                " )");
*/


        Lister ls = new Lister(ctx);
        boolean isUpdate = ls.executeNonQuery("UPDATE patient set " +
                "Name = '"+pName+"' , " +
                "Dob = '"+pDob+"' , " +
                "Age = '"+pAge+"' , " +
                "Gender = '"+pgender+"' , " +
                "AlternateSim = '"+pAltSim+"' , " +
                "Address = '"+pAddress+"' , " +
                "LivesInMalir = '"+pLivesInMalir+"' , " +
                "NotMovingFor6Months = '"+pNotMoving+"' , " +
                "SmartPhone = '"+pHaveSmartphone+"' , " +
                "ParticipateFOR6Months = '"+participate+"' , " +
                "InformedConsentTaken = '"+pConsentTaken+"' , " +
                "Reason = '"+pReason+"'  " +
                " where ContactSim = '"+pContactSim+"'" +
                " ");



        if (isUpdate) {
            Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
           // Intent intent=new Intent(RegisterParticipant.this, MainActivity.class);
            //startActivity(intent);



            Lister lister = new Lister(RegisterParticipant.this);

            String[][] mData = lister.executeReader("Select * From patient where ContactSim = '" + pContactSim + "'");

            try {

                if (mData.length > 0) {

                    Log.d("000222", "mData[0][0] =  " + mData[0][0]);
                    Log.d("000222", "mData[0][1] =  " + mData[0][1]);
                    Log.d("000222", "mData[0][2] =  " + mData[0][2]);
                    Log.d("000222", "mData[0][3] =  " + mData[0][3]);
                    Log.d("000222", "mData[0][4] =  " + mData[0][4]);
                    Log.d("000222", "mData[0][5] =  " + mData[0][5]);
                    Log.d("000222", "mData[0][6] =  " + mData[0][6]);
                    Log.d("000222", "mData[0][7] =  " + mData[0][7]);
                    Log.d("000222", "mData[0][8] =  " + mData[0][8]);
                    Log.d("000222", "mData[0][9] =  " + mData[0][9]);
                    Log.d("000222", "mData[0][10] =  " + mData[0][10]);
                    Log.d("000222", "mData[0][11] =  " + mData[0][11]);
                }
            }catch (Exception e){


            }

                } else {
            Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
           // Intent intent=new Intent(RegisterParticipant.this, MainActivity.class);
            //startActivity(intent);
            finish();
        }
    }

    private void isPatEligible() {
        if (rd_plives_yes.isChecked() == true && rd_pnotmoving_no.isChecked() == true && rd_participate_yes.isChecked() == true && rd_psmartphone_yes.isChecked() == true) {
            Toast.makeText(this, "This participant is Eligible", Toast.LENGTH_SHORT).show();
            Enroll="1";
            Tool1="0";
            Tool2="0";
            Tool3="0";
            Tool4="0";
            Tool5="0";
            Tool6a="0";
            Tool6b="0";
            Tool7="0";
//            addData();
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.putExtra("ContactNo", pContactSim);
//            startActivity(intent);
        } else {
            Toast.makeText(this, "This Participant is not Eligible", Toast.LENGTH_SHORT).show();
            dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            dialogView = inflater.inflate(R.layout.alert_dialog, null);
            btn_submitReason=(Button)dialogView.findViewById(R.id.add_reason);
            btn_submitReason.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Enroll = "0";
                    et_reason = (EditText) alertDialog.findViewById(R.id.reason);
                    pReason = et_reason.getText().toString();
                    Tool1 = null;
                    Tool2 = null;
                    Tool3 = null;
                    Tool4 = null;
                    Tool5 = null;
                    Tool6a = null;
                    Tool6b = null;
                    Tool7 = null;

                    Lister ls = new Lister(ctx);
                    boolean isUpdate = ls.executeNonQuery("UPDATE patient set " +
                            "Enroll = '" + Enroll + "' , " +
                            "Reason = '" + pReason + "'  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");

                    if (isUpdate) {
                        Toast.makeText(RegisterParticipant.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialogBuilder.setView(dialogView);
            alertDialog = dialogBuilder.create();
            alertDialog.show();
        }

}

    private void checkDataForUpdate() {
        if (rd_plives_yes.isChecked() == true && rd_pnotmoving_no.isChecked() == true && rd_participate_yes.isChecked() == true && rd_psmartphone_yes.isChecked() == true) {
            Toast.makeText(this, "This participant is Eligible", Toast.LENGTH_SHORT).show();
            Enroll="Yes";
            //updateParticipantDetail();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("ContactNo", pContactSim);
            startActivity(intent);
        } else {
            Toast.makeText(this, "This Participant is not Eligible", Toast.LENGTH_SHORT).show();
            dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            dialogView = inflater.inflate(R.layout.alert_dialog, null);
            btn_submitReason=(Button)dialogView.findViewById(R.id.add_reason);
            btn_submitReason.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Enroll="No";
                    et_reason=(EditText)alertDialog.findViewById(R.id.reason);
                    pReason=et_reason.getText().toString();
                    //updateParticipantDetail();
                    Intent intent=new Intent(RegisterParticipant.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            dialogBuilder.setView(dialogView);
            alertDialog = dialogBuilder.create();
            alertDialog.show();

        }

    }

    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }



    public boolean isRegistered(String mPhone) {

        Lister lister = new Lister(RegisterParticipant.this);

        String[][] mData = lister.executeReader("Select * From patient where ContactSim = '" + mPhone + "'");

        try {

            if (mData.length > 0){
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

}