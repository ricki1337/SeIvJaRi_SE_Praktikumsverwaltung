package Controller;

import java.awt.event.MouseEvent;

import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;

public class StudentSingle extends SingleController{
	
	Views.StudentSingle view;
	
	private String srcSqlQuery = "select " +
									SqlTableStudent.TableNameDotMatrikelNummer + " as Matrikelnr, " +
									SqlTableStudent.TableNameDotVorname + " as Vorname, " +
									SqlTableStudent.TableNameDotNachname + " as Nachname, " +
									SqlTableStudent.TableNameDotEMail + " as 'E-Mail', " +
									SqlTableStudent.TableNameDotStudiengruppe + " as Studiengruppe, " +
									SqlTableStudent.TableNameDotBemerkung + " as Bemerkung " +
								"from " +
									SqlTableStudent.tableName;
	
	
	public StudentSingle(){
		setModel(new Models.Model(SqlTableStudent.tableName,SqlTableStudent.TableNameDotPrimaryKey));
		setView((view = new Views.StudentSingle(this)));
	}
	
	
	public StudentSingle(Object primaryKeys){
		super();
		Models.Model model = new Models.Model(srcSqlQuery,SqlTableStudent.tableName,SqlTableStudent.TableNameDotPrimaryKey);
				
		if(primaryKeys instanceof Object[]){		
			for(Object item:(Object[])primaryKeys){
				model.setOrFilter(SqlTableStudent.TableNameDotMatrikelNummer, new IntFilter(Integer.parseInt(item.toString())));
			}
		} else{
			model.setAndFilter(SqlTableStudent.TableNameDotMatrikelNummer, new IntFilter(Integer.parseInt(primaryKeys.toString())));
		}
		model.setResult();
		setModel(model);
		setView((view = new Views.StudentSingle(this)));
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
