package com.eview;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public class PhoneSystem {
    public static boolean shutdown(Context context) {
        /*    <uses-permission android:name="android.permission.SHUTDOWN"/>*/
        Intent intent = new Intent(
                "com.android.internal.intent.action.REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return true;
    }

    public static boolean reboot(Context context) {
        PowerManager pm = (PowerManager) context
                .getSystemService(Context.POWER_SERVICE);
        pm.reboot("ecarefw");
        return true;
    }
}
