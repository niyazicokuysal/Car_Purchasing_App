package com.niyazi.cokuysal.hw2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Car> recyclerItemValues;
    Dialog customDialog;

    DatabaseHelper dbHelper;

    public static final int TYPE_DISCOUNT_ITEM = 0;
    public static final int TYPE_NORMAL_ITEM= 1;

    public RecyclerViewAdapter(Context context, ArrayList<Car> values){
        this.context = context;
        this.recyclerItemValues = values;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());

        if(viewType == TYPE_DISCOUNT_ITEM) {

            itemView = inflator.inflate(R.layout.recycler_item0, parent, false);
            MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
            return mViewHolder;
        }else {

            itemView = inflator.inflate(R.layout.recycler_item1, parent, false);
            MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
            return mViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Car sm = recyclerItemValues.get(position);

        MyRecyclerViewItemHolder itemView = (MyRecyclerViewItemHolder) holder;
            itemView.tvItemCarModel.setText(sm.getModel() + ", " + sm.getYear());
            itemView.tvItemCarPrice.setText(sm.getPrice());

            String imgName = sm.getImage();
            if (imgName.equalsIgnoreCase("bmw1.jpg")){ itemView.imgItemCarImage.setImageResource(R.drawable.bmw1);}
            else if (imgName.equalsIgnoreCase("bmw2.jpg")){ itemView.imgItemCarImage.setImageResource(R.drawable.bmw2);}
            else if (imgName.equalsIgnoreCase("bmw3.jpg")){ itemView.imgItemCarImage.setImageResource(R.drawable.bmw3);}
            else if (imgName.equalsIgnoreCase("opel1.jpg")){ itemView.imgItemCarImage.setImageResource(R.drawable.opel1);}
            else if (imgName.equalsIgnoreCase("opel2.jpg")){ itemView.imgItemCarImage.setImageResource(R.drawable.opel2);}
            else if (imgName.equalsIgnoreCase("opel3.jpg")){ itemView.imgItemCarImage.setImageResource(R.drawable.opel3);}
            else if (imgName.equalsIgnoreCase("volkswagen1.jpeg")){ itemView.imgItemCarImage.setImageResource(R.drawable.volkswagen1);}
            else if (imgName.equalsIgnoreCase("volkswagen2.jpeg")){ itemView.imgItemCarImage.setImageResource(R.drawable.volkswagen2);}
            else { itemView.imgItemCarImage.setImageResource(R.drawable.volkswagen3);}

            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    displayDialog(sm.toString(), position);

                }
            });
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    @Override
    public int getItemViewType(int position) {
        Car sc = recyclerItemValues.get(position);
        if(sc.getDiscount() == 0)
            return TYPE_DISCOUNT_ITEM;
        else
            return TYPE_NORMAL_ITEM;
    }

    class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder{

        TextView tvItemCarModel;
        TextView tvItemCarPrice;
        ImageView imgItemCarImage;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            tvItemCarModel = itemView.findViewById(R.id.tvItemCarModel);
            tvItemCarPrice = itemView.findViewById(R.id.tvItemCarPrice);
            imgItemCarImage = itemView.findViewById(R.id.imgItemCarImage);
            parentLayout = itemView.findViewById(R.id.itemConstLayout);
        }
    }

    public void displayDialog(final String msg,int pos){

        final TextView tv;
        Button btnUpdate;

        customDialog = new Dialog(context);

        customDialog.setContentView(R.layout.dialog);
        tv =  customDialog.findViewById(R.id.tvDialogCar);

        btnUpdate = customDialog.findViewById(R.id.btnUPDATE);
        tv.setText(msg+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ThirdActivity.class);
                Bundle b = new Bundle();
                Car temp = recyclerItemValues.get(pos);
                b.putParcelable("Cars",temp);
                intent.putExtras(b);
                context.startActivity(intent);

            }
        });
        customDialog.show();
    }
}
