package com.sori.zenvo.util;

import android.content.Context;

/**
 * Created by zhouyi on 2015/12/30.
 */
public class DensityUtil {
    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }

    public static float px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return pxValue / scale + 0.5f;
    }
}
