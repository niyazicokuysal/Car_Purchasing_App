package com.niyazi.cokuysal.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    EditText year,price, discount;
    TextView id,model,img;

    Car car=null;
    Intent intent;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        dbHelper = new DatabaseHelper(this);

        id = findViewById(R.id.tvID);
        model = findViewById(R.id.tvModel);
        year = findViewById(R.id.editYear);
        price = findViewById(R.id.editPrice);
        img = findViewById(R.id.tvImage);
        discount = findViewById(R.id.editDiscount);

        intent = getIntent();
        Bundle b = intent.getExtras();
        car = b.getParcelable("Cars");

        id.setText(car.getId()+" ");
        discount.setText(car.getDiscount()+" ");
        model.setText(car.getModel());
        year.setText(car.getYear()+" ");
        price.setText(car.getPrice()+" ");
        img.setText(car.getImage()+" ");
    }

    public void onClick(View view) {

        String ID = id.getText().toString();
        String YEAR = year.getText().toString();
        String PRICE = price.getText().toString();
        String DISCOUNT = discount.getText().toString();

        CarDB.update(dbHelper,ID,YEAR,PRICE, DISCOUNT);

        Intent intent = new Intent(this, SecondActivity.class);

        Bundle b = new Bundle();
        b.putString("model", model.getText().toString());
        intent.putExtras(b);

        startActivity(intent);
    }
}
