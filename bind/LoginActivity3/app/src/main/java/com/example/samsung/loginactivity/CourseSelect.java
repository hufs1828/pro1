package com.example.samsung.loginactivity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class CourseSelect extends AppCompatActivity {
    ViewPager pager;
    private static final int MISSION_SELECT = 9004;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_select);
        pager= (ViewPager)findViewById(R.id.pager);
        CustomAdapter adapter= new CustomAdapter(getLayoutInflater());
        //ViewPager에 Adapter 설정
        pager.setAdapter(adapter);
        Intent intent=getIntent();
        Integer courseID = intent.getIntExtra("courseID",-1);

    }

    private void showMission(Integer value){
        Intent intent = new Intent(CourseSelect.this,MissionActivity.class);
        intent.putExtra("courseID",value);
        startActivityForResult(intent,MISSION_SELECT);
    }
    //onClick속성이 지정된 View를 클릭했을때 자동으로 호출되는 메소드
    public void mOnClick(View v){
        int position;
        switch( v.getId() ){
            case R.id.btn_previous://이전버튼 클릭
                position=pager.getCurrentItem();//현재 보여지는 아이템의 위치를 리턴
                pager.setCurrentItem(position-1,true);
                break;
            case R.id.btn_next://다음버튼 클릭
                position=pager.getCurrentItem();//현재 보여지는 아이템의 위치를 리턴
                pager.setCurrentItem(position+1,true);
                break;
        }
    }

    public void courseSelect(View v) {
        Integer position;
        switch (v.getId()) {
            case R.id.btn_select:
                position = pager.getCurrentItem();
                Toast.makeText(this, "select" + position + "course!!", Toast.LENGTH_LONG).show();
                showMission(position+1);
        }
        return;
    }
}
