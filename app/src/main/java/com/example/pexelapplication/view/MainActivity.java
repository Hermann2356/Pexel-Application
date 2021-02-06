package com.example.pexelapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.pexelapplication.R;
import com.example.pexelapplication.model.PexelResponse;
import com.example.pexelapplication.utils.Constants.Constants;
import com.example.pexelapplication.viewmodel.MainViewModel;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private Map<String, String> queryMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new  ViewModelProvider(this).get(MainViewModel.class);
        setContentView(R.layout.activity_main);
        queryMap = new HashMap<>();
        queryMap.put(Constants.QUERY, "Tiger");
        viewModel.setPexel(Constants.AUTH, queryMap);
        viewModel.getPexel().observe(this, new Observer<PexelResponse>() {
            @Override
            public void onChanged(PexelResponse pexelResponse) {
                Log.i("Pixel", pexelResponse.toString());
            }
        });
    }


}