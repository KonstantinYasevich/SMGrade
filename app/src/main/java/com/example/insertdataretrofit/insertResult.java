package com.example.insertdataretrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface insertResult {
    @FormUrlEncoded
    @POST("insertResult.php")
    Call<ResponseBody> insertdata(
            @Field("date_time")String date_time,
            @Field("WorkSpace")String WorkSpase,
            @Field("grade")String grade

    );
}
