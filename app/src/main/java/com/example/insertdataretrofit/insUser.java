package com.example.insertdataretrofit;

import android.widget.EditText;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class insUser implements Runnable{
    String name, user_WS;
    public insUser(String mnameUser, String mUser_WS){
        name = mnameUser;
        user_WS = mUser_WS;
    }
    public void run() {
        insertUser taskService = MyClient.getInstance().getMyApi_user();
        Call<ResponseBody> call = taskService.insertdata(name, user_WS);
        ResponseBody response = null;
        try {
            response = call.execute().body();
            System.out.println("7");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

