package com.example.fastfooddelivery2025.Fragment;

import static com.example.fastfooddelivery2025.InitData.referenceHistory;
import static com.example.fastfooddelivery2025.InitData.referenceOrder;

import android.app.AlertDialog;
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
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.fastfooddelivery2025.Adapter.OrderAdapter;
import com.example.fastfooddelivery2025.Data.DataSharedPreferences;
import com.example.fastfooddelivery2025.Model.Order_FB;
import com.example.fastfooddelivery2025.Model.Staff;
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
    private Button btn_dismiss,btn_confirm,btn_dagiao;
    RelativeLayout view_main;
    private TextView txt_name,txt_phone,txt_address,txt_total,txt_check;
    private OrderAdapter orderAdapter;
    private List<Order_FB> list = new ArrayList<>();
    private Order_FB order_fb;
    int i = 0;
    boolean checkdung = true;
    boolean checktaixe = false;
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
        btn_dismiss = mView.findViewById(R.id.btn_dismiss);
        view_main = mView.findViewById(R.id.view_main);
        btn_dagiao = mView.findViewById(R.id.btn_dagiao);
        Picasso.get().load("https://st2.depositphotos.com/5834696/11681/v/950/depositphotos_116817166-stock-illustration-phone-call-vector-icon2.jpg").into(imageView_call);
        checkView();
        referenceOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(checkdung) {
                    list.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        order_fb = ds.getValue(Order_FB.class);
                        if(order_fb.getCheck() == 2 && order_fb.getStaff().getPhoneNumber().equals(DataSharedPreferences.getUser(getContext(),"DRIVER").getPhoneNumber())){
                            btn_dismiss.setVisibility(View.GONE);
                            btn_confirm.setVisibility(View.GONE);
                            btn_dagiao.setVisibility(View.VISIBLE);
                            rcv_order.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                            orderAdapter = new OrderAdapter(getContext(), order_fb.getListFood());
                            rcv_order.setAdapter(orderAdapter);
                            txt_name.setText(order_fb.getUser().getFullName());
                            txt_phone.setText(order_fb.getUser().getPhoneNumber());
                            txt_address.setText(order_fb.getAddress_order());
                            txt_total.setText(order_fb.getTotal_cart() + " ");
                            orderAdapter.notifyDataSetChanged();
                            checktaixe = true;
                            return;
                        }
                        if (order_fb.getCheck() == 1)
                            list.add(order_fb);
                    }
                    txt_check.setText("Bạn đã nhận được đơn hàng");
                    rcv_order.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    rcv_order.setHasFixedSize(true);

                    for (i = 0; i < list.size(); i++) {
                        if (list.get(i).getCheck() == 1) {
                            orderAdapter = new OrderAdapter(getContext(), list.get(i).getListFood());
                            rcv_order.setAdapter(orderAdapter);
                            orderAdapter.notifyDataSetChanged();
                            txt_name.setText(list.get(i).getUser().getFullName());
                            txt_phone.setText(list.get(i).getUser().getPhoneNumber());
                            txt_address.setText(list.get(i).getAddress_order());
                            txt_total.setText(list.get(i).getTotal_cart() + " ");
                            return;
                        }
                    }
                    i = 0;
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.size() == 0)
                    return;
                btn_dismiss.setVisibility(View.GONE);
                btn_confirm.setVisibility(View.GONE);
                btn_dagiao.setVisibility(View.VISIBLE);
                Staff taixe = DataSharedPreferences.getUser(getContext(), "DRIVER");

                Order_FB fb = list.get(i);
                fb.getStaff().setId_staff(taixe.getId_staff());
                fb.getStaff().setFullName_staff(taixe.getFullName_staff());
                fb.getStaff().setPhoneNumber(taixe.getPhoneNumber());
                fb.setCheck(2);
                referenceOrder.child(fb.getId_order()).setValue(fb);
                checkdung = false;
            }
        });

        btn_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(list.size() - 1 < i)
                    i = 0;
                if(list.size() == 0)
                    return;
                orderAdapter = new OrderAdapter(getContext(), list.get(i).getListFood());
                rcv_order.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();

            }
        });
        btn_dagiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkdung = true;
                orderAdapter = new OrderAdapter(getContext(),new ArrayList<>());
                rcv_order.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();
                txt_name.setText(" ");
                txt_phone.setText(" ");
                txt_address.setText(" ");
                txt_total.setText(" ");
                btn_dismiss.setVisibility(View.VISIBLE);
                btn_confirm.setVisibility(View.VISIBLE);
                btn_dagiao.setVisibility(View.GONE);

                if(checktaixe){
                    order_fb.setCheck(3);
                    referenceOrder.child(order_fb.getId_order()).setValue(order_fb);
                    referenceHistory.child(order_fb.getId_order()).setValue(order_fb);
                    referenceOrder.child(order_fb.getId_order()).removeValue();
                    return;
                }

                Order_FB fb = list.get(i);
                fb.setCheck(3);
                referenceOrder.child(fb.getId_order()).setValue(fb);
                referenceHistory.child(order_fb.getId_order()).setValue(order_fb);
                referenceOrder.child(order_fb.getId_order()).removeValue();
                i++;
                if(list.size() - 1 < i)
                    i = 0;
                if(list.size() == 0) {
                    return;
                }
                orderAdapter = new OrderAdapter(getContext(), list.get(i).getListFood());
                rcv_order.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();

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
                    view_main.setVisibility(View.VISIBLE);
                }else{
                    txt_check.setText("Bạn đã ngưng nhận đơn");
                    view_main.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}