package com.example.insertdataretrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface insertResultText {
    @FormUrlEncoded
    @POST("insertResultText.php")
    Call<ResponseBody> insertdata(
            @Field("text")String text,
            @Field("WorkSpace")String WorkSpace,
            @Field("UserName")String UserName

    );
}
