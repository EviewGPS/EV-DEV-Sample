package com.eview.sample;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class HrsSample {
    public static final String INTENT_ACTION_HRS = "android.intent.hrs.control";
    public static final String INTENT_NOTIFY_RESULT_HRS = "android.intent.hrs.result_notify";
    public static final String HRS_TIMESTAMP = "timestamp";
    public static final String HRS_UTCTIME = "time";
    public static final String HRS_TRUSTLEVEL = "trustLevel";
    public static final String HRS_VALUE = "value";

    public static final String TAG = "HrsSample";
    private int hrsValue;
    private Context mContext;
    private TextView textView;

    public boolean process(Intent intent) {
        final String action = intent.getAction();
        if (action.equalsIgnoreCase(INTENT_NOTIFY_RESULT_HRS)) {
            long timestamp = intent.getLongExtra(HRS_TIMESTAMP, 0);
            long time = intent.getLongExtra(HRS_UTCTIME, 0);
            int trustLevel = intent.getIntExtra(HRS_TRUSTLEVEL, 0);
            int hrsValue = intent.getIntExtra(HRS_VALUE, 0);
            Log.d(TAG, "timestamp=" + timestamp);
            Log.d(TAG, "time=" + time);
            Log.d(TAG, "trustLevel=" + trustLevel);
            Log.d(TAG, "hrsValue=" + hrsValue);
            this.hrsValue = hrsValue;
            mDisplayHandler.sendEmptyMessage(0);
            return true;
        }
        return false;
    }

    public void addActionFilter(IntentFilter intentFilter) {
        intentFilter.addAction(INTENT_NOTIFY_RESULT_HRS);
    }

    private Handler mDisplayHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            display();
        }
    };

    public void start(Context context, TextView textView) {
        mContext = context;
        this.textView = textView;
        Intent intent = new Intent();
        intent.setAction(INTENT_ACTION_HRS);
        intent.putExtra("state", true);
        context.sendBroadcast(intent);
    }

    public void display() {
        textView.setText("HR:" + hrsValue);
    }

    public void stop() {
        if(mContext == null)
            return;
        Intent intent = new Intent();
        intent.setAction(INTENT_ACTION_HRS);
        intent.putExtra("state", false);
        mContext.sendBroadcast(intent);
        mContext = null;
    }
}
