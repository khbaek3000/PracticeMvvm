package com.example.brianbaek.practicemvvm.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "uname")
    private String name;

    @ColumnInfo(name = "uage")
    private int age;

    @ColumnInfo(name = "region")
    private String region;

    public User(){

    }

    public User(int uid, String name, int age, String region) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.region = region;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {

        return "userId = " + uid + ", username = " + name + ", age = " + age + ", region = " + region;
    }
}
