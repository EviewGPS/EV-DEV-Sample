package com.eview.sample;

import android.os.Bundle;
import android.os.Message;

import com.eview.ecare.eCareServiceInterface;

public class AlertSettings {
    public static boolean setECareSOSControl(boolean sosEn) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_ALERT_SWITCH_CTRL);
        Bundle bundle = new Bundle();
        bundle.putInt("sos", sosEn ? 1 : 0);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
    public static boolean setECareFallControl(boolean fallEn) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_ALERT_SWITCH_CTRL);
        Bundle bundle = new Bundle();
        bundle.putInt("fall", fallEn ? 1 : 0);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
    public static boolean setECareMotionControl(boolean motionEn) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_ALERT_SWITCH_CTRL);
        Bundle bundle = new Bundle();
        bundle.putInt("motion", motionEn ? 1 : 0);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
    public static boolean setECareNoMotionControl( boolean nomotionEn) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_ALERT_SWITCH_CTRL);
        Bundle bundle = new Bundle();
        bundle.putInt("nomotion", nomotionEn ? 1 : 0);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
    public static boolean setECareTiltControl(boolean tiltEn) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_ALERT_SWITCH_CTRL);
        Bundle bundle = new Bundle();
        bundle.putInt("tilt", tiltEn ? 1 : 0);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static boolean setFalldown(boolean enable, boolean dial, int level) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ALERT_FALLDOWN_SETTINGS);
        Bundle bundle = new Bundle();
        bundle.putBoolean("enable", enable);
        bundle.putBoolean("dial", dial);
        bundle.putInt("level", level);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static void queryFalldownSettings() {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ALERT_FALLDOWN_QUERY);
        eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static boolean setTilt(boolean enable, boolean dial, int angle, long time) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ALERT_TILT_SETTINGS);
        Bundle bundle = new Bundle();
        bundle.putBoolean("enable", enable);
        bundle.putBoolean("dial", dial);
        bundle.putInt("angle", angle);
        bundle.putLong("time", time);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static void queryTiltSettings() {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ALERT_TILT_QUERY);
        eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static boolean setMotion(boolean enable, boolean dial, long activeTime, long setupTime) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ALERT_MOTION_SETTINGS);
        Bundle bundle = new Bundle();
        bundle.putBoolean("enable", enable);
        bundle.putBoolean("dial", dial);
        bundle.putLong("activeTime", activeTime);
        bundle.putLong("setupTime", setupTime);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static void queryMotionSettings() {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ALERT_MOTION_QUERY);
        eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static boolean setNoMotion(boolean enable, boolean dial, long time) {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ALERT_NOMOTION_SETTINGS);
        Bundle bundle = new Bundle();
        bundle.putBoolean("enable", enable);
        bundle.putBoolean("dial", dial);
        bundle.putLong("time", time);
        message.setData(bundle);
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    public static void queryNoMotionSettings() {
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ALERT_NOMOTION_QUERY);
        eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
}
