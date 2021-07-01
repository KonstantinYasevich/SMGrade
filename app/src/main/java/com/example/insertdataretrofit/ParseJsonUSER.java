package com.example.insertdataretrofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ParseJsonUSER implements Runnable{
    SQLiteDatabase db;
    DBHelper dbHelper;
    List<Post> USER = new ArrayList<>();
    Context context;
    public ParseJsonUSER(Context mcontext){
        context = mcontext;
    }

    public void run() {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        JSONApi_GetUSER taskService = MyClient.getInstance().getJSONApi_GetUSER();
        Call<List<Post>> call = taskService.getUSER();
        List<Post> response = null;
        try {
            response = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        USER = response;
        Cursor qwe = db.rawQuery("SELECT * FROM user", null);
        qwe.moveToLast();
        System.out.println(qwe.getCount());
        if (qwe.getCount() ==0){
            System.out.println("masd");
        }
        //if (qwe.getCount() == 0 || qwe.getCount()!=USER.size() || !qwe.getString(1).equals(USER.get(USER.size()-1).getName()) || !qwe.getString(2).equals(USER.get(USER.size()-1).getUser_WS() )){
            System.out.println(qwe + "asasd");
            db.execSQL("DELETE FROM user");
            System.out.println(qwe);
            for (int i = 0 ; i < USER.size(); i++) {
                String name = USER.get(i).getName();
                String user_WS = USER.get(i).getUser_WS();
                System.out.println(name + "name");
                System.out.println(user_WS + "WS");
                String id = USER.get(i).getId();
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_USER_WS, user_WS);
                db.insert(DBHelper.TABLE_USER, null, contentValues);
                Cursor qwe1 = db.rawQuery("SELECT * FROM user", null);
                qwe1.moveToLast();
                System.out.println(qwe1.getCount());
                System.out.println(qwe1.getString(1));

    //        }
    }

    }
}
