package com.dyl.chatbot;

import java.util.ArrayList;
import java.util.List;

import com.dyl.chatbot.adapter.FriendListAdapter;
import com.dyl.chatbot.vo.User;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ChatListActivity extends Activity{

    private FriendListAdapter mFriendListAdapter;
    private ListView lvFriends;
    private List<User> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat_list);
        
        initFriendsData();
        
        mFriendListAdapter = new FriendListAdapter(this, datas);
        lvFriends = (ListView) findViewById(R.id.lv_friend);
        lvFriends.setAdapter(mFriendListAdapter);
        lvFriends.setOnItemClickListener(new MyOnItemClickListener());
    }
    
    /**
     * 从服务器查询当前用户的好友数据
     */
    private void initFriendsData() {
        datas = new ArrayList<>();
        User user0 = new User();
        user0.setName("图灵机器人");
        user0.setAge((byte)101);
        user0.setHeadIcon(BitmapFactory.decodeResource(getResources(), R.drawable.tuling));
        user0.setSex((byte)0);
        datas.add(user0);
        for(int i=0;i<10; i++){
            User user = new User();
            user.setName("朋友"+i);
            user.setLastMessage("昨天说的今天说的明天说的都是话");
            datas.add(user);
        }
    }
    
    private class MyOnItemClickListener implements OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (((User)mFriendListAdapter.getItem(position)).getName().equals("图灵机器人")) {
                Intent intent = new Intent(ChatListActivity.this,ChatContentActivity.class);
                ChatListActivity.this.startActivity(intent);
            }
        }
        
    }

    /**
     * 打开个人中心
     * @param view
     */
    public void openPersonal(View view){
        
    }
}
