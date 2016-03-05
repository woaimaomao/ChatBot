package com.dyl.chatbot;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dyl.chatbot.vo.User;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class LoginActivity extends Activity {
    private EditText mEtUsername;
    private EditText mEtPassword;
    private TextView mTvLoginTips;
    private Button mBtLogin;
    private LoginHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mHandler = new LoginHandler(this);
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mTvLoginTips = (TextView) findViewById(R.id.tv_login_tips);
        mBtLogin = (Button) findViewById(R.id.bt_login);
    }

    /**
     * 点击登录按钮事件
     * @param view
     */
    public void login(View view) {
        
        
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        if(username != null && !"".equals(username) && password != null && !"".equals(password)){
            setLoginUIStatus();
        // 测试：start
        try {
            Thread.sleep(3000);
            User user = new User();
            user.setName(username);
            user.setPassword(password);
            user.setLastLoginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            ChatApplication app = (ChatApplication) getApplication();
            app.saveLoginUser(user);
            mHandler.obtainMessage(LoginHandler.LOGIN_OK).sendToTarget();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 测试：end
        
        
        /*
        String url = "username="+username+"&password="+password;
        
        StringRequest stringRequest = new StringRequest(Method.POST, url, new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                
            }
            
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                
            }
            
        });*/
        }
    }

    /**
     * 设置当点击登录按钮后界面状态
     */
    private void setLoginUIStatus() {
        mTvLoginTips.setText("");
        mEtPassword.setEnabled(false);
        mEtUsername.setEnabled(false);
        mBtLogin.setEnabled(false);
        mBtLogin.setTextColor(Color.parseColor("#c4c4c4"));
        mBtLogin.setBackgroundColor(Color.WHITE);
        mBtLogin.setText("正在登录...");
    }
    
    private class LoginHandler extends Handler{
        public static final int LOGIN_OK = 0x200;
        public static final int LOGIN_FAIL = 0x400;
        private Context mContext ;
        public LoginHandler(Context context) {
            mContext = context;
        }
        
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            
            if(msg.what == LOGIN_OK){
                loginSuccess("豆豆", "123456");
            }else if(msg.what == LOGIN_FAIL){
                loginFail();
            }
        }

    }
    
    /**
     * 处理登录成功
     * @param uname
     * @param pwd
     */
    private void loginSuccess(String uname,String pwd) {
        User user = new User();
        user.setName(uname);
        user.setPassword(pwd);
        user.setLastLoginTime(String.valueOf(System.currentTimeMillis()));
        ChatApplication app = (ChatApplication) getApplication();
        app.saveLoginUser(user);
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        finish();
    }

    /**
     * 处理登录失败
     */
    private void loginFail() {
        mEtUsername.setText("");
        mEtPassword.setText("");
        mTvLoginTips.setText("");
        mEtPassword.setEnabled(true);
        mEtUsername.setEnabled(true);
        mBtLogin.setEnabled(true);
    }

    

}
