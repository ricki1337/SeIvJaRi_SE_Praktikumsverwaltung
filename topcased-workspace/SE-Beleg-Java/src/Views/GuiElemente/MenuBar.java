package Views.GuiElemente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import Controller.CompanieList;
import Controller.ContractList;
import Controller.ProfList;
import Controller.StudentList;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.Import.ImportWizard;


public class MenuBar implements ActionListener{

	Praktikumsverwaltung frame;
    
    // Menüleiste
    JMenuBar menueLeiste;
    
    // Menüleiste Elemente
    JMenu datei;
    JMenu hilfe;

    // Datei
    JMenuItem oeffnen;
    JMenuItem importieren;
    JMenuItem beenden;
    
    // Hilfe
    JMenuItem faq;
    JMenuItem about;
    
    //Button
    JButton		student;
    JButton		firma;
    JButton		vertrag;
    JButton		betreuer;
    
    //Label
    JLabel label;
    
    ImageIcon img;
    
    
    public MenuBar(){
    	
    	JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
    	
    	img = new ImageIcon("D:\\Programme\\Topcased-5.3.0\\workspace\\topcased-workspace\\bin\\ui\\db_ok_32.png");
    	
    	//Buttons erzeugen
        student = new JButton("Student");
        student.setName("student");
        student.addActionListener(this);
        
        firma = new JButton("Firma");
        firma.setName("firma");
        firma.addActionListener(this);
        
        vertrag = new JButton("Vertrag");
        vertrag.setName("vertrag");
        vertrag.addActionListener(this);
        
        betreuer = new JButton("Betreuer");
        betreuer.setName("betreuer");
        betreuer.addActionListener(this);
        
        label = new JLabel();
        label.setText("Suchmaske:    \t");
        
        // Menüleiste erzeugen
        menueLeiste = new JMenuBar();
        
        // Menüelemente erzeugen
        datei = new JMenu("Datei");
        hilfe = new JMenu("Hilfe");
        
        // Untermenüelemente erzeugen
        oeffnen = new JMenuItem("öffnen");
        oeffnen.addActionListener(this);
        beenden = new JMenuItem("beenden");
        beenden.addActionListener(this);
        importieren = new JMenuItem("Studenten Importieren");
        importieren.addActionListener(this);
        faq = new JMenuItem("F.A.Q.");
        faq.addActionListener(this);
        about = new JMenuItem("Über");
        about.addActionListener(this);
        
        // Menüelemente hinzufügen
        menueLeiste.add(datei);
        menueLeiste.add(hilfe);
        menueLeiste.add(separator);
        menueLeiste.add(label);
        menueLeiste.add(student);
        menueLeiste.add(betreuer);
        menueLeiste.add(vertrag);
        menueLeiste.add(firma);
        label.setIcon(img);
        
        
        // Untermenüelemente hinzufügen
        datei.add(oeffnen);
        datei.add(importieren);
        datei.add(beenden);
        hilfe.add(faq);
        hilfe.add(about);
    }
    
    public JMenuBar getMenu(){return menueLeiste;}   	
    public void setFrame(Praktikumsverwaltung frame){
    	this.frame = frame;
    }
	
    
    public void actionPerformed(ActionEvent object) {
        if (object.getSource() == student){
            Praktikumsverwaltung.addFrameToForeground(new StudentList());
       }
        if (object.getSource() == firma){
        	Praktikumsverwaltung.addFrameToForeground(new CompanieList());
       }
        if (object.getSource() == vertrag){
        	Praktikumsverwaltung.addFrameToForeground(new ContractList());
       }
        if (object.getSource() == betreuer){
        	Praktikumsverwaltung.addFrameToForeground(new ProfList());
       }
    	
    	
    	if (object.getSource() == oeffnen){
             System.out.println("öffnen wurde angeklickt");
        }
    	if (object.getSource() == importieren){
            System.out.println("imortieren wurde angeklickt");
            startImport();
       }
        if (object.getSource() == beenden){
             System.out.println("beenden wurde angeklickt");
        }
        if (object.getSource() == faq){
             System.out.println("faq wurde angeklickt");
        }
        if (object.getSource() == about){
             System.out.println("über wurde angeklickt");
        }
   }
    
    private void startImport(){
    	ImportWizard importwizard =new ImportWizard();
    	importwizard.setSize(800, 600);
    	importwizard.setTitle("ImportWizard");
    	importwizard.setLocationRelativeTo(null);
		importwizard.setModal(true);
		importwizard.setVisible(true);

    	
    }
  
	
}
