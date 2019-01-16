package com.example.brianbaek.practicemvvm.common;

import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.Observer;

public abstract class Action1<T extends Object>{

    abstract public void call(T s);
}
