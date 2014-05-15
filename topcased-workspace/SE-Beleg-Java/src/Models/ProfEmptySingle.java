package Models;

public class ProfEmptySingle extends Model{

	public ProfEmptySingle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTableNameForUpdateOrInsert(){
		return new String("profs");
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String("NameID");
	}

}
