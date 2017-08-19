package demon.com.bindservice;

import android.content.Context;

/**
 * Created by D&LL on 2017/5/2.
 */

public class TimeUtil {
    public TimeUtil() {

    }

    public void wasteTime(Context context) {
        long nowTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis() + 20 * 1000;
        while (nowTime < endTime) {
            synchronized (context) {
                try {
                    wait(endTime - nowTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
