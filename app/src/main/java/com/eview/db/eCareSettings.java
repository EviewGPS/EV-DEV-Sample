package com.eview.db;

import android.net.Uri;

import com.eview.ecare.eCareShareDataProvider;

public class eCareSettings {
    /**
     * StepCount.
     */
    public static final class StepCount  {
        public static final Uri CONTENT_URI = Uri.parse("content://" +
                eCareProvider.AUTHORITY + "/" + eCareProvider.TABLE_STEPCOUNT );
        public static final Uri CONTENT_SHARE_DATA_URI = Uri.parse("content://" +
                eCareShareDataProvider.AUTHORITY + "/" + eCareProvider.TABLE_STEPCOUNT );
        public static final Uri CONTENT_SHARE_DATA_URI_QUERY = Uri.parse("content://" +
                eCareShareDataProvider.AUTHORITY + "/" + eCareProvider.TABLE_STEPCOUNT + "Q" );

        public static final String _ID = "_id";
        public static final String STATE = "state";
        public static final String RESENDTIMES = "resendTimes";
        public static final String COUNT = "stepcount";
        public static final String HOURSTEP = "hourstep";
        public static final String DAYSTEP = "daystep";
        public static final String DISTANCE = "distance";
        public static final String CALORIES = "calories";
        public static final String DATETIME = "datetime";
        public static final String USER = "user";
        public static final String DESC = "discrible";

        public static final String[] STEPCOUNT_QUERY_COLUMNS = { _ID, STATE, RESENDTIMES, COUNT, HOURSTEP,
                DAYSTEP, DISTANCE, CALORIES, DATETIME, USER, DESC};
    }

    /**
     * WeatherData.
     */
    public static final class WeatherData  {
        public static final Uri CONTENT_URI = Uri.parse("content://" +
                eCareProvider.AUTHORITY + "/" + eCareProvider.TABLE_WEATHERDATA );
        public static final Uri CONTENT_SHARE_DATA_URI = Uri.parse("content://" +
                eCareShareDataProvider.AUTHORITY + "/" + eCareProvider.TABLE_WEATHERDATA );
        public static final Uri CONTENT_SHARE_DATA_URI_QUERY = Uri.parse("content://" +
                eCareShareDataProvider.AUTHORITY + "/" + eCareProvider.TABLE_WEATHERDATA + "Q" );

        public static final String _ID = "_id";
        public static final String WEATHERCODE = "weatherCode";
        public static final String WEATHERCODE_NIGHT = "weatherCode_n";
        public static final String TEMPTURE = "tempture";
        public static final String TEMPTURE_LOW = "tempture_l";
        public static final String WINDDIR = "wind_dir";
        public static final String WINDSRC = "wind_src";
        public static final String HUM = "hum";
        public static final String UVINDEX = "uv_index";
        public static final String DATETIME = "datetime";
        public static final String CITY = "city";
        public static final String UPDATE_DATETIME = "update_datetime";
        public static final String LAT = "lat";
        public static final String LNG = "lng";
        public static final String DESC = "discrible";

        public static final String[] WEATHERDATA_QUERY_COLUMNS = {_ID, WEATHERCODE, WEATHERCODE_NIGHT, TEMPTURE, TEMPTURE_LOW, WINDDIR, WINDSRC,
                HUM, UVINDEX, DATETIME, CITY, UPDATE_DATETIME, LAT, LNG, DESC};
    }
    public static final class PersonalData {
        public static final Uri CONTENT_URI = Uri.parse("content://" +
                eCareProvider.AUTHORITY + "/" + eCareProvider.TABLE_PERSONALDATA);
        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String BIRTHDAY = "birthday";
        public static final String HEIGHT = "height";
        public static final String WEIGHT = "weight";
        public static final String URL = "url";
        public static final String EMERGENCY_CALL = "emergencyCall";//contact
        public static final String EMERGENCY_NAME = "emergencyName";//contact
        public static final String HEAD_URL = "headURL";//head url
        public static final String BLOOD_TYPE = "bloodType";//blood type
        public static final String DESC = "discrible";
        public static final String[] PERSONALDATA_QUERY_COLUMNS = {_ID, NAME, BIRTHDAY, HEIGHT, WEIGHT, URL, EMERGENCY_CALL, EMERGENCY_NAME, HEAD_URL, BLOOD_TYPE, DESC};
    }
}
