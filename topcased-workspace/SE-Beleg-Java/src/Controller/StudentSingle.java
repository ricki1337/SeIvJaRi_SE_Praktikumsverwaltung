package Controller;

import java.awt.event.MouseEvent;

public class StudentSingle extends SingleController{
	
	Views.StudentSingle view;
	
	public StudentSingle(){
		setModel(new Models.StudentSingle());
		setView((view = new Views.StudentSingle(this)));
	}
	
	
	public StudentSingle(Object primaryKeys){
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
		
		setModel(new Models.StudentSingle("select * from student where MatrNr in (" + sqlFilter +")"));
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
