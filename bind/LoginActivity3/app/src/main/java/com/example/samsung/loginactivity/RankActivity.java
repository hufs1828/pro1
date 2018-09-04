package com.example.samsung.loginactivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by samsung on 2018-09-04.
 */

public class RankActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ListView listview=findViewById(R.id.List_name);
        List<String> list=new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list);

        listview.setAdapter(adapter);
        int score=1000;
        for(int i=1;i<=30;i++){
            list.add(i+".  AAA                                                            "+(score-i*10)+"pts");
        }
    }


}
