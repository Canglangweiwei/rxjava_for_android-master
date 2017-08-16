package com.che58.ljb.rxjava;

import android.app.Application;

/**
 * Demo Application
 * Created by ljb on 2016/3/23.
 */
@SuppressWarnings("ALL")
public class DemoApplication extends Application {

    public static Application mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Application get() {
        return mApp;
    }
}
