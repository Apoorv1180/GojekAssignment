package com.example.gojekassignment.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.gojekassignment.R;
import com.example.gojekassignment.viewmodel.RepositoryResponseViewModel;

public class MainActivity extends AppCompatActivity {

    //viewModel
    private RepositoryResponseViewModel repositoryResponseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repositoryResponseViewModel= ViewModelProviders.of(this).get(RepositoryResponseViewModel.class);
    }
}
