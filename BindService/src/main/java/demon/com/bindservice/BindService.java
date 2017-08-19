package demon.com.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by D&LL on 2017/5/2.
 */

public class BindService extends Service {
    private int count = 0;
    private boolean quit;
    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        public int getCount() {
            return count;
        }
    }

    private String TAG = "BindService";

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Service is Bind!");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service is Created!");

        class MyThread extends Thread {
            @Override
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }
        MyThread myThread = new MyThread();
        myThread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service is Started!");
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "Service is Unbinded!");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        quit = true;
        Log.i(TAG, "Service is Destroyed!");
    }
}
