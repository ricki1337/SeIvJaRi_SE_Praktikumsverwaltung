package Models;

public class ContractEmptySingle extends Model{

	public ContractEmptySingle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getTableNameForUpdateOrInsert(){
		return new String("contract");
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String("ID");
	}

}
