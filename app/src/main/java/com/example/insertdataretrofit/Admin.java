package com.example.insertdataretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Admin extends AppCompatActivity {
    Button wsUpd;
    Button userUpd;
    Button chngAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        wsUpd = (Button) findViewById(R.id.wsUpd);
        userUpd = (Button) findViewById(R.id.userUpd);
        chngAdmin = (Button) findViewById(R.id.chngAdmin);
        OnClickListener oclwsUpd = new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, WSupd.class));
            }
        };
        OnClickListener ocluserUpd = new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, USERUpd.class));
            }
        };
        OnClickListener oclchngAdmin = new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, chngAdmin.class));
            }
        };
        wsUpd.setOnClickListener(oclwsUpd);
        userUpd.setOnClickListener(ocluserUpd);
        chngAdmin.setOnClickListener(oclchngAdmin);
    }

    }
