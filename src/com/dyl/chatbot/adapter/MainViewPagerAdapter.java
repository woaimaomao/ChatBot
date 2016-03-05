package com.dyl.chatbot.adapter;

import java.util.ArrayList;
import java.util.List;

import com.dyl.chatbot.fragment.ChatListFragment;
import com.dyl.chatbot.fragment.FriendListFragment;
import com.dyl.chatbot.fragment.PersonalCenterFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    
    public MainViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        mFragments = list;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public android.support.v4.app.Fragment getItem(int arg0) {
        return mFragments.get(arg0);
    }
    
    @Override
    public void finishUpdate(View container) {
        // TODO Auto-generated method stub
        super.finishUpdate(container);
    }

}
