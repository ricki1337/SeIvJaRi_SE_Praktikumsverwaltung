package Controller;

import java.awt.event.ActionEvent;

public class Student extends Controller{
	
	public Student(){
		super();
		setModel(new Models.Student("select * from student"));
		setView(new Views.Student(this));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		view.display();
		pack();
		setVisible(true);
	}

}
