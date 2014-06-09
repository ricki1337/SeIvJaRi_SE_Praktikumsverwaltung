package Controller;

import java.awt.event.MouseEvent;

import Models.Datenbank.SqlTableCompanies;

public class CompanieEmptySingle extends SingleController{
	
	Views.CompanieEmptySingle view;
	
	public CompanieEmptySingle(){
		setModel(new Models.Model(SqlTableCompanies.tableName,SqlTableCompanies.TableNameDotPrimaryKey));
		setView((view = new Views.CompanieEmptySingle(this)));
	}

	@Override
	public void display() {
		view.display();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getComponent().getName()){
			case "save": 	model.insertIntoDatabase();
							view.clearAllFields();
							break;
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
