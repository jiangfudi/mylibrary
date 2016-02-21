package com.qf.demo.test02.other.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.qf.demo.test02.R;
import com.qf.demo.test02.other.utils.ZhuShouContants;

public class MainActivity extends Activity {
    private ImageView ivIcon,ivLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFirstUsed()){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }else {
            setContentView(R.layout.activity_main);
            ivIcon = (ImageView) findViewById(R.id.main_icon_iv);
            ivLabel = (ImageView) findViewById(R.id.main_label_iv);

            Animation labelAnim
                    = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_main_label_in);

            final Animation iconAnim
                    = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_main_icon_in);

            labelAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ivIcon.startAnimation(iconAnim);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            iconAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ivIcon.setVisibility(View.VISIBLE);
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            ivLabel.startAnimation(labelAnim);
        }

    }

    private boolean isFirstUsed(){
        SharedPreferences preferences = getSharedPreferences(ZhuShouContants.PERFERENCE_FIRST_USED, Context.MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean(ZhuShouContants.PERFERENCE_FLAG_USED, true);
        return isFirstRun;
    }
}
