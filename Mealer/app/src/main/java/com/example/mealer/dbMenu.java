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

public class dbMenu extends SQLiteOpenHelper {
    public static final String DBNAME = "Repas.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_KEY = "nom";
    public dbMenu(Context context) {
        super(context, DBNAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table users(username TEXT, nom TEXT primary key, type TEXT, cuisine Text, ingredients TEXT, allergenes TEXT, prix DOUBLE, description TEXT, offert INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists users");
    }


    public Boolean insertData(String username, String nom, String type, String cuisine, String ingredients, String allergenes, Double prix, String description) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("nom", nom);
        contentValues.put("type", type);
        contentValues.put("cuisine", cuisine);
        contentValues.put("ingredients", ingredients);
        contentValues.put("allergenes", allergenes);
        contentValues.put("prix", prix);
        contentValues.put("description", description);
        contentValues.put("offert", 0);

        //contentValues.put("role", role);
        long result = DB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }



    public Boolean checkOffert(String username, String nom) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ? and nom = ?", new String[]{username, nom});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            int offert=cursor.getInt(8);
            if (offert==1)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public void offrir(String username, String nom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("offert", 1);
        db.update("users", values, COLUMN_KEY+"=?", new String[]{nom});
        db.close();
    }

    public void enlever(String username, String nom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("offert", 0);
        db.update("users", values, COLUMN_KEY+"=?", new String[]{nom});
        db.close();
    }

    public Boolean delete(String name){

        boolean result =false;

        SQLiteDatabase DB = this.getWritableDatabase();
        String query="Select * FROM "+TABLE_NAME+" WHERE "+COLUMN_KEY+" =\""+name+"\"";

        Cursor cursor = DB.rawQuery(query, null);

        if(cursor.moveToFirst()){
            DB.delete(TABLE_NAME, COLUMN_KEY+ "=?", new String[]{name});
            cursor.close();
            result=true;
        }
        DB.close();
        return result;
    }

    public Cursor getinfo(int pos){

        SQLiteDatabase DB = this.getReadableDatabase();
        String query ="SELECT * FROM users LIMIT "+pos+",1";
        Cursor cursor = DB.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return cursor;
        }
        return null;
    }
    public String getCuis(String nom){
        SQLiteDatabase DB=this.getReadableDatabase();
        Cursor s= DB.query("users", null, "nom = ? ", new String[] {nom}, null, null, null, null);
        s.moveToNext();
        return s.getString(0);
    }
}