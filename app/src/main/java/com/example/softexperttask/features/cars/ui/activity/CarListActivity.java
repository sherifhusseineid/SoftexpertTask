package com.example.softexperttask.features.cars.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softexperttask.R;
import com.example.softexperttask.databinding.ActivityCarListBinding;
import com.example.softexperttask.features.cars.adapter.CarAdapter;
import com.example.softexperttask.features.cars.data.model.CarModel;
import com.example.softexperttask.features.cars.ui.viewmodel.CarViewModel;

import java.util.List;

public class CarListActivity extends AppCompatActivity {
    private static final String TAG = "CarListActivity";
    private ActivityCarListBinding binding;
    private CarViewModel carViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCarListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initViewModel();
        setObservers();
    }

    private void initViewModel() {
        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);
    }

    private void setObservers() {
        carViewModel.getAllCars(2).observe(this,carResponse -> {
            if (carResponse.getStatus() == 1){
                initCarRecycler(carResponse.getData());
            }
            else {
                showToastMessage(carResponse.getError());
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }

    private void initCarRecycler(List<CarModel> data) {
        binding.rVCarList.setHasFixedSize(true);
        binding.rVCarList.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        CarAdapter addressAdapter = new CarAdapter(this);
        addressAdapter.setCarResult(data);
        binding.rVCarList.setAdapter(addressAdapter);
    }

    private void showToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
