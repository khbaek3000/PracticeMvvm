package com.example.brianbaek.practicemvvm.di.subcomponent;

import com.example.brianbaek.practicemvvm.di.module.MainModule;
import com.example.brianbaek.practicemvvm.main.MainAct;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainAct mainAct();
}
