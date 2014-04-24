package Views;

import java.awt.GridBagConstraints;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import Controller.Controller;

public class SingleView extends View{
	
	public SingleView(Controller controller){
		super(controller);
	}
	
	/**
	 * erzeugen eines textfield mit label
	 * das textfield wird dabei in einem array referenziert
	 * TODO
	 * nötige info zum zeichnen?
	 * 	position
	 * 	labelname
	 * 	Sql Spalte
	 * 	Wert
	 * 
	 *
	 */
	protected void setTextfield(String labelText,String sqlSpaltenName,int posX, int posY){
		ResultSet result = model.getResult();
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX,posY);
		
		JTextField f;
		try {
			//zum ersten Eintrag des ResultSet springen
			result.first();
			//den Wert der Spalte in das jTextField schreiben
			f = new JTextField(result.getString(sqlSpaltenName));
			//das JTextField neben dem JLabel positionieren
			gbc.gridx = posX+1;
			//GridBagConstraints registrieren
			setConstraintToComponent(f);
			//das JTextField in die Liste der Informationstragenden Elemente einhängen
			listOfallComponentsOnView.add(f);
			//die parallele Liste für die Sql Spalte
			listOfComponentsToSqlReference.add(sqlSpaltenName);
			//das JTextField im JPanel registrieren
			addComponentToView(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * erzeugen einer textbox mit label
	 * die textbox wird dabei in einem array referenziert
	 * TODO
	 * nötige info zum zeichnen?
	 * 	position
	 * 	labelname
	 * 	Sql Spalte
	 * 	Wert
	 *  
	 * wer implementiert den actionListener? -> der controller
	 */
	protected void setTextbox(String labelText,String sqlSpaltenName,int posX, int posY){}
	
	
	/**
	 * erzeugen einer checkbox mit label
	 * die checkbox wird dabei in einem array referenziert
	 * TODO
	 * nötige info zum zeichnen?
	 * 	position
	 * 	labelname
	 * 	Sql Spalte
	 * 	Wert
	 * 
	 * wer implementiert den actionListener? -> der controller
	 */
	protected void setCheckbox(String labelText,String sqlSpaltenName,int posX, int posY){}

	@Override
	public void setElemente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelHasChanged() {
		// TODO Auto-generated method stub
		
	}
}
