package com.sample.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eview.PhoneSystem;
import com.eview.sample.AlertSample;
import com.eview.sample.AlertSettings;
import com.eview.sample.ConnectionTrigger;
import com.eview.sample.GestureSample;
import com.eview.sample.HrsSample;
import com.eview.sample.KeySample;
import com.eview.sample.LedSample;
import com.eview.sample.PedoSample;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";

    public BroadcastReceiver mBroadcastEventReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(KeySample.process(intent)) {
                return;
            }
            if(AlertSample.process(intent)) {
                return;
            }
            if(hrsSample.process(intent)) {
                return;
            }
        }
    };
    private PedoSample pedoSample = new PedoSample();
    private HrsSample hrsSample = new HrsSample();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        KeySample.addActionFilter(intentFilter);
        AlertSample.addActionFilter(intentFilter);
        hrsSample.addActionFilter(intentFilter);
        registerReceiver(mBroadcastEventReceiver, intentFilter);
        Button button = (Button)findViewById(R.id.shutdown);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneSystem.shutdown(MainActivity.this);
            }
        });
        button = (Button)findViewById(R.id.trigger_connect);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionTrigger.triggerConnect(MainActivity.this);
            }
        });
        button = (Button)findViewById(R.id.start_hrs);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrsSample.start(MainActivity.this, (TextView)findViewById(R.id.txt_hrs));
            }
        });
        button = (Button)findViewById(R.id.stop_hrs);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrsSample.stop();
            }
        });
        button = (Button)findViewById(R.id.enable_gesture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureSample.setGesture(MainActivity.this, true);
            }
        });
        button = (Button)findViewById(R.id.disable_gesture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureSample.setGesture(MainActivity.this, false);
            }
        });
        button = (Button)findViewById(R.id.set_falldown);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertSettings.setFalldown(true, true, 6);
            }
        });
        button = (Button)findViewById(R.id.query_falldown);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertSettings.queryFalldownSettings();
            }
        });

        button = (Button)findViewById(R.id.set_tilt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertSettings.setTilt(true, true, 30, 30);
            }
        });
        button = (Button)findViewById(R.id.query_tilt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertSettings.queryTiltSettings();
            }
        });

        button = (Button)findViewById(R.id.set_motion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertSettings.setMotion(true, true, 4, 45);
            }
        });
        button = (Button)findViewById(R.id.query_motion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertSettings.queryMotionSettings();
            }
        });

        button = (Button)findViewById(R.id.set_nomotion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertSettings.setNoMotion(true, true, 60);
            }
        });
        button = (Button)findViewById(R.id.query_nomotion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertSettings.queryNoMotionSettings();
            }
        });

        button = (Button)findViewById(R.id.set_off_ecare_led);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LedSample.setECareControl(false);
            }
        });
        button = (Button)findViewById(R.id.set_led_status);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LedSample.setLed(LedSample.LED_STATE_ON,LedSample.LED_STATE_ON,LedSample.LED_STATE_ON,LedSample.LED_BR_STATE_BR_DD);
            }
        });

        pedoSample.start(this, (TextView)findViewById(R.id.txt_step_count));
        pedoSample.query();
    }

    @Override
    protected void onDestroy() {
        pedoSample.stop();
        hrsSample.stop();
        try {
            unregisterReceiver(mBroadcastEventReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

}
