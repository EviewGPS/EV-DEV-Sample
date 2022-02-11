package com.eview.sample;

import android.content.Context;
import android.content.Intent;

public class GestureSample {
    public static void setGesture(Context context, boolean left) {
        final String ACTION_GESTURE_SETTINGS = "android.intent.action.gestureSettings";
        Intent intent = new Intent(ACTION_GESTURE_SETTINGS);
        intent.putExtra("leftEnable", left);
        intent.putExtra("rightEnable", false);
        context.sendBroadcast(intent);
    }
}
