package ConfigParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import Controller.ErrorManager;

/**
 * Erstellt und verwaltet Parameter in einer {@link Properties} Instanz.<br>
 * Die Parameter k�nnen �berall gesetzt und abgefragt werden.
 * @see Properties
 */
public class Config {
	private static Properties  properties = new Properties( System.getProperties() );
	
	private Config(){
	}
	
	/**
	 * Gibt den Wert einer Property zur�ck.
	 * @param key 	Der Name einer Property
	 * @return		Den Wert der Property wenn die Property existiert, sonst null.
	 */
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
	
	/**
	 * Setzt den Wert einer angegebenen Property.
	 * @param key		Name der zu setzenden Property.
	 * @param value		Wert der Property.
	 * @return			Gibt bei Erfolg true, sonst false zur�ck.
	 */
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
