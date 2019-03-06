package com.example.brianbaek.practicemvvm.dbpractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.brianbaek.practicemvvm.R;
import com.example.brianbaek.practicemvvm.databinding.DbInsertActBinding;
import com.example.brianbaek.practicemvvm.main.MainAct;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class UserInsertAct extends AppCompatActivity {

    @Inject
    UserInsertVM userInsertVM;

    private DbInsertActBinding dbInsertActBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dbInsertActBinding = DataBindingUtil.setContentView(this, R.layout.db_insert_act);
        dbInsertActBinding.setDbinVM(userInsertVM);

        dbInsertActBinding.btDbinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, region;
                int age;
                try {
                    name = dbInsertActBinding.dbinsertIncContent.etName.getText().toString();
                    age = Integer.parseInt(dbInsertActBinding.dbinsertIncContent.etAge.getText().toString());
                    region = dbInsertActBinding.dbinsertIncContent.etRegion.getText().toString();
                    userInsertVM.onButtonClick(name, age, region, userInsertVM.userDao);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "예외 발생", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    public void onGoPlayshopClick(View view){
        Intent intentGoPlayshop = new Intent(this, MainAct.class);
        startActivity(intentGoPlayshop);
    }


}
