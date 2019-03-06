package com.example.brianbaek.practicemvvm.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import com.example.brianbaek.practicemvvm.database.dao.UserDao;
import com.example.brianbaek.practicemvvm.main.MainAct;
import com.example.brianbaek.practicemvvm.main.MainListAdapter;
import com.example.brianbaek.practicemvvm.main.MainVM;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainVM provideMainVM(MainAct mainAct, UserDao userDao){
        return ViewModelProviders.of(mainAct, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new MainVM(userDao);
            }
        }).get(MainVM.class);
    }

    @Provides
    MainListAdapter provideMainListAdapter(){
        return new MainListAdapter();
    }
}
