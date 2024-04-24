package com.example.hotelbooking.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.DBManager;
import com.example.hotelbooking.R;

import java.io.IOException;

public class AddHotelActivity extends AppCompatActivity {

    private EditText hotelName,location,room_type,price;
    private Button btnPickImage;
    private ImageView imageView;
    private final int SELECT_PICTURE = 200;

    private Uri uriPath;
    private Bitmap imageToStored;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_add_hotel);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        hotelName = findViewById(R.id.editHotelName);
        location = findViewById(R.id.editLocation);
        room_type = findViewById(R.id.editRoomType);
        price = findViewById(R.id.editPrice);
        btnPickImage = findViewById(R.id.BSelectImage);
        imageView = findViewById(R.id.ImageView);

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                    uriPath = data.getData();

                    try {
                        imageToStored = MediaStore.Images.Media.getBitmap(getContentResolver(),uriPath);
                        ///
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // update the preview image in the layout
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }

    public void logout(View view) {
        startActivity(new Intent(this,UserLogInActivity.class));
//        overridePendingTransition(0, 0);
    }

    public void addHotel(View view) {
        DBManager db = new DBManager();
        db.addHotel(hotelName.getText().toString(),
                location.getText().toString(),
                room_type.getText().toString(),
                imageToStored,
                Double.parseDouble(price.getText().toString()));


        Toast t = Toast.makeText(this, "Успешно добавихте хотел!", Toast.LENGTH_SHORT);
        t.show();
        hotelName.setText("");
        location.setText("");
        room_type.setText("");
        imageView.setImageResource(0);
        price.setText("");

    }

    //TODO: validate inputData

//    public void addHotel(View view) {
//        boolean enteredData = checkEnteredData();
//        if(checkEnteredData()){
//            DBManager db = new DBManager();
//
//
//            db.addHotel(hotelName.getText().toString(),
//                    location.getText().toString(),
//                    room_type.getText().toString(),
//                    Double.parseDouble(price.getText().toString()),
//                    password.getText().toString());
//            startActivity(new Intent(this, UserLogInActivity.class));
//        }else{
//            Toast t = Toast.makeText(this, "Enter your data correctly!", Toast.LENGTH_SHORT);
//            t.show();
//
//        }
//
//
//    }
}