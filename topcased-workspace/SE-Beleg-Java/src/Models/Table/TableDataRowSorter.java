package Models.Table;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TableDataRowSorter<M extends TableModel> extends TableRowSorter{
	
	public TableDataRowSorter(){
		super();
	}
	
	public TableDataRowSorter(M model){
		super(model);
	}
	
	@Override
	public void sort(){}
}
