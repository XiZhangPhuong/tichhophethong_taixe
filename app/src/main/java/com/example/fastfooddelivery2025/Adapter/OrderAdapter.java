package com.example.fastfooddelivery2025.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery2025.Model.Food;
import com.example.fastfooddelivery2025.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private List<Food> listFood;

    public OrderAdapter(Context context, List<Food> listFood) {
        this.context = context;
        this.listFood = listFood;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Food food = this.listFood.get(position);
        if(food==null){
            return;
        }
        Picasso.get().load(food.getImage_Food()).into(holder.imageView_Food);
        holder.txt_name_food.setText(food.getName_Food());
        holder.txt_category_food.setText("X"+food.getQuantity());
        holder.txt_price_food.setText(food.getPrice_Food()+" đ");
    }

    @Override
    public int getItemCount() {
        if(listFood.size()!=0){
            return listFood.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView_Food;
        private TextView txt_name_food,txt_category_food,txt_price_food;
        private RelativeLayout relative_clickFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_Food = itemView.findViewById(R.id.imageView_Food);
            txt_name_food = itemView.findViewById(R.id.txt_name_food);
            txt_category_food = itemView.findViewById(R.id.txt_category_food);
            txt_price_food = itemView.findViewById(R.id.txt_price_food);
            relative_clickFood = itemView.findViewById(R.id.relative_clickFood);
        }
    }
}
