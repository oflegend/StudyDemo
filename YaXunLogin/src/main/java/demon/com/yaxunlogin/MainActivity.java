package demon.com.yaxunlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.btn)
    Button btn;
    private String account1 = "18819269394";
    private String account2 = "18819269418";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btn1.setText(account1);
        btn2.setText(account2);

    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Bundle data1 = new Bundle();
                data1.putString("account", account1);
                Intent intent1 = getPackageManager().getLaunchIntentForPackage("com.example.materialscancode");//包名
                if (intent1 == null) {
                    Toast.makeText(this, "程序不存在！", Toast.LENGTH_LONG).show();
                } else {
                    intent1.putExtras(data1);
                    startActivity(intent1);
                }
                break;
            case R.id.btn2:
                Bundle data2 = new Bundle();
                data2.putString("account", account2);
                Intent intent2 = getPackageManager().getLaunchIntentForPackage("com.example.materialscancode");//包名
                if (intent2 == null) {
                    Toast.makeText(this, "程序不存在！", Toast.LENGTH_LONG).show();
                } else {
                    intent2.putExtras(data2);
                    startActivity(intent2);
                }
                break;
        }
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        Bundle data1 = new Bundle();
        data1.putString("account", edit.getText().toString());
        Intent intent1 = getPackageManager().getLaunchIntentForPackage("com.example.materialscancode");//包名
        if (intent1 == null) {
            Toast.makeText(this, "程序不存在！", Toast.LENGTH_LONG).show();
        } else {
            intent1.putExtras(data1);
            startActivity(intent1);
        }
    }
}
