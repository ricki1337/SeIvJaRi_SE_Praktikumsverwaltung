package Controller;

import java.awt.event.MouseEvent;

public class ProfSingle extends SingleController{
	
	Views.ProfSingle view;
	
	public ProfSingle(){
		setModel(new Models.ProfSingle());
		setView((view = new Views.ProfSingle(this)));
	}
	
	
	public ProfSingle(Object primaryKeys){
		super();
		String sqlFilter = new String();
		
		if(primaryKeys instanceof Object[]){		
			int count = 0;
			for(Object item:(Object[])primaryKeys){
				if(count == 0) sqlFilter = "'"+ item.toString()+"'";
				else sqlFilter += " ,'"+item.toString()+"'";
				count++;
			}
		} else{
			sqlFilter = "'"+primaryKeys.toString()+"'";
		}
		
		setModel(new Models.ProfSingle("select * from profs where NameID in (" + sqlFilter +")"));
		setView((view = new Views.ProfSingle(this)));
	}
	
	public ProfSingle(String columnName, Object keys){
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
		
		setModel(new Models.ProfSingle("select * from profs where "+ columnName +" in (" + sqlFilter +")"));
		setView((view = new Views.ProfSingle(this)));
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
