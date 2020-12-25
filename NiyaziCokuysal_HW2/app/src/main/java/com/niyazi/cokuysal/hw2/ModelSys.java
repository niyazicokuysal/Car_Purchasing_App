package com.niyazi.cokuysal.hw2;

import java.util.ArrayList;
import java.util.Collections;

public class ModelSys {
    private static ArrayList<Model> models;

    public static void prepareData(){
        models = new ArrayList<>();
        int [] imgIds = new int[]{R.drawable.bmw, R.drawable.opel, R.drawable.volkswagen};


        Collections.addAll(models,
                new Model("Bmw", R.drawable.bmw),
                new Model("Opel",R.drawable.opel),
                new Model("Volkswagen",R.drawable.volkswagen));
    }

    public static ArrayList<Model> getSocialItems() {
        return models;
    }
}

