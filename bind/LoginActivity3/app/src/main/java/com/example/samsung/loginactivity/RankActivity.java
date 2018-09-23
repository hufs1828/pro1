package com.example.samsung.loginactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private static String IP_Addr="http://14.63.171.18/score.php";
    private String mJsonString;
    private ArrayList<User> mArrayList=new ArrayList<User>();
    ListView listview;
    TextView tv_myid;
    TextView tv_myrank;
    TextView tv_mypts;
    String currentID;
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        currentID=intent.getStringExtra("ID");
        setContentView(R.layout.activity_rank);
        GetData task=new GetData();/*
        final ListView listview = (ListView) findViewById(R.id.listview);
        final TextView tv_myid = (TextView) findViewById(R.id.textview_myid);
        final TextView tv_myrank = (TextView) findViewById(R.id.textview_myrank);
        final TextView tv_mypts = (TextView) findViewById(R.id.textview_mypts);*/
        mAdapter= new UserAdapter(this,mArrayList,listview);
        listview = (ListView) findViewById(R.id.listview);
        tv_myid = (TextView) findViewById(R.id.textview_myid);
        tv_myrank = (TextView) findViewById(R.id.textview_myrank);
        tv_mypts = (TextView) findViewById(R.id.textview_mypts);
        task.execute(IP_Addr,"");
 /*
        Collections.sort(userinfo,new Comparator<userinfo>(){
            public int compare(userinfo a,userinfo b){
                // TODO Auto-generated method stub
                Integer apts=Integer.parseInt(a.pts);
                Integer bpts=Integer.parseInt(b.pts);
                return bpts.compareTo(apts);
            }
        });
//        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                Log.d("@@@Changed", "mListView.getFirstVisiblePosition()="+ listview.getFirstVisiblePosition() );
//                Log.d("@@@Changed", "mListView.getLastVisiblePosition()="+ listview.getLastVisiblePosition() );
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//
//            }
//        });*/
    }

    //JSON으로 데이터 가져와서 파싱
    private class GetData extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog=ProgressDialog.show(RankActivity.this,"Please wait",null,true,true);
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            progressDialog.dismiss();
            if(result!=null){
                mJsonString = result;
                showResult();
            }
            else{
                finish();
            }
        }
        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            String postParameters = "id=" + params[1];
            try{
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK){
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream=httpURLConnection.getErrorStream();
                }
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line=bufferedReader.readLine())!=null){
                    sb.append(line);
                }
                bufferedReader.close();

                return sb.toString().trim();
            }catch (Exception e) {

                errorString = e.toString();

                return null;
            }

        }
    }
    private void showResult(){
        String TAG_JSON="1828";
        String TAG_ID="email";
        String TAG_PTS="score";

        try{
            Log.e("gotten ID: ",currentID.trim());
          //  mAdapter.notifyDataSetChanged();
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
           // Log.e("Error",jsonArray.getString(0));
            for(Integer i=0;i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);
                String id= item.getString(TAG_ID);
                String PTS = item.getString(TAG_PTS); //ID, point를 가져옴
                User user = new User();
                if(id==currentID){
                    Log.e("왜아이디를 찾지 못할까","대체");
                }
                user.setID(id);
                user.setPts(PTS);
                user.setRank(null);
                mArrayList.add(user);
            }
            Collections.sort(mArrayList,new Comparator<User>(){
                @Override
                public int compare(User a,User b){
                    // TODO Auto-generated method stub
                    Integer apts=Integer.parseInt(a.getPts());
                    Integer bpts=Integer.parseInt(b.getPts());
                    return bpts.compareTo(apts);
                }
            });
            ArrayList<User> array=new ArrayList<User>();
            for(int i=0;i<mArrayList.size();i++) { //내랭크 찾기 및 등수 입력
                Log.e("ID", mArrayList.get(i).getID());
                array.add(new User((i + 1) + "등", mArrayList.get(i).getID(), mArrayList.get(i).getPts() + " pts"));
                if(mArrayList.get(i).getPts()=="1750")
                    Log.e("HELLO",mArrayList.get(i).getPts());
            }
            final UserAdapter userAdapter = new UserAdapter(this, array, listview);
            listview.setAdapter(userAdapter);
        }catch (JSONException e) {

        }
    }
}