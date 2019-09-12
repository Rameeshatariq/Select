package com.example.cv.select;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.cv.select.RegisterParticipant.hideSoftKeyboard;

public class Teleconsulation extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText selectDate, time;
    private Button saveTeleconsultation;
    Context ctx = this;
    String selectedTime, sekectedDate, ContactNo;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    DatabaseHelperRP databaseHelperRP;
    Lister ls;
    int teleconsultation_syncData = 0;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleconsulation);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        // toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseHelperRP=new DatabaseHelperRP(this);
        ls = new Lister(ctx);

        final Intent intent = getIntent();
        ContactNo = intent.getStringExtra("ContactNo");


        selectDate = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Teleconsulation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }

                        time.setText(String.format("%02d:%02d", hourOfDay, minutes) +" "+ amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });
        saveTeleconsultation=(Button)findViewById(R.id.saveTeleconsultation);
        saveTeleconsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeleconsultation();
                finish();
            }
        });


        try {
            boolean mflag= isCompleted(ContactNo);

            setData(ContactNo);

            if(mflag == true){
                // Toast.makeText(this, "Tool1 Completed", Toast.LENGTH_SHORT).show();
            }
            else{
                // Toast.makeText(this, "Tool1 not Completed", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
        }

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard(Teleconsulation.this);
                Calendar cal = Calendar.getInstance();

                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Teleconsulation.this,
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
                selectDate.setText(date);
            }

        };
    }
    private void addTeleconsultation(){
        sekectedDate=selectDate.getText().toString();
        selectedTime=time.getText().toString();

        try{
            Log.d("000333", "save and exit");

            String [][] mData = ls.executeReader("Select *from teleconsultation where ContactSim  = '"+ContactNo+"'");

            if(mData != null){
              boolean  isInserted = ls.executeNonQuery("Update teleconsultation set " +
                        "date = '"+sekectedDate+"', " +
                        "time = '"+selectedTime+"' " +
                        " where ContactSim  = '"+ContactNo+"'");
                if (isInserted == true) {
                    // Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Teleconsultation Added", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Teleconsulation.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    //Toast.makeText(this, "Data Not Updated Successfully", Toast.LENGTH_SHORT).show();
                }

            }else {

                boolean isInserted = databaseHelperRP.addTeleconsualtation(ContactNo, sekectedDate, selectedTime, teleconsultation_syncData);
                if (isInserted == true) {
                    Toast.makeText(this, "Teleconsultation Added", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Teleconsulation.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){

            Toast.makeText(ctx, "=-=-=-=Exception  "+e, Toast.LENGTH_SHORT).show();
            Log.e("000333", "Exception "+e);
        }

    }

    public boolean isCompleted(String mPhone) {

        Lister lister = new Lister(Teleconsulation.this);

        String[][] mData = lister.executeReader("Select * From tool1 where ContactSim = '" + mPhone + "'");

        try {

            if (mData.length > 0){
                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);
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

        Lister lister = new Lister(Teleconsulation.this);

        String[][] mData = lister.executeReader("Select * From teleconsultation where ContactSim = '" + contactNo + "'");

        try {

            if (mData.length > 0) {

                Log.d("000111", "mData[0][1] =  " + mData[0][1]);
                Log.d("000111", "mData[0][2] =  " + mData[0][2]);


               selectDate.setText(mData[0][1]);
               time.setText(mData[0][2]);

            } else {

            }

        } catch (Exception e) {
            Log.d("111", e.getMessage());

        }
    }

}
