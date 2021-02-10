package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import android.widget.AbsListView;
public class MainActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    Button viewCart;
    SQLiteDatabase myDatabase1;
    public void cuisineShow1(View view){
        Intent intent=new Intent(this,MainActivity3.class);
        startActivity(intent);
    }
    public void cuisineShow2(View view){
        Intent intent=new Intent(this,MainActivity4.class);
        startActivity(intent);
    }
    public void cuisineShow3(View view){
        Intent intent=new Intent(this,MainActivity5.class);
        startActivity(intent);
    }
    public void cuisineShow4(View view){
        Intent intent=new Intent(this,MainActivity6.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDatabase1();
//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x;
//        int height = size.y;
        viewCart=(Button)findViewById(R.id.viewCart);


    }
    public void openDatabase1(){

        myDatabase1=this.openOrCreateDatabase("foodItems",MODE_PRIVATE,null);
        myDatabase1.execSQL("CREATE TABLE IF NOT EXISTS foodItems (itemName VARCHAR PRIMARY KEY,number INTEGER(3),amount INTEGER(5))");
//        myDatabase1.execSQL("INSERT INTO foodItems(itemName,number,amount) VALUES('Pasta',0,210)");
//        myDatabase1.execSQL("INSERT INTO foodItems(itemName,number,amount) VALUES('Dosa',0,150)");
//        myDatabase1.execSQL("INSERT INTO foodItems(itemName,number,amount) VALUES('Pizza',0,300)");
//        myDatabase1.execSQL("INSERT INTO foodItems(itemName,number,amount) VALUES('Shahi Paneer',0,220)");
//        myDatabase1.execSQL("INSERT INTO foodItems(itemName,number,amount) VALUES('North Indian Thali',0,200)");
//        myDatabase1.execSQL("INSERT INTO foodItems(itemName,number,amount) VALUES('Noodles',0,150)");
            Cursor c=myDatabase1.rawQuery("SELECT * FROM foodItems",null);
            c.moveToFirst();
        int numberIndex=c.getColumnIndex("itemName");
        int x=c.getCount();
        for(int i=0;i<x;i++){
                Log.i("name",c.getString(numberIndex));
                c.moveToNext();

            }

    }
    public void logging(){
        String s="SELECT * FROM foodItems";
        Cursor c=myDatabase1.rawQuery(s,null);
        c.moveToFirst();
        int item=c.getColumnIndex("itemName");
        int amt=c.getColumnIndex("amount");
        int num=c.getColumnIndex("number");

        int x=c.getCount();
        for(int i=0;i<x;i++){
            Log.i("name",c.getString(item));
            Log.i("amount",c.getInt(amt)+"");
            Log.i("number",c.getInt(num)+"");
            c.moveToNext();
        }
    }
    public int checkPresence(int x){
        int val=0;
        String s="";
        if(x==1)s+="SELECT number FROM foodItems WHERE itemName = 'North Indian Thali' ";
        else if(x==2)s+="SELECT * FROM foodItems WHERE itemName = 'Noodles' ";
        else if(x==3)s+="SELECT * FROM foodItems WHERE itemName = 'Pasta' ";
        else if(x==4)s+="SELECT * FROM foodItems WHERE itemName = 'Pizza' ";
        else if(x==5)s+="SELECT * FROM foodItems WHERE itemName = 'Dosa' ";
        else if(x==6)s+="SELECT * FROM foodItems WHERE itemName = 'Shahi Paneer' ";
        Cursor c=myDatabase1.rawQuery(s,null);
        c.moveToFirst();
        int numberIndex=c.getColumnIndex("number");
        if(c!=null)val=c.getInt(numberIndex);
        return val;
    }
    public void openCart(View view){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    public void addToCart1(View view){
        try{

            int x=checkPresence(1)+1;
//            myDatabase1.execSQL("UPDATE foodItems SET number = x WHERE itemName='North Indian Thali'");
            ContentValues con = new ContentValues();
            con.put("number", x);
            myDatabase1.update("foodItems",con,"itemName='North Indian Thali'",null);
            Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
            logging();
        }catch(Exception e){
            Log.i("error","what is");
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, Try Again", Toast.LENGTH_SHORT).show();
        }
    }
    public void addToCart2(View view){
        try{
            int x=checkPresence(2)+1;
//            myDatabase1.execSQL("UPDATE foodItems SET number =? WHERE itemName='Noodles'");
            ContentValues con = new ContentValues();
            con.put("number", x);
            myDatabase1.update("foodItems",con,"itemName='Noodles'",null);
            Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
            logging();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, Try Again", Toast.LENGTH_SHORT).show();
        }
    }
    public void addToCart3(View view){
        try{
            int x=checkPresence(3)+1;
//            myDatabase1.execSQL("UPDATE foodItems SET number = x WHERE itemName='Pasta'");
            ContentValues con = new ContentValues();
            con.put("number", x);
            myDatabase1.update("foodItems",con,"itemName='Pasta'",null);
            Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
            logging();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, Try Again", Toast.LENGTH_SHORT).show();
        }
    }
    public void addToCart4(View view){
        try{
            int x=checkPresence(4)+1;
//            myDatabase1.execSQL("UPDATE foodItems SET number = x WHERE itemName='Pizza'");
            ContentValues con = new ContentValues();
            con.put("number", x);
            myDatabase1.update("foodItems",con,"itemName='Pizza'",null);
            Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
            logging();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, Try Again", Toast.LENGTH_SHORT).show();
        }
    }
    public void addToCart5(View view){
        try{
            int x=checkPresence(5);
//            myDatabase1.execSQL("UPDATE foodItems SET number = x WHERE itemName='Dosa'");
            ContentValues con = new ContentValues();
            con.put("number", x);
            myDatabase1.update("foodItems",con,"itemName='Dosa'",null);
            Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
            logging();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, Try Again", Toast.LENGTH_SHORT).show();
        }
    }
    public void addToCart6(View view){
        try{
            int x=checkPresence(6)+1;
//            myDatabase1.execSQL("UPDATE foodItems SET number = x WHERE itemName='Shahi Paneer'");
            ContentValues con = new ContentValues();
            con.put("number", x);
            myDatabase1.update("foodItems",con,"itemName='Shahi Paneer'",null);
            Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
            logging();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error, Try Again", Toast.LENGTH_SHORT).show();
        }
    }

}