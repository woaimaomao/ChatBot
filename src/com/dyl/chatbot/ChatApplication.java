package com.dyl.chatbot;

import com.dyl.chatbot.tools.Settings;
import com.dyl.chatbot.vo.User;

import android.app.Application;

public class ChatApplication extends Application{

    private User loginUser;
    
    @Override
    public void onCreate() {
        super.onCreate();
        loginUser = new User();
    }
    
    /**
     * 保存登录成功的当前用户
     * @param user
     * @returnqtmaqt
     */
    public User saveLoginUser(User user){
        loginUser = user;
        Settings.getInstance(getApplicationContext()).saveUserStatus(loginUser);
        return loginUser;
    }
    
    /**
     * 注销当前用户
     * @return
     */
    public User logoutUser(){
        if(loginUser != null){
            loginUser = null;
        }
        return loginUser;
    }
    
    public User getLoginUser(){
        return loginUser;
    }
}
