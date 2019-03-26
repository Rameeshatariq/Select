package com.example.cv.select;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterParticipant extends AppCompatActivity {
    private Button btn_pregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_participant);

        btn_pregister=(Button) findViewById(R.id.btn_p_register);
        btn_pregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterParticipant.this, Modules.class);
                startActivity(intent);
            }
        });
    }
}
