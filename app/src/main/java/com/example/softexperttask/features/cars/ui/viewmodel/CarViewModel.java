package com.example.softexperttask.features.cars.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.softexperttask.features.cars.data.model.CarResponse;
import com.example.softexperttask.features.cars.repository.CarRepository;

public class CarViewModel extends ViewModel {
    public CarRepository carRepository = new CarRepository();
    private LiveData<CarResponse> carResponseLiveData;

    public LiveData<CarResponse> getAllCars(int page){
        carResponseLiveData = carRepository.getAllCars(page);
        return carResponseLiveData;
    }
}
