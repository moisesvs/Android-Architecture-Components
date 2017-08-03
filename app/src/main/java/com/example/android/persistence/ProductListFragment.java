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

package com.example.android.persistence;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.persistence.databinding.ListFragmentBinding;
import com.example.android.persistence.db.entity.NetworkStateEntity;
import com.example.android.persistence.db.entity.ProductEntity;
import com.example.android.persistence.model.Product;
import com.example.android.persistence.network.HttpBinservice;
import com.example.android.persistence.repository.HttpBinRespository;
import com.example.android.persistence.ui.ProductAdapter;
import com.example.android.persistence.ui.ProductClickCallback;
import com.example.android.persistence.viewmodel.NetworkStateViewModel;
import com.example.android.persistence.viewmodel.ProductListViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Retrofit;

public class ProductListFragment extends LifecycleFragment {

    public static final String TAG = "ProductListViewModel";

    private ProductAdapter mProductAdapter;

    private ListFragmentBinding mBinding;

    NetworkStateViewModel networkStateVideModel;

    @Inject Retrofit retrofit;

    HttpBinRespository repositoryHttpBin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

        mProductAdapter = new ProductAdapter(mProductClickCallback);
        mBinding.productsList.setAdapter(mProductAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ProductListViewModel viewModel =
                ViewModelProviders.of(this).get(ProductListViewModel.class);

        networkStateVideModel =
                ViewModelProviders.of(this).get(NetworkStateViewModel.class);

        HttpBinservice service = retrofit.create(HttpBinservice.class);
        repositoryHttpBin = new HttpBinRespository(service);

        networkStateVideModel.setHttpBinRespository(repositoryHttpBin);
        networkStateVideModel.init();

        subscribeUi(viewModel);
    }

    private void subscribeUi(ProductListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getProducts().observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> myProducts) {
                if (myProducts != null) {
                    mBinding.setIsLoading(false);
                    mProductAdapter.setProductList(myProducts);
                } else {
                    mBinding.setIsLoading(true);
                }
            }
        });

//        networkStateViewModel.getNetworkState().observe(this, new Observer<NetworkStateEntity>() {
//            @Override
//            public void onChanged(@Nullable NetworkStateEntity networkStateEntity) {
//                Log.v("Network state list", networkStateEntity.getOrigin());
//                Log.v("Thread: ", Thread.currentThread().getName());
//                mBinding.loadingTv.setText("Success!");
//            }
//        });

        networkStateVideModel.getNetworkStateObserver()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<NetworkStateEntity>() {
                    @Override
                    public void onNext(NetworkStateEntity networkStateEntity) {
                        Log.v("Network OBSERVER", networkStateEntity.getOrigin());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.v("Network OBSERVER Final" ,"");
                    }
                });

    }

    private final ProductClickCallback mProductClickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(product);
            }
        }
    };

}