package com.example.caryard.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.caryard.FormHandlerActivity;
import com.example.caryard.Models.Car;
import com.example.caryard.R;

import java.util.ArrayList;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {

    private final ArrayList<Car> carList;
    private final Context context;

    public CarListAdapter(Context context, ArrayList<Car> carList) {
        this.carList = carList;
        this.context = context;
    }

    // Create a ViewHolder for each item in the RecyclerView
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_card_layout, parent, false);
        return new ViewHolder(view);
    }

    // Bind data to each item in the RecyclerView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.bind(car);
        // open form handler which will mount the car details fragment
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FormHandlerActivity.class);
            intent.putExtra("carData", car);
            context.startActivity(intent);
        });
    }

    // Get the number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return carList.size();
    }

    // Define the ViewHolder for the items in the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView carMakeTV, carManYrTV, carColorTV, carRegoTV, carPriceTV;

        public ViewHolder(View itemView) {
            super(itemView);
            carMakeTV = itemView.findViewById(R.id.carMakeTV);
            carManYrTV = itemView.findViewById(R.id.carManYrTV);
            carColorTV = itemView.findViewById(R.id.carColorTV);
            carRegoTV = itemView.findViewById(R.id.carRegoTV);
            carPriceTV = itemView.findViewById(R.id.carPriceTV);
        }

        public void bind(Car car) {
            // Bind data to the views in the ViewHolder
            carMakeTV.setText(car.carMake);
            carManYrTV.setText(car.carManYr);
            carColorTV.setText(car.carColor);
            carRegoTV.setText(car.carRego);
            carPriceTV.setText(car.carPrice);

        }
    }
}
