package com.qf.demo.test02.gift.ui;

import android.support.v4.view.ViewPager;

import com.qf.demo.test02.R;
import com.qf.demo.test02.gift.adapter.GiftListAdapter;
import com.qf.demo.test02.other.ui.BaseFragment;

/**
 * Created by Administrator on 16-1-15.
 */
public class GiftFragment extends BaseFragment{
    private ViewPager vpContent;//声明一个ViewPager属性
    private GiftListAdapter pagerAdapter;//声明一个适配器对象

    @Override
    protected int getLyout() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initViews() {
        vpContent = (ViewPager) root.findViewById(R.id.gift_type_vp);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }
}
