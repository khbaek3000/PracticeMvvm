package com.example.brianbaek.practicemvvm.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.brianbaek.practicemvvm.database.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    Void InsertUser(User... users);

    @Query("SELECT * From user Where uid = (:uid)")
    User getUserById(int uid);

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE uname (:userNames)")
    List<User> getAllUserByName(String[] userNames);

    @Delete
    void deleteUser(User user);
}
