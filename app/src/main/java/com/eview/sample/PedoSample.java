package com.eview.sample;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.eview.db.eCareSettings;
import com.eview.ecare.data.StepCountData;

public class PedoSample {
    public static final String TAG = "PedoSample";
    private ContentResolver mContentResolver;
    private TextView textView;
    private StepCountData mStepCountData;

    private void queryStep() {
        ContentResolver contentResolver = mContentResolver;
        Cursor cursor = contentResolver.query(eCareSettings.StepCount.CONTENT_SHARE_DATA_URI,
                eCareSettings.StepCount.STEPCOUNT_QUERY_COLUMNS,
                "", null, "_id DESC");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                mStepCountData = new StepCountData(cursor);
                Log.d(TAG, "step count " + mStepCountData);
            }
            cursor.close();
        }
        mDisplayHandler.sendEmptyMessage(0);
    }

    private ContentObserver mObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            Log.d(TAG, "step notify!");
            queryStep();
        }
    };

    private Handler mDisplayHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            display();
        }
    };

    public void start(Context context, TextView textView) {
        mContentResolver = context.getContentResolver();
        mContentResolver.registerContentObserver(eCareSettings.StepCount.CONTENT_SHARE_DATA_URI, false, mObserver);
        this.textView = textView;
    }

    public void display() {
        if(mStepCountData == null)
            return;
        textView.setText("Step:" + mStepCountData.getStepCount());
    }

    public void query() {
        queryStep();
    }

    public void stop() {
        mContentResolver.unregisterContentObserver(mObserver);
    }
}
