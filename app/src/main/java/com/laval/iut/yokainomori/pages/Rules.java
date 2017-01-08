package com.laval.iut.yokainomori.pages;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.laval.iut.yokainomori.R;
import com.ortiz.touch.ExtendedViewPager;
import com.ortiz.touch.TouchImageView;

/**
 * Created by lione on 10/12/2016.
 */
public class Rules extends Page {

    private ViewGroup root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_rules, container, false);

        init();

        return root;
    }


    public void init() {
        final ExtendedViewPager mViewPager = (ExtendedViewPager) root.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new TouchImageAdapter());
        ((ImageButton)root.findViewById(R.id.rules_left)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mViewPager.getCurrentItem()>0)
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
            }
        });
        ((ImageButton)root.findViewById(R.id.rules_right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mViewPager.getCurrentItem()<TouchImageAdapter.images.length-1)
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
            }
        });
        ((Button)root.findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(PageName.HOME);
            }
        });
    }
    static class TouchImageAdapter extends PagerAdapter {

        private static int[] images = { R.drawable.rules1, R.drawable.rules2, R.drawable.rules3, R.drawable.rules4 };

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            TouchImageView img = new TouchImageView(container.getContext());
            img.setImageResource(images[position]);
            container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

}
