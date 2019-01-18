package com.example.brianbaek.practicemvvm.di.subcomponent;

import com.example.brianbaek.practicemvvm.database.entity.User;
import com.example.brianbaek.practicemvvm.dbpractice.UserInsertAct;
import com.example.brianbaek.practicemvvm.di.module.DbInsertModule;
import com.example.brianbaek.practicemvvm.di.module.MainModule;
import com.example.brianbaek.practicemvvm.main.MainAct;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainAct mainAct();

    @ContributesAndroidInjector(modules = {DbInsertModule.class})
    abstract UserInsertAct userInsertAct();
}
