package Models.Table;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementiert Funktionen zur Verwaltund der Tabellen�berschriften.
 */
public class TableColumnNames {

	private ResultSetMetaData sqlMetaInformation;
	private ArrayList<String> columnAliasList;
	private ArrayList<String> columnNameList;
	private ArrayList<String> columnTableList;
	
	
	/**
	 * Initialisiert die internen Listen auf Grundlage der �bergebenen {@link java.sql.ResultSetMetaData}.<br>
	 * Erstellt eine Liste f�r:<br>
	 * Spaltenaliasnamen, Spaltennamen ohne f�hrenden Tabellennamen, Spaltennamen mit f�hrendem Tabellennamen.
	 *
	 * @param metaInformation	Metainformationen aus einem {@link java.sql.ResultSet}
	 */
	public TableColumnNames(ResultSetMetaData metaInformation){
		sqlMetaInformation = metaInformation;
		columnAliasList = new ArrayList<String>();
		columnNameList = new ArrayList<String>();
		columnTableList = new ArrayList<String>();
		fillColumnListFromSqlMetaInformation();
	}
	
	/**
	 * Liest die {@link java.sql.ResultSetMetaData} Informationen aus und f�llt die Listen f�r Aliasnamen, Spaltennamen und Spaltennamen mit f�hrenden Tabellennamen.
	 */
	private void fillColumnListFromSqlMetaInformation(){
		try {
			for(int i = 1;i<=sqlMetaInformation.getColumnCount();i++){
				columnAliasList.add(sqlMetaInformation.getColumnLabel(i));
				columnNameList.add(sqlMetaInformation.getColumnName(i));
				columnTableList.add(sqlMetaInformation.getTableName(i)+'.'+sqlMetaInformation.getColumnName(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gibt die Liste mit Aliasnamen zur�ck.
	 * @return	Liste mit Aliasnamen der Tabelle
	 */
	public Object[] getColumnAliasNames(){
		return columnAliasList.toArray();
	}
	
	/**
	 * Gibt die Liste der Spaltennamen mit Tabellennamen zur�ck.
	 * @return	Liste der Spaltennamen mit Tabellennamen.
	 */
	public String[] getColumnNames(){

		String [] columnNames;
		columnNames  = new  String[columnTableList.size()];
		
		for (int i=0;i<columnTableList.size(); i++ ){
			columnNames[i]=columnTableList.get(i);
		}
		
		return columnNames;
	}
	
	/**
	 * Ermittelt den Spaltenindex des �bergebenen Aliasspaltennamens.
	 * @param column	Aliasname der Spalte
	 * @return			Spaltenindex des Aliasnamens
	 */
	public int getColumnAliasNameIndex(String column){
		return columnAliasList.indexOf(column);
	}
	
	/**
	 * Ermittelt den Spaltenindex des �bergebenen Spaltennamens.
	 * @param column	Spaltenname der Spalte
	 * @return			Spaltenindex des Spaltennamens
	 */
	public int getColumnNameIndex(String column){
		return columnNameList.indexOf(column);
	}
	
	/**
	 * Ermittelt den Spaltenindex des �bergebenen Spaltennamens mit Tabellennamen.
	 * @param column	Spaltenname mit Tabellenname der Spalte
	 * @return			Spaltenindex des Spaltennamens
	 */
	public int getColumnTableNameIndex(String column){
		return columnTableList.indexOf(column);
	}
	
	/**
	 * Ermittelt den Spaltenindex des �bergebenen Spaltennamens. Der Spaltenname kann<br>
	 * - ein Aliasname,<br>
	 * - ein Spaltenname,<br>
	 * - ein Spaltenname mit f�hrendem Tabellennamen sein.
	 * 
	 * @param columnName	Spaltenname
	 * @return				Index des Spaltennamens
	 */
	public int getColumnIndex(String columnName){
		int columnIndex = getColumnAliasNameIndex(columnName);
		if(columnIndex == -1) columnIndex = getColumnNameIndex(columnName);
		if(columnIndex == -1) columnIndex = getColumnTableNameIndex(columnName);
		return columnIndex;
	}
	
	/**
	 * Ermittelt den Aliasnamen eines Spaltenindex.
	 * @param index	Index des zu ermittelnden Spaltennamens
	 * @return		Aliasname der Spalte.
	 */
	public String getColumnAliasNameByIndex(int index){
		return columnAliasList.get(index);
	}
	
	/**
	 * Ermittelt den Spaltennamen eines Spaltenindex.
	 * @param index	Index des zu ermittelnden Spaltennamens.
	 * @return		Spaltenname der Spalte.
	 */
	public String getColumnNameByIndex(int index){
		return columnNameList.get(index);
	}
	
	/**
	 * F�gt eine Spalte an einer �bergebenen Position ein. Alle dahinter liegenden Spalten werden im Index erh�ht.
	 * @param columnName	Spaltenname der neuen Spalte.
	 * @param position		Position der neuen Spalte.
	 */
	public void addColumn(String columnName,int position){
		try{
			columnAliasList.add(position, columnName);
			columnNameList.add(position, columnName);
			columnTableList.add(position, columnName);
		}catch(IndexOutOfBoundsException exception){
			exception.printStackTrace();
		}
	}
	
	/**
	 * Ermittelt die Anzahl der Spalten und gibt sie zur�ck.
	 * @return	Anzahl Spalten.
	 */
	public int getCountOfColumns(){
		return columnAliasList.size();
	}
	
	/**
	 * Ermittelt die Klasse der angegebenen Spalte und gibt sie zur�ck.
	 * @param columnName	Spaltenname
	 * @return				Klassenname der Spalte, z.B. java.lang.Boolean
	 */
	public String getColumnClass(String columnName){
		return getColumnClass(getColumnIndex(columnName));
	}
	
	/**
	 * Ermittelt die Klasse des angegebenen Spaltenindex und gibt sie zur�ck.
	 * @param columnIndex	Index der Spalte
	 * @return				Klassenname der Spalte, z.B. java.lang.Boolean
	 */
	public String getColumnClass(int columnIndex){
		String columnClass = null;
		try {
			System.out.println(sqlMetaInformation.getColumnClassName(columnIndex));
			columnClass = sqlMetaInformation.getColumnClassName(columnIndex);
		} catch (SQLException e) {
			return null;
		}
		return columnClass;
	}
	
	/**
	 * Ermittelt die definierte Zeichenanzahl des Spaltenindex und gibt ihn zur�ck.<br>
	 * Bsp.: Spalte "name" ist in Datenbank als varchar(40) definiert, wird 40 zur�ckgegeben.
	 * @param columnIndex	Index der Spalte
	 * @return				definierte Zeichenanzahl der Spalte
	 * @throws SQLException
	 */
	public int getDefinedColumnLength(int columnIndex) throws SQLException{
		return sqlMetaInformation.getColumnDisplaySize(columnIndex);
	}
	
}
