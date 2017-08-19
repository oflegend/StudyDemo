package demon.com.handlerdemo;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D&LL on 2017/5/16.
 */

public class PrimeNum {
    public static void primeNum(final Handler handler, final int n) {
        class myThread extends Thread {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Integer> num = new ArrayList<>();
                outer:
                for (int i = 2; i <= n; i++) {
                    for (int j = 2; j <= Math.sqrt(i); j++) {
                        if (i != 2 && i % j == 0) {
                            continue outer;
                        }
                    }
                    num.add(i);
                }
                Message msg = new Message();
                msg.what = 0x123;
                msg.obj = num.toString();
                handler.sendMessage(msg);
            }
        }
        new myThread().start();
    }
}
