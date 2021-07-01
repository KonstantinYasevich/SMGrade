package com.example.insertdataretrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface insertWS {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseBody>insertdata(
            @Field("name")String name,
            @Field("pass")String pass

    );
}
