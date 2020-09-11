package com.example.softexperttask.features.cars.data.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.HttpException;

public class ErrorModel {
    public static CarResponse parseCarsError(Throwable throwable) {

        if (throwable instanceof HttpException) {

            HttpException exception = (HttpException) throwable;

            try {

                String jsonString = exception.response().errorBody().string();

                Gson gson = new Gson();

                return gson.fromJson(jsonString, CarResponse.class);

            } catch (IOException e) {

                Log.e("API Error", e.getMessage());

                return new CarResponse();

            }

        }
        return new CarResponse();
    }
}
