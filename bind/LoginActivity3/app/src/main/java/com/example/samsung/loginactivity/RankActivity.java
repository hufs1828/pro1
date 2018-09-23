package com.example.samsung.loginactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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

public class RankActivity extends AppCompatActivity implements AbsListView.OnScrollListener{
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
    private int page = 0;
    private final int OFFSET = 6;
    private UserAdapter mAdapter;
    private boolean lastItemVisibleFlag = false;
    private boolean mLockListView = false;
    ArrayList<User> array=new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        currentID=intent.getStringExtra("ID");
        setContentView(R.layout.activity_rank);
        GetData task=new GetData();
        mAdapter= new UserAdapter(this,mArrayList,listview);
        listview = (ListView) findViewById(R.id.listview);
        tv_myid = (TextView) findViewById(R.id.textview_myid);
        tv_myrank = (TextView) findViewById(R.id.textview_myrank);
        tv_mypts = (TextView) findViewById(R.id.textview_mypts);
        task.execute(IP_Addr,"");

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
    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        // 1. OnScrollListener.SCROLL_STATE_IDLE : 스크롤이 이동하지 않을때의 이벤트(즉 스크롤이 멈추었을때).
        // 2. lastItemVisibleFlag : 리스트뷰의 마지막 셀의 끝에 스크롤이 이동했을때.
        // 3. mLockListView == false : 데이터 리스트에 다음 데이터를 불러오는 작업이 끝났을때.
        // 1, 2, 3 모두가 true일때 다음 데이터를 불러온다.
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false) {
            // 화면이 바닦에 닿을때 처리
            // 로딩중을 알리는 프로그레스바를 보인다.
            // 다음 데이터를 불러온다.
            getItem();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // firstVisibleItem : 화면에 보이는 첫번째 리스트의 아이템 번호.
        // visibleItemCount : 화면에 보이는 리스트 아이템의 갯수
        // totalItemCount : 리스트 전체의 총 갯수
        // 리스트의 갯수가 0개 이상이고, 화면에 보이는 맨 하단까지의 아이템 갯수가 총 갯수보다 크거나 같을때.. 즉 리스트의 끝일때. true
        lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
    }
    private void getItem(){

        // 리스트에 다음 데이터를 입력할 동안에 이 메소드가 또 호출되지 않도록 mLockListView 를 true로 설정한다.
        mLockListView = true;

        // 다음 6개의 데이터를 불러와서 리스트에 저장한다.

        // 1초 뒤 프로그레스바를 감추고 데이터를 갱신하고, 중복 로딩 체크하는 Lock을 했던 mLockListView변수를 풀어준다.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },1000);
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
                if(id.equals(currentID))
                    Log.e("위쪽",id);
                User user = new User();
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
            for(int i=0;i<mArrayList.size();i++) { //내랭크 찾기 및 등수 입력
                Log.e("ID", mArrayList.get(i).getID());
                if(i<6)
                    array.add(new User((i + 1) + " 등", mArrayList.get(i).getID(), mArrayList.get(i).getPts() + " pts"));
                if(mArrayList.get(i).getID().equals(currentID)) {
                    tv_myid.setText(currentID);
                    tv_mypts.setText(mArrayList.get(i).getPts() + " pts");
                    tv_myrank.setText((i + 1) + " 등");
                }
            }
            final UserAdapter userAdapter = new UserAdapter(this, array, listview);
            listview.setAdapter(userAdapter);


        }catch (JSONException e) {

        }
    }
}