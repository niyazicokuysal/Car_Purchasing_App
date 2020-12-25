package com.niyazi.cokuysal.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerModel;

    private String jsonStr;
    private ProgressBar mProgressBar;
    DatabaseHelper dbHelper;

    private JSONArray cars;
    private JSONObject carJSONObject;
    String carModel;


    private ArrayList<Car> carArrayList;

    public static final String TAG_CARS = "cars";
    public static final String TAG_MODEL = "model";
    public static final String TAG_YEAR = "year";
    public static final String TAG_IMAGE = "image";
    public static final String TAG_PRICE = "price";
    public static final String TAG_DISCOUNT = "discount";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModelSys.prepareData();

        spinnerModel = findViewById(R.id.spinnerModel);

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, ModelSys.getSocialItems());
        spinnerModel.setAdapter(adapter);

        spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                carModel = ModelSys.getSocialItems().get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        dbHelper = new DatabaseHelper(this);
    }

    public void onParse(View view) {
        carArrayList = new ArrayList<Car>();
        jsonStr = loadFileFromAssets("cars.json");
        Log.d("TAG", "\n" + jsonStr);

        new GetCars().execute();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);

        Bundle b = new Bundle();
        b.putString("model", carModel);
        intent.putExtras(b);

        Log.d("model", carModel);

        startActivity(intent);
    }

    private String loadFileFromAssets(String fileName) {
        String fileContent = null;
        try {
            InputStream is = getBaseContext().getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            fileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return fileContent;
    }

    private class GetCars extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            if (jsonStr != null) {
                try {
                    carArrayList.clear();

                    carJSONObject = new JSONObject(jsonStr);
                    // Getting JSON Array
                    cars = carJSONObject.getJSONArray(TAG_CARS);

                    for (int i = 0; i < cars.length(); i++) {

                        JSONObject jsonObj = cars.getJSONObject(i);

                        Thread.sleep(300);

                        String model = jsonObj.getString(TAG_MODEL);
                        int year = jsonObj.getInt(TAG_YEAR);
                        String image = jsonObj.getString(TAG_IMAGE);
                        String price = jsonObj.getString(TAG_PRICE);
                        int discount = jsonObj.getInt(TAG_DISCOUNT);

                        Car car = new Car(i ,model, year, image, price, discount);

                        carArrayList.add(car);
                        Log.d("Array",carArrayList.get(i).toString()); //check if array is full
                    }

                } catch (JSONException | InterruptedException ee) {
                    ee.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Dismiss the progress dialog
            mProgressBar.setVisibility(View.INVISIBLE);
            boolean res;
            for (int e = 0; e < carArrayList.size(); e++) {
                res = CarDB.delete(dbHelper, e);
            }

            for (int i = 0; i < carArrayList.size(); i++) {
                res = CarDB.insert(dbHelper, i, carArrayList.get(i).getModel(), carArrayList.get(i).getYear(), carArrayList.get(i).getImage(),
                        carArrayList.get(i).getPrice(), carArrayList.get(i).getDiscount());
            }
        }
    }
}