package com.niyazi.cokuysal.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Intent intent;
    LinearLayoutManager  layoutManager;
    ArrayList<Car> carList;
    DatabaseHelper dbHelper;
    RecyclerView recyclerViewCars;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        intent = getIntent();
        Bundle b = intent.getExtras();

        String carModel = b.getString("model");

        dbHelper = new DatabaseHelper(this);
        recyclerViewCars = findViewById(R.id.recyclerViewCars);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCars.setLayoutManager(layoutManager);

        carList = CarDB.findCar(dbHelper, carModel);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, carList);
        recyclerViewCars.setAdapter(adapter);
    }
}
