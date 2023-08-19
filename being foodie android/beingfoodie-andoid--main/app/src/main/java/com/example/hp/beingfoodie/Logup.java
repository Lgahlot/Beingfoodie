package com.example.hp.beingfoodie;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Logup extends AppCompatActivity {
    EditText e1, e2, e3, e4;
    Button b1;
    logindatahelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logup);
        e1 = findViewById(R.id.un1);
        e2 = findViewById(R.id.email);
        e3 = findViewById(R.id.un);
        e4 = findViewById(R.id.pass);
        mydb = new logindatahelper(this);
        b1 = findViewById(R.id.abc);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e1.getText().toString();
                String email = e2.getText().toString();
                String phone = e3.getText().toString();
                String password = e4.getText().toString();
                if ((name.equals("")) || (name.equals("")) || (email.equals("")) || (phone.equals("")) || (password.equals(""))) {
                    Toast.makeText(Logup.this, "All Fields Are necessary", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean result = mydb.Insertdata(name, phone, email, password);
                    if (result == true) {
                        Toast.makeText(Logup.this, "Data Inserted successfully", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(Logup.this,LogIn.class);
                        startActivity(i);

                    } else {
                        Toast.makeText(Logup.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}