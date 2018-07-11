
class insertQuery{
  private String tableName;
  private int colNum;
  private String column;
  private String sql;

	insertQuery(){
 //생성자 : 디비 연결

  }

	~insertQuery(){
	//소멸자 : 디비 연결 해제

  }

  public void insert1(string tableName, string column1,string value1){
    //컬럼 한개에 대해서 insert
    this->tableName=tableName;

     this->sql = "insert ".append(this->tableName);
     this->sql = this->sql.append("(").append(this->column1).append("') values('");
     this->sql = this->sql.append(value1).append("')");
   }

   public void insert2(string tableName,string column1,string value1,string column2,string value2){
     //컬럼 두개에 대해서 insert

     this->tableName=tableName;

      this->sql = "insert ".append(this->tableName);
      this->sql = this->sql.append("(");
      this->sql = this->sql.append(column1);
      this->sql = this->sql.append(",");
      this->sql = this->sql.append(column2).applend(") values('");
      this->sql = this->sql.append(value1);
      this->sql = this->sql.append("','");
      this->sql = this->sql.append(value2);
      this->sql = this->sql.append("')");
    }


}
