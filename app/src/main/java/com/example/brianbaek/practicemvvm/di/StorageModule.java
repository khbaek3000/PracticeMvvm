package com.example.brianbaek.practicemvvm.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.brianbaek.practicemvvm.database.dao.UserDao;
import com.example.brianbaek.practicemvvm.database.db.AppDataBase;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    public AppDataBase provideDataBase(Context context){
        return Room.databaseBuilder(context.getApplicationContext()
                , AppDataBase.class, "mvvmdb.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    public UserDao provideUserDao(AppDataBase appDataBase){
        return appDataBase.userDao();
    }
}
