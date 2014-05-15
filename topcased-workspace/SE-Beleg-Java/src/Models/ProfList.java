package Models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import Models.Filter.StringFilter;

public class ProfList extends Model{

	public ProfList(String srcQuery) {
		super(srcQuery);
	}

	public void setSearchFilter(Object searchValue){
		
		deleteSearchFilter();
		
		if(!searchValue.equals("")){
		
			String[] columnNames = getSqlResultColumnNames();
	
			for(String columnName:columnNames){
				
				StringFilter filter = new StringFilter(searchValue);
				filter.setWertPraefix('%');
				filter.setWertSuffix('%');
				setOrFilter(columnName,filter);
			}
		}
		setResult();
		informView();
	}
	
	public void deleteSearchFilter(){
		String[] columnNames = getSqlResultColumnNames();
		
		for(String columnName:columnNames){
			deleteFilter(columnName);
		}
		
	}
	
	private String[] getSqlResultColumnNames(){
		String[] columnNames = null;
		try{
			ResultSet result = getResult();
			ResultSetMetaData metaData = result.getMetaData();
			columnNames = new String[metaData.getColumnCount()];
			
			for(int index = 1; index <= metaData.getColumnCount();index++){
				columnNames[index-1] = (metaData.getColumnName(index));
			}
			
			
		}catch(SQLException exception){
			exception.printStackTrace();
		}
		return columnNames;
	}

	@Override
	public String getTableNameForUpdateOrInsert() {
		// TODO Auto-generated method stub
		return new String("profs");
	}

	@Override
	protected String getPrimaryKeyColumnName() {
		// TODO Auto-generated method stub
		return new String("NameID");
	}
}
