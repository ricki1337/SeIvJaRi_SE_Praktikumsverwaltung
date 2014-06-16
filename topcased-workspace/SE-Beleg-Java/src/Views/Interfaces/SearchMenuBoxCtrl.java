package Views.Interfaces;





public interface SearchMenuBoxCtrl extends BasicBoxCtrl{
	public void searchFieldChanged();
	public void RecordLimitFieldChanged();
	public void buttonShowExtendedSearchClicked();
	
	public int getSqlRecordLimit();
}
