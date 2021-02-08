package com.example.pexelapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pexelapplication.databinding.ActivityQueryBinding;
import com.example.pexelapplication.model.Photo;
import com.example.pexelapplication.utils.Constants.Constants;
import com.example.pexelapplication.viewmodel.QueryViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import adapter.PexelAdapter;
import adapter.PixelClickListener;

public class QueryActivity extends AppCompatActivity implements PixelClickListener {

    private ActivityQueryBinding binding;
    private QueryViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQueryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(QueryViewModel.class);

        initViews();

        initObservers();
    }

    private void initViews() {
        viewModel.retrieveRecentPhoto();

        setGridLayout();

        binding.smLayout.setOnCheckedChangeListener((v, isChecked) -> {
            if (isChecked)
                setLinearLayout();
            else
                setGridLayout();
        });

        binding.etSearch.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                String searchString = null;
                Intent intent = new Intent(QueryActivity.this, MainActivity.class);

                if (binding.etSearch.getText() != null)
                    searchString = binding.etSearch.getText().toString();

                if (searchString != null && !searchString.isEmpty())
                    intent.putExtra(Constants.QUERY, searchString);

                startActivity(intent);
                return true;
            }

            return false;
        });
    }

    private void setLinearLayout() {
        linearLayoutManager = new LinearLayoutManager(binding.rvFavList.getContext());
        binding.smLayout.setText(Constants.Linear_LAYOUT);
        binding.rvFavList.setLayoutManager(linearLayoutManager);
    }

    private void setGridLayout() {
        gridLayoutManager = new GridLayoutManager(binding.rvFavList.getContext(), 3);
        binding.smLayout.setText(Constants.GRID_LAYOUT);
        binding.rvFavList.setLayoutManager(gridLayoutManager);
    }

    private void initObservers() {
        viewModel.getRecentPhotos().observe(this, new Observer<Set<Photo>>() {
            @Override
            public void onChanged(Set<Photo> photos) {
                ArrayList<Photo> list = new ArrayList<>(photos);
                Collections.reverse(list);
                PexelAdapter pexelAdapter = new PexelAdapter(list, null);
                binding.rvFavList.setAdapter(pexelAdapter);
            }
        });
    }

    @Override
    public void itemClicked(Photo photo) {

    }
}

