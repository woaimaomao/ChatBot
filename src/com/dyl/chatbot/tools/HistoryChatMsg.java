package com.dyl.chatbot.tools;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import com.dyl.chatbot.vo.MsgBean;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 操作聊天记录
 * @author Bill-Gates
 *
 */
public class HistoryChatMsg {

    private static final int MAX_RECORD = 10;
    private static final String CHAT_HISTORY = "chat_history";
    
    private LinkedList<MsgBean> history ;
    private static HistoryChatMsg instance = null;
    
    private HistoryChatMsg(){
        history = new LinkedList<MsgBean>(); 
    }
    
    public HistoryChatMsg getInstance(){
        if(instance == null){
            instance = new HistoryChatMsg();
        }
        return instance;
    }
    
    public void addChat(MsgBean msg){
        if(history.size()<MAX_RECORD){
            history.addLast(msg);
        }else{
            history.removeFirst();
            history.addLast(msg);
        }
    }
    
    public void saveChatToXML(Context context){
        SharedPreferences sp = null;
        Editor editor = null;
        if(history != null && history.size()>0){
            sp = context.getSharedPreferences(CHAT_HISTORY, Context.MODE_PRIVATE);
            editor = sp.edit();
            for (Iterator<MsgBean> iterator = history.iterator(); iterator.hasNext();) {
                Set<String> set = new HashSet<>();
                MsgBean msgBean = (MsgBean) iterator.next();
                set.add(msgBean.getMsg());
                set.add(String.valueOf(msgBean.getFromUser()));
                set.add(String.valueOf(msgBean.getMsg_type()));
                set.add(String.valueOf(msgBean.getToUser()));
                set.add(String.valueOf(msgBean.getPic_res()));
                set.add(String.valueOf(msgBean.getSendTime()));
                editor.putStringSet(String.valueOf(msgBean.getFromUser()), set);
            }
            editor.commit();
        }
    }
    
}
