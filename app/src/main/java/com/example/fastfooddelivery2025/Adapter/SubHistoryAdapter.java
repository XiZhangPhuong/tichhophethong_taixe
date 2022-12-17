package com.example.fastfooddelivery2025.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery2025.Model.Food;
import com.example.fastfooddelivery2025.Model.Order_FB;
import com.example.fastfooddelivery2025.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubHistoryAdapter extends RecyclerView.Adapter<SubHistoryAdapter.SubItemViewHolder> {
    List<Food> subItems;

    public SubHistoryAdapter(List<Food> subitem) {
        this.subItems = subitem;
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_history_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder holder, int position) {
        Food order = subItems.get(position);
        if(order == null)
            return;
        Picasso.get().load(order.getImage_Food()).into(holder.imageView_Food);
        holder.txt_category_food.setText(order.getCategory_Food());
        holder.txt_name_food.setText(order.getName_Food());
        holder.txt_price_food.setText(order.getPrice_Food()+"");
    }

    @Override
    public int getItemCount() {
        if(subItems != null)
            return subItems.size();
        return 0;
    }

    class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name_food,txt_category_food,txt_price_food;
        ImageView imageView_Food;
        SubItemViewHolder(View itemView) {
            super(itemView);
            txt_name_food = itemView.findViewById(R.id.txt_name_food);
            imageView_Food = itemView.findViewById(R.id.imageView_Food);
            txt_category_food = itemView.findViewById(R.id.txt_category_food);
            txt_price_food = itemView.findViewById(R.id.txt_price_food);
        }
    }
}
