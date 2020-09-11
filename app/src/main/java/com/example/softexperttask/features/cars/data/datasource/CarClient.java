package com.example.softexperttask.features.cars.data.datasource;

import com.example.softexperttask.features.cars.data.model.CarResponse;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarClient {
    private static final String BASE_URL = "http://demo1585915.mockable.io/api/v1/";
    private CarInterface carInterface;
    private static CarClient INSTANCE;

    public CarClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        carInterface = retrofit.create(CarInterface.class);
    }

    public static CarClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new CarClient();
        }
        return INSTANCE;
    }

    public Single<CarResponse> getCars(int page) {
        return carInterface.getAllCars(page);
    }
}
