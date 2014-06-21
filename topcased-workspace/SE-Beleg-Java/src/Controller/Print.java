package Controller;


import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Models.Filter.StringFilter;
import Print.PrintExport;

public class Print extends Controller{

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
	
	/**
	 * Initialisiert die Printansicht auf Grundlage der übergebenen Primärschlüssel.<br>
	 * Erstellt das Datenmodel und setzt die übergebenen primaryKeyValues als Filter von primaryKey.<br>
	 * 
	 * @param primaryKey		Wert einer SqlTable-Definition.
	 * @param primaryKeyValues	Werte für primaryKey, auf welche gefiltert wird.
	 */
	public Print(String primaryKey, Object primaryKeyValues){
		setPrimaryKey(primaryKey);
		setPrimaryKeys(primaryKeyValues);
		
		model = new Models.ListModel(sqlQueryString,SqlTableContracts.tableName,primaryKey);
		setModel(model);
		
		setModelFilter(primaryKey, primaryKeyValues);
		model.setResult();
	}

	

	/**
	 * Setzt den internen primaryKey.
	 * @param primaryKey	Wert aus einer SqlTable-Definition.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void setPrimaryKey(String primaryKey) {
		if(primaryKey == null)
			throw new IllegalArgumentException();
		this.primaryKey = primaryKey;
	}
	
	/**
	 * Gibt den gesetzten primaryKey zurück.
	 * @return	primaryKey.
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}
	

	/**
	 * Setzt die internen primaryKeyValues, welche zum primaryKey gehören.
	 * @param primaryKeyValues	gesetzte primaryKeyValues.	
	 */
	public void setPrimaryKeys(Object primaryKeys) {
		if(primaryKeys == null)
			throw new IllegalArgumentException();
		this.primaryKeys = primaryKeys;
	}

	/**
	 * Gibt die gesetzten primaryKeyValues zurück.
	 * @return gesetzte primaryKeyValues.
	 */
	public Object getPrimaryKeys() {
		return primaryKeys;
	}

	/**
	 * Fügt die gesetzten Filter dem Model hinzu.<br>
	 * Grundlage der Filter sind die übergebenen Werte von primaryKey und primaryKeyValues.
	 */
	protected void setModelFilter(String primaryKey, Object primaryKeyValues) {
		if(primaryKeyValues instanceof Object[]){		
			
			for(Object item:(Object[])primaryKeyValues){
				
				if(primaryKey.equals(SqlTableStudent.TableNameDotMatrikelNummer)|| primaryKey.equals(SqlTableContracts.TableNameDotPrimaryKey) || primaryKey.equals(SqlTableCompanies.TableNameDotPrimaryKey)){
					model.setOrFilter(primaryKey, new IntFilter(item.toString()));
				}
				
				if(primaryKey.equals(SqlTableProfs.TableNameDotPrimaryKey)){
					model.setOrFilter(primaryKey, new StringFilter(item));
				}
			
			}
		} else{
			if(primaryKey.equals(SqlTableStudent.TableNameDotMatrikelNummer)|| primaryKey.equals(SqlTableContracts.TableNameDotPrimaryKey) || primaryKey.equals(SqlTableCompanies.TableNameDotPrimaryKey)){
				model.setOrFilter(primaryKey, new IntFilter(primaryKeyValues.toString()));
			}
			
			if(primaryKey.equals(SqlTableProfs.TableNameDotPrimaryKey)){
				model.setOrFilter(primaryKey, new StringFilter(primaryKeyValues));
			}
		}
	}
	
	/**
	 * Erstellt das Datenarray für den Print mit Inhalten aus dem Model.
	 * @return	2D Array mit den Inhalten.
	 */
	public String[][] createArrayForPrinting(){
		String[][] array = new String[model.getTableRowCount()][9]; 
		try {
			for(int i=0;i<model.getTableRowCount();i++){
				array[i][0]= model.getStringValueFromPosition(i, "Matrikelnr.");
				array[i][1]= model.getStringValueFromPosition(i, "Vorname") + " " + model.getStringValueFromPosition(i, "Nachname");
				array[i][2]= model.getStringValueFromPosition(i, "Email");
				array[i][3]= model.getStringValueFromPosition(i, "Studiengruppe");
				array[i][4]= model.getStringValueFromPosition(i, "Firmenname");
			}
		
		} catch (Exception e) {
			ErrorManager errorManager = new ErrorManager(e);
				if(errorManager.retry)
					return createArrayForPrinting();
		}
		return array;
	}
	
	/**
	 * Bringt den Print-Dialog zur Anzeige.<br>
	 * Initialisiert {@link PrintExport} und übermittelt die zu druckenden Daten.
	 */
	@Override
	public void display() {
		try {
			PrintExport testding;
			testding = new PrintExport();
			testding.setData(createArrayForPrinting(), "Titel","Folgende(r) Student(en) absolviert(en) das Praxisprojekt erfolgreich:");
			testding.print();
		} catch (Exception e) {
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				display();
		}
	}

}
