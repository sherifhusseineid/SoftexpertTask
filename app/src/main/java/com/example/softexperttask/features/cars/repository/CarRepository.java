package com.example.softexperttask.features.cars.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softexperttask.features.cars.data.datasource.CarClient;
import com.example.softexperttask.features.cars.data.model.CarResponse;
import com.example.softexperttask.features.cars.data.model.ErrorModel;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CarRepository {
    private static final String TAG = "CarRepository";
    private CompositeDisposable disposable = new CompositeDisposable();

    public LiveData<CarResponse> getAllCars(int page){
        final MutableLiveData<CarResponse> data = new MutableLiveData<>();

        Single<CarResponse> carResponseSingle =
                CarClient.getINSTANCE().getCars(page);

        DisposableSingleObserver<CarResponse> disposableSingleObserver = new DisposableSingleObserver<CarResponse>() {
            @Override public void onSuccess(CarResponse carResponse) {
                data.setValue(carResponse);
            }

            @Override public void onError(Throwable e) {
                CarResponse error = ErrorModel.parseCarsError(e);
                Log.d(TAG, "onErrorproduct: " + e.getMessage());
                data.setValue(error);
            }
        };
        disposable.add(carResponseSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableSingleObserver));
        return data;
    }
}
