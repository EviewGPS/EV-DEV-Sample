package com.eview.ecare.data;
public abstract class CollectDataUpload {
    public static final int STATE_INIT = 0;
    public static final int STATE_SENDING = -1;
    public static final int STATE_SEND_OK = 1;
    public static final int STATE_SEND_FAILED = -3;

    private int state;
    private int retryUploadTimes;

    public CollectDataUpload() {
        state = STATE_INIT;
        retryUploadTimes = 0;
    }

    public CollectDataUpload(int state, int retryUploadTimes) {
        super();
        this.state = state;
        this.retryUploadTimes = retryUploadTimes;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRetryUploadTimes() {
        return retryUploadTimes;
    }

    public void setRetryUploadTimes(int retryUploadTimes) {
        this.retryUploadTimes = retryUploadTimes;
    }
}
