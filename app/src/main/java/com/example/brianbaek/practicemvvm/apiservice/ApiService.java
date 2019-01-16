package com.example.brianbaek.practicemvvm.apiservice;

import com.example.brianbaek.practicemvvm.model.Product;
import com.google.gson.JsonArray;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //flowable 대신 single 사용
    @GET("api/products")
    Single<List<Product>> getProducts(@Query("category") String category);

}
