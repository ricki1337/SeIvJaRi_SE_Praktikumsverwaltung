package Controller.Mock;

import Views.UpdateView;

public class MockListModel extends Models.ListModel{
	
	public MockListModel(){}

	
	public MockListModel(String srcQuery, String tableNameForUpdateOrInsert, String primaryKeyColumnName) {
		super(srcQuery, tableNameForUpdateOrInsert, primaryKeyColumnName);

	}

	@Override
	public void setView(UpdateView view){}

}
