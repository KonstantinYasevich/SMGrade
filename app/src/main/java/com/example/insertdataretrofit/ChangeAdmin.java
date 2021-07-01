package com.example.insertdataretrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ChangeAdmin {
    @FormUrlEncoded
    @POST("chng_Admin.php")
    Call<ResponseBody> chngdata(
            @Field("pass")String pass);

}
