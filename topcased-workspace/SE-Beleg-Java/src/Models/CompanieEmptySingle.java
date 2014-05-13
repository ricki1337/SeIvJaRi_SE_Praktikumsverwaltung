package Models;

public class CompanieEmptySingle extends Model{

	public CompanieEmptySingle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTableNameForUpdateOrInsert(){
		return new String("companies");
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String("ID");
	}

}
