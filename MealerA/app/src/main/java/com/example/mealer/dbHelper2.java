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

public class dbHelper2 extends SQLiteOpenHelper {
    public static final String DBNAME = "cuisinier.db";
    //public boolean suspended;
    //public String endDate;
    public dbHelper2(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table users(username TEXT primary key, password TEXT, name TEXT, lastName TEXT, address TEXT, description TEXT, suspended TEXT, date TEXT, traite TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists users");
    }

    public void upgradeStatus(String username, String password, String date){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("suspended", "1");
        contentValues.put("date", date);

        DB.update("users", contentValues, "username = ?", new String[] {username});
    }
    public void upgradeStatus(String username){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("suspended", "1");
        DB.update("users", contentValues, "username = ?", new String[] {username});
        upgradeFB(username);
    }
    public void upgradeStatus(String username, String date){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("suspended", "1");
        contentValues.put("date", date);
        DB.update("users", contentValues, "username = ?", new String[] {username});
        upgradeFB(username);
    }
    public void upgradeFB(String username){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("traite", "1");
        DB.update("users", contentValues, "username = ?", new String[] {username});
    }


    public Boolean insertData(String username, String password, String name, String lastName, String address, String description) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("lastName", lastName);
        contentValues.put("address", address);
        contentValues.put("description", description);
        contentValues.put("suspended", "0");
        contentValues.put("date", "");
        contentValues.put("traite", "0");
        //contentValues.put("role", role);
        long result = DB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertData(String username, String password) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("suspended", "0");
        contentValues.put("date", "");
        contentValues.put("traite", "0");
        //contentValues.put("role", role);
        long result = DB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        String suspended="0";
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ? and password = ? and suspended = ?", new String[] {username,password,suspended});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checksuspended(String username, String password){
        String suspended="1";
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ? and password = ? and suspended =?", new String[] {username,password,suspended});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkFB(String username, String password){
        String traite="1";
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ? and password = ? and traite =?", new String[] {username,password,traite});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkdate(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ? and password = ? and suspended = 0", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Cursor getdate(String username){
        SQLiteDatabase DB=this.getReadableDatabase();
        return DB.query("users", null, "username = ? ", new String[] {username}, null, null, null, null);

    }
}