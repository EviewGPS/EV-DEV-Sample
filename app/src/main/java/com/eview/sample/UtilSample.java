package com.eview.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.eview.ecare.eCareServiceInterface;

public class UtilSample {
    public static void setGesture(Context context, boolean left) {
        final String ACTION_GESTURE_SETTINGS = "android.intent.action.gestureSettings";
        Intent intent = new Intent(ACTION_GESTURE_SETTINGS);
        intent.putExtra("leftEnable", left);
        intent.putExtra("rightEnable", false);
        context.sendBroadcast(intent);
    }

    public static boolean setVibratePackageName(String pkgName) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_SET_VIB_PACKAGENAME);
        Bundle bundle = new Bundle();
        bundle.putString("pkgName", pkgName);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
}
