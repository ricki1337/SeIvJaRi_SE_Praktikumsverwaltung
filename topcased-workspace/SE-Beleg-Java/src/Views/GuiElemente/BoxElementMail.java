package Views.GuiElemente;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ConfigParser.Debug;
import Views.Interfaces.BasicBox;
import Views.Interfaces.MailBox;
import Views.Interfaces.MailBoxCtrl;
 

public class BoxElementMail extends JPanel implements BasicBox, MouseListener, MailBox{
   
	   	ArrayList<JTextArea> txtareas = new ArrayList<JTextArea>();
		ArrayList<JCheckBox> zeugnisCheckBoxList = new ArrayList<JCheckBox>();
		ArrayList<JCheckBox> berichtCheckBoxList = new ArrayList<JCheckBox>();
		ArrayList<JCheckBox> senden = new ArrayList<JCheckBox>();
		ArrayList<JTextField> txtfields = new ArrayList<JTextField>();

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
		String[] studiengruppeListe;
		String[] betriebsliste;
		boolean[]	berichtvorliegendliste;
		boolean[]	zeugnisvorliegendliste;
		String[] recipientEmailList;
		String[][] dataArray;
		
		private MailBoxCtrl controller;
	
	public BoxElementMail(MailBoxCtrl controller){
		this.controller = controller;
		dataArray = controller.getMailData();   	
		initComponents();
		setToolTip();
    } 

	@Override
	public void initComponents() {
		int anzahl = dataArray.length;
    	
    	
    	vornamenliste = new String[anzahl];
    	nachnamenliste = new String[anzahl];
    	matrklliste = new String[anzahl];
    	profliste = new String[anzahl];
    	studiengruppeListe = new String[anzahl];
    	betriebsliste = new String[anzahl];
    	berichtvorliegendliste = new boolean[anzahl];
    	zeugnisvorliegendliste = new boolean[anzahl];
    	recipientEmailList = new String[anzahl];
    	
    	for (int i = 0; i < dataArray.length; i++)
		{
			
		    vornamenliste[i]=dataArray[i][0];
    		nachnamenliste[i]=dataArray[i][1];
    		matrklliste[i]=dataArray[i][2];
    		profliste[i]=dataArray[i][3];
    		studiengruppeListe[i]=dataArray[i][4];
    		betriebsliste[i]=dataArray[i][5];
    		berichtvorliegendliste[i]=Boolean.parseBoolean(dataArray[i][6]);
    		zeugnisvorliegendliste[i]=Boolean.parseBoolean(dataArray[i][7]);
    		recipientEmailList[i]=dataArray[i][8];}
    	
    	
    	 
    	 setLayout( new GridLayout(/*3*/ 0, 6, 6, 3) );
    	 
    	 add(Jm);
         add(Jabs);
         add(Jp);
         add(Jpw);
         add(label);
         label2.setText(String.valueOf(anzahl));
         add(label2);
         
         

    	JTextField jcb_empfaenger;
    	JCheckBox jcb_zeugnis;
    	JCheckBox jcb_bericht;
    	JCheckBox jcb_send;
    	
    	
    	for(int i=0;i<anzahl;i++){
    		
    	add(new JLabel(vornamenliste[i]));
    		 
        add(new JLabel(nachnamenliste[i]));
        
        //bericht checkbox
    	jcb_bericht = new JCheckBox("Bericht"); 
    	jcb_bericht.addMouseListener(this);
    	berichtCheckBoxList.add(jcb_bericht);
    	add(jcb_bericht);
    	if(berichtvorliegendliste[i])
    		jcb_bericht.setSelected(true);
    	else 
    		jcb_bericht.setSelected(false);
    	
    	//Zeugnis checkbox 
    	jcb_zeugnis = new JCheckBox("Zeugnis");
    	jcb_zeugnis.addMouseListener(this);
    	zeugnisCheckBoxList.add(jcb_zeugnis);
    	add(jcb_zeugnis);
    	if(zeugnisvorliegendliste[i])
    		jcb_zeugnis.setSelected(true);
    	else 
    		jcb_zeugnis.setSelected(false);
    	
    	//empfänger
    	jcb_empfaenger= new JTextField(recipientEmailList[i]+"@htw-dresden.de");
    	txtfields.add(jcb_empfaenger);
    	add(jcb_empfaenger);
    	
    	//senden
    	jcb_send = new JCheckBox("senden");
    	jcb_send.addMouseListener(this);
    	jcb_send.setSelected(true);
    	senden.add(jcb_send);
    	add(jcb_send);
    	
    	}
    
    	add(mailsenden);
    	mailsenden.addMouseListener(this);
        this.setSize(750, 200);
        
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int index;
		if((index = senden.indexOf(e.getComponent())) != -1)
			controller.setSendFlag(index,senden.get(index).isSelected());
		
		if((index = berichtCheckBoxList.indexOf(e.getComponent())) != -1)
			controller.setBerichtFlag(index,berichtCheckBoxList.get(index).isSelected());
		
		if((index = zeugnisCheckBoxList.indexOf(e.getComponent())) != -1)
			controller.setZeugnisFlag(index,zeugnisCheckBoxList.get(index).isSelected());
		
		if(e.getComponent() == mailsenden)
			controller.buttonSendMailClicked();
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}
	

	@Override
	public String getSenderEmailAdress() {
		return Jabs.getText();
	}


	@Override
	public char[] getSenderEmailPassword() {
		return Jpw.getPassword();
	}


	@Override
	public void setMailsSend(boolean status) {
		if(status)
			label.setText(("Mail erfolgreich gesendet"));
		else 
			label.setText(("Mail nicht gesendet"));
	}


	@Override
	public String getRecipientEmailAdress(int index) {
		return txtfields.get(index).getText();
	}
	
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}


	@Override
	public void mouseExited(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent arg0) {}


	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	@Override
	public void setComponentNames() {}


	@Override
	public void setComponentValues() {}


	@Override
	public void setComponentEventHandler() {}


	@Override
	public void refreshContent() {}
}