package com.example.app_smart_pan.steps;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.app_smart_pan.steps.fragments.FragmentPage;


public class SwipeAdapter extends FragmentStatePagerAdapter {
    public SwipeAdapter(FragmentManager fm, int bh) {super(fm, bh);}

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FragmentPage();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNumber", position+1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
