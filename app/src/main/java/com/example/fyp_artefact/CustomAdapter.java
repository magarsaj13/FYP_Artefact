package com.example.fyp_artefact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Animation translate;
    private Context context;
    private ArrayList itemName, itemID, itemPrice, itemCategory, itemQuantity;
    Activity activity;
    int position;


    public CustomAdapter(Activity activity, Context context, ArrayList itemName, ArrayList itemID, ArrayList itemPrice, ArrayList itemCategory, ArrayList itemQuantity){
        this.context = context;
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemQuantity = itemQuantity;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.itemIDTxt.setText(String.valueOf(itemID.get(position)));
        holder.itemNameTxt.setText(String.valueOf(itemName.get(position)));
        holder.itemPriceTxt.setText(String.valueOf(itemPrice.get(position)));
        holder.itemCategoryTxt.setText(String.valueOf(itemCategory.get(position)));
        holder.itemQuantityTxt.setText(String.valueOf(itemQuantity.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_Items.class);
                intent.putExtra("id", String.valueOf(itemID.get(position)));
                intent.putExtra("name", String.valueOf(itemName.get(position)));
                intent.putExtra("quantity", String.valueOf(itemQuantity.get(position)));
                intent.putExtra("category", String.valueOf(itemCategory.get(position)));
                intent.putExtra("price", String.valueOf(itemPrice.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTxt, itemPriceTxt, itemIDTxt, itemQuantityTxt, itemCategoryTxt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIDTxt = itemView.findViewById(R.id.tv_id);
            itemNameTxt = itemView.findViewById(R.id.tv_name);
            itemPriceTxt = itemView.findViewById(R.id.tv_price);
            itemQuantityTxt = itemView.findViewById(R.id.tv_Quantity);
            itemCategoryTxt = itemView.findViewById(R.id.tv_category);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate = AnimationUtils.loadAnimation(context, R.anim.translate);
            mainLayout.setAnimation(translate);
        }
    }
}
