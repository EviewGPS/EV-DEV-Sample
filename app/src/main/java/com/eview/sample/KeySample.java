package com.eview.sample;

import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class KeySample {
    public static final String ACTION_KEY_EVENT = "android.intent.action.eCareKeyEventInternal";
    public static final String ECARE_KEYCODE = "keyCode";
    public static final String ECARE_KEYID = "keyId";
    public static final String ECARE_KEYACTION = "keyAction";
    public static final String ECARE_KEYTIME = "keyTime";

    public static final int KEY_ID1 = 1;//sos
    public static final int KEY_ID2 = 2;//down
    public static final int KEY_ID3 = 3;//up/sos

    public static final String TAG = "KeySample";

    public static boolean process(Intent intent) {
        final String action = intent.getAction();

        if (!action.equalsIgnoreCase(ACTION_KEY_EVENT)) {
            return false;
        }
        int keyCode = intent.getIntExtra(ECARE_KEYCODE, 0);
        int keyId = intent.getIntExtra(ECARE_KEYID, 0);
        int keyAction = intent.getIntExtra(ECARE_KEYACTION, 0);
        long timestamp = intent.getLongExtra(ECARE_KEYTIME, 0);
        Log.d(TAG, "keyEvent keyCode=" + keyCode + " keyId=" + keyId + " keyAction=" + keyAction + " Timestamp=" + timestamp);
        switch (keyId) {
            case KEY_ID1:
                if (keyAction == 0)
                    Log.d(TAG, "sos press!");
                else
                    Log.d(TAG, "sos release!");
                break;
            case KEY_ID2:
                if (keyAction == 0)
                    Log.d(TAG, "down press!");
                else
                    Log.d(TAG, "down release!");
                break;
            case KEY_ID3:
                if (keyAction == 0)
                    Log.d(TAG, "up press!");
                else
                    Log.d(TAG, "up release!");
                break;
        }
        return true;
    }

    public static void addActionFilter(IntentFilter intentFilter) {
        intentFilter.addAction(ACTION_KEY_EVENT);
    }
}
