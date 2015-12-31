package com.sori.zenvo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.sori.zenvo.util.DensityUtil;

/**
 * Created by zhouyi on 2015/12/30.
 */
public class ArrowView extends View {

    private static final float TRIANGLE_HEIGHT = 10;
    private static final float TRIANGLE_BOTTOM_WIDTH = 10;

    private float mAboveViewWidth;
    private int mAboveViewIndex;
    private Paint mTrianglePaint;


    public ArrowView(Context context) {
        super(context);
        init(context);
    }

    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArrowListView);
        mAboveViewWidth = typedArray.getFloat(R.styleable.ArrowListView_aboveViewWidth, 100f);
        mAboveViewIndex = typedArray.getInt(R.styleable.ArrowListView_aboveViewIndex, 0);
        init(context);
    }

    public ArrowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArrowListView);
        mAboveViewWidth = typedArray.getFloat(R.styleable.ArrowListView_aboveViewWidth, 100f);
        mAboveViewWidth = typedArray.getInt(R.styleable.ArrowListView_aboveViewIndex, 0);
        init(context);
    }

    private void init(Context context) {
        mTrianglePaint = new Paint();
        mTrianglePaint.setAntiAlias(true);
//        mTrianglePaint.setColor(context.getResources().getColor(android.R.color.holo_green_light));
        mTrianglePaint.setColor(context.getResources().getColor(android.R.color.holo_green_light));
        mTrianglePaint.setStyle(Paint.Style.FILL);
        mTrianglePaint.setStrokeWidth(2.0f);
    }

    public float getmAboveViewWidth() {
        return mAboveViewWidth;
    }

    public void setmAboveViewWidth(float mAboveViewWidth) {
        this.mAboveViewWidth = mAboveViewWidth;
    }

    public int getmAboveViewIndex() {
        return mAboveViewIndex;
    }

    public void setmAboveViewIndex(int mAboveViewIndex) {
        this.mAboveViewIndex = mAboveViewIndex;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float startPointX, startPointY;
        float topPointX, topPointY;
        float endPointX, endPointY;

        Path path = new Path();

        int index = mAboveViewIndex % 3 + 1;
        int offset = 16;
        startPointX = (2*index - 1)* mAboveViewWidth / 2 + (index - 1) * offset;
        startPointY = offset;
        topPointX = startPointX + TRIANGLE_BOTTOM_WIDTH / 2;
        topPointY = startPointY - TRIANGLE_HEIGHT;
        endPointX = startPointX + TRIANGLE_BOTTOM_WIDTH;
        endPointY = startPointY;
        startPointX = DensityUtil.dip2px(getContext(), startPointX);
        startPointY = DensityUtil.dip2px(getContext(), startPointY);
        topPointX = DensityUtil.dip2px(getContext(), topPointX);
        topPointY = DensityUtil.dip2px(getContext(), topPointY);
        endPointX = DensityUtil.dip2px(getContext(), endPointX);
        endPointY = DensityUtil.dip2px(getContext(), endPointY);

                path.moveTo(startPointX, startPointY);
        path.lineTo(topPointX, topPointY);
        path.lineTo(endPointX, endPointY);
        path.close();
        canvas.drawPath(path, mTrianglePaint);

        //canvas.drawLine(DensityUtil.dip2px(getContext(), mAboveViewWidth / 2.0f - TRIANGLE_BOTTOM_WIDTH / 2.0f), DensityUtil.dip2px(getContext(), 16), DensityUtil.dip2px(getContext(), mAboveViewWidth / 2.0f), 0, mTrianglePaint);
    }
}
