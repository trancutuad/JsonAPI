package com.example.baithi1.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {

//    https://picsum.photos/v2/list?page=2&limit=100

    //define base url
    public static String base_url = "https://picsum.photos/";

    //retrofit

    public static Retrofit getClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


    public static APIinterface apIinterface(){
        return getClient().create(APIinterface.class);
    }
}
