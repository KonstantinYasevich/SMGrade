package com.example.insertdataretrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class deleteUser implements Runnable {
    String name;
    public deleteUser (String mname){
        name = mname;
    }

    @Override
    public void run() {
        dellUser taskService = MyClient.getInstance().dellMyApi_User();
        Call<ResponseBody> call = taskService.delldata(name);
        ResponseBody response = null;
        try {
            response = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
