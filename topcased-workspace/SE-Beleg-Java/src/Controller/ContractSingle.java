package Controller;

import java.awt.event.MouseEvent;

import Controller.Interfaces.ChangeableController;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableStudent;

public class ContractSingle extends SingleController implements ChangeableController{
	
	Views.ContractSingle view;
	
	public ContractSingle(){
		setModel(new Models.ContractSingle(SqlTableContracts.tableName,SqlTableContracts.TableNameDotPrimaryKey));
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
		
		String query = new String("SELECT " +
				SqlTableContracts.TableNameDotId + " as ID," +
				SqlTableStudent.TableNameDotMatrikelNummer + " as 'Matrikelnr.'," +
				SqlTableStudent.TableNameDotVorname + " as Vorname, " +
				SqlTableStudent.TableNameDotNachname + " as Nachname, " +
				SqlTableStudent.TableNameDotEMail + " as 'E-Mail', " +
				SqlTableStudent.TableNameDotStudiengruppe + " as Studiengruppe, " +
				SqlTableCompanies.TableNameDotId + " as FirmenID, " +
				SqlTableCompanies.TableNameDotFirmenname + " as Firmenname, " +
				SqlTableCompanies.TableNameDotStrasse + " as Strasse, " +
				SqlTableCompanies.TableNameDotPLZ + " as PLZ, " +
				SqlTableCompanies.TableNameDotOrt + " as Ort, " +
				SqlTableContracts.TableNameDotFK_Betreuer + " as Betreuer, " +
				SqlTableContracts.TableNameDotTyp + " as Typ, " +
				SqlTableContracts.TableNameDotBeginn + " as 'beginnt am', " +
				SqlTableContracts.TableNameDotEnde + " as 'endet am', " +
				SqlTableContracts.TableNameDotBericht + " as Bericht, " +
				SqlTableContracts.TableNameDotZeugnis + " as Zeugnis, " +
				SqlTableContracts.TableNameDotEmpfehlung + " as Empfehlung " +
				"FROM "+ SqlTableContracts.tableName +
					" JOIN " + SqlTableStudent.tableName + " ON " +
						SqlTableContracts.TableNameDotFK_Student + " = " + SqlTableStudent.TableNameDotPrimaryKey + 
					" JOIN " + SqlTableCompanies.tableName + " ON " +
						SqlTableContracts.TableNameDotFK_Firma + " = "+ SqlTableCompanies.TableNameDotPrimaryKey +
				" where " +
					SqlTableContracts.TableNameDotId + " IN (" + sqlFilter +")");
		
		
		setModel(new Models.ContractSingle(query,SqlTableContracts.tableName,SqlTableContracts.TableNameDotPrimaryKey));
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
		case "modifyCompanie": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new CompanySingle(view.getValueFromCurrentItem("FirmenID")));
								break;
		case "changeCompanie": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new CompanyListToContract(this));
								break;
		case "modifyStudent": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new StudentSingle(view.getValueFromCurrentItem("Matrikelnr.")));
								break;
		case "changeStudent": 	Praktikumsverwaltung.Praktikumsverwaltung.addFrameToForeground(new StudentListToContract(this));
								break;
		}
	}

	@Override
	public void change(String valueName, Object value) {
		// TODO Auto-generated method stub
		if(valueName.equals("company"))
			((Models.ContractSingle)model).changeCompany(view.getValueFromCurrentItem("ID"),(String)value);
		if(valueName.equals("student"))
			((Models.ContractSingle)model).changeStudent(view.getValueFromCurrentItem("ID"),(String)value);
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
