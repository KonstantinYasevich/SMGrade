package com.example.insertdataretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONApi_GetUSER {
    @GET("get_user.php")
    Call<List<Post>> getUSER();

}
