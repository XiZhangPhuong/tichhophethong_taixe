package com.example.fastfooddelivery2025.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery2025.Model.Order_FB;
import com.example.fastfooddelivery2025.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewholder> {
    private List<Order_FB> itemList;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public HistoryAdapter(List<Order_FB> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public HistoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout, parent, false);
        return new HistoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewholder holder, int position) {
        Order_FB order = itemList.get(position);
        if(order == null)
            return;
        holder.tv_item_title.setText(order.getId_order());
        holder.tv_item_time.setText(order.getTime_order());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rv_sub_item.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(order.getListFood().size());

        // Create sub item view adapter
        SubHistoryAdapter subItemAdapter = new SubHistoryAdapter(itemList.get(position).getListFood());

        holder.rv_sub_item.setLayoutManager(layoutManager);
        holder.rv_sub_item.setAdapter(subItemAdapter);
        holder.rv_sub_item.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        if(itemList != null)
            return itemList.size();
        return 0;
    }

    class HistoryViewholder extends RecyclerView.ViewHolder{
        private TextView tv_item_title,tv_item_time;
        private RecyclerView rv_sub_item;
        public HistoryViewholder(@NonNull View itemView) {
            super(itemView);
            tv_item_title = itemView.findViewById(R.id.tv_item_title);
            rv_sub_item = itemView.findViewById(R.id.rv_sub_item);
            tv_item_time = itemView.findViewById(R.id.tv_item_time);
        }
    }
}
