package Views;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.*;
import Models.*;
import Controller.*;

public abstract class View extends JInternalFrame implements UpdateView{

	protected Model model;
	protected Controller controller;
	
	private JPanel contentPanel;
	
	private GridBagLayout layout;
	
	private GridBagConstraints gridBagConstraint;
	
	
	protected ArrayList<JComponent> listOfallComponentsOnView;
	
	protected ArrayList<String> listOfComponentsToSqlReference;
	
	
	
	public View(Controller controller){
		super("",false,true,true,true);
		this.controller = controller;
		
		model = this.controller.getModel();
		model.registerView(this);
		
		listOfallComponentsOnView = new ArrayList<JComponent>();
		listOfComponentsToSqlReference = new ArrayList<String>();
		
		contentPanel = new JPanel();
		layout = new GridBagLayout();
		setGridBagConstraint(new GridBagConstraints());
		contentPanel.setLayout(layout);
		
	}
	/**
	 * TODO
	 * 		model meldet das sich daten geändert haben
	 */
	@Override
	public abstract void modelHasChanged();
	
	/**
	 * über diese funktion wird die view gefüllt -> hier werden die elemente gesetzt
	 */
	public abstract void setElemente();
	
	public void deleteComponentFromView(JComponent component){
		contentPanel.remove(component);
	}
	
	public void addComponentToView(JComponent component){
		System.out.println("add "+ component.getName());
		contentPanel.add(component);
	}
	
	protected void setLabel(String labelText,int posX, int posY){
		JLabel label = new JLabel(labelText);
		GridBagConstraints gridBagConstraint = getGridBagConstraint();
		
		gridBagConstraint.gridx = posX;
		gridBagConstraint.gridy = posY;
		gridBagConstraint.gridwidth = GridBagConstraints.RELATIVE;
		
		setConstraintToComponent(label);
		addComponentToView(label);
	}
	

	
	/**
	 * gibt alle Werte der Eingabefelder der View zurück
	 * TODO
	 * 
	 * @return Object[]
	 */
	public Object[] getEingabeWerte(){
		return null;
	}
	
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
	
	public void setConstraintToComponent(JComponent component){
		layout.setConstraints(component,getGridBagConstraint());
	}
}
