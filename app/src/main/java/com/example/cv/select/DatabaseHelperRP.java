package com.example.cv.select;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperRP extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "select.db";
    public static File Database_Path = null;

    public static final String TABLE_NAME = "patient";
    private static final String COL1 = "UserID";
    private static final String COL2 = "Name";
    private static final String COL3 = "Dob";
    private static final String COL4 = "Age";
    private static final String COL5 = "Gender";
    private static final String COL6 = "ContactSim";
    private static final String COL7 = "AlternateSim";
    private static final String COL8 = "Address";
    private static final String COL9 = "LivesInMalir";
    private static final String COL10 = "NotMovingFor6Months";
    private static final String COL11 = "SmartPhone";
    private static final String COL12 = "ParticipateFor6Months";
    private static final String COL13 = "InformedConsentTaken";
    private static final String COL25 = "RespondedToIVR";
    private static final String COL26 = "RespondedToSMS";
    private static final String COL14 = "Reason";
    private static final String COL15 = "Tool1";
    private static final String COL16 = "Tool2";
    private static final String COL17 = "Tool3";
    private static final String COL18 = "Tool4";
    private static final String COL19 = "Tool5";
    private static final String COL20 = "Tool6a";
    private static final String COL22 = "Tool7";
    private static final String COL23 = "Enroll";
    private static final String COL24 = "SyncData";


    public static final String TABLE_NAME_2 = "tool1";
    private static final String TOOL1_Q1 = "tool1_Q1";
    private static final String TOOL1_Q2 = "tool1_Q2";
    private static final String TOOL1_Q3 = "tool1_Q3";
    private static final String TOOL1_Q4 = "tool1_Q4";
    private static final String TOOL1_Q5 = "tool1_Q5";
    private static final String TOOL1_Q6 = "tool1_Q6";
    private static final String TOOL1_Q7 = "tool1_Q7";
    private static final String TOOL1_Q8 = "tool1_Q8";
    private static final String TOOL1_SyncData = "tool1_syncData";
    private static final String CVAEVENT = "cvaEvent";

    public static final String TABLE_NAME_3 = "tool2";
    private static final String TOOL2_Q1 = "tool2_Q1";
    private static final String TOOL2_Q2 = "tool2_Q2";
    private static final String TOOL2_Q3 = "tool2_Q3";
    private static final String TOOL2_Result= "result";
    private static final String TOOL2_SyncData = "tool2_syncData";

    public static final String TABLE_NAME_4 = "tool3";
    private static final String TOOL3_Q1_Diabetic = "Diabetic";
    private static final String TOOL3_Q1_Hypertension = "Hypertension";
    private static final String TOOL3_Diabetic_ControlBy_Medicines = "DiabeticControlByMedicines";
    private static final String TOOL3_Diabetic_ControlBy_Insulin = "DiabeticControlByInsulin";
    private static final String TOOL3_Diabetic_ControlBy_Diet = "DiabeticControlByDiet";
    private static final String TOOL3_Diabetic_ControlBy_PNRMed = "DiabeticControlByPNRMedication";
    private static final String TOOL3_Diabetic_ControlBy_AlternateMed = "DiabeticControlByAlternateMedication";
    private static final String TOOL3_Hypertension_ControlBy_NotMed = "HypertensionNotControlByMedicines";
    private static final String TOOL3_Hypertension_ControlBy_Medicines = "HypertensionControlByMedicines";
    private static final String TOOL3_Hypertension_ControlBy_DietOrMed = "HypertensionControlByDietOrMedicines";
    private static final String TOOL3_Hypertension_ControlBy_PnrMed = "HypertensionNotControlByPNRMedication";
    private static final String TOOL3_Hypertension_ControlBy_AlternateMed = "HypertensionControlByAlternateMedication";
    private static final String TOOL3_SyncData = "tool3_syncData";


    public static final String TABLE_NAME_5 = "tool4";
    private static final String Tool4_Q1 = "tool4_Q1";
    private static final String Tool4_RESULT = "result";
    private static final String TOOL4_SyncData = "tool4_syncData";


    public static final String TABLE_NAME_6 = "tool5";
    private static final String TOOL5_Q1 = "VigourousExercise";
    private static final String TOOL5_Q1_days = "DaysOfVigourous";
    private static final String TOOL5_Q2_hours = "HoursOfVigorous";
    private static final String TOOL5_Q2_mins = "MinsOfVigorous";
    private static final String TOOL5_Q3 = "ModerateExercise";
    private static final String TOOL5_Q3_days = "DaysOfModerate";
    private static final String TOOL5_Q4_hours = "HoursOfModerate";
    private static final String TOOL5_Q4_mins = "MinsOfModerate";
    private static final String TOOL5_Q5 = "Walk";
    private static final String TOOL5_Q5_days = "DaysOfWalk";
    private static final String TOOL5_Q6_hours = "HoursOfWalk";
    private static final String TOOL5_Q6_mins = "MinsOfWalk";
    private static final String RESULT = "result";
    private static final String TOOL5_SyncData = "tool5_syncData";


    public static final String TABLE_NAME_7 = "tool6a";
    private static final String Tool6a_Q1 = "tool6a_Q1";
    private static final String Tool6a_Q2 = "tool6a_Q2";
    private static final String Tool6a_Q3 = "tool6a_Q3";
    private static final String Tool6a_Q4 = "tool6a_Q4";
    private static final String TOOL6a_SyncData = "tool6a_syncData";

   /* public static final String TABLE_NAME_8 = "tool6b";
    private static final String Tool6b_Q1 = "tool6b_Q1";
    private static final String Tool6b_Q2 = "tool6b_Q2";
    private static final String TOOL6b_SyncData = "tool6b_syncData";*/

    public static final String TABLE_NAME_9 = "tool7";
    private static final String Tool7_Q1 = "tool7_Q1";
    private static final String Tool7_Q2 = "tool7_Q2";
    private static final String Tool7_Q3 = "tool7_Q3";
    private static final String Tool7_Q4 = "tool7_Q4";
    private static final String Tool7_Q5 = "tool7_Q5";
    private static final String Tool7_Q6 = "tool7_Q6";
    private static final String Tool7_Q7 = "tool7_Q7";
    private static final String Tool7_Result = "result";
    private static final String TOOL7_SyncData = "tool7_syncData";

    public static final String TABLE_NAME_10 = "summary";
    private static final String Tool1 = "tool1";
    private static final String Tool2 = "tool2";
    private static final String Tool3 = "tool3";
    private static final String Tool7 = "tool7";

    public static final String TABLE_NAME_11 = "teleconsultation";
    private static final String Date = "date";
    private static final String Time = "time";

    public DatabaseHelperRP(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Database_Path = context.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                //"ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "UserID VARCHAR(10) NULL, " +
                "Name VARCHAR(100) NULL, " +
                "Dob VARCHAR(20) NULL, " +
                "Age VARCHAR(5) NULL, " +
                "Gender VARCHAR(10) NULL, " +
                "ContactSim VARCHAR(20) PRIMARY KEY UNIQUE NOT NULL, " +
                "AlternateSim VARCHAR(20) NULL, " +
                "Address VARCHAR(100) NULL, " +
                "LivesInMalir VARCHAR(10) NULL, " +
                "NotMovingFor6Months VARCHAR(10) NULL, " +
                "Smartphone VARCHAR(10) NULL, " +
                "ParticipateFOR6Months VARCHAR(10) NULL, " +
                "InformedConsentTaken VARCHAR(10) NULL, " +
                "RespondedToIVR VARCHAR(10) NULL, " +
                "RespondedToSMS VARCHAR(10) NULL, " +
                "Reason VARCHAR(200) NULL, " +
                "Tool1 INTEGER(1) NULL, " +
                "Tool2 INTEGER(1) NULL, " +
                "Tool3 INTEGER(1) NULL, " +
                "Tool4 INTEGER(1) NULL, " +
                "Tool5 INTEGER(1) NULL, " +
                "Tool6a INTEGER(1) NULL, " +
                "Tool7 INTEGER(1) NULL, " +
                "Enroll INTEGER(1) NULL, " +
                "SyncData INTEGER(1) NULL " +
                " )";
        db.execSQL(createTable);

        String createTable2 = "CREATE TABLE " + TABLE_NAME_2 + " (" +
                COL6 + " VARCHAR(20), " +
                "tool1_Q1 VARCHAR(10) NULL, " +
                "tool1_Q2 VARCHAR(10) NULL, " +
                "tool1_Q3 VARCHAR(10) NULL, " +
                "tool1_Q4 VARCHAR(10) NULL, " +
                "tool1_Q5 VARCHAR(10) NULL, " +
                "tool1_Q6 VARCHAR(10) NULL, " +
                "tool1_Q7 VARCHAR(10) NULL, " +
                "tool1_Q8 VARCHAR(10) NULL, " +
                "cvaEvent VARCHAR(12) NULL, "+
                "tool1_syncData VARCHAR(2) NULL, "+
                " FOREIGN KEY (" + COL6 + ") REFERENCES  " + TABLE_NAME + "(" + COL6 + "))";
        db.execSQL(createTable2);

        String createTable3 = "CREATE TABLE " + TABLE_NAME_3 + " ("
                + COL6 + " VARCHAR(20), " +
                "tool2_Q1 VARCHAR(10) NULL, " +
                "tool2_Q2 VARCHAR(10) NULL, " +
                "tool2_Q3 VARCHAR(10) NULL, " +
                "result VARCHAR(100) NULL, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable3);

        String createTable4 = "CREATE TABLE " + TABLE_NAME_4 + " ( "
                + COL6 + " VARCHAR(20), " +
                "Diabetic VARCHAR(10) NULL," +
                TOOL3_Diabetic_ControlBy_Medicines + " VARCHAR(10) NULL, " +
                TOOL3_Diabetic_ControlBy_Insulin + " VARCHAR(10) NULL, " +
                TOOL3_Diabetic_ControlBy_Diet + " VARCHAR(10) NULL, " +
                TOOL3_Diabetic_ControlBy_PNRMed + " VARCHAR(10) NULL, " +
                TOOL3_Diabetic_ControlBy_AlternateMed + " VARCHAR(30) NULL, " +
                "Hypertension VARCHAR(10) NULL, " +
                TOOL3_Hypertension_ControlBy_NotMed + " VARCHAR(10) NULL, " +
                TOOL3_Hypertension_ControlBy_Medicines + " VARCHAR(10) NULL, " +
                TOOL3_Hypertension_ControlBy_DietOrMed + " VARCHAR(10) NULL, "
                + TOOL3_Hypertension_ControlBy_PnrMed + " VARCHAR(10) NULL, " +
                TOOL3_Hypertension_ControlBy_AlternateMed + " VARCHAR(10) NULL, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable4);

        String createTable5 = "CREATE TABLE " + TABLE_NAME_5 + " ("
                + COL6 + " VARCHAR(20), " +
                "tool4_Q1 VARCHAR(20) NULL, " +
                "result VARCHAR(20) NULL, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable5);

        String createTable6 = "CREATE TABLE " + TABLE_NAME_6 + " ("
                + COL6 + " VARCHAR(20), " +
                "VigourousExercise VARCHAR(30) NULL, " +
                "DaysOfVigourous VARCHAR(10) NULL, " +
                "HoursOfVigorous VARCHAR(10) NULL, " +
                "MinsOfVigorous VARCHAR(10) NULL, " +
                "ModerateExercise VARCHAR(30) NULL, " +
                "DaysOfModerate VARCHAR(10) NULL, " +
                "HoursOfModerate VARCHAR(10) NULL, " +
                "MinsOfModerate VARCHAR(10) NULL, " +
                "Walk VARCHAR(30) NULL, " +
                "DaysOfWalk VARCHAR(10) NULL, " +
                "HoursOfWalk VARCHAR(10) NULL, " +
                "MinsOfWalk VARCHAR(10) NULL, " +
                "result VARCHAR(100) NULL, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable6);

        String createTable7 = "CREATE TABLE " + TABLE_NAME_7 + " ("
                + COL6 + " VARCHAR(20), " +
                "tool6a_Q1 VARCHAR(30) NULL, " +
                "tool6a_Q2 VARCHAR(40) NULL, " +
                "tool6a_Q3 VARCHAR(40) NULL, " +
                "tool6a_Q4 VARCHAR(40) NULL, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable7);

        /*String createTable8 = "CREATE TABLE " + TABLE_NAME_8 + " ("
                + COL6 + " VARCHAR(20), " +
                "tool6b_Q1 VARCHAR(30) NULL, " +
                "tool6b_Q2 VARCHAR(30) NULL, " +
                "tool6b_syncData VARCHAR(10) NULL, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable8);*/

        String createTable9 = "CREATE TABLE " + TABLE_NAME_9 + " (" +
                COL6 + " VARCHAR(20), " +
                "tool7_Q1 VARCHAR(10) NULL, " +
                "tool7_Q2 VARCHAR(10) NULL, " +
                "tool7_Q3 VARCHAR(10) NULL, " +
                "tool7_Q4 VARCHAR(10) NULL, " +
                "tool7_Q5 VARCHAR(10) NULL, " +
                "tool7_Q6 VARCHAR(10) NULL, " +
                "tool7_Q7 VARCHAR(10) NULL, " +
                "result VARCHAR(300) NULL, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES  " + TABLE_NAME + "(" + COL6 + "))";
        db.execSQL(createTable9);

        String createTable10 = "CREATE TABLE " + TABLE_NAME_10 + " (" +
                COL6 + " VARCHAR(20), " +
                "tool1 VARCHAR(20) NULL, " +
                "tool2 VARCHAR(50) NULL, " +
                "tool3 VARCHAR(30) NULL, " +
                "tool7 VARCHAR(100) NULL, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES  " + TABLE_NAME + "(" + COL6 + "))";
        db.execSQL(createTable10);

        String createTable11 = "CREATE TABLE " + TABLE_NAME_11 + " (" +
                COL6 + " VARCHAR(20), " +
                "date VARCHAR(30) NULL, " +
                "time VARCHAR(30) NULL, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES  " + TABLE_NAME + "(" + COL6 + "))";
        db.execSQL(createTable11);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);
    }

    public boolean addData(String userID, String name, String dob, String age, String gender, String contactSim, String altSim, String address, String livesInMalir, String notMoving,
                           String smartphone, String participate, String informedconsent, String respondedIVR, String respondedSMS, String reason, String Tool1, String Tool2,
                           String Tool3, String Tool4, String Tool5, String Tool6a, String Tool7, String Enroll, int sync) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, userID);
        contentValues.put(COL2, name);
        contentValues.put(COL3, dob);
        contentValues.put(COL4, age);
        contentValues.put(COL5, gender);
        contentValues.put(COL6, contactSim);
        contentValues.put(COL7, altSim);
        contentValues.put(COL8, address);
        contentValues.put(COL9, livesInMalir);
        contentValues.put(COL10, notMoving);
        contentValues.put(COL11, smartphone);
        contentValues.put(COL12, participate);
        contentValues.put(COL13, informedconsent);
        contentValues.put(COL25, respondedIVR);
        contentValues.put(COL26, respondedSMS);
        contentValues.put(COL14, reason);
        contentValues.put(COL15, Tool1);
        contentValues.put(COL16, Tool2);
        contentValues.put(COL17, Tool3);
        contentValues.put(COL18, Tool4);
        contentValues.put(COL19, Tool5);
        contentValues.put(COL20, Tool6a);
        contentValues.put(COL22, Tool7);
        contentValues.put(COL23, Enroll);
        contentValues.put(COL24, sync);

        Log.d(TAG, "addData: Adding" + name + age + gender + contactSim + altSim + address + livesInMalir + notMoving + smartphone + participate + informedconsent +
                reason + "to" + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool1Data(String ContactNo, String Q1, String Q2, String Q3, String Q4, String Q5, String Q6, String Q7, String Q8, String CVAEvent, int syncData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL1_Q1, Q1);
        contentValues.put(TOOL1_Q2, Q2);
        contentValues.put(TOOL1_Q3, Q3);
        contentValues.put(TOOL1_Q4, Q4);
        contentValues.put(TOOL1_Q5, Q5);
        contentValues.put(TOOL1_Q6, Q6);
        contentValues.put(TOOL1_Q7, Q7);
        contentValues.put(TOOL1_Q8, Q8);
        contentValues.put(CVAEVENT, CVAEvent);
        contentValues.put(TOOL1_SyncData, syncData);

        Log.d(TAG, "addTool1Data: Adding" + ContactNo + Q1 + Q2 + Q3 + Q4 + Q5 + Q6 + Q7 + Q8 + "to" + TABLE_NAME_2);
        long result1 = db.insert(TABLE_NAME_2, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool2Data(String ContactNo, String tool1bQ1, String tool1bQ2, String tool1bQ3, String result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL2_Q1, tool1bQ1);
        contentValues.put(TOOL2_Q2, tool1bQ2);
        contentValues.put(TOOL2_Q3, tool1bQ3);
        contentValues.put(TOOL2_Result, result);


        Log.d(TAG, "addTool2Data: Adding" + tool1bQ1 + tool1bQ2 + tool1bQ3 + "to" + TABLE_NAME_3);
        long result1 = db.insert(TABLE_NAME_3, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool3Data(String ContactNo, String diabetic, String diabeticControlByMed, String diabeticControlByInsulin, String diabeticControlByDiet,
                                String diabeticControlByPnrMed, String diabeticControlByAltMed, String hypertension, String hypertensionControlByNotMed,
                                String hypertensionControlByMed, String hypertensionControlByDietOrMed, String hypertensionControlByPnrMed,
                                String hypertensionControlByAltMed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL3_Q1_Diabetic, diabetic);
        contentValues.put(TOOL3_Diabetic_ControlBy_Medicines, diabeticControlByMed);
        contentValues.put(TOOL3_Diabetic_ControlBy_Insulin, diabeticControlByInsulin);
        contentValues.put(TOOL3_Diabetic_ControlBy_Diet, diabeticControlByDiet);
        contentValues.put(TOOL3_Diabetic_ControlBy_PNRMed, diabeticControlByPnrMed);
        contentValues.put(TOOL3_Diabetic_ControlBy_AlternateMed, diabeticControlByAltMed);
        contentValues.put(TOOL3_Q1_Hypertension, hypertension);
        contentValues.put(TOOL3_Hypertension_ControlBy_NotMed, hypertensionControlByNotMed);
        contentValues.put(TOOL3_Hypertension_ControlBy_Medicines, hypertensionControlByMed);
        contentValues.put(TOOL3_Hypertension_ControlBy_DietOrMed, hypertensionControlByDietOrMed);
        contentValues.put(TOOL3_Hypertension_ControlBy_PnrMed, hypertensionControlByPnrMed);
        contentValues.put(TOOL3_Hypertension_ControlBy_AlternateMed, hypertensionControlByAltMed);

        Log.d(TAG, "addTool2Data: Adding" + COL6 + TOOL3_Q1_Diabetic + TOOL3_Diabetic_ControlBy_Medicines + TOOL3_Hypertension_ControlBy_AlternateMed + "to" + TABLE_NAME_3);
        long result1 = db.insert(TABLE_NAME_4, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool4Data(String ContactNo, String tool4Q1, float score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(Tool4_Q1, tool4Q1);
        contentValues.put(Tool7_Result,score);


        Log.d(TAG, "addTool4Data: Adding" + COL6 + Tool4_Q1 + "to" + TABLE_NAME_5);
        long result1 = db.insert(TABLE_NAME_5, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool5Data(String ContactNo, String vigourous, String vigorousDays, String vigourousHours, String vigourousMins, String moderate, String moderateDays,
                                String moderateHours, String moderateMins, String walk, String walkDays, String walkHours, String walkMins, String result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL5_Q1, vigourous);
        contentValues.put(TOOL5_Q1_days, vigorousDays);
        contentValues.put(TOOL5_Q2_hours, vigourousHours);
        contentValues.put(TOOL5_Q2_mins, vigourousMins);
        contentValues.put(TOOL5_Q3, moderate);
        contentValues.put(TOOL5_Q3_days, moderateDays);
        contentValues.put(TOOL5_Q4_hours, moderateHours);
        contentValues.put(TOOL5_Q4_mins, moderateMins);
        contentValues.put(TOOL5_Q5, walk);
        contentValues.put(TOOL5_Q5_days, walkDays);
        contentValues.put(TOOL5_Q6_hours, walkHours);
        contentValues.put(TOOL5_Q6_mins, walkMins);
        contentValues.put(RESULT, result);

        Log.d(TAG, "addTool5Data: Adding" + ContactNo + vigourous + vigorousDays + vigourousHours + moderate + moderateDays +
                moderateHours + walk + walkDays + walkHours + "to" + TABLE_NAME_6);
        long result1 = db.insert(TABLE_NAME_6, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool6aData(String contactNo, String tool6a_q1, String tool6a_q2, String tool6b_Q1, String tool6b_Q2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Tool6a_Q1, tool6a_q1);
        contentValues.put(Tool6a_Q2, tool6a_q2);
        contentValues.put(Tool6a_Q3,tool6b_Q1);
        contentValues.put(Tool6a_Q4,tool6b_Q2);
        Log.d(TAG, "addTool5Data: Adding" + contactNo + tool6a_q1 + tool6a_q2 + "to" + TABLE_NAME_7);
        long result1 = db.insert(TABLE_NAME_7, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

  /*  public boolean addTool6bData(String contactNo, String tool6b_q1, String tool6b_q2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Tool6b_Q1, tool6b_q1);
        contentValues.put(Tool6b_Q2, tool6b_q2);

        Log.d(TAG, "addTool5Data: Adding" + contactNo + tool6b_q1 + tool6b_q2 + "to" + TABLE_NAME_8);
        long result1 = db.insert(TABLE_NAME_8, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }*/

    public boolean addTool7Data(String contactNo, String tl7_q1, String tl7_q2, int tl7_q3, int tl7_q4, int tl7_q5, int tl7_q6,
                                int tl7_q7, String result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Tool7_Q1, tl7_q1);
        contentValues.put(Tool7_Q2, tl7_q2);
        contentValues.put(Tool7_Q3, tl7_q3);
        contentValues.put(Tool7_Q4, tl7_q4);
        contentValues.put(Tool7_Q5, tl7_q5);
        contentValues.put(Tool7_Q6, tl7_q6);
        contentValues.put(Tool7_Q7, tl7_q7);
        contentValues.put(Tool7_Result, result);

        Log.d(TAG, "addTool7Data: Adding" + contactNo + tl7_q1 + tl7_q2 + "to" + TABLE_NAME_9);
        long result1 = db.insert(TABLE_NAME_9, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTeleconsualtation(String contactNo, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Date, date);
        contentValues.put(Time, time);

        Log.d(TAG, "addTool7Data: Adding" + contactNo + date + time + "to" + TABLE_NAME_11);
        long result1 = db.insert(TABLE_NAME_11, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addToolsSummary(String contactNo, String tl1, String tl2, String tl3, String tl7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Tool1, tl1);
        contentValues.put(Tool2, tl2);
        contentValues.put(Tool3, tl3);
        contentValues.put(Tool7, tl7);


        Log.d(TAG, "addTool7Data: Adding" + contactNo + tl1 + tl2 + tl3 + tl7 + "to" + TABLE_NAME_10);
        long result1 = db.insert(TABLE_NAME_10, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTool1Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_2;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTool2Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_3;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTool3Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_4;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTool4Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_5;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTool5Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_6;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTool6aData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_7;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

  /*  public Cursor getTool6bData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_8;
        Cursor data = db.rawQuery(query, null);
        return data;
    }*/

    public Cursor getTool7Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_9;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPartiData(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPartiTool1Data(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_2
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPartiTool2Data(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_3
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPartiTool3Data(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_4
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPartiTool4Data(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_5
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPartiTool5Data(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_6
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPartiTool6aData(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_7
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

  /*  public Cursor getPartiTool6bData(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_8
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
*/
    public Cursor getPartiTool7Data(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_9
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPartiToolsSummary(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_10
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor syncPatient() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE sync = 0";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean updatePrtiData(String name, String dob, String age, String gender, String ContactSim, String altSim, String address, String livesInMalir, String notMoving,
                                  String smartphone, String participate, String informedconsent, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, dob);
        contentValues.put(COL4, age);
        contentValues.put(COL5, gender);
        contentValues.put(COL7, altSim);
        contentValues.put(COL8, address);
        contentValues.put(COL9, livesInMalir);
        contentValues.put(COL10, notMoving);
        contentValues.put(COL11, smartphone);
        contentValues.put(COL12, participate);
        contentValues.put(COL13, informedconsent);
        contentValues.put(COL14, reason);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactSim});
        return true;
    }

    public boolean updateTool1Data(String ContactNo, String Q1, String Q2, String Q3, String Q4, String Q5, String Q6, String Q7, String Q8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL1_Q1, Q1);
        contentValues.put(TOOL1_Q2, Q2);
        contentValues.put(TOOL1_Q3, Q3);
        contentValues.put(TOOL1_Q4, Q4);
        contentValues.put(TOOL1_Q5, Q5);
        contentValues.put(TOOL1_Q6, Q6);
        contentValues.put(TOOL1_Q7, Q7);
        contentValues.put(TOOL1_Q8, Q8);
        db.update(TABLE_NAME_2, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool2Data(String ContactNo, String tool1bQ1, String tool1bQ2, String tool1bQ3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL2_Q1, tool1bQ1);
        contentValues.put(TOOL2_Q2, tool1bQ2);
        contentValues.put(TOOL2_Q3, tool1bQ3);
        db.update(TABLE_NAME_3, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool3Data(String ContactNo, String diabetic, String diabeticControlByMed, String diabeticControlByInsulin, String diabeticControlByDiet,
                                   String diabeticControlByPnrMed, String diabeticControlByAltMed, String hypertension, String hypertensionControlByNotMed,
                                   String hypertensionControlByMed, String hypertensionControlByDietOrMed, String hypertensionControlByPnrMed,
                                   String hypertensionControlByAltMed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL3_Q1_Diabetic, diabetic);
        contentValues.put(TOOL3_Diabetic_ControlBy_Medicines, diabeticControlByMed);
        contentValues.put(TOOL3_Diabetic_ControlBy_Insulin, diabeticControlByInsulin);
        contentValues.put(TOOL3_Diabetic_ControlBy_Diet, diabeticControlByDiet);
        contentValues.put(TOOL3_Diabetic_ControlBy_PNRMed, diabeticControlByPnrMed);
        contentValues.put(TOOL3_Diabetic_ControlBy_AlternateMed, diabeticControlByAltMed);
        contentValues.put(TOOL3_Q1_Hypertension, hypertension);
        contentValues.put(TOOL3_Hypertension_ControlBy_NotMed, hypertensionControlByNotMed);
        contentValues.put(TOOL3_Hypertension_ControlBy_Medicines, hypertensionControlByMed);
        contentValues.put(TOOL3_Hypertension_ControlBy_DietOrMed, hypertensionControlByDietOrMed);
        contentValues.put(TOOL3_Hypertension_ControlBy_PnrMed, hypertensionControlByPnrMed);
        contentValues.put(TOOL3_Hypertension_ControlBy_AlternateMed, hypertensionControlByAltMed);
        db.update(TABLE_NAME_4, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool4Data(String ContactNo, String tool4Q1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(Tool4_Q1, tool4Q1);
        db.update(TABLE_NAME_5, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool5Data(String ContactNo, String vigourous, String vigorousDays, String vigourousHours, String vigourousMins, String moderate, String moderateDays,
                                   String moderateHours, String moderateMins, String walk, String walkDays, String walkHours, String walkMins){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL5_Q1, vigourous);
        contentValues.put(TOOL5_Q1_days, vigorousDays);
        contentValues.put(TOOL5_Q2_hours, vigourousHours);
        contentValues.put(TOOL5_Q2_mins, vigourousMins);
        contentValues.put(TOOL5_Q3, moderate);
        contentValues.put(TOOL5_Q3_days, moderateDays);
        contentValues.put(TOOL5_Q4_hours, moderateHours);
        contentValues.put(TOOL5_Q4_mins, moderateMins);
        contentValues.put(TOOL5_Q5, walk);
        contentValues.put(TOOL5_Q5_days, walkDays);
        contentValues.put(TOOL5_Q6_hours, walkHours);
        contentValues.put(TOOL5_Q6_mins, walkMins);
        db.update(TABLE_NAME_6, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool6aData(String contactNo, String tool6a_q1, String tool6a_q2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Tool6a_Q1, tool6a_q1);
        contentValues.put(Tool6a_Q2, tool6a_q2);

        db.update(TABLE_NAME_7, contentValues, "ContactSim = ?", new String[]{contactNo});
        return true;
    }
/*
    public boolean updateTool6bData(String contactNo, String tool6b_q1, String tool6b_q2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Tool6b_Q1, tool6b_q1);
        contentValues.put(Tool6b_Q2, tool6b_q2);

        db.update(TABLE_NAME_8, contentValues, "ContactSim = ?", new String[]{contactNo});
        return true;
    }*/

    public boolean updateTool7Data(String contactNo, String tl7_q1, String tl7_q2, int tl7_q3, int tl7_q4, int tl7_q5, int tl7_q6,
                                   int tl7_q7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Tool7_Q1, tl7_q1);
        contentValues.put(Tool7_Q2, tl7_q2);
        contentValues.put(Tool7_Q3, tl7_q3);
        contentValues.put(Tool7_Q4, tl7_q4);
        contentValues.put(Tool7_Q5, tl7_q5);
        contentValues.put(Tool7_Q6, tl7_q6);
        contentValues.put(Tool7_Q7, tl7_q7);

        db.update(TABLE_NAME_9, contentValues, "ContactSim = ?", new String[]{contactNo});
        return true;
    }


    public boolean updateTool1Status(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL15, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool2Status(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL16, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool3Status(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL17, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool4Status(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL18, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool5Status(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL19, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean updateTool6aStatus(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL20, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

   /* public boolean updateTool6bStatus(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL21, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }*/

    public boolean updateTool7Status(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL22, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

    public boolean ContactNo(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME + " WHERE ContactSim ='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean columnExistsTool1(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_2 + " WHERE ContactSim ='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean columnExistsTool2(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_3 + " WHERE ContactSim ='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean columnExistsTool3(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_4 + " WHERE ContactSim ='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean columnExistsTool4(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_5 + " WHERE ContactSim ='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean columnExistsTool5(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_6 + " WHERE ContactSim ='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean columnExistsTool6a(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_7 + " WHERE ContactSim ='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

  /*  public boolean columnExistsTool6b(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_8 + " WHERE ContactSim ='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }*/

    public boolean columnExistsTool7(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_9 + " WHERE ContactSim ='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }



   /* public boolean tool5Result(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select Diabetic, Hypertension from " + TABLE_NAME_4 + " WHERE ContactSim ='" + value + "';", null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
    public Cursor tool1Result(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select cvaEvent from " + TABLE_NAME_2 + " WHERE ContactSim ='" + value + "';", null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return cursor;
        } else {
            cursor.close();
            return cursor;
        }
    }

    public Cursor tool2Result(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select result from " + TABLE_NAME_3 + " WHERE ContactSim ='" + value + "';", null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return cursor;
        } else {
            cursor.close();
            return cursor;
        }
    }
    public Cursor tool7Result(String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select result from " + TABLE_NAME_9 + " WHERE ContactSim ='" + value + "';", null);
        cursor.moveToFirst();
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return cursor;
        } else {
            cursor.close();
            return cursor;
        }
    }*/

    public List<syncedPatients> syncPatients(String userId) {
        List<syncedPatients> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " WHERE (UserID = '" +userId + "') AND (SyncData = 0 OR 1);", null);
        StringBuffer stringBuffer = new StringBuffer();
        syncedPatients dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new syncedPatients();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow("ContactSim"));
            dataModel.setParticipantName(name);
            dataModel.setParticipantContact(contact);
            stringBuffer.append(dataModel);
            data.add(dataModel);
            }
        Log.d(TAG, "getCompPartidata: 123");
        return data;
    }

    public List<CompParticipantsInfo> getCompPartidata(String userId) {
        // DataModel dataModel = new DataModel();
        List<CompParticipantsInfo> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " WHERE (UserID = '" +userId + "') AND (Enroll = 0 OR Tool7 = 1 ) ORDER BY Name ;", null);
        StringBuffer stringBuffer = new StringBuffer();
        CompParticipantsInfo dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new CompParticipantsInfo();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow("ContactSim"));
            String enroll = cursor.getString(cursor.getColumnIndexOrThrow("Enroll"));
            dataModel.setParticipantName(name);
            dataModel.setParticipantContact(contact);
            dataModel.setParticipantEnroll(enroll);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
            
        }
        Log.d(TAG, "getCompPartidata: 123");
        return data;
    }

    public List<CompParticipantsInfo> getIncompPartidata(String userid) {
        // DataModel dataModel = new DataModel();
        List<CompParticipantsInfo> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " WHERE (UserID = '" + userid + "') AND (Tool1 = 0 OR Tool2 = 0 OR " +
                "Tool3 = 0 OR Tool4 = 0 OR Tool5 = 0 OR Tool6a = 0 " +
                " OR Tool7 = 0 ) ORDER BY Name;", null);
        StringBuffer stringBuffer = new StringBuffer();
        CompParticipantsInfo dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new CompParticipantsInfo();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow("ContactSim"));
            String enroll = cursor.getString(cursor.getColumnIndexOrThrow("Enroll"));
            dataModel.setParticipantName(name);
            dataModel.setParticipantContact(contact);
            dataModel.setParticipantEnroll(enroll);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }
        return data;
    }

    public List<CompParticipantsInfo> SearchIncompPartidata(String userid, String name) {
        // DataModel dataModel = new DataModel();
        List<CompParticipantsInfo> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " WHERE (UserID = '" + userid + "') AND (Tool1 = 0 OR Tool2 = 0 OR " +
                "Tool3 = 0 OR Tool4 = 0 OR Tool5 = 0 OR Tool6a = 0 " +
                " OR Tool7 = 0 ) AND NAME LIKE '" +name+'%'+ "' ORDER BY Name;", null);

        StringBuffer stringBuffer = new StringBuffer();
        CompParticipantsInfo dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new CompParticipantsInfo();
            String names = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow("ContactSim"));
            String enroll = cursor.getString(cursor.getColumnIndexOrThrow("Enroll"));
            dataModel.setParticipantName(name);
            dataModel.setParticipantContact(contact);
            dataModel.setParticipantEnroll(enroll);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }
        return data;
    }
}