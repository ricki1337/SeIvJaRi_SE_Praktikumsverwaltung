package Controller;

import drucken.PrintExport;

import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Models.Filter.StringFilter;

public class Print extends ControllerNew{

	String sqlQueryString = "SELECT " +
								SqlTableStudent.TableNameDotEMail + " as Email, " +
								SqlTableStudent.TableNameDotVorname + " as Vorname, "  +
								SqlTableStudent.TableNameDotNachname + " as Nachname, " +
								SqlTableStudent.TableNameDotMatrikelNummer + " as 'Matrikelnr.', " +
								SqlTableStudent.TableNameDotStudiengruppe + " as Studiengruppe, " +
								SqlTableCompanies.TableNameDotFirmenname + " as Firmenname " +
							"FROM "+ SqlTableContracts.tableNameWithAlias + " " +
								"JOIN " + SqlTableStudent.tableNameWithAlias + " ON " +
										SqlTableContracts.TableNameDotFK_Student + " = " + SqlTableStudent.TableNameDotPrimaryKey + " " +
								"JOIN " + SqlTableCompanies.tableNameWithAlias + " ON " +
										SqlTableContracts.TableNameDotFK_Firma + " = " + SqlTableCompanies.TableNameDotPrimaryKey;
	
	private Models.ListModel model;
	
	private String primaryKey;
	private Object primaryKeys;
	
	public Print(String primaryKey, Object primaryKeyValues){
		setPrimaryKey(primaryKey);
		setPrimaryKeys(primaryKeyValues);
		
		model = new Models.ListModel(sqlQueryString,SqlTableContracts.tableName,primaryKey);
		setModel(model);
		
		setModelFilter(primaryKey, primaryKeyValues);
		model.setResult();
	}

	public void setPrimaryKeys(Object primaryKeys) {
		if(primaryKeys == null)
			throw new IllegalArgumentException();
		this.primaryKeys = primaryKeys;
	}


	public void setPrimaryKey(String primaryKey) {
		if(primaryKey == null)
			throw new IllegalArgumentException();
		this.primaryKey = primaryKey;
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}


	public Object getPrimaryKeys() {
		return primaryKeys;
	}


	protected void setModelFilter(String primaryKey, Object primaryKeyValues) {
		if(primaryKeyValues instanceof Object[]){		
			
			for(Object item:(Object[])primaryKeyValues){
				
				if(primaryKey.equals(SqlTableStudent.MatrikelNummer)|| primaryKey.equals("ID") || primaryKey.equals("IDCompanies")){
					model.setOrFilter(primaryKey, new IntFilter(item.toString()));
				}
				
				if(primaryKey.equals("Name")){
					model.setOrFilter(primaryKey, new StringFilter(item));
				}
			
			}
		} else{
			if(primaryKey.equals("Matrikelnr") || primaryKey.equals("ID")|| primaryKey.equals("IDCompanies")){
				model.setOrFilter(primaryKey, new IntFilter(primaryKeyValues.toString()));
			}
			
			if(primaryKey.equals("Name")){
				model.setOrFilter(primaryKey, new StringFilter(primaryKeyValues));
			}
		}
	}
	
	
	
	
	public String[][] createArrayForPrinting(){
		String[][] array = new String[model.tableRowData.getRowCount()][9]; 
		try {
			for(int i=0;i<model.tableRowData.getRowCount();i++){
				array[i][0]= model.tableRowData.getStringValueFromPosition(i, "Matrikelnr.");
				array[i][1]= model.tableRowData.getStringValueFromPosition(i, "Vorname") + " " + model.tableRowData.getStringValueFromPosition(i, "Nachname");
				array[i][2]= model.tableRowData.getStringValueFromPosition(i, "Email");
				array[i][3]= model.tableRowData.getStringValueFromPosition(i, "Studiengruppe");
				array[i][4]= model.tableRowData.getStringValueFromPosition(i, "Firmenname");
			}
		
		} catch (Exception e) {
			ErrorManager errorManager = new ErrorManager(e);
				if(errorManager.retry)
					return createArrayForPrinting();
		}
		return array;
	}
	
	@Override
	public void display() {
		try {
			PrintExport testding;
			testding = new PrintExport();
			testding.setData(createArrayForPrinting(), "Titel","Überschrift");
			testding.print();
		} catch (Exception e) {
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				display();
		}
	}

}
