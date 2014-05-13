package Models;

public class StudentSingle extends Model{

	public StudentSingle(String srcQuery) {
		super(srcQuery);
		// TODO Auto-generated constructor stub
	}
	
	public StudentSingle(){
		super();
	}
	
	
	public String getTableNameForUpdateOrInsert(){
		return new String("student");
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String("MatrNr");
	}

}
