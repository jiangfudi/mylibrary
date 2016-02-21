package com.qf.demo.test02.other.utils;

import android.app.Activity;
import android.content.Intent;

import com.qf.demo.test02.other.ui.HomeActivity;

/**
 * 跳转工具类
 * Created by Administrator on 16-1-16.
 */
public class JumpManager {
    /**
     * 跳转到主页面
     * 发起页面
     */
    public static void jumpToHome(Activity activity){
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }
}
