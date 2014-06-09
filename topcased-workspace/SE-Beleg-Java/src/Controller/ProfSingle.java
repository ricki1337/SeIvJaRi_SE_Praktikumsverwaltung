package Controller;

import java.awt.event.MouseEvent;

import Models.Datenbank.SqlTableProfs;
import Models.Filter.StringFilter;

public class ProfSingle extends SingleController{
	private String srcSqlQuery = "select " +
						SqlTableProfs.TableNameDotName + " as Name, " +
						SqlTableProfs.TableNameDotId + " as 'E-Mail' " +
					"from " +
						SqlTableProfs.tableName;
	Views.ProfSingle view;
	
	public ProfSingle(){
		setModel(new Models.Model(SqlTableProfs.tableName,SqlTableProfs.TableNameDotPrimaryKey));
		setView((view = new Views.ProfSingle(this)));
	}
	
	
	public ProfSingle(Object primaryKeys){
		super();
		Models.Model model = new Models.Model(srcSqlQuery,SqlTableProfs.tableName,SqlTableProfs.TableNameDotPrimaryKey);
		
		if(primaryKeys instanceof Object[]){		
			for(Object item:(Object[])primaryKeys){
				model.setOrFilter(SqlTableProfs.TableNameDotId, new StringFilter(item.toString()));
			}
		} else{
			model.setAndFilter(SqlTableProfs.TableNameDotId, new StringFilter(primaryKeys.toString()));
		}
		model.setResult();
		setModel(model);
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
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
