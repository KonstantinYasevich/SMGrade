package com.example.insertdataretrofit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class insResult implements Runnable {
    String WorkSpace, date_time;
    Date date_timeD;
    String grade;

    public insResult(String mWorkSpace, String mgrade){
        WorkSpace = mWorkSpace;
        grade = mgrade;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date_timeD = new Date();
        date_time = formater.format(date_timeD);

    }
    public void run() {
        System.out.println("6");
        insertResult taskService = MyClient.getInstance().getMyApi_result();
        Call<ResponseBody> call = taskService.insertdata(date_time, WorkSpace, grade);
        ResponseBody response = null;
        try {
            response = call.execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

