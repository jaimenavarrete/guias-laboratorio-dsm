package com.udb.dsm.novenaapp;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.udb.dsm.novenaapp.databinding.ItemReposBinding;

public class ReposViewHolder extends RecyclerView.ViewHolder {
    private final ItemReposBinding itemReposBinding;

    public ReposViewHolder(View view) {
        super(view);

        itemReposBinding = ItemReposBinding.bind(view);
    }

    public void bind(ReposResponse repo) {
        itemReposBinding.textRepoName.setText(repo.getFullName());
        itemReposBinding.textRepoCreated.setText(repo.getCreatedAt());
        itemReposBinding.textRepoUpdated.setText(repo.getUpdatedAt());
        itemReposBinding.textRepoLanguage.setText(repo.getLanguage());
    }
}
