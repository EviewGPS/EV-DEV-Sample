package com.eview.sample;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.eview.ecare.eCareServiceInterface;

public class MotionSample {

    private static final String TAG = "MotionSample";


    private static MotionSample instance;

    private boolean motion;

    private MotionSample() {
    }

    public static MotionSample getInstance(){
        if (instance == null){
            instance = new MotionSample();
        }
        return instance;
    }


    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            int motion = msg.arg1;
            onMotion(motion);
        }
    };



    private void onMotion(int motion) {
        Log.d(TAG,"motion --->" + motion);
    }


    /**
     * 1、register motion state Listener
     * @return
     */
    public static boolean registerMotionState(){
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_REGISTER_MOTION);
        message.arg1 = -1;
        return eCareServiceInterface.getServiceInterface().sendMessage(message);
    }

    /**
     * 2、receive motion state
     * @param motion 1 motion 0 no motion
     */
    public void receiveMotionState(int motion){
        Message message = mHandler.obtainMessage();
        message.arg1 = motion;
        mHandler.sendMessage(message);
    }

    /**
     * query motion result
     * @param motion
     */
    public void motionStateResult(boolean motion){
        Log.d(TAG,"motion = " + motion);
        this.motion = motion;
    }

    /**
     * query motion current state
     */
    public static void queryMotion(){
        Message message = Message.obtain(null, eCareServiceInterface.MSG_ECARE_QUERY_MOTION);
        eCareServiceInterface.getServiceInterface().sendMessage(message);
    }
}
