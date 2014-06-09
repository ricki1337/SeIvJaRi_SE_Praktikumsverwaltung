package Controller;

import java.awt.event.MouseEvent;

import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContacts;
import Models.Filter.IntFilter;
import Praktikumsverwaltung.Praktikumsverwaltung;

public class CompanySingle extends SingleController{
	
	private Models.Model contactModel;
	
	private String srcSqlQuery = "select " +
									SqlTableCompanies.TableNameDotId + " as ID, " +
									SqlTableCompanies.TableNameDotFirmenname + " as Name, " +
									SqlTableCompanies.TableNameDotStrasse + " as Strasse, " +
									SqlTableCompanies.TableNameDotPLZ + " as PLZ, " +
									SqlTableCompanies.TableNameDotOrt + " as Ort, " +
									SqlTableCompanies.TableNameDotLand + " as Land, " +
									SqlTableCompanies.TableNameDotTelefonnummer + " as 'Tel.', " +
									SqlTableCompanies.TableNameDotBemerkung + " as Bemerkung " +
								"from "+ SqlTableCompanies.tableName;
							
	private String contactSrcSqlQuery = "select " +
											SqlTableContacts.TableNameDotId + " as ID, " +
											SqlTableContacts.TableNameDotName + " as Name, " +
											SqlTableContacts.TableNameDotTelefonnummer + " as 'Tel.', "+
											SqlTableContacts.TableNameDotBemerkung + " as Bemerkung, "+
											SqlTableContacts.TableNameDotZuordnungFirma + " as FirmenID " +
										" from " +
											SqlTableContacts.tableName;
	
	Views.CompanySingle view;
	
	public CompanySingle(Object primaryKeys){
		super();
		Models.Model model = new Models.Model(srcSqlQuery,SqlTableCompanies.tableName,SqlTableCompanies.TableNameDotPrimaryKey); 
		
		if(primaryKeys instanceof Object[]){		
			for(Object item:(Object[])primaryKeys){
				model.setOrFilter(SqlTableCompanies.TableNameDotId, new IntFilter(Integer.parseInt(item.toString())));
			}
		} else{
			model.setAndFilter(SqlTableCompanies.TableNameDotId, new IntFilter(Integer.parseInt(primaryKeys.toString())));
		}
		model.setResult();
		
		setModel(model);
		
		setContactModel();
		setView((view = new Views.CompanySingle(this)));
		
	}
	
	@Override
	public void display() {
		view.display();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getComponent().getName()){
		case "next": 	view.gotoNext();
						setContactModel();
						break;
		case "save": 	model.updateDatabase();
						break;
		case "previus": view.gotoPrevius();
						setContactModel();
						break;
			
		case "nextContact": 	contactModel.nextRow();
								((Views.CompanySingle)view).refreshContacts();
								break;
		case "previusContact": 	contactModel.previusRow();
								((Views.CompanySingle)view).refreshContacts();
								break;
		case "newContact": 		ContactEmptySingle newEmptyFrame;
								try {
									newEmptyFrame = new ContactEmptySingle(contactModel.tableRowData.getStringValueFromPosition(contactModel.rowPosition, "FirmenID"));
									Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								
								break;
		case "modifyContact": 	ContactSingle newFrame = new ContactSingle(contactModel.tableRowData.getValueFromPosition(contactModel.rowPosition, "ID"));
								Praktikumsverwaltung.addFrameToForeground(newFrame);
								break;
		}
	}

	private void setContactModel(){
		try {
			contactModel = new Models.Model(SqlTableContacts.tableName, SqlTableContacts.TableNameDotPrimaryKey);
			contactModel.setSrcQuery(contactSrcSqlQuery);
			contactModel.setAndFilter(SqlTableContacts.TableNameDotZuordnungFirma, new IntFilter(Integer.parseInt(model.tableRowData.getStringValueFromPosition(model.rowPosition, "ID"))));
			contactModel.setResult();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public Models.Model getContactModel(){
		return contactModel;
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
