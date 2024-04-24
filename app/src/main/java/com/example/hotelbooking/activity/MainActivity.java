package com.example.hotelbooking.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.DBManager;
import com.example.hotelbooking.R;

public class MainActivity extends AppCompatActivity {

    DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        db = new DBManager();
    }
//    public void startdbapp(View view){
//        new DBManager(this).getWritableDatabase();
//    }



    public void btnRegister(View view) {
        startActivity(new Intent(this, RegisterUserActivity.class));
        overridePendingTransition(0, 0);
    }

    public void btnLogin(View view) {
        startActivity(new Intent(this, UserLogInActivity.class));
        overridePendingTransition(0, 0);
    }
}