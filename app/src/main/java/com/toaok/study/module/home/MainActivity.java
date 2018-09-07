package com.toaok.study.module.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.toaok.study.R;

public class MainActivity extends AppCompatActivity {


    public static Intent getIntent(Context context){

        Intent intent=new Intent();
        intent.setClass(context,MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
