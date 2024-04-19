package com.example.hotelbooking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotelbooking.DBManager;
import com.example.hotelbooking.R;
import com.example.hotelbooking.model.User;

import java.util.List;

public class LogUserActivity extends AppCompatActivity {

    EditText username,password;
    Button loginBtn;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_user);
        username = findViewById(R.id.editLogUsername);
        password = findViewById(R.id.editLogPassword);
        loginBtn = findViewById(R.id.loginBtn);
    }


    //checks if  user is in the database
    private User isUserInDatabase(){
        DBManager db = new DBManager(this);

        User loggedUser = new User(username.toString(),password.toString());

        List<User> allUsers = db.getAllUsers();
        for (User user:allUsers){
            String currentUsername = user.getUsername();
            if(loggedUser.getUsername().equals(currentUsername)){
                return user;
            }
        }
        return null;
    }

    private User aboutLoggedUser(){
        DBManager db = new DBManager(this);
        List<User> users = db.getAllUsers();
        for (User user:users){
            String usernameFromDB = user.getUsername();
            String passFromDB = user.getPassword();
            String usernameStr = username.getText().toString();
            String passStr = password.getText().toString();
            boolean equalUsername = usernameFromDB.equals(usernameStr);
            boolean equalPassword = passFromDB.equals(passStr);
            //TODO: write shorter way

            if (equalUsername && equalPassword){
                return user;
            }
        }
        return null;
    }

    //checks if login-input data is correct
    public void btnClickLogInForm(View view) {
        if(aboutLoggedUser()==null){
            Toast.makeText(LogUserActivity.this,"Username or Password is incorrect",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(LogUserActivity.this,"Username and Password is correct",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

//    public boolean isLoginDataValid(String username,String password){
//
//
//
//        void Login(){
//            btn_login.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(et_username.getText().toString().equals("admin") && et_password.getText().toString().equals("admin")){
//                        Toast.makeText(Login.this, "Username and Password is correct", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Login.this,User.Class);
//                        startActivity(intent);
//                    }else{
//                        Toast.makeText(Login.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//
//
//    }


}