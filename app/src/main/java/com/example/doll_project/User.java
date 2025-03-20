package com.example.doll_project;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {
    public String Email,UserName,phoneNumber;

    public Map<String, int[]> map;
    public User(String name, String Email, String phoneNumber, Map<String , int[]> map) {

        this.Email=Email;
        this.UserName = name;
        this.phoneNumber=phoneNumber;
        this.map = map;

    }

    public User(){

    }

    public Map<String , int[]> getMap(){
        return this.map;
    }

    public void SetEmail(String Email) {
        this.Email = Email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String GetUserName(){
        return this.UserName;
    }

    public String GetUserPhoneNum(){
        return this.UserName;
    }

    public String GetUserEmail(){
        return this.Email;
    }

    public void SetUserName(String NewUserName){
        this.UserName=NewUserName;
    }


    @Override
    public String toString() {
        return "User{" +
                "UserName='" + UserName + '\'' +
                ", Email='" + Email + '\'' +
                ", phoneNumber='" + phoneNumber + '\''  +
                '}';
    }
}
