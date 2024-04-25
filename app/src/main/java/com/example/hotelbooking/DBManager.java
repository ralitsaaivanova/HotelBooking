package com.example.hotelbooking;

import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.model.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBManager {

    private String url = "jdbc:mysql://192.168.0.4:3306/hotelbooking";
    private String user = "root";
    private String pass = "Ivanova65a3";
    private Statement statement;
    private Connection connection;

    public DBManager(){

        try {
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username,String name, String surname,String email, String phone, String password) {
        try {
            String insert = "INSERT INTO `users`(`username`,`name`,`surname`,`email`,`phone`,`password`) VALUES ('"+
                    username +"','"+name+"','"+surname+"','"+email+"','"+phone+"','"+password+"');";
            statement.execute(insert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addHotel(String name,String location,String type_of_room,Bitmap bmp,double price){
//        String query = "INSERT INTO users (name,location,type_of_room,photo,price) VALUES(?, ?, ?, ?, ?);";

        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setString(1,name);
//            preparedStatement.setString(2,location);
//            preparedStatement.setString(3,type_of_room);


            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

            byte[] byteArray = stream.toByteArray();

            bmp.recycle();

//            File path = Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_PICTURES);
//            File file = new File(path_of_photo);
//
//            try {
//                FileInputStream fin = new FileInputStream(file);
//                preparedStatement.setBinaryStream(1, fin, (int) file.length());
//                //preparedStatement.executeUpdate();
//
//
//            }catch(IOException e){
//                e.printStackTrace();
//            }

            //FileInputStream fis = new FileInputStream(image);

//            String insert = "INSERT INTO `users`(`name`,`location`,`type_of_room`,`photo`,`price`) VALUES ('"+
//                    name +"','"+location+"','"+type_of_room+"','"+fis+"','"+price+"'');";
//            statement.execute(insert);
            //String query = "INSERT INTO users (name,location,type_of_room,photo,price) VALUES(?, ?, ?, ?, ?);";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,name);
//            preparedStatement.setString(2,location);
//            preparedStatement.setString(3,type_of_room);
//            preparedStatement.setBytes(4,byteArray);
//            preparedStatement.setDouble(5,price);
//
//            preparedStatement.executeUpdate()

            String insert = "INSERT INTO `hotels`(`name`,`location`,`type_of_room`,`photo`,`price`) VALUES ('"+
                    name +"','"+location+"','"+type_of_room+"','"+byteArray+"','"+price+"');";
            statement.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String select = "SELECT * FROM users;";
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(select);
            while (rs.next()){
                User user = new User();
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");

                user.setId(id);
                user.setUsername(username);
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setPhone(phone);
                user.setPassword(password);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return users;
    }

    public ArrayList<Hotel> getAllHotels(){
        ArrayList<Hotel> hotels = new ArrayList<>();
        String select = "SELECT * FROM hotels;";
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(select);
            while (rs.next()){
                Hotel hotel = new Hotel();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                String type_of_room = rs.getString("type_of_room");
                byte[] photo = rs.getBytes("photo");
                double price = Double.parseDouble(rs.getString("price"));

                hotel.setId(id);
                hotel.setName(name);
                hotel.setLocation(location);
                hotel.setType_of_room(type_of_room);
                hotel.setImg(photo);
                hotel.setPrice(price);
                hotels.add(hotel);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return hotels;
    }


}
