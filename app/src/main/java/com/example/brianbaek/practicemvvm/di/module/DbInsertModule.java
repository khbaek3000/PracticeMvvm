package com.example.brianbaek.practicemvvm.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.example.brianbaek.practicemvvm.database.dao.UserDao;
import com.example.brianbaek.practicemvvm.dbpractice.UserInsertAct;
import com.example.brianbaek.practicemvvm.dbpractice.UserInsertVM;

import dagger.Module;
import dagger.Provides;

@Module
public class DbInsertModule {
    @Provides
    UserInsertVM provideUserInsertVM(UserInsertAct userInsertAct, UserDao userDao){
        return ViewModelProviders.of(userInsertAct, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new UserInsertVM(userDao);
            }
        }).get(UserInsertVM.class);
    }
}
