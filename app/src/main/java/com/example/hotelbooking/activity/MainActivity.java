package com.example.hotelbooking.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking.DBManager;
import com.example.hotelbooking.R;
import com.example.hotelbooking.model.Hotel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        db = new DBManager();

    }


    public void btnRegister(View view) {
        startActivity(new Intent(this, RegisterUserActivity.class));
        overridePendingTransition(0, 0);
    }

    public void btnLogin(View view) {
        startActivity(new Intent(this, UserLogInActivity.class));
        overridePendingTransition(0, 0);
    }



}