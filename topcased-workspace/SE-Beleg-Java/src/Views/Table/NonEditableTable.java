package Views.Table;

import javax.swing.JTable;


public class NonEditableTable extends JTable{
	public NonEditableTable(Object[][] rowData, Object[] columnNames) {
		super(rowData,columnNames);
	}

	public boolean isCellEditable(int row, int col){
        return false;
    }
	
}
