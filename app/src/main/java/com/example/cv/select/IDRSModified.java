package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class IDRSModified extends AppCompatActivity {
    private DatabaseHelperRP mDatabaseHelper;
    private RadioButton radiovalueQ1;
    private String ContactNo, tool4_Q1;
    private RadioGroup rd_IDRS_Q1;
    private Button btn_IDRS_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idrsmodified);

        Intent intent=getIntent();
        ContactNo=intent.getStringExtra("ContactNo");
        Toast.makeText(this, ""+ContactNo, Toast.LENGTH_SHORT).show();

        mDatabaseHelper=new DatabaseHelperRP(this);

        rd_IDRS_Q1=(RadioGroup)findViewById(R.id.rd_IDRS_Q1);
        btn_IDRS_submit=(Button)findViewById(R.id.btn_IDRS_submit);
        btn_IDRS_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTool4Data();
                String tool4="Completed";
                mDatabaseHelper.updateTool4Status(ContactNo,tool4);
                Toast.makeText(IDRSModified.this, "Tool4 Completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(IDRSModified.this, Modules.class);
                intent.putExtra("ContactNo", ContactNo);
                startActivity(intent);
            }
        });



    }

    private void addTool4Data() {
        radiovalueQ1 = (RadioButton) this.findViewById(rd_IDRS_Q1.getCheckedRadioButtonId());
        tool4_Q1 = radiovalueQ1.getText().toString();

        boolean isInserted = mDatabaseHelper.addTool4Data(ContactNo, tool4_Q1);
        if (isInserted == true) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Not Inserted Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}
