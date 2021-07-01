package com.example.insertdataretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class chngAdmin extends AppCompatActivity {
    Button button;
    EditText pass;
    String mpass;
    Context mcontext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chng_admin);
        button = (Button) findViewById(R.id.chngAdminPass);
        pass = (EditText) findViewById(R.id.editTextAdminPass);
        View.OnClickListener oclchngPass = new View.OnClickListener() {
            public void onClick(View v) {
                chngPass();
                Runnable r = new ParseJsonWS(mcontext);
                Thread t = new Thread(r);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                }
            }

        };
        button.setOnClickListener(oclchngPass);
    }
    public void chngPass(){
        mpass = pass.getText().toString().trim();

        Runnable r = new chngAdminPass(mpass);
        Thread t = new Thread(r);
        t.start();
        try {
            t.join();


        } catch (InterruptedException e) {

        }
    }
}