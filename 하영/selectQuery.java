
class selectQuery{

  private String sql;

  selectQuery(){
    //생성자 : 디비 연결

  }

  ~selectQuery(){
      //소멸자 : 디비 연결 해제
  }

  public String getSQL(){
      return this->sql;
  }

  public void selectAll(String tableName){
    //select *
    this->sql="select * from";
    this->sql=this->sql.append(tableName)
  }

  public void select1(String tableName, String column1){
    //select 한 컬럼
    this->sql = "select ".append(column1);
    this->sql = this->sql.append(" from ");
    this->sql = this->sql.append(tableName);
  }

  public void select2(String tableName, String column1, String column2){
    //select 두 컬럼
    this->sql = "select ".append(column1);
    this->sql = this->sql.append(", ");
    this->sql = this->sql.append(column2);
    this->sql = this->sql.append(" from ");
    this->sql = this->sql.append(tableName);
  }

  public void select3(String tableName, String column1, String column2, String column3){
    //select 세 컬럼
    this->sql = "select ".append(column1);
    this->sql = this->sql.append(", ");
    this->sql = this->sql.append(column2);
    this->sql = this->sql.append(", ");
    this->sql = this->sql.append(column3);
    this->sql = this->sql.append(" from ");
    this->sql = this->sql.append(tableName);
  }

  public void selectCNT(String tableName){
    //select CNT(*)
    this->sql = "select CNT(*) from";
    this->sql = this->sql.append(tableName);
  }
}
