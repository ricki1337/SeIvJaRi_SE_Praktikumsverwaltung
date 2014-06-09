package Models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import Models.Filter.StringFilter;

public class ListModel extends Model{
	
	
	protected ListModel(){}
	
	public ListModel(String srcQuery,String tableNameForUpdateOrInsert, String primaryKeyColumnName) {
		super(srcQuery,tableNameForUpdateOrInsert,primaryKeyColumnName);
		
	}

	public void setSearchFilter(Object searchValue){
		
		deleteSearchFilter();
		
		if(!searchValue.equals("")){
		
			String[] columnNames = getSqlResultColumnNames();
	
			for(String columnName:columnNames){
				if(getSqlResultColumnNamesClassType(columnName).equals("java.lang.Boolean")) continue;
				StringFilter filter = new StringFilter(searchValue);
				filter.setWertPraefix("%");
				filter.setWertSuffix("%");
				setOrFilter(columnName,filter);
			}
		}
		setResult();
		informView();
	}
	
	public void setSearchFilter(Object[][] searchValues){
			
			deleteSearchFilter();
			
			for(Object[] row:searchValues){
				if(row == null) continue;
				StringFilter filter = new StringFilter((String)row[1]);
				filter.setWertPraefix("%");
				filter.setWertSuffix("%");
				setAndFilter((String)row[0],filter);
	
			}
			setResult();
			informView();
	}
	
	public void setSearchFilter(Map<String,Object> searchValues){
		
		deleteSearchFilter();
		
		for(Map.Entry<String, Object> entry:searchValues.entrySet()){
			StringFilter filter = new StringFilter((String)entry.getValue());
			filter.setWertPraefix("%");
			filter.setWertSuffix("%");
			setAndFilter(entry.getKey(),filter);

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
	
	public String getSqlResultColumnNamesClassType(String columnName){
		try{
			ResultSet result = getResult();
			ResultSetMetaData metaData = result.getMetaData();
			
			for(int index = 1; index <= metaData.getColumnCount();index++){
				if(metaData.getColumnName(index).equals(columnName)){
					System.out.println(columnName+ ": " +metaData.getColumnClassName(index));
					return metaData.getColumnClassName(index);
				}
			}
			
			
		}catch(SQLException exception){
			exception.printStackTrace();
		}
		return null;
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
}
