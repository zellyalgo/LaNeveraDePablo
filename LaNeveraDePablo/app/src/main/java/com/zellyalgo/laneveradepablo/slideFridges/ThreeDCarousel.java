package com.zellyalgo.laneveradepablo.slideFridges;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by zellyalgo on 14/11/15.
 */
public class ThreeDCarousel implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -0.5) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setScaleX(0.45f);
            view.setScaleY(0.45f);

        } else if (position <= 1.75) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            if(position <= 0.625){
                view.setScaleX(position/2 + 0.70f);
                view.setScaleY(position/2 + 0.70f);
            } else {
                view.setScaleX(-position/2 +1.3125f);
                view.setScaleY(-position/2 +1.3125f);
            }
            Log.d("Laneveradepablo", "posicion: " + position);
            // Fade the page relative to its size.
            /*view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));*/

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setScaleX(0.45f);
            view.setScaleY(0.45f);
        }
    }
}
