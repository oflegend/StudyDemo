package demon.com.bindservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bind)
    Button bind;
    @BindView(R.id.unbind)
    Button unbind;
    @BindView(R.id.getService)
    Button getService;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.myService)
    Button myService;
    @BindView(R.id.intentService)
    Button intentService;
    private String TAG = "MainActivity";
    private BindService.MyBinder binder;
    private Intent intent;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "Service Connected!");
            binder = (BindService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "Service DisConnected!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        intent = new Intent(this, BindService.class);


    }

    @OnClick({R.id.bind, R.id.unbind, R.id.getService, R.id.start, R.id.stop,R.id.myService, R.id.intentService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bind:
                bindService(intent, connection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(connection);
                break;
            case R.id.getService:
                count.setText(binder.getCount() + "");
                break;
            case R.id.start:
                startService(intent);
                break;
            case R.id.stop:
                stopService(intent);
                break;
            case R.id.myService:
                Intent intent1 = new Intent(this,MyService.class);
                startService(intent1);
                break;
            case R.id.intentService:
                Intent intent2 = new Intent(this,MyIntentService.class);
                startService(intent2);
                break;
        }
    }


}
