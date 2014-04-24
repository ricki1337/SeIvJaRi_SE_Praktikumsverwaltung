package Views;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import Views.Elemente.ListBottomMenu;
import Views.Elemente.NonEditableTable;
import Views.Elemente.TableColumnNames;
import Views.Elemente.ListTopMenu;

import Controller.*;

public abstract class ListView extends View{

		private Object[][] tableRowData;
		private TableColumnNames columnNames;
		private JTable table;
		
		
		public ListView(ListController controller){
			super(controller);
		}
		
		protected void addListTopMenu(){
			GridBagConstraints gbc = getGridBagConstraint();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1.0;
			addComponentToView(new ListTopMenu((EventListener) controller));
		}
		
		protected void addListBottomMenu(){
			GridBagConstraints gbc = getGridBagConstraint();
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1.0;
			addComponentToView(new ListBottomMenu((EventListener) controller));
		}
		
		public void setFlag(){
			if(table.getSelectedColumn() != 0) return;
			
			tableRowData[table.getSelectedRow()][0] = tableRowData[table.getSelectedRow()][0].equals(true)?false:true;
			
			table.repaint();
		}
		
		public void setFlagOnSelectedRow(){
			if(table.getSelectedRows().length == 0) return;
			for(int index:table.getSelectedRows()){
				System.out.println(index);
				tableRowData[index][0] = tableRowData[index][0].equals(true)?false:true;
			}
			table.repaint();
		}
		
		protected void addListTableWithDataFromModel(){
			GridBagConstraints gbc = getGridBagConstraint();
			ResultSet sqlResultSet = model.getResult();
			java.sql.ResultSetMetaData meta;
			try {
				meta = sqlResultSet.getMetaData();
			
				sqlResultSet.last();

				tableRowData = new Object[sqlResultSet.getRow()][meta.getColumnCount()+1];

				columnNames = new TableColumnNames(meta);
				columnNames.addColumn("Auswahl", 0);

				sqlResultSet.first();
				

				int row=0;
				do{
					tableRowData[row][0] = Boolean.FALSE;
					for(int column = 0;column<meta.getColumnCount();column++){
						tableRowData[row][column+1] = sqlResultSet.getObject(column+1);
					}
					row++;
				}while(sqlResultSet.next());
				
								
				table = new JTable(new NonEditableTable(tableRowData, columnNames.getColumnNames()));
				table.setName("table");
				table.setAutoCreateRowSorter(true);
				table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				
				table.addMouseListener((ListController)controller);
				
				JScrollPane scrollPane = new JScrollPane(table);
				table.setFillsViewportHeight(true);
				
				
				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.weightx = 1.0;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				setConstraintToComponent(table.getTableHeader());
				addComponentToView(table.getTableHeader());
				
				gbc.weightx = 1.0;
				gbc.gridx = 0;
				gbc.gridy = 2;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				setConstraintToComponent(table);

				addComponentToView(table);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		protected void deleteTable(){
			table.removeAll();
			deleteComponentFromView(table);
		}
		
		public Object getMatrikelnrFromSelectedRowOnTable(){
			int selectedRow = table.getSelectedRow();
			int matrikelnrIndex = columnNames.getColumnIndex("Matrikelnr");
			return tableRowData[selectedRow][matrikelnrIndex];
		}
}
