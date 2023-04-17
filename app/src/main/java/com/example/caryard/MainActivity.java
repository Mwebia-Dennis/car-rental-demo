package com.example.caryard;

import android.content.Intent;
import android.os.Bundle;
import com.example.caryard.Adapters.CarListAdapter;
import com.example.caryard.DatabaseUtils.AppDatabase.AppDatabase;
import com.example.caryard.Models.Car;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.app_name);

        init();




    }

    private void init() {


        //init adapter and bind with the recyclerview
        ArrayList<Car> listOfCars = new ArrayList<>();
        CarListAdapter adapter = new CarListAdapter(this, listOfCars);
        RecyclerView carListRV = findViewById(R.id.carListRV);
        carListRV.setLayoutManager(new LinearLayoutManager(this));
        carListRV.setAdapter(adapter);

        // run db operations inside thread to avoid stalling the activity
        new Thread (() -> {
            // populate data
            AppDatabase db = AppDatabase.getInstance(MainActivity.this);
            listOfCars.addAll(db.carDao().getAllCars());
            adapter.notifyDataSetChanged();
        }).start();

        // init add new car btn click listener
        findViewById(R.id.addNewCarBtn).setOnClickListener( btnView -> {
            startActivity(new Intent(this, FormHandlerActivity.class));
        });
    }


}