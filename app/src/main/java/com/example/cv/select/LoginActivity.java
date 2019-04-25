package com.example.cv.select;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;

import static com.example.cv.select.DatabaseHelperRP.DATABASE_NAME;
import static com.example.cv.select.DatabaseHelperRP.Database_Path;
import static com.example.cv.select.DatabaseHelperRP.TABLE_NAME;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText edtemail, edtpassword;
    static String UserID = null;
    Toolbar toolbar;
    String User1ID, UserName1, UserPassword1, User2ID, UserName2, UserPassword2, User3ID, UserName3, UserPassword3, User4ID, UserName4,
            UserPassword4, User5ID, UserName5, UserPassword5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        login = (Button) findViewById(R.id.login);
        edtemail = (EditText) findViewById(R.id.email);
        edtpassword = (EditText) findViewById(R.id.password);

        User1ID="1";
        UserName1="user1";
        UserPassword1="user1";

        User2ID="2";
        UserName2="user2";
        UserPassword2="user2";

        User3ID="3";
        UserName3="user3";
        UserPassword3="user3";

        User4ID="4";
        UserName4="user4";
        UserPassword4="user4";

        User5ID="5";
        UserName5="user5";
        UserPassword5="user5";


        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,
                "PackageName = " + info.packageName + "\nVersionCode = "
                        + info.versionCode + "\nVersionName = "
                        + info.versionName + "\nPermissions = " + info.permissions, Toast.LENGTH_SHORT).show();

       /* getResults();
        Log.d("Results", "onCreate: "+getResults());

        exportDB();*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });
    }

    private void userlogin() {
        final String email = edtemail.getText().toString().trim();
        final String password = edtpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_LONG).show();
            return;
        }
       else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG).show();
            return;
        }

       else if (email.equals(UserName1) && password.equals(UserPassword1)) {
            Toast.makeText(this, "User1 Logged in", Toast.LENGTH_SHORT).show();
            UserID=User1ID;
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
       else if (email.equals(UserName2) && password.equals(UserPassword2)) {
            Toast.makeText(this, "User2 Logged in", Toast.LENGTH_SHORT).show();
            UserID = User2ID;
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
       else if (email.equals(UserName3) && password.equals(UserPassword3)) {
            Toast.makeText(this, "User3 Logged in", Toast.LENGTH_SHORT).show();
            UserID=User3ID;
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
       else if (email.equals(UserName4) && password.equals(UserPassword4)) {
            Toast.makeText(this, "User4 Logged in", Toast.LENGTH_SHORT).show();
            UserID = User4ID;
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
       else if (email.equals(UserName5) && password.equals(UserPassword5)) {
            Toast.makeText(this, "User5 Logged in", Toast.LENGTH_SHORT).show();
            UserID=User5ID;
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(LoginActivity.this, "Invaild Email or Password.", Toast.LENGTH_SHORT).show();
        }
    }
    public static String getInstance(){
        return UserID;
    }
/*
    private JSONArray getResults()
    {
      //  String myPath = Database_Path + "select";// Set path to your database

        String mPath = "" + LoginActivity.this.getDatabasePath(DatabaseHelperRP.DATABASE_NAME);


        String myTable = TABLE_NAME;//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);

        String searchQuery = "SELECT  * FROM " + myTable;
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


    private void exportDB() {

        DatabaseHelperRP dbhelper = new DatabaseHelperRP(getApplicationContext());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "Hello");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvname.csv");
        try
        {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM " +TABLE_NAME,null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to export
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {

            Log.d("000000", "exception "+sqlEx.getMessage());
            Log.e("LoginActivity", sqlEx.getMessage(), sqlEx);
        }
    }*/
}