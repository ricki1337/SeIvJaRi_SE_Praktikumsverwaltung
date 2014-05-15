package Views;

import java.awt.GridBagConstraints;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.Controller;
import Views.GuiElemente.AddButton;
import Views.GuiElemente.SingleBottomMenu;

public abstract class EmptySingleView extends View{
	
	public EmptySingleView(Controller controller){
		super(controller);
	}

	public void clearAllFields(){
		for(JComponent comp:listOfallComponentsOnView){
			if(comp instanceof JTextField)	
				((JTextField) comp).setText("");
		
			if(comp instanceof JCheckBox){	
				((JCheckBox) comp).setSelected(false);
			}
			
			if(comp instanceof JTextArea)	
				((JTextArea) comp).setText("");
			}
	}
	
	
	protected void addTextfieldWithSqlReference(String labelText,String sqlSpaltenName,int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX,posY);
		
		JTextField f;
		
		try {
			f = new JTextField();
			f.setColumns(20);
			
			gbc.gridx = posX+1;

			addComponentToView(f,sqlSpaltenName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void addTextAreaWithSqlReference(String labelText,String sqlSpaltenName,int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX,posY);
		
		JTextArea f;
		
		try {
			f = new JTextArea();
			f.setColumns(20);
			f.setRows(5);
			
			gbc.gridx = posX+1;

			addComponentToView(f,sqlSpaltenName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void addCheckboxWithSqlReference(String labelText,String sqlSpaltenName,int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX+1,posY);
		
		JCheckBox f;
		
		try {
			Boolean value = false;
			f = new JCheckBox();
			f.setSelected(value);
			
			gbc.gridx = posX;
	;
			addComponentToView(f,sqlSpaltenName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void addButton(String buttonText, String buttonName,int posX, int posY, int width, int height){
		AddButton button = new AddButton(buttonText,buttonName,(MouseListener)controller);
		GridBagConstraints gbc = getGridBagConstraint();
		gbc.gridx = posX;
		gbc.gridy = posY;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		
		addComponentToView(button);
	}
	
	protected void setBottomMenu(int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		gbc.gridx = 0;
		gbc.gridy = posY;
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		SingleBottomMenu bottomMenu = new SingleBottomMenu((EventListener) controller);
		
		addComponentToView(bottomMenu);
	}

	
	@Override
	public Object[][] getEingabeWerte() {
		Object[][] updatedValues = new Object[countOfSqlReferencesInList][2];
		int index = 0;
		int sqlReferenceIndex = 0;
		for(String sqlReference : listOfComponentsToSqlReference){
			if(sqlReference.equals("")){
				sqlReferenceIndex++;
				continue;
			}
			updatedValues[index][0] = sqlReference;
			updatedValues[index][1] = getValueFromComponent(index+sqlReferenceIndex);
			index++;
		}
//		for(int index = 0;index < listOfComponentsToSqlReference.size();index++){
//			if(listOfComponentsToSqlReference.get(index) != null){
//				 = listOfComponentsToSqlReference.get(index);
//				//updatedValues[index][1] = getStringValueFromComponent(index);
//				
//			}
//		}
		
		return updatedValues;
	}
	
	private String getStringValueFromComponent(int componentIndex){
		JComponent comp = listOfallComponentsOnView.get(componentIndex);
		
		if(comp instanceof JTextField){
			return ((JTextField)comp).getText();
		}
		
		if(comp instanceof JTextArea){
			return ((JTextArea)comp).getText();
		}
		
		if(comp instanceof JCheckBox){
			return new String(((JCheckBox)comp).isSelected()?"true":"false");
		}

		return new String();
		
	}
	
	private Object getValueFromComponent(int componentIndex){
		JComponent comp = listOfallComponentsOnView.get(componentIndex);
		
		if(comp instanceof JTextField){
			return ((JTextField)comp).getText();
		}
		
		if(comp instanceof JTextArea){
			return ((JTextArea)comp).getText();
		}
		
		if(comp instanceof JCheckBox){
			return new Boolean(((JCheckBox)comp).isSelected()?"true":"false");
		}
		
		if(comp instanceof JLabel){
			return ((JLabel)comp).getText();
		}

		return new Object();
		
	}
	
	@Override
	public void modelHasChanged() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getValueFromCurrentItem(String sqlColumnName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
