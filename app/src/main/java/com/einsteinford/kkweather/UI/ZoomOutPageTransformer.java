package com.einsteinford.kkweather.UI;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by KK on 2016-08-23.
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    String TAG = getClass().getSimpleName();

    @Override
    public void transformPage(View page, float position) {
        //position指的是图片左边界所在的相对位置
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();

        if (position < -1) {     //表示页面完全向左划出主屏幕
            page.setAlpha(0);
        } else if (position <= 1) {   //表示页面向右滑动
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float verMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                page.setTranslationX(horzMargin - verMargin / 2);
            } else {
                page.setTranslationX(-horzMargin + verMargin / 2);
            }

            //缩放下滑效果
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }else {
            page.setAlpha(0);
        }
    }
}
