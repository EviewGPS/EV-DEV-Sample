package com.eview.sample;

import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class AlertSample {
    public static final String ACTION_ALERT_START = "android.intent.action.eCareAlertStart";
    public static final String ACTION_ALERT_STOP = "android.intent.action.eCareAlertStop";
    public static final String ALERT_TYPE = "alarmCode";
    public static final String ALERT_TIMESTAMP = "timestamp";

    public static final long ALMCODE_SOS = (1 << 12);
    public static final long ALMCODE_MOTION = (1 << 10);
    public static final long ALMCODE_NO_MOTION = (1 << 11);
    public static final long ALMCODE_TILT = (1 << 3);
    public static final long ALMCODE_FALL_DOWN = (1 << 2);

    public static final String ACTION_ALERT_DETECTED = "android.intent.action.eCareAlertDetected";
    public static final String ALERT_FLAG = "flag";

    public static final String TAG = "AlertSample";

    public static boolean process(Intent intent) {
        final String action = intent.getAction();
        if (action.equalsIgnoreCase(ACTION_ALERT_START)) {
            long alarmCode = intent.getLongExtra(ALERT_TYPE, 0);
            if ((alarmCode & ALMCODE_SOS) == ALMCODE_SOS) {
                Log.d(TAG, "sos start!");
            }
            if ((alarmCode & ALMCODE_MOTION) == ALMCODE_MOTION) {
                Log.d(TAG, "motion start!");
            }
            if ((alarmCode & ALMCODE_NO_MOTION) == ALMCODE_NO_MOTION) {
                Log.d(TAG, "no motion start!");
            }
            if ((alarmCode & ALMCODE_TILT) == ALMCODE_TILT) {
                Log.d(TAG, "tilt start!");
            }
            if ((alarmCode & ALMCODE_FALL_DOWN) == ALMCODE_FALL_DOWN) {
                Log.d(TAG, "fall start!");
            }
            return true;
        } else if (action.equalsIgnoreCase(ACTION_ALERT_STOP)) {
            long timstamp = intent.getLongExtra(ALERT_TIMESTAMP, 0);
            Log.d(TAG, "alarm stop timstamp=" + timstamp);
            return true;
        } else if (action.equalsIgnoreCase(ACTION_ALERT_DETECTED)) {
            long alarmFlag = intent.getLongExtra(ALERT_FLAG, 0);
            if ((alarmFlag & ALMCODE_SOS) == ALMCODE_SOS) {
                Log.d(TAG, "sos detected!");
            }
            if ((alarmFlag & ALMCODE_MOTION) == ALMCODE_MOTION) {
                Log.d(TAG, "motion detected!");
            }
            if ((alarmFlag & ALMCODE_NO_MOTION) == ALMCODE_NO_MOTION) {
                Log.d(TAG, "no motion detected!");
            }
            if ((alarmFlag & ALMCODE_TILT) == ALMCODE_TILT) {
                Log.d(TAG, "tilt detected!");
            }
            if ((alarmFlag & ALMCODE_FALL_DOWN) == ALMCODE_FALL_DOWN) {
                Log.d(TAG, "fall detected!");
            }
            return true;
        }
        return false;
    }

    public static void addActionFilter(IntentFilter intentFilter) {
        intentFilter.addAction(ACTION_ALERT_START);
        intentFilter.addAction(ACTION_ALERT_STOP);
        intentFilter.addAction(ACTION_ALERT_DETECTED);
    }
}
