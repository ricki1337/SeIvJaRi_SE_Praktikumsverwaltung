package ConfigParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import org.xml.sax.ErrorHandler;

import Controller.ErrorManager;


public class Config {
	private static Properties  properties = new Properties( System.getProperties() );
	
	private Config(){
	}

	public static String getProperties(String key){
		Reader reader = null;

		try{
		  reader = new FileReader( "properties.txt" );
		  
		  properties.load(reader);
		  String value = properties.getProperty(key);
		  reader.close();
		  return value;
		}
		catch ( IOException e )
		{
			//properties konnten nicht geladen werde, nicht weiter schlimm wir schreiben nach erfolgreicher anmeldung einfach ein neues file
			return null;
		 
		}
		finally
		{
		  try {
			  reader.close();
		  }
		  catch(Exception e){}
		}

	}
	
	public static boolean setProperties(String key, String value){
		Writer writer = null;
		
		try {
			writer = new FileWriter( "properties.txt" );
	
			//Properties setzten
			properties.setProperty( key, value );
			  
			//Properties mit Absatzbezeichnung (e.g "Database Configuration") in Datei schreiben
			properties.store( writer, "Database Configuration");
			return true;
			
		} catch (IOException e) {
			ErrorManager errorHandler = new ErrorManager(e);
			if(errorHandler.retry)
				Config.setProperties(key,value);
		}

		return false;
	}
}
