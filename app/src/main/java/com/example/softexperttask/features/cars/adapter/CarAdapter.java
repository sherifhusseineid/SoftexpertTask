package com.example.softexperttask.features.cars.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.softexperttask.databinding.ItemCarBinding;
import com.example.softexperttask.features.cars.data.model.CarModel;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private static final String TAG = "CarAdapter";
    private List<CarModel> carModelList;
    private Activity activity;

    public CarAdapter(Activity activity){
        this.activity = activity;
    }

    public void setCarResult(List<CarModel> carModelList) {
        this.carModelList = carModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCarBinding binding =
                ItemCarBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new CarAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CarAdapter.ViewHolder holder, int position) {
        final CarModel addressModel = carModelList.get(position);
        holder.bindItem(addressModel);
    }

    @Override
    public int getItemCount() {
        return carModelList != null ? carModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCarBinding binding;

        public ViewHolder(@NonNull ItemCarBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

        public void bindItem(CarModel carModel) {
            binding.tvCarBrand.setText(carModel.getBrand());
            if (carModel.isUsed()){
                binding.tvIsUsed.setText("Is Used");
            }
            binding.tvConsYear.setText(carModel.getConstractionYear());
            Glide.with(activity).load(carModel.getImageUrl()).into(binding.iVCarLogo);

        }
    }
}
