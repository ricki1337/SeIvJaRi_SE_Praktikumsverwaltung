package Controller;

import java.awt.event.MouseEvent;

import Models.Datenbank.SqlTableContacts;
import Models.Datenbank.SqlTableProfs;

public class ContactEmptySingle extends SingleController{
	
	Views.ContactEmptySingle view;
	
	/**
	 * TODO
	 * neues anlegen mittels companyid
	 * @param companyId
	 */
	public ContactEmptySingle(String companyId){
		setModel(new Models.Model(SqlTableContacts.tableName,SqlTableContacts.TableNameDotPrimaryKey));
		view = new Views.ContactEmptySingle(this);
		view.setCompany(companyId);
		
		setView(view);
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
