package com.example.caryard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caryard.DatabaseUtils.AppDatabase.AppDatabase;
import com.example.caryard.Models.Car;

public class CarDetailsFragment extends Fragment {

    public CarDetailsFragment() {
        // Required empty public constructor
    }

    private Car car;
    private Boolean isEdit = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_details, container, false);

        // Get the extras as a Bundle from the activity
        //if there is data, then we set the data to our variable car for displaying
        // also set is Edit to true
        // if no data we are adding a new car
        Intent intent = getActivity().getIntent();
        if(intent != null) {
            Bundle extras = intent.getExtras();
            if(extras != null) {
                car = (Car) extras.getSerializable("carData");
                isEdit = true;
            }
        } else {
            car = new Car();
        }

        //init data
        init(view);

        //return view for inflation
        return view;
    }

    private void init(View view) {

        // init our UI components
        EditText carMakeET = view.findViewById(R.id.carMakeET);
        EditText carManYrET = view.findViewById(R.id.carManYrET);
        EditText carColorET = view.findViewById(R.id.carColorET);
        EditText carRegoET = view.findViewById(R.id.carRegoET);
        EditText carPriceET = view.findViewById(R.id.carPriceET);
        Button saveBtn = view.findViewById(R.id.saveBtn);

        // populate data to UI
        if(isEdit) {
            carMakeET.setText(car.carMake);
            carManYrET.setText(car.carManYr);
            carColorET.setText(car.carColor);
            carRegoET.setText(car.carRego);
            carPriceET.setText(car.carPrice);
        }

        // configure saving data
        saveBtn.setOnClickListener(btnView -> {

            // validating fields
            if(carMakeET.getText().toString().isEmpty()) {
                showToast("Make & model field is required");
                carMakeET.requestFocus();
                carMakeET.setError("required");
            } else if(carManYrET.getText().toString().isEmpty()) {
                showToast("Manufacture year field is required");
                carManYrET.requestFocus();
                carManYrET.setError("required");
            } else if(carColorET.getText().toString().isEmpty()) {
                showToast("Color field is required");
                carColorET.requestFocus();
                carColorET.setError("required");
            } else if(carRegoET.getText().toString().isEmpty()) {
                showToast("Rego field is required");
                carRegoET.requestFocus();
                carRegoET.setError("required");
            } else if(carPriceET.getText().toString().isEmpty()) {
                showToast("Price field is required");
                carPriceET.requestFocus();
                carPriceET.setError("required");
            } else {

                //save to database
                // run db operations inside thread to avoid stalling the activity
                new Thread (() -> {
                    // populate data
                    AppDatabase db = AppDatabase.getInstance(requireContext());
                    Log.i("isEdit", isEdit.toString());
                    if(isEdit) {
                        car.carMake = carMakeET.getText().toString();
                        car.carManYr = carManYrET.getText().toString();
                        car.carColor = carColorET.getText().toString();
                        car.carRego = carRegoET.getText().toString();
                        car.carPrice = carPriceET.getText().toString();
                        db.carDao().updateCar(car);
                    } else {
                        car = new Car(
                            carMakeET.getText().toString(),
                            carManYrET.getText().toString(),
                            carColorET.getText().toString(),
                            carRegoET.getText().toString(),
                            carPriceET.getText().toString()
                        );
                        db.carDao().insertCar(car);
                    }

                    //redirect to car list page
                    Intent intent = new Intent(requireContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //remove activities from stack
                    requireContext().startActivity(intent); //open car list

                }).start();


            }
        });


    }

    void showToast(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show();
    }

}