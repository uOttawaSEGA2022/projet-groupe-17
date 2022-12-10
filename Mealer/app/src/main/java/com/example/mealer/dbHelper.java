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

public class dbHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login6.db";
    public dbHelper(Context context) {
        super(context, "Login6.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table users(username TEXT primary key, password TEXT, notification INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists users");
    }


    public Boolean insertData(String username, String password) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("notification", 0);
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
    public Boolean checkNotif(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor c = DB.rawQuery("Select * from users where username = ?", new String[]{username});
        c.moveToNext();
        return (c.getInt(2)!=0);
    }
    public void setNotif(String username, int notification) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("notification", notification);
        DB.update("users", contentValues, "username = ?", new String[] {username});
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}