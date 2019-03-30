package com.example.cv.select;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelperRP extends SQLiteOpenHelper {
    private static  final  String TAG= "DatabaseHelper";
    public static final String DATABASE_NAME = "select.db";
    private static  final  String TABLE_NAME= "patient";
    private static  final  String COL1= "ID";
    private static  final  String COL2= "Name";
    private static  final  String COL3= "StudyID";
    private static  final  String COL4= "Age";
    private static  final  String COL5= "Gender";
    private static  final  String COL6= "ContactSim";
    private static  final  String COL7= "AlternateSim";
    private static  final  String COL8= "Address";
    private static  final  String COL9= "LivesInMalir";
    private static  final  String COL10= "NotMovingFor6Months";
    private static  final  String COL11= "SmartPhone";
    private static  final  String COL12= "ParticipateFor6Months";
    private static  final  String COL13= "InformedConsentTaken";
    private static  final  String COL14= "Reason";
   // private static  final  String COL15= "SyncData";


    public DatabaseHelperRP(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " Name TEXT, StudyID TEXT, Age TEXT, Gender TEXT, ContactSim TEXT, AlternateSim TEXT, Address TEXT, LivesInMalir TEXT, NotMovingFor6Months TEXT," +
                "Smartphone TEXT, ParticipateFOR6Months TEXT,  InformedConsentTaken TEXT, Reason TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public boolean addData(String name, String studyID, String age, String gender, String contactSim, String altSim, String address, String livesInMalir, String notMoving,
                           String smartphone, String participate, String informedconsent, String reason){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, studyID);
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
        //contentValues.put(COL15, syncData);

        Log.d(TAG, "addData: Adding" + name + studyID + age + gender + contactSim + altSim + address + livesInMalir + notMoving + smartphone + participate + informedconsent +
                reason + "to" + TABLE_NAME);
        long result =db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;

        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

   /* public boolean updateData(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,email);
        contentValues.put(COL4,password);
        db.update(TABLE_NAME, contentValues, "Email = ?", new String[] {email});
        return true;
    }

    public Integer deleteData(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Email = ?", new String[] {email});
    }*/
}
