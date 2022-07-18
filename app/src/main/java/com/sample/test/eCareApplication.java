package com.sample.test;

import android.app.Application;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eview.ecare.eCareServiceInterface;


public class eCareApplication extends Application {
    private static Application mInstance;
    public static SharedPreferences sharedPreferences;
    public static Service mServiceInstance;
    public static ContentResolver mContentResolver;
    public LogWatcher mLogWatcher;
    private eCareServiceInterface serviceInterface;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContentResolver = getContentResolver();
        serviceInterface = eCareServiceInterface.getServiceInterface(this);
        serviceInterface.bindService();
        mLogWatcher = LogWatcher.getInstance().init(getApplicationContext(), "ev-log");
        mLogWatcher.startWatch();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        serviceInterface.unBindService();
        mLogWatcher.stopWatch();
    }

    public static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mInstance);
        }
        return sharedPreferences;
    }

    public static Application getInstance() {
        return mInstance;
    }

    public static void setServiceInstance(Service instance) {
        mServiceInstance = instance;
    }

    public static Context getContext() {
        if (mServiceInstance == null)
            return mInstance;
        return mServiceInstance;
    }

    public static ContentResolver getRunContentResolver() {
        return mContentResolver;
    }
}
