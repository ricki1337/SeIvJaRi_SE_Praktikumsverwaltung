package Models.Table;

import javax.swing.table.AbstractTableModel;

/**
 * Hilfs-{@link AbstractTableModel} um die Manipulation von Daten in der Tabellenansicht zu unterbinden.
 */
public class NonEditableTableModel extends AbstractTableModel{
	
	private Object[] columns;
    private Object[][] data;
 
    /**
     * Initialisiert die Tabellenwerte und Tabellen�berschriften.
     * @param rowData		Tabellenwerte.
     * @param columnNames	Tabellen�berschriften.
     */
	public NonEditableTableModel(Object[][] rowData, Object[] columnNames) {
		data = rowData;
		columns = columnNames;
	}

	/**
	 * Erlaubt das editieren der ersten Spalte der Tabelle.<br>
	 * Die Erste Spalte wird immer mit "Auswahl" gef�llt.
	 */
	public boolean isCellEditable(int row, int col){
        if(col == 0) 
        	return true;        	
        return false;
		
    }

	/**
	 * Ermittelt die Gesamtzahl der Zeilen und gibt diese zur�ck.
	 * @return	Gesamtanzahl der Zeilen der Tabelle.
	 */
	public int getRowCount() {
        return data.length;
    }

	/**
	 * Ermittelt die Gesamtanzahl der Spalten und gibt diese zur�ck.
	 * @return	Gesamtanzahl der Spalten der Tabelle.
	 */
    public int getColumnCount() {
        return columns.length;
    }
    
    /**
     * Gibt den Wert an der angeforderten Position zur�ck.<br>
     * @param rowIndex		Zeilenindex, von welcher der Wert zur�ckgegeben werden soll.
     * @param columnIndex	Spaltenindex, von welcher der Wert zur�ckgegeben werden soll.
     * @return				Wert an der �bermittelten Position.
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
    	if(data[0][columnIndex] instanceof Boolean && data[rowIndex][columnIndex] instanceof Models.Table.EmptyObject)
    		return new Boolean(false);
        return data[rowIndex][columnIndex];
    }

    /**
     * Setzt die Tabellendaten und Tabellen�berschriften und informiert das angeschlossene TableView �ber die �nderung.
     * @param rowData		Neue Tabellendaten.
     * @param columnNames	Neue Tabellen�berschriften.
     */
    public void setDataAndColumnNames(Object[][] rowData, Object[] columnNames){
    	data = rowData;
		columns = columnNames;
		fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column].toString();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data[0][columnIndex].getClass();
    }
	
   
	
}
