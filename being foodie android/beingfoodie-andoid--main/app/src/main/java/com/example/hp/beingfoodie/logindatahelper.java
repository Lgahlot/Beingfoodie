package com.example.hp.beingfoodie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.concurrent.Callable;

public class logindatahelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Info.db";
    public static final String TABLE_NAME="STUDENTS_TABLE";
    public static final String TABLE_NAME1="ORDERS";


    public static final String COL0="NAME";
    public static final String COL1="PHONE_NUMBER";
    public static final String COL2="EMAIL";
    public static final String COL3="PASSWORD";
    public static final String COL01="Phone_number";
    public static final String COL02="order_items";
    public static final String COL03="final_price";

    public logindatahelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "  + TABLE_NAME + "(  NAME TEXT,PHONE_NUMBER TEXT PRIMARY KEY,EMAIL TEXT,PASSWORD TEXT )" );
        db.execSQL(" CREATE TABLE "  + TABLE_NAME1 + "(  Phone_number TEXT ,order_items TEXT,final_price TEXT )" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OLDVERSION, int NEWVERSION)
    {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public boolean Insertdata(String Name,String phonenumber,String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL0,Name);
        contentValues.put(COL1,phonenumber);
        contentValues.put(COL2,email);
        contentValues.put(COL3,password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();

        return result != -1;
    }
    public boolean Insertdata1(String phonenumber, String orderl, int finalp)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL01,phonenumber);
        contentValues.put(COL02,orderl);
        contentValues.put(COL03,finalp);
        long result = db.insert(TABLE_NAME1,null,contentValues);
        db.close();

        return result != -1;
    }



    public Cursor getalldata(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("Select * from " + TABLE_NAME ,null);
        return res;

        }
    public Cursor getalldata1(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("Select * from " + TABLE_NAME1 ,null);
        return res;

    }

   public boolean updateinfo(String Name,String phonenumber,String email,String password)
        {
            SQLiteDatabase db =this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(COL0,Name);
            contentValues.put(COL1,phonenumber);
            contentValues.put(COL2,email);
            contentValues.put(COL3,password);
            db.update(TABLE_NAME,contentValues,"PHONE_NUMBER = ?",new String[]{phonenumber});
            return true;
        }

        public Integer deletedata(String phonenumber)
        {
           SQLiteDatabase db = this.getWritableDatabase();
           int del=db.delete(TABLE_NAME,"PHONENUMBER=?",new String[]{phonenumber});
           return del;

        }

    public Cursor getData(String phonen) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME1 + " where Phone_number=\'"+ phonen + "\'", null);
        return res;
    }
    public Cursor check(String us)
    {   SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where PHONE_NUMBER=\'"+ us + "\'", null);
        return res;
    }

}
