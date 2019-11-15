package com.example.gojekassignment.service.network;
import com.example.gojekassignment.service.model.RepositoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("repositories")
    Call<List<RepositoryResponse>> getRepoList(@Query("language") String language, @Query ("since") String since);
}