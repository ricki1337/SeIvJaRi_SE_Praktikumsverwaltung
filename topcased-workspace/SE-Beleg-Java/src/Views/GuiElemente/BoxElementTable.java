package Views.GuiElemente;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.TableModel;

import ConfigParser.Debug;
import Models.Table.NonEditableTableModel;
import Models.Table.TableDataRowSorter;
import Views.Interfaces.BasicBox;
import Views.Interfaces.TableBoxCtrl;

public class BoxElementTable extends JPanel implements BasicBox, MouseListener{

		private javax.swing.JScrollPane jScrollPane1;
	    
	    private javax.swing.JTable jt_table;
	    private NonEditableTableModel tableModel;

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


        jScrollPane1 = new javax.swing.JScrollPane();
        jt_table = new javax.swing.JTable();
        jt_table.setAlignmentY(Component.TOP_ALIGNMENT);
        jt_table.setAlignmentX(Component.LEFT_ALIGNMENT);
        jt_table.setAutoCreateRowSorter(true);
        jt_table.setDoubleBuffered(true);
        jt_table.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jt_table);

        layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
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
        jt_table.setRowSorter(new TableDataRowSorter<TableModel>(tableModel));
                
        jt_table.getRowSorter().addRowSorterListener(
                new RowSorterListener() {

                    @Override
                    public void sorterChanged(RowSorterEvent e) {
                    	List<? extends SortKey> test = jt_table.getRowSorter().getSortKeys();
                    	controller.setOrderByColumn(test.get(0).getColumn(),test.get(0).getSortOrder());
                    }
                });
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
	
	

	@Override
	public JComponent getJComponent() {
		return this;
	}

	public int getSelectedRowCount(){
		return jt_table.getSelectedRowCount();
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
	
	public Object getColumnValueFromSelectedRow(String string) {
		return controller.getValueFromPosition(jt_table.getSelectedRow(), string);
	}
	
	public Object[] getColumnValuesFromSelectedRows(String columnName){
		Object[] returnValues = new Object[getSelectedRowCount()];
		int rowIndex = 0;
		int column = jt_table.convertColumnIndexToModel(controller.getColumnAliasIndex(columnName));
		for(int selectedRow:getSelectedRows()){
			returnValues[rowIndex] = jt_table.getValueAt(jt_table.convertRowIndexToModel(selectedRow),column);
			rowIndex++;
		}
		return returnValues;
	}
	
	public int getFlaggedRowCount(){
		int flaggedColumnCount = 0;
		for(int index=0;index<jt_table.getRowCount();index++){
			if((boolean) controller.getValueFromPosition(index, "Auswahl")){
				flaggedColumnCount++;
			}
		}
		
		return flaggedColumnCount;
	}
	
	public int[] getFlaggedRows(){
		int[] flaggedRows = new int[getFlaggedRowCount()];
		int flaggedRowsCount = 0;
		for(int index=0;index<jt_table.getRowCount();index++){
			if((boolean) controller.getValueFromPosition(index, "Auswahl")){
				flaggedRows[flaggedRowsCount] = index;
				flaggedRowsCount++;
			}
		}
		
		return flaggedRows;
	}
	
	public Object[] getColumnValuesFromFlaggedRows(String columnName){
		Object[] returnValues = new Object[getFlaggedRowCount()];
		int rowIndex = 0;
		int column = jt_table.convertColumnIndexToModel(controller.getColumnAliasIndex(columnName));
		for(int flaggedRow:getFlaggedRows()){
			returnValues[rowIndex] = jt_table.getValueAt(jt_table.convertRowIndexToModel(flaggedRow),column);
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
