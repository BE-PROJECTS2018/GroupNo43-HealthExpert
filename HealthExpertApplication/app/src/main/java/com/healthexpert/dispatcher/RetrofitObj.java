package com.healthexpert.dispatcher;

import com.healthexpert.common.Config;
import com.healthexpert.data.remote.models.requests.UserLoginRequest;
import com.healthexpert.data.remote.models.response.UserResponse;
import com.healthexpert.network.RxErrorHandlingCallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Shivani on 1/14/2017.
 */

public class RetrofitObj {
    public static Retrofit getInstance() {
        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
    }

}
