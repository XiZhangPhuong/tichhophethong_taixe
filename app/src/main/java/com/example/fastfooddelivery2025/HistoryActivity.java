package com.example.fastfooddelivery2025;

import static com.example.fastfooddelivery2025.InitData.referenceHistory;
import static com.example.fastfooddelivery2025.MainActivity.KeyUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fastfooddelivery2025.Adapter.HistoryAdapter;
import com.example.fastfooddelivery2025.Data.DataSharedPreferences;
import com.example.fastfooddelivery2025.Model.Order_FB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rcv_history;
    private HistoryAdapter mHistoryAdapter;
    private ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rcv_history = findViewById(R.id.rcv_history);
        img_back= findViewById(R.id.img_back);

        mHistoryAdapter = new HistoryAdapter(getListData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcv_history.setLayoutManager(linearLayoutManager);
        rcv_history.setAdapter(mHistoryAdapter);


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private List<Order_FB> getListData() {
        List<Order_FB> orders = new ArrayList<>();
        referenceHistory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orders.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Order_FB order = ds.getValue(Order_FB.class);
                    if(DataSharedPreferences.getUser(getApplicationContext(),KeyUser).getId_staff().equals(order.getStaff().getId_staff())){
                        orders.add(order);
                    }
                }
                mHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return orders;
    }
}