package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tv_version;
    private LinearLayout LinearLayout01;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_version = (TextView) this.findViewById(R.id.tv_version);
        tv_version.setText("版本:"+getVersion());

        LinearLayout01 =(LinearLayout) this.findViewById(R.id.LinearLayout01);
        AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
        aa.setDuration(2000);
        LinearLayout01.startAnimation(aa);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(isNetworkConnected()){
            Log.i(TAG,"进入主界面");
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Intent intent = new Intent(MainActivity.this,Splash_Activity.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

        }else{
            //弹出对话框 让用户设置网络
            AlertDialog.Builder builder = new Builder(this);
            builder.setTitle("设置网络");
            builder.setMessage("网络错误请设置网络");
            builder.setPositiveButton("设置网络", new OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if(android.os.Build.VERSION.SDK_INT>10){
                        intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    }else{
                        intent = new Intent();
                        ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                        intent.setComponent(component);
                        intent.setAction("android.intent.action.VIEW");
                    }
                    startActivity(intent);

                }
            });
            builder.setNegativeButton("取消", new OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.create().show();
        }
    }

    private String getVersion() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
            return info.versionName;
        } catch (NameNotFoundException e) {

            e.printStackTrace();
            return  "";
            //不会发生的
        }
    }
    /**
     * 判断系统的网络是否可用
     * @return
     */
    private boolean isNetworkConnected(){
        ConnectivityManager cm =	(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo info =cm.getActiveNetworkInfo();
        if(info!=null&&info.isConnected()){
            return true;
        }else {
            return false ;
        }
//		return (info!=null&&info.isConnected());

    }

}
