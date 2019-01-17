package com.example.brianbaek.practicemvvm.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    Context providesContext(Application application){
        return application;
    }
}
