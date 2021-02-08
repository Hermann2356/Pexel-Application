package com.example.pexelapplication.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pexelapplication.databinding.ActivityDescriptionBinding;
import com.example.pexelapplication.utils.Constants.Constants;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityDescriptionBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initView();
    }

    private void initView() {
        binding.tvPhotographerName.setText(getIntent().getStringExtra(Constants.PHOTOGRAPHER_NAME));
        Glide.with(binding.getRoot())
                .load(getIntent().getStringExtra(Constants.PHOTO_ORIGINAL_SRC))
                .centerCrop()
                .into(binding.ivPhoto);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

        }
    }
}
