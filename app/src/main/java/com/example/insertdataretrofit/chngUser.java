package com.example.insertdataretrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface chngUser {
    @FormUrlEncoded
    @POST("chng_User.php")
    Call<ResponseBody> chngdata(
            @Field("name")String name,
            @Field("WS")String WS


    );
}
