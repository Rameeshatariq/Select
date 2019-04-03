package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TobbaccoSmokingSL extends AppCompatActivity {

    private DatabaseHelperRP mDatabaseHelper;
    private RadioButton radiovalueQ1, radiovalueQ2;
    private String ContactNo, tool6b_Q1, tool6b_Q2;
    private RadioGroup rd_TS_SL_Q1, rd_TS_SL_Q2;
    private Button btn_TS_SL_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tobbacco_smoking_sl);

        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        mDatabaseHelper=new DatabaseHelperRP(this);

        rd_TS_SL_Q1=(RadioGroup)findViewById(R.id.rd_TS_SL_Q1);
        rd_TS_SL_Q2=(RadioGroup)findViewById(R.id.rd_TS_SL_Q2);

        btn_TS_SL_submit=(Button)findViewById(R.id.btn_TS_SL_submit);
        btn_TS_SL_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool6bData();
                String tool6b="Completed";
                mDatabaseHelper.updateTool6bStatus(ContactNo,tool6b);
                Toast.makeText(TobbaccoSmokingSL.this, "Tool6b Completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TobbaccoSmokingSL.this, Modules.class);
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });



    }

    private void addTool6bData() {
        radiovalueQ1 = (RadioButton) this.findViewById(rd_TS_SL_Q1.getCheckedRadioButtonId());
        tool6b_Q1 = radiovalueQ1.getText().toString();

        radiovalueQ2 = (RadioButton) this.findViewById(rd_TS_SL_Q2.getCheckedRadioButtonId());
        tool6b_Q2 = radiovalueQ2.getText().toString();

        boolean isInserted = mDatabaseHelper.addTool6bData(ContactNo, tool6b_Q1, tool6b_Q2);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }

}
