package com.example.insertdataretrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class deleteWS implements Runnable{
    String name;
    public deleteWS (String mname){
        name = mname;
    }

    @Override
    public void run() {
        dellWS taskService = MyClient.getInstance().dellMyApi();
        Call<ResponseBody> call = taskService.delldata(name);
        ResponseBody response = null;
        try {
            response = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
