package com.dyl.chatbot.vo;

import java.io.Serializable;

import android.graphics.Bitmap;

public class User implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3470122517935523408L;
    private long id;
    private String name;
    private String password;
    private String email;
    private String phoneNum;
    private String cardId;
    private byte age;
    private byte sex;
    private String lastLoginTime;
    private Bitmap headIcon;
    private String lastMessage;
    
    
    public User() {
    }
    public User(String name, String password, String email, String phoneNum, String cardId, byte age, byte sex,String lastLoginTime,Bitmap headIcon,String lastMessage) {
        super();
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.cardId = cardId;
        this.age = age;
        this.sex = sex;
        this.lastLoginTime = lastLoginTime;
        this.headIcon = headIcon;
        this.lastMessage = lastMessage;
    }
    
    
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLastMessage() {
        return lastMessage;
    }
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
    public Bitmap getHeadIcon() {
        return headIcon;
    }
    public void setHeadIcon(Bitmap headIcon) {
        this.headIcon = headIcon;
    }
    public String getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public byte getAge() {
        return age;
    }
    public void setAge(byte age) {
        this.age = age;
    }
    public byte getSex() {
        return sex;
    }
    public void setSex(byte sex) {
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", password=" + password + ", email=" + email + ", phoneNum=" + phoneNum
                + ", cardId=" + cardId + ", age=" + age + ", sex=" + sex + ", lastLoginTime=" + lastLoginTime
                + ", headIcon=" + headIcon + ", lastMessage=" + lastMessage + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((headIcon == null) ? 0 : headIcon.hashCode());
        result = prime * result + ((lastLoginTime == null) ? 0 : lastLoginTime.hashCode());
        result = prime * result + ((lastMessage == null) ? 0 : lastMessage.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
        result = prime * result + sex;
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
        User other = (User) obj;
        if (age != other.age)
            return false;
        if (cardId == null) {
            if (other.cardId != null)
                return false;
        } else if (!cardId.equals(other.cardId))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (headIcon == null) {
            if (other.headIcon != null)
                return false;
        } else if (!headIcon.equals(other.headIcon))
            return false;
        if (lastLoginTime == null) {
            if (other.lastLoginTime != null)
                return false;
        } else if (!lastLoginTime.equals(other.lastLoginTime))
            return false;
        if (lastMessage == null) {
            if (other.lastMessage != null)
                return false;
        } else if (!lastMessage.equals(other.lastMessage))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (phoneNum == null) {
            if (other.phoneNum != null)
                return false;
        } else if (!phoneNum.equals(other.phoneNum))
            return false;
        if (sex != other.sex)
            return false;
        return true;
    }
   
    
    
    
    
    
}
