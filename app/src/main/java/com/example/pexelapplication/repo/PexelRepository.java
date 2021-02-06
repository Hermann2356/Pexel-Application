package com.example.pexelapplication.repo;

import com.example.pexelapplication.model.PexelResponse;
import com.example.pexelapplication.repo.remote.RetrofitInstance;

import java.util.Map;

import retrofit2.Call;

public class PexelRepository {

    public Call<PexelResponse> getPexel(String auth, Map<String, String> queries) {
        return RetrofitInstance.getPexelService().getPexel(auth, queries);
    }
}
