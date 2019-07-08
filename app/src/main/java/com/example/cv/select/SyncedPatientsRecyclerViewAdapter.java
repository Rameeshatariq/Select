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
    Lister ls;
    String ContactNo;
    DatabaseHelperRP mdatabaseHelper;
    String userID,Name, Dob, Age, Gender, AlternateSim,Address, LivesInMalir, NotMovingFor6Months, Smartphone, ParticipateFOR6Months, InformedConsentTaken,
            RespondedtoIVR, RespondedtoSMS,Reason, Tool1, Tool2, Tool3, Tool4, Tool5, Tool6a, Tool7, Enroll, SyncData;

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

        Log.d("ABC", "onBindViewHolder: "+ContactNo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item Clicked", Toast.LENGTH_SHORT).show();
               // getPatient(ContactNo);
               // getTool1(ContactNo);
               // getTool2(ContactNo);
               // getTool3(ContactNo);
               // getTool4(ContactNo);
               // getTool5(ContactNo);
               // getTool6(ContactNo);
               // getTool7(ContactNo);
               // getTeleconsultation(ContactNo);
               // saveNameToServer();



                JSONObject mJobjPatient =  getPatientData(ContactNo);
                Log.d("ABC", "onClick: "+mJobjPatient);
                Log.d("ABC", "onClick: "+ContactNo);

                if(mJobjPatient.length() == 0) {
                    Toast.makeText(mContext, "Patient Already synced", Toast.LENGTH_SHORT).show();
                }
                else{
                    syncPatient(ContactNo, mJobjPatient);
                }


                JSONObject mJobjTool1 = getPatientTool1(ContactNo);
                syncTool1(ContactNo, mJobjTool1);


                JSONObject mJobjTool2 = getPatientTool2(ContactNo);
                syncTool2(ContactNo, mJobjTool2);


                JSONObject mJobjTool3 = getPatientTool3(ContactNo);
                syncTool3(ContactNo, mJobjTool3);


                JSONObject mJobjTool4 = getPatientTool4(ContactNo);
                syncTool4(ContactNo, mJobjTool4);


                JSONObject mJobjTool5 = getPatientTool5(ContactNo);
                syncTool5(ContactNo, mJobjTool5);


                JSONObject mJobjTool6 = getPatientTool6(ContactNo);
                syncTool6(ContactNo, mJobjTool6);


                JSONObject mJobjTool7 = getPatientTool7(ContactNo);
                syncTool7(ContactNo, mJobjTool7);


                JSONObject mJobjSummary = getPatientSummary(ContactNo);
                syncSummary(ContactNo, mJobjSummary);


                JSONObject mJobjTeleconsultation = getTeleconsultation(ContactNo);
                syncTeleconsultation(ContactNo, mJobjTeleconsultation);



            }
        });
    }


    private void syncTeleconsultation(final String contactNo, final JSONObject mJobjTeleconsultation) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);

                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("0009992", "response:syncTeleconsultation  "+error.getMessage());
                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_teleconsultation");
                    params.put("table_data", mJobjTeleconsultation.toString());

                    Log.d("000999", "param: "+params);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);




    }

    private void syncSummary(final String contactNo, final JSONObject mJobjSummary) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);

                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("0009992", "response:syncSummary  "+error.getMessage());

                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_summary");
                    params.put("table_data", mJobjSummary.toString());

                    Log.d("000999", "param: "+params);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);

    }

    private void syncTool7(final String contactNo, final JSONObject mJobjTool7) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);

                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("0009992", "response:syncTool7  "+error.getMessage());

                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_tool7");
                    params.put("table_data", mJobjTool7.toString());

                    Log.d("000999", "param: "+params);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);




    }

    private void syncTool6(final String contactNo, final JSONObject mJobjTool6) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);

                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("0009992", "response:syncTool6  "+error.getMessage());
                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_tool6a");
                    params.put("table_data", mJobjTool6.toString());

                    Log.d("000999", "param: "+params);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);




    }

    private void syncTool5(final String contactNo, final JSONObject mJobjTool5) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);

                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("0009992", "response:syncTool5  "+error.getMessage());
                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_tool5");
                    params.put("table_data", mJobjTool5.toString());

                    Log.d("000999", "param: "+params);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);




    }

    private void syncTool4(final String contactNo, final JSONObject mJobjTool4) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);

                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("0009992", "response:syncTool4  "+error.getMessage());
                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_tool4");
                    params.put("table_data", mJobjTool4.toString());

                    Log.d("000999", "param: "+params);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);




    }

    private void syncTool3(final String contactNo, final JSONObject mJobjTool3) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);

                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("0009992", "response:syncTool3  "+error.getMessage());
                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_tool3");
                    params.put("table_data", mJobjTool3.toString());

                    Log.d("000999", "param: "+params);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);




    }

    private void syncTool2(final String contactNo, final JSONObject mJobjTool2) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);

                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("0009992", "response:syncTool2  "+error.getMessage());
                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_tool2");
                    params.put("table_data", mJobjTool2.toString());

                    Log.d("000999", "param: "+params);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);




    }

    private void syncTool1(final String contactNo, final JSONObject mJobjTool1) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);
                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");

                            if(resultObj.getString("status").equals("success")){
                                Toast.makeText(mContext, "success true", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Log.d("0009992", "response:syncTool1  "+error.getMessage());
                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_tool1");
                    params.put("table_data", mJobjTool1.toString());

                    Log.d("000999", "param: "+params);

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);




    }

    private void syncPatient(final String contactNo, final JSONObject mJson) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait!");
        progressDialog.setMessage("Sync Patient Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject mainObj = new JSONObject(response);
                            Log.d("0009991", "response: "+mainObj);

                            JSONObject resultObj = mainObj.getJSONObject("result");
                            JSONObject contact= mainObj.getJSONObject("response");

                            if(resultObj.getString("status").equals("success")){
                                Log.d("0009991", "onResponse: "+response);

                                String ctactno= contact.getString("id");
                                Log.d("0009991", "onResponse: "+ctactno);


                                Lister ls = new Lister(mContext);
                                boolean isUpdate = ls.executeNonQuery("UPDATE patient set " +
                                        "SyncData =  1  " +
                                        " where ContactSim = '" + ctactno + "'" +
                                        " ");
                                if (isUpdate == true) {
                                    Log.d("0009991", "onResponse: "+response);
                                    Toast.makeText(mContext, "patient synced", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(mContext, "patient notsynced", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(mContext, "success false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Lister ls = new Lister(mContext);

                        Log.d("0009992", "response:syncPatient  "+error.getMessage());
                        error.printStackTrace();
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                try {
                    params.put("id", ""+contactNo);
                    params.put("table_name", "app_patient");
                    params.put("table_data", mJson.toString());

                }catch (Exception e){
                    Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "getParams:"+params);
                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }

    private JSONObject getPatientData(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData1 = ls.executeReader("Select *from patient where ContactSim = '"+ContactNo+"' AND SyncData = 0 ");

            Log.d("ABC", "getPatientData: "+mData1 );
            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData1.length; i++){
                for (int j=0; j<mData1[i].length; j++){
                    ////Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }


                jObj.put("id", 1);
                jObj.put("UserID", mData1[i][0]);
                jObj.put("Name", mData1[i][1]);
                jObj.put("Dob", mData1[i][2]);
                jObj.put("Age", mData1[i][3]);
                jObj.put("Gender", mData1[i][4]);
                jObj.put("ContactSim", mData1[i][5]);
                jObj.put("AlternateSim", mData1[i][6]);
                jObj.put("Address", mData1[i][7]);
                jObj.put("LivesInMalir", mData1[i][8]);
                jObj.put("NotMovingFor6Months", mData1[i][9]);
                jObj.put("Smartphone", mData1[i][10]);
                jObj.put("ParticipateFor6Months", mData1[i][11]);
                jObj.put("InformedConsentTaken", mData1[i][12]);
                jObj.put("RespondedToIVR", mData1[i][13]);
                jObj.put("RespondedToSMS", mData1[i][14]);
                jObj.put("Reason", mData1[i][15]);
                jObj.put("Tool1", mData1[i][16]);
                jObj.put("Tool2", mData1[i][17]);
                jObj.put("Tool3", mData1[i][18]);
                jObj.put("Tool4", mData1[i][19]);
                jObj.put("Tool5", mData1[i][20]);
                jObj.put("Tool6a", mData1[i][21]);
                jObj.put("Tool7", mData1[i][22]);
                jObj.put("Enroll", mData1[i][23]);
                jObj.put("SyncData", mData1[i][24]);
            }


            //jObjMain.put("result", new JSONObject().put("status", "success"));
            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            Log.d("ABC", "getPatientData: "+jArray);
            jObjMain.put("response", jArray);
            //jObjMain.put("response", "{\"result\":{\"status\":\"success\"},\"response\":[{\"id\":1,\"UserID\":\"8787\",\"Name\":\"test\",\"Dob\":\"3434\",\"Age\":\"8787\",\"Gender\":\"8787\",\"ContactSim\":\"8787\",\"AlternateSim\":\"8787\",\"Address\":\"8787\",\"LivesInMalir\":\"8787\",\"NotMovingFor6Months\":\"8787\",\"Smartphone\":\"8787\",\"ParticipateFOR6Months\":\"8787\",\"InformedConsentTaken\":\"8787\",\"Reason\":\"8787\",\"Tool1\":\"8787\",\"Tool2\":\"8787\",\"Tool3\":\"8787\",\"Tool4\":\"8787\",\"Tool5\":\"8787\",\"Tool6a\":\"8787\",\"Tool7\":\"8787\",\"Enroll\":\"8787\",\"SyncData\":\"8787\"},{\"id\":2,\"UserID\":\"8787\",\"Name\":\"test\",\"Dob\":\"3434\",\"Age\":\"8787\",\"Gender\":\"8787\",\"ContactSim\":\"8787\",\"AlternateSim\":\"8787\",\"Address\":\"8787\",\"LivesInMalir\":\"8787\",\"NotMovingFor6Months\":\"8787\",\"Smartphone\":\"8787\",\"ParticipateFOR6Months\":\"8787\",\"InformedConsentTaken\":\"8787\",\"Reason\":\"8787\",\"Tool1\":\"8787\",\"Tool2\":\"8787\",\"Tool3\":\"8787\",\"Tool4\":\"8787\",\"Tool5\":\"8787\",\"Tool6a\":\"8787\",\"Tool7\":\"8787\",\"Enroll\":\"8787\",\"SyncData\":\"8787\"}]}\n");


            return jObjMain;

        } catch (Exception e){
            return new JSONObject();
        }


    }

    private JSONObject getPatientTool1(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData = ls.executeReader("Select *from tool1 where ContactSim = '"+contactNo+"'");

            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData.length; i++){
                for (int j=0; j<mData[i].length; j++){
                    Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }

                jObj.put("ContactSim", mData[i][0]);
                jObj.put("tool1_Q1", mData[i][1]);
                jObj.put("tool1_Q2", mData[i][2]);
                jObj.put("tool1_Q3", mData[i][3]);
                jObj.put("tool1_Q4", mData[i][4]);
                jObj.put("tool1_Q5", mData[i][5]);
                jObj.put("tool1_Q6", mData[i][6]);
                jObj.put("tool1_Q7", mData[i][7]);
                jObj.put("tool1_Q8", mData[i][8]);
                jObj.put("tool1_syncData", mData[i][9]);
                jObj.put("cvaEvent", mData[i][10]);


            }

            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            jObjMain.put("response", jArray);

            return jObjMain;

        } catch (Exception e){
            Log.d("000999", "Exception   "+e);

            return new JSONObject();
        }


    }

    private JSONObject getPatientTool2(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData = ls.executeReader("Select *from tool2 where ContactSim = '"+contactNo+"'");

            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData.length; i++){
                for (int j=0; j<mData[i].length; j++){
                    //Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }

                jObj.put("ContactSim", mData[i][0]);
                jObj.put("tool2_Q1", mData[i][1]);
                jObj.put("tool2_Q2", mData[i][2]);
                jObj.put("tool2_Q3", mData[i][3]);
                jObj.put("result", mData[i][4]);


            }

            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            jObjMain.put("response", jArray);

            return jObjMain;

        } catch (Exception e){
            Log.d("000999", "Exception   "+e);

            return new JSONObject();
        }


    }

    private JSONObject getPatientTool3(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData = ls.executeReader("Select *from tool3 where ContactSim = '"+contactNo+"'");

            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData.length; i++){
                for (int j=0; j<mData[i].length; j++){
                    //Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }



                jObj.put("ContactSim", mData[i][0]);
                jObj.put("Diabetic", mData[i][1]);
                jObj.put("Hypertension", mData[i][2]);
                jObj.put("DiabeticControlByMedicines", mData[i][3]);
                jObj.put("DiabeticControlByInsulin", mData[i][4]);
                jObj.put("DiabeticControlByDiet", mData[i][5]);
                jObj.put("DiabeticControlByPNRMedication", mData[i][6]);
                jObj.put("DiabeticControlByAlternateMedication", mData[i][7]);
                jObj.put("HypertensionNotControlByMedicines", mData[i][8]);
                jObj.put("HypertensionControlByMedicines", mData[i][9]);
                jObj.put("HypertensionControlByDietOrMedicines", mData[i][10]);
                jObj.put("HypertensionNotControlByPNRMedication", mData[i][11]);
                jObj.put("HypertensionControlByAlternateMedication", mData[i][12]);



            }

            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            jObjMain.put("response", jArray);

            return jObjMain;

        } catch (Exception e){
            Log.d("000999", "Exception   "+e);

            return new JSONObject();
        }


    }

    private JSONObject getPatientTool4(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData = ls.executeReader("Select *from tool4 where ContactSim = '"+contactNo+"'");

            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData.length; i++){
                for (int j=0; j<mData[i].length; j++){
                    //Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }

                jObj.put("ContactSim", mData[i][0]);
                jObj.put("tool4_Q1", mData[i][1]);
                jObj.put("result", mData[i][2]);


            }

            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            jObjMain.put("response", jArray);

            return jObjMain;

        } catch (Exception e){
            Log.d("000999", "Exception   "+e);

            return new JSONObject();
        }


    }

    private JSONObject getPatientTool5(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData = ls.executeReader("Select *from tool5 where ContactSim = '"+contactNo+"'");

            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData.length; i++){
                for (int j=0; j<mData[i].length; j++){
                    //Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }

                jObj.put("ContactSim", mData[i][0]);
                jObj.put("VigourousExercise", mData[i][1]);
                jObj.put("DaysOfVigourous", mData[i][2]);
                jObj.put("HoursOfVigorous", mData[i][3]);
                jObj.put("MinsOfVigorous", mData[i][4]);
                jObj.put("ModerateExercise", mData[i][5]);
                jObj.put("DaysOfModerate", mData[i][6]);
                jObj.put("HoursOfModerate", mData[i][7]);
                jObj.put("MinsOfModerate", mData[i][8]);
                jObj.put("Walk", mData[i][9]);
                jObj.put("DaysOfWalk", mData[i][10]);
                jObj.put("HoursOfWalk", mData[i][11]);
                jObj.put("MinsOfWalk", mData[i][12]);
                jObj.put("result", mData[i][13]);


            }

            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            jObjMain.put("response", jArray);

            return jObjMain;

        } catch (Exception e){
            Log.d("000999", "Exception   "+e);

            return new JSONObject();
        }


    }

    private JSONObject getPatientTool6(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData = ls.executeReader("Select *from tool6a where ContactSim = '"+contactNo+"'");

            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData.length; i++){
                for (int j=0; j<mData[i].length; j++){
                    //Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }

                jObj.put("ContactSim", mData[i][0]);
                jObj.put("tool6a_Q1", mData[i][1]);
                jObj.put("tool6a_Q2", mData[i][1]);
                jObj.put("tool6a_Q3", mData[i][1]);
                jObj.put("tool6a_Q4", mData[i][1]);

            }

            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            jObjMain.put("response", jArray);

            return jObjMain;

        } catch (Exception e){
            Log.d("000999", "Exception   "+e);

            return new JSONObject();
        }


    }

    private JSONObject getPatientTool7(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData = ls.executeReader("Select *from tool7 where ContactSim = '"+contactNo+"'");

            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData.length; i++){
                for (int j=0; j<mData[i].length; j++){
                    //Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }



                jObj.put("ContactSim", mData[i][0]);
                jObj.put("tool7_Q1", mData[i][1]);
                jObj.put("tool7_Q2", mData[i][2]);
                jObj.put("tool7_Q3", mData[i][3]);
                jObj.put("tool7_Q4", mData[i][4]);
                jObj.put("tool7_Q5", mData[i][5]);
                jObj.put("tool7_Q6", mData[i][6]);
                jObj.put("tool7_Q7", mData[i][7]);
                jObj.put("result", mData[i][8]);


            }

            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            jObjMain.put("response", jArray);

            return jObjMain;

        } catch (Exception e){
            Log.d("000999", "Exception   "+e);

            return new JSONObject();
        }


    }

    private JSONObject getPatientSummary(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData = ls.executeReader("Select *from summary where ContactSim = '"+contactNo+"'");

            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData.length; i++){
                for (int j=0; j<mData[i].length; j++){
                    //Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }

                jObj.put("ContactSim", mData[i][0]);
                jObj.put("tool1", mData[i][1]);
                jObj.put("tool2", mData[i][2]);
                jObj.put("tool3", mData[i][3]);
                jObj.put("tool7", mData[i][4]);


            }

            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            jObjMain.put("response", jArray);

            return jObjMain;

        } catch (Exception e){
            Log.d("000999", "Exception   "+e);

            return new JSONObject();
        }


    }

    private JSONObject getTeleconsultation(String contactNo) {

        try{


            ls = new Lister(mContext);
            JSONObject jObj = new JSONObject();
            String[][] mData = ls.executeReader("Select *from teleconsultation where ContactSim = '"+contactNo+"'");

            JSONObject jObjMain = new JSONObject();

            for (int i=0; i<mData.length; i++){
                for (int j=0; j<mData[i].length; j++){
                    //Log.d("000999", "   mData["+i+"]["+j+"]"+mData[i][j]);
                }

                jObj.put("ContactSim", mData[i][0]);
                jObj.put("date", mData[i][1]);
                jObj.put("time", mData[i][2]);


            }

            JSONArray jArray = new JSONArray();
            jArray.put(jObj);

            jObjMain.put("response", jArray);

            return jObjMain;

        } catch (Exception e){
            Log.d("000999", "Exception   "+e);

            return new JSONObject();
        }


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
}

       /*     }
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
            RespondedtoIVR=(data.getString(13));
            RespondedtoSMS=(data.getString(14));
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
                        }
                        catch (JSONException e) {
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
                    jObj.put("RespondedToIVR", RespondedtoIVR);
                    jObj.put("RespondedToSMS", RespondedtoSMS);
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

                    params.put("id", ContactSim);
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
}*/

