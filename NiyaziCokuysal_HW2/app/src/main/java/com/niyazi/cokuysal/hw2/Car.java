package com.niyazi.cokuysal.hw2;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private int id;
    private String model;
    private int year;
    private String image;
    private String price;
    private int discount;

    public Car(int id, String model, int year, String image, String price, int discount) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.image = image;
        this.price = price;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    protected Car(Parcel in) {
        id = in.readInt();
        model = in.readString();
        year = in.readInt();
        image = in.readString();
        price = in.readString();
        discount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(model);
        dest.writeInt(year);
        dest.writeString(image);
        dest.writeString(price);
        dest.writeInt(discount);
    }

    @Override
    public String toString() {
        return "Car " + id + "\n" +
                "\n" +  "Model = " + model + "\n" +
                "\n" +  "Year = " + year + "\n" +
                "\n" +  "Image = " + image + "\n" +
                "\n" +  "Price = " + price + "\n" +
                "\n" +  "Discount = " + discount;
    }
}
