package demon.com.bindservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by D&LL on 2017/5/2.
 */

public class MyIntentService extends IntentService {
    private String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "MyIntentService is onStart!");
        long endTime = System.currentTimeMillis() + 20 * 1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i(TAG, "耗时任务完成！");
    }
}
