package com.example.baithi1.Network;



import com.example.baithi1.Model.Animals;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {
    @GET("v2/list?page=2&limit=100")
    Call<List<Animals>> getAnimals();
}
