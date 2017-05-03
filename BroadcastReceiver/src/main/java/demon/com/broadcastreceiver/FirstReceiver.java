package demon.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by D&LL on 2017/5/2.
 * 第一条广播
 */

public class FirstReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "FirstReceiver" + intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("first", "The First Message!");
        setResultExtras(bundle);
        //abortBroadcast(); //取消广播继续传播
    }
}