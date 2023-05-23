package com.eview.sample;

import android.os.Bundle;
import android.os.Message;

import com.eview.ecare.eCareServiceInterface;

public class VibrateSample {

    public static boolean setECareControl(boolean enable) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_VIB_SWITCH_CTRL);
        Bundle bundle = new Bundle();
        bundle.putBoolean("enable", enable);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
}
