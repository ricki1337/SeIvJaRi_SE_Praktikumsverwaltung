import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import com.itextpdf.text.DocumentException;
 

public class guiMail extends JFrame implements ActionListener
{
	
	//setzt Pfad zusammen wo Pdf gespeichert werden soll	
	public static String pfadanpassen(String eingabe){
		//    '\' wird zu '/', da der Pfad sonst nicht akzepiert wird 
		
		char [] eingabeC;
		eingabeC = eingabe.toCharArray();
		
		for(int i=0; i<eingabeC.length; i++){
			//92 = ascii code für '\'
        	if (eingabeC[i]==92){
        		eingabeC[i]='/';}

        }
		eingabe = String.valueOf(eingabeC);
		eingabe = eingabe + "/anerkennung.pdf";
		return eingabe;
	}
	
  //aktueller pfad speichern
	static String path = System.getProperty("user.dir");
   
  // pdf wird in aktuellem pfad gespeichert

   public static final String PDF = pfadanpassen(path);
   
   //pdf entfernen
   public void löschePdf(String Pfad){
	   File file = new File(Pfad);
       
       if(file.exists()){
           file.delete();
       }
   }
   
   
   
   
	
	
	
	
   ArrayList<JTextArea> txtareas = new ArrayList<JTextArea>();
	ArrayList<JCheckBox> zgchkboxs = new ArrayList<JCheckBox>();
	ArrayList<JCheckBox> berchkboxs = new ArrayList<JCheckBox>();
	ArrayList<JCheckBox> senden = new ArrayList<JCheckBox>();
	ArrayList<JTextField> txtfields = new ArrayList<JTextField>();
	JPanel panel;
	JLabel Jm = new JLabel("email");
	JTextField Jabs = new JTextField("s68551@htw-dresden.de");
	JLabel Jp = new JLabel("passwort");
	JPasswordField Jpw = new JPasswordField();
	JLabel label= new JLabel();
	JLabel label2= new JLabel();
	JButton mailsenden = new JButton("Mail(s) senden");
	
	
	
