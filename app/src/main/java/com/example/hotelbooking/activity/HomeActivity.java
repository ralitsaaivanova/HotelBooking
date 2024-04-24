package com.example.hotelbooking.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;

import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.DBManager;
import com.example.hotelbooking.R;
import com.example.hotelbooking.model.Hotel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private DBManager db;
    private ListAdapter listAdapter;
    private ArrayList<Hotel> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_home);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



//        ListView listView = findViewById(R.id.listview);
//        db = new DBManager();
//        hotels=db.getAllHotels();
//        listAdapter = new ListAdapter();
//        listView.setAdapter(listAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        hotels = db.getAllHotels();
        listAdapter.notifyDataSetChanged();
    }


    public void logout(View view) {
        DBManager db = new DBManager();
        startActivity(new Intent(this,UserLogInActivity.class));
//        overridePendingTransition(0, 0);

    }

    public void myProfileBtn(View view) {
        startActivity(new Intent(this,UserProfileActivity.class));
//        overridePendingTransition(0, 0);
    }




    private class ListAdapter extends BaseAdapter {

        public ListAdapter() {}

        @Override
        public int getCount() {
            return hotels.size();
        }

        @Override
        public Hotel getItem(int i) {
            return hotels.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"InflateParams", "ViewHolder"})
            View v = inflater.inflate(R.layout.list_item, null);

            ImageView listImage = v.findViewById(R.id.listImage);
            TextView listName = v.findViewById(R.id.listName);
            TextView listLocation = v.findViewById(R.id.listLocation);
            TextView listRoomType = v.findViewById(R.id.listRoomType);
            TextView listPrice = v.findViewById(R.id.listPrice);
//            Button buttonMakeReservation = v.findViewById(R.id.makeReservation);

            listName.setText(hotels.get(i).getName());
            listLocation.setText(hotels.get(i).getLocation());
            listRoomType.setText(hotels.get(i).getType_of_room());
            listPrice.setText(String.valueOf(hotels.get(i).getPrice()));

            byte[] imgByteArr = hotels.get(i).getImg();
            Bitmap bmp = BitmapFactory.decodeByteArray(imgByteArr, 0, imgByteArr.length);
            listImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, listImage.getWidth(), listImage.getHeight(), false));

            return v;
        }
    }


}