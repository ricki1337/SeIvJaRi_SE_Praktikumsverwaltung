package Controller;

import java.awt.event.MouseEvent;

public class CompanieSingle extends SingleController{
	
	Views.CompanieSingle view;
	
	public CompanieSingle(){
		setModel(new Models.CompanieSingle());
		setView((view = new Views.CompanieSingle(this)));
	}
	
	
	public CompanieSingle(Object primaryKeys){
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
		
		setModel(new Models.CompanieSingle("select * from companies where ID in (" + sqlFilter +")"));
		setView((view = new Views.CompanieSingle(this)));
	}
	
	public CompanieSingle(String columnName, Object keys){
		super();
		String sqlFilter = new String();
	
		
		if(keys instanceof Object[]){		
			int count = 0;
			for(Object item:(Object[])keys){
				if(count == 0) sqlFilter = "'"+item.toString()+"'";
				else sqlFilter += " ,'"+item.toString()+"'";
				count++;
			}
		} else{
			sqlFilter = "'"+keys.toString()+"'";
		}
		
		setModel(new Models.CompanieSingle("select * from companies where "+ columnName +" in (" + sqlFilter +")"));
		setView((view = new Views.CompanieSingle(this)));
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
