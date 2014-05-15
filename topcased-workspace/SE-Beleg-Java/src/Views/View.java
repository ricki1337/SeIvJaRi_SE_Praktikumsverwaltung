package Views;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import Controller.Controller;
import Models.Model;

public abstract class View extends JInternalFrame implements UpdateView{

	protected Model model;
	protected Controller controller;
	
	private JPanel contentPanel;
	
	private GridBagLayout layout;
	
	private GridBagConstraints gridBagConstraint;
	
	
	protected ArrayList<JComponent> listOfallComponentsOnView;
	
	protected ArrayList<String> listOfComponentsToSqlReference;
	protected int countOfSqlReferencesInList;
	
	
	public View(Controller controller){
		super("",false,true,true,true);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		final JInternalFrame instance = this;
		this.addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
            	model.modelClose();
            	instance.dispose();
              
            }
		});
		
		this.controller = controller;
		this.setName("view");
		model = this.controller.getModel();
		model.setView(this);
		
		listOfallComponentsOnView = new ArrayList<JComponent>();
		listOfComponentsToSqlReference = new ArrayList<String>();
		countOfSqlReferencesInList = 0;
		
		contentPanel = new JPanel();
		layout = new GridBagLayout();
		setGridBagConstraint(new GridBagConstraints());
		contentPanel.setLayout(layout);
		
	}
	
	public abstract void setElemente();
	
	public abstract String getValueFromCurrentItem(String sqlColumnName);
	
	public void deleteComponentFromView(JComponent component){
		contentPanel.remove(component);
		listOfComponentsToSqlReference.remove(listOfallComponentsOnView.indexOf(component));
		listOfallComponentsOnView.remove(component);
	}
	
	public void deleteComponentFromView(String componentName){
		for(int index=listOfallComponentsOnView.size()-1;index>=0;index--){
			String compName = listOfallComponentsOnView.get(index).getName();
			if(compName != null && compName.equals(componentName)){
				deleteComponentFromView(listOfallComponentsOnView.get(index));
			}
		}
		
	}
	
	public void addComponentToView(JComponent component){
		setConstraintToComponent(component);
		contentPanel.add(component);
		addComponentToInternList(component,new String(""));
	}
	
	public void addComponentToView(JComponent component, String sqlUpdateOrInstertSpaltenName){
		setConstraintToComponent(component);
		contentPanel.add(component);
		addComponentToInternList(component,sqlUpdateOrInstertSpaltenName);
	}
	
	private void addComponentToInternList(JComponent component, String sqlUpdateOrInstertSpaltenName){
		listOfallComponentsOnView.add(component);
		listOfComponentsToSqlReference.add(sqlUpdateOrInstertSpaltenName);
		if(!sqlUpdateOrInstertSpaltenName.equals(""))
			countOfSqlReferencesInList++;
	}
	
	protected void setLabel(String labelText,int posX, int posY){
		JLabel label = new JLabel(labelText);
		
		GridBagConstraints gridBagConstraint = getGridBagConstraint();
		
		gridBagConstraint.ipadx = 5;
		gridBagConstraint.ipady = 5;
		gridBagConstraint.insets = new Insets(1,2,3,4);
		
		gridBagConstraint.gridx = posX;
		gridBagConstraint.gridy = posY;
		gridBagConstraint.gridwidth = 1;
		
		addComponentToView(label);
	}
	

	public abstract Object[][] getEingabeWerte();
	
	public final void display(){
		setElemente();
		add(contentPanel);
		pack();
		setVisible(true);
	}
	
	public GridBagConstraints getGridBagConstraint() {
		return gridBagConstraint;
	}
	
	public void setGridBagConstraint(GridBagConstraints gridBagConstaint) {
		this.gridBagConstraint = gridBagConstaint;
	}
	
	private void setConstraintToComponent(JComponent component){
		layout.setConstraints(component,getGridBagConstraint());
		restoreConstraint();
	}
	
	private void restoreConstraint(){
		gridBagConstraint.ipadx = 0;
		gridBagConstraint.ipady = 0;
		gridBagConstraint.insets = new Insets(0,0,0,0);
		gridBagConstraint.gridwidth = 1;
		gridBagConstraint.gridheight = 1;
	}
	
	public void refreshViewComponents(){
		pack();
	}
	
}
