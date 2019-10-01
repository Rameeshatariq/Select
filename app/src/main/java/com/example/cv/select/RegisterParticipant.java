package com.example.cv.select;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
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

    private Button btn_pregister, btn_submitReason, btn_updateParti, btn_cancel;
    private EditText et_pname, et_paddress, et_page, et_StudyID, et_contactSim, et_altSim, et_reason, et_pdateOfBirth;
    public String pName, pDob, pAddress, pAge, pStudyID, pContactSim, pAltSim, pLivesInMalir, pHaveSmartphone, participate, pNotMoving, pgender, pConsentTaken, pRespondedIVR, pRespondedSMS, pReason;
    private RadioGroup rd_pgender, rd_plives, rd_psmartphone, rd_pnotmoving, rd_participate, rd_pconsenttaken, rd_prespondedIVR, rd_prespondedSMS;
    private RadioButton rd_pgender_male, rd_pgender_female, rd_plives_yes, rd_plives_no, rd_psmartphone_yes, rd_psmartphone_no, rd_pnotmoving_yes, rd_pnotmoving_no, rd_participate_yes, rd_participate_no, rd_pconsenttaken_yes, rd_pconsenttaken_no, rd_prespondedIVR_no, rd_prespondedIVR_yes, rd_prespondedSMS_yes,rd_prespondedSMS_no;
    private RadioButton radiovalueGender, radiovalueLives, radiovalueNotmoving, radiovalueParticipate, radiovalueSmartphone, radiovalueConsentTaken, radioValueRespondedIVR, radioValueRespondedSMS;
    private DatabaseHelperRP mDatabaseHelperRP;
    String UserID;
    String Tool1, Tool2, Tool3, Tool4, Tool5, Tool6a, Tool6b, Tool7, Enroll;
    View dialogView;
    int syncPatient=0, syncTool1 = 0, syncTool2=0, syncTool3 = 0, syncTool4=0, syncTool5=0, syncTool6=0, syncTool7=0, synctele=0, syncSummary=0;
    private Toolbar toolbar;
    AlertDialog.Builder dialogBuilder;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    int year, month, day;
    String ContactNo,regxStr;
    AlertDialog alertDialog;
    Context ctx = this;
    boolean mFlagReasonable = true;
    boolean c;
    String[][] mData;
    SharedPreferences sharedPreferences;
    boolean isFromRegistered = true;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_participant);

        //  Toast.makeText(ctx, "Register", Toast.LENGTH_SHORT).show();

        sharedPreferences = getSharedPreferences("loginref", MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);

        mDatabaseHelperRP=new DatabaseHelperRP(this);

        try {

            if (username != null) {

                Cursor cursor = mDatabaseHelperRP.getUserID(username);
                if (cursor.getCount() == 0) {
                    return;
                }

                while (cursor.moveToNext()) {

                    UserID = cursor.getString(0);
                }

              //  Toast.makeText(ctx, "" + UserID, Toast.LENGTH_SHORT).show();
            }
        }

      /*  try {

            if (username.equals("raheel.allana")) {
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
            }
        }*/
        catch (Exception e){
            Toast.makeText(ctx, "Please Login Again", Toast.LENGTH_SHORT).show();
            SharedPreferences blockSession = this.getSharedPreferences("loginref", MODE_PRIVATE);
            SharedPreferences.Editor blockEdit = blockSession.edit();
            blockEdit.putBoolean("savelogin", false);
            blockEdit.commit();

            Intent intent = new Intent(RegisterParticipant.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
            et_pname = (EditText) findViewById(R.id.ed_pname);
            et_paddress = (EditText) findViewById(R.id.ed_paddress);
            et_page = (EditText) findViewById(R.id.ed_page);
            et_contactSim = (EditText) findViewById(R.id.ed_pcontact);
            et_altSim = (EditText) findViewById(R.id.ed_p_altsim);
            et_pdateOfBirth = (EditText) findViewById(R.id.ed_pdateOfBirth);


            rd_psmartphone_yes = (RadioButton) findViewById(R.id.rd_p_smrtph_yes);
            rd_plives_yes = (RadioButton) findViewById(R.id.rd_plives_yes);
            rd_participate_yes = (RadioButton) findViewById(R.id.rd_participate_yes);
            rd_pnotmoving_yes = (RadioButton) findViewById(R.id.rd_p_notmoving_yes);
            rd_pnotmoving_no = (RadioButton) findViewById(R.id.rd_p_notmoving_no);
            rd_pconsenttaken_yes=(RadioButton)findViewById(R.id.rd_p_consenttaken_yes);
            rd_prespondedIVR_no=(RadioButton)findViewById(R.id.rd_p_respondedtoIVR_no);
            rd_prespondedIVR_yes=(RadioButton)findViewById(R.id.rd_p_respondedtoIVR_yes);
            rd_prespondedSMS_yes=(RadioButton)findViewById(R.id.rd_p_respondedtoSMS_yes);
            rd_prespondedSMS_no=(RadioButton)findViewById(R.id.rd_p_respondedtoSMS_no);



            rd_pgender = (RadioGroup) findViewById(R.id.rd_pgender);
            rd_plives = (RadioGroup) findViewById(R.id.rd_plives);
            rd_pnotmoving = (RadioGroup) findViewById(R.id.rd_p_notmoving);
            rd_pconsenttaken = (RadioGroup) findViewById(R.id.rd_p_consenttaken);
            rd_psmartphone = (RadioGroup) findViewById(R.id.rd_p_smrtph);
            rd_participate = (RadioGroup) findViewById(R.id.rd_participate);
            rd_prespondedSMS=(RadioGroup)findViewById(R.id.rd_p_respondedtoSMS);
            rd_prespondedIVR=(RadioGroup)findViewById(R.id.rd_p_respondedtoIVR);

            // UserID=LoginActivity.getInstance();
            // Toast.makeText(ctx, "UserID"+UserID, Toast.LENGTH_SHORT).show();


            try {
                Intent intent = getIntent();
                ContactNo = intent.getStringExtra("ContactNo");
                boolean mflag = isRegistered(ContactNo);
                setData(ContactNo);
                Log.d("000333", "Set data " + ContactNo);

                if (ContactNo == null) {
                    isFromRegistered = true;

                } else {
                    isFromRegistered = false;
                }


            } catch (Exception e) {
                Log.d("000333", "Exception " + e);
            }

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("");
            //toolbar.setTitleTextColor(Color.WHITE);
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
                    Calendar cal = Calendar.getInstance();

                    year = cal.get(Calendar.YEAR);
                    month = cal.get(Calendar.MONTH);
                    day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            RegisterParticipant.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            dateSetListener,
                            year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });


            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    Log.d("TAG", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                    String date = month + "-" + day + "-" + year;
                    et_pdateOfBirth.setText(date);
                    et_page.setText(getAge(year, month, day));

                }
            };

            btn_pregister = (Button) findViewById(R.id.btn_p_register);
            btn_cancel = (Button) findViewById(R.id.btn_p_cancel);
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            btn_pregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean mflag = isRegistered(et_contactSim.getText().toString().trim());

                    Log.d("000333", "is already " + mflag);
                    boolean isValidatedFlag = isValidated();
                   // Toast.makeText(ctx, ""+isValidatedFlag, Toast.LENGTH_SHORT).show();
                    if (isValidatedFlag == false) {
                       // Toast.makeText(ctx, "Validate false", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (mflag == true) {
                            if (!isFromRegistered) {
                                isPatEligible();
                                updatePatient();
                            } else {
                                Toast.makeText(getApplicationContext(), "Phone number already existed " + et_contactSim.getText().toString(), Toast.LENGTH_LONG).show();
                            }

                        } else {
                            isPatEligible();
                            insertPatient();
                            Log.d("000333", "Insert");
                        }
                    }
                }
            });


