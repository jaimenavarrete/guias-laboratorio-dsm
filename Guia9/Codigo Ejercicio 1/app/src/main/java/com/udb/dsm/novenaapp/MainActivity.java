package com.udb.dsm.novenaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.udb.dsm.novenaapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ActivityMainBinding binding;
    ReposAdapter reposAdapter;
    List<ReposResponse> repos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRecyclerView();
        binding.searchUserName.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
    }

    private void initRecyclerView() {
        reposAdapter = new ReposAdapter(repos);
        binding.listRepos.setLayoutManager(new LinearLayoutManager(this));
        binding.listRepos.setAdapter(reposAdapter);
    }

    private ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        return service;
    }

    private void searchByUser(String userName) {
        final Call<List<ReposResponse>> batch = getApiService().getReposByUserName(userName);

        batch.enqueue(new Callback<List<ReposResponse>>() {
            @Override
            public void onResponse(Call<List<ReposResponse>> call, Response<List<ReposResponse>> response) {
                if(response != null && response.body() != null) {
                    repos.clear();

                    List<ReposResponse> responseRepos = response.body();

                    for(ReposResponse repo : responseRepos) {
                        repos.add(repo);
                        reposAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ReposResponse>> call, Throwable t) {
                if(t != null) {
                    showError();
                }
            }
        });
    }

    private void showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(!query.isEmpty()) {
            searchByUser(query.toLowerCase());
        }

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }
}