import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;


public class MenuBar implements ActionListener{

	Praktikumsverwaltung frame;
    
    // Men�leiste
    JMenuBar menueLeiste;
    
    // Men�leiste Elemente
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
    	
    	JSeparator sep = new JSeparator(SwingConstants.VERTICAL);

    	img = new ImageIcon("D:\\Programme\\Topcased-5.3.0\\workspace\\topcased-workspace\\bin\\ui\\db_ok_32.png");
    	
    	//Buttons erzeugen
        student = new JButton("Student");
       
        student.addActionListener(this);
        firma = new JButton("Firma");
        firma.addActionListener(this);
        vertrag = new JButton("Vertrag");
        vertrag.addActionListener(this);
        betreuer = new JButton("Betreuer");
        betreuer.addActionListener(this);
        
        label = new JLabel();
        label.setText("Suchmaske:    \t");
        
        // Men�leiste erzeugen
        menueLeiste = new JMenuBar();
        
        // Men�elemente erzeugen
        datei = new JMenu("Datei");
        hilfe = new JMenu("Hilfe");
        
        // Untermen�elemente erzeugen
        oeffnen = new JMenuItem("�ffnen");
        oeffnen.addActionListener(this);
        beenden = new JMenuItem("beenden");
        beenden.addActionListener(this);
        importieren = new JMenuItem("Studenten Importieren");
        importieren.addActionListener(this);
        faq = new JMenuItem("F.A.Q.");
        faq.addActionListener(this);
        about = new JMenuItem("�ber");
        about.addActionListener(this);
        
        // Men�elemente hinzuf�gen
        menueLeiste.add(datei);
        menueLeiste.add(hilfe);
        menueLeiste.add(sep);
        menueLeiste.add(label);
        menueLeiste.add(student);
        menueLeiste.add(betreuer);
        menueLeiste.add(vertrag);
        menueLeiste.add(firma);
        label.setIcon(img);
        
        
        // Untermen�elemente hinzuf�gen
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
            System.out.println("Student wurde angeklickt");
       }
        if (object.getSource() == firma){
            System.out.println("firma wurde angeklickt");
       }
        if (object.getSource() == vertrag){
            System.out.println("vertrag wurde angeklickt");
       }
        if (object.getSource() == betreuer){
            System.out.println("betreuer wurde angeklickt");
       }
    	
    	
    	if (object.getSource() == oeffnen){
             System.out.println("�ffnen wurde angeklickt");
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
             System.out.println("�ber wurde angeklickt");
        }
   }
    
    private void startImport(){
    	ImportWizard a =new ImportWizard();
    	a.setSize(800, 600);
    	a.setTitle("ImportWizard");
		a.setModal(true);
		a.setVisible(true);

    	
    }
  
	
}
