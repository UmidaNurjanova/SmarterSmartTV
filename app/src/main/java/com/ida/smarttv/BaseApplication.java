package com.ida.smarttv;

import android.app.Application;

import com.ida.smarttv.utils.CrashReportingTree;

import timber.log.Timber;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(BuildConfig.DEBUG
                ? new Timber.DebugTree()
                : new CrashReportingTree());
    }
}
