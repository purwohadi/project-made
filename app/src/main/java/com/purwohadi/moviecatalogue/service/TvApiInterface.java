package com.purwohadi.moviecatalogue.service;

import com.purwohadi.moviecatalogue.model.TvModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TvApiInterface {
    @GET("Tv.json")
    Call<List<TvModel>> getMovie();
}
