package Models;

public class ProfSingle extends Model{

	public ProfSingle(String srcQuery) {
		super(srcQuery);
		// TODO Auto-generated constructor stub
	}
	
	public ProfSingle(){
		super();
	}
	
	
	public String getTableNameForUpdateOrInsert(){
		return new String("profs");
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String("NameID");
	}

}
