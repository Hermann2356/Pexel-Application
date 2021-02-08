package com.example.pexelapplication.repo;

import com.example.pexelapplication.model.PexelResponse;
import com.example.pexelapplication.repo.remote.RetrofitInstance;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Call;

public class PexelRepository {

//    public Call<PexelResponse> getPexel(String auth, Map<String, String> queries) {
//        return RetrofitInstance.getPexelService().getPexel(auth, queries);
//    }

    public Observable<PexelResponse> getPexel(String auth, Map<String, String> queries) {
        Observable<PexelResponse> pexelResponseObservable = RetrofitInstance.getPexelService()
                .getPexel(auth, queries);

        return pexelResponseObservable;
    }

    }