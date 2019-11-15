package com.example.gojekassignment.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.gojekassignment.service.model.RepositoryResponse;
import com.example.gojekassignment.service.repository.DataRepository;

import java.util.List;


public class RepositoryResponseViewModel extends AndroidViewModel {

    DataRepository dataRepository;

    public RepositoryResponseViewModel(Application mApplication) {
        super(mApplication);
        try {
            dataRepository = DataRepository.getInstance(mApplication);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<RepositoryResponse>> getMeetingResponse(String language,String since) {
        return (LiveData<List<RepositoryResponse>>) dataRepository.getRepositoryLiveData(language,since);
    }
}
