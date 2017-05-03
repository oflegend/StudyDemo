package demon.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * 监听网络连接状态的广播
 */
public class NetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (!gprs.isConnected() && !wifi.isConnected()) {
                Toast.makeText(context, "无网络连接", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "网络已连接", Toast.LENGTH_LONG).show();
            }
        }
    }
}
