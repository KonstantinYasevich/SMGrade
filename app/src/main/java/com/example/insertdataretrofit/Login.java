package com.example.insertdataretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;
    Spinner spinner;
    Button btn;
    EditText editText;
    String pass;
    String ws;
    Context mcontext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        //Context mcontext = this;
        Runnable r = new ParseJsonWS(mcontext);
        Thread t = new Thread(r);
        spinner = (Spinner)findViewById(R.id.spinner);
        Runnable r1 = new SpinnerData(spinner, mcontext);
        Thread t1 = new Thread(r1);

        Runnable r2 = new ParseJsonUSER(mcontext);
        Thread t2 = new Thread(r2);
        t2.start();
        try {
            t2.join();
            // System.out.println("weqwe1234");
        } catch (InterruptedException e) {

        }

        t.start();


        try {
            t.join();

        } catch (InterruptedException e) {

        }

            t1.start();



        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
      //  dbHelper.onCreate();


        btn = (Button) findViewById(R.id.btn_insert);
        editText = (EditText) findViewById(R.id.et_pass);
        OnClickListener oclbtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = editText.getText().toString().trim();
                ws = spinner.getSelectedItem().toString();
                Cursor cursor = db.rawQuery(("SELECT pass FROM Work_Space WHERE name = " + "\"" + ws + "\""), null);

                if (cursor.getCount()!=0){
                cursor.moveToLast();
                String cur1  = cursor.getString(0);
                if (pass.length()==0){
                    Toast toast = Toast.makeText(Login.this, "Введите пароль", Toast.LENGTH_LONG);
                    toast.show();}

                else {
                    if(ws.equals("Admin") ){
                        if (pass.equals(cur1)){

                            startActivity(new Intent(Login.this, Admin.class));
                        }
                        else {
                            Toast toast = Toast.makeText(Login.this, "Неверный пароль", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                    else {
                    if (pass.equals(cur1) ){
                        Intent intent = new Intent(Login.this, GRADE.class);
                        intent.putExtra("WS", ws);
                        System.out.println(ws);
                        startActivity(intent);
                    }
                    else {
                        Toast toast = Toast.makeText(Login.this, "Неверный пароль", Toast.LENGTH_LONG);

                        toast.show();

                    }}
                }

            }
            else
                {
                    Toast toast = Toast.makeText(Login.this, "Неверный пароль123", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };
        btn.setOnClickListener(oclbtn);

    }
    protected void onResume() {

        super.onResume();
        editText = (EditText) findViewById(R.id.et_pass);
        editText.setText("");
        List<String> ws1;
        //Context mcontext = this;
        spinner = (Spinner)findViewById(R.id.spinner);
        dbHelper = new DBHelper(mcontext);
        db = dbHelper.getWritableDatabase();
        ws1= dbHelper.getAllws();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mcontext,
                android.R.layout.simple_spinner_item, ws1);
        spinner.setAdapter(dataAdapter);





    }

}