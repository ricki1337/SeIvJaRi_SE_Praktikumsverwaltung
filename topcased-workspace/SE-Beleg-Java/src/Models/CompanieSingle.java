package Models;

public class CompanieSingle extends Model{
	
	public CompanieSingle(String srcQuery) {
		super(srcQuery);
		// TODO Auto-generated constructor stub
	}
	
	public CompanieSingle(){
		super();
	}
	
	
	public String getTableNameForUpdateOrInsert(){
		return new String("companies");
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String("ID");
	}

}
