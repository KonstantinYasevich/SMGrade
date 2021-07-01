package com.example.insertdataretrofit;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class insWS implements Runnable{
        EditText nameWS,passWS;
    public insWS(EditText mnameWS, EditText mpassWS){
        nameWS = mnameWS;
        passWS = mpassWS;
    }
    public void run() {
        String name=nameWS.getText().toString().trim();
        String pass=passWS.getText().toString().trim();
        insertWS taskService = MyClient.getInstance().getMyApi();
        Call<ResponseBody> call = taskService.insertdata(name, pass);
        ResponseBody response = null;
        try {
            response = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }


    } }

