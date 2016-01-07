package com.sori.zenvo;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.sori.zenvo.util.DensityUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<ImageView> mImages = new ArrayList<>();
    private List<CustomListView> mLists = new ArrayList<>();
    private List<ArrowView> mArrowViews = new ArrayList<>();
    private List<LinearLayout> mSections = new ArrayList<>();
    private List<LinearLayout> mLayouts = new ArrayList<>();
    private LinearLayout rootView;
    private ScrollView mScrollview;


    private Resources mResources;
    private float mScreenWidth;
    private float mImageWidth;
    private int mOffset;

    private int COLUMN_NUM;

    public void scrollMove(final ScrollView mscroll, final int offset) {
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int scrollY = offset * 150 + offset * 16;
                mscroll.smoothScrollTo(0, (int) DensityUtil.dip2px(MainActivity.this, scrollY));
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResources = getResources();
        mScrollview = (ScrollView) findViewById(R.id.rootScroll);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mImageWidth = DensityUtil.dip2px(this, 110);
        COLUMN_NUM = (int) mScreenWidth / (int) mImageWidth;
        mOffset = ((int) mScreenWidth - (int) (mImageWidth * COLUMN_NUM) - (int) (2 * DensityUtil.dip2px(this, 16f))) / (2 * (COLUMN_NUM - 1));
        mOffset = (int) DensityUtil.px2dip(this, mOffset);
        if (mOffset <= 0) {
            COLUMN_NUM--;
            mOffset = ((int) mScreenWidth - (int) (mImageWidth * COLUMN_NUM) - (int) (2 * DensityUtil.dip2px(this, 16f))) / (2 * (COLUMN_NUM - 1));
            mOffset = (int) DensityUtil.px2dip(this, mOffset);
        }
        LinkedHashMap<Integer, List<String>> datas = new LinkedHashMap<>();
        ArrayList<String> listItem = new ArrayList<>();
        listItem.add("1");
        datas.put(1, listItem);
        listItem = new ArrayList<>();
        listItem.add("1");
        listItem.add("2");
        datas.put(2, listItem);
        listItem = new ArrayList<>();
        listItem.add("1");
        listItem.add("2");
        listItem.add("3");
        datas.put(3, listItem);
        listItem = new ArrayList<>();
        listItem.add("1");
        listItem.add("2");
        listItem.add("3");
        listItem.add("4");
        datas.put(4, listItem);
        listItem = new ArrayList<>();
        listItem.add("1");
        listItem.add("2");
        listItem.add("3");
        listItem.add("4");
        listItem.add("5");
        datas.put(5, listItem);
        listItem = new ArrayList<>();
        listItem.add("1");
        listItem.add("2");
        listItem.add("3");
        listItem.add("4");
        listItem.add("5");
        datas.put(6, listItem);
        listItem = new ArrayList<>();
        listItem.add("1");
        listItem.add("2");
        listItem.add("3");
        listItem.add("4");
        listItem.add("5");
        datas.put(7, listItem);
        listItem = new ArrayList<>();
        listItem.add("1");
        listItem.add("2");
        listItem.add("3");
        listItem.add("4");
        listItem.add("5");
        datas.put(8, listItem);
        initLayout(datas);
        setEvents();
    }

    private void initLayout(LinkedHashMap<Integer, List<String>> imageViews) {
        rootView = (LinearLayout) findViewById(R.id.rootLinearLayout);
        if (imageViews == null) {
            return;
        }
        int i = 0;
        int j = 0;
        LinearLayout layout = null;
        FrameLayout frameLayout = null;
        for (Map.Entry<Integer, List<String>> item : imageViews.entrySet()) {
            if (i % COLUMN_NUM == 0) {
                j = 0;
                LinearLayout sectionLayout = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                sectionLayout.setLayoutParams(params);
                sectionLayout.setOrientation(LinearLayout.VERTICAL);
                //sectionLayout.setBackgroundColor(mResources.getColor(android.R.color.holo_red_dark));
                mSections.add(sectionLayout);
                rootView.addView(sectionLayout);

                layout = new LinearLayout(this);
                layout.setLayoutParams(params);
                mLayouts.add(layout);
                mSections.get(i / COLUMN_NUM).addView(layout);
            }
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) mImageWidth, (int) DensityUtil.dip2px(this, 150));
            imageView.setBackgroundColor(mResources.getColor(android.R.color.holo_blue_light));
            //imageView.setImageDrawable(mResources.getDrawable(item.getKey()));
            params.setMargins((int) DensityUtil.dip2px(this, mOffset), 0, (int) DensityUtil.dip2px(this, mOffset), 0);
            if (j == 0) {
                params.setMargins(0, 0, (int) DensityUtil.dip2px(this, mOffset), 0);
            } else if (j == COLUMN_NUM - 1) {
                params.setMargins((int) DensityUtil.dip2px(this, mOffset), 0, 0, 0);
            }
            imageView.setLayoutParams(params);
            mImages.add(imageView);

            mLayouts.get(i / COLUMN_NUM).addView(imageView);

            if (i % COLUMN_NUM == 0) {
                ArrowView arrowView = new ArrowView(this, mOffset);
                arrowView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DensityUtil.dip2px(this, 16f)));
                arrowView.setmAboveViewWidth(DensityUtil.px2dip(this, mImageWidth));
                mArrowViews.add(arrowView);
                mSections.get(i / COLUMN_NUM).addView(arrowView);

                frameLayout = new FrameLayout(this);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                mSections.get(i / COLUMN_NUM).addView(frameLayout);
            }

            CustomListView customListView = new CustomListView(this);
            customListView.setVisibility(View.GONE);
            customListView.setBackgroundColor(mResources.getColor(android.R.color.holo_green_light));
            FrameLayout.LayoutParams listParam = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            listParam.setMargins(0, 0, 0, (int) DensityUtil.dip2px(this, 16f));
            customListView.setLayoutParams(listParam);
            customListView.setAdapter(new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, item.getValue()));
            mLists.add(customListView);
            frameLayout.addView(customListView);

            i++;
            j++;
        }

        i = 0;
    }

    private void setEvents() {
        for (int i = 0; i < mImages.size(); i++) {
            final ImageView target = mImages.get(i);
            final int index = i;
            final int row = index / COLUMN_NUM;
            target.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrowView arrow = mArrowViews.get(row);
                    arrow.setmAboveViewIndex(index % COLUMN_NUM + 1);
                    arrow.postInvalidate();
                    if (mLists.get(index).getVisibility() != View.GONE) {
                        target.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    } else {
                        target.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    }
                    expand(index);
                    for (int j = 0; j < mImages.size(); j++) {
                        if (j != index) {
                            mImages.get(j).setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                        }
                    }

                    scrollMove(mScrollview, row);
                }
            });
        }
    }

    private void expand(int index) {
        int row = index / COLUMN_NUM;
        for (int i = 0; i < mLists.size(); i++) {
            CustomListView listView = mLists.get(i);
            if (index != i && listView.getVisibility() != View.GONE) {
                loadFadeOutAnimation(listView, View.GONE);
                if (row != i / COLUMN_NUM) {
                    loadFadeOutAnimation(mArrowViews.get(i / COLUMN_NUM), View.INVISIBLE);
                }
            }
            if (i == index) {
                if (mLists.get(index).getVisibility() == View.VISIBLE) {
                    mImages.get(index).setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    loadFadeOutAnimation(mLists.get(index), View.GONE);
                    loadFadeOutAnimation(mArrowViews.get(row), View.INVISIBLE);
                } else {
                    mLists.get(index).setVisibility(View.VISIBLE);
                    loadFadeInAnimation(listView);
                    loadFadeInAnimation(mArrowViews.get(row));
                }
            }
        }

    }

    private void loadFadeInAnimation(View view) {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        view.setVisibility(View.VISIBLE);
        view.startAnimation(fadeInAnimation);
    }

    private void loadFadeOutAnimation(final View view, final int visibility) {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(visibility);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(fadeOutAnimation);
    }

}