//        et_pname.setText("Pat");
//        et_paddress.setText("Karachi");
//        et_altSim.setText("03120031231");
//        et_pdateOfBirth.setText("12/12/12");
//        et_page.setText("6");
//        et_contactSim.setText("03362451199");
//
//        rd_psmartphone_yes.setChecked(true);
//        rd_plives_yes.setChecked(true);
//        rd_participate_yes.setChecked(true);
//        rd_pnotmoving_yes.setChecked(false);
//        rd_pnotmoving_no.setChecked(false);


        }


    private void setData(String contactNo) {

        Lister lister = new Lister(RegisterParticipant.this);

       mData = lister.executeReader("Select * From patient where ContactSim = '" + contactNo + "'");

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

                et_pname.setText(mData[0][1]);
                et_pdateOfBirth.setText(mData[0][2]);
                et_page.setText(mData[0][3]);

                if(mData[0][4].equalsIgnoreCase("Male")){
                    rd_pgender.check(R.id.rd_male);
                }else {
                    rd_pgender.check(R.id.rd_female);
                }
                et_contactSim.setText(mData[0][5]);
                et_altSim.setText(mData[0][6]);
                et_paddress.setText(mData[0][7]);

                if(mData[0][8].equalsIgnoreCase("Yes")){

                    rd_plives.check(R.id.rd_plives_yes);
                }else {
                    rd_plives.check(R.id.rd_plives_no);

                }
                if(mData[0][9].equalsIgnoreCase("Yes")){

                    rd_pnotmoving.check(R.id.rd_p_notmoving_yes);
                }else {
                    rd_pnotmoving.check(R.id.rd_p_notmoving_no);

                }
                if(mData[0][10].equalsIgnoreCase("Yes")){

                    rd_psmartphone.check(R.id.rd_p_smrtph_yes);
                }else {
                    rd_psmartphone.check(R.id.rd_p_smrtph_no);

                }
                if(mData[0][11].equalsIgnoreCase("Yes")){

                    rd_participate.check(R.id.rd_participate_yes);
                }else {
                    rd_participate.check(R.id.rd_participate_no);

                }
                if(mData[0][12].equalsIgnoreCase("Yes")){

                    rd_pconsenttaken.check(R.id.rd_p_consenttaken_yes);
                }else {
                    rd_pconsenttaken.check(R.id.rd_p_consenttaken_no);

                }
                if(mData[0][13].equalsIgnoreCase("Yes")){

                    rd_prespondedIVR.check(R.id.rd_p_respondedtoIVR_yes);
                }else {
                    rd_prespondedIVR.check(R.id.rd_p_respondedtoIVR_no);

                }
                if(mData[0][14].equalsIgnoreCase("Yes")){

                    rd_prespondedSMS.check(R.id.rd_p_respondedtoSMS_yes);
                }else {
                    rd_prespondedSMS.check(R.id.rd_p_respondedtoSMS_no);

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
        regxStr= "^[0][3][\\d]{2}[\\d]{7}$";
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
        }
        else if (!TextUtils.isEmpty(pAltSim) && pAltSim.matches(regxStr)==false){
                Toast.makeText(this, "Invalid Alternate Sim Number", Toast.LENGTH_SHORT).show();
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
        else if(rd_prespondedIVR.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please Select One Option", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(rd_prespondedSMS.getCheckedRadioButtonId() == -1){
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
            pContactSim = et_contactSim.getText().toString().trim();
            pAltSim = et_altSim.getText().toString();


            if(rd_pgender.getCheckedRadioButtonId() != -1) {
                radiovalueGender = (RadioButton) this.findViewById(rd_pgender.getCheckedRadioButtonId());
                pgender = radiovalueGender.getText().toString();
            }

            if(rd_plives.getCheckedRadioButtonId() != -1) {
                radiovalueLives = (RadioButton) this.findViewById(rd_plives.getCheckedRadioButtonId());
                pLivesInMalir = radiovalueLives.getText().toString();
            }

            if(rd_participate.getCheckedRadioButtonId() != -1) {
                radiovalueParticipate = (RadioButton) this.findViewById(rd_participate.getCheckedRadioButtonId());
                participate = radiovalueParticipate.getText().toString();
            }

        if(rd_pnotmoving.getCheckedRadioButtonId() != -1) {
                radiovalueNotmoving = (RadioButton) this.findViewById(rd_pnotmoving.getCheckedRadioButtonId());
                pNotMoving = radiovalueNotmoving.getText().toString();
            }

        if(rd_psmartphone.getCheckedRadioButtonId() != -1) {
                radiovalueSmartphone = (RadioButton) this.findViewById(rd_psmartphone.getCheckedRadioButtonId());
                pHaveSmartphone = radiovalueSmartphone.getText().toString();
            }

        if(rd_pconsenttaken.getCheckedRadioButtonId() != -1) {
                radiovalueConsentTaken = (RadioButton) this.findViewById(rd_pconsenttaken.getCheckedRadioButtonId());
                pConsentTaken = radiovalueConsentTaken.getText().toString();
            }

        if(rd_prespondedIVR.getCheckedRadioButtonId() != -1) {
                radioValueRespondedIVR = (RadioButton) this.findViewById(rd_prespondedIVR.getCheckedRadioButtonId());
                pRespondedIVR = radioValueRespondedIVR.getText().toString();
            }

        if(rd_prespondedSMS.getCheckedRadioButtonId() != -1) {
                radioValueRespondedSMS = (RadioButton) this.findViewById(rd_prespondedSMS.getCheckedRadioButtonId());
                pRespondedSMS = radioValueRespondedSMS.getText().toString();
            }

        if(pAltSim.equals("")){
                pAltSim="";
            }
            pReason = "0";
            Tool1 = "0";
        Tool2 = "0";
        Tool3 = "0";
        Tool4 = "0";
        Tool5 = "0";
        Tool6a = "0";
        Tool7 = "0";

        c = mDatabaseHelperRP.ContactNo(pContactSim);
        if(c == true){
            Toast.makeText(getApplicationContext(), "Phone number already existed " +pContactSim, Toast.LENGTH_LONG).show();
            return;
        } else {
            boolean isInserted = mDatabaseHelperRP.addData(UserID, pName, pDob, pAge, pgender, pContactSim, pAltSim, pAddress, pLivesInMalir,
                    pNotMoving, pHaveSmartphone, participate, pConsentTaken, pRespondedIVR, pRespondedSMS, pReason, Tool1, Tool2, Tool3, Tool4, Tool5, Tool6a, Tool7,
                    Enroll,syncPatient, syncTool1,syncTool2,syncTool3,syncTool4,syncTool5,syncTool6,syncTool7,synctele,syncSummary);
            if (mFlagReasonable) {
            } else {
                Intent intent = new Intent(ctx, MainActivity.class);
                intent.putExtra("ContactNo", pContactSim);
                startActivity(intent);

//            finish();
            }

            Log.d("111", "tool 1: " + Tool1);
            if (isInserted == true) {
                //  Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
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
        Log.d("000222", "==pAddress==" + pAddress);
        Log.d("000222", "=====et_paddress====" + et_paddress);


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

        radioValueRespondedIVR = (RadioButton) this.findViewById(rd_prespondedIVR.getCheckedRadioButtonId());
        pRespondedIVR = radioValueRespondedIVR.getText().toString();

        radioValueRespondedSMS = (RadioButton) this.findViewById(rd_prespondedSMS.getCheckedRadioButtonId());
        pRespondedSMS = radioValueRespondedSMS.getText().toString();

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
                "UserID = '" + UserID + "' , " +
                "Name = '" + pName + "' , " +
                "Dob = '" + pDob + "' , " +
                "Age = '" + pAge + "' , " +
                "Gender = '" + pgender + "' , " +
                "AlternateSim = '" + pAltSim + "' , " +
                "Address = '" + pAddress + "' , " +
                "LivesInMalir = '" + pLivesInMalir + "' , " +
                "NotMovingFor6Months = '" + pNotMoving + "' , " +
                "SmartPhone = '" + pHaveSmartphone + "' , " +
                "ParticipateFOR6Months = '" + participate + "' , " +
                "InformedConsentTaken = '" + pConsentTaken + "' , " +
                "RespondedToIVR = '" + pRespondedIVR + "' , " +
                "RespondedToSMS = '" + pRespondedSMS + "' , " +
                "Reason = '" + pReason+"'  " +
                " where ContactSim = '" + pContactSim + "'" +
                " ");


        if (isUpdate) {
            //   Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
            // Intent intent=new Intent(RegisterParticipant.this, ShowIndividualPartiData.class);
            // intent.putExtra("ContactNo", pContactSim );
            //startActivity(intent);

           // Intent intentt = new Intent(ctx, MainActivity.class);
            //intentt.putExtra("ContactNo", pContactSim);
            //startActivity(intentt);

            if (mFlagReasonable) {
            } else {
                Intent intent = new Intent(ctx, ShowIndividualPartiData.class);
                intent.putExtra("ContactNo", pContactSim);
                startActivity(intent);

//            finish();
            }

            Lister lister = new Lister(RegisterParticipant.this);

            mData = lister.executeReader("Select * From patient where ContactSim = '" + pContactSim + "'");

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
            } catch (Exception e) {


            }

        } else {
            Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
            // Intent intent=new Intent(RegisterParticipant.this, MainActivity.class);
            //startActivity(intent);
            finish();
        }
    }

    private void isPatEligible() {
        Log.d("000000", "isPatEligible");
        if (rd_plives_yes.isChecked() == true && rd_pnotmoving_no.isChecked() == true && rd_participate_yes.isChecked() == true  && rd_pconsenttaken_yes.isChecked() == true) {
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
            pReason="0";
//            addData();
           /* Lister ls = new Lister(ctx);
            boolean isUpdate = ls.executeNonQuery("UPDATE patient set " +
                    "Enroll = '" + Enroll + "' , " +
                    "Tool1 = '" + 0 + "' , " +
                    "Tool2 = '" + 0 + "' , " +
                    "Tool3 = '" + 0 + "' , " +
                    "Tool4 = '" + 0 + "' , " +
                    "Tool5 = '" + 0 + "' , " +
                    "Tool6a = '" + 0 + "' , " +
                    "Tool7 = '" + 0 + "' , " +
                    "Reason = '" +pReason+ "' "+
                    " where ContactSim = '" + pContactSim + "'" +
                    " ");
            Log.d("abcdef", "isPatEligible: "+Enroll);
            Log.d("abcdef", "isPatEligible: "+pReason);*/
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("ContactNo", pContactSim);
            startActivity(intent);
         //   mFlagReasonable = false;

        } else {
            Toast.makeText(this, "This Participant is not Eligible", Toast.LENGTH_SHORT).show();
            mFlagReasonable = true;
            dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            dialogView = inflater.inflate(R.layout.alert_dialog, null);

            btn_submitReason=(Button)dialogView.findViewById(R.id.add_reason);
            et_reason = (EditText) dialogView.findViewById(R.id.reason);
            InputFilter filter = new InputFilter() {
                public CharSequence filter(CharSequence src, int start, int end,
                                           Spanned d, int dstart, int dend) {
                    String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.:-'';§£¥...";
                    for (int i = start; i < end; i++) {
                        int type = Character.getType(src.charAt(i));
                        if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL || type == Character.MATH_SYMBOL || specialChars.contains("" + src) || Character.isWhitespace(0)) {
                            return "";
                        }
                    }
                    return null;
                }
            };

            et_reason.setFilters(new InputFilter[]{filter});


            btn_submitReason.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Enroll = "0";
                    pReason = et_reason.getText().toString();
                    Tool1 = "1";
                    Tool2 = "1" ;
                    Tool3 = "1";
                    Tool4 = "1";
                    Tool5 = "1";
                    Tool6a = "1";
                    Tool6b = "1";
                    Tool7 = "1";

                    Lister ls = new Lister(ctx);
                    boolean isUpdate = ls.executeNonQuery("UPDATE patient set " +
                            "Enroll = '" + Enroll + "' , " +
                            "Tool1 = '" + 1 + "' , " +
                            "Tool2 = '" + 1 + "' , " +
                            "Tool3 = '" + 1 + "' , " +
                            "Tool4 = '" + 1 + "' , " +
                            "Tool5 = '" + 1 + "' , " +
                            "Tool6a = '" + 1 + "' , " +
                            "Tool7 = '" + 1 + "' , " +
                            "Reason = '" + pReason + "'  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");
/*
                    boolean isUpdateTool1 = ls.executeNonQuery("UPDATE tool1 set " +
                            "tool1_sync = 1  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");
                    boolean isUpdateTool2 = ls.executeNonQuery("UPDATE tool2 set " +
                            "tool2_sync = 1  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");
                    boolean isUpdateTool3 = ls.executeNonQuery("UPDATE tool3 set " +
                            "tool3_sync = 1  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");
                    boolean isUpdateTool4 = ls.executeNonQuery("UPDATE tool4 set " +
                            "tool4_sync = 1  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");
                    boolean isUpdateTool5 = ls.executeNonQuery("UPDATE tool5 set " +
                            "tool5_sync = 1  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");
                    boolean isUpdateTool6a = ls.executeNonQuery("UPDATE tool6a set " +
                            "tool6a_sync = 1  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");

                    boolean isUpdateTool7 = ls.executeNonQuery("UPDATE tool7 set " +
                            "tool7_sync = 1  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");
                    boolean isUpdateTele = ls.executeNonQuery("UPDATE teleconsultation set " +
                            "teleconsultation_sync = 1  " +
                            " where ContactSim = '" + pContactSim + "'" +
                            " ");*/

                    if (isUpdate) {
                      //  Toast.makeText(RegisterParticipant.this, "Reason Inserted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ctx, MainActivity.class);
                        intent.putExtra("ContactNo", pContactSim);
                        startActivity(intent);
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
        lister.createAndOpenDB();

        String[][] mData = lister.executeReader("Select *from patient where ContactSim = '" + mPhone + "'");

        try {

            if (mData.length > 0){
                return  true;

            }else {
                return false;
            }

        }

        catch (Exception e) {
            Log.d("000333", e.getMessage());
            return false;
        }


    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.:-'';§£¥...";
        for (int i = start; i < end; i++) {
            int type = Character.getType(source.charAt(i));
            if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL || type == Character.MATH_SYMBOL || specialChars.contains("" + source) || Character.isWhitespace(0)) {
                return "";
            }
        }
        return null;
    }

}
