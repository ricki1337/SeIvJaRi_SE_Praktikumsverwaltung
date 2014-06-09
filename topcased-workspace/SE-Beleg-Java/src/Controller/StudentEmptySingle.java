package Controller;

import java.awt.event.MouseEvent;

import Models.Datenbank.SqlTableStudent;

public class StudentEmptySingle extends SingleController{
	
	Views.StudentEmptySingle view;
	
	public StudentEmptySingle(){
		setModel(new Models.Model(SqlTableStudent.tableName,SqlTableStudent.TableNameDotPrimaryKey));
		setView((view = new Views.StudentEmptySingle(this)));
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
