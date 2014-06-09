package Controller;

import java.awt.event.MouseEvent;

import Controller.Interfaces.ChangeableController;
import Models.Datenbank.SqlTableContracts;

public class ContractEmptySingle extends SingleController implements ChangeableController{
	
	Views.ContractEmptySingle view;
	
	public ContractEmptySingle(){
		setModel(new Models.Model(SqlTableContracts.tableName,SqlTableContracts.TableNameDotPrimaryKey));
		setView((view = new Views.ContractEmptySingle(this)));
	}

	@Override
	public void display() {
		view.display();
	}

	
	/**
	 * TODO
	 * views bekommen extra funktionen zum zurückliefern der entsprechenden werte....
	 */
	
	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getComponent().getName()){
			case "save": 			model.insertIntoDatabase();
									view.clearAllFields();
									break;
									
			case "modifyCompany": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new CompanySingle(view.getValueFromCurrentItem("FirmenID")));
									break;
			case "addCompany":							
			case "changeCompany": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new CompanyListToContract(this));
									break;
			
			case "modifyStudent": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new StudentSingle(view.getValueFromCurrentItem("Matrikelnr")));
									break;
			case "addStudent":							
			case "changeStudent": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new StudentListToContract(this));
									break;
			
		}
	}

	@Override
	public void change(String valueName, Object value) {
		if(valueName.equals("company")){
			view.setCompany((String)value);
		}
		
		if(valueName.equals("student")){
			view.setStudent((String)value);
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
