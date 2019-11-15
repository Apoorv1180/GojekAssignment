package com.example.gojekassignment.service.repository;


import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.gojekassignment.service.model.RepositoryResponse;
import com.example.gojekassignment.service.network.ApiService;
import com.example.gojekassignment.util.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {

    private static DataRepository dataRepository;
    private static Context context;
    private ApiService apiService;


    private DataRepository(Application application) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public synchronized static DataRepository getInstance(Application application) {
        if (dataRepository == null) {
            if (dataRepository == null) {
                dataRepository = new DataRepository(application);
                context = application.getApplicationContext();
            }
        }
        return dataRepository;
    }

    public LiveData<List<RepositoryResponse>> getRepositoryLiveData(String language,String since) {

        final MutableLiveData<List<RepositoryResponse>> repositoryResponseData = new MutableLiveData<>();

        apiService.getRepoList(language,since).enqueue(new Callback<List<RepositoryResponse>>() {
            @Override
            public void onResponse(Call<List<RepositoryResponse>> call, Response<List<RepositoryResponse>> response) {
                repositoryResponseData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RepositoryResponse>> call, Throwable t) {
                repositoryResponseData.setValue(null);
            }
        });
        return repositoryResponseData;
    }
}