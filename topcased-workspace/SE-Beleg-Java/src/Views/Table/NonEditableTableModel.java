package Views.Table;

import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

//http://www.javacoderanch.com/how-to-render-boolean-value-as-checkbox-in-jtable-component.html
//public class NonEditableTable extends JTable{
public class NonEditableTableModel extends AbstractTableModel{
	private Object[] columns;
    private Object[][] data;
	
	public NonEditableTableModel(Object[][] rowData, Object[] columnNames) {
		data = rowData;
		columns = columnNames;
	}

	public boolean isCellEditable(int row, int col){
        if(col == 0) 
        	return true;        	
        return false;
		
    }

	public int getRowCount() {
        return data.length;
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columns[column].toString();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data[0][columnIndex].getClass();
    }
	
    public void setDataAndColumnNames(Object[][] rowData, Object[] columnNames){
    	data = rowData;
		columns = columnNames;
		fireTableDataChanged();
    }
	
}
