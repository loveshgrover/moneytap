package com.colony.loveshgrover.moneytap.services;

import com.colony.loveshgrover.moneytap.restinterface.APIInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {
    private BaseService() {
    }

    private static Retrofit.Builder getBuilder() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.REST_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build());
    }

    public static APIInterface getApi() {
        Gson gson = new GsonBuilder().create();

        return getBuilder().addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(APIInterface.class);
    }

}
