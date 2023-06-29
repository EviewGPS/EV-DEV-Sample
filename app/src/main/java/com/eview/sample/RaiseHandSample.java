package com.eview.sample;

import android.os.Bundle;
import android.os.Message;

import com.eview.ecare.eCareServiceInterface;

public class RaiseHandSample {

    public static boolean setECareRaiseHandControl(boolean enable) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_RAISE_HAND_CTRL);
        Bundle bundle = new Bundle();
        bundle.putBoolean("enable", enable);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
}
