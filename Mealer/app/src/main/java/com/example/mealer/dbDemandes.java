package com.example.mealer;

import android.content.ContentValues;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import androidx.annotation.Nullable;

/**
 * Created by Jazia on 2022-10-21.
 */

public class dbDemandes extends SQLiteOpenHelper {
    public static final String DBNAME = "Demandes.db";
    public dbDemandes(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table users(number INTEGER primary key, repas TEXT, cuisinier TEXT, client TEXT, status INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists users");
    }

    public void insertFirst(){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("number", 0);
        values.put("status", 0);
        DB.insert("users", null, values);
    }

    public void insertData(String repas, String client, String cuisinier) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        addNum();
        int numm=checkNum();
        values.put("number", numm);
        values.put("repas", repas);
        values.put("cuisinier", cuisinier);
        values.put("client", client);
        values.put("status", 0);
        DB.insert("users", null, values);
    }

    public int checkstatus(int numero) {
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return c.getInt(4);
    }
    public Boolean isClient(String name, int numero){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return (c.getString(3)).equals(name);
        //return ("j").equals(name);
    }
    public Boolean isCook(String name, String cook, int numero){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        //return ((c.getString(3)).equals(name)&&(c.getString(2)).equals(cook)&&(c.getInt(4)==1));
        return ((c.getString(3)).equals(name)&&(c.getString(2)).equals(cook));
        // return ("j").equals(name);
    }
    public Boolean showCook(String cook, int numero){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        //return ((c.getString(3)).equals(name)&&(c.getString(2)).equals(cook)&&(c.getInt(4)==1));
        return ((c.getString(2)).equals(cook)&&c.getInt(4)==0);
        // return ("j").equals(name);
    }
    public String getMeal(int numero){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return c.getString(1);
    }
    public String getClient(int numero){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return c.getString(3);
    }

    public int checkNum(){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {"0"}, null, null, null, null);
        c.moveToNext();
        return c.getInt(4);
    }
    public void addNum(){
        int num=checkNum()+1;
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("number", 0);
        contentValues.put("status", num);
        DB.update("users", contentValues, "number = ?", new String[] {"0"});
    }
    public void setstatus(int numero, int status) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("number", numero);
        contentValues.put("status", status);
        DB.update("users", contentValues, "number = ?", new String[] {String.valueOf(numero)});
    }
}