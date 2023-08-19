package com.example.hp.beingfoodie;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.beingfoodie.FoodMenu;

public class LogIn extends AppCompatActivity {
    Button b;
    EditText e1,e2;
    logindatahelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);
        mydb=new logindatahelper(this);
        e1= findViewById(R.id.un);
        e2= findViewById(R.id.pass);
        b= findViewById(R.id.lg);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chk()){
                    Intent i=new Intent(LogIn.this,FoodMenu.class);
                    i.putExtra("phonen",e1.getText().toString());
                    startActivity(i);
                }
                else
                    Toast.makeText(LogIn.this, "INVALID ATTEMPT TO LOG IN", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public Boolean chk() {
        Cursor res=mydb.getalldata();
        String username=e1.getText().toString();
        String password=e2.getText().toString();
        StringBuffer sb=new StringBuffer();
        String us,ps;
        if((res!=null)&&(res.getCount()>0)){
            while(res.moveToNext()){
                sb.append(res.getString(1));
                us= String.valueOf(sb);
                if(us.equals(username)){
                    sb.delete(0,sb.length());
                    sb.append(res.getString(3));
                    ps= String.valueOf(sb);
                    if(ps.equals(password))
                        return true;
                    else
                        return false;
                }
            }
        }
        res.moveToFirst();
        return false;
    }
}
