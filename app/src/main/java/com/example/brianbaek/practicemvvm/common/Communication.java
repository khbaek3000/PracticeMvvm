package com.example.brianbaek.practicemvvm.common;

import android.databinding.ObservableArrayList;

import com.example.brianbaek.practicemvvm.apiservice.ApiService;
import com.example.brianbaek.practicemvvm.model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Communication {

    public Single<List<Product>> getProductList(String query){
        return RetrofitProvider.getApiService()
                .getProducts(query)
                .subscribeOn(Schedulers.io());
    }

}
