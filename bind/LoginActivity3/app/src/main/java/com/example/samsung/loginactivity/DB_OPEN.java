package com.example.samsung.loginactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_OPEN extends SQLiteOpenHelper {

    public DB_OPEN(Context context){
        //TODO Auto-generated constructor stub
        super(context,"db_user",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO Auto-gernerated method stub
        db.execSQL(" create table user" + "(id integer primary key autoincrement,name varchar(50)," +
                "current_gps text, current_course_id integer default 0, current_step_id integer default 0,points integer default 0);");

        db.execSQL("insert into user(name,current_gps,points) values('김하영','000',300)");
        db.execSQL("insert into user(name,current_gps,points) values('김문업','000',340)");
        db.execSQL("insert into user(name,current_gps,points) values('오주훈','000',500)");
        db.execSQL("insert into user(name,current_gps,points) values('이형준','000',1000)");


        db.execSQL(" create table step(" +
                "id integer primary key autoincrement," +
                "name varchar(50)," +
                "contents varchar(1024)," +
                "gps text," +
                "site_id integer,"+
                "cid integer,"+
                "step_id integer);");

        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values('세종대왕동상','조선의 제 4 대 왕을 찾아가세요.', '37.572806&126.976861',1,1,1);");
        db.execSQL("insert into step(name, contents,gps,site_id,cid,step_id) values('광화문','조선시대의 정궁(正宮)의 정문(正門)을 찾아가세요.','37.575996&126.976882',1,1,2);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values('경회루','임금과 신하가 덕으로서 만나는 곳을 찾아가세요.','37.579778&126.976000',1,1,3);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values( '쌈지길','1층부터 4층까지 길로 연결된 곳을 찾아가세요.','37.574335&126.984866',3,1,4);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values('홍대 엘큐브','브라운&초코 남매를 찾아가세요.','37.554694&126.922250',2,4,1);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values( '조각공원','거리 속 도미노를 찾아가세요.','37.555761&126.924021',2,4,2);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values('경의선 숲길','기찻길의 아이들을 찾아가세요.','37.558500&126.925583',4,4,3); ");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values('전쟁기념관','대외항쟁사와 국난극복사를 기획, 전시, 교육하고 있는 곳을  찾아가세요.','37.536583&126.977139',5,2,1);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values( '서울중앙성원','서울에 있는 모스크를 찾아가세요.','37.533361&126.997611',6,2,2);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values('N서울타워','우리나라 최초의 종합전파탑을 찾아가세요.','37.551250&126.988222',7,2,3);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values('바이닐앤 플라스틱','현대카드 뮤직스토어를 찾아가세요.','37.536778&127.000889',8,2,4);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values('코엑스','삼성동 코엑스로 가세요','37.512250&127.058889',9,3,1);");
        db.execSQL("insert into step(name, contents, gps, site_id,cid,step_id) values('코엑스 지하','전시회 관람 후 다양한 놀거리가 있는 코엑스 지하로 가세요.','37.512250&127.058889',9,3,2);");

        db.execSQL("create table quiz(" +
                " id integer primary key autoincrement," +
                " contents text," +
                " answer text," +
                "  step_id integer);");

        db.execSQL("insert into quiz(contents,answer,step_id) values('동상의 높이+폭','10.5',1);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('이곳에 불이 나지 않도록 지키고 있는 상상동물의 이름은?','해태',2);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('현재 경회루의 편액은 누가 작성했나?', '신헌',3);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('이곳에서 지하1층을 부르는 말','아랫길' ,4);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('이곳에서 지하1층을 부르는 말','아랫길' ,4);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('경의선은 용산과 이곳을 연결한 철도다. 이곳은?','신의주',7);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('이곳에 있는 탱크는 총 몇대?','5대',8);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('이곳에서 메카를 바라보는 방향은?', '서북쪽',9);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('이곳 전망대에 있는 화장실의 개수는?','10개',10);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('악마의 집으로 찾아가서 상호명을 정확히 적으세요.' ,'DEVILs DOOR BREWERY',12);");
        db.execSQL("insert into quiz(contents,answer,step_id) values('한국의 돈키호테라 불리는 곳의 이름을 적으세요.','삐에로 쇼핑',13);");


        db.execSQL("create table site( id integer primary key autoincrement, name varchar(50), site_gps text);");
        db.execSQL("insert into site(name,site_gps) values('광화문','37.575996&126.976882');");
        db.execSQL("insert into site(name,site_gps) values('홍대입구역','37.557561&126.924515');");
        db.execSQL("insert into site(name,site_gps) values('인사동쌈지길','37.574299&126.984878');");
        db.execSQL("insert into site(name,site_gps) values('경의선 숲길공원','37.558500&126.925583');");
        db.execSQL(" insert into site(name,site_gps) values('용산 전쟁기념관','37.536583&126.977139');");
        db.execSQL(" insert into site(name,site_gps) values('서울중앙성원','37.533361&126.997611');");
        db.execSQL("insert into site(name,site_gps) values('N서울타워','37.551250&126.988222');");
        db.execSQL("insert into site(name,site_gps) values('한남동','37.537346&126.986997');");
        db.execSQL("insert into site(name,site_gps) values('코엑스','37.512250&127.058889');");


        db.execSQL(" create table course(" +
                " id integer primary key autoincrement," +
                " course_name varchar(50)," +
                " cost integer default 0," +
                " step1 integer default 0," +
                " step2 integer default 0," +
                "  step3 integer default 0," +
                " step4 integer default 0," +
                " step5 integer default 0," +
                "  step6 integer default 0," +
                " distance varchar(100)," +
                " ave_cost integer default 0);");

        db.execSQL("insert into course(course_name,step1,step2,step3,step4) values(\"광화문\",1,2,3,4);");
        db.execSQL("insert into course(course_name,step1,step2,step3,step4) values(\"이태원\",8,11,9,10);");
        db.execSQL("insert into course(course_name,step1,step2) values(\"코엑스\",12,13);\n");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //TODO Auto-generated method stub

        /*기존 테이블 삭제*/
        db.execSQL("Drop table user");
        db.execSQL("drop table step");
        db.execSQL("drop table site");
        db.execSQL("drop table course");
        db.execSQL("drop table quiz");

        /*새로운 디비 생성*/
        onCreate(db);
    }
}
