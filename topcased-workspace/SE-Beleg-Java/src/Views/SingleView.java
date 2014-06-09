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
import Views.Table.TableData;

public abstract class SingleView extends View{
	

	
	public SingleView(Controller controller){
		super(controller);
	}

	protected void addTextfieldWithSqlReference(String labelText,String sqlAliasSpaltenName, String sqlUpdateOrInstertSpaltenName,int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX,posY);
		
		JTextField f;
		
		try {
			f = new JTextField(model.tableRowData.getStringValueFromPosition(model.rowPosition, sqlAliasSpaltenName));
			f.setName(sqlAliasSpaltenName);
			f.setColumns(20);
			
			gbc.gridx = posX+1;
			addComponentToView(f,sqlUpdateOrInstertSpaltenName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void addTextfield(String labelText,String sqlSpaltenName,int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX,posY);
		
		JTextField f;
		
		try {
			f = new JTextField(model.tableRowData.getStringValueFromPosition(model.rowPosition, sqlSpaltenName));
			f.setColumns(20);
			f.setName(sqlSpaltenName);
			gbc.gridx = posX+1;

			addComponentToView(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void addTextAreaWithSqlReference(String labelText,String sqlSpaltenName, String sqlUpdateOrInstertSpaltenName,int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX,posY);
		
		JTextArea f;
		
		try {
			f = new JTextArea(model.tableRowData.getStringValueFromPosition(model.rowPosition, sqlSpaltenName));
			f.setColumns(20);
			f.setRows(5);
			f.setName(sqlSpaltenName);
			gbc.gridx = posX+1;

			addComponentToView(f,sqlUpdateOrInstertSpaltenName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void addTextArea(String labelText,String sqlSpaltenName,int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX,posY);
		
		JTextArea f;
		
		try {
			f = new JTextArea(model.tableRowData.getStringValueFromPosition(model.rowPosition, sqlSpaltenName));
			f.setColumns(20);
			f.setRows(5);
			f.setName(sqlSpaltenName);
			gbc.gridx = posX+1;
			addComponentToView(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void addCheckboxWithSqlReference(String labelText,String sqlSpaltenName, String sqlUpdateOrInstertSpaltenName,int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX+1,posY);
		
		JCheckBox f;
		
		try {
			Boolean value = model.tableRowData.getBooleanValueFromPosition(model.rowPosition, sqlSpaltenName);
			f = new JCheckBox();
			f.setSelected(value);
			f.setName(sqlSpaltenName);
			gbc.gridx = posX;
			addComponentToView(f,sqlUpdateOrInstertSpaltenName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void addCheckbox(String labelText,String sqlSpaltenName,int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX+1,posY);
		
		JCheckBox f;
		
		try {
			Boolean value = model.tableRowData.getBooleanValueFromPosition(model.rowPosition, sqlSpaltenName);
			f = new JCheckBox();
			f.setSelected(value);
			f.setName(sqlSpaltenName);
			gbc.gridx = posX;
			
			addComponentToView(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void addHiddenFieldWithSqlReference(String sqlSpaltenName, String value){

		JLabel hiddenContractId = new JLabel(value);
		hiddenContractId.setVisible(false);
		
		addComponentToView(hiddenContractId,sqlSpaltenName);

	}
	
	protected void addAddButton(String buttonText, String buttonName,int posX, int posY, int width, int height){
		AddButton button = new AddButton(buttonText,buttonName,(MouseListener)controller);
		GridBagConstraints gbc = getGridBagConstraint();
		gbc.gridx = posX;
		gbc.gridy = posY;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		addComponentToView(button);
	}
	
	protected void addBottomMenu(int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		gbc.gridx = 0;
		gbc.gridy = posY;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		SingleBottomMenu bottomMenu = new SingleBottomMenu((EventListener) controller);
		addComponentToView(bottomMenu);
	}
	
	public void gotoNext(){
		if((model.rowPosition+1) == model.tableRowData.getRowCount()) return;
		model.rowPosition++;
		refreshComponentsContent();
	}
	
	public void gotoPrevius(){
		if(model.rowPosition == 0) return;
		model.rowPosition--;
		refreshComponentsContent();
	}
	
	protected void refreshComponentsContent(){
		for(JComponent comp:listOfallComponentsOnView){
			if(comp.getName() != null && !comp.getName().equals("")){
				if(comp instanceof JTextField)	
						((JTextField) comp).setText(model.tableRowData.getValueFromPosition(model.rowPosition, comp.getName()).toString());
				
				if(comp instanceof JCheckBox){	
					boolean flag = model.tableRowData.getValueFromPosition(model.rowPosition, comp.getName())=="true"?true:false;
					((JCheckBox) comp).setSelected(flag);
				}
				
				if(comp instanceof JTextArea)	
					((JTextArea) comp).setText(model.tableRowData.getValueFromPosition(model.rowPosition, comp.getName()).toString());
			}
		}
	}
	
	@Override
	public void modelHasChanged() {
		// TODO Auto-generated method stub
		refreshComponentsContent();
	}
	
	@Override
	public Object[][] getInputValues() {
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
		
		if(comp instanceof JLabel){
			return ((JLabel)comp).getText();
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
	
	public String getValueFromCurrentItem(String sqlColumnName){
		try {
			return model.tableRowData.getStringValueFromPosition(model.rowPosition, sqlColumnName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
