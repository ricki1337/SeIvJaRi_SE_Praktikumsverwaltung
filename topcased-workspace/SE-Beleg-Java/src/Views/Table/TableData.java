package Views.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableData {
	
	protected TableColumnNames columnNames;
	protected ResultSet sqlResultSet;
	protected Object[][] data;
	protected int rowCount;
	
	
	protected TableData(){}
	
	public TableData(ResultSet sqlResultSet){
		this(sqlResultSet,20);
	}
	
	public TableData(ResultSet sqlResultSet, int rowCount){
		setSqlResultSet(sqlResultSet);
		setRowCount(rowCount);
		setColumnNames();
		setDataFromResultSet();
		setTableRowsUpToRowCount();
	}
	
	protected void setSqlResultSet(ResultSet sqlResultSet){
		if(sqlResultSet == null) throw new IllegalArgumentException();
		this.sqlResultSet = sqlResultSet;	
	}
	
	public void setRowCount(int rowCount){
		if(rowCount < 1) throw new IllegalArgumentException();
		this.rowCount = rowCount;
	}
	
	public void setNewRowCountAndRefreshData(int rowCount){
		setRowCount(rowCount);
		setDataFromResultSet();
		setTableRowsUpToRowCount();
	}
	
	protected void setColumnNames(){
		try {
			columnNames = new TableColumnNames(sqlResultSet.getMetaData());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void setDataFromResultSet(){
		
		try {
			if(!sqlResultSet.last()){
				return;
			}else{
				sqlResultSet.last();
				
				data = new Object[sqlResultSet.getRow()][columnNames.getCountOfColumns()];
				sqlResultSet.first();
				int dataRow = 0;
				do{
					for(int dataColumn = 0, resultColumn = 1;dataColumn<columnNames.getCountOfColumns();dataColumn++,resultColumn++){
						Object object = sqlResultSet.getObject(resultColumn);
						if(object == null){
							object = sqlResultSet.getBoolean(resultColumn);
						}
						data[dataRow][dataColumn] = object;
					}
					dataRow++;
				}while(sqlResultSet.next());
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	
	protected void setTableRowsUpToRowCount(){
		int rows = rowCount;
		int columns = columnNames.getCountOfColumns();
		Object[][] tmp_data = new Object[rows][columns];
		Object[] emptyRow = new EmptyObject[columns];
		
		for(int column = 0; column < columns;column++) 
			emptyRow[column] = new EmptyObject();
		
		for(int row = 0; row < rows;row++){
			try{
				tmp_data[row] = data[row];
			}
			catch(IndexOutOfBoundsException exception){
				tmp_data[row] = emptyRow;
			}
			catch(NullPointerException exception){
				tmp_data[row] = emptyRow;
			}
		}
		data = tmp_data;
	}

	
	public void addColumnAtBegin(String columnName,Object data){
		addColumnAtPosition(columnName,0,data);
	}
	
	public void addColumnAtPosition(String columnName,int position, Object data){
		addColumnName(columnName,position);
		addColumn(position,data);
	}
	
	protected void addColumnName(String name, int position){
		if(position < 0 || position > columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		
		if(name == null | name.length() == 0)
			throw new IllegalArgumentException();
		
		columnNames.addColumn(name, position);
	}
	
	protected void addColumn(int position, Object data){
		if(position < 0 || position > columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		
		if(data == null)
			throw new IllegalArgumentException();
		
		Object[][] tmp_data = new Object[this.data.length][columnNames.getCountOfColumns()];
		
		for(int row = 0;row < this.data.length;row++){
			for(int column = columnNames.getCountOfColumns()-1;column>=0;column--){
				tmp_data[row][column] = column==position?data:this.data[row][column-1];
			}
		}
		this.data = tmp_data;
	}
	
	public String[] getColumnNames(){ 
		return columnNames.getColumnAliasNames();
	}
	
	public Object[][] getTableData(){
		return data;
	}

	public Object getValueFromPosition(int row, String column){
		int index = columnNames.getColumnIndex(column);
		return getValueFromPosition(row,index);
	}
	
	public Object getValueFromPosition(int row, int column){
		if(row < 0 || row >= data.length)
			throw new IndexOutOfBoundsException();
		
		if(column < 0 || column >= columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		
		return data[row][column];
	}
	
	public String getStringValueFromPosition(int row, String column) throws Exception{
		String valueFromPosition = null;
		try{
			int index = columnNames.getColumnIndex(column);
			valueFromPosition = getStringValueFromPosition(row,index);
		}catch(Exception e){
			
		}
		return valueFromPosition;
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
		
		throw new IndexOutOfBoundsException("Element an Position row: "+row+", column: "+column+" was not found in this function.");
	}
	
	public boolean getBooleanValueFromPosition(int row, String column) throws Exception{
		int index = columnNames.getColumnIndex(column);
		return getBooleanValueFromPosition(row,index);
	}
	
	public boolean getBooleanValueFromPosition(int row, int column) throws Exception{
		Object value = getValueFromPosition(row,column);
		
		if(value instanceof Boolean){
			return ((Boolean) value);
		}
		
		if(value instanceof String){
			return Boolean.parseBoolean((String)value);
		}
		
		if(value instanceof Integer){
			return (((Integer)value)<=0)?false:true;
		}
		
		throw new Exception();
	}	
	
	public void setValueAtPosition(int row, String column, Object value){
		int columnIndex = columnNames.getColumnIndex(column);
		setValueAtPosition(row,columnIndex,value);
	}
	
	public void setValueAtPosition(int row, int column, Object value){
		if(row < 0 || row >= data.length)
			throw new IndexOutOfBoundsException();
		if(column < 0 || column >= columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		data[row][column] = value;
	}
	
	public int getColumnIndex(String columnName){
		return columnNames.getColumnIndex(columnName);
	}
	
	public int getColumnAliasIndex(String columnName){
		return columnNames.getColumnAliasNameIndex(columnName);
	}
	
	public int getColumnNameIndex(String columnName){
		return columnNames.getColumnNameIndex(columnName);
	}
	
	public int getRowCount(){
		int count=0;
		for(Object[] object:data){
			if(object[0] instanceof EmptyObject | object[columnNames.getCountOfColumns()-1] instanceof EmptyObject)
				continue;
			count++;
		}
		return count;
	}
	
}
