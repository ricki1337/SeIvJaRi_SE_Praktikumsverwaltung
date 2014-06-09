package Models;

import Models.Datenbank.SqlTableContracts;

public class ContractSingle extends Model{
	
	public ContractSingle(String srcQuery,String tableNameForUpdateOrInsert, String primaryKeyColumnName) {
		super(srcQuery,tableNameForUpdateOrInsert,primaryKeyColumnName);
	}
	
	public ContractSingle(String tableNameForUpdateOrInsert, String primaryKeyColumnName){
		super(tableNameForUpdateOrInsert,primaryKeyColumnName);
	}
	
	public void changeCompany(String contractId,String companyId){
		this.updateDatabase("UPDATE " + SqlTableContracts.tableName + " set " + SqlTableContracts.TableNameDotFK_Firma + " = '" + companyId + "' where " + SqlTableContracts.TableNameDotId + " = '" + contractId + "'");
	}
	
	public void changeStudent(String contractId,String matrikelNr){
		this.updateDatabase("UPDATE " + SqlTableContracts.tableName + " set " + SqlTableContracts.TableNameDotFK_Student + " = '" + matrikelNr + "' where " + SqlTableContracts.TableNameDotId + " = '" + contractId + "'");
	}

}
