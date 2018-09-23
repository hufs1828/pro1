package com.example.samsung.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class SampleIntent extends AppCompatActivity implements View.OnClickListener {
    private static final int COURSE_SELECT = 9003;
    private static final int MISSION_SELECT = 9004;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        ID=intent.getStringExtra("ID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_intent);

        findViewById(R.id.button_continue).setOnClickListener(this);
        findViewById(R.id.button_new).setOnClickListener(this);
        findViewById(R.id.button_rank).setOnClickListener(this);
        findViewById(R.id.button_store).setOnClickListener(this);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void selectCourse(){
        Intent intent = new Intent(SampleIntent.this,CourseSelect.class);
        intent.putExtra("courseID",0);
        startActivityForResult(intent,COURSE_SELECT);
    }
    private void showRank(){
        Intent intent = new Intent(SampleIntent.this,RankActivity.class);
        intent.putExtra("ID",ID);
        intent.putExtra("Format",18);
        startActivity(intent);
    }

    private void showStore(){
        Log.e("enter","enter to the store");
        Intent intent = new Intent(SampleIntent.this, StoreActivity.class);
        intent.putExtra("ID",ID);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_continue:
                Intent intent = new Intent();
                Toast.makeText(getApplicationContext(),"이어하기",Toast.LENGTH_LONG).show();
                break;
            case R.id.button_new:
                Toast.makeText(getApplicationContext(),"새로운 코스 선택!",Toast.LENGTH_LONG).show();
                selectCourse();
                break;
            case R.id.button_rank:
                Toast.makeText(getApplicationContext(),"RANK 준비중입니다.",Toast.LENGTH_LONG).show();
                showRank();
                break;
            case R.id.button_store:
                showStore();
                Toast.makeText(getApplicationContext(),"STORE로 이동합니다.",Toast.LENGTH_LONG).show();
                break;
        }
    }
}