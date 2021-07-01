package com.example.insertdataretrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class changeUser implements Runnable {
    String name, WS;

   public changeUser (String mname, String mWS){
       name = mname;
       WS = mWS;
   }

    @Override
    public void run() {
        chngUser taskService = MyClient.getInstance().chng_user();
        Call<ResponseBody> call = taskService.chngdata(name, WS);
        ResponseBody response = null;
        try {
            response = call.execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

