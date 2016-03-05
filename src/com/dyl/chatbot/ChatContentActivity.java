package com.dyl.chatbot;

import java.lang.ref.WeakReference;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.dyl.chatbot.adapter.ChatListAdapter;
import com.dyl.chatbot.tools.SendMessage;
import com.dyl.chatbot.tools.Settings;
import com.dyl.chatbot.vo.MsgBean;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ChatContentActivity extends Activity {

    protected static final String TAG = "MainActivity";
    private ListView mListView;
    private EditText mEditText;
    private Button sendButton;
    private MyHandler mHandler;
    private ChatListAdapter mChatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_content);
        initViews();
        mHandler = new MyHandler(this);
        mChatListAdapter = new ChatListAdapter(this, mHandler.getMsgList());
        mListView.setAdapter(mChatListAdapter);
        initEvents();

    }

    private void initEvents() {

        mListView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return false;
            }

        });

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (mEditText.getText() != null && !"".equals(mEditText.getText().toString().trim())) {
                    setSendButtonEnable(true);
                } else {
                    setSendButtonEnable(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

    }

    /*
     * public void parentLayoutClick(View view){ Log.d(TAG, "需要隐藏输入法");
     * InputMethodManager imm = (InputMethodManager)
     * getSystemService(Context.INPUT_METHOD_SERVICE);
     * imm.hideSoftInputFromWindow(view.getWindowToken(), 0); }
     */

    /*
     * @Override public boolean dispatchTouchEvent(MotionEvent ev) { if
     * (ev.getAction() == MotionEvent.ACTION_DOWN) { View v = getCurrentFocus();
     * if (isShouldHideInput(v, ev)) {
     * 
     * InputMethodManager imm = (InputMethodManager)
     * getSystemService(Context.INPUT_METHOD_SERVICE); if (imm != null) {
     * imm.hideSoftInputFromWindow(v.getWindowToken(), 0); } } return
     * super.dispatchTouchEvent(ev); } // 必不可少，否则所有的组件都不会有TouchEvent了 if
     * (getWindow().superDispatchTouchEvent(ev)) { return true; } return
     * onTouchEvent(ev); }
     */

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 判断是否需要隐藏输入法
        if (shouldHideInputMethod(event)) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromInputMethod(mEditText.getWindowToken(), 0);
        }

        return super.onTouchEvent(event);
    }

    private boolean shouldHideInputMethod(MotionEvent event) {

        if (mEditText.getScrollY() > event.getX() + 10) {
            return true;
        }
        return false;
    }

    private void initViews() {
        mListView = (ListView) findViewById(R.id.chatList);
        mEditText = (EditText) findViewById(R.id.tv);
        sendButton = (Button) findViewById(R.id.sendButton);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            String enterToSend = Settings.getInstance(this.getApplicationContext()).getSetting(Settings.ENTER_TO_SEND);
            Log.d(TAG, "enterTosend= "+enterToSend);
            if (Boolean.valueOf(enterToSend)) {
                sendMsg();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

   
    private static class MyHandler extends Handler {
        private static final String ERROR_MESSAGE = "啊偶，好像出现一些问题了!";
        WeakReference<ChatContentActivity> mActivity;
        private List<MsgBean> msgList;

        public MyHandler(ChatContentActivity activity) {
            mActivity = new WeakReference<ChatContentActivity>(activity);
            msgList = new ArrayList<MsgBean>();
            MsgBean msgBean = new MsgBean(activity.getText(R.string.say_hello).toString(), R.drawable.tuling,
                    MsgBean.TYPE_RECV);
            msgList.add(msgBean);
        }

        public List<MsgBean> getMsgList() {
            return msgList;
        }

        @Override
        public void handleMessage(Message msg) {
            ChatContentActivity ac = mActivity.get();
            MsgBean msgBean = new MsgBean("", R.drawable.tuling, MsgBean.TYPE_RECV);
            switch (msg.what) {
            case SendMessage.RESPONES_OK: {
                Bundle bundle = msg.getData();
                String str = bundle.getString("msg");
                Log.v("client", str);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(str);
                    String ss = jsonObject.getString("text");
                    msgBean.setMsg(ss);
                } catch (JSONException e) {
                    msgBean.setMsg("啊偶，好像出现一些问题了!");
                    e.printStackTrace();
                }
                /*JSONObject jsonObject = (JSONObject) msg.obj;
                Log.d(TAG, "jsonObject=="+jsonObject);
                String ss;
                try {
                    ss = jsonObject.getString("text");
                    msgBean.setMsg(ss);
                } catch (JSONException e) {
                    e.printStackTrace();
                    msgBean.setMsg(ERROR_MESSAGE);
                }*/
            }
                break;
            default: {
                msgBean.setMsg(ERROR_MESSAGE);
            }
                break;
            }
            msgList.add(msgBean);
            ac.showMsg();
        }
    }

    private void showMsg() {
        mChatListAdapter.notifyDataSetChanged();
        mListView.setSelection(mHandler.getMsgList().size() - 1);
    }

    private void sendMsg() {
        Editable message = mEditText.getText();
        if (message != null && !"".equals(message.toString().trim())) {
            MsgBean msgBean = new MsgBean("", R.drawable.bill, MsgBean.TYPE_SEND);
            msgBean.setMsg(message.toString());
            msgBean.setFromUser(1111);
            msgBean.setToUser(10000);
            msgBean.setSendTime(System.currentTimeMillis());
            mHandler.getMsgList().add(msgBean);
            showMsg();
            new Thread(new SendMessage(ChatContentActivity.this,mHandler, message.toString())).start();
            mEditText.setText("");
            setSendButtonEnable(false);
        }
    }

    private void setSendButtonEnable(boolean enable) {
        sendButton.setEnabled(enable);
        if (enable) {
            sendButton.setTextColor(Color.parseColor("#ff00ddff"));
        } else {
            sendButton.setTextColor(Color.parseColor("#c4c4c4"));
        }
    }
}
