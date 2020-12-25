package com.niyazi.cokuysal.hw2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<Model> {
    Context context;
    ArrayList<Model> spinnerItemValues;
    int [] imgIds = null;
    public CustomSpinnerAdapter(@NonNull Context context, @NonNull ArrayList<Model> spinnerItems) {
        super(context, R.layout.spinner_item, spinnerItems);
        this.context = context;
        spinnerItemValues = spinnerItems;
        this.imgIds = imgIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.spinner_item, parent, false);

        ConstraintLayout constraintLayout = view.findViewById(R.id.itemConstraintLayout);
        ImageView imgItemSocial = view.findViewById(R.id.imgItemModel);
        TextView tvItemSocialName = view.findViewById(R.id.tvItemModel);

        Model selectedSocial = spinnerItemValues.get(position);

        tvItemSocialName.setText(selectedSocial.getName());
        imgItemSocial.setImageResource(selectedSocial.getImgID());


        return view;
    }

}
