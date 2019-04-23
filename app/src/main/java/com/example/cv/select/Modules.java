package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Modules extends AppCompatActivity {
    private Button btn_risktriageCVA, btn_risktriageMI, btn_risktriageDiabetic, btn_IDRSmodified, btn_physicalActivity, btn_tobaccoSmoking,
            btn_tobaccoSmoking_SL, btn_dietLifestyle, btn_summary;
    private Toolbar toolbar;
    String ContactNo, Tool1, Tool2, Tool3, Tool7;
    private DatabaseHelperRP mDatabaseHelper;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Modules.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_item1) {
            Toast.makeText(Modules.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        mDatabaseHelper=new DatabaseHelperRP(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Modules");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Modules.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_risktriageCVA=(Button)findViewById(R.id.btn_risktriageCVA);
        btn_risktriageMI=(Button)findViewById(R.id.btn_risktriageMI);
        btn_risktriageDiabetic=(Button)findViewById(R.id.btn_risktriageDiabetic);
        btn_IDRSmodified=(Button)findViewById(R.id.btn_IDRSmodified);
        btn_physicalActivity=(Button)findViewById(R.id.btn_physicalActivity);
        btn_tobaccoSmoking=(Button)findViewById(R.id.btn_tobaccoSmoking);
        btn_tobaccoSmoking_SL=(Button)findViewById(R.id.btn_tobaccoSmokingSL);
        btn_dietLifestyle=(Button)findViewById(R.id.btn_dietLifestyle);
        btn_summary=(Button)findViewById(R.id.btn_summary);

        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");
        Tool1=intent.getStringExtra("tool1");
        Tool2=intent.getStringExtra("tool2");
        Tool3=intent.getStringExtra("tool3");
        Tool7=intent.getStringExtra("tool7");

        Toast.makeText(this, "" +ContactNo, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" +Tool1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" +Tool2, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" +Tool3, Toast.LENGTH_SHORT).show();





        btn_risktriageCVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = mDatabaseHelper.columnExistsTool1(ContactNo);
                if (check == true) {
                    Toast.makeText(Modules.this, "The Patient Holding This Contact No has Already Completed Tool 1", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Modules.this, RiskAndTriageCVA.class);
                    intent.putExtra("ContactNo", ContactNo);
                    startActivity(intent);
              }
            }
        });

        btn_risktriageMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = mDatabaseHelper.columnExistsTool2(ContactNo);
                if (check == true) {
                    Toast.makeText(Modules.this, "The Patient Holding This Contact No has Already Completed Tool 2", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Modules.this, RiskAndTriageMI.class);
                    intent.putExtra("ContactNo", ContactNo);
                    intent.putExtra("tool1",Tool1);
                    Toast.makeText(Modules.this, "" + ContactNo, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
        btn_risktriageDiabetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = mDatabaseHelper.columnExistsTool3(ContactNo);
                if (check == true) {
                    Toast.makeText(Modules.this, "The Patient Holding This Contact No has Already Completed Tool 3", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Modules.this, RiskAndTriageDiabetic.class);
                    intent.putExtra("ContactNo", ContactNo);
                    intent.putExtra("tool1",Tool1);
                    intent.putExtra("tool2",Tool2);
                    startActivity(intent);
                }
            }
        });

        btn_IDRSmodified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = mDatabaseHelper.columnExistsTool4(ContactNo);
                if (check == true) {
                    Toast.makeText(Modules.this, "The Patient Holding This Contact No has Already Completed Tool 4", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Modules.this, IDRSModified.class);
                    intent.putExtra("ContactNo", ContactNo);
                    intent.putExtra("tool1",Tool1);
                    intent.putExtra("tool2",Tool2);
                    intent.putExtra("tool3",Tool3);
                    startActivity(intent);
                }
            }
        });
        btn_physicalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = mDatabaseHelper.columnExistsTool5(ContactNo);
                if (check == true) {
                    Toast.makeText(Modules.this, "The Patient Holding This Contact No has Already Completed Tool 5", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Modules.this, PhysicalActivity.class);
                    intent.putExtra("ContactNo", ContactNo);
                    intent.putExtra("tool1",Tool1);
                    intent.putExtra("tool2",Tool2);
                    intent.putExtra("tool3",Tool3);
                    startActivity(intent);
                }
            }
        });
        btn_tobaccoSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = mDatabaseHelper.columnExistsTool6a(ContactNo);
                if (check == true) {
                    Toast.makeText(Modules.this, "The Patient Holding This Contact No has Already Completed Tool 6a", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Modules.this, TobbaccoSmoking.class);
                    intent.putExtra("ContactNo", ContactNo);
                    intent.putExtra("tool1",Tool1);
                    intent.putExtra("tool2",Tool2);
                    intent.putExtra("tool3",Tool3);
                    startActivity(intent);
                }
            }
        });
        btn_tobaccoSmoking_SL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = mDatabaseHelper.columnExistsTool6b(ContactNo);
                if (check == true) {
                    Toast.makeText(Modules.this, "The Patient Holding This Contact No has Already Completed Tool 6b", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Modules.this, TobbaccoSmokingSL.class);
                    intent.putExtra("ContactNo", ContactNo);
                    intent.putExtra("tool1",Tool1);
                    intent.putExtra("tool2",Tool2);
                    intent.putExtra("tool3",Tool3);
                    startActivity(intent);
                }
            }
        });
        btn_dietLifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = mDatabaseHelper.columnExistsTool7(ContactNo);
                if (check == true) {
                    Toast.makeText(Modules.this, "The Patient Holding This Contact No has Already Completed Tool 7", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Modules.this, DietLifestyle.class);
                    intent.putExtra("ContactNo", ContactNo);
                    intent.putExtra("ContactNo", ContactNo);
                    intent.putExtra("tool1",Tool1);
                    intent.putExtra("tool2",Tool2);
                    intent.putExtra("tool3",Tool3);
                    startActivity(intent);
                }
            }
        });
        btn_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Modules.this, Summary.class);
                    intent.putExtra("ContactNo", ContactNo);
                    intent.putExtra("tool1",Tool1);
                    intent.putExtra("tool2",Tool2);
                    intent.putExtra("tool3",Tool3);
                    intent.putExtra("tool7",Tool7);
                startActivity(intent);
            }
        });

    }
}
