package com.example.hotelbooking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.DBManager;
import com.example.hotelbooking.R;

public class RegisterUserActivity extends AppCompatActivity {

    EditText username,name,surname,email,phone,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        username = findViewById(R.id.editUsername);
        name = findViewById(R.id.editName);
        surname = findViewById(R.id.editSurname);
        email = findViewById(R.id.editEmail);
        phone = findViewById(R.id.editPhone);
        password = findViewById(R.id.editPassword);
    }
    public void addUser(View view){
        DBManager db = new DBManager(this);

        //TODO: валидация, да бъдат попълнени данните

        String res = db.addRecord(username.getText().toString(),
                    name.getText().toString(),
                surname.getText().toString(),
                email.getText().toString(),
                phone.getText().toString(),
                password.getText().toString());
        Toast.makeText(this,res,Toast.LENGTH_SHORT).show();


        username.setText("");
        name.setText("");
        surname.setText("");
        email.setText("");
        phone.setText("");
        password.setText("");

        startActivity(new Intent(this,LogUserActivity.class));
    }
}