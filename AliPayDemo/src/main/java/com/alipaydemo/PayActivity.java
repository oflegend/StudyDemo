package com.alipaydemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {
    // 支付宝支付业务：入参app_id
    public static final String APPID = "2017011705156206";
    // 商户私钥，pkcs8格式
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDuV85RW5j9saMf+H3kANZdCZR8ELtGhiNGwJ3DrpQ2TJ/+NKdYqSbz7/MZh40Dax2T5Or5+9vXH+ovLc9cd7bcnNtd7DoBu7WkmT2SI7AJTpKCR9EwcLAPvu/8VYuQ0dQv3O0bNfC8rqBwdRe2sVw4XWiFkbCwdj4Lzxt0ZpN99+ycv2/tcnLQl4Ivx5EdFGOgTqxJVADZ5jf/K0KRycoosbLpPTt/FcKoiF5Ah2CO1DbrrzuzCLIHDgZaA++R5rgoHlOpeiko4zoVxo8DOkrfsBQbCKSk9Yp0g82TX5xStQyiMVngSpPx24fH4fQZhZGl45D3Uh1wUh9GYPcWzu/BAgMBAAECggEAU1njrjIRS88+k+Sr6fuc+8Zo5gZX5+7rEUg1JAS+kkqzvQue5nOpSq/EXU45M1PXcSwiPLAmjpH7rOohVV4MVhSI5szA8HA7ssdgb+YJHwgaVnNVJdKSz77fYGhuOYQX53EkflqbyPBs7UcVtu9Jp/CKqANuPhfr1Odl24wv0mIf0uA0+tXpDHuK5Gcp4QVcELp7tySXDWNCzporQ9ytmOtqICYhNAHFVgv1hbSP8GLXQlr39krOiZyNn7ZrUn2Seot/upyYlbvg4VCfzUkJCcB7JZmRqciDj1/4XRmQKUMEPy3Vh2bGZyp5v0lkOOhyzUEnu7lsziOxWc4PSn5IQQKBgQD7N4MTjIjiGmpOad+s86JiZxRsp+Hpyj4LC49fIaIqM4vmgmT7t2Y/fn7/95Jmhp5zLFuu/yjsmy79syh0M5uSSeCZ2l0Y/8wk91vK0qdn2Pp3H/65x9MsLwRWkQmrXkCGAmo9ZPocHVPnG4IERyBCVzBaQFwcLKk3SYVXPJNbmQKBgQDy4Ys4lxNold3y0krf53Z7nvSn6nKoVeC2/Tn0DvHkokSPOtJhjk6IVn0uakOmPK1JACRakd1fH7nTHLKtp9kBHkcVQTWIrby5LaLFMi8dZAE2GyqKmUl9+9PMsRC1Q/4+VMh0c8KaXidmqQLXnRYKidgvkUACiuJEx9dyI7wOaQKBgEHDMmj3rQ2jZJjKVmdsD+I6obof6J/O9Zbmm82HfHAXtlPNZqoORJHyh0RyhryYGPqFbirNWOU3aoG3WKuyptMQy976rsxQihxTNJBPvMsOneJ5WbFAgiNa24Pdy+40aieqki0oNeEOPFyPcuWzyNoVAO9fUb1+Y325nVJM5TZhAoGAPgvMKwq9FIykhHGgWw6gP/hde7/yxnoZy+VotqZSuRuS9BwMvotCGKYvmHnhCkiZ4/o7Tz/QE4rhR8MfCinEz/pSImXkLSRk/9FesY2eZ52yiYXrGjkKgzqf4ryhDg1ul7HE+6rO3eyLmDg416fzh4GrTouGlJ+yKev7Z9clJXECgYA8/ECqV5WwURkhr23wKqQASa6tc3Pwz6IKzcqDkup2wv0ZVI44rMaYjnuzoedRxY1U21BCb7IwhHG2EP2Qy9IOFd7wtVO4MVuh34wlzci2Q4edTH7Alf8t8qtj53SuFbzlqr1NJCkDsTpQit+4OfQI2KhtL8E3i5Ifie2pjqRlxw==";

    private LinearLayout alipay, wechatpay;
    private Button pay;
    private TextView tv_no, tv_price;
    private ImageView ali, wechat;
    private String no = "201430330220", price = "0.01";
    private int payWay = 0;//1支付宝，2微信


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_main);


        initView();
        initData();
    }

    private void initView() {
        pay = (Button) findViewById(R.id.pay);
        pay.setOnClickListener(this);
        alipay = (LinearLayout) findViewById(R.id.alipay);
        alipay.setOnClickListener(this);
        wechatpay = (LinearLayout) findViewById(R.id.wechatpay);
        wechatpay.setOnClickListener(this);
        ali = (ImageView) findViewById(R.id.ali);
        wechat = (ImageView) findViewById(R.id.wechat);
        tv_no = (TextView) findViewById(R.id.tv_no);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_no.setText(no);
        tv_price.setText(price + " 元");
    }

    private void initData() {
        /**
         * 代码修改处
         */
        payWay = 1;
        ali.setImageResource(R.drawable.sel_check);
    }


    @Override
    public void onClick(View view) {
        if (view == alipay) {
            payWay = 1;
            ali.setImageResource(R.drawable.sel_check);
            wechat.setImageResource(R.drawable.sel_nor);
        }
        if (view == wechatpay) {
            payWay = 2;
            ali.setImageResource(R.drawable.sel_nor);
            wechat.setImageResource(R.drawable.sel_check);
        }

        if (view == pay) {

            /**
             * 支付宝支付业务
             *
             * @param v
             */
            if (payWay == 1) {
                boolean rsa2 = (RSA2_PRIVATE.length() > 0);
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, no, price);
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

                String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
                String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
                final String orderInfo = orderParam + "&" + sign;

                Runnable payRunnable = new Runnable() {


                    public void run() {
                        PayTask alipay = new PayTask(PayActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
            if (payWay == 2) {
                Toast.makeText(this, "微信支付暂未开通！", Toast.LENGTH_SHORT);
            }
        }

    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);

                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


}
