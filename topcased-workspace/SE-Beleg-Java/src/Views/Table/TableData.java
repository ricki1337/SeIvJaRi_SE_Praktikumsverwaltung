package Views.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableData {
	
	private TableColumnNames columnNames;
	private ResultSet sqlResultSet;
	private Object[][] data;
	private int rowCount;
	
	public TableData(ResultSet sqlResultSet){
		this(sqlResultSet,20);
	}
	
	public TableData(ResultSet sqlResultSet, int rowCount){
		this.sqlResultSet = sqlResultSet;
		this.rowCount = rowCount;
		setColumnNames();
		setDataFromResultSet();
		setTableRowsUpToRowCount();
	}
	
	private void setColumnNames(){
		try {
			columnNames = new TableColumnNames(sqlResultSet.getMetaData());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setDataFromResultSet(){
		
		try {
			sqlResultSet.last();
			
			data = new Object[sqlResultSet.getRow()][columnNames.getCountOfColumns()];
			sqlResultSet.first();
			int row = 0;
			do{
				for(int column = 0;column<columnNames.getCountOfColumns();column++){
					Object object = sqlResultSet.getObject(column+1);
					if(object == null){
						object = sqlResultSet.getBoolean(column+1);
					}
					data[row][column] = object;
				}
				row++;
			}while(sqlResultSet.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setRowCount(int rowCount){
		if(rowCount < 0) throw new IllegalArgumentException();
		this.rowCount = rowCount;
		setTableRowsUpToRowCount();
	}
	
	private void setTableRowsUpToRowCount(){
		Object[][] tmp_data = new Object[rowCount][columnNames.getCountOfColumns()];

		for(int row = 0; row < tmp_data.length;row++){
			try{
				tmp_data[row] = data[row];
			}catch(IndexOutOfBoundsException exception){
				tmp_data[row] = new EmptyObject[columnNames.getCountOfColumns()];
			}
		}
		data = tmp_data;
	}
	
	public void addColumnAtBegin(String columnName){
		addColumnAtPosition(columnName,0,null);
	}
	
	public void addColumnAtBegin(String columnName,Object data){
		addColumnAtPosition(columnName,0,data);
	}
	
	public void addColumnAtBegin(String columnName, Object data[]){
		addColumnAtPosition(columnName,0,data);
	}
	
	public void addColumnAtEnd(String columnName){
		addColumnAtPosition(columnName,columnNames.getCountOfColumns()-1,null);
	}
	
	public void addColumnAtEnd(String columnName,Object data){
		addColumnAtPosition(columnName,columnNames.getCountOfColumns()-1,data);
	}
	
	public void addColumnAtEnd(String columnName, Object data[]){
		addColumnAtPosition(columnName,columnNames.getCountOfColumns()-1,data);
	}
	
	public void addColumnAtPosition(String columnName,int position){
		addColumnAtPosition(columnName,position,null);
	}
	
	public void addColumnAtPosition(String columnName,int position, Object data){
		addColumnName(columnName,position);
		addColumn(position,data);
	}
	
	public void addColumnAtPosition(String columnName,int position, Object[] data){
		addColumnName(columnName,position);
		addColumn(position,data);
	}
	
	private void addColumnName(String name, int position){
		if(position < 0 || position > columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		columnNames.addColumn(name, position);
	}
	
	private void addColumn(int position, Object data){
		if(position < 0 || position > columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		
		Object[][] tmp_data = new Object[this.data.length][columnNames.getCountOfColumns()];
		
		for(int row = 0;row < this.data.length;row++){
			for(int column = columnNames.getCountOfColumns()-1;column>=0;column--){
				tmp_data[row][column] = column==position?data:this.data[row][column-1];
			}
		}
		this.data = tmp_data;
	}
	
	private void addColumn(int position, Object[] data){
		if(position < 0 || position > columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		
		if(data.length != columnNames.getCountOfColumns()-1)
			throw new IndexOutOfBoundsException();
		
		Object[][] tmp_data = new Object[this.data.length][columnNames.getCountOfColumns()];
		for(int row = 0;row < this.data.length;row++){
			for(int column = columnNames.getCountOfColumns();column>=0;column--){
				tmp_data[row][column] = column==position?data[row]:this.data[row][column-1];
			}
		}
		this.data = tmp_data;
	}
	
	public Object[] getColumnNames(){
		return columnNames.getColumnNames();
	}
	
	public Object[][] getTableData(){
		return data;
	}

	public Object getValueFromPosition(int row, String column){
		return getValueFromPosition(row,columnNames.getColumnIndex(column));
	}
	
	public Object getValueFromPosition(int row, int column){
		if(row < 0 || row >= data.length)
			throw new IndexOutOfBoundsException();
		if(column < 0 || column >= columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		
		return data[row][column];
	}
	
	public String getStringValueFromPosition(int row, String column) throws Exception{
		return getStringValueFromPosition(row,columnNames.getColumnIndex(column));
	}
	
	public String getStringValueFromPosition(int row, int column) throws Exception{
		Object value = getValueFromPosition(row,column);
		
		if(value instanceof Long){
			return Long.toString((Long) value);
		}
		
		if(value instanceof Boolean){
			return Boolean.toString((Boolean) value);
		}
		
		if(value instanceof Double){
			return Double.toString((Double) value);
		}
		
		if(value instanceof Integer){
			return Integer.toString((Integer) value);
		}
		
		if(value instanceof String){
			return (String) value;
		}
		
		throw new Exception();
	}
	
	public boolean getBooleanValueFromPosition(int row, String column) throws Exception{
		return getBooleanValueFromPosition(row,columnNames.getColumnIndex(column));
	}
	
	public boolean getBooleanValueFromPosition(int row, int column) throws Exception{
		Object value = getValueFromPosition(row,column);
		
		if(value instanceof Boolean){
			return ((Boolean) value);
		}
		
		throw new Exception();
	}	
	
	public void setValueAtPosition(int row, String column, Object value){
		setValueAtPosition(row,columnNames.getColumnIndex(column),value);
	}
	
	public void setValueAtPosition(int row, int column, Object value){
		if(row < 0 || row >= data.length)
			throw new IndexOutOfBoundsException();
		if(column < 0 || column >= columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		data[row][column] = value;
		System.out.println();
	}
	
	public String getColumnName(int columnIndex){
		return columnNames.getColumnNameByIndex(columnIndex);
	}
	
	public int getColumnIndex(String columnName){
		return columnNames.getColumnIndex(columnName);
	}
	
	public int getRowCount(){
		return data.length;
	}
	
}
