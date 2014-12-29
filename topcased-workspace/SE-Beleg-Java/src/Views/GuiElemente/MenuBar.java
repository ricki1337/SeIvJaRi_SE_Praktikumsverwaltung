package Views.GuiElemente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import ConfigParser.Debug;
import Controller.CompanyList;
import Controller.ContractList;
import Controller.ProfList;
import Controller.StudentList;
import Models.Datenbank.Database;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.Import.ImportWizard;

/**
 * Implementiert die Menüleiste des Hauptfensters.
 */
public class MenuBar implements ActionListener{

	private Praktikumsverwaltung frame;
    
    // Menüleiste
	private JMenuBar menueLeiste;
    
    // Menüleiste Elemente
	private JMenu jm_datei;
	private JMenu jm_hilfe;

    // Datei
	private JMenuItem jmi_oeffnen;
	private JMenuItem jmi_importieren;
	private JMenuItem jmi_beenden;
    
    //Button
	private JButton		jb_student;
	private JButton		jb_firma;
	private JButton		jb_vertrag;
	private JButton		jb_betreuer;
    
    //Label
	private JLabel jl_suchmaske;

    /**
     * Initialisiert das Menü und bringt es zur Anzeige.
     */
    public MenuBar(){
    	
    	JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
    	
    	//Buttons erzeugen
        jb_student = new JButton("Student");
        jb_student.setName("student");
        jb_student.addActionListener(this);
        
        jb_firma = new JButton("Firma");
        jb_firma.setName("firma");
        jb_firma.addActionListener(this);
        
        jb_vertrag = new JButton("Vertrag");
        jb_vertrag.setName("vertrag");
        jb_vertrag.addActionListener(this);
        
        jb_betreuer = new JButton("Betreuer");
        jb_betreuer.setName("betreuer");
        jb_betreuer.addActionListener(this);
        
        jl_suchmaske = new JLabel();
        jl_suchmaske.setText("Suchmaske:    \t");
        
        // Menüleiste erzeugen
        menueLeiste = new JMenuBar();
        
        // Menüelemente erzeugen
        jm_datei = new JMenu("Datei");
        jm_hilfe = new JMenu("Hilfe");
        
        // Untermenüelemente erzeugen
        jmi_oeffnen = new JMenuItem("\u00D6ffnen");
        jmi_oeffnen.addActionListener(this);
        jmi_beenden = new JMenuItem("Beenden");
        jmi_beenden.addActionListener(this);
        jmi_importieren = new JMenuItem("Studenten importieren");
        jmi_importieren.addActionListener(this);
        
        // Menüelemente hinzufügen
        menueLeiste.add(jm_datei);
        menueLeiste.add(separator);
        menueLeiste.add(jl_suchmaske);
        menueLeiste.add(jb_student);
        menueLeiste.add(jb_betreuer);
        menueLeiste.add(jb_vertrag);
        menueLeiste.add(jb_firma);

        // Untermenüelemente hinzufügen
        jm_datei.add(jmi_importieren);
        jm_datei.add(jmi_beenden);
        setToolTip();
    }
    
    /**
     * Liefert das Menüobjekt zurück.
     * @return	JMenuBar Repräsentation des Menüs
     */
    public JMenuBar getMenu(){
    	return menueLeiste;
    }   	
    
    /**
     * Speichert den Hauptframe.
     * @param frame	Hauptframe
     */
    public void setFrame(Praktikumsverwaltung frame){
    	this.frame = frame;
    }
	
    @Override
    public void actionPerformed(ActionEvent object) {
        if (object.getSource() == jb_student){
        	Praktikumsverwaltung.addFrameToForeground(new StudentList());
       }
        if (object.getSource() == jb_firma){
        	Praktikumsverwaltung.addFrameToForeground(new CompanyList());
       }
        if (object.getSource() == jb_vertrag){
        	Praktikumsverwaltung.addFrameToForeground(new ContractList());
       }
        if (object.getSource() == jb_betreuer){
        	Praktikumsverwaltung.addFrameToForeground(new ProfList());
       }
    	
    	
    	if (object.getSource() == jmi_importieren){
            startImport();
       }
        if (object.getSource() == jmi_beenden){
             Database db = Database.getInstance();
             db.disconnect();
             System.exit(0);
        }
   }
    
    /**
     * Öffnet den Importdialog.
     */
    private void startImport(){
    	ImportWizard importwizard =new ImportWizard();
    	importwizard.setSize(800, 600);
    	importwizard.setTitle("Importassistent");
    	importwizard.setLocationRelativeTo(null);
		importwizard.setModal(true);
		importwizard.setVisible(true);
    }
  
    /**
     * Zeigt im DEBUG-Modus den Klassenname in einem Tooltip an.
     */
    public void setToolTip(){
		if(Debug.isDebugMode()){
			menueLeiste.setToolTipText(this.getClass().getCanonicalName());
			menueLeiste.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}
	
}
