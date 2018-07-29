package com.example.samsung.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SampleIntent extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_intent);

        findViewById(R.id.button_continue).setOnClickListener(this);
        findViewById(R.id.button_new).setOnClickListener(this);
        findViewById(R.id.button_rank).setOnClickListener(this);
        findViewById(R.id.button_store).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_continue:
                Intent intent = new Intent();
                //intent.putExtra("name","mike");
                finish(); Toast.makeText(getApplicationContext(),"CONITNUE 클릭",Toast.LENGTH_LONG).show();
                break;
            case R.id.button_new:
                Toast.makeText(getApplicationContext(),"New Start 클릭",Toast.LENGTH_LONG).show();
                break;
            case R.id.button_rank:
                Toast.makeText(getApplicationContext(),"RANK 클릭",Toast.LENGTH_LONG).show();
                break;
            case R.id.button_store:
                Toast.makeText(getApplicationContext(),"STORE 클릭",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
