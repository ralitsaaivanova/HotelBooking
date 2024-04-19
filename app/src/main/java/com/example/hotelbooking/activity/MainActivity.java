package com.example.hotelbooking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.DBManager;
import com.example.hotelbooking.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void startdbapp(View view){
        new DBManager(this);
        //
    }


    //go to Register form
    public void btnClickRegister(View view) {
        startdbapp(view);
        startActivity(new Intent(this, RegisterUserActivity.class));
    }

    //go to Log in form
    public void btnClickLogIn(View view) {

        //startdbapp(view);
        startActivity(new Intent(this, LogUserActivity.class));
    }
}