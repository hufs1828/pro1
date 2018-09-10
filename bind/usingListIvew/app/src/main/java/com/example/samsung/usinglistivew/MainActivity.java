package com.example.samsung.usinglistivew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.listview);

        final List<User> user = new ArrayList<>();
        user.add(new User("1등", "aaa", "100 pts"));
        user.add(new User("2등", "bbb", "99 pts"));
        user.add(new User("3등", "CCC", "88 pts"));
        user.add(new User("4등", "DDD", "77 pts"));
        user.add(new User("5등", "EEE", "66 pts"));
        user.add(new User("6등", "FFF", "55 pts"));
        final UserAdapter userAdapter = new UserAdapter(this, user, listview);

        listview.setAdapter(userAdapter);

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("changed", "changed");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibelItemCount,
                                 int totalItemCount) {
            }
        });
    }
}