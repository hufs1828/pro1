
class updateQuery{
  private String tableName;
  private String sql;

	updateQuery(){
 //생성자 : 디비 연결

  }

	~updateQuery(){
	//소멸자 : 디비 연결 해제

  }
  public String getsql(){
    //sql문 반환. 코드에서 where 절을 붙여서 사용하면됨.
    return this->sql;
  }


  public void update1(string tableName, string column1,string value1){
    //컬럼 한개에 대해서 update
    this->tableName=tableName;

     this->sql = "update ".append(this->tableName);
     this->sql = this->sql.append("set ").append(this->column1).append(" = ");
     this->sql = this->sql.append(value1);
   }

   public void update2(string tableName,string column1,string value1,string column2,string value2){
     //컬럼 두개에 대해서 update

     this->tableName=tableName;
     this->sql = "update ".append(this->tableName);
     this->sql = this->sql.append("set ").append(this->column1).append(" = ");
     this->sql = this->sql.append(value1);
     this->sql = this->sql.append(" , set ")
     this->sql = this->sql.append("set").append(this->column1).append(" = ");
     this->sql = this->sql.append(value1);
    }


}
