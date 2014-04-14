package Views;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.ResultSetMetaData;

import Models.*;
import Views.Elemente.TopMenu;

import Controller.*;

public abstract class View extends JInternalFrame implements UpdateView{

	protected Model model;
	protected Controller controller;
	
	private JPanel contentPanel;
	
	private GridBagLayout layout;
	
	private GridBagConstraints gbc;
	
	//hält alle informationen repräsentierenden elemente
	protected ArrayList<JComponent> viewElemente;
	//hält assoziation der Sql Tabelle zu viewElemente
	protected ArrayList<String> viewElementeToSql;
	
	
	
	public View(Controller controller){
		super("",false,true,true,true);
		this.controller = controller;
		
		model = this.controller.getModel();
		model.registerView(this);
		
		viewElemente = new ArrayList<JComponent>();
		viewElementeToSql = new ArrayList<String>();
		
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
	public void modelHasChanged() {
		//alle elemente löschen
		contentPanel.removeAll();
		//array zurücksetzen
		viewElemente.clear();
		viewElementeToSql.clear();		
		//elemente neu setzen
		setElemente();
	}
	
	/**
	 * über diese funktion wird die view gefüllt -> hier werden die elemente gesetzt
	 */
	public abstract void setElemente();
	
	
	public void addElement(JComponent element){
		contentPanel.add(element);
	}
	
	
	
	
	
	
	protected void setLabel(String labelText,int posX, int posY){
		JLabel l = new JLabel(labelText);
		GridBagConstraints gbc = getGridBagConstraint();
		//gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = posX;
		gbc.gridy = posY;
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		setConstraint(l);
		addElement(l);
		
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
		return gbc;
	}
	
	public void setGridBagConstraint(GridBagConstraints gbc) {
		this.gbc = gbc;
	}
	
	public void setConstraint(JComponent element){
		layout.setConstraints(element,getGridBagConstraint());
	}
}
