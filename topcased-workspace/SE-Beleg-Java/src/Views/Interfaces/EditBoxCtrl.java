package Views.Interfaces;


public interface EditBoxCtrl extends BasicBoxCtrl{
	public void setElementsForNewData();
	public String getStringValueForBoxElementEdit(String sqlAlias);
	public int getIntValueForBoxElementEdit(String sqlAlias);
	public boolean getBooleanValueForBoxElementEdit(String sqlAlias);
}
