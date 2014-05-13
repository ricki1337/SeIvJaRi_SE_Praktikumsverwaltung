package Models;

public class StudentEmptySingle extends Model{

	public StudentEmptySingle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTableNameForUpdateOrInsert(){
		return new String("student");
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String("MatrNr");
	}

}
