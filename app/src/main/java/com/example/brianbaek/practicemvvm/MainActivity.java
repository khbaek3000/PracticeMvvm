package com.example.brianbaek.practicemvvm;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.brianbaek.practicemvvm.databinding.ActMainBinding;

public class MainActivity extends AppCompatActivity {
    ActMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.act_main);

    }



}
