package com.example.softexperttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.softexperttask.databinding.ActivityMainBinding;
import com.example.softexperttask.features.cars.ui.activity.CarListActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        iniView();
    }

    private void iniView() {
        new Thread(() -> {
            doDummyWorkProgress();
            Intent intent = new Intent(MainActivity.this, CarListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }).start();
    }

    private void doDummyWorkProgress() {
        // make progress working
        for (int progress = 0; progress < 100; progress += 20) {
            try {
                // thread every 500 mill second
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}