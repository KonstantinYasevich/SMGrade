package com.example.insertdataretrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class insResultText implements Runnable {
    String WorkSpace, text, UserName;
    public insResultText(String mWS, String mtext, String mUserName){
        WorkSpace = mWS;
        text = mtext;
        UserName = mUserName;
    }
    public void run() {
        insertResultText taskService = MyClient.getInstance().getMyApi_resulttext();
        Call<ResponseBody> call = taskService.insertdata(text, WorkSpace, UserName);
        ResponseBody response = null;
        try {
            response = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
