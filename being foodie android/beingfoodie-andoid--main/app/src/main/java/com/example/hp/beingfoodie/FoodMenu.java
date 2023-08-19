package com.example.hp.beingfoodie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodMenu extends AppCompatActivity  {

    Bundle extra;
    String p;
    String[] titles={"Rajma_Chawal","Dosa","Chowmein","chilli_potato","chole_bature","chole_chawal","spring_rolls"};
    ListView lv;
    StringBuffer str=new StringBuffer();
    AlertDialog.Builder b;
    logindatahelper mydb;
    String s;
    int[] images={R.drawable.indian,R.drawable.south,R.drawable.chinese,R.drawable.chilli,R.drawable.choleb,R.drawable.cholec,R.drawable.spring};
    int counter[]={0,0,0,0,0,0,0};
    int sum=0;
    String descrip[]={"Price: Rs.30/- (including taxes)","Price: Rs.30/- (including taxes)","Price: Rs.20/- (including taxes)","Price: Rs.40/- (including taxes)","Price: Rs.35/- (including taxes)","Price: Rs.30/- (including taxes)","Price: Rs.40/- (including taxes)"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food_menu);
        extra=getIntent().getExtras();
        p=extra.getString("phonen");
        mydb=new logindatahelper(this);
        b=new AlertDialog.Builder(this);
        lv = findViewById(R.id.list);
        CustomAdapter cs = new CustomAdapter();
        lv.setAdapter(cs);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: {
                        sum += 30;
                        counter[0]++;
                        return ;
                    }
                    case 1: {
                        sum += 30;
                        counter[1]++;
                        return ;
                    }
                    case 2: {
                        sum += 20;
                        counter[2]++;
                        return ;
                    }
                    case 3: {
                        sum += 40;
                        counter[3]++;
                        return ;
                    }
                    case 4: {
                        sum += 35;
                        counter[4]++;
                        return ;
                    }
                    case 5: {
                        sum += 30;
                        counter[5]++;
                        return ;
                    }
                    case 6: {
                        sum += 40;
                        counter[6]++;
                        return ;
                    }



                }
            }
        });

    }


    class CustomAdapter extends BaseAdapter{

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view=getLayoutInflater().inflate(R.layout.subcategory, null);
                TextView t1=view.findViewById(R.id.t3);
                t1.setText(descrip[position]);
                ImageView img=view.findViewById(R.id.img);
                img.setImageResource(images[position]);
                TextView t=view.findViewById(R.id.t2);
                t.setText(titles[position]);
                return view;
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.cart):

            {
                for(int i=0;i<counter.length;i++)
                {
                    if(counter[i]!=0) {
                        str.append(titles[i]+"\n");
                        str.append("\t\t\t\tQuantity"+(counter[i]));
                        str.append("\n");
                    }

                }
                s= String.valueOf(str);
                AlertDialog.Builder alert=new AlertDialog.Builder(this);
                alert.setTitle("CART");
                alert.setMessage(str + "\nTOTAL VALUE  "+ (sum));
                alert.setIcon(R.drawable.ic_shopping_cart_black_24dp);
                alert.setPositiveButton("place order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(sum==0){
                            Toast.makeText(FoodMenu.this, "please enter some items!!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(sum!=0) {

                            Boolean result = mydb.Insertdata1(p, s, sum);
                        }
                        Toast.makeText(FoodMenu.this, "Order Plcaed Successfully", Toast.LENGTH_SHORT).show();
                        str.delete(0,str.length());
                        for(int i1=0;i1<descrip.length;i1++)
                            counter[i1]=0;
                        sum=0;
                        return;


                    }
                });
                alert.setNegativeButton("add more items", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        str.delete(0,str.length());
                        Toast.makeText(FoodMenu.this, "ok!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
                alert.setNeutralButton("Clear Cart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        str.delete(0,str.length());
                        for(int i1=0;i1<descrip.length;i1++)
                            counter[i1]=0;
                       sum=0;
                       return;
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
                str.delete(0,str.length());
                return true;
            }

            case (R.id.yo):
            {
                int count = 1;
                Cursor res=mydb.getalldata1();
                StringBuffer sb=new StringBuffer();

                if((res!=null)&&(res.getCount()>0))
                {
                    while(res.moveToNext())
                    {if(res.getString(0).equals("9911955393"))
                    {//sb.append("phoneno "+res.getString(0)+"\n");
                        sb.append("Order "+ ( count++ )+"\norder\n "+res.getString(1)+"\n");
                        sb.append("final price \t"+res.getString(2)+"\n\n");
                    }}

                }
                AlertDialog.Builder alert1=new AlertDialog.Builder(this);
                alert1.setTitle("ORDERS");
                alert1.setMessage(sb);
                alert1.setIcon(R.drawable.ic_shopping_cart_black_24dp);
                alert1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(FoodMenu.this, "Here are your orders!!", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alert1.create();
                alertDialog.show();
                return true;
            }

            case (R.id.i11):

            { Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.instagram.com/anubhav_goel_ag/"));
                startActivity(i);
                return true;}

            case (R.id.i12):

            { Intent i1 = new Intent();
                i1.setAction(Intent.ACTION_VIEW);
                i1.setData(Uri.parse("https://www.instagram.com/_lakshay.gahlot_/"));
                startActivity(i1);
                return true;}

            case (R.id.i13):

            {Intent i2 = new Intent();
                i2.setAction(Intent.ACTION_VIEW);
                i2.setData(Uri.parse("https://www.instagram.com/i.m.g.u.i.t.a.a.r.i.s.t/"));
                startActivity(i2);
                return true;}


            case (R.id.i2):

            { Intent i3 = new Intent(FoodMenu.this, SignUp.class);
                startActivity(i3);
                return true;}



        }

        return true;
    }}


