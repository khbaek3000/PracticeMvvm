package com.example.brianbaek.practicemvvm.di.component;

import android.app.Application;

import com.example.brianbaek.practicemvvm.BaseApplication;
import com.example.brianbaek.practicemvvm.di.ApplicationModule;
import com.example.brianbaek.practicemvvm.di.subcomponent.ActivityBindingModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        //AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class
        })
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);
        AppComponent build();
    }

//    @Component.Builder
//    abstract class Builder{
//        @BindsInstance
//        public abstract Builder application(Application application);
//
//        public abstract AppComponent build();
//    }
}
