package com.example.insertdataretrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class chngAdminPass implements Runnable {
    String pass;
    public chngAdminPass(String mpass) {
        pass = mpass;
    }

    @Override
    public void run() {

        ChangeAdmin taskService = MyClient.getInstance().chngAdmin();
        Call<ResponseBody> call = taskService.chngdata(pass);
        ResponseBody response = null;

        try {
            response = call.execute().body();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
