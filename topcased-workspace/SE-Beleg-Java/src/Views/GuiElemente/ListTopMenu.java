package Views.GuiElemente;



import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import Views.ListView;
import Views.GuiElemente.SearchPanel.CompanyExtendedSearchPanel;
import Views.GuiElemente.SearchPanel.ContractExtendedSearchPanel;
import Views.GuiElemente.SearchPanel.ExtendedSearchPanel;
import Views.GuiElemente.SearchPanel.ProfExtendedSearchPanel;
import Views.GuiElemente.SearchPanel.StudentExtendedSearchPanel;

public class ListTopMenu extends JPanel{
	JPanel oben;
	JPanel unten;
	GridLayout layout;
	ExtendedSearchPanel searchPanel;
	EventListener controller;
	ListView view;
	
	
	public ListTopMenu(EventListener controller, final ListView view){
		super();
		layout = new GridLayout(1,2);
		layout.setHgap(5);
		setLayout(layout);
		setName("topmenu");
		this.controller = controller;
		this.view = view;
		
		oben = new JPanel();
		oben.setLayout(new FlowLayout());
		
		unten = new JPanel();
		unten.setLayout(new GridLayout(1,1));
		unten.setName("unten");
		
		
		JPanel suche = new JPanel(new FlowLayout());
		suche.add(new JLabel("Suchen"));
		
		JTextField suchFeld = new JTextField(20);
		suchFeld.setName("suchFeld");
		suchFeld.getDocument().addDocumentListener((DocumentListener)controller);
		suche.add(suchFeld);
		
		oben.add(suche);
		
		JButton anlegen = new JButton("Anlegen");
		anlegen.setName("anlegen");
		anlegen.addMouseListener((MouseListener)controller);
		

		oben.add(anlegen);
		
		setSearchPanel();
		
		searchPanel.setName("searchPanel");

		searchPanel.setVisible(true);
		unten.add(searchPanel);
		
		JButton erweiterteSuche = new JButton("Erweiterte Suche");

		erweiterteSuche.addActionListener(
				new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent ae) {
							showExtendedSearch();
							view.pack();
						}
				});
		

		oben.add(erweiterteSuche);
		
		oben.add(new JLabel("Anz. Datensätze: "));
		JTextField anzDatensaetze = new JTextField("20");
		anzDatensaetze.setName("anzDatensaetze");
		anzDatensaetze.setColumns(3);
		anzDatensaetze.addActionListener((ActionListener)controller);
		oben.add(anzDatensaetze);
		
		
		oben.setVisible(true);
		
		add(oben);
		
		setVisible(true);
	}
	
	final private void showExtendedSearch(){
		if(unten.isShowing()){
			remove(unten);
			layout.setRows(1);
		}else{
			layout.setRows(2);
			unten.setVisible(true);
			add(unten);
		}
		layout.layoutContainer(getParent());

	}
	
	public void setSearchPanel(){
		if(view instanceof Views.CompanieList | view instanceof Views.CompanieListToContract)
			searchPanel = new CompanyExtendedSearchPanel(controller,view);
		
		if(view instanceof Views.StudentList | view instanceof Views.StudentListToContract)
			searchPanel = new StudentExtendedSearchPanel(controller,view);
		
		if(view instanceof Views.ContractList)
			searchPanel = new ContractExtendedSearchPanel(controller,view);
		
		if(view instanceof Views.ProfList)
			searchPanel = new ProfExtendedSearchPanel(controller,view);
	}
	
	

}
