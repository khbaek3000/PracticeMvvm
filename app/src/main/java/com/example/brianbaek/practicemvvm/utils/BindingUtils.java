package com.example.brianbaek.practicemvvm.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class BindingUtils {

    @BindingAdapter("data")
    public static <T> void setListData(RecyclerView recyclerView, List<T> list){
        if(recyclerView.getAdapter() instanceof BindableAdapter){

            ((BindableAdapter) recyclerView.getAdapter()).setData(list);
        }
    }
}
