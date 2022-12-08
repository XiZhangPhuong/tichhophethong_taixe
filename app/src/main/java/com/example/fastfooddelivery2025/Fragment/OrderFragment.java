package com.example.fastfooddelivery2025.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.fastfooddelivery2025.Adapter.OrderAdapter;
import com.example.fastfooddelivery2025.Model.Order_FB;
import com.example.fastfooddelivery2025.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {
private View mView;
private Switch switch2;
private RecyclerView rcv_order;
private ImageView imageView_call;
private Button btn_dismiss,btn_confirm;
private TextView txt_name,txt_phone,txt_address,txt_total,txt_check;
private OrderAdapter orderAdapter;
private final DatabaseReference dataOrder = FirebaseDatabase.getInstance().getReference("Order");
private List<Order_FB> list = new ArrayList<>();
private Order_FB order_fb;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_order,container,false);
        switch2 = mView.findViewById(R.id.switch2);
        rcv_order = mView.findViewById(R.id.rcv_order);
        txt_name = mView.findViewById(R.id.txt_name);
        txt_phone = mView.findViewById(R.id.txt_phone);
        txt_address = mView.findViewById(R.id.txt_address);
        txt_total = mView.findViewById(R.id.txt_total);
        imageView_call = mView.findViewById(R.id.imageView_call);
        btn_dismiss = mView.findViewById(R.id.btn_dismiss);
        btn_confirm = mView.findViewById(R.id.btn_confirm);
        txt_check = mView.findViewById(R.id.txt_check);

        Picasso.get().load("https://st2.depositphotos.com/5834696/11681/v/950/depositphotos_116817166-stock-illustration-phone-call-vector-icon2.jpg").into(imageView_call);
        checkView();
        dataOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    order_fb = ds.getValue(Order_FB.class);
                    list.add(order_fb);
                }
                if (order_fb == null) {
                    return;
                }
                    txt_check.setText("Bạn đã nhận được đơn hàng");
                    rcv_order.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    rcv_order.setHasFixedSize(true);

                    for(int i = 0;i<list.size();i++){
                        if(list.get(i).getCheck()==1){
                            orderAdapter = new OrderAdapter(getContext(), list.get(i).getListFood());
                            rcv_order.setAdapter(orderAdapter);
                            orderAdapter.notifyDataSetChanged();
                            txt_name.setText(list.get(i).getUser().getFullName());
                            txt_phone.setText(list.get(i).getUser().getPhoneNumber());
                            txt_address.setText(list.get(i).getAddress_order());
                            txt_total.setText(list.get(i).getTotal_cart() + " ");
                        }
                    }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return mView;
    }
    private void checkView(){
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch2.isChecked()){
                    txt_check.setText("Ứng dụng đang hoạt động");
                }else{
                    txt_check.setText("Bạn đã ngưng nhận đơn");
                }
            }
        });
    }
    private int checkListOrder(){
        for(Order_FB o : list){
            if(o.getCheck()==1){
                return 1;
            }
        }
        return 0;
    }
}