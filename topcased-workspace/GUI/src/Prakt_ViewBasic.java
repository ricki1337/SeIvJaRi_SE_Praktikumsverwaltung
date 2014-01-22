import javax.swing.*;

import java.awt.*;

import javax.swing.event.*;
import java.awt.event.*;

public abstract class Prakt_ViewBasic  extends JInternalFrame{
	//erweiterte JTable, um die Datensatzauswahl per doppelklick zu ermöglichen
	class ExtJTable extends JTable{
		public ExtJTable(Object[][] rowData, Object[] columnNames) {
			super(rowData,columnNames);
		}

		public boolean isCellEditable(int row, int col){
	        return false;
	    }
		
	}
	//mouselistener für doppelklick
	class MySelectionListener extends MouseAdapter{
		JTable table;
		public MySelectionListener(JTable table){
			this.table = table;
			
		}
		public void mouseClicked(MouseEvent e){
			if (e.getClickCount() == 2) { 
				getSelectedItemIndex(table.getSelectedRow());
			}
		}

	}
	
	
	JPanel Head;//enhält navigationselemente
	JPanel Body;//enthält info oder tabellen...
	Object TableHeader[];//enthält die tabellenüberschriften
	Object TableData[][];//enthält die Daten in listenform
	ExtJTable table;
	
	//aktualisiere den datenbestand
	public void refreshTable(Object Data[][], Object Header[]){}
	
	//wird vom mouselistener bei doppelklick auf einen datensatz gerufen
	public abstract void getSelectedItemIndex(int ItemIndex);
}
