package com.dyl.chatbot.adapter;

import java.util.List;

import org.w3c.dom.Text;

import com.dyl.chatbot.R;
import com.dyl.chatbot.vo.User;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendListAdapter extends BaseAdapter{

    private List<User> mList;
    
    private LayoutInflater mInflater;
    private Context mContext;
    public FriendListAdapter(Context context,List<User> friendList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = friendList;
    }
    
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ValueHolder valueHolder = null;
        if(convertView == null){
            valueHolder = new ValueHolder();
            convertView = mInflater.inflate(R.layout.chat_list_item, null);
            valueHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            valueHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            valueHolder.tvMessage = (TextView) convertView.findViewById(R.id.tv_shot_message);
            convertView.setTag(valueHolder);
        }else{
            valueHolder = (ValueHolder) convertView.getTag();
        }
        valueHolder.ivIcon.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher));
        valueHolder.tvName.setText(mList.get(position).getName());
        valueHolder.tvMessage.setText(mList.get(position).getLastMessage());
        return convertView;
    }
    
    private class ValueHolder{
        ImageView ivIcon;
        TextView tvName;
        TextView tvMessage;
    }

}
