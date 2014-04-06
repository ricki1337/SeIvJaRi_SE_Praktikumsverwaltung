package Views;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import com.mysql.jdbc.ResultSetMetaData;

import Models.*;

import Controller.*;

public abstract class View implements UpdateView{

	private Model model;
	private Controller controller;
	
	private JPanel contentPanel;
	
	private GridBagLayout layout;
	
	private GridBagConstraints gbc;
	
	//liste von jComponent, parallel dazu liste von String => assoziatives array!
		//in stringliste werden sql spaltennamen gespeichert
		//jComponent detail:
			//->instanceOf
				//- jTextComponent -> jTextField & jTextBox
				//- AbstractButton -> jCheckbox
		
	//hält alle informationen repräsentierenden elemente
	private ArrayList<JComponent> viewElemente;
	//hält assoziation der Sql Tabelle zu viewElemente
	private ArrayList<String> viewElementeToSql;
	
	
	
	public View(Controller controller){
		this.controller = controller;
		
		model = this.controller.getModel();
		model.registerView(this);
		
		viewElemente = new ArrayList<JComponent>();
		viewElementeToSql = new ArrayList<String>();
		contentPanel = new JPanel();
		layout = new GridBagLayout();
		gbc = new GridBagConstraints();
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
	
	//erzeugt die tabelle mit allen informationen
	protected void setTable(){
		
		try {
			//zeileninhalte
			Object rowData[][];
			//Spaltennamen
			Object columnNames[];
			
			ResultSet result = model.getResult();
			java.sql.ResultSetMetaData meta;
			meta = result.getMetaData();

			//ans ende des result gehen
			result.last();
			//das datenarray erstellen
			rowData = new Object[result.getRow()][meta.getColumnCount()];
			//Spaltennamenarray erstellen
			columnNames = new Object[meta.getColumnCount()];
			//wieder an den anfang des array gehen
			result.first();
			
			//datenarray füllen
			int j=0;
			while(result.next()){
				for(int i = 1;i<=meta.getColumnCount();i++){
					rowData[j][i] = result.getObject(i);
				}
				j++;
			}
			
			NonEditableTable table = new NonEditableTable(rowData, columnNames);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			
			layout.setConstraints(table.getTableHeader(), gbc);
			contentPanel.add(table.getTableHeader());
			layout.setConstraints(table, gbc);
			contentPanel.add(table);
			/*
			 
			Object rowData[][] = {
					{"12341","Rick","Hermenau","s12312@htw-dresden.de","12/043/01","Ja","Ja","Ja"},
					{"12342","Max","Meier","s12212@htw-dresden.de","11/041/01","Ja","Ja","Nein"},
					{"12343","Ingo","Kummer","s22312@htw-dresden.de","11/043/01","Ja","Ja","Nein"},
					{"12344","Ivo","Beier","s12312@htw-dresden.de","12/043/01","Ja","Nein","Nein"},
					{"12345","Sepp","Härtel","s16312@htw-dresden.de","12/043/01","Nein","Nein","Nein"},
					{"12346","Jakob","Helzig","s12812@htw-dresden.de","12/043/01","Ja","Nein","Nein"}
			};
			
			Object columnNames[]= {"Matrikelnr.","Vorname","Nachname","Email","Studiengr.","Betreuer","Firma","Vertrag"};
			
			mytable table = new mytable( rowData, columnNames );
			
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.addMouseListener(new MySelectionListener(table));
			
			JScrollPane scrollPane = new JScrollPane(table);
			table.setFillsViewportHeight(true);
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbl.setConstraints(table.getTableHeader(), gbc);
			TablePanel.add(table.getTableHeader());
			gbl.setConstraints(table, gbc);
			TablePanel.add(table);
			PanelCenter.add(TablePanel);
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private void setLabel(String labelText,int posX, int posY){
		JLabel l = new JLabel(labelText);
		
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = posX;
		gbc.gridy = posY;
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		layout.setConstraints(l, gbc);
		contentPanel.add(l);
		
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
			layout.setConstraints(f, gbc);
			//das JTextField in die Liste der Informationstragenden Elemente einhängen
			viewElemente.add(f);
			//die parallele Liste für die Sql Spalte
			viewElementeToSql.add(sqlSpaltenName);
			//das JTextField im JPanel registrieren
			contentPanel.add(f);
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
	protected void setTextbox(String labelText,String sqlSpaltenName,String wert,int posX, int posY){}
	
	
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
	protected void setCheckbox(String labelText,String sqlSpaltenName,String wert,int posX, int posY){}
	
	
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
		controller.add(contentPanel);
		controller.pack();
		controller.setVisible(true);
	}
}
