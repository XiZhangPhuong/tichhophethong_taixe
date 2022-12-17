package com.example.fastfooddelivery2025;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.example.fastfooddelivery2025.ViewPager.MainPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
private ViewPager2 viewPager2;
private MainPager mainPager;
private BottomNavigationView bottomNavigationView;
public static String KeyUser = "Key_Staff";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        mainPager = new MainPager(this);
        viewPager2.setAdapter(mainPager);
        bottomNavigationView.setBackground(null);
        viewPager2.setUserInputEnabled(false);

        setWindow();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_cart) {
                    viewPager2.setCurrentItem(0);
                } else if (id == R.id.menu_user) {
                    viewPager2.setCurrentItem(1);
                }
                return false;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_cart).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_user).setChecked(true);
                        break;
                }
            }
        });
    }

    private void setWindow(){
        if(Build.VERSION.SDK_INT>=21){
            Window window = MainActivity.this.getWindow();
            window.setStatusBarColor(MainActivity.this.getResources().getColor(android.R.color.holo_blue_dark));
            window.setNavigationBarColor(MainActivity.this.getResources().getColor(android.R.color.holo_blue_dark));
        }
    }
}