/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.persistence.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.persistence.db.entity.NetworkStateEntity;
import com.example.android.persistence.repository.HttpBinRespository;

import io.reactivex.Flowable;

public class NetworkStateViewModel extends ViewModel {

    private HttpBinRespository httpBinRespository;
    private LiveData<NetworkStateEntity> networkState;

    public void setHttpBinRespository(HttpBinRespository httpBinRespository) {
        this.httpBinRespository = httpBinRespository;
    }

    public void init() {
        if (this.networkState != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        networkState = httpBinRespository.getNetworkState();
    }

    public LiveData<NetworkStateEntity>  getNetworkState() {
        return networkState;
    }

    public Flowable<NetworkStateEntity> getNetworkStateObserver() {
        return httpBinRespository.getNetworkStateObservable();
    }

}