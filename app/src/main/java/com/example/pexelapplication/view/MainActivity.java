package com.example.pexelapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import com.example.pexelapplication.databinding.ActivityMainBinding;
import com.example.pexelapplication.model.PexelResponse;
import com.example.pexelapplication.model.Photo;
import com.example.pexelapplication.utils.Constants.Constants;
import com.example.pexelapplication.viewmodel.MainViewModel;
import com.example.pexelapplication.viewmodel.QueryViewModel;

import java.util.HashMap;
import java.util.Map;

import adapter.PexelAdapter;
import adapter.PixelClickListener;

public class MainActivity extends AppCompatActivity implements PixelClickListener {

    private MainViewModel viewModel;
    private QueryViewModel queryViewModel;
    private ActivityMainBinding binding;
    private Map<String, String> queryMap;
    PexelAdapter pexelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        queryViewModel = new ViewModelProvider(this).get(QueryViewModel.class);

        initViews();

        initObservers();
    }

    private void initViews() {
        queryMap = new HashMap<>();
        String query = getIntent().getStringExtra(Constants.QUERY);
        queryMap.put(Constants.QUERY, query);
        queryMap.put(Constants.PER_PAGE, "1000");
        viewModel.fetchPexel(Constants.AUTH, queryMap);
        switchLayout();

        binding.etSearch.setOnEditorActionListener((view, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_GO) {
                String searchString = null;
                Intent intent = new Intent(MainActivity.this, MainActivity.class);

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


    private void initObservers() {
//        queryViewModel.getFavoritePhotos().observe(this, new Observer<LinkedHashSet<Photo>>() {
//            @Override
//            public void onChanged(LinkedHashSet<Photo> photos) {
//
//            }
//        });

        viewModel.getPexel().observe(this, new Observer<PexelResponse>() {
            @Override
            public void onChanged(PexelResponse pexelResponse) {
                pexelAdapter = new PexelAdapter(pexelResponse.getPhotos(), MainActivity.this::itemClicked);
                pexelAdapter.notifyDataSetChanged();
                binding.rvPhotoList.setAdapter(pexelAdapter);
            }
        });
    }

    private void switchLayout() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.rvPhotoList.getContext());
//        binding.rvPhotoList.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(binding.rvPhotoList.getContext(), 3);
        binding.rvPhotoList.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void itemClicked(Photo photo) {
        Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
        intent.putExtra(Constants.PHOTOGRAPHER_NAME, photo.getPhotographer());
        intent.putExtra(Constants.PHOTOGRAPHER_URL, photo.getPhotographerUrl());
        intent.putExtra(Constants.PHOTO_ORIGINAL_SRC, photo.getSrc().getOriginal());

        startActivity(intent);
        queryViewModel.addToRecentPhotos(photo);
    }
}