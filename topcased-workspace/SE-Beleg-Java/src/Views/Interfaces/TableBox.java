package Views.Interfaces;

public interface TableBox{

	public Object[] getColumnValuesFromFlaggedRows(String columnName);

	public int[] getFlaggedRows();

	public int getFlaggedRowCount();

	public Object[] getColumnValuesFromSelectedRows(String columnName);

	public Object getColumnValueFromSelectedRow(String string);

	public int[] getSelectedRows();

	public int getSelectedRow();

	public int getSelectedRowCount();

	public void setFlagOnSelectedRows();

	public void setFlag();
	
	public void setColumnSelectionToOnlyOne();
	
	public void setColumnSelectionToMulti();

}
