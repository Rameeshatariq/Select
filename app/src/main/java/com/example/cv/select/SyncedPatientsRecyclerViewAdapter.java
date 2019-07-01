package com.example.cv.select;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.opencsv.CSVWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME;
import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME_11;
import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME_2;
import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME_3;
import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME_4;
import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME_5;
import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME_6;
import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME_7;
import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME_9;
import static com.example.cv.select.SyncedPatientsRecyclerViewAdapter.MyViewHolder.NAME_NOT_SYNCED_WITH_SERVER;
import static com.example.cv.select.SyncedPatientsRecyclerViewAdapter.MyViewHolder.NAME_SYNCED_WITH_SERVER;
import static com.example.cv.select.SyncedPatientsRecyclerViewAdapter.MyViewHolder.URL_SAVE_NAME;

public class SyncedPatientsRecyclerViewAdapter extends RecyclerView.Adapter<SyncedPatientsRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<syncedPatients> mData;
    Cursor mCursor;
    String ContactNo;
    DatabaseHelperRP mdatabaseHelper;
    String userID,Name, Dob, Age, Gender, ContactSim, AlternateSim,Address, LivesInMalir, NotMovingFor6Months, Smartphone, ParticipateFOR6Months, InformedConsentTaken,
            Reason, Tool1, Tool2, Tool3, Tool4, Tool5, Tool6a, Tool7, Enroll, SyncData;

    public SyncedPatientsRecyclerViewAdapter(Context mContext, List<syncedPatients> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public SyncedPatientsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.syncedpatients, parent, false);
        SyncedPatientsRecyclerViewAdapter.MyViewHolder viewHolder = new SyncedPatientsRecyclerViewAdapter.MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SyncedPatientsRecyclerViewAdapter.MyViewHolder holder, final int position) {
       /* if(!mCursor.moveToPosition(position)){
            return;
        }*/
        holder.tvsyncName.setText(mData.get(position).getParticipantName());
        holder.tvsyncContact.setText(mData.get(position).getParticipantContact());

        final TextView textView = (TextView)holder.tvsyncContact;
       ContactNo= textView.getText().toString();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item Clicked", Toast.LENGTH_SHORT).show();
                getPatient(ContactNo);
                getTool1(ContactNo);
                getTool2(ContactNo);
                getTool3(ContactNo);
                getTool4(ContactNo);
                getTool5(ContactNo);
                getTool6(ContactNo);
                getTool7(ContactNo);
                getTeleconsultation(ContactNo);
                saveNameToServer();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvsyncName;
        private TextView tvsyncContact;
        public static final String URL_SAVE_NAME = "https://merishifa.akdndhrc.org/api/data-tables";
        public static final String DATA_SAVED_BROADCAST = "net.simplifiedcoding.datasaved";
        public static int NAME_SYNCED_WITH_SERVER;
        public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;
        private BroadcastReceiver broadcastReceiver;
        private TextView tvinprogpartienroll;
        private Button patient_detail, tools;
        private View.OnClickListener onItemClickListener;
        private DatabaseHelperRP databaseHelperRP;

        public void setItemClickListener(View.OnClickListener clickListener) {
            onItemClickListener = clickListener;
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);


            tvsyncName = (TextView) itemView.findViewById(R.id.tvsyncName);
            tvsyncContact = (TextView) itemView.findViewById(R.id.tvsyncContact);
        }
    }

    private JSONArray getPatient(String ContactNo)
    {
        //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + mContext.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable+  " WHERE ContactSim = '"+ContactNo+"' ";
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }

    private JSONArray getTool1(String ContactNo)
    {
        //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + mContext.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME_2;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable+  " WHERE ContactSim = '"+ContactNo+"' ";
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }

    private JSONArray getTool2(String ContactNo)
    {
        //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + mContext.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME_3;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable+  " WHERE ContactSim = '"+ContactNo+"' ";
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }

    private JSONArray getTool3(String ContactNo)
    {
        //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + mContext.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME_4;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable+  " WHERE ContactSim = '"+ContactNo+"' ";
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }

    private JSONArray getTool4(String ContactNo)
    {
        //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + mContext.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME_5;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable+  " WHERE ContactSim = '"+ContactNo+"' ";
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }
    private JSONArray getTool5(String ContactNo)
    {
        //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + mContext.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME_6;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable+  " WHERE ContactSim = '"+ContactNo+"' ";
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }

    private JSONArray getTool6(String ContactNo)
    {
        //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + mContext.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME_7;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable+  " WHERE ContactSim = '"+ContactNo+"' ";
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }

    private JSONArray getTool7(String ContactNo)
    {
        //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + mContext.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME_9;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable+  " WHERE ContactSim = '"+ContactNo+"' ";
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }

    private JSONArray getTeleconsultation(String ContactNo)
    {
        //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + mContext.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME_11;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable+  " WHERE ContactSim = '"+ContactNo+"' ";
        Cursor cursor = myDataBase.rawQuery(searchQuery, null );

        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
                            Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;
    }

    private void saveNameToServer() {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Saving Name...");
        progressDialog.show();

        mdatabaseHelper=new DatabaseHelperRP(mContext);


        Cursor data = mdatabaseHelper.getPartiData(ContactNo);
        if (data.getCount() == 0) {
            return;
        }

        while (data.moveToNext()) {

            // tv_tl7_contact.setText(data.getString(0));
            userID= data.getString(0);
            Name=(data.getString(1));
            Dob=(data.getString(2));
            Age=(data.getString(3));
            Gender=(data.getString(4));
            ContactSim=(data.getString(5));
            AlternateSim=(data.getString(6));
            Address=(data.getString(7));
            LivesInMalir=(data.getString(8));
            NotMovingFor6Months=(data.getString(9));
            Smartphone=(data.getString(10));
            ParticipateFOR6Months=(data.getString(11));
            InformedConsentTaken=(data.getString(12));
            Reason=(data.getString(15));
            Tool1=(data.getString(16));
            Tool2=(data.getString(17));
            Tool3=(data.getString(18));
            Tool4=(data.getString(19));
            Tool5=(data.getString(20));
            Tool6a=(data.getString(21));
            Tool7=(data.getString(22));
            Enroll=(data.getString(23));
            SyncData=(data.getString(24));
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Log.d("TAG", "onResponse: "+response);
                        try {
                            JSONObject mainObj = new JSONObject(response);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            Log.d("TAG", "onResponse: "+e);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("TAG", "onErrorResponse: "+error);
                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                        //on error storing the name to sqlite with status unsynced
                      //  saveNameToLocalStorage(name, NAME_NOT_SYNCED_WITH_SERVER);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {

                    JSONObject jObj = new JSONObject();
                    jObj.put("id", 1);
                    jObj.put("UserID",userID);
                    jObj.put("Name", Name);
                    jObj.put("Dob",Dob );
                    jObj.put("Age", Age);
                    jObj.put("Gender", Gender);
                    jObj.put("ContactSim", ContactSim);
                    jObj.put("AlternateSim", AlternateSim);
                    jObj.put("Address", Address);
                    jObj.put("LivesInMalir", LivesInMalir);
                    jObj.put("NotMovingFor6Months", NotMovingFor6Months);
                    jObj.put("Smartphone", Smartphone);
                    jObj.put("ParticipateFOR6Months", ParticipateFOR6Months);
                    jObj.put("InformedConsentTaken", InformedConsentTaken);
                    jObj.put("Reason", Reason);
                    jObj.put("Tool1", Tool1);
                    jObj.put("Tool2", Tool2);
                    jObj.put("Tool3", Tool3);
                    jObj.put("Tool4", Tool4);
                    jObj.put("Tool5", Tool5);
                    jObj.put("Tool6a", Tool6a);
                    jObj.put("Tool7", Tool7);
                    jObj.put("Enroll", Enroll);
                    jObj.put("SyncData", SyncData);

                    String mstring=jObj.toString();

                    params.put("id", "1");
                    params.put("table_name", "app_patient");
                    params.put("table_data", mstring);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }
}

