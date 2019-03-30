package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class RegisterParticipant extends AppCompatActivity {

    private Button btn_pregister, btn_submitReason;
    private EditText et_pname, et_paddress, et_page, et_StudyID, et_contactSim, et_altSim, et_reason;
    public String pName, pAddress, pAge, pStudyID, pContactSim, pAltSim, pLivesInMalir, pHaveSmartphone, participate, pNotMoving, pgender, pConsentTaken, pReason;
    private RadioGroup rd_pgender, rd_plives, rd_psmartphone, rd_pnotmoving, rd_participate, rd_pconsenttaken;
    private RadioButton rd_pgender_male, rd_pgender_female, rd_plives_yes, rd_plives_no, rd_psmartphone_yes, rd_psmartphone_no, rd_pnotmoving_yes, rd_pnotmoving_no, rd_participate_yes, rd_participate_no, rd_pconsenttaken_yes, rd_pconsenttaken_no;
    private RadioButton radiovalueGender, radiovalueLives, radiovalueNotmoving, radiovalueParticipate, radiovalueSmartphone, radiovalueConsentTaken;
    private Switch syncData;
    private DatabaseHelperRP mDatabaseHelperRP;
    private boolean switchState;
    View dialogView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_participant);

        et_pname = (EditText) findViewById(R.id.ed_pname);
        et_paddress = (EditText) findViewById(R.id.ed_paddress);
        et_page = (EditText) findViewById(R.id.ed_page);
        et_StudyID = (EditText) findViewById(R.id.ed_studyID);
        et_contactSim = (EditText) findViewById(R.id.ed_pcontact);
        et_altSim = (EditText) findViewById(R.id.ed_p_altsim);
//        et_reason = (EditText) dialogView.findViewById(R.id.reason);

        rd_psmartphone_yes = (RadioButton) findViewById(R.id.rd_p_smrtph_yes);
        rd_plives_yes = (RadioButton) findViewById(R.id.rd_plives_yes);
        rd_participate_yes = (RadioButton) findViewById(R.id.rd_participate_yes);
        rd_pnotmoving_yes = (RadioButton) findViewById(R.id.rd_p_notmoving_yes);

        rd_pgender=(RadioGroup)findViewById(R.id.rd_pgender);
        rd_plives=(RadioGroup)findViewById(R.id.rd_plives);
        rd_pnotmoving=(RadioGroup)findViewById(R.id.rd_p_notmoving);
        rd_pconsenttaken=(RadioGroup)findViewById(R.id.rd_p_consenttaken);
        rd_psmartphone=(RadioGroup)findViewById(R.id.rd_p_smrtph);
        rd_participate=(RadioGroup)findViewById(R.id.rd_participate);

        syncData = (Switch) findViewById(R.id.syncData);

        mDatabaseHelperRP = new DatabaseHelperRP(this);

        btn_pregister = (Button) findViewById(R.id.btn_p_register);
        btn_pregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
                validateParticipant();
                //Intent intent = new Intent(RegisterParticipant.this, Modules.class);
                // startActivity(intent);
            }
        });
    }

    private void validateParticipant() {

        pName = et_pname.getText().toString();
        pStudyID = et_StudyID.getText().toString();
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


        if (TextUtils.isEmpty(pName)) {
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pStudyID)) {
            Toast.makeText(this, "Please Enter Study ID", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(pAge)) {
            Toast.makeText(this, "Please Enter Age", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(pAddress)) {
            Toast.makeText(this, "Please Enter Address", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(pContactSim)) {
            Toast.makeText(this, "Please Enter Contact Sim Number", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(pAltSim)) {
            Toast.makeText(this, "Please Enter Alternate Sim Number", Toast.LENGTH_SHORT).show();

        } else {
            checkData();
        }
    }

    private void checkData() {
        if (rd_plives_yes.isChecked() == true && rd_pnotmoving_yes.isChecked() == true && rd_participate_yes.isChecked() == true && rd_psmartphone_yes.isChecked() == true) {
            Toast.makeText(this, "This participant is Eligible", Toast.LENGTH_SHORT).show();
            addData();
            Intent intent = new Intent(RegisterParticipant.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "This Participant is not Eligible", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            dialogView = inflater.inflate(R.layout.alert_dialog, null);

            et_reason=(EditText)dialogView.findViewById(R.id.reason);
            pReason=et_reason.getText().toString();

            btn_submitReason=(Button)dialogView.findViewById(R.id.add_reason);
            btn_submitReason.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addDataWithReason();
                }
            });
            dialogBuilder.setView(dialogView);
            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();

        }

    }

    private void addData() {
        pName = et_pname.getText().toString();
        pStudyID = et_StudyID.getText().toString();
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

        pReason="null";


        boolean isInserted = mDatabaseHelperRP.addData(pName, pStudyID, pAge, pgender, pContactSim, pAltSim, pAddress, pLivesInMalir,
                pNotMoving, pHaveSmartphone, participate, pConsentTaken,pReason);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }


    private void addDataWithReason() {
        pName = et_pname.getText().toString();
        pStudyID = et_StudyID.getText().toString();
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


        boolean isInserted = mDatabaseHelperRP.addData(pName, pStudyID, pAge, pgender, pContactSim, pAltSim, pAddress, pLivesInMalir,
                pNotMoving, pHaveSmartphone, participate, pConsentTaken, pReason);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}