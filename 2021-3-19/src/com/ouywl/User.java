package com.ouywl;

/**
 * @description:
 * @author: oywl
 * @time: 2022-8-9 18:41
 */

public class User {
    private String userName;
    private String PassWord;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
