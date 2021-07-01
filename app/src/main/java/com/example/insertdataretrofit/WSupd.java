package com.example.insertdataretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WSupd extends AppCompatActivity {
    private EditText nameWS,passWS;
    Button insert;
    Button dell;
    Spinner spinner;
    List<String> ws1;
    DBHelper dbHelper;
    String wsfordell;
    Context mcontext = this;


        @Override
    protected void onCreate(Bundle savedInstanceState) {



        dbHelper = new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_supd);
        nameWS=(EditText)findViewById(R.id.NameWS);
        passWS=(EditText)findViewById(R.id.passWS);
        insert = (Button) findViewById(R.id.insert_WS);
       // dell = (Button) findViewById(R.id.dell_WS);
        spinner = (Spinner)findViewById(R.id.spinner2);

        Runnable r = new ParseJsonWS(mcontext);
        Thread t = new Thread(r);
        SpinnerData();

        View.OnClickListener oclinsertWS = new View.OnClickListener() {
            public void onClick(View v) {
                Runnable r1 = new insWS(nameWS, passWS);
                Thread t1 = new Thread(r1);
                ws1 = dbHelper.getAllws();
                int s = 0;

                if (nameWS.getText().toString().trim().isEmpty() || passWS.getText().toString().trim().isEmpty()){
                    Toast toast = Toast.makeText(WSupd.this, "Введите все необходимые данные", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                for (int i = 0; i < ws1.size(); i++){
                    if (nameWS.getText().toString().trim().equals(ws1.get(i).toString().trim())){
                        s = 1;
                        System.out.println("asddasadsdas");
                    }
                }
                if (s == 1){
                    Toast toast = Toast.makeText(WSupd.this, "Рабочее место уже существует", Toast.LENGTH_LONG);
                    toast.show();
                }

                else {
                t1.start();
                try {

                t1.join();}
                catch (InterruptedException e){}

                InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(passWS.getWindowToken(), 0);

                nameWS.setText("");
                passWS.setText("");
                passWS.clearFocus();
                nameWS.clearFocus();

                Runnable r = new ParseJsonWS(mcontext);
                Thread t = new Thread(r);
                t.start();
                try {
                    t.join();
                    // System.out.println("weqwe1234");
                } catch (InterruptedException e) {
                    System.out.println("бля");
                }
                SpinnerData();
            }}}
        };

        insert.setOnClickListener(oclinsertWS);
        passWS.clearFocus();
        nameWS.clearFocus();

    }
    private void insertData() {
        String name=nameWS.getText().toString().trim();
        String pass=passWS.getText().toString().trim();

        Call<ResponseBody> call=MyClient.getInstance().getMyApi().insertdata(name,pass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    private void DellData() {

        String name=spinner.getSelectedItem().toString();
        if (name.equals("Admin")) {
            Toast toast = Toast.makeText(WSupd.this, "Невозможно удалить учетную запись Администратора", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
        Runnable r2 = new deleteWS(name);
        Thread t2 = new Thread(r2);
        t2.start();
        try {
            t2.join();

        } catch (InterruptedException e) {

        }}

    }

    private void SpinnerData() {

        ws1 = dbHelper.getAllws();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ws1);
        spinner.setAdapter(dataAdapter);
    }
    public void showAlertWithTwoButton(View v){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Подтвердить удаление...");
        alertDialog.setMessage("Вы уверены, что хотите удалить " + spinner.getSelectedItem().toString() + "?");
        alertDialog.setIcon(android.R.drawable.ic_delete);
        alertDialog.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                DellData();

                Runnable r = new ParseJsonWS(mcontext);
                Thread t = new Thread(r);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                }
                SpinnerData();
            }
        });

        alertDialog.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}