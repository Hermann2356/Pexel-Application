package com.example.pexelapplication.viewmodel;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pexelapplication.model.PexelResponse;
import com.example.pexelapplication.repo.PexelRepository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<PexelResponse> _Pexel = new MutableLiveData<>();

    public LiveData<PexelResponse> getPexel() {
        return _Pexel;
    }


    //    public void fetchPexel(String authentication, Map<String, String> queryMap) {
//
//        new PexelRepository().getPexel(authentication, queryMap).enqueue(new Callback<PexelResponse>() {
//            @Override
//            public void onResponse(Call<PexelResponse> call, Response<PexelResponse> response) {
//                _Pexel.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<PexelResponse> call, Throwable t) {
//
//            }
//        });
//    }
    public void fetchPexel(String authentication, Map<String, String> queryMap) {

        new PexelRepository().getPexel(authentication, queryMap).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleResults(PexelResponse pexelResponse) {
        _Pexel.setValue(pexelResponse);
    }


    private void handleError(Throwable throwable) {
    }


}
