package Controller;

import java.awt.event.MouseEvent;

import Models.Datenbank.SqlTableProfs;

public class ProfEmptySingle extends SingleController{
	
	Views.ProfEmptySingle view;
	
	public ProfEmptySingle(){
		setModel(new Models.Model(SqlTableProfs.tableName,SqlTableProfs.TableNameDotPrimaryKey));
		setView((view = new Views.ProfEmptySingle(this)));
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
