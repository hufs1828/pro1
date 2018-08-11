package com.example.samsung.commwithsocket;

import java.io.*;
import java.net.*;

import android.app.*;
import android.widget.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HTTP 요청 방법에 대해 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class commActivity extends TabActivity {
    TabHost mTabHost = null;
    String myId, myPWord, myTitle, mySubject, myResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm);

        mTabHost = getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec("tab_1").setIndicator("서버로 전송").setContent(R.id.page01));
        mTabHost.addTab(mTabHost.newTabSpec("tab_2").setIndicator("서버에서 받음").setContent(R.id.page02));
        // 버튼 이벤트 처리
        findViewById(R.id.button_submit).setOnClickListener(buttonClick);

    }

    Button.OnClickListener buttonClick = new Button.OnClickListener() {
        public void onClick(View v) {
            myId = ((EditText) (findViewById(R.id.edit_Id))).getText().toString();
            myPWord = ((EditText) (findViewById(R.id.edit_pword))).getText().toString();
            mySubject = ((EditText) (findViewById(R.id.edit_subject))).getText().toString();
            myTitle = ((EditText) (findViewById(R.id.edit_title))).getText().toString();

            ConnectThread thread = new ConnectThread();
            thread.start();
        }
    };

    /**
     * 소켓 연결할 스레드 정의
     */
    class ConnectThread extends Thread {
        public void run() {
            try {
                //--------------------------
                //   URL 설정하고 접속하기
                //--------------------------
                URL url = new URL("http://14.63.171.18/jj.php");       // URL 설정
                HttpURLConnection http = (HttpURLConnection) url.openConnection();   // 접속
                //--------------------------
                //   전송 모드 설정 - 기본적인 설정이다
                //--------------------------
                http.setDefaultUseCaches(false);
                http.setDoInput(true);                         // 서버에서 읽기 모드 지정
                http.setDoOutput(true);                       // 서버로 쓰기 모드 지정
                http.setRequestMethod("POST");         // 전송 방식은 POST

                // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
                http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                //--------------------------
                //   서버로 값 전송
                //--------------------------
                StringBuffer buffer = new StringBuffer();
                buffer.append("id").append("=").append(myId).append("&");                 // php 변수에 값 대입
                buffer.append("pword").append("=").append(myPWord).append("&");   // php 변수 앞에 '$' 붙이지 않는다
                buffer.append("title").append("=").append(myTitle).append("&");           // 변수 구분은 '&' 사용
                buffer.append("subject").append("=").append(mySubject);

                OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
                PrintWriter writer = new PrintWriter(outStream);
                writer.write(buffer.toString());
                writer.flush();
                //--------------------------
                //   서버에서 전송받기
                //--------------------------
                InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuilder builder = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {       // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                    builder.append(str + "\n");                     // View에 표시하기 위해 라인 구분자 추가
                }
                myResult = builder.toString();                       // 전송결과를 전역 변수에 저장
                ((TextView) (findViewById(R.id.text_result))).setText(myResult);
            } catch (MalformedURLException e) {
                //
            } catch (IOException e) {
                //
            } // try
        } // HttpPostData
    }
}
