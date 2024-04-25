package com.example.hotelbooking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotelbooking.DBManager;
import com.example.hotelbooking.R;
import com.example.hotelbooking.model.User;

import java.util.List;

public class UserLogInActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_user_log_in);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        username= findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
    }

    public void loginUser(View view) {
        //Toast.makeText(this,"Ok",Toast.LENGTH_SHORT).show();

        String currentUsername = username.getText().toString();
        String currentPassword = password.getText().toString();

        User currentUser = getCurrentUser(currentUsername,currentPassword);

        //TODO:check for  empty field
        if(currentUser==null){
            Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
        }else {
            if(currentUser.getUsername().equals("admin")){
                startActivity(new Intent(UserLogInActivity.this,AddHotelActivity.class));
                overridePendingTransition(0, 0);
            }else {
                //            Toast.makeText(this, "Username and Password is correct", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserLogInActivity.this, HomeActivity.class));
//                overridePendingTransition(0, 0);
            }

        }
    }


    //Метод, с който променяму статуса на потребителя, че вече се е логнал и го взимаме
    private User getCurrentUser(String username,String password){
            db = new DBManager();
            List<User> users = db.getAllUsers();

        for (User user:users){
            String currentUsername = user.getUsername();
            String currentPassword = user.getPassword();
            if (user.getUsername().equals(username)&& user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }


}