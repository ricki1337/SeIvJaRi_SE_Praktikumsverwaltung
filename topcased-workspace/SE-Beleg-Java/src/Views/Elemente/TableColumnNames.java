package Views.Elemente;
import java.sql.*;
import java.util.ArrayList;

public class TableColumnNames {

	ResultSetMetaData sqlMetaInformation;
	ArrayList<String> columnList;
	
	
	
	public TableColumnNames(ResultSetMetaData metaInformation){
		sqlMetaInformation = metaInformation;
		columnList = new ArrayList<String>();
		
		fillColumnListFromSqlMetaInformation();
	}
	
	private void fillColumnListFromSqlMetaInformation(){
		try {
			for(int i = 1;i<=sqlMetaInformation.getColumnCount();i++){
				columnList.add(sqlMetaInformation.getColumnLabel(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object[] getColumnNames(){
		return columnList.toArray();
	}
	
	public int getColumnIndex(String column){
		return columnList.indexOf(column);
	}
	
	public String getColumnNameByIndex(int index){
		return columnList.get(index);
		
	}
	
	public void addColumn(String columnName,int position){
		try{
			columnList.add(position, columnName);
		}catch(IndexOutOfBoundsException exception){
			exception.printStackTrace();
		}
	}
	
	public int getCountOfColumns(){
		return columnList.size();
	}
	
	
	
}
