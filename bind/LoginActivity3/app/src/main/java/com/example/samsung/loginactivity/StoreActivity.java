package com.example.samsung.loginactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class StoreActivity extends AppCompatActivity implements View.OnClickListener{

    private static String IP_Addr="http://14.63.171.18/score.php";
    private String mJsonString;
    private ArrayList<User> mArrayList=new ArrayList<User>();
    String currentID;
    TextView tv_myid;
    TextView tv_mypts;
    ListView listview;
    TextView textview;
    private UserAdapter mAdapter;

      @Override
    protected void onCreate(Bundle savedInstanceState){
          super.onCreate(savedInstanceState);
          Intent intent = getIntent();
          currentID=intent.getStringExtra("ID");

          setContentView(R.layout.store);
          GetData task=new GetData();
          mAdapter= new UserAdapter(this,mArrayList,listview);

          tv_myid = (TextView) findViewById(R.id.my_id);
          tv_mypts = (TextView) findViewById(R.id.my_points);
          task.execute(IP_Addr,"");

          findViewById(R.id.buy_btn1).setOnClickListener(this);
          findViewById(R.id.buy_btn2).setOnClickListener(this);
          findViewById(R.id.buy_btn3).setOnClickListener(this);
          findViewById(R.id.buy_btn4).setOnClickListener(this);
      }


     @Override
         public void onClick(View v) {
             switch (v.getId()) {
                 case R.id.buy_btn1:
                     showMessage();
                     break;
                 case R.id.buy_btn2:
                     showMessage();
                     break;
                 case R.id.buy_btn3:
                     showMessage();
                     break;
                 case R.id.buy_btn4:
                     showMessage();
                     break;
             }
    }

    private class GetData extends AsyncTask<String,Void,String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog=ProgressDialog.show(StoreActivity.this,"Please wait",null,true,true);
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
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(Integer i=0;i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);
                String id= item.getString(TAG_ID);
                String PTS = item.getString(TAG_PTS); //ID, point를 가져옴
                User user = new User();
                if(id.equals(currentID)){

                    tv_myid.setText(id);
                    tv_mypts.setText(PTS);
                    Log.e("로그인아이디 : ",id);
                    Log.e("내 포인트 : ",PTS);

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

        }catch (JSONException e) {

        }
    }


    private void showMessage() {
        Toast.makeText(this, "상품 구매는 아직 준비 중 입니다.", Toast.LENGTH_LONG).show();

    }
}
