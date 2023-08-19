package com.example.hp.beingfoodie;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class order_placed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        ActionBar ab=getSupportActionBar();
        ab.hide();
        Thread mythraed=new Thread()
        {
            public void run()
            {
                try{
                    sleep(2000);
                    Intent i=new Intent(order_placed.this,FoodMenu.class);
                    startActivity(i);
                    finish();
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        mythraed.start();
    }
}
