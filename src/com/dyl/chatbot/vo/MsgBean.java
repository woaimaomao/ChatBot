package com.dyl.chatbot.vo;

/**
 * 消息包装类，用于包装一个消息 Created by Jay on 2016/1/22.
 */
public class MsgBean {
    // 消息类型，发送者还是接受者
    public static final int TYPE_SEND = 1;
    public static final int TYPE_RECV = 2;
    // 消息包含数据，一个String，一个联系人头像的id
    private String msg;
    private int pic_res;
    private int msg_type;
    private long sendTime; // 消息发送时间
    private long fromUser; // 发送者id
    private long toUser; // 接收者id

    public MsgBean(String msg, int pic_res, int msg_type) {
        this.msg = msg;
        this.pic_res = pic_res;
        this.msg_type = msg_type;
    }

    public long getFromUser() {
        return fromUser;
    }

    public void setFromUser(long fromUser) {
        this.fromUser = fromUser;
    }

    public long getToUser() {
        return toUser;
    }

    public void setToUser(long toUser) {
        this.toUser = toUser;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPic_res() {
        return pic_res;
    }

    public void setPic_res(int pic_res) {
        this.pic_res = pic_res;
    }

    public int getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(int msg_type) {
        this.msg_type = msg_type;
    }

    @Override
    public String toString() {
        return "MsgBean [msg=" + msg + ", pic_res=" + pic_res + ", msg_type=" + msg_type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((msg == null) ? 0 : msg.hashCode());
        result = prime * result + msg_type;
        result = prime * result + pic_res;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MsgBean other = (MsgBean) obj;
        if (msg == null) {
            if (other.msg != null)
                return false;
        } else if (!msg.equals(other.msg))
            return false;
        if (msg_type != other.msg_type)
            return false;
        if (pic_res != other.pic_res)
            return false;
        return true;
    }

}