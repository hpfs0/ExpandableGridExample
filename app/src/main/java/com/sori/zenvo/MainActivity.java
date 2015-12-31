package com.sori.zenvo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ImageView> mImages;
    private List<ListView> mLists;
    private ArrowView mArrowView1, mArrowView2,mArrowView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setEvents();

    }

    private void init(){
        int[] imgId = new int[] {
                R.id.img1,
                R.id.img2,
                R.id.img3,
                R.id.img4,
                R.id.img5,
                R.id.img6,
                R.id.img7,
                R.id.img8,
                R.id.img9
        };
        int[] listId = new int[] {
                R.id.list1,
                R.id.list2,
                R.id.list3,
                R.id.list4,
                R.id.list5,
                R.id.list6,
                R.id.list7,
                R.id.list8,
                R.id.list9
        };
        mImages = new ArrayList<>();
        for(int i = 0; i< imgId.length; i++){
            mImages.add((ImageView)findViewById(imgId[i]));
        }
        mLists = new ArrayList<>();
        for(int i = 0; i< listId.length; i++){
            mLists.add((ListView)findViewById(listId[i]));
        }
        for (int i = 0; i< mLists.size(); i++){
            String[] data = new String[i+1];
            for(int k=0; k <= i; k++){
                data[k] = i + 1 + "";
            }
            mLists.get(i).setAdapter(new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1, data));
        }
        mArrowView1 = (ArrowView) findViewById(R.id.arrowview1);
        mArrowView2 = (ArrowView) findViewById(R.id.arrowview2);
        mArrowView3 = (ArrowView) findViewById(R.id.arrowview3);
    }

    private void setEvents(){
        for (int i = 0; i < mImages.size(); i++){
            final ImageView target = mImages.get(i);
            final int index = i;
            target.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    target.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    for (int j = 0; j < mImages.size(); j++) {
                        if (j != index) {
                            mImages.get(j).setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                        }
                    }
                    int row = index / 3;
                    if (row < 1) {
                        mArrowView1.setmAboveViewIndex(index % 3);
                        mArrowView1.postInvalidate();
                    } else if (row < 2) {
                        mArrowView2.setmAboveViewIndex(index % 3);
                        mArrowView2.postInvalidate();
                    } else {
                        mArrowView3.setmAboveViewIndex(index % 3);
                        mArrowView3.postInvalidate();
                    }
                    expand(index);
                }
            });
        }
    }

    private void expand(int index){
        int row = index / 3;
        for(int i = 0; i < mLists.size(); i++){
            ListView listView = mLists.get(i);
            if(index != i && listView.getVisibility() != View.GONE){
                loadFadeOutAnimation(listView, View.GONE);
                if(index / 3 != i / 3) {
                    if (i / 3 < 1) {
                        loadFadeOutAnimation(mArrowView1, View.INVISIBLE);
                    } else if (i / 3 < 2) {
                        loadFadeOutAnimation(mArrowView2, View.INVISIBLE);
                    } else if (i / 3 < 3) {
                        loadFadeOutAnimation(mArrowView3, View.INVISIBLE);
                    }
                }
            }
            if(i == index) {
                if (mLists.get(index).getVisibility() == View.VISIBLE) {
                    mImages.get(index).setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    loadFadeOutAnimation(mLists.get(index), View.GONE);
                    if (index / 3 < 1) {
                        loadFadeOutAnimation(mArrowView1, View.INVISIBLE);
                    } else if (index / 3 < 2) {
                        loadFadeOutAnimation(mArrowView2, View.INVISIBLE);
                    } else if (index / 3 < 3) {
                        loadFadeOutAnimation(mArrowView3, View.INVISIBLE);
                    }
                } else {
                    mLists.get(index).setVisibility(View.VISIBLE);
                    loadFadeInAnimation(mLists.get(index));
                    if(row < 1){
                        mArrowView1.setVisibility(View.VISIBLE);
                        loadFadeInAnimation(mArrowView1);
                    }else if (row < 2){
                        mArrowView2.setVisibility(View.VISIBLE);
                        loadFadeInAnimation(mArrowView2);
                    }else if(row < 3){
                        mArrowView3.setVisibility(View.VISIBLE);
                        loadFadeInAnimation(mArrowView3);
                    }
                }
            }
        }

    }

    private void loadFadeInAnimation(View view){
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        view.startAnimation(fadeInAnimation);
    }

    private void loadFadeOutAnimation(final View view, final int visibility){
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
