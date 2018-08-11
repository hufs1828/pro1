package com.example.samsung.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
    }

    public void testButtonClicked(View v) {
        String msg = "미션완료!";
        Intent my_intent = new Intent(getApplicationContext(),HintActivity.class);
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        startActivity(my_intent);
    }
}
