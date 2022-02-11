package com.eview.ecare.data;

import android.database.Cursor;
import android.database.MatrixCursor;

import com.eview.db.eCareSettings;

public class StepCountData extends CollectDataUpload{
    private long id;
    private long stepCount;
    private long dayStepCount;
    private long hourStepCount;
    private float distance;
    private float calories;
    private long datetime;
    private String desc;

    public StepCountData(long dayStepCount, float distance, float calories, long datetime) {
        this.dayStepCount = dayStepCount;
        this.distance = distance;
        this.calories = calories;
        this.datetime = datetime;
    }

    public StepCountData(Cursor cursor){
        super(cursor.getInt(cursor.getColumnIndex(eCareSettings.StepCount.STATE)),
                cursor.getInt(cursor.getColumnIndex(eCareSettings.StepCount.RESENDTIMES)));
        id = cursor.getLong(cursor.getColumnIndex(eCareSettings.StepCount._ID));
        stepCount = cursor.getLong(cursor.getColumnIndex(eCareSettings.StepCount.COUNT));
        dayStepCount = cursor.getLong(cursor.getColumnIndex(eCareSettings.StepCount.DAYSTEP));
        hourStepCount = cursor.getLong(cursor.getColumnIndex(eCareSettings.StepCount.HOURSTEP));
        distance = cursor.getFloat(cursor.getColumnIndex(eCareSettings.StepCount.DISTANCE));
        calories = cursor.getFloat(cursor.getColumnIndex(eCareSettings.StepCount.CALORIES));
        datetime = cursor.getLong(cursor.getColumnIndex(eCareSettings.StepCount.DATETIME));
        desc = cursor.getString(cursor.getColumnIndex(eCareSettings.StepCount.DESC));
    }

    public long getDayStepCount() {
        return dayStepCount;
    }

    public void setDayStepCount(long dayStepCount) {
        this.dayStepCount = dayStepCount;
    }

    public long getHourStepCount() {
        return hourStepCount;
    }

    public void setHourStepCount(long hourStepCount) {
        this.hourStepCount = hourStepCount;
    }

    public long getStepCount() {
        return stepCount;
    }

    public void setStepCount(long stepCount) {
        this.stepCount = stepCount;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Cursor getCursor(String projection[]) {
        MatrixCursor ret = new MatrixCursor(projection);
        Object[] values = new Object[projection.length];
        for (int i = 0, count = projection.length; i < count; i++) {
            String column = projection[i];
            if (eCareSettings.StepCount.CALORIES.equalsIgnoreCase(column)) {
                values[i] = calories;
            } else if (eCareSettings.StepCount.COUNT.equalsIgnoreCase(column)) {
                values[i] = stepCount;
            } else if (eCareSettings.StepCount.DATETIME.equalsIgnoreCase(column)) {
                values[i] = datetime;
            } else if (eCareSettings.StepCount.DISTANCE.equalsIgnoreCase(column)) {
                values[i] = distance;
            } else if (eCareSettings.StepCount._ID.equalsIgnoreCase(column)) {
                values[i] = id;
            } else if(eCareSettings.StepCount.DAYSTEP.equalsIgnoreCase(column)) {
                values[i] = dayStepCount;
            } else if(eCareSettings.StepCount.HOURSTEP.equalsIgnoreCase(column)) {
                values[i] = hourStepCount;
            } else if(eCareSettings.StepCount.DESC.equalsIgnoreCase(column)) {
                values[i] = "";
            } else if(eCareSettings.StepCount.USER.equalsIgnoreCase(column)) {
                values[i] = "";
            }
        }
        ret.addRow(values);
        return ret;
    }

    @Override
    public String toString() {
        return "StepCountData{" +
                "id=" + id +
                ", stepCount=" + stepCount +
                ", dayStepCount=" + dayStepCount +
                ", hourStepCount=" + hourStepCount +
                ", distance=" + distance +
                ", calories=" + calories +
                ", datetime=" + datetime +
                '}';
    }
}
