package com.example.brianbaek.practicemvvm.main;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;

import com.example.brianbaek.practicemvvm.R;
import com.example.brianbaek.practicemvvm.database.entity.User;
import com.example.brianbaek.practicemvvm.databinding.ItemActmainListBinding;
import com.example.brianbaek.practicemvvm.model.Product;
import com.example.brianbaek.practicemvvm.utils.BindableAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ProductListViewHolder>
        implements BindableAdapter<List<User>>, SectionIndexer {
    //List<Product> productList = new ArrayList<>();
    List<User> productList = new ArrayList<>();

    public MainListAdapter() {
    }

    @Override
    public void setData(List<User> data) {
        if(data == null)
            return;

        this.productList= data;
        notifyDataSetChanged();
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemActmainListBinding itemActmainListBinding = DataBindingUtil.inflate(inflater, R.layout.item_actmain_list, viewGroup, false);
        return new ProductListViewHolder(itemActmainListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder viewHolder, int position) {
        User user = productList.get(position);
        viewHolder.itemActmainListBinding.setItem(user);

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
        return productList.size();
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder{
        final ItemActmainListBinding itemActmainListBinding;

        public ProductListViewHolder(@NonNull ItemActmainListBinding itemActmainListBinding) {
            super(itemActmainListBinding.getRoot());
            this.itemActmainListBinding = itemActmainListBinding;
        }
    }
}
