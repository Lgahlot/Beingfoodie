package com.example.hp.beingfoodie;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class DetailsStaff extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mdl;
    ActionBarDrawerToggle mtoggle;
    logindatahelper lg=new logindatahelper(this);
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    ListView l;
    AlertDialog.Builder alert1;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_staff);
        mdl = findViewById(R.id.drawer_layout);
        listitem=new ArrayList<>();
        alert1=new AlertDialog.Builder(this);
        l=(ListView)findViewById(R.id.ls);
        mtoggle=new ActionBarDrawerToggle(this,mdl,R.string.open,R.string.close);
        mdl.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nv= findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                alert1.setTitle("Order complete?");
                if(flag==0)
                alert1.setMessage("click yes if order is complete and recieve payment.");
                else
                    alert1.setMessage("Order successfully completed");
                alert1.setIcon(R.drawable.ic_shopping_cart_black_24dp);
                if(flag==0){
                alert1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        flag=1;
                        Toast.makeText(DetailsStaff.this, "order successfully completed", Toast.LENGTH_SHORT).show();

                    }
                });
                alert1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });}
                else{
                    alert1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    alert1.setNegativeButton("Return", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });

                }
                AlertDialog alertDialog = alert1.create();
                alertDialog.show();
                flag=0;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.it2:{
                show();
                return true;
            }

            case R.id.it3:{
                Intent i=new Intent(DetailsStaff.this,SignUp.class);
                startActivity(i);
                return true;
            }

        }
        return true;
    }

    private void show() {
        Cursor res=lg.getalldata1();
        StringBuffer sb=new StringBuffer();
        if((res!=null)&&(res.getCount()>0))
        {
            while(res.moveToNext())
            {
                sb.append("phoneno "+res.getString(0)+"\n");
                sb.append("order "+res.getString(1)+"\n");
                sb.append("final "+res.getString(2)+"\n");
                listitem.add(sb.toString());
                sb.delete(0,(sb.length()-1));
            }
            adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listitem);
            l.setAdapter(adapter);
        }

    }
}
