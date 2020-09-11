package com.example.softexperttask.features.cars.data.datasource;

import com.example.softexperttask.features.cars.data.model.CarResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface CarInterface {
    @GET("cars")
    Single<CarResponse> getAllCars(@Query("page") int page);
}
