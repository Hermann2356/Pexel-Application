package com.example.pexelapplication.repo.remote;

import com.example.pexelapplication.model.PexelResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface PexelService {


//    @GET("search")
//    Call<PexelResponse> getPexel(@Header ("Authorization") String authorization,
//                                 @QueryMap Map<String, String> queryMap);

    @GET("search")
    Observable<PexelResponse> getPexel(@Header ("Authorization") String authorization,
                                       @QueryMap Map<String, String> queryMap);

}
