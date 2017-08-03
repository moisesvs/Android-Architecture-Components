package com.example.android.persistence.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.android.persistence.db.entity.NetworkStateEntity;
import com.example.android.persistence.network.HttpBinservice;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by moises on 5/7/17.
 */
public class HttpBinRespository {

    private HttpBinservice webservice;

    public HttpBinRespository(HttpBinservice webservice) {
        this.webservice = webservice;
    }

    public LiveData<NetworkStateEntity> getNetworkState() {
        // This is not an optimal implementation, we'll fix it below
        final MutableLiveData<NetworkStateEntity> data = new MutableLiveData<>();
        webservice.getNetworkInfo().enqueue(new Callback<NetworkStateEntity>() {
            @Override
            public void onResponse(Call<NetworkStateEntity> call, Response<NetworkStateEntity> response) {

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NetworkStateEntity> call, Throwable throwable) {
                // nothing
            }
        });
        return data;
    }

    public Flowable<NetworkStateEntity> getNetworkStateObservable() {
        // This is not an optimal implementation, we'll fix it below
        return Flowable.fromCallable(new Callable<NetworkStateEntity>() {
            @Override
            public NetworkStateEntity call() throws Exception {
                return executeNetwork();
            }
        });
    }

    private NetworkStateEntity executeNetwork () {
        NetworkStateEntity entity = null;
        try {
            entity = webservice.getNetworkInfo().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entity;
    }

}
