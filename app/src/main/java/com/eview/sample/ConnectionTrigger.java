package com.eview.sample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class ConnectionTrigger {
    public static final String ACTION_CONNECT_SERVER = "android.intent.action.CONNECT_SERVER";
    private static final String ECARE_PACKAGE = "com.eview.ecare";
    private static final String ECARE_MAIN_RECIVER = "com.eview.ecare.eCareBroadcastReceiver";
    public static void triggerConnect(Context context) {
        final ComponentName componentname = new ComponentName(ECARE_PACKAGE,
                ECARE_MAIN_RECIVER);
        Intent intent = new Intent(ACTION_CONNECT_SERVER);
        intent.setComponent(componentname);
        intent.addFlags(0x01000000);
        context.sendBroadcast(intent);
    }
}
