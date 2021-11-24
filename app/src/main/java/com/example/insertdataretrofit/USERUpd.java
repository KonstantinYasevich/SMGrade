package com.example.insertdataretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class USERUpd extends AppCompatActivity {
    private EditText nameUser;
    String User_WS;
    Button insert;
    Button chngUser;
    Button dell;
    Spinner spinner;
    Spinner spinner1;
    Spinner spinner2;
    List<String> ws1;
    DBHelper dbHelper;
    String userfordell;
    TextView textView1;

    Context mcontext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_s_e_r_upd);
        insert = (Button) findViewById(R.id.insert_User);
        chngUser = (Button) findViewById(R.id.chng_WS);
        // dell = (Button) findViewById(R.id.dell_WS);
        spinner = (Spinner)findViewById(R.id.User);
        spinner1 = (Spinner)findViewById(R.id.WS_User);
        spinner2 = (Spinner)findViewById(R.id.WS_User1);
        Runnable r = new ParseJsonWS(mcontext);
        Thread t = new Thread(r);
        try {
            t.join();
            // System.out.println("weqwe1234");
        } catch (InterruptedException e) {
            System.out.println("бля");
        }
        Runnable r1 = new ParseJsonUSER(mcontext);
        Thread t1 = new Thread(r1);
       t1.start();
        try {
            t1.join();

        } catch (InterruptedException e) {
            System.out.println("бля");
        }
        SpinnerData();
        SpinnerData1();


        textView1 = (TextView)findViewById(R.id.textView8);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                textView1.setText(" " + dbHelper.getWSfromUser(spinner.getSelectedItem().toString().trim()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

       textView1.setText(" " + dbHelper.getWSfromUser(spinner.getSelectedItem().toString().trim()));

        View.OnClickListener oclinsertUser = new View.OnClickListener() {
            public void onClick(View v) {
                nameUser=(EditText)findViewById(R.id.Name_User);
                User_WS = spinner1.getSelectedItem().toString().trim();
                Runnable r1 = new insUser(nameUser.getText().toString().trim(), User_WS);
                Thread t1 = new Thread(r1);
                ws1 = dbHelper.getAlluser();
                int s = 0;

                if (User_WS.isEmpty() || nameUser.getText().toString().trim().isEmpty()){
                    Toast toast = Toast.makeText(USERUpd.this, "Введите все необходимые данные", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    for (int i = 0; i < ws1.size(); i++){
                        if (nameUser.getText().toString().trim().equals(ws1.get(i).toString().trim())){
                            s = 1;

                        }
                    }
                    if (s == 1){
                        Toast toast = Toast.makeText(USERUpd.this, "Пользователь уже существует", Toast.LENGTH_LONG);
                        toast.show();
                    }

                    else {
                        t1.start();
                        try {

                            t1.join();}
                        catch (InterruptedException e){}

                        InputMethodManager imm = (InputMethodManager)getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(nameUser.getWindowToken(), 0);

                        nameUser.setText("");
                        nameUser.clearFocus();

                        Runnable r = new ParseJsonUSER(mcontext);
                        Thread t = new Thread(r);
                        t.start();
                        try {
                            t.join();
                            // System.out.println("weqwe1234");
                        } catch (InterruptedException e) {

                        }
                        SpinnerData();
                        SpinnerData1();
                    }}}
        };

        insert.setOnClickListener(oclinsertUser);



    }


    private void DellData() {

        String name=spinner.getSelectedItem().toString();
        if (name.equals("admin")) {
            Toast toast = Toast.makeText(WSupd.this, "Невозможно удалить учетную запись Администратора", Toast.LENGTH_LONG);
            toast.show();
        }

        Runnable r2 = new deleteUser(name);
        Thread t2 = new Thread(r2);
        t2.start();
        try {
            t2.join();

        } catch (InterruptedException e) {

        }

    }
    private void ChngData() {

        String name=spinner.getSelectedItem().toString();
        String WS=spinner2.getSelectedItem().toString();

        Runnable r = new changeUser(name, WS);
        Thread t = new Thread(r);
        t.start();
        try {
            t.join();

        } catch (InterruptedException e) {

        }
        textView1.setText(" " + dbHelper.getWSfromUser(spinner.getSelectedItem().toString().trim()));
    }

    private void SpinnerData() {

        ws1 = dbHelper.getAllws();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ws1);

        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
    }
    private void SpinnerData1() {
        System.out.println("ew");
        ws1 = dbHelper.getAlluser();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ws1);
        spinner.setAdapter(dataAdapter);
        System.out.println("ew1");

    }

    public void showAlertWithTwoButton(View v){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Подтвердить удаление...");
        alertDialog.setMessage("Вы уверены, что хотите удалить " + spinner.getSelectedItem().toString() + "?");
        alertDialog.setIcon(android.R.drawable.ic_delete);

        alertDialog.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                DellData();

                Runnable r = new ParseJsonUSER(mcontext);
                Thread t = new Thread(r);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                }
                SpinnerData1();
            }
        });

        alertDialog.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
    public void showAlertchngUser(View v){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Подтвердить изменение...");
        alertDialog.setMessage("Вы уверены, что хотите изменить рабочее место пользователя: " + spinner.getSelectedItem().toString() + " на: " + spinner2.getSelectedItem().toString() + "?");
        alertDialog.setIcon(android.R.drawable.ic_delete);
        alertDialog.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                ChngData();

                Runnable r = new ParseJsonUSER(mcontext);
                Thread t = new Thread(r);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                }
                SpinnerData1();
                textView1.setText(" " + dbHelper.getWSfromUser(spinner.getSelectedItem().toString().trim()));
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