package com.example.insertdataretrofit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gradle";
    public static final String TABLE_WS = "Work_Space";
    public static final String TABLE_USER = "user";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASS = "pass";
    public static final String KEY_USER_WS = "Work_Space_Name";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_WS +  "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, " + KEY_PASS + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER +  "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, " + KEY_USER_WS + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WS);
        onCreate(db);
    }
    public List<String> getAllws(){
        List<String> ws= new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_WS;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                ws.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }

        return ws;
    }
    public List<String> getAlluser(){
        List<String> ws= new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                ws.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }

        return ws;
    }
    public List<String> getWSfromUser(String name){
        List<String> ws= new ArrayList<>();
        String selectQuery = "SELECT  " + KEY_USER_WS + " FROM " + TABLE_USER + " WHERE name='" + name + "'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                ws.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        return ws;
    }
    public List<String> getUSERfromWS(String mws){

        List<String> ws= new ArrayList<>();
        String selectQuery = "SELECT  " + KEY_NAME + " FROM " + TABLE_USER + " WHERE Work_Space_Name='" + mws + "'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                ws.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        return ws;
    }
}
