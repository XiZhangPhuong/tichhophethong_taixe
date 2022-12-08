package com.example.fastfooddelivery2025.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fastfooddelivery2025.Fragment.OrderFragment;
import com.example.fastfooddelivery2025.Fragment.UserFragment;

public class MainPager extends FragmentStateAdapter {

    public MainPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new OrderFragment();
            case 1:
                return new UserFragment();

            default: return new OrderFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}
