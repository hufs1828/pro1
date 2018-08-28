package com.example.kimhayoung.untitled;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class DB_TESTActivity extends Activity {
    DB_OPEN db_open;
    SQLiteDatabase db;

    /**Called when the activity is first created. */
    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db__test);

        ArrayList<String> arr = new ArrayList<>();

        db_open = new DB_OPEN(this);
        db= db_open.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT course_name from course",null);
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
