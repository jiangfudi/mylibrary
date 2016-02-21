package com.qf.demo.test02.other.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qf.demo.test02.R;
import com.qf.demo.test02.other.adapter.GuideAdapter;
import com.qf.demo.test02.other.utils.JumpManager;
import com.qf.demo.test02.other.utils.ZhuShouContants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-1-15.
 */
public class GuideActivity extends Activity{
    private ViewPager viewPager;
    private GuideAdapter adapter;
    private List<ImageView> list;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.guide_content_vp);
        btn = (Button) findViewById(R.id.guide_jump_btn);

        list = new ArrayList<>();

        final int[] bitmaps = new int[]{
                R.drawable.bg_guide_01,
                R.drawable.bg_guide_02,
                R.drawable.bg_guide_03,
                R.drawable.bg_guide_04
        };

        for (int i = 0;i < bitmaps.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(bitmaps[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }

        adapter = new GuideAdapter(list);

        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == (bitmaps.length - 1)) {
                    btn.setVisibility(View.VISIBLE);
                } else {
                    btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(ZhuShouContants.PERFERENCE_FIRST_USED, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(ZhuShouContants.PERFERENCE_FLAG_USED, false);
                editor.commit();
                JumpManager.jumpToHome(GuideActivity.this);
                finish();
            }
        });
    }
}
