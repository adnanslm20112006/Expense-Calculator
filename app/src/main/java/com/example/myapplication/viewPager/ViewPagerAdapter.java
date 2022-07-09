package com.example.myapplication.viewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.myapplication.fragments.viewPager.entry.FirstScreen;
import com.example.myapplication.fragments.viewPager.entry.SecondScreen;
import com.example.myapplication.fragments.viewPager.entry.ThirdScreen;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new FirstScreen();
            case 1: return new SecondScreen();
            case 2: return new ThirdScreen();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
