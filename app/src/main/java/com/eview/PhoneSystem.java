package com.eview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;

import com.eview.ecare.eCareServiceInterface;

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

    public static boolean setVolte(boolean enable) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_SET_VOLTE);
        message.arg1 = enable ? 1 : 0;
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static boolean addWifiBackgroundWhitelist(String pkgName) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ADD_WIFI_SCAN_THROTTLE);
        Bundle bundle = new Bundle();
        bundle.putString("pkgName", pkgName);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static boolean addDataShipWhitelist(String pkgName) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_SET_DATASHIP_PACKAGENAME);
        Bundle bundle = new Bundle();
        bundle.putString("pkgName", pkgName);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    /*
     0:(234);
     1:(23);
     2:2G
     3:3G
     4:4G
     5:3G/4G
    * */
    public static boolean setPreferNetworkType(int type) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_SET_NETWORK_PREFERRED_TYPE);
        message.arg1 = type;
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
}
