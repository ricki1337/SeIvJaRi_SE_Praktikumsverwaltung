package Controller;

import java.awt.event.MouseEvent;

public class ContractSingle extends SingleController implements ChangeableController{
	
	Views.ContractSingle view;
	
	public ContractSingle(){
		setModel(new Models.ContractSingle());
		setView((view = new Views.ContractSingle(this)));
	}
	
	
	public ContractSingle(Object primaryKeys){
		super();
		String sqlFilter = new String();
		
		if(primaryKeys instanceof Object[]){		
			int count = 0;
			for(Object item:(Object[])primaryKeys){
				if(count == 0) sqlFilter = item.toString();
				else sqlFilter += " ,"+item.toString();
				count++;
			}
		} else{
			sqlFilter = primaryKeys.toString();
		}
		
		String query = new String("SELECT c.id as ID," +
				"s.matrnr as 'Matrikelnr.'," +
				" s.firstname as Vorname," +
				" s.name as Nachname," +
				" s.Email as 'E-Mail'," +
				" s.StGr as Studiengruppe," +
				" co.id as FirmenID," +
				" co.Name as Firmenname," +
				" co.Street as Strasse," +
				" co.ZIP as PLZ," +
				" co.Town as Ort," +
				" Prof as Betreuer," +
				" type as Typ," +
				" begpr as 'beginnt am'," +
				" endpr as 'endet am'," +
				" bericht as Bericht," +
				" zeugnis as Zeugnis," +
				" empfehlung as Empfehlung" +
				" FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id" +
				" where c.id IN (" + sqlFilter +")");
		
		
		setModel(new Models.ContractSingle(query));
		setView((view = new Views.ContractSingle(this)));
	}

	@Override
	public void display() {
		view.display();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getComponent().getName()){
		case "next": 			view.gotoNext();
								break;
		case "save": 			model.updateDatabase();
								break;
		case "previus": 		view.gotoPrevius();
								break;
		case "modifyCompanie": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new CompanieSingle(view.getValueFromCurrentItem("FirmenID")));
								break;
		case "changeCompanie": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new CompanieListToContract(this));
								break;
		case "modifyStudent": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new StudentSingle(view.getValueFromCurrentItem("Matrikelnr.")));
								break;
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
		if(valueName.equals("company"))
			((Models.ContractSingle)model).changeCompanie(view.getValueFromCurrentItem("ID"),(String)value);
		if(valueName.equals("student"))
			((Models.ContractSingle)model).changeStudent(view.getValueFromCurrentItem("ID"),(String)value);
	}

}
