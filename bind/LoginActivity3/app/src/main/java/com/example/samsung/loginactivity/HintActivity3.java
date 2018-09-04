package com.example.samsung.loginactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by samsung on 2018-09-04.
 */

public class HintActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint3);
    }

    public void returnButtonClicked(View view) {
        finish();
    }
}