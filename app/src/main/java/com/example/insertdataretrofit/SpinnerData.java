package com.example.insertdataretrofit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class SpinnerData implements Runnable
{
    Spinner spinner;
    List<String> ws1;
    DBHelper dbHelper;
    SQLiteDatabase db;
    Context context;
    public SpinnerData(Spinner spinner1, Context context1){
        spinner = spinner1;
        context = context1;
    }
    public void run()  {
       dbHelper = new DBHelper(context);
       db = dbHelper.getWritableDatabase();
       ws1= dbHelper.getAllws();
       ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
        android.R.layout.simple_spinner_item, ws1);
       spinner.setAdapter(dataAdapter);


    }

}
