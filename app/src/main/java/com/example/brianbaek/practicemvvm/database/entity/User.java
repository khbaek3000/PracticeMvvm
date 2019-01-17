package com.example.brianbaek.practicemvvm.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "uname")
    private String name;

    @ColumnInfo(name = "uage")
    private int age;

    @ColumnInfo(name = "region")
    private String region;
}
