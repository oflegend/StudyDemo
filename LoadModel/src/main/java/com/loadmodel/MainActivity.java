package com.loadmodel;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.text)
    TextView text;
    private String TAG = "---MainActivity---";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        text.setText("MainActivity");
        Log.i(TAG, "TaskID:" + this.getTaskId() + " hashCode:" + this.hashCode());

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        //Intent intent = new Intent(this, MainActivity.class);
        //Intent intent = new Intent(this, OtherActivity.class);
       /* Intent intent = getPackageManager().getLaunchIntentForPackage("com.activitylifecycledemo");
        if (intent == null) {
            Toast.makeText(this, "程序不存在", Toast.LENGTH_LONG).show();
        } else {
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setAction(Intent.ACTION_MAIN);
            startActivity(intent);
        }*/
        Intent intent = new Intent();
        //intent.setAction(Intent.ACTION_MAIN);
        //intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName("com.activitylifecycledemo", "com.activitylifecycledemo.SecondActivity");
        intent.setComponent(cn);
        startActivity(intent);

   /*     PackageInfo pi = null;
        try {
            pi = getPackageManager().getPackageInfo("com.activitylifecycledemo", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(pi.packageName);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> apps = pm.queryIntentActivities(resolveIntent, 0);

        ResolveInfo ri = apps.iterator().next();
        if (ri != null) {
            String packageName = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;//获取主Activity名
            System.out.println(packageName + "\n" + className);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            startActivity(intent);
        }*/
    }
}
