package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Modules extends AppCompatActivity {
    private Button btn_risktriageCVA, btn_risktriageMI, btn_risktriageDiabetic, btn_IDRSmodified, btn_physicalActivity, btn_tobaccoSmoking, btn_tobaccoSmoking_SL, btn_dietLifestyle;
    String ContactNo;
    private DatabaseHelperRP mDatabaseHelper;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Modules.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        mDatabaseHelper=new DatabaseHelperRP(this);

        btn_risktriageCVA=(Button)findViewById(R.id.btn_risktriageCVA);
        btn_risktriageMI=(Button)findViewById(R.id.btn_risktriageMI);
        btn_risktriageDiabetic=(Button)findViewById(R.id.btn_risktriageDiabetic);
        btn_IDRSmodified=(Button)findViewById(R.id.btn_IDRSmodified);
        btn_physicalActivity=(Button)findViewById(R.id.btn_physicalActivity);
        btn_tobaccoSmoking=(Button)findViewById(R.id.btn_tobaccoSmoking);
        btn_tobaccoSmoking_SL=(Button)findViewById(R.id.btn_tobaccoSmokingSL);
        btn_dietLifestyle=(Button)findViewById(R.id.btn_dietLifestyle);

        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");
        Toast.makeText(this, "" +ContactNo, Toast.LENGTH_SHORT).show();




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
                    startActivity(intent);
                }
            }
        });

    }
}
