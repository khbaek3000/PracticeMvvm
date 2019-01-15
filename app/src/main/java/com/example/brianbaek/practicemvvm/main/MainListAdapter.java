package com.example.brianbaek.practicemvvm.main;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.brianbaek.practicemvvm.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ProductListViewHolder> {
    //List<Product> productList = new ArrayList<>();
    ObservableArrayList<Product> productList = new ObservableArrayList<>();

    public MainListAdapter() {
    }
    public void setItem(List<Product> list){
        if(list==null)
            return;
        this.productList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder{

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
