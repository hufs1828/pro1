package com.example.kimhayoung.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // String 보다 StringBuffer가 Query 만들기 편하다.
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE TEST_TABLE ( ");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" NAME TEXT, "); sb.append(" AGE INTEGER, ");
        sb.append(" PHONE TEXT ) ");

        // SQLite Database로 쿼리 실행
        db.execSQL(sb.toString());

        //생성 후 Toast로 메시지 출력됨.
        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();
    }
    /** * Application의 버전이 올라가서
     * * Table 구조가 변경되었을 때 실행된다.
     * * @param db * @param oldVersion
     * * @param newVersion */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();

    }
    /**
     *
     **/ public void testDB() {

         SQLiteDatabase db = getReadableDatabase();
     }


     public void addPerson(Person person){
         //1. 쓸 수 있는 DB 객체를 가져온다.
         SQLiteDatabase db = getWritableDatabase();

         //2. Person Data를 insert한다.
         //_id는 자동으로 증가함
         StringBuffer sb = new StringBuffer();
         sb.append(" INSERT INTO TEST_TABLE( ");
         sb.append(" NAME, AGE, PHONE)");
         sb.append("VALUES(?,?,?)");

         db.execSQL(sb.toString(), new Object[]{
                 person.getName(),
                 Integer.parseInt(person.getAge()),
                 person.getPhone()
         });
         Toast.makeText(context,"Insert 완료", Toast.LENGTH_SHORT).show();

     }

     public List getAllPersonData(){
         StringBuffer sb = new StringBuffer();
         sb.append(" SELECT _ID, NAME, AGE, PHONE from TEST_TABLE ");

         SQLiteDatabase db = getReadableDatabase();

         Cursor cursor = db.rawQuery(sb.toString(),null);

         List people = new ArrayList();
         Person person = null;

         //MoveToNext 다음에 데이터가 존재하면 ture, 존재하지 않으면 false
         while(cursor.moveToNext()){
             person= new Person();
             person.set_id(cursor.getInt(0));
             person.setName(cursor.getString(1));
             person.setAge(cursor.getString(2));
             person.setPhone(cursor.getString(3));

             people.add(person);
         }
         return people;
     }


}
