package com.example.samsung.loginactivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by samsung on 2018-09-04.
 */

public class RankActivity extends AppCompatActivity {
    DB_OPEN db_open;
    SQLiteDatabase db;
    class userinfo{
        private String name;
        private String pts;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPts() {
            return pts;
        }

        public void setPts(String pts) {
            this.pts = pts;
        }

        public userinfo(String name, String pts){
            this.name=name;
            this.pts=pts;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        final ListView listview = (ListView) findViewById(R.id.listview);
        final TextView tv_myname = (TextView) findViewById(R.id.textview_myname);
        final TextView tv_myrank = (TextView) findViewById(R.id.textview_myrank);
        final TextView tv_mypts = (TextView) findViewById(R.id.textview_mypts);
        final List<User> user = new ArrayList<>();

        //여기부터
        db_open = new DB_OPEN(this);
        db = db_open.getReadableDatabase();
//        StringBuffer sb = new StringBuffer("Select name from user");
        Cursor c = db.rawQuery("Select * from user",null);
  /*      if(c.moveToNext()){
            user.add(new User("1등",c.getString(0),c.getString(2)));
        }*/
        //여기까지*
        ArrayList<userinfo> userinfo = new ArrayList<>();

        for(int i=0;i<c.getCount();i++){
            c.moveToNext();
            userinfo.add(i,new userinfo(c.getString(1),c.getString(5)));
            Log.e("User name: ",c.getString(1)+"pts: "+c.getString(5));
        }

        Collections.sort(userinfo,new Comparator<userinfo>(){
           public int compare(userinfo a,userinfo b){
               // TODO Auto-generated method stub
                Integer apts=Integer.parseInt(a.pts);
                Integer bpts=Integer.parseInt(b.pts);
                return bpts.compareTo(apts);
            }
        });
        for(int i=1;i<=c.getCount();i++){
            user.add(new User(i+"등",userinfo.get(i-1).getName(),userinfo.get(i-1).getPts()+" pts"));
        }
      /*  user.add(new User("1등", "aaa", "1000 pts "));
        user.add(new User("2등", "bbb", "99 pts "));
        user.add(new User("3등", "CCC", "88 pts "));
        user.add(new User("4등", "DDD", "77 pts "));
        user.add(new User("5등", "EEE", "66 pts "));*/
        final UserAdapter userAdapter = new UserAdapter(this, user, listview);
        tv_myname.setText("MyName");
        tv_mypts.setText("000 pts ");
        tv_myrank.setText("15 등");

        listview.setAdapter(userAdapter);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("@@@Changed", "mListView.getFirstVisiblePosition()="+ listview.getFirstVisiblePosition() );
                Log.d("@@@Changed", "mListView.getLastVisiblePosition()="+ listview.getLastVisiblePosition() );
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


            }
        });
    }

}
