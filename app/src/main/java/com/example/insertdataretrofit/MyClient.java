package com.example.insertdataretrofit;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClient {
    private static final String BASE_URL="http://h102555691.nichost.ru/";
    private static MyClient myClient;
    private Retrofit retrofit;

    private MyClient(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized MyClient getInstance(){
        if (myClient==null){
            myClient=new MyClient();
        }
        return myClient;
    }
    public insertWS getMyApi(){
        return retrofit.create(insertWS.class);
    }
    public insertUser getMyApi_user(){
           return retrofit.create(insertUser.class);
    }
    public insertResult getMyApi_result(){
        return retrofit.create(insertResult.class);
    }
    public insertResultText getMyApi_resulttext(){ return retrofit.create(insertResultText.class); }
    public insertResultAudio getMyApi_resultaudio(){ return retrofit.create(insertResultAudio.class); }
    public chngUser chng_user(){
        return retrofit.create(chngUser.class);
    }
    public dellWS dellMyApi(){
        return retrofit.create(dellWS.class);
    }
    public ChangeAdmin chngAdmin(){
        return retrofit.create(ChangeAdmin.class);
    }
    public dellUser dellMyApi_User(){
        return retrofit.create(dellUser.class);
    }
    public JSONApi_GetWS getJSONApi_GetWS(){
        return retrofit.create(JSONApi_GetWS.class);
    }
    public JSONApi_GetUSER getJSONApi_GetUSER(){
        return retrofit.create(JSONApi_GetUSER.class);
    }
}
