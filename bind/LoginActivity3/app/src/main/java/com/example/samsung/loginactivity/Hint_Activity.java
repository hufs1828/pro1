package com.example.samsung.loginactivity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Hint_Activity extends AppCompatActivity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_hint);
 }

 public void returnButtonClicked(View view) {
  finish();
 }
}
