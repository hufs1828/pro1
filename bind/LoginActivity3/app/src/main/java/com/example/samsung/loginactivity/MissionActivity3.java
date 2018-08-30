package com.example.samsung.loginactivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MissionActivity3 extends AppCompatActivity implements View.OnClickListener {
    private static final int CLEAR_PAGE = 9007;

    String url ="http://14.63.171.18/android.php?ID=1";

    TextView tv;
    DB_OPEN db_open;
    SQLiteDatabase db;
    Integer cid;
    public GettingPHP gPHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission3);

        Intent intent=getIntent();
        Integer strPramIntent = intent.getIntExtra("courseID",-1);

        cid=strPramIntent;
        //toast data that in the previous intent
        Toast.makeText(this, Integer.toString(strPramIntent), Toast.LENGTH_LONG).show();
        ArrayList<String> arr = new ArrayList<>();

        db_open = new DB_OPEN(this);
        db= db_open.getWritableDatabase();

        Cursor c=db.rawQuery("Select s.contents from step s, course c where c.id="+strPramIntent+" and c.id = s.cid and s.step_id=3",null);

        c.moveToFirst();
        c.getCount();

        arr.add(c.getString(0));

        ListView list = findViewById(R.id.textView);
        final ArrayAdapter<String> aaa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arr);
        list.setAdapter(aaa);

    }

    class GettingPHP extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) phpUrl.openConnection();

                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        while (true) {
                            String line = br.readLine();
                            if (line == null)
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            try {
                // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                JSONObject jObject = new JSONObject(str);
                // results라는 key는 JSON배열로 되어있다.
                JSONArray results = jObject.getJSONArray("results");
                String zz = "";
                zz += "Status : " + jObject.get("status");
                zz += "\n";
                zz += "Number of results : " + jObject.get("num_result");
                zz += "\n";
                zz += "Results : \n";

                for (int i = 0; i < results.length(); ++i) {
                    JSONObject temp = results.getJSONObject(i);
                    zz += "\tdoc_idx : " + temp.get("doc_idx");
                    zz += "\tmember_idx : " + temp.get("member_idx");
                    zz += "\tsubject : " + temp.get("subject");
                    zz += "\tcontent : " + temp.get("content");
                    zz += "\treg_date : " + temp.get("reg_date");
                    zz += "\n\t--------------------------------------------\n";
                }
                tv.setText(zz);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearMission3(){
       // Intent intent = new Intent(MissionActivity2.this,MissionActivity3.class);
       // startActivityForResult(intent,MISSION3_MOVE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mission3_clear:
                Toast.makeText(getApplicationContext(),"미션3 클리어!",Toast.LENGTH_LONG).show();
                clearMission3();
                break;
        }
    }
    public void testButtonClicked(View v) {
        String msg = "미션완료!";
        Intent my_intent = new Intent(getApplicationContext(),Hint_Activity.class);
        my_intent.getIntExtra("courseID",cid);
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        startActivity(my_intent);
    }
}
