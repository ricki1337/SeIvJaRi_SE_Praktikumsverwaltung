package Views;

import java.awt.GridBagConstraints;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import Views.Elemente.NonEditableTable;
import Views.Elemente.TopMenu;

import Controller.*;

public abstract class ListView extends View{
		//tabellendaten
		private Object[][] rowData;
		private Object[] columnNames;
		private JTable table;
		
		
		public ListView(ListController controller){
			super(controller);
		}
		
		protected void setTopMenu(){
			GridBagConstraints gbc = getGridBagConstraint();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1.0;
//			addElement(new TopMenu((InputMethodListener)controller));
			addElement(new TopMenu((KeyListener)controller));
		}
		
		/**
		 * setzt die Auswahlhaken in der Tabelle.
		 */
		public void setMarker(boolean value){
			for(int index:table.getSelectedRows()){
				rowData[index][0] = value;
			}
		}
		
		//erzeugt die tabelle mit allen informationen
		protected void setTable(){
			GridBagConstraints gbc = getGridBagConstraint();
			try {
				
				
				ResultSet result = model.getResult();
				java.sql.ResultSetMetaData meta;
				meta = result.getMetaData();

				//ans ende des result gehen
				result.last();
				//das datenarray erstellen
				rowData = new Object[result.getRow()][meta.getColumnCount()+1];
				//Spaltennamenarray erstellen
				columnNames = new Object[meta.getColumnCount()+1];
				//wieder an den anfang des array gehen
				result.first();
				
				//datenarray füllen
				int j=0;
				do{
					rowData[j][0] = Boolean.FALSE;
					for(int i = 0;i<meta.getColumnCount();i++){
						rowData[j][i+1] = result.getObject(i+1);
					}
					j++;
				}while(result.next());
				
				//Spaltenüberschriften setzen
				columnNames[0] = new String("");
				for(int i = 0;i<meta.getColumnCount();i++){
					columnNames[i+1] = meta.getColumnLabel(i+1);
				}
					
				//NonEditableTable table = new NonEditableTable(rowData, columnNames);
				table = new JTable(new NonEditableTable(rowData, columnNames));
				
				//sortierung einschalten
				table.setAutoCreateRowSorter(true);
				
				
				
				//nur einzelne zeilen sind auswählbar
				table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				
				//actionhandler für rowselection registrieren
				table.getSelectionModel().addListSelectionListener((ListSelectionListener) controller);
				
				
				//scrollbar registrieren
				JScrollPane scrollPane = new JScrollPane(table);
				table.setFillsViewportHeight(true);
				
				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.weightx = 1.0;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				
				//header constraint setzen und header dem panel hinzufügen
				setConstraint(table.getTableHeader());
				addElement(table.getTableHeader());
				gbc.weightx = 1.0;
				gbc.gridx = 0;
				gbc.gridy = 2;
				
				//tablebody constraint setzen und dem panel hinzufügen
				setConstraint(table);
				table.addMouseListener((ListController)controller);
				addElement(table);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public abstract void setElemente();
}
