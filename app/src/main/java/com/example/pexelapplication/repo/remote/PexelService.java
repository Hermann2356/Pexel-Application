package com.example.pexelapplication.repo.remote;

import com.example.pexelapplication.model.PexelResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface PexelService {




    @GET("search")
    Call<PexelResponse> getPexel(@Header ("Authorization") String authorization,
                                 @QueryMap Map<String, String> queryMap);

}
