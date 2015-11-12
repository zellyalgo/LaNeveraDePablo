package com.zellyalgo.laneveradepablo.slideFridges;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zellyalgo.laneveradepablo.R;

/**
 * Created by zellyalgo on 31/8/15.
 */
public class BigFridge extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fridge_selector, container, false);
    }
}
