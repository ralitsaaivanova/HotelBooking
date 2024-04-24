package com.example.hotelbooking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.DBManager;
import com.example.hotelbooking.R;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText username,name,surname,email,phone,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_register_user);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        username = findViewById(R.id.editUsername);
        name = findViewById(R.id.editName);
        surname = findViewById(R.id.editSurname);
        email = findViewById(R.id.editEmail);
        phone = findViewById(R.id.editPhone);
        password = findViewById(R.id.editPassword);
    }


    //TODO: да се валидират данните, които се въвеждат за email
    public void registerUser(View view){
        boolean enteredData = checkEnteredData();
        if(checkEnteredData()){
            DBManager db = new DBManager();
            db.addUser(username.getText().toString(),
                    name.getText().toString(),
                    surname.getText().toString(),
                    email.getText().toString(),
                    phone.getText().toString(),
                    password.getText().toString());
            startActivity(new Intent(this, UserLogInActivity.class));
//            overridePendingTransition(0, 0);
        }else{
            Toast t = Toast.makeText(this, "Enter your data correctly!", Toast.LENGTH_SHORT);
            t.show();

        }

    }

    private boolean checkEnteredData(){
        boolean emptyUsername = isEmpty(username);
        boolean emptyName = isEmpty(name);
        boolean emptySurname = isEmpty(surname);
        boolean emptyPassword = isEmpty(password);
        //boolean emptyEmail = !isEmail(email); //default - false -> ако не е имейл
        boolean emptyPhone = isEmpty(phone);

        if (emptyUsername) {
            username.setError("Username is required!");
        }

        if (emptyName) {
            name.setError("Name is required!");
        }

        if (emptySurname) {
            surname.setError("Surname is required!");
        }

        if (emptyPassword) {
            password.setError("Password is required!");
        }

//        if (emptyEmail) {
//            email.setError("Enter valid email!");
//        }

        if (emptyPhone) {
            phone.setError("Phone is required!");
        }

        return !emptyUsername && !emptyName && !emptySurname && !emptyPassword && !emptyPhone;
    }

    //check if EditText is empty
    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    //check if EditText is email
    private boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}