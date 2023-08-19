package com.example.hp.beingfoodie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class staff extends AppCompatActivity {
    Button log;
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        log= findViewById(R.id.login);
        et1= findViewById(R.id.un);
        et2= findViewById(R.id.pass);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=et1.getText().toString();
                String s2=et2.getText().toString();
                if((s1.equals("admin"))&&(s2.equals("admin")))
                { //Toast.makeText(getApplicationContext(),"Log In Successful",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(staff.this,DetailsStaff.class);
                    startActivity(i);

                }
                else
                    if((s1.equals(""))||(s2.equals("")))
                    Toast.makeText(getApplicationContext(),"Sorry! Fields Not Filled",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Either Username or Password Incorrect",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
