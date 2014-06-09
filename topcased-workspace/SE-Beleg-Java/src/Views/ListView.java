package Views;

import java.awt.GridBagConstraints;
import java.sql.ResultSet;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import Controller.ListController;
import Views.GuiElemente.ListBottomMenu;
import Views.GuiElemente.ListBottomMenuForSelection;
import Views.GuiElemente.ListTopMenu;
import Views.Table.NonEditableTableModel;
import Views.Table.TableData;

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
			boolean currentValue = (boolean) tableRowData.getValueFromPosition(table.getSelectedRow(), "Auswahl");
			tableRowData.setValueAtPosition(getSelectedRow(), "Auswahl", !currentValue);
			table.repaint();
		}
		
		public void setFlagOnSelectedRow(){
			if(table.getSelectedRows().length == 0) return;
			for(int row:getSelectedRows()){
				boolean currentValue = (boolean) tableRowData.getValueFromPosition(row, "Auswahl");
				tableRowData.setValueAtPosition(row, "Auswahl", !currentValue);
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
			
			tableRowData = new TableData(model.getResult());			
			tableRowData.addColumnAtBegin("Auswahl", (Boolean)false);
			
			tableModel = new NonEditableTableModel(tableRowData.getTableData(), tableRowData.getColumnNames());
			
			table = new JTable(tableModel);
			table.setName("table");
			table.setAutoCreateRowSorter(true);
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			table.addMouseListener((ListController)controller);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setAutoscrolls(true);
			table.setFillsViewportHeight(true);
			
			
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
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
		
		private int getSelectedRow(){
			int selectedRow = table.getSelectedRow();
			int rowIdInTableModel = table.convertRowIndexToModel(selectedRow);
			return rowIdInTableModel;
		}
		
		public Object[] getColumnValuesFromSelectedRows(String columnName){
			Object[] returnValues = new Object[table.getSelectedRowCount()];
			int rowIndex = 0;
			int column = table.convertColumnIndexToModel(tableRowData.getColumnAliasIndex(columnName));
			for(int selectedRow:table.getSelectedRows()){
				returnValues[rowIndex] = table.getValueAt(table.convertRowIndexToModel(selectedRow),column);
				rowIndex++;
			}
			return returnValues;
		}
		
		
		
		private int[] getSelectedRows(){
			int[] selectedRows = new int[table.getSelectedRowCount()]; 
			int rowIndex = 0;
			for(int selectedRow:table.getSelectedRows()){
				selectedRows[rowIndex] = table.convertRowIndexToModel(selectedRow);
				rowIndex++;
			}
			return selectedRows;
		}
		
		public void setTableRowsCount(int rowsCount){
			refreshListTableWithDataFromModel(rowsCount);
		}
		
		@Override
		public void modelHasChanged() {
			refreshListTableWithDataFromModel();
		}

		@Override
		public Object[][] getInputValues() {
			return null;
		}
		
		@Override
		public String getValueFromCurrentItem(String sqlColumnName) {
			return null;
		}
		
		@Override
		public void setElemente() {
			addListTopMenu();
			addListBottomMenu();
			addListTableWithDataFromModel();
		}
		
		//abstract public void getExtendedSearchInputFields(JPanel target);
}
