package com.eview.sample;

import android.os.Bundle;
import android.os.Message;

import com.eview.ecare.eCareServiceInterface;
/*
* support ev04 only
* step1, call  method turn off eCare app control : setECareControl(false);
* step2, call setLed update led state, if param is -1, state will not update.
* */
public class LedSample {
    //led state mode
    public static final int LED_STATE_OFF = 0;//0-	OFF
    public static final int LED_STATE_ON = 1;//1-	ON
    public static final int LED_STATE_525 = 2;//2-	ON 500ms, OFF 2500ms，Repeat
    public static final int LED_STATE_129 = 3;//3-	ON 100ms,OFF 2900ms，Repeat
    public static final int LED_STATE_13125 = 4;//4-	ON 100ms, OFF 300ms, ON 100ms, OFF 2500ms，repeat
    public static final int LED_STATE_327 = 5;//5-	ON 300ms, OFF 2700ms，repeat
    public static final int LED_STATE_129_x = 6;//6-	ON 100ms, OFF 2900ms，repeat
    public static final int LED_STATE_1020 = 7;//7-	ON 1000ms, OFF 2000ms，repeat
    public static final int LED_STATE_1_0s = 8;//8-	ON 100ms, OFF～
    public static final int LED_STATE_131_0s = 9;//9-	ON 100ms, OFF 300ms, ON 100ms, OFF～
    public static final int LED_STATE_13131_0s = 10;//10-	ON 100ms, OFF 300ms, ON 100ms, OFF 300ms, ON 100ms, OFF～
    public static final int LED_STATE_1313131_0s = 11;//11-	ON 100ms, OFF 300ms, ON 100ms, OFF 300ms, ON 100ms, OFF 300ms, ON 100ms, OFF～
    public static final int LED_STATE_131313131_0s = 12;//12-	ON 100ms, OFF 300ms, ON 100ms, OFF 300ms, ON 100ms, OFF 300ms, ON 100ms, OFF 300ms, ON 100ms, OFF～
    public static final int LED_STATE_11 = 14;//14- ON 100ms, OFF 100ms, repeat

    public static final int LED_BR_STATE_B_D = 0x0d;//breath <SOS button led only>
    public static final int LED_BR_STATE_R_D = 0xd0;//breath <SOS button led only>
    public static final int LED_BR_STATE_BR_DD = 0xdd;//breath <SOS button led only>

    public static boolean setLed(int modeR, int modeG, int modeB, int modeBR) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_LED_SET);
        Bundle bundle = new Bundle();
        bundle.putInt("modeR", modeR);
        bundle.putInt("modeG", modeG);
        bundle.putInt("modeB", modeB);
        bundle.putInt("modeBR", modeBR);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static boolean setECareControl(boolean enable) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_LED_SWITCH_CTRL);
        Bundle bundle = new Bundle();
        bundle.putBoolean("enable", enable);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
}
