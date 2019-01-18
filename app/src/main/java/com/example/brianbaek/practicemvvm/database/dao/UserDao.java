package com.example.brianbaek.practicemvvm.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.brianbaek.practicemvvm.database.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface UserDao {
    @Insert
    void InsertUser(User... users);

    @Query("SELECT * From User Where uid = (:uid)")
    Single<User> getUserById(int uid);

    @Query("SELECT * FROM User")
    Single<List<User>> getAllUser();

    @Query("SELECT * FROM User WHERE uname = (:userNames)")
    Single<List<User>> getAllUserByName(String[] userNames);

    @Delete
    void deleteUser(User user);
}
