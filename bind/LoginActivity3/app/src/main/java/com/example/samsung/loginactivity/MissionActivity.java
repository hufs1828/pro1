package com.example.samsung.loginactivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MissionActivity extends AppCompatActivity {
    DB_OPEN db_open;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        ArrayList<String> arr = new ArrayList<>();

        db_open = new DB_OPEN(this);
        db= db_open.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT contents from step",null);
        c.moveToFirst();
        c.getCount();

        //row수만큼 반복.
       /* for(int i=0; i< c.getCount(); i++){
            arr.add(c.getString(0));
            c.moveToNext();
        }*/
        arr.add(c.getString(0));

        ListView list = findViewById(R.id.textView);
        final ArrayAdapter<String> aaa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arr);
        list.setAdapter(aaa);


    }

    public void testButtonClicked(View v) {
        String msg = "미션완료!";
        Intent my_intent = new Intent(getApplicationContext(),Hint_Activity.class);
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        startActivity(my_intent);
    }
}
