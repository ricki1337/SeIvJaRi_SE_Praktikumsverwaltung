package Models.Table;

import javax.swing.table.AbstractTableModel;

/**
 * Hilfs-{@link AbstractTableModel} um die Manipulation von Daten in der Tabellenansicht zu unterbinden.
 */
public class NonEditableTableModel extends AbstractTableModel{
	
	private Object[] columns;
    private Object[][] data;
 
    /**
     * Initialisiert die Tabellenwerte und Tabellenüberschriften.
     * @param rowData		Tabellenwerte.
     * @param columnNames	Tabellenüberschriften.
     */
	public NonEditableTableModel(Object[][] rowData, Object[] columnNames) {
		data = rowData;
		columns = columnNames;
	}

	/**
	 * Erlaubt das editieren der ersten Spalte der Tabelle.<br>
	 * Die Erste Spalte wird immer mit "Auswahl" gefüllt.
	 */
	public boolean isCellEditable(int row, int col){
        if(col == 0) 
        	return true;        	
        return false;
		
    }

	/**
	 * Ermittelt die Gesamtzahl der Zeilen und gibt diese zurück.
	 * @return	Gesamtanzahl der Zeilen der Tabelle.
	 */
	public int getRowCount() {
        return data.length;
    }

	/**
	 * Ermittelt die Gesamtanzahl der Spalten und gibt diese zurück.
	 * @return	Gesamtanzahl der Spalten der Tabelle.
	 */
    public int getColumnCount() {
        return columns.length;
    }
    
    /**
     * Gibt den Wert an der angeforderten Position zurück.<br>
     * @param rowIndex		Zeilenindex, von welcher der Wert zurückgegeben werden soll.
     * @param columnIndex	Spaltenindex, von welcher der Wert zurückgegeben werden soll.
     * @return				Wert an der übermittelten Position.
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
    	if(data[0][columnIndex] instanceof Boolean && data[rowIndex][columnIndex] instanceof Models.Table.EmptyObject)
    		return new Boolean(false);
        return data[rowIndex][columnIndex];
    }

    /**
     * Setzt die Tabellendaten und Tabellenüberschriften und informiert das angeschlossene TableView über die Änderung.
     * @param rowData		Neue Tabellendaten.
     * @param columnNames	Neue Tabellenüberschriften.
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
