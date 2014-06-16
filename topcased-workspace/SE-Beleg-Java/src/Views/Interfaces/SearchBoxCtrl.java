package Views.Interfaces;





public interface SearchBoxCtrl extends BasicBoxCtrl{
	public void searchFieldChanged();
	public void RecordLimitFieldChanged();
	public void buttonShowExtendedSearchClicked();
	public void buttonAddNewDataClicked();
	
	public int getSqlRecordLimit();
}
