package com.example.fastfooddelivery2025.Fragment;

import static com.example.fastfooddelivery2025.MainActivity.KeyUser;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fastfooddelivery2025.Adapter.FunctionUser_Adapter;
import com.example.fastfooddelivery2025.Data.DataSharedPreferences;
import com.example.fastfooddelivery2025.HistoryActivity;
import com.example.fastfooddelivery2025.LoginActivity;
import com.example.fastfooddelivery2025.MainActivity;
import com.example.fastfooddelivery2025.Model.FunctionUser;
import com.example.fastfooddelivery2025.Model.Order_FB;
import com.example.fastfooddelivery2025.Model.Staff;
import com.example.fastfooddelivery2025.Model.User;
import com.example.fastfooddelivery2025.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class UserFragment extends Fragment {
    private View mView;
    private DatabaseReference data_Function_user,data_chart ;
    private List<FunctionUser> listFun = new ArrayList<>();
    private FunctionUser_Adapter functionUser_adapter;
    private MainActivity mMainActivity;
    private RecyclerView rcv_fun;
    private ProgressBar progressFun;
    private BarChart barChart;
    private TextView tv_name_user,tv_phone_user,tv_id_user;
    List<BarEntry> entries = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_user,container,false);
        mMainActivity = (MainActivity) getActivity();
        rcv_fun = mView.findViewById(R.id.rcv_fun);
        progressFun = mView.findViewById(R.id.progressFun);
        barChart = mView.findViewById(R.id.barChart);
        tv_name_user = mView.findViewById(R.id.tv_name_user);
        tv_phone_user = mView.findViewById(R.id.tv_phone_user);
        tv_id_user = mView.findViewById(R.id.tv_id_user);


        Staff staff = DataSharedPreferences.getUser(getContext(),KeyUser);

        tv_id_user.setText("ID"+staff.getId_staff());
        tv_phone_user.setText("Phone:" +staff.getPhoneNumber());
        tv_name_user.setText(staff.getFullName_staff().toUpperCase(Locale.ROOT));

        setupCharView();
        loadDataFunction_User();
        return mView;
    }
    private int getMonth(){
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }


    private void setupCharView() {

        data_chart = FirebaseDatabase.getInstance().getReference("History");
        data_chart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int month;
                entries.clear();
                entries.add(new BarEntry(1f,0));
                entries.add(new BarEntry(2f,0));
                entries.add(new BarEntry(3f,0));
                entries.add(new BarEntry(4f,0));
                entries.add(new BarEntry(5f,0));
                entries.add(new BarEntry(6f,0));
                entries.add(new BarEntry(7f,0));
                entries.add(new BarEntry(8f,0));
                entries.add(new BarEntry(9f,0));
                entries.add(new BarEntry(10f,0));
                entries.add(new BarEntry(11f,0));
                entries.add(new BarEntry(12f,0));
                for (DataSnapshot ds : snapshot.getChildren()){
                    Order_FB order_fb = ds.getValue(Order_FB.class);
                    if(order_fb.getStaff().getId_staff().equals(DataSharedPreferences.getUser(getContext(),KeyUser).getId_staff())) {
                        String data = order_fb.getTime_order();
                        month = Integer.parseInt(data.split("/")[1]);
                        switch (month){
                            case 1:
                                entries.get(0).setY(entries.get(0).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 2:
                                entries.get(1).setY(entries.get(1).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 3:
                                entries.get(2).setY(entries.get(2).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 4:
                                entries.get(3).setY(entries.get(3).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 5:
                                entries.get(4).setY(entries.get(4).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 6:
                                entries.get(5).setY(entries.get(5).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 7:
                                entries.get(6).setY(entries.get(6).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 8:
                                entries.get(7).setY(entries.get(7).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 9:
                                entries.get(8).setY(entries.get(8).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 10:
                                entries.get(9).setY(entries.get(9).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 11:
                                entries.get(10).setY(entries.get(10).getY()+(float) order_fb.getTotal_cart());
                                break;
                            case 12:
                                entries.get(11).setY(entries.get(11).getY()+(float) order_fb.getTotal_cart());
                                break;
                        }
                    }

                }
                BarDataSet barDataSet = new BarDataSet(entries,"Các tháng");
                //set colors
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                //hide draw value
                //barDataSet.setDrawValues(false);
                //set bar data
                barChart.setData(new BarData(barDataSet));
                //set animation
                barChart.animateY(2000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void loadDataFunction_User(){
        data_Function_user = FirebaseDatabase.getInstance().getReference("Function_Driver");
        new Thread(new Runnable() {
            @Override
            public void run() {
                data_Function_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        progressFun.setVisibility(View.VISIBLE);
                        listFun.clear();
                        for(DataSnapshot ds : snapshot.getChildren()){
                            FunctionUser f = ds.getValue(FunctionUser.class);
                            listFun.add(f);
                        }
                        functionUser_adapter.notifyDataSetChanged();
                        progressFun.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        }).start();

        functionUser_adapter = new FunctionUser_Adapter(listFun, new FunctionUser_Adapter.ClickFunction() {
            @Override
            public void Click(FunctionUser functionUser) {
                int index = Integer.parseInt(functionUser.getId());
                switch (index){
                    case 1 : startActivity(new Intent(getContext(), HistoryActivity.class));
                        break;

                    case 2 :

                        break;

                    case 3 :
                        break;

                    case 4 :
                        break;

                    case 5:
                        break;

                    case 6:
                        break;

                    case 7:
                        break;

                    case 8:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Thông báo").setMessage("Bạn có muốn đăng xuất ? ").setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(getContext(), LoginActivity.class));
                                        DataSharedPreferences.setUser(mMainActivity,null,KeyUser);
                                        mMainActivity.finishAffinity();
                                    }
                                }).setNegativeButton("No",null).create().show();
                        break;
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcv_fun.setLayoutManager(linearLayoutManager);
        rcv_fun.setAdapter(functionUser_adapter);
        rcv_fun.setHasFixedSize(true);
    }
}