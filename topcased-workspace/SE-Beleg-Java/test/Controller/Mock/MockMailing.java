package Controller.Mock;

public class MockMailing extends Controller.Mailing{

	@Override
	protected void setListModel(){
		model = new MockListModel();
		setModel(model);
	}

}
