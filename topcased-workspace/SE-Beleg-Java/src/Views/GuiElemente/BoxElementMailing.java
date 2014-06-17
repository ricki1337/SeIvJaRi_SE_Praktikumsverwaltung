package Views.GuiElemente;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Config;
import ConfigParser.Debug;
import Views.Interfaces.BasicBox;
import Views.Interfaces.MailBox;
import Views.Interfaces.MailBoxCtrl;
 

public class BoxElementMailing extends JPanel implements BasicBox, MouseListener, MailBox{
   
	   	ArrayList<JTextArea> txtareas = new ArrayList<JTextArea>();
		ArrayList<JCheckBox> zeugnisCheckBoxList = new ArrayList<JCheckBox>();
		ArrayList<JCheckBox> berichtCheckBoxList = new ArrayList<JCheckBox>();
		ArrayList<JCheckBox> senden = new ArrayList<JCheckBox>();
		ArrayList<JTextField> txtfields = new ArrayList<JTextField>();
		ArrayList<JLabel> statusList = new ArrayList<JLabel>();

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
		
		
		private JPanel jp_maildata;
		private JTextField jtf_absender;
		private JPasswordField jpw_password;
		private JLabel jl_mailStatus;
		
		private MailBoxCtrl controller;
	
	public BoxElementMailing(MailBoxCtrl controller){
		this.controller = controller;
		   	
		initComponents();
		setComponentValues();
		setMailingList();
		setToolTip();
    } 

	
	
	
	@Override
	public void initComponents() {
		jp_maildata = new JPanel();
		
		jtf_absender = new JTextField();
		jtf_absender.setColumns(40);
		
		JLabel jl_absender = new JLabel("E-Mail Absender:");
		
		JLabel jl_password = new JLabel("Account-Passwort:");
		
		jpw_password = new JPasswordField();
		
		jl_mailStatus = new JLabel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jp_maildata, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(jl_password, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jl_absender, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(jpw_password)
								.addComponent(jtf_absender, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(jl_mailStatus, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jl_absender)
						.addComponent(jtf_absender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jl_mailStatus))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jl_password)
						.addComponent(jpw_password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(jp_maildata, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
					.addGap(17))
		);
		setLayout(groupLayout);
        
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
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}
	

	@Override
	public String getSenderEmailAdress() {
		Config.setProperties("emailAdress",jtf_absender.getText());
		return jtf_absender.getText();
	}


	@Override
	public char[] getSenderEmailPassword() {
		return jpw_password.getPassword();
	}


	@Override
	public void setMailSend(int statusLabelIndex, boolean status) {
		JLabel label = statusList.get(statusLabelIndex);
		if(status){
			label.setText("erfolgreich gesendet");
			label.setBackground(Color.GREEN);
		}else {
			label.setText("nicht gesendet");
			label.setBackground(Color.RED);
		}
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

	private void setMailingList(){
		int anzahl = dataArray.length;
		jp_maildata.setLayout( new GridLayout(/*3*/ 0, 7, 6, 3) );
		JTextField jcb_empfaenger;
    	JCheckBox jcb_zeugnis;
    	JCheckBox jcb_bericht;
    	JCheckBox jcb_send;
    	JLabel jl_status;
    	
    	for(int i=0;i<anzahl;i++){
    		JPanel mailData = new JPanel();
    		mailData.setLayout(new GridLayout(0, 7, 6, 3));
    		jp_maildata.add(new JLabel(vornamenliste[i]));
	    		 
    		jp_maildata.add(new JLabel(nachnamenliste[i]));
	        
	        //bericht checkbox
	    	jcb_bericht = new JCheckBox("Bericht"); 
	    	jcb_bericht.addMouseListener(this);
	    	berichtCheckBoxList.add(jcb_bericht);
	    	jp_maildata.add(jcb_bericht);
	    	if(berichtvorliegendliste[i])
	    		jcb_bericht.setSelected(true);
	    	else 
	    		jcb_bericht.setSelected(false);
	    	
	    	//Zeugnis checkbox 
	    	jcb_zeugnis = new JCheckBox("Zeugnis");
	    	jcb_zeugnis.addMouseListener(this);
	    	zeugnisCheckBoxList.add(jcb_zeugnis);
	    	jp_maildata.add(jcb_zeugnis);
	    	if(zeugnisvorliegendliste[i])
	    		jcb_zeugnis.setSelected(true);
	    	else 
	    		jcb_zeugnis.setSelected(false);
	    	
	    	//empfänger
	    	jcb_empfaenger= new JTextField(recipientEmailList[i]+"@htw-dresden.de");
	    	txtfields.add(jcb_empfaenger);
	    	jp_maildata.add(jcb_empfaenger);
	    	
	    	//senden
	    	jcb_send = new JCheckBox("senden");
	    	jcb_send.addMouseListener(this);
	    	jcb_send.setSelected(true);
	    	senden.add(jcb_send);
	    	jp_maildata.add(jcb_send);
	    	
	    	// status
	    	jl_status = new JLabel();
	    	statusList.add(jl_status);
	    	jp_maildata.add(jl_status);
    	}
	}

	@Override
	public void setComponentValues() {
		String emailAdress = Config.getProperties("emailAdress");
		if(emailAdress == null) emailAdress = new String();
		jtf_absender.setText(emailAdress);
		
		dataArray = controller.getMailData();
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
    	
    	for (int i = 0; i < dataArray.length; i++){
			
		    vornamenliste[i]=dataArray[i][0];
    		nachnamenliste[i]=dataArray[i][1];
    		matrklliste[i]=dataArray[i][2];
    		profliste[i]=dataArray[i][3];
    		studiengruppeListe[i]=dataArray[i][4];
    		betriebsliste[i]=dataArray[i][5];
    		berichtvorliegendliste[i]=Boolean.parseBoolean(dataArray[i][6]);
    		zeugnisvorliegendliste[i]=Boolean.parseBoolean(dataArray[i][7]);
    		recipientEmailList[i]=dataArray[i][8];
    	}
		
	}


	@Override
	public void setComponentEventHandler() {}


	@Override
	public void refreshContent() {}
	
	@Override
	public void setMailingStatus(String statusText){
		jl_mailStatus.setText(statusText);
		repaint();
	}
}