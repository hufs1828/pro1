package com.example.user.db_sample;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DB_OPEN db_open;
    SQLiteDatabase db;

    /**Called when the activity is first created. */
    @Override
    public  void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

     ArrayList<String> arr = new ArrayList<>();

        db_open = new DB_OPEN(this);
        db= db_open.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT contents from step",null);
        c.moveToFirst();
        c.getCount();

        //row수만큼 반복.
        for(int i=0; i<1; i++){
            arr.add(c.getString(0));
            c.moveToNext();
        }
        ListView list = findViewById(R.id.listview_user);
        final ArrayAdapter<String> aaa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arr);
        list.setAdapter(aaa);

    }
    public void onclick(View view){
      /*  Intent intent =new Intent(this,HintActivity.class);
        startActivity(intent);
        finish();*/
    }
    public void onclick_get_Hint(View view){
        ArrayList<String> arr = new ArrayList<>();

        db_open = new DB_OPEN(this);
        db= db_open.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT contents from step",null);
        c.moveToFirst();
        c.getCount();

        //row수만큼 반복.
        for(int i=0; i< c.getCount(); i++){
            arr.add(c.getString(0));
            c.moveToNext();
        }
        ListView list = findViewById(R.id.listview_user);
        final ArrayAdapter<String> aaa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arr);
        list.setAdapter(aaa);

    }
}

