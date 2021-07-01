package com.example.insertdataretrofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParseJsonWS implements Runnable {
    SQLiteDatabase db;
    DBHelper dbHelper;
    List<Post> WS = new ArrayList<>();
    Context context;


    public ParseJsonWS(Context mcontext){
        context = mcontext;
    }

    public void run() {

        System.out.println("4");
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        JSONApi_GetWS taskService = MyClient.getInstance().getJSONApi_GetWS();
        Call<List<Post>> call = taskService.getWS();
        List<Post> response = null;
        try {
            response = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
                WS = response;
                Cursor qwe = db.rawQuery("SELECT * FROM user", null);
                qwe.moveToLast();
                if (qwe.getCount()!=WS.size() || !qwe.getString(1).equals(WS.get(WS.size()-1).getName()) || !qwe.getString(2).equals(WS.get(WS.size()-1).getPass() )){
                  db.execSQL("DELETE FROM Work_Space");
                    System.out.println(qwe);
                    for (int i = 0 ; i < WS.size(); i++) {
                        String name = WS.get(i).getName();
                        String pass = WS.get(i).getPass();
                        String id = WS.get(i).getId();
                        contentValues.put(DBHelper.KEY_NAME, name);
                        contentValues.put(DBHelper.KEY_PASS, pass);
                        db.insert(DBHelper.TABLE_WS, null, contentValues);
                        Cursor qwe1 = db.rawQuery("SELECT * FROM Work_Space", null);
                        qwe1.moveToLast();
                        System.out.println(qwe1.getCount());
                        System.out.println(qwe1.getString(1));

                    }}
    }
}