	String[] vornamenliste;
	String[] nachnamenliste;
	String[] matrklliste;
	String[] profliste;
	String[] stdgrpliste;
	String[] betriebsliste;
	int[]	berichtvorliegendliste;
	int[]	zeugnisvorliegendliste;
	String[] empfängerliste;
	
 
public guiMail(String[][] array){
    	
    	int anzahl = array.length;
    	
    	
    	vornamenliste = new String[anzahl];
    	nachnamenliste = new String[anzahl];
    	matrklliste = new String[anzahl];
    	profliste = new String[anzahl];
    	stdgrpliste = new String[anzahl];
    	betriebsliste = new String[anzahl];
    	berichtvorliegendliste = new int[anzahl];
    	zeugnisvorliegendliste = new int[anzahl];
    	empfängerliste = new String[anzahl];
    	
    	for (int i = 0; i < array.length; i++)
		{
			
		    	vornamenliste[i]=array[i][0];
    		nachnamenliste[i]=array[i][1];
    		matrklliste[i]=array[i][2];
    		profliste[i]=array[i][3];
    		stdgrpliste[i]=array[i][4];
    		betriebsliste[i]=array[i][5];
    		berichtvorliegendliste[i]=Integer.parseInt(array[i][6]);
    		zeugnisvorliegendliste[i]=Integer.parseInt(array[i][7]);
    		empfängerliste[i]=array[i][8];}
    	
    	
    	 panel = new JPanel();
    	 panel.setLayout( new GridLayout(/*3*/ 0, 6, 6, 3) );
    	 
    	 panel.add(Jm);
         panel.add(Jabs);
         panel.add(Jp);
         panel.add(Jpw);
         panel.add(label);
         String nummer;
         label2.setText(nummer = String.valueOf(anzahl));
         panel.add(label2);
         
         

    	JTextField abs;
    	JCheckBox zgn;
    	JCheckBox ber;
    	JCheckBox send;
    	
    	
    	for(int i=0;i<anzahl;i++){
    		
    	panel.add(new JLabel(vornamenliste[i]));
    		 
        panel.add(new JLabel(nachnamenliste[i]));
        
        //bericht checkbox
    	ber = new JCheckBox("Bericht"); berchkboxs.add(ber); panel.add(ber);
    	if(berichtvorliegendliste[i]==1)ber.setSelected(true);
    	else ber.setSelected(false);
    	//Zeugnis checkbox 
    	zgn = new JCheckBox("Zeugnis"); zgchkboxs.add(zgn); panel.add(zgn);
    	if(zeugnisvorliegendliste[i]==1)zgn.setSelected(true);
    	else zgn.setSelected(false);
    	//empfänger
    	abs= new JTextField(empfängerliste[i]+"@htw-dresden.de"); txtfields.add(abs); panel.add(abs);
    	//senden
    	send = new JCheckBox("senden"); senden.add(send); panel.add(send);
    	send.setSelected(true);
    	}
    
    	  panel.add(mailsenden);
    	  mailsenden.addActionListener(this);
        this.setTitle("Mail senden");
        this.setSize(750, 200);
        this.add(panel);
        
        }   
 
    
    //wenn Button gedrückt wird
    public void actionPerformed (ActionEvent ae){
    	//wenn Button geklickt, absender, empfänger, pw speichern
        if(ae.getSource() == this.mailsenden){
            String absender = Jabs.getText();
         
			char[] pw = Jpw.getPassword();

            
			
			int anz = Integer.parseInt(label2.getText());
	    	String[] empfängerliste = new String[anz];
	    	boolean[]	berichtliste = new boolean[anz];
	    	boolean[]	zeugnisliste = new boolean[anz];
	    	boolean[]		sendenliste = new boolean[anz];
	    	JTextField test;
	    	JCheckBox bericht;
	    	JCheckBox zeugnis;
	    	JCheckBox send;
	    	
	    	//speichern
			for(int i=0;i<anz;i++){
				test = txtfields.get(i);
	        	empfängerliste[i] = test.getText();
	        	bericht = berchkboxs.get(i);
	        	berichtliste[i] = bericht.isSelected();
	        	
	        	zeugnis = zgchkboxs.get(i);
	        	zeugnisliste[i] = zeugnis.isSelected();
	        	
	        	send = senden.get(i);
	        	sendenliste[i] = send.isSelected();
	        }
			
			
			
			
           //Pdf erzeugen 
            createPDF ps = new createPDF();
            
            //mails in for schleife senden          
            for(int i=0;i<anz;i++){
            	
            	if(sendenliste[i]==true){
            
            try {
				ps.createPdf(PDF,berichtliste[i],zeugnisliste[i],vornamenliste[i],nachnamenliste[i],matrklliste[i],
						stdgrpliste[i],profliste[i],betriebsliste[i]
						);} 
            catch (DocumentException e) {
					e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			try {
				if(mail.sendMail(absender, empfängerliste[i], pw)==true){
					label.setText(("Mail erfolgreich gesendet"));
				}
				else label.setText(("Mail nicht gesendet"));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Pdf vom aktuellen Verzeichniss entfernen
			löschePdf(PDF);
			
			
            	}
            
			//ende der mail senden for schleife
            }
        }
    
    }
    
    public static void main(String[] args)
    {
    	
    
    	String array [] []  = { {"hans", "wurst", "34049" , "beck", "043", "wurstbetrieb","0","1","s68551"},
    			{"jakob", "heltzig", "34050" , "beck", "043", "prbetrieb","1","1","s68551"}, 
    			{"seppus", "härtelus", "33350" , "beck", "043", "prbetrieb","0","0","s68551"} };
    	
    	
        guiMail bl = new guiMail(array);
        bl.setVisible(true);
        
        
    }
}