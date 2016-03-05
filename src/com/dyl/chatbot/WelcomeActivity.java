package com.dyl.chatbot;

import java.util.Timer;
import java.util.TimerTask;

import com.dyl.chatbot.tools.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WelcomeActivity extends Activity{

    protected static final int STATUS_OK = 0x200;
    protected static final int STATUS_LOGIN = 0x201;
    private MyHandler mHandler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mHandler = new MyHandler(this);
        new Timer().schedule(new TimerTask(){

            @Override
            public void run() {

                // 将初始时需要做的工作放在此处，例如检查登录状态等
                if (isUserHasLogin()) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.obtainMessage(STATUS_OK).sendToTarget();
                } else {
                    mHandler.obtainMessage(STATUS_LOGIN).sendToTarget();
                }
            }

            /**
             * 判断用户是否已登录过
             * @return
             */
            private boolean isUserHasLogin() {
                if(Settings.getInstance(getApplicationContext()).getUserStatus()!=null){
                    return true;
                }
                return false;
            }
            
        }, 0);
    }
    
    private static class MyHandler extends Handler {
        
        private Context mContext;
        
        public MyHandler(Context context) {
            mContext = context;
        }
        
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
            case STATUS_OK:
                Intent intent = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent);
                break;
            case STATUS_LOGIN:
                Intent intent0 = new Intent(mContext,LoginActivity.class);
                mContext.startActivity(intent0);
                break;
            default:
                break;
            }
            ((WelcomeActivity)mContext).finish();
        }
    }
}
