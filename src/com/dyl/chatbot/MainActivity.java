package com.dyl.chatbot;

import java.util.ArrayList;
import java.util.List;

import com.dyl.chatbot.adapter.MainViewPagerAdapter;
import com.dyl.chatbot.fragment.ChatListFragment;
import com.dyl.chatbot.fragment.FriendListFragment;
import com.dyl.chatbot.fragment.PersonalCenterFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    protected static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment>mFragments = new ArrayList<>();
        mFragments.add(0, new ChatListFragment());
        mFragments.add(1, new FriendListFragment());
        mFragments.add(2, new PersonalCenterFragment());
        mViewPager = (ViewPager) findViewById(R.id.vp_main_frame);
        mViewPager.setCurrentItem(0);
        mViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(),mFragments));
    }
    

}
