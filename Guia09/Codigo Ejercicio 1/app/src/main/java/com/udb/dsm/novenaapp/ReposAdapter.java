package com.udb.dsm.novenaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposViewHolder> {
    private List<ReposResponse> repos;

    public ReposAdapter(List<ReposResponse> repos) {
        this.repos = repos;
    }

    @NonNull
    @Override
    public ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repos, parent, false);

        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposViewHolder holder, int position) {
        holder.bind((repos.get(position)));
    }

    @Override
    public int getItemCount() {
        return repos != null ? repos.size() : 0;
    }
}
