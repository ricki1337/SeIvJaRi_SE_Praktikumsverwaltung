package Views.GuiElemente.SearchPanel;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Views.ListView;

public abstract class ExtendedSearchPanel extends JPanel{
	protected EventListener controller;
	protected ListView view;
	public ExtendedSearchPanel(EventListener controller,ListView view){
		super();
		this.controller = controller;
		this.view = view;
		
		
		setLayout(new GridLayout(5,2));
		
		addSearchFields();
		setVisible(true);
	}
	
	protected void addTextfield(String labelText,String sqlSearchField){
		addLabel(labelText);
		JTextField textfield = new JTextField(20);
		textfield.setName(sqlSearchField);
		add(textfield);
	}
	
	protected void addCheckBox(String labelText,String sqlSearchField){
		addLabel(labelText);
		JCheckBox checkbox = new JCheckBox();
		checkbox.setName(sqlSearchField);
		add(checkbox);
	}
	
	protected void addLabel(String labelText){
		JLabel label = new JLabel(labelText);
		add(label);
	}
	 
	protected void addSearchButton(){
		JButton searchbutton = new JButton("suchen");
		searchbutton.addActionListener((ActionListener) controller);
		add(searchbutton);
	}
	
	public Object[][] getSearchValues(){
		Component[] components = (Component[]) getComponents();
		
		String[][] sqlWhereArray = new String[components.length][2];
		int row = 0;
		for(Component comp:components){
			if(comp.getName() != null){
				String[] sqlColumnToValue = new String[2];
				if(comp instanceof JTextField){
					if(((JTextField)comp).getText().equals("")) continue;
					sqlColumnToValue[0] = comp.getName();
					sqlColumnToValue[1] = ((JTextField)comp).getText();
				}
					
			
				if(comp instanceof JCheckBox){
					sqlColumnToValue[0] = comp.getName();
					sqlColumnToValue[1] = Boolean.toString(((JCheckBox)comp).isSelected());
				}
				sqlWhereArray[row++] = sqlColumnToValue;
					
			}
		}
		
		for(int i=row;i<components.length;i++)
			sqlWhereArray[i] = null;
		
		return sqlWhereArray;
		
	}
	
	abstract protected void addSearchFields();
	
}
