package com.niyazi.cokuysal.hw2;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class CarDB {
    public static String TABLE_NAME="cars";
    public static String FIELD_ID = "id";
    public static String FIELD_MODEL = "model";
    public static String FIELD_YEAR = "year";
    public static String FIELD_IMAGE = "image";
    public static String FIELD_PRICE = "price";
    public static String FIELD_DISCOUNT = "discount";


    public static String CREATE_TABLE_SQL="CREATE TABLE "+TABLE_NAME+" ( "+FIELD_ID+" INTEGER, "+FIELD_MODEL+" TEXT, "+FIELD_YEAR+" INTEGER, "
            +FIELD_IMAGE+" TEXT,"+FIELD_PRICE+" TEXT, "+FIELD_DISCOUNT+" INTEGER,  PRIMARY KEY("+FIELD_ID+" AUTOINCREMENT))";

    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME+" ";


    public static boolean insert(DatabaseHelper dbHelper, int id, String model, int year, String image,String price, int discount) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_MODEL, model);
        contentValues.put(FIELD_YEAR, year);
        contentValues.put(FIELD_IMAGE, image);
        contentValues.put(FIELD_PRICE, price);
        contentValues.put(FIELD_DISCOUNT, discount);
        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static ArrayList<Car> findCar(DatabaseHelper dbHelper, String key) {
        Car anItem;
        ArrayList<Car> data = new ArrayList<>();
        String where = FIELD_MODEL +" like '%"+key+"%'";

        Cursor cursor = dbHelper.getSomeRecords(TABLE_NAME, null, where);
        Log.d("DATABASE OPERATIONS",  where+", "+cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String model = cursor.getString(1);
            int year= cursor.getInt(2);
            String image= cursor.getString(3);
            String price= cursor.getString(4);
            int discount= cursor.getInt(5);

            anItem = new Car(id, model, year, image,price, discount);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static boolean update(DatabaseHelper dbHelper, String id, String year,String price, String discount) {

        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_YEAR, year);
        contentValues.put(FIELD_PRICE, price);
        contentValues.put(FIELD_DISCOUNT, discount);

        String where = FIELD_ID +" = "+id;
        boolean res = dbHelper.update(TABLE_NAME,contentValues,where );
        return res;
    }

    public static boolean delete(DatabaseHelper dbHelper, int id){
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_ID + " = "+id;
        boolean res =  dbHelper.delete(TABLE_NAME, where);
        return  res;
    }
}
