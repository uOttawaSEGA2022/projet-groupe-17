package com.example.mealer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbPleintes extends SQLiteOpenHelper {

    public static final String DBNAME = "Pleintes.db";
    public dbPleintes(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table users(number INTEGER primary key, pleinte TEXT, cuisinier TEXT, client TEXT, traite INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists users");
    }

    public void insertFirst(){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("number", 0);
        values.put("traite", 0);
        DB.insert("users", null, values);
    }

    public void insertData(String pleinte, String client, String cuisinier) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        addNum();
        int numm=checkNum();
        values.put("number", numm);
        values.put("pleinte", pleinte);
        values.put("cuisinier", cuisinier);
        values.put("client", client);
        values.put("traite", 0);
        DB.insert("users", null, values);
    }

    public Boolean isTraite(int numero) {
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return (c.getInt(4)!=0);
    }

    public String getPleinte(int numero){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return c.getString(1);
    }
    public String getCook(int numero){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return c.getString(2);
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
        contentValues.put("traite", num);
        DB.update("users", contentValues, "number = ?", new String[] {"0"});
    }

    public void settraite(int numero) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("number", numero);
        contentValues.put("traite", 1);
        DB.update("users", contentValues, "number = ?", new String[] {String.valueOf(numero)});
    }


    /*
    public static final String DBNAME = "Pleintes.db";
    public dbPleintes(Context context) {
        super(context, "Pleintes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table users(number INTEGER primary key, pleinte TEXT, cuisinier TEXT, client TEXT, traite INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists users");
    }

    public void insertFirst(){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("number", 0);
        values.put("traite", 0);
        DB.insert("users", null, values);
    }

    public void insertData(String pleinte, String client, String cuisinier) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        addNum();
        int numm=checkNum();
        values.put("pleinte", numm);
        values.put("cuisinier", cuisinier);
        values.put("client", client);
        values.put("traite", 0);
        DB.insert("users", null, values);
    }

    public Boolean isTraite(int numero) {
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return (c.getInt(4)!=0);
    }

    public String getPleinte(int numero){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return c.getString(1);
    }
    public String getCook(int numero){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor c=DB.query("users", null, "number = ? ", new String[] {String.valueOf(numero)}, null, null, null, null);
        c.moveToNext();
        return c.getString(2);
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
    public void settraite(int numero) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("number", numero);
        contentValues.put("status", 1);
        DB.update("users", contentValues, "number = ?", new String[] {String.valueOf(numero)});
    }
    */
}
