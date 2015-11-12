package com.zellyalgo.laneveradepablo.loginScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zellyalgo.laneveradepablo.slideFridges.BigFridge;

/**
 * Created by zellyalgo on 12/11/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public static int NUM_PAGES = 15;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public float getPageWidth(int position){
        return 1.1f;
    }

    @Override
    public Fragment getItem(int position) {
        BigFridge bf = new BigFridge();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bf.setArguments(bundle);
        return bf;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
