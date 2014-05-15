package Views;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import Views.GuiElemente.ListBottomMenu;
import Views.GuiElemente.ListBottomMenuForSelection;
import Views.GuiElemente.ListTopMenu;
import Views.Table.NonEditableTableModel;
import Views.Table.TableColumnNames;
import Views.Table.TableData;

import Controller.*;

public abstract class ListView extends View{

		private TableData tableRowData;
		private JTable table;
		private NonEditableTableModel tableModel;
		
		public ListView(ListController controller){
			super(controller);
		}
		
		protected void addListTopMenu(){
			GridBagConstraints gbc = getGridBagConstraint();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			ListTopMenu topMenu = new ListTopMenu((EventListener) controller,this);
			
			addComponentToView(topMenu);
		}
		
		protected void addListBottomMenu(){
			GridBagConstraints gbc = getGridBagConstraint();
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			ListBottomMenu bottomMenu = new ListBottomMenu((EventListener) controller);
			
			addComponentToView(bottomMenu);
		}
		
		protected void addListBottomMenuForSelection(){
			GridBagConstraints gbc = getGridBagConstraint();
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			ListBottomMenuForSelection bottomMenu = new ListBottomMenuForSelection((EventListener) controller);
			
			addComponentToView(bottomMenu);
		}
		
		public void setFlag(){
			if(table.getSelectedColumn() != 0) return;
			boolean presentValue = (boolean) tableRowData.getValueFromPosition(table.getSelectedRow(), "Auswahl");
			tableRowData.setValueAtPosition(getSelectedRow(), "Auswahl", !presentValue);
			
			table.repaint();
		}
		
		public void setFlagOnSelectedRow(){
			if(table.getSelectedRows().length == 0) return;
			for(int row:getSelectedRows()){
				boolean presentValue = (boolean) tableRowData.getValueFromPosition(row, "Auswahl");
				tableRowData.setValueAtPosition(row, "Auswahl", !presentValue);
			}
			table.repaint();
		}
		
		protected void refreshListTableWithDataFromModel(){
			refreshListTableWithDataFromModel(tableRowData.getRowCount());
		}
		
		protected void refreshListTableWithDataFromModel(int newRowCount){
			tableRowData = new TableData(model.getResult(),newRowCount);
			
			tableRowData.addColumnAtBegin("Auswahl", (boolean)false);

			tableModel.setDataAndColumnNames(tableRowData.getTableData(), tableRowData.getColumnNames());
			tableModel.fireTableDataChanged();
			
			table.repaint();
		}
		
		protected void addListTableWithDataFromModel(){
			GridBagConstraints gbc = getGridBagConstraint();
			ResultSet tmp = model.getResult();
			
			tableRowData = new TableData(tmp);
			
			tableRowData.addColumnAtBegin("Auswahl", (Boolean)false);
			tableModel = new NonEditableTableModel(tableRowData.getTableData(), tableRowData.getColumnNames());
			table = new JTable(tableModel);
			
			table.setName("table");
			table.setAutoCreateRowSorter(true);
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			table.addMouseListener((ListController)controller);
			
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						
			table.setAutoscrolls(true);
			table.setFillsViewportHeight(true);
			
			
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.weightx = 1.0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			
			addComponentToView(table.getTableHeader());
			
			gbc.weightx = 1.0;
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			

			addComponentToView(scrollPane);
		}
		
		public Object getColumnValueFromSelectedRow(String columnName){
			return tableRowData.getValueFromPosition(getSelectedRow(), columnName);
		}
		
		public Object[] getColumnValuesFromSelectedRows(String columnName){
			Object[] returnValues = new Object[table.getSelectedRowCount()];
			int rowCount = 0;
			int column = table.convertColumnIndexToModel(tableRowData.getColumnIndex(columnName));
			for(int selectedRow:table.getSelectedRows()){
				returnValues[rowCount] = table.getValueAt(table.convertRowIndexToModel(selectedRow),column);
				rowCount++;
			}
			return returnValues;
		}
		
		private int getSelectedRow(){
			int selectedRow = table.getSelectedRow();
			int rowIdInTableModel = table.convertRowIndexToModel(selectedRow);
			return rowIdInTableModel;
		}
		
		private int[] getSelectedRows(){
			int[] selectedRows = new int[table.getSelectedRowCount()]; 
			int rowCount = 0;
			for(int selectedRow:table.getSelectedRows()){
				selectedRows[rowCount] = table.convertRowIndexToModel(selectedRow);
				rowCount++;
			}
			return selectedRows;
		}
		
		public void setTableRowsCount(int rowsCount){
			refreshListTableWithDataFromModel(rowsCount);
		}

}
