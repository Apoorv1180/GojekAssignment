package com.example.gojekassignment.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gojekassignment.R;
import com.example.gojekassignment.service.model.RepositoryResponse;
import com.example.gojekassignment.view.adapter.RepositoryResponseAdapter;
import com.example.gojekassignment.viewmodel.RepositoryResponseViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //viewModel
    private RepositoryResponseViewModel repositoryResponseViewModel;

    //RECYCLER VIEW
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RepositoryResponseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);

        repositoryResponseViewModel= ViewModelProviders.of(this).get(RepositoryResponseViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        repositoryResponseViewModel.getMeetingResponse(null,null).observe(this, new Observer<List<RepositoryResponse>>() {
            @Override
            public void onChanged(List<RepositoryResponse> repositoryResponses) {
                if(repositoryResponses!= null){
                    if(repositoryResponses.size()!=0){
                        layoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new RepositoryResponseAdapter(repositoryResponses,MainActivity.this);
                        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

                        recyclerView.setAdapter(adapter );
                    }else{

                    }
                }
            }
        });
    }
}
