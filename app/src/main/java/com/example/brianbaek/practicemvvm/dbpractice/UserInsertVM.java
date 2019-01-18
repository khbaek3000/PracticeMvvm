package com.example.brianbaek.practicemvvm.dbpractice;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.example.brianbaek.practicemvvm.common.BaseViewModel;
import com.example.brianbaek.practicemvvm.database.dao.UserDao;
import com.example.brianbaek.practicemvvm.database.entity.User;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UserInsertVM extends BaseViewModel {

    String name, region;
    int age;
    User user;
    UserDao userDao;

    //public UserData userData = new UserData();
    //UserData userData = new UserData();

    public UserInsertVM(UserDao userDao) {
        this.userDao = userDao;
    }

    public void onButtonClick(String name, int age, String region, UserDao userDao){
        Log.d("user insert VM", "on ButtonClick");

        user = new User();
        user.setName(name);
        user.setAge(age);
        user.setRegion(region);

        Completable.complete().subscribeOn(Schedulers.io()).subscribe(()->userDao.InsertUser(user));

        userDao.getAllUser().subscribeOn(Schedulers.io())
                .subscribe(

                        userList->{
                            for(User u : userList){
                                Log.d("user data => ", u.toString());
                            }
                        });


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.InsertUser(user);
//
//
//
//                for(User u : userDao.getAllUser()){
//                    Log.d("dao test", "data =>>  " + u.toString());
//
//                }
//            }
//        }).start();

        //userDao.InsertUser(user).subscribeOn(Schedulers.io()).subscribe();

    }


}
