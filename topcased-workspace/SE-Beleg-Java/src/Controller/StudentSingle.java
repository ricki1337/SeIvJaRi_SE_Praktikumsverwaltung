package Controller;

import java.awt.event.MouseEvent;

public class StudentSingle extends SingleController{
	
	public StudentSingle(){
		super();
		setModel(new Models.Student("select * from student"));
		setView(new Views.SingleStudent(this));
	}
	
	
	public StudentSingle(String sqlWhereQuery){
		super();
		setModel(new Models.Student("select * from student " + sqlWhereQuery));
		setView(new Views.SingleStudent(this));
	}

	@Override
	public void display() {
		view.display();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

}
