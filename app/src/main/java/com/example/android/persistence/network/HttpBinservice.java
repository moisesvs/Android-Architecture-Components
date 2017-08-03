package com.example.android.persistence.network;

import com.example.android.persistence.db.entity.NetworkStateEntity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HttpBinservice {
    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    @GET("/get")
    Call<NetworkStateEntity> getNetworkInfo();
}