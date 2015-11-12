package com.zellyalgo.laneveradepablo.slideFridges;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zellyalgo.laneveradepablo.R;

/**
 * Created by zellyalgo on 31/8/15.
 */
public class BigFridge extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fridge_selector, container, false);
        TextView text = (TextView)view.findViewById(R.id.textFragment);
        Bundle bundle = getArguments();
        text.setText("posicion: " + bundle.getInt("position", 0));
        return view;
    }
}
