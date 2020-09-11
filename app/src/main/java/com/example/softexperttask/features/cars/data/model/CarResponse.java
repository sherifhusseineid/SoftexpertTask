package com.example.softexperttask.features.cars.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("data")
    private List<CarModel> data;

    @SerializedName("erroe")
    private String error;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CarModel> getData() {
        return data;
    }

    public void setData(List<CarModel> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
