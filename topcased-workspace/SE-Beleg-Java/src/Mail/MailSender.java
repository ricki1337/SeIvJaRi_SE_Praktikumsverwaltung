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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import Controller.ErrorManager;
import Models.Interfaces.Observer;


/**
 * Stellt die Verbindung zum Postfach her und versendet E-Mails.
 */
public class MailSender
{
		private Transport transport = null;
		private String user = null;
		private String pass = null;
		private Session session = null;
		private String status = null;
		private Observer observer;
	
	/**
	 * Registriert einen Observer, welcher alle Statusinformationen übermittelt bekommt<br>
	 * und setzt den aktuellen Status auf "idle" 
	 * @param observer	Wird informiert, wenn sich der Status des MailSender Objektes ändert.
	 */
	public MailSender(Observer observer){
		this.observer = observer;
		setStatus("idle");
	}
	
	/**
	 * Registriert einen Observer, welcher alle Statusinformationen übermittelt bekommt<br>
	 * und versucht die Verbindung mit dem Postfach aufzubauen.
	 * @param sender		Postfach E-Mailadresse & Absender
	 * @param password		Passwort für das Postfach
	 * @param recipients	Empfänger der E-Mail
	 * @param observer		Wird informiert, wenn sich der Status des MailSender Objektes ändert.
	 */
	public MailSender(String sender, char[] password, String recipients,Observer observer){
		this(observer);
		if(connect(sender,password))
			sendMail(recipients);
	}
	
	/**
	 * Stellt eine Verbindung mit dem Postfach her<br>
	 * und informiert {@link Observer} über den aktuellen Status.
	 * 
	 * @param sender	Postfach E-Mailadresse
	 * @param password	Passwort für das Postfach
	 * @return			True wenn die Verbindung geklappt hat, sonst False
	 */
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

	/**
	 * Versendet die Mail an den übergebenen Empfänger und informiert {@link Observer} über den aktuellen Status.
	 * 
	 * @param recipients Empfänger-E-Mailadresse
	 * @return	True wenn die E-Mail gesendet wurde, sonst False
	 */
	public boolean sendMail(String recipients){
		if(transport == null) return false;

		if(!transport.isConnected())
			if(!connect(user,pass.toCharArray())) return false;
		
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
			 messageBodyPart.setText("Sehr geehrte Damen und Herren,\nhiermit senden wir Ihnen die Empfehlung über die Anerkennung des praktischen Studiensemesters zu. Mit freundlichen Gr\u00FC\u00DFen,\n\nder Praktikumsverantwortliche\nFakultät Mathematik / Informatik \nHTW - Dresden");
	
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
		setStatus("E-Mail(s) erfolgreich versandt");
		return true;
	 }
	
	/**
	 * Übermittelt den "status" den {@link Observer}. 
	 * @param status	aktueller Status.
	 */
	public void setStatus(String status){
		String[] observerInfo = new String[1];
		observerInfo[0] = status;
		observer.refresh(observerInfo);
		this.status = status;
	}
	
 }