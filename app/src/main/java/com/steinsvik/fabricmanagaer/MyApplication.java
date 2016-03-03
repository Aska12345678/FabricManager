package com.steinsvik.fabricmanagaer;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Gigabyte on 2/29/2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

    }
}
