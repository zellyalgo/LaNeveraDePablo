package com.zellyalgo.laneveradepablo.slideFridges;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zellyalgo on 14/11/15.
 */
public class ThreeDCarousel implements ViewPager.PageTransformer {

    private static final float FINAL_POSITION = 1.75f;
    private static final float INITIAL_POSITION = 0.5f;
    private static final float MEDIUM_POSITION = 0.625F;
    private static final float SCALE_MINIMUN_FACTOR = 0.625F;
    private static final float INCLINE_FACTOR = 2;
    private static final float MOVE_FACTOR_LEFT = 0.70f;
    private static final float MOVE_FACTOR_RIGHT = 1.3125f;

    @Override
    public void transformPage(View view, float position) {

        if (-INITIAL_POSITION <= position && position <= FINAL_POSITION) {
            if(position <= MEDIUM_POSITION){
                view.setScaleX(position/INCLINE_FACTOR + MOVE_FACTOR_LEFT);
                view.setScaleY(position/INCLINE_FACTOR + MOVE_FACTOR_LEFT);
            } else {
                view.setScaleX(-position/INCLINE_FACTOR +MOVE_FACTOR_RIGHT);
                view.setScaleY(-position/INCLINE_FACTOR +MOVE_FACTOR_RIGHT);
            }
        } else {
            view.setScaleX(SCALE_MINIMUN_FACTOR);
            view.setScaleY(SCALE_MINIMUN_FACTOR);
        }
    }
}
