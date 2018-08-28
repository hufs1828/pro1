package com.example.samsung.loginactivity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Hint_Activity extends AppCompatActivity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_hint);
 }

 public void mission1clicked(View view) {
  String msg = "이동!";
  Intent my_intent = new Intent(getApplicationContext(),MissionActivity.class);
  Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
  startActivity(my_intent);
 }

 public void mission2clicked(View v) {
  String msg = "이동!";
  Intent my_intent = new Intent(getApplicationContext(),MissionActivity2.class);
  Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
  startActivity(my_intent);
 }
 public void mission3clicked(View v) {
  String msg = "이동!";
  Intent my_intent = new Intent(getApplicationContext(),MissionActivity3.class);
  Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
  startActivity(my_intent);
 }
}
