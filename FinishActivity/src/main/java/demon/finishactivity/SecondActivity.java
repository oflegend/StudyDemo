package demon.finishactivity;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //System.exit(0);
                //android.os.Process.killProcess(android.os.Process.myPid());
               // SecondActivity.this.onDestroy();
                //ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                //manager.restartPackage(getPackageName());
            }
        });
    }
}
