package com.dyl.chatbot.adapter;

import java.util.List;

import com.dyl.chatbot.R;
import com.dyl.chatbot.vo.MsgBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatListAdapter extends BaseAdapter{

    private Context context;
    private List<MsgBean> data;
    
    public ChatListAdapter(Context context,List<MsgBean> data) {
        
        this.context = context;
        this.data = data;
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        MsgBean msg = data.get(position);
        View view = null;
        if (msg.getMsg_type() == MsgBean.TYPE_RECV) {
            view = View.inflate(context, R.layout.left_item, null);
            ImageView picleft = (ImageView) view.findViewById(R.id.pic_left);
            TextView textleft = (TextView) view.findViewById(R.id.text_left);
            picleft.setImageResource(msg.getPic_res());
            textleft.setText(msg.getMsg());
        } else if (msg.getMsg_type() == MsgBean.TYPE_SEND) {
            view = View.inflate(context, R.layout.right_item, null);
            ImageView picright = (ImageView) view.findViewById(R.id.pic_right);
            TextView textright = (TextView) view.findViewById(R.id.text_right);
            picright.setImageResource(msg.getPic_res());
            textright.setText(msg.getMsg());
        }
        return view;
    }

}
