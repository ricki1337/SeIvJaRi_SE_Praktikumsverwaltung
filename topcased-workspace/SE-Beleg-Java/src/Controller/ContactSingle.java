package Controller;

import java.awt.event.MouseEvent;

import Models.Datenbank.SqlTableContacts;
import Models.Datenbank.SqlTableProfs;
import Models.Filter.StringFilter;

public class ContactSingle extends SingleController{
	private String srcSqlQuery = "select " +
									SqlTableContacts.TableNameDotId + " as ID, " +
									SqlTableContacts.TableNameDotName + " as Name, " +
									SqlTableContacts.TableNameDotTelefonnummer + " as 'Tel.', "+
									SqlTableContacts.TableNameDotBemerkung + " as Bemerkung, "+
									SqlTableContacts.TableNameDotZuordnungFirma + " as FirmenID " +
								" from " +
									SqlTableContacts.tableName;
	
	Views.ContactSingle view;
	
	public ContactSingle(){
		setModel(new Models.Model(SqlTableContacts.tableName,SqlTableContacts.TableNameDotPrimaryKey));
		setView((view = new Views.ContactSingle(this)));
	}
	
	
	public ContactSingle(Object primaryKeys){
		super();
		Models.Model model = new Models.Model(srcSqlQuery,SqlTableContacts.tableName,SqlTableContacts.TableNameDotPrimaryKey);
		
		if(primaryKeys instanceof Object[]){		
			for(Object item:(Object[])primaryKeys){
				model.setOrFilter(SqlTableContacts.TableNameDotId, new StringFilter(item.toString()));
			}
		} else{
			model.setAndFilter(SqlTableContacts.TableNameDotId, new StringFilter(primaryKeys.toString()));
		}
		model.setResult();
		setModel(model);
		setView((view = new Views.ContactSingle(this)));
	}
	
	@Override
	public void display() {
		view.display();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getComponent().getName()){
		case "next": 	view.gotoNext();
						break;
		case "save": 	model.updateDatabase();
						break;
		case "previus": view.gotoPrevius();
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
