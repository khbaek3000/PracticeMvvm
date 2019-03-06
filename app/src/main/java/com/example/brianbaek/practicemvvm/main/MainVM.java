package com.example.brianbaek.practicemvvm.main;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.brianbaek.practicemvvm.R;
import com.example.brianbaek.practicemvvm.apiservice.ApiService;
import com.example.brianbaek.practicemvvm.common.Action1;
import com.example.brianbaek.practicemvvm.common.BaseViewModel;
import com.example.brianbaek.practicemvvm.common.Communication;
import com.example.brianbaek.practicemvvm.database.dao.UserDao;
import com.example.brianbaek.practicemvvm.database.entity.User;
import com.example.brianbaek.practicemvvm.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainVM extends BaseViewModel {
    MutableLiveData<List<Product>> productLiveData = new MutableLiveData<>();
    ObservableBoolean isLogin = new ObservableBoolean();
    ObservableInt menuResId = new ObservableInt();
    ObservableBoolean isLoadFinish = new ObservableBoolean();
    ObservableArrayList<Product> productList = new ObservableArrayList<>();
    private ObservableField<List<User>> observableProductList = new ObservableField<>(new ArrayList<>());
    List<User> userList = new ArrayList<>();
    UserDao userDao;

    public MainVM(UserDao userDao) {
        this.userDao = userDao;
    }

    public void init(){
        setObservableProductList();
    }



    public ObservableField<List<User>> getObservableProductList() {
        return observableProductList;
    }

    public void setObservableProductList() {
        userDao.getAllUser()
                .subscribeOn(Schedulers.io())
                .subscribe(list-> {
                    userList.addAll(list);
                    observableProductList.set(list);

                    isLoadFinish.set(true);
                    for(User user: observableProductList.get()){
                        Log.d("userdata", user.toString());
            }
                });
//        List<User> list = new ArrayList();
//        userList.add(new User(123, "user1", 11, "한국"));
//        userList.add(new User(123, "user2", 21, "한국"));
//        userList.add(new User(123, "user3", 31, "한국"));
//        userList.add(new User(123, "user4", 41, "한국"));
//        userList.add(new User(123, "user5", 51, "한국"));
//        observableProductList.set(list);
    }

    @BindingAdapter("app:menu")
    public static void nvMenuSetting(NavigationView view, boolean login){
        if(login){
            view.inflateMenu(R.menu.drawermenulogin);
            return;
        }
        view.inflateMenu(R.menu.drawermenulogout);
    }

    public void setIsLogin(Boolean check) {

        this.isLogin.set(check);
    }

    public ObservableBoolean getIsLogin(){
        return isLogin;
    }

    public void setMenuResId(Boolean isLogin) {
        if(isLogin){
            this.menuResId.set(R.menu.drawermenulogin);
            return;
        }
        this.menuResId.set(R.menu.drawermenulogout);
    }

    public ObservableInt getMenuResId(){
        return menuResId;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public ObservableArrayList<Product> getProductList() {
        return productList;
    }

//    public void isEmulator(){
//        Log.d("Is Emulator", Build.MANUFACTURER.toString());
//    }

    /**********************************
     *
    RxJava Observable practice
    *
    ***********************************/

    Observable<String> myObservable;
    Observable<String> observable;


    public void observableTest(){
        myObservable = Observable
                .create(subscriber -> {subscriber.onNext("kkk");
                subscriber.onComplete();
                });

        myObservable.subscribe();
    }

    Consumer c = s->{System.out.println(s);};
    public void observableTest2(){
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("kkk");
            }
        });

        observable.subscribe(s->Log.d("Observable Test", s));
    }
    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            System.out.println(s);
        }
    };

    public Observable getStringObservable(String content){
        return Observable.create(subscriber -> subscriber.onNext(content));

    }

    public Observable<Integer> getIntegerObservable(String content){
        return testObservable(content)
                .map(new Function<String , Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        try {
                            return Integer.parseInt(s);
                        } catch (Exception e){
                            return 0;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable testObservable(String content){
        return Observable.just(content);


    }

    //@BindingAdapter("item")
    public void setRvAdapter(RecyclerView recyclerView, ObservableArrayList<Product> observableArrayList){
        MainListAdapter mainListAdapter = (MainListAdapter)recyclerView.getAdapter();
        if(mainListAdapter != null){

        }
    }


    public void setProductList(String query){
       new Communication()
               .getProductList(query)
               .subscribe(productList-> {
                   this.productList.addAll(productList);
                   if(this.productList!=null){
                       for(Product p : this.productList){
                           Log.d("product list ", " name = "
                                   + p.name + " company = " + p.company + " category = "
                                   + p.category.name + " quantity = " + p.quantity);
                       }
                   }

       }, throwable -> {Log.d("Network Exception" , throwable.toString());});

       //Log.d("product list", "이름 = " + productList.get(1).name + "회사명 = " + productList.get(1).company);

    }

    public ObservableBoolean getIsLoadFinish() {
        return isLoadFinish;
    }

    public void setIsLoadFinish(ObservableBoolean isLoadFinish) {
        this.isLoadFinish = isLoadFinish;
    }
}
