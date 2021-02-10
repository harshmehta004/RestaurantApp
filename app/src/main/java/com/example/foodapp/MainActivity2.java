package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    SQLiteDatabase myDatabase;

    public void openDatabase1(){
        try {
            myDatabase = this.openOrCreateDatabase("foodItems", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS foodItems (itemName VARCHAR,number INT(5),amount INT(5))");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        openDatabase1();
        onView12();
        
    }
    public void onView12(){int amt=0;
    LinearLayout mainLayout=(LinearLayout)findViewById(R.id.mainLayout);
    mainLayout.setVisibility(View.VISIBLE);
        try{
            Cursor c= myDatabase.rawQuery("SELECT * FROM foodItems WHERE number>0",null);
            int nameIndex= c.getColumnIndex("itemName");
            int numberIndex=c.getColumnIndex("number");
            int amountIndex=c.getColumnIndex("amount");
            c.moveToFirst();
            int count=c.getCount();amt=0;
            for(int i=0;i<count;i++){
                String name=c.getString(nameIndex);
                int yy=(c.getInt(amountIndex)*c.getInt(numberIndex));
                amt+=yy;
                int num=c.getInt(numberIndex);
                TextView textView=(TextView)findViewById(R.id.textView1);
                TextView numberView=(TextView)findViewById(R.id.number1);
                LinearLayout linearLayout=(LinearLayout)findViewById(R.id.l1);
                if(i==0) {
                    linearLayout=(LinearLayout)findViewById(R.id.l1);
                    numberView=(TextView)findViewById(R.id.number1);
                    textView = (TextView) findViewById(R.id.textView1);
                }else if(i==1) {
                    linearLayout=(LinearLayout)findViewById(R.id.l2);
                    numberView=(TextView)findViewById(R.id.number2);
                    textView = (TextView) findViewById(R.id.textView2);
                }else if(i==2) {
                    linearLayout=(LinearLayout)findViewById(R.id.l3);
                    numberView=(TextView)findViewById(R.id.number3);
                    textView = (TextView) findViewById(R.id.textView3);
                }else if(i==3) {
                    linearLayout=(LinearLayout)findViewById(R.id.l4);
                    numberView=(TextView)findViewById(R.id.number4);
                    textView = (TextView) findViewById(R.id.textView4);
                }else if(i==4) {
                    linearLayout=(LinearLayout)findViewById(R.id.l5);
                    numberView=(TextView)findViewById(R.id.number5);
                    textView = (TextView) findViewById(R.id.textView5);
                }else if(i==5) {
                    linearLayout=(LinearLayout)findViewById(R.id.l6);
                    numberView=(TextView)findViewById(R.id.number6);
                    textView = (TextView) findViewById(R.id.textView6);
                }else if (i==6){
                    linearLayout=(LinearLayout)findViewById(R.id.l7);
                    numberView=(TextView)findViewById(R.id.number7);
                    textView = (TextView) findViewById(R.id.textView7);
                }
//                Log.i("itemName",c.getString(nameIndex));
//                Log.i("Amount",Integer.toString(c.getInt(amountIndex)));
                linearLayout.setVisibility(View.VISIBLE);
                textView.setText(name);
                numberView.setText(num+"");
                c.moveToNext();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        if(amt>0){
            LinearLayout l=(LinearLayout)findViewById(R.id.amount);
            l.setVisibility(View.VISIBLE);
            TextView t=(TextView)findViewById(R.id.total);
            t.setText(amt+"");
        }
    }
    public void updation1(int x, String s){
        try {
            myDatabase = this.openOrCreateDatabase("foodItems", MODE_PRIVATE, null);
            ContentValues con=new ContentValues();
            con.put("number",x);
            String[] ss={s};
            myDatabase.update("foodItems", con, "itemName=?", ss);
            logging();
//            onView12();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"not happening",Toast.LENGTH_SHORT).show();
        }
    }
    public void logging(){
        String s="SELECT * FROM foodItems";
        Cursor c=myDatabase.rawQuery(s,null);
        c.moveToFirst();
        int item=c.getColumnIndex("itemName");
        int amt=c.getColumnIndex("amount");
        int num=c.getColumnIndex("number");
        int aa=0;
        int x=c.getCount();
        for(int i=0;i<x;i++){
            Log.i("name",c.getString(item));
            Log.i("amount",c.getInt(amt)+"");
            Log.i("number",c.getInt(num)+"");
            int y=c.getInt(amt)*c.getInt(num);
            aa+=y;
            TextView total=(TextView)findViewById(R.id.total);
            total.setText(aa+"");
            c.moveToNext();
        }
    }
    public void plus1(View view){
            TextView textView=(TextView)findViewById(R.id.number1);
            TextView textView1=(TextView)findViewById(R.id.textView1);
            int x=Integer.parseInt(textView.getText().toString());
            String s=textView1.getText().toString();
            x++;
            updation1(x,s);
            textView.setText(x+"");
    }
    public void minus1(View view){
        TextView textView=(TextView)findViewById(R.id.number1);
        TextView textView1=(TextView)findViewById(R.id.textView1);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x--;
        if(x>=0) {
            updation1(x,s);
            textView.setText(x+"");
        }else{
//            LinearLayout l=(LinearLayout)findViewById(R.id.mainLayout);
//            l.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Not Possible",Toast.LENGTH_SHORT).show();
//            onView12();
        }
    }
    public void plus2(View view){
        TextView textView=(TextView)findViewById(R.id.number2);
        TextView textView1=(TextView)findViewById(R.id.textView2);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x++;
        updation1(x,s);
        textView.setText(x+"");
    }
    public void minus2(View view){
        TextView textView=(TextView)findViewById(R.id.number2);
        TextView textView1=(TextView)findViewById(R.id.textView2);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x--;
        if(x>=0) {
            updation1(x,s);
            textView.setText(x+"");
        }else{
//            LinearLayout l=(LinearLayout)findViewById(R.id.mainLayout);
//            l.setVisibility(View.INVISIBLE);
//            onView12();
            Toast.makeText(getApplicationContext(),"Not Possible",Toast.LENGTH_SHORT).show();onView12();
        }
    }
    public void plus3(View view){
        TextView textView=(TextView)findViewById(R.id.number3);
        TextView textView1=(TextView)findViewById(R.id.textView3);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x++;
        updation1(x,s);
        textView.setText(x+"");
    }
    public void minus3(View view){
        TextView textView=(TextView)findViewById(R.id.number3);
        TextView textView1=(TextView)findViewById(R.id.textView3);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x--;
        if(x>=0) {
            updation1(x,s);
            textView.setText(x+"");
        }else{
//            LinearLayout l=(LinearLayout)findViewById(R.id.mainLayout);
//            l.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Not Possible",Toast.LENGTH_SHORT).show();
//            onView12();
        }
    }
    public void plus4(View view){
        TextView textView=(TextView)findViewById(R.id.number4);
        TextView textView1=(TextView)findViewById(R.id.textView4);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x++;
        updation1(x,s);
        textView.setText(x+"");
    }
    public void minus4(View view){
        Toast.makeText(getApplicationContext(),"minus 4",Toast.LENGTH_SHORT);
        TextView textView=(TextView)findViewById(R.id.number4);
        TextView textView1=(TextView)findViewById(R.id.textView4);
        int x=Integer.parseInt(textView.getText().toString());
        String s="";
        s+=(textView1.getText().toString());
        Log.i("String",s);
        x--;
        if(x>=0) {
            updation1(x,s);
            textView.setText(x+"");
        }else{
//            LinearLayout l=(LinearLayout)findViewById(R.id.mainLayout);
//            l.setVisibility(View.INVISIBLE);
//            onView12();
            Toast.makeText(getApplicationContext(),"Not Possible",Toast.LENGTH_SHORT).show();
        }
    }
    public void plus5(View view){
        TextView textView=(TextView)findViewById(R.id.number5);
        TextView textView1=(TextView)findViewById(R.id.textView5);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x++;
        updation1(x,s);
        textView.setText(x+"");
    }
    public void minus5(View view){
        TextView textView=(TextView)findViewById(R.id.number5);
        TextView textView1=(TextView)findViewById(R.id.textView5);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x--;
        if(x>=0) {
            updation1(x,s);
            textView.setText(x+"");
        }else{
//            LinearLayout l=(LinearLayout)findViewById(R.id.mainLayout);
//            l.setVisibility(View.INVISIBLE);
//            onView12();
            Toast.makeText(getApplicationContext(),"Not Possible",Toast.LENGTH_SHORT).show();
        }
    }
    public void plus6(View view){
        TextView textView=(TextView)findViewById(R.id.number6);
        TextView textView1=(TextView)findViewById(R.id.textView6);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x++;
        updation1(x,s);
        textView.setText(x+"");
    }
    public void minus6(View view){
        TextView textView=(TextView)findViewById(R.id.number6);
        TextView textView1=(TextView)findViewById(R.id.textView6);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x--;
        if(x>=0) {
            updation1(x,s);
            textView.setText(x+"");
        }else{
//            LinearLayout l=(LinearLayout)findViewById(R.id.mainLayout);
//            l.setVisibility(View.INVISIBLE);
//            onView12();
            Toast.makeText(getApplicationContext(),"Not Possible",Toast.LENGTH_SHORT).show();
        }
    }
    public void plus7(View view){
        TextView textView=(TextView)findViewById(R.id.number7);
        TextView textView1=(TextView)findViewById(R.id.textView7);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x++;
        updation1(x,s);
        textView.setText(x+"");
    }
    public void minus7(View view){
        TextView textView=(TextView)findViewById(R.id.number7);
        TextView textView1=(TextView)findViewById(R.id.textView7);
        int x=Integer.parseInt(textView.getText().toString());
        String s=textView1.getText().toString();
        x--;
        if(x>=0) {
            updation1(x,s);
            textView.setText(x+"");
        }else{
//            LinearLayout l=(LinearLayout)findViewById(R.id.mainLayout);
//            l.setVisibility(View.INVISIBLE);
//            onView12();
            Toast.makeText(getApplicationContext(),"Not Possible",Toast.LENGTH_SHORT);
        }
    }

}