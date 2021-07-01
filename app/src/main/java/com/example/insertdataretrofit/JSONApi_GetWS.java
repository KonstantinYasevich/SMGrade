package com.example.insertdataretrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;

public interface JSONApi_GetWS {
    @GET("get_workspace.php")
     Call<List<Post>> getWS();

}

