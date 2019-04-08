package com.example.cv.select;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.Log;
import android.widget.Switch;

public class DatabaseHelperRP extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "select.db";

    private static final String TABLE_NAME = "patient";
    private static final String COL2 = "Name";
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
    private static final String COL14 = "Reason";
    private static final String COL15 = "Tool1";
    private static final String COL16 = "Tool2";
    private static final String COL17 = "Tool3";
    private static final String COL18 = "Tool4";
    private static final String COL19 = "Tool5";
    private static final String COL20 = "Tool6a";
    private static final String COL21 = "Tool6b";
    private static final String COL22 = "Tool7";
    private static final String COL23 = "Enroll";
    private static final String COL24 = "SyncData";


    private static final String TABLE_NAME_2 = "tool1";
    private static final String TOOL1_Q1 = "tool1_Q1";
    private static final String TOOL1_Q2 = "tool1_Q2";
    private static final String TOOL1_Q3 = "tool1_Q3";
    private static final String TOOL1_Q4 = "tool1_Q4";
    private static final String TOOL1_Q5 = "tool1_Q5";
    private static final String TOOL1_Q6 = "tool1_Q6";
    private static final String TOOL1_Q7 = "tool1_Q7";
    private static final String TOOL1_Q8 = "tool1_Q8";
    private static final String TOOL1_SyncData= "tool1_syncData";
    private static final String CVAEVENT = "cvaEvent";

    private static final String TABLE_NAME_3 = "tool2";
    private static final String TOOL2_Q1 = "tool2_Q1";
    private static final String TOOL2_Q2 = "tool2_Q2";
    private static final String TOOL2_Q3 = "tool2_Q3";
    private static final String TOOL2_SyncData= "tool2_syncData";

    private static final String TABLE_NAME_4 = "tool3";
    private static final String TOOL3_Q1_Diabetic = "Diabetic";
    private static final String  TOOL3_Q1_Hypertension= "Hypertension";
    private static final String  TOOL3_Diabetic_ControlBy_Medicines= "DiabeticControlByMedicines";
    private static final String  TOOL3_Diabetic_ControlBy_Insulin= "DiabeticControlByInsulin";
    private static final String  TOOL3_Diabetic_ControlBy_Diet= "DiabeticControlByDiet";
    private static final String  TOOL3_Diabetic_ControlBy_PNRMed= "DiabeticControlByPNRMedication";
    private static final String  TOOL3_Diabetic_ControlBy_AlternateMed= "DiabeticControlByAlternateMedication";
    private static final String  TOOL3_Hypertension_ControlBy_NotMed= "HypertensionNotControlByMedicines";
    private static final String  TOOL3_Hypertension_ControlBy_Medicines= "HypertensionControlByMedicines";
    private static final String  TOOL3_Hypertension_ControlBy_DietOrMed= "HypertensionControlByDietOrMedicines";
    private static final String  TOOL3_Hypertension_ControlBy_PnrMed= "HypertensionNotControlByPNRMedication";
    private static final String  TOOL3_Hypertension_ControlBy_AlternateMed= "HypertensionControlByAlternateMedication";
    private static final String TOOL3_SyncData= "tool3_syncData";


    private static final String TABLE_NAME_5 = "tool4";
    private static final String Tool4_Q1 = "tool4_Q1";
    private static final String TOOL4_SyncData= "tool4_syncData";


    private static final String TABLE_NAME_6 = "tool5";
    private static final String TOOL5_Q1 = "VigourousExercise";
    private static final String TOOL5_Q1_days = "DaysOfVigourous";
    private static final String TOOL5_Q2= "HoursMinsOfVigorous";
    private static final String TOOL5_Q3= "ModerateExercise";
    private static final String TOOL5_Q3_days = "DaysOfModerate";
    private static final String TOOL5_Q4= "HoursMinsOfModerate";
    private static final String TOOL5_Q5= "Walk";
    private static final String TOOL5_Q5_days = "DaysOfWalk";
    private static final String TOOL5_Q6= "HoursMinsOfWalk";
    private static final String TOOL5_SyncData= "tool5_syncData";


    private static final String TABLE_NAME_7 = "tool6a";
    private static final String Tool6a_Q1 = "tool6a_Q1";
    private static final String Tool6a_Q2 = "tool6a_Q2";
    private static final String TOOL6a_SyncData= "tool6a_syncData";

    private static final String TABLE_NAME_8 = "tool6b";
    private static final String Tool6b_Q1 = "tool6b_Q1";
    private static final String Tool6b_Q2 = "tool6b_Q2";
    private static final String TOOL6b_SyncData= "tool6b_syncData";

    private static final String TABLE_NAME_9 = "tool7";
    private static final String Tool7_Q1 = "tool7_Q1";
    private static final String Tool7_Q2 = "tool7_Q2";
    private static final String Tool7_Q3 = "tool7_Q3";
    private static final String Tool7_Q4 = "tool7_Q4";
    private static final String Tool7_Q5 = "tool7_Q5";
    private static final String Tool7_Q6 = "tool7_Q6";
    private static final String Tool7_Q7 = "tool7_Q7";
    private static final String TOOL7_SyncData= "tool7_syncData";

    private static final String TABLE_NAME_10 = "summary";
    private static final String Tool1 = "tool1";
    private static final String Tool2 = "tool2";
    private static final String Tool3 = "tool3";
    private static final String Tool7 = "tool7";



    public DatabaseHelperRP(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + "(Name TEXT, Age TEXT, Gender TEXT, " +
                "ContactSim TEXT PRIMARY KEY, AlternateSim TEXT, Address TEXT, LivesInMalir TEXT, NotMovingFor6Months TEXT," +
                "Smartphone TEXT, ParticipateFOR6Months TEXT,  InformedConsentTaken TEXT, Reason TEXT," +
                "Tool1 TEXT, Tool2 TEXT, Tool3 TEXT, Tool4 TEXT, Tool5 TEXT, Tool6a TEXT, Tool6b TEXT," +
                "Tool7 TEXT, Enroll TEXT,  SyncData TEXT )";
        db.execSQL(createTable);

        String createTable2 = "CREATE TABLE " + TABLE_NAME_2 + " (" +
                COL6 + " TEXT, " +
                "tool1_Q1 TEXT, tool1_Q2 TEXT, tool1_Q3 TEXT, tool1_Q4 TEXT, tool1_Q5 TEXT, tool1_Q6 TEXT, tool1_Q7 TEXT,tool1_Q8 TEXT, tool1_syncData TEXT," +
                " FOREIGN KEY (" + COL6 + ") REFERENCES  " + TABLE_NAME + "(" + COL6 + "))";
        db.execSQL(createTable2);

        String createTable3 = "CREATE TABLE " + TABLE_NAME_3 + " ("
                + COL6 + " TEXT, " +
                "tool2_Q1 TEXT, tool2_Q2 TEXT, tool2_Q3 TEXT, tool2_syncData TEXT, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable3);

        String createTable4 = "CREATE TABLE " + TABLE_NAME_4 + " ( "
                + COL6 + " TEXT, " +
                "Diabetic TEXT," + TOOL3_Diabetic_ControlBy_Medicines + " TEXT, " + TOOL3_Diabetic_ControlBy_Insulin + " TEXT, "
                + TOOL3_Diabetic_ControlBy_Diet + " TEXT, " + TOOL3_Diabetic_ControlBy_PNRMed + " TEXT, " + TOOL3_Diabetic_ControlBy_AlternateMed + " TEXT, " +
                "Hypertension TEXT, "+ TOOL3_Hypertension_ControlBy_NotMed + " TEXT, " + TOOL3_Hypertension_ControlBy_Medicines + " TEXT, " + TOOL3_Hypertension_ControlBy_DietOrMed + " TEXT, "
                + TOOL3_Hypertension_ControlBy_PnrMed + " TEXT, " + TOOL3_Hypertension_ControlBy_AlternateMed + " TEXT, tool3_syncData TEXT, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable4);

        String createTable5 = "CREATE TABLE " + TABLE_NAME_5 + " ("
                + COL6 + " TEXT, " +
                "tool4_Q1 TEXT, tool4_syncData TEXT, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable5);

        String createTable6 = "CREATE TABLE " + TABLE_NAME_6 + " ("
                + COL6 + " TEXT, " +
                "VigourousExercise TEXT, DaysOfVigourous TEXT, HoursMinsOfVigorous TEXT, ModerateExercise TEXT, DaysOfModerate TEXT, " +
                "HoursMinsOfModerate TEXT, Walk TEXT, DaysOfWalk TEXT, HoursMinsOfWalk TEXT, tool5_syncData TEXT, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable6);

        String createTable7 = "CREATE TABLE " + TABLE_NAME_7 + " ("
                + COL6 + " TEXT, " +
                "tool6a_Q1 TEXT, tool6a_Q2 TEXT, tool6a_syncData TEXT, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable7);

        String createTable8 = "CREATE TABLE " + TABLE_NAME_8 + " ("
                + COL6 + " TEXT, " +
                "tool6b_Q1 TEXT, tool6b_Q2 TEXT, tool6b_syncData TEXT, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES " + TABLE_NAME + " ( " + COL6 + "))";
        db.execSQL(createTable8);

        String createTable9 = "CREATE TABLE " + TABLE_NAME_9 + " (" +
                COL6 + " TEXT, " +
                "tool7_Q1 TEXT, tool7_Q2 TEXT, tool7_Q3 TEXT, tool7_Q4 TEXT, tool7_Q5 TEXT, tool7_Q6 TEXT, tool7_Q7 TEXT, tool7_syncData TEXT, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES  " + TABLE_NAME + "(" + COL6 + "))";
        db.execSQL(createTable9);

        String createTable10 = "CREATE TABLE " + TABLE_NAME_10 + " (" +
                COL6 + " TEXT, " +
                "tool1 TEXT, tool2 TEXT, tool3 TEXT, tool7 TEXT, " +
                " FOREIGN KEY (" + COL6 + ") REFERENCES  " + TABLE_NAME + "(" + COL6 + "))";
        db.execSQL(createTable10);
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

    public boolean addData(String name, String age, String gender, String contactSim, String altSim, String address, String livesInMalir, String notMoving,
                           String smartphone, String participate, String informedconsent, String reason, String Tool1, String Tool2,
                           String Tool3, String Tool4, String Tool5, String Tool6a, String Tool6b, String Tool7, String Enroll, boolean IsSync) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
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
        contentValues.put(COL14, reason);
        contentValues.put(COL15, Tool1);
        contentValues.put(COL16, Tool2);
        contentValues.put(COL17, Tool3);
        contentValues.put(COL18, Tool4);
        contentValues.put(COL19, Tool5);
        contentValues.put(COL20, Tool6a);
        contentValues.put(COL21, Tool6b);
        contentValues.put(COL22, Tool7);
        contentValues.put(COL23, Enroll);
        contentValues.put(COL24, IsSync);

        Log.d(TAG, "addData: Adding" + name + age + gender + contactSim + altSim + address + livesInMalir + notMoving + smartphone + participate + informedconsent +
                reason + "to" + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool1Data(String ContactNo, String Q1, String Q2, String Q3, String Q4, String Q5, String Q6, String Q7, String Q8, boolean syncData) {
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
        contentValues.put(TOOL1_SyncData, syncData);

        Log.d(TAG, "addTool1Data: Adding" + ContactNo + Q1 + Q2 + Q3 + Q4 + Q5 + Q6 + Q7 + Q8 + "to" + TABLE_NAME_2);
        long result1 = db.insert(TABLE_NAME_2, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool2Data(String ContactNo, String tool1bQ1, String tool1bQ2, String tool1bQ3, boolean syncData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL2_Q1, tool1bQ1);
        contentValues.put(TOOL2_Q2, tool1bQ2);
        contentValues.put(TOOL2_Q3, tool1bQ3);
        contentValues.put(TOOL2_SyncData, syncData);

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
                                String hypertensionControlByAltMed, boolean syncData) {
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
        contentValues.put(TOOL3_SyncData, syncData);

        Log.d(TAG, "addTool2Data: Adding" + COL6 + TOOL3_Q1_Diabetic + TOOL3_Diabetic_ControlBy_Medicines + TOOL3_Hypertension_ControlBy_AlternateMed + "to" + TABLE_NAME_3);
        long result1 = db.insert(TABLE_NAME_4, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool4Data(String ContactNo, String tool4Q1, boolean syncData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(Tool4_Q1, tool4Q1);
        contentValues.put(TOOL4_SyncData, syncData);


        Log.d(TAG, "addTool4Data: Adding" + COL6 + Tool4_Q1 + "to" + TABLE_NAME_5);
        long result1 = db.insert(TABLE_NAME_5, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool5Data(String ContactNo, String vigourous, String vigorousDays, String vigourousHoursMins, String moderate, String moderateDays,
                                String moderateHoursMins, String walk, String walkDays, String walkHoursMins, boolean syncData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, ContactNo);
        contentValues.put(TOOL5_Q1, vigourous);
        contentValues.put(TOOL5_Q1_days, vigorousDays);
        contentValues.put(TOOL5_Q2, vigourousHoursMins);
        contentValues.put(TOOL5_Q3, moderate);
        contentValues.put(TOOL5_Q3_days, moderateDays);
        contentValues.put(TOOL5_Q4, moderateHoursMins);
        contentValues.put(TOOL5_Q5, walk);
        contentValues.put(TOOL5_Q5_days, walkDays);
        contentValues.put(TOOL5_Q6, walkHoursMins);
        contentValues.put(TOOL5_SyncData, syncData);

        Log.d(TAG, "addTool5Data: Adding" + ContactNo + vigourous + vigorousDays + vigourousHoursMins + moderate + moderateDays +
                moderateHoursMins + walk + walkDays + walkHoursMins + "to" + TABLE_NAME_2);
        long result1 = db.insert(TABLE_NAME_6, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool6aData(String contactNo, String tool6a_q1, String tool6a_q2, boolean syncData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Tool6a_Q1, tool6a_q1);
        contentValues.put(Tool6a_Q2, tool6a_q2);
        contentValues.put(TOOL6a_SyncData, syncData);

        Log.d(TAG, "addTool5Data: Adding" + contactNo + tool6a_q1 + tool6a_q2 + "to" + TABLE_NAME_7);
        long result1 = db.insert(TABLE_NAME_7, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool6bData(String contactNo, String tool6b_q1, String tool6b_q2, boolean syncData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL6, contactNo);
        contentValues.put(Tool6b_Q1, tool6b_q1);
        contentValues.put(Tool6b_Q2, tool6b_q2);
        contentValues.put(TOOL6b_SyncData, syncData);

        Log.d(TAG, "addTool5Data: Adding" + contactNo + tool6b_q1 + tool6b_q2 + "to" + TABLE_NAME_8);
        long result1 = db.insert(TABLE_NAME_8, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTool7Data(String contactNo, String tl7_q1, String tl7_q2, int tl7_q3, int tl7_q4, int tl7_q5, int tl7_q6,
                                int tl7_q7, boolean syncData) {
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
        contentValues.put(TOOL7_SyncData, syncData);

        Log.d(TAG, "addTool7Data: Adding" + contactNo + tl7_q1 + tl7_q2 + "to" + TABLE_NAME_9);
        long result1 = db.insert(TABLE_NAME_9, null, contentValues);

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
    public Cursor getTool6bData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_8;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
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
    public Cursor getPartiTool6bData(String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_8
                + " WHERE ContactSim = '" + contactNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
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

    public boolean updatePrtiData(String name, String age, String gender, String ContactSim, String altSim, String address, String livesInMalir, String notMoving,
                                  String smartphone, String participate, String informedconsent, String reason, boolean SwitchState){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
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
        contentValues.put(COL24,SwitchState);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[] {ContactSim});
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
    public boolean updateTool6bStatus(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL21, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    } public boolean updateTool7Status(String ContactNo, String completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL22, completed);
        db.update(TABLE_NAME, contentValues, "ContactSim = ?", new String[]{ContactNo});
        return true;
    }

   public boolean columnExistsTool1(String value) {
       SQLiteDatabase db= this.getWritableDatabase();
       String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_2 + " WHERE ContactSim ='"+value+"' LIMIT 1)";
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
        SQLiteDatabase db= this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_3 + " WHERE ContactSim ='"+value+"' LIMIT 1)";
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
        SQLiteDatabase db= this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_4 + " WHERE ContactSim ='"+value+"' LIMIT 1)";
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
        SQLiteDatabase db= this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_5 + " WHERE ContactSim ='"+value+"' LIMIT 1)";
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
        SQLiteDatabase db= this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_6 + " WHERE ContactSim ='"+value+"' LIMIT 1)";
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
        SQLiteDatabase db= this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_7 + " WHERE ContactSim ='"+value+"' LIMIT 1)";
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
    public boolean columnExistsTool6b(String value) {
        SQLiteDatabase db= this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_8 + " WHERE ContactSim ='"+value+"' LIMIT 1)";
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

    public boolean columnExistsTool7(String value) {
        SQLiteDatabase db= this.getWritableDatabase();
        String sql = "SELECT EXISTS (SELECT * FROM " + TABLE_NAME_9 + " WHERE ContactSim ='"+value+"' LIMIT 1)";
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


}