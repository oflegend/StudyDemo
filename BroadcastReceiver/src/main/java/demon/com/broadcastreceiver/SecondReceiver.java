package demon.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 接收第一条广播的数据
 */
public class SecondReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = getResultExtras(true);
        String first = bundle.getString("first");
        Toast.makeText(context, "第一条存入的消息为:" + first, Toast.LENGTH_LONG).show();
    }
}
