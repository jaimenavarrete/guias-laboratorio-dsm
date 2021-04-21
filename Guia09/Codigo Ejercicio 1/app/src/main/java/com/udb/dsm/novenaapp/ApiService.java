package com.udb.dsm.novenaapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("{useName}/repos")
    Call<List<ReposResponse>> getReposByUserName(@Path("useName") String useName);
}
