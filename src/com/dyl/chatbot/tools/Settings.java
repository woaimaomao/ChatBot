package com.dyl.chatbot.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dyl.chatbot.vo.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.format.DateFormat;

/**
 * 保存用户设置
 * @author Bill-Gates
 *
 */
public class Settings {

    public static final String ENTER_TO_SEND = "enter_to_send";
    public static final String GLOBAL_SETTING = "global_settings";
    public static final String USER_STATUS = "user_status";
    private static final String USER_INFO = "user_info";
    private static Settings instance = null;
    private static SharedPreferences mSettingsPreference;
    private static SharedPreferences mUserPreference;
    private Context mContext ;
    
    private Settings(Context context){
        mContext = context;
    }
    
    public static Settings getInstance(Context context) {
        if(instance == null){
            instance = new Settings(context);
        }
        return instance;
    }
    
    /**
     * 保存用户设置
     * @param settingName
     * @param value
     */
    public void saveSetting(String settingName,Object value){
        if(mSettingsPreference == null)
            mSettingsPreference = mContext.getSharedPreferences(GLOBAL_SETTING,Context.MODE_PRIVATE);
        Editor editor = mSettingsPreference.edit();
        editor.putString(settingName, String.valueOf(value));
        editor.commit();
        
    }
    
    /**
     * 保存用户登录状态信息
     * @param loginTime
     * @param user
     */
    public void saveUserStatus(User user){
        if(mUserPreference == null)
            mUserPreference = mContext.getSharedPreferences(USER_STATUS, Context.MODE_PRIVATE);
        Editor editor = mUserPreference.edit();
        Set<String> userInfos = new HashSet<>();
        userInfos.add(user.getName());
        
        userInfos.add(user.getLastLoginTime());
        // ...
        editor.putStringSet(USER_INFO, userInfos);
        editor.commit();
        
    }
    
    /**
     * 根据属性名获取设置值
     * @param settingName
     * @return
     */
    public String getSetting(String settingName){
        if(mSettingsPreference == null)
            mSettingsPreference = mContext.getSharedPreferences(GLOBAL_SETTING,Context.MODE_PRIVATE);
        return mSettingsPreference.getString(settingName, "true");
    }
    
    public Set<String> getUserStatus(){
        if(mUserPreference == null)
            mUserPreference = mContext.getSharedPreferences(USER_STATUS, Context.MODE_PRIVATE);
        return mUserPreference.getStringSet(USER_INFO, null);
    }
    
}
