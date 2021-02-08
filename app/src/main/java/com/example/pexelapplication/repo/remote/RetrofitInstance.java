package com.example.pexelapplication.repo.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Create Singleton Retrofit Instance and instance of our service
 */
public class RetrofitInstance {
    private static final String BASE_URL = "https://api.pexels.com/v1/";

    // Step 1: Declare instance initialized as null
    private static PexelService INSTANCE = null;

    // Step 2: Make the constructor private
    private RetrofitInstance() {

    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    // Step 3: public method to access the new instance
    public static PexelService getPexelService() {
        if(INSTANCE == null)
            INSTANCE = getRetrofit().create(PexelService.class);

        return INSTANCE;
    }


    private static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
}
