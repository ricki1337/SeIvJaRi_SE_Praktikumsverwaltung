import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Prakt_ViewStudent extends JInternalFrame{
	
	class MySelectionListener extends MouseAdapter{
		JTable table;
		public MySelectionListener(JTable table){
			this.table = table;
			
		}
		public void mouseClicked(MouseEvent e){
			if (e.getClickCount() == 2) { 
		           MainWindow.addInternalFrame(new Prakt_ViewStudent(MainWindow,table.getSelectedRow()));
			}
		}

	}
	
	Prakt_MainWindow MainWindow;
	
	JPanel PanelTop,PanelCenter;
	ListSelectionModel listSelectionModel;
	
	
	
	public Prakt_ViewStudent(final Prakt_MainWindow MainWindow,int auswahl){
		super("Studenten",true,true,true,true);
		this.MainWindow = MainWindow;
		setSize(500,500);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);
		
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx= 1;
		gbc.weighty= 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		PanelTop = new JPanel();
			PanelTop.setLayout(new FlowLayout());
			PanelTop.add(new JLabel("Suchen"));
			PanelTop.add(new JTextField(20));
			PanelTop.add(new JButton("Anlegen"));
			PanelTop.add(new JButton("Bearbeiten"));
			PanelTop.add(new JButton("Mail senden"));
		
		gbl.setConstraints(PanelTop, gbc);
		add(PanelTop);
		if(auswahl > -1) displayOne();
		else displayList();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx= 1;
		gbc.weighty= 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		//gbc.gridheight = GridBagConstraints.REMAINDER;
		gbl.setConstraints(PanelCenter, gbc);
		add(PanelCenter);
		
		pack();		
		setVisible(true);
	}
	
	public void displayOne(){
		PanelCenter = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		PanelCenter.setLayout(gbl);
		GridBagConstraints pcc = new GridBagConstraints();
		int x=0,y=0;
		//pcc.gridwidth = 5;
		//pcc.gridheight = 5;
		
		//Label
		JLabel l = new JLabel("Student");
		pcc.gridx = 0;
		pcc.ipady = 20;
		pcc.gridy = y;
		pcc.gridwidth = 2;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		//studentendaten
		//matrikel start
		l = new JLabel("Matrikelnr.:");
		pcc.gridx = 0;
		pcc.gridy = ++y;
		pcc.gridwidth = 1;
		pcc.ipady = 5;
		pcc.ipadx = 10;
		//pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		JTextField t= new JTextField(20);
		t.setText("34120");
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//matrikel ende
		
		//Vorname start
		l = new JLabel("Vorname:");
		pcc.gridx = 0;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("Rick");
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//Vorname ende
		
		//Nachname start
		l = new JLabel("Nachname:");
		pcc.gridx = 0;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("Hermenau");
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//Nachname ende
		
		//email start
		l = new JLabel("Email:");
		pcc.gridx = 0;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("s68524@htw-dresden.de");
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//email ende
		
		//studiengruppe start
		l = new JLabel("Studiengr.:");
		pcc.gridx = 0;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("12/043/01");
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//studiengruppe ende
		
		//bemerkung start
		l = new JLabel("Bemerkung:");
		pcc.gridx = 0;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		JTextArea ta= new JTextArea(5,20);
		ta.setText("macht sich....");
		pcc.gridx = 1;
		pcc.gridy = y;
		pcc.gridheight = 5;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(ta, pcc);
		PanelCenter.add(ta);
		//bemerkung ende
		
		
		//label vertrag (wenn ein vertrag vorliegt)
		
		l = new JLabel("Vertrag");
		pcc.gridx = 3;
		pcc.gridy = y=0;
		pcc.ipady = 20;
		//pcc.anchor = GridBagConstraints.EAST;
		pcc.gridheight = 1;
		pcc.gridwidth = 2;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		//vertragsdaten
		//firma start
		l = new JLabel("Firma:");
		pcc.ipady = 5;
		pcc.ipadx = 10;
		pcc.gridx = 3;
		pcc.gridy = ++y;
		pcc.gridwidth = 1;
		
		pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("BeispielFirma GmbH");
		pcc.gridx = 4;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//firma ende
		
		//ansprechpartner firma start
		l = new JLabel("Ansprechpartner:");
		pcc.gridx = 3;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("Herr Meiﬂe");
		pcc.gridx = 4;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//ansprechpartner firma ende
		
		//ansprechpartner firma start
		l = new JLabel("Tel. Ansprechpartner:");
		pcc.gridx = 3;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("0351 9827642-21");
		pcc.gridx = 4;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//ansprechpartner firma ende
		
		//beginn start
		l = new JLabel("beginnt am:");
		pcc.gridx = 3;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("01.03.2014");
		pcc.gridx = 4;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//beginn ende
		
		//ende start
		l = new JLabel("endet am:");
		pcc.gridx = 3;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("31.10.2014");
		pcc.gridx = 4;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//ende ende
		
		//betreuer start
		l = new JLabel("Betreuer:");
		pcc.gridx = 3;
		pcc.gridy = ++y;
		//pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("Herr Prof. Fritzsche");
		pcc.gridx = 4;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//betreuer ende
		

		//label details zum praktikum
		l = new JLabel("Details zum Praktikum");
		pcc.gridx = 0;
		pcc.gridy = y=15;
		//pcc.anchor = GridBagConstraints.EAST;
		pcc.ipady = 20;
		pcc.gridheight = 1;
		pcc.gridwidth = 2;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		//vortrag start
		l = new JLabel("Vortrag am:");
		pcc.ipady = 5;
		pcc.ipadx = 10;
		pcc.gridx = 0;
		pcc.gridy = ++y;
		pcc.gridwidth = 1;
		
		pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		t= new JTextField(20);
		t.setText("08.08.2014 10:30:00");
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(t, pcc);
		PanelCenter.add(t);
		//vortrag ende
		
		//erfolg start
		l = new JLabel("Erfolg:");
		pcc.ipady = 0;
		pcc.gridx = 0;
		pcc.gridy = ++y;
		pcc.gridwidth = 1;
		
		pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		JCheckBox cb = new JCheckBox();
		
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(cb, pcc);
		PanelCenter.add(cb);
		//erfolg ende
		
		//Bericht start
		l = new JLabel("Bericht:");
		pcc.ipady = 0;
		pcc.gridx = 0;
		pcc.gridy = ++y;
		pcc.gridwidth = 1;
		
		pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		cb = new JCheckBox();
		
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(cb, pcc);
		PanelCenter.add(cb);
		//Bericht ende
		
		//Zeugnis start
		l = new JLabel("Zeugnis:");
		pcc.ipady = 0;
		pcc.gridx = 0;
		pcc.gridy = ++y;
		pcc.gridwidth = 1;
		
		pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		cb = new JCheckBox();
		
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(cb, pcc);
		PanelCenter.add(cb);
		//Zeugnis ende
		
		//empfehlung start
		l = new JLabel("Empfehlung:");
		pcc.ipady = 0;
		pcc.gridx = 0;
		pcc.gridy = ++y;
		pcc.gridwidth = 1;
		
		pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		cb = new JCheckBox();
		
		pcc.gridx = 1;
		pcc.gridy = y;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(cb, pcc);
		PanelCenter.add(cb);
		//empfehlung ende
		
		//Text Prof
		
		//vortrag start
		l = new JLabel("Bemerkung Betreuer:");
		pcc.ipady = 0;
		pcc.gridx = 3;
		pcc.gridy = y = 16;
		pcc.gridwidth = 1;
		
		pcc.fill = GridBagConstraints.NONE;
		gbl.setConstraints(l, pcc);
		PanelCenter.add(l);
		
		ta= new JTextArea(7,20);
		ta.setText("naja......nen haufen text");
		pcc.gridx = 4;
		pcc.gridy = y;
		pcc.gridheight = 7;
		//pcc.fill = GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(ta, pcc);
		PanelCenter.add(ta);
		//vortrag ende
		
	}
	
	public void displayList(){
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel TablePanel = new JPanel();
		TablePanel.setLayout(gbl);
		
		Object rowData[][] = {
				{"12341","Rick","Hermenau","s12312@htw-dresden.de","12/043/01","Ja","Ja","Ja"},
				{"12342","Max","Meier","s12212@htw-dresden.de","11/041/01","Ja","Ja","Nein"},
				{"12343","Ingo","Kummer","s22312@htw-dresden.de","11/043/01","Ja","Ja","Nein"},
				{"12344","Ivo","Beier","s12312@htw-dresden.de","12/043/01","Ja","Nein","Nein"},
				{"12345","Sepp","H‰rtel","s16312@htw-dresden.de","12/043/01","Nein","Nein","Nein"},
				{"12346","Jakob","Helzig","s12812@htw-dresden.de","12/043/01","Ja","Nein","Nein"}
		};
		
		Object columnNames[]= {"Matrikelnr.","Vorname","Nachname","Email","Studiengr.","Betreuer","Firma","Vertrag"};
		PanelCenter = new JPanel();
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
	}
}



class mytable extends JTable{
	public mytable(Object[][] rowData, Object[] columnNames) {
		super(rowData,columnNames);
	}

	public boolean isCellEditable(int row, int col){
        return false;
    }
	
}