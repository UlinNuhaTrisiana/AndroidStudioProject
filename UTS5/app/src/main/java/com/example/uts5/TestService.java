package com.example.uts5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TestService extends Service {
    Timer timer;
    TimerTask TimerTask;

    public TestService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Test Service","Service diciptakan" );
        mulaiTimer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Test Service","Service dimulai" );
        Log.d("Test Service","Selesai" );
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Test Service","Service dimusnahkan" );
        hentikanTimer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("Test Service","Service bind" );
        return null;
    }

    private void mulaiTimer(){
        TimerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d("Test Service", "Timer Task dijalankan");
            }
        };
        timer = new Timer();
        timer.schedule(TimerTask, 5000, 10000);
    }

    private void hentikanTimer(){
        timer.cancel();
    }
}