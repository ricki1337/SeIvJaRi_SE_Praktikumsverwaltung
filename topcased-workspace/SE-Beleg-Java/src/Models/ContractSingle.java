package Models;

public class ContractSingle extends Model{
	
	public ContractSingle(String srcQuery) {
		super(srcQuery);
		// TODO Auto-generated constructor stub
	}
	
	public ContractSingle(){
		super();
	}
	
	
	public String getTableNameForUpdateOrInsert(){
		return new String("contract");
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String("ID");
	}
	
	public void changeCompanie(String contractId,String companieId){
		this.updateDatabase("UPDATE "+getTableNameForUpdateOrInsert()+" set CompID = '"+companieId+"' where "+getPrimaryKeyColumnName()+" = '"+contractId+"'");
	}

}
