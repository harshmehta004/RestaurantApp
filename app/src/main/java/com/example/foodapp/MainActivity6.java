package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {
    SQLiteDatabase myDatabase1;
    public void addToCart(View view){
        try{
            openDatabase();
            Cursor c = myDatabase1.rawQuery("SELECT * FROM foodItems WHERE itemName='Dosa'", null);
            int numberIndex = c.getColumnIndex("number");
            int x = 0;
            c.moveToFirst();
            x = c.getInt(numberIndex) + 1;
            ContentValues con = new ContentValues();
            con.put("number", x);
            myDatabase1.update("foodItems", con, "itemName='Dosa'", null);
            Toast.makeText(getApplicationContext(),"Added to Cart",Toast.LENGTH_SHORT).show();
        }catch(Exception e){e.printStackTrace();}
    }
    public void openDatabase(){
        myDatabase1=this.openOrCreateDatabase("foodItems",MODE_PRIVATE,null);
        myDatabase1.execSQL("CREATE TABLE IF NOT EXISTS foodItems (itemName VARCHAR PRIMARY KEY,number INTEGER(3),amount INTEGER(5))");

    }
    public void openCart(View view){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }
}