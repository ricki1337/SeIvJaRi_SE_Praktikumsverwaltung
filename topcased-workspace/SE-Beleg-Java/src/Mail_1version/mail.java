import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
import javax.mail.internet.*;
import javax.mail.*;

import com.itextpdf.text.DocumentException;


 
public class mail
{

	public static boolean sendMail(String Absender, String empfänger, char[] Pw) throws Exception{
		// Google-Mail mit TLSv1 und Auth
		 String host="webmail.htw-dresden.de";
		 int port=25;
		 String user=Absender;
		 String pass=new String(Pw);
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
		 	
		 Session session=Session.getInstance(props);
		 Transport transport=session.getTransport("smtp");
		 transport.connect(host, port, user, pass);

		 Address[] addresses=InternetAddress.parse(empfänger);

		 Message message=new MimeMessage(session);
		 message.setFrom(new InternetAddress(user));
		 message.setRecipients(Message.RecipientType.TO, addresses);
		 //Betreff
		 message.setSubject("Betreff");
		  
		 BodyPart messageBodyPart = new MimeBodyPart();

		 //Inhalt
		 messageBodyPart.setText("hi");

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
		 return true;
		 	
		 
	 }
	
 }