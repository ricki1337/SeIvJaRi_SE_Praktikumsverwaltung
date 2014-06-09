package Views.Interfaces;

import java.awt.event.MouseListener;

public interface EditBoxCtrl extends BasicBoxCtrl{
	public void setElementsForNewData();
	public String getStringValueForBoxElementEdit(String sqlAlias);
	public int getIntValueForBoxElementEdit(String sqlAlias);
	public boolean getBooleanValueForBoxElementEdit(String sqlAlias);
}
