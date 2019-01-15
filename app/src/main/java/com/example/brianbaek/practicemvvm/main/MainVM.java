package com.example.brianbaek.practicemvvm.main;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.brianbaek.practicemvvm.R;
import com.example.brianbaek.practicemvvm.apiservice.ApiService;
import com.example.brianbaek.practicemvvm.common.Action1;
import com.example.brianbaek.practicemvvm.common.BaseViewModel;
import com.example.brianbaek.practicemvvm.common.Communication;
import com.example.brianbaek.practicemvvm.model.Product;

import java.util.List;
import java.util.function.Consumer;

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
    ObservableArrayList<Product> productList = new ObservableArrayList<>();

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

    /**********************************
     *
    RxJava Observable practice
    *
    ***********************************/

    Observable<String> myObservable;

    public void observableTest(){
        myObservable = Observable
                .create(subscriber -> {subscriber.onNext("kkk");
                subscriber.onComplete();
                });

        myObservable.subscribe();
    }

    Observable<String> observable;

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

//    @BindingAdapter("bind:item")
//    public void setRvAdapter(RecyclerView recyclerView, ObservableArrayList<Product> observableArrayList){
//        MainListAdapter mainListAdapter = (MainListAdapter)recyclerView.getAdapter();
//        if(mainListAdapter != null){
//
//        }
//    }


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

       });

       //Log.d("product list", "이름 = " + productList.get(1).name + "회사명 = " + productList.get(1).company);



    }
}
