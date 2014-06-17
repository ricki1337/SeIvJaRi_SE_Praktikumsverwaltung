package Mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import Controller.ErrorManager;
import Models.Datenbank.Observer;


 
public class MailSender
{
	private Transport transport = null;
	private String user = null;
	private String pass = null;
	private Session session = null;
	private String status = null;
	private Observer observer;
	
	public MailSender(Observer observer){
		this.observer = observer;
		setStatus("idle");
	}
	
	public MailSender(String sender, char[] password, String recipients,Observer observer){
		this(observer);
		if(connect(sender,password))
			sendMail(recipients);
	}
	
	public boolean connect(String sender, char[] password){
		if(transport != null && transport.isConnected()) 
			return transport.isConnected();
		
		if(sender == null | password == null){
			setStatus("Falsche Verbindungsdaten.");
			return false;
		}
			
		
		setStatus("Verbinde mit Postfach...");
		// Google-Mail mit TLSv1 und Auth
		 String host="webmail.htw-dresden.de";
		 int port=25;
		 user = sender;
		 pass= new String(password);
		 			/*
		 			 Server-Namen (postausgangsserver) und Port vom E-Mail-Provider. 
		 			 */

		 Properties props=new Properties();
		 //Authentifizierung aktivieren
		 props.put("mail.smtp.auth", "true");
		 //SSLv3/TLSv1
		 props.put("mail.smtp.starttls.enable", "true");
		 //Certificate
		 props.put("mail.smtp.ssl.trust", "webmail.htw-dresden.de");
		 	
		 session=Session.getInstance(props);
		 try {
			 transport=session.getTransport("smtp");
			 transport.connect(host, port, user, pass);
		} catch (MessagingException e) {
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				connect(sender,password);
		}
		 if(transport.isConnected()) 
			 setStatus("erfolgreich Verbunden...");
		 else 
			 setStatus("Verbindung nicht möglich...");
		return transport.isConnected();
	}

	public boolean sendMail(String recipients){
		if(transport == null) return false;

		if(!transport.isConnected())
			if(!connect(user,pass.toCharArray())) return false;
		
		//test
		recipients = "s68424@htw-dresden.de";
		
		
		setStatus("Sende E-Mail an: " + recipients);
		 Address[] addresses;
		 Message message=new MimeMessage(session);
		try {
			addresses = InternetAddress.parse(recipients);
			message.setFrom(new InternetAddress(user));
		
			 message.setRecipients(Message.RecipientType.TO, addresses);
			 //Betreff
			 message.setSubject("Praktikumsvertrag");
			  
			 BodyPart messageBodyPart = new MimeBodyPart();
	
			 //Inhalt
			 messageBodyPart.setText("Sehr geehrte Damen und Herren,\n hiermit senden wir Ihnen die Empfehlung über die Anerkennung des praktischen Studiensemesters zu. Mit freundlichen Gr\u00FC\u00DFen, ");
	
			 Multipart multipart = new MimeMultipart();
			 multipart.addBodyPart(messageBodyPart);
	
			 // Anhang
			 messageBodyPart = new MimeBodyPart();
			 String filename = "anerkennung.pdf";
			 DataSource source = new FileDataSource(filename);
			 messageBodyPart.setDataHandler(new DataHandler(source));
			 messageBodyPart.setFileName(filename);
			 multipart.addBodyPart(messageBodyPart);
	
			 // Put parts in message
			 message.setContent(multipart);
	
	
			 transport.sendMessage(message, addresses);
			 
	
			 transport.close();
		} catch (MessagingException e) {
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				sendMail(recipients);
		}
		setStatus("E-Mail erfolgreich versandt");
		return true;
	 }
	
	public void setStatus(String status){
		String[] observerInfo = new String[1];
		observerInfo[0] = status;
		observer.refresh(observerInfo);
		System.out.println(status);
		this.status = status;
	}
	
 }