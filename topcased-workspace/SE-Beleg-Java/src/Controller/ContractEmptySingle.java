package Controller;

import java.awt.event.MouseEvent;

public class ContractEmptySingle extends SingleController implements changeableController{
	
	Views.ContractEmptySingle view;
	
	public ContractEmptySingle(){
		setModel(new Models.ContractSingle());
		setView((view = new Views.ContractEmptySingle(this)));
	}

	@Override
	public void display() {
		view.display();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getComponent().getName()){
			case "save": 			model.insertIntoDatabase();
									view.clearAllFields();
									break;
									
			case "modifyCompany": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new CompanieSingle(view.getValueFromCurrentItem("FirmenID")));
									break;
			case "addCompany":							
			case "changeCompany": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new CompanieListToContract(this));
									break;
			
			case "modifyStudent": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new StudentSingle(view.getValueFromCurrentItem("Matrikelnr")));
									break;
			case "addStudent":							
			case "changeStudent": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new StudentListToContract(this));
									break;
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void change(String valueName, Object value) {
		// TODO Auto-generated method stub
		if(valueName.equals("company")){
			view.setCompany((String)value);
		}
		
		if(valueName.equals("student")){
			view.setStudent((String)value);
		}
			
	}

}
