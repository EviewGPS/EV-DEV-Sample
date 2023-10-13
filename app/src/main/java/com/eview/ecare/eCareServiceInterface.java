package com.eview.ecare;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.sample.test.eCareApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class eCareServiceInterface {
    public static String TAG = "eCareServiceInterface";
    public static final int MSG_ALERT_FALLDOWN_SETTINGS = 1;
    public static final int MSG_ALERT_FALLDOWN_QUERY = 2;
    public static final int MSG_ALERT_TILT_SETTINGS = 3;
    public static final int MSG_ALERT_TILT_QUERY = 4;
    public static final int MSG_ALERT_MOTION_SETTINGS = 5;
    public static final int MSG_ALERT_MOTION_QUERY = 6;
    public static final int MSG_ALERT_NOMOTION_SETTINGS = 7;
    public static final int MSG_ALERT_NOMOTION_QUERY = 8;
    public static final int MSG_ECARE_LED_SWITCH_CTRL = 9;
    public static final int MSG_LED_SET = 10;
    public static final int MSG_SET_VOLTE = 11;
    public static final int MSG_ECARE_ALERT_SWITCH_CTRL = 12;
    public static final int MSG_SET_VIB_PACKAGENAME = 13;
    public static final int MSG_ADD_WIFI_SCAN_THROTTLE = 14;
    public static final int MSG_SET_DATASHIP_PACKAGENAME = 15;
    public static final int MSG_SET_NETWORK_PREFERRED_TYPE = 16;

    public static final int MSG_ECARE_VIB_SWITCH_CTRL = 17;

    public static final int MSG_ECARE_RAISE_HAND_CTRL = 18;

    public static final int MSG_ECARE_VOLUME_CTRL = 19;
    private Context mContext;
    private Messenger mServerMessage;
    private List<eCareServiceListener> mFwServiceListener = new ArrayList<>();

    public eCareServiceInterface(Context context) {
        mContext = context;
    }

    private Messenger mReplayToMessage = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage what=" + msg.what);
            Bundle bundle = msg.getData();
            switch (msg.what) {
                case MSG_ALERT_FALLDOWN_SETTINGS:
                    Log.d(TAG, "fall settings replay");
                    break;
                case MSG_ALERT_FALLDOWN_QUERY:
                    Log.d(TAG, "fall down settings enable=" + bundle.getBoolean("enable")
                            + ",dial=" + bundle.getBoolean("dial")
                            + ",level=" + bundle.getInt("level"));
                    break;
                case MSG_ALERT_TILT_SETTINGS:
                    Log.d(TAG, "tilt settings replay");
                    break;
                case MSG_ALERT_TILT_QUERY:
                    Log.d(TAG, "tilt settings enable=" + bundle.getBoolean("enable")
                            + ",dial=" + bundle.getBoolean("dial")
                            + ",angle=" + bundle.getInt("angle")
                            + ",time=" + bundle.getLong("time")
                    );
                    break;
                case MSG_ALERT_MOTION_SETTINGS:
                    Log.d(TAG, "motion settings replay");
                    break;
                case MSG_ALERT_MOTION_QUERY:
                    Log.d(TAG, "motion settings enable=" + bundle.getBoolean("enable")
                            + ",dial=" + bundle.getBoolean("dial")
                            + ",activeTime=" + bundle.getLong("activeTime")
                            + ",setupTime=" + bundle.getLong("setupTime")
                    );
                    break;
                case MSG_ALERT_NOMOTION_SETTINGS:
                    Log.d(TAG, "no motion settings replay");
                    break;
                case MSG_ALERT_NOMOTION_QUERY:
                    Log.d(TAG, "no motion settings enable=" + bundle.getBoolean("enable")
                            + ",dial=" + bundle.getBoolean("dial")
                            + ",time=" + bundle.getLong("time")
                    );
                    break;
                case MSG_ECARE_LED_SWITCH_CTRL:
                    Log.d(TAG, "led switch settings replay");
                    break;
                case MSG_LED_SET:
                    Log.d(TAG, "led set replay");
                    break;
                case MSG_SET_VIB_PACKAGENAME:
                    Log.d(TAG, "set vib pkg replay");
                    break;
                case MSG_ADD_WIFI_SCAN_THROTTLE:
                    Log.d(TAG, "add wifi scan throttle replay");
                    break;
                case MSG_SET_DATASHIP_PACKAGENAME:
                    Log.d(TAG, "add data shiping replay");
                    break;
                case MSG_SET_NETWORK_PREFERRED_TYPE:
                    Log.d(TAG, "set network prefer replay");
                    break;
                case MSG_ECARE_RAISE_HAND_CTRL:
                    Log.d(TAG, "set Raise hand replay");
                    break;
                case MSG_ECARE_VOLUME_CTRL:
                    Log.d(TAG, "set  Volume replay");
                    break;
            }
        }
    });

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServerMessage = new Messenger(service);
            notifyServiceUsable(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            notifyServiceUsable(false);
            mServerMessage = null;
        }
    };

    public interface eCareServiceListener {
        boolean onServiceUsable(boolean usable);
    }

    private void notifyServiceUsable(boolean usable) {
        Iterator<eCareServiceListener> iterator = mFwServiceListener.iterator();
        while (iterator.hasNext()) {
            eCareServiceListener item = iterator.next();
            boolean remove = item.onServiceUsable(usable);
            if (remove) {
                iterator.remove();
                Log.d(TAG, "FwService remove1 num=" + mFwServiceListener.size());
            }
        }
    }

    public void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.eview.ecare.eCareService");
        intent.setPackage("com.eview.ecare");
        mContext.bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    public void unBindService() {
        mContext.unbindService(mServiceConn);
    }

    public boolean sendMessage(Message message) {
        message.replyTo = mReplayToMessage;
        if (mServerMessage == null) {
            Log.e(TAG, "Fw Service unbind msg=" + message);
            bindService();
            return false;
        }
        try {
            mServerMessage.send(message);
            return true;
        } catch (DeadObjectException e) {
            e.printStackTrace();
            bindService();
            Log.e(TAG, "error=" + e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG, "error=" + e.getMessage());
        }
        return false;
    }

    public void addServerListener(eCareServiceListener listener) {
        Log.d(TAG, "FwService add num=" + mFwServiceListener.size());
        if (mFwServiceListener.contains(listener))
            return;
        mFwServiceListener.add(listener);
    }

    private static eCareServiceInterface serviceInterface;

    public static eCareServiceInterface getServiceInterface(Context context) {
        if (serviceInterface == null)
            serviceInterface = new eCareServiceInterface(context);
        return serviceInterface;
    }

    public static eCareServiceInterface getServiceInterface() {
        if (serviceInterface == null)
            serviceInterface = new eCareServiceInterface(eCareApplication.getInstance());
        return serviceInterface;
    }
}
