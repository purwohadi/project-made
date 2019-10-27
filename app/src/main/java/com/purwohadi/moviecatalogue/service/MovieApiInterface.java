package com.purwohadi.moviecatalogue.service;

import com.purwohadi.moviecatalogue.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiInterface {
    @GET("movies.json")
    Call<List<MovieModel>> getMovie();
}
