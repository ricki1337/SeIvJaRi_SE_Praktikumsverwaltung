package Views.GuiElemente;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JPanel;

import ConfigParser.Debug;
import Controller.ProfSingle;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.Interfaces.BasicBox;
import Views.Interfaces.TableBoxCtrl;
import Views.Table.NonEditableTableModel;

public class BoxElementTable extends JPanel implements BasicBox, MouseListener{

		private javax.swing.JScrollPane jScrollPane1;
	    
	    private javax.swing.JTable jt_table;
	    private NonEditableTableModel tableModel;
	    private java.awt.ScrollPane scrollPane1;
	    private GroupLayout layout;
	    
	    private TableBoxCtrl controller;
    
    
    public BoxElementTable(TableBoxCtrl controller) {
    	this.controller = controller;
        initComponents();
        setComponentNames();
        setComponentValues();
        setComponentEventHandler();
        setToolTip();
    }

    @Override
	public void initComponents() {

        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_table = new javax.swing.JTable();
        jt_table.setAlignmentY(Component.TOP_ALIGNMENT);
        jt_table.setAlignmentX(Component.LEFT_ALIGNMENT);
        jt_table.setAutoCreateRowSorter(true);
        jt_table.setDoubleBuffered(true);
        jt_table.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jt_table);
        
        scrollPane1.add(jScrollPane1);

        layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 300, GroupLayout.DEFAULT_SIZE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 300, GroupLayout.DEFAULT_SIZE)
        			.addContainerGap())
        );
        this.setLayout(layout);
    }
    
    @Override
	public void setComponentNames() {
    	jt_table.setName("table");
	}

	@Override
	public void setComponentValues() {
		tableModel = new NonEditableTableModel(controller.getTableData(), controller.getTableHeader());
        jt_table.setModel(tableModel);
		
	}

    public void setComponentEventHandler() {
    	jt_table.addMouseListener(this);
	}

	@Override
	public void refreshContent() {
		tableModel.setDataAndColumnNames(controller.getTableData(), controller.getTableHeader());
		jt_table.printAll(jt_table.getGraphics());
	}
	
	public void setFlag(){
		if(jt_table.getSelectedColumn() != 0) return;
		boolean currentValue = (boolean) controller.getValueFromPosition(jt_table.getSelectedRow(), "Auswahl");
		controller.setValueAtPosition(getSelectedRow(), "Auswahl", !currentValue);
		jt_table.repaint();
	}
	
	public void setFlagOnSelectedRows(){
		if(jt_table.getSelectedRows().length == 0) return;
		for(int row:getSelectedRows()){
			boolean currentValue = (boolean) controller.getValueFromPosition(row, "Auswahl");
			controller.setValueAtPosition(row, "Auswahl", !currentValue);
		}
		jt_table.repaint();
	}
	
	public int getSelectedRow(){
		int selectedRow = jt_table.getSelectedRow();
		int rowIdInTableModel = jt_table.convertRowIndexToModel(selectedRow);
		return rowIdInTableModel;
	}
	
	public int[] getSelectedRows(){
		int[] selectedRows = new int[jt_table.getSelectedRowCount()]; 
		int rowIndex = 0;
		for(int selectedRow:jt_table.getSelectedRows()){
			selectedRows[rowIndex] = jt_table.convertRowIndexToModel(selectedRow);
			rowIndex++;
		}
		return selectedRows;
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	public Object getColumnValueFromSelectedRow(String string) {
		return controller.getValueFromPosition(jt_table.getSelectedRow(), string);
	}
	
	public int getFlaggedColumnCount(){
		int flaggedColumnCount = 0;
		for(int index=0;index<jt_table.getRowCount();index++){
			if((boolean) controller.getValueFromPosition(index, "Auswahl")){
				flaggedColumnCount++;
			}
		}
		
		return flaggedColumnCount;
	}
	
	public int[] getFlaggedColumns(){
		int[] flaggedColumns = new int[getFlaggedColumnCount()];
		int flaggedColumnsCount = 0;
		for(int index=0;index<jt_table.getRowCount();index++){
			if((boolean) controller.getValueFromPosition(index, "Auswahl")){
				flaggedColumns[flaggedColumnsCount] = index;
				flaggedColumnsCount++;
			}
		}
		
		return flaggedColumns;
	}
	
	public Object[] getColumnValuesFromSelectedRows(String columnName){
		Object[] returnValues = new Object[getFlaggedColumnCount()];
		int rowIndex = 0;
		int column = jt_table.convertColumnIndexToModel(controller.getColumnAliasIndex(columnName));
		//for(int selectedRow:jt_table.getSelectedRows()){
		for(int selectedRow:getFlaggedColumns()){
			returnValues[rowIndex] = jt_table.getValueAt(jt_table.convertRowIndexToModel(selectedRow),column);
			rowIndex++;
		}
		return returnValues;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent().getName() == "table"){
			if (e.getClickCount() == 2) {
				controller.tableRowDoubleClicked();
			
			}else{
				controller.tableRowClicked();
			}
		}
	}
	
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	                       
}
