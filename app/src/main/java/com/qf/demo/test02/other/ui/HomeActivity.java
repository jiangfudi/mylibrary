package com.qf.demo.test02.other.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.qf.demo.test02.R;
import com.qf.demo.test02.active.ui.ActiveFragment;
import com.qf.demo.test02.exchange.ui.ExChangeFragment;
import com.qf.demo.test02.gift.ui.GiftFragment;
import com.qf.demo.test02.my.ui.MyFragment;
import com.qf.demo.test02.profit.ui.ProfitFragment;

/**
 * Created by Administrator on 16-1-15.
 */
public class HomeActivity extends FragmentActivity {
    private Fragment[] fragments;//声明一个Fragment数组
    private FrameLayout flContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragments = new Fragment[]{
                new ProfitFragment(),
                new GiftFragment(),
                new ActiveFragment(),
                new ExChangeFragment(),
                new MyFragment()
        };

        flContent = (FrameLayout) findViewById(R.id.home_content_fl);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        for (int i = 0; i < fragments.length; i++){
            Fragment fragment = fragments[i];
            transaction.add(R.id.home_content_fl,fragment);
            transaction.hide(fragment);
        }
        transaction.show(fragments[1]);
        transaction.commit();
    }
}
