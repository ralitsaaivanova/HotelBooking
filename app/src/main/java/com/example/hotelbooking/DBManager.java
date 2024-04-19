package com.example.hotelbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hotelbooking.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hotelbooking";

    //TABLE USER
    private static final String TABLE_USER = "USER";
    private static final String KEY_USER_ID = "id";
    private static final String  KEY_USER_USERNAME= "username";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_SURNAME = "surname";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PHONE = "phone";
    private static final String KEY_USER_PASSWORD = "password";

    //TABLE HOTEL
    /*
    private static final String TABLE_HOTEL = "HOTEL";
    private static final String KEY_HOTEL_ID = "id";
    private static final String KEY_HOTEL_NAME = "name";
    private static final String KEY_HOTEL_LOCATION = "location";
    private static final String KEY_HOTEL_PHOTO = "photo";
    private static final String KEY_HOTEL_TYPE_OF_ROOM = "type_of_room";
    private static final String KEY_HOTEL_PRICE = "price";*/

    //TABLE ORDER
/*    private static final String TABLE_HOTEL = "HOTEL";
    private static final String KEY_HOTEL_ID = "id";
    private static final String KEY_HOTEL_ID = "id";
    private static final String KEY_HOTEL_ID = "id";*/



    public DBManager(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE =
                "CREATE TABLE " + TABLE_USER + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY,"
                + KEY_USER_USERNAME + " VARCHAR(255) NOT NULL,"
                + KEY_USER_NAME + " VARCHAR(255) NOT NULL,"
                + KEY_USER_SURNAME + " VARCHAR(255) NOT NULL,"
                + KEY_USER_EMAIL + " VARCHAR(255) NOT NULL UNIQUE,"
                + KEY_USER_PHONE + " VARCHAR(10) NOT NULL,"
                + KEY_USER_PASSWORD + " VARCHAR(30) NOT NULL UNIQUE"
                + ")";

       /* String CREATE_HOTEL_TABLE =
                "CREATE TABLE " + TABLE_HOTEL + "("
                + KEY_HOTEL_ID + "INTEGER PRIMARY KEY,"
                + KEY_HOTEL_NAME + " VARCHAR(255) NOT NULL,"
                + KEY_HOTEL_LOCATION + " VARCHAR(255) NOT NULL,"
                + KEY_HOTEL_TYPE_OF_ROOM + " VARCHAR(255) NOT NULL,"
                + KEY_HOTEL_PHOTO + " LONGBLOB,"
                + KEY_HOTEL_PRICE + " DOUBLE NOT NULL"
                + ")";*/


        db.execSQL(CREATE_USER_TABLE);
        //db.execSQL(CREATE_HOTEL_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + TABLE_USER + ";";
        //String DROP_TABLE_HOTEL = "DROP TABLE IF EXISTS " + TABLE_HOTEL + ";";

        db.execSQL(DROP_TABLE_USER);
        //db.execSQL(DROP_TABLE_HOTEL);
        onCreate(db);
    }

    public String addRecord(String username,String name, String surname,String email, String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("name",name);
        cv.put("surname",surname);
        cv.put("email",email);
        cv.put("phone",phone);
        cv.put("password",password);

        long res = db.insert(TABLE_USER,null,cv);
        if(res==1){
            return "Failed";
        }else {
            return "Successful";
        }

    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_USER,null);

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int currentUserId = cursor.getInt(0);
                String currentUsername = cursor.getString(1);
                String currentName = cursor.getString(2);
                String currentSurname = cursor.getString(3);
                String currentEmail = cursor.getString(4);
                String currentPhone = cursor.getString(5);
                String currentPassword = cursor.getString(6);
                User user = new User(currentUserId,
                                    currentUsername,
                                    currentName,
                                    currentSurname,
                                    currentEmail,
                                    currentPhone,
                                    currentPassword);

                users.add(user);
                cursor.moveToNext();
            }
        }

        return users;
    }
}
