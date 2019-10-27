package com.purwohadi.moviecatalogue.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvApiClient {
    public static final String BASE_URL = "https://api.themoviedb.org/3/discover/tv?api_key=f3284c069160e4cfd76c15db9f344441&language=en-US";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory
                            .create()).build();
        }
        return retrofit;
    }
}
