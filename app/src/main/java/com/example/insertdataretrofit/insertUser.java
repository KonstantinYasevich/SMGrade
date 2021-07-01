package com.example.insertdataretrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface insertUser {

    @FormUrlEncoded
    @POST("insertUser.php")

    Call<ResponseBody> insertdata(
            @Field("name")String name,
            @Field("user_WS")String user_WS

    );

}

