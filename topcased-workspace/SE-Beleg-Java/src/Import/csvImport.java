package Import;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import Controller.ErrorManager;


//Konstruktoren
//------------------
//	csvImport(String file, String delimiter, char [] spaltenreihenfolge)
//
//	Parameter
//	------------------
//	delimiter: e.g ';' -Symbol welches die Spaltenwerte untereinander trennt
//	spaltenreihenfolge: e.g {'n','v','m','b','g'} - Reihenfolge  der Spalten in der *.csv Datei
//				n - name , v - vorname , m - matrikelnr, b - bibliotheksnr, s - studiengruppenr



public class CsvImport {

		private String file, delimiter, errorlog="Beim Import sind Fehler aufgetreten:\n";
		private int lines=0;
		private Boolean importerrors=false,MetaDatenKopfZeile = true; //Wenn in der ersten Zeile Spaltenbezeichner stehen true, dann wird diese Zeile übersprungen
		private DefaultTableModel datamodel = new DefaultTableModel(0,5);
	

	/**
	 * Stellt Methoden zur Voransicht der zu importierenden Daten bereit.
	 * @param file							Name der Datei, welche importiert wird
	 * @param delimiter						Datentrennzeichen, z.B.: ',' oder ';'
	 * @param firstLineIncludesColumnNames	True wenn Kopfzeile = Spaltennamen, False wenn nicht.
	 */
	public CsvImport(String file, String delimiter, boolean firstLineIncludesColumnNames){
		this.file=file;
		this.delimiter=delimiter;
		this.MetaDatenKopfZeile=firstLineIncludesColumnNames;	}

	/**
	 * Liest den Dateiinhalt in ein {@link DefaultTableModel} ein und gibt dieses zurück.
	 * @return	{@link DefaultTableModel} mit den eingelesenen Informationen der Datei.
	 * @throws IOException 
	 * @throws ArrayIndexOutOfBoundsException 
	 */
	public DefaultTableModel parseIt() throws ArrayIndexOutOfBoundsException, IOException {
		parseCsvFile();
		return datamodel;
	}


	/**
	 * Geht alle Zeilen der Datei durch, spaltet die Informationen gemäß des Delimiters auf und schreibt die Info in das interne {@link DefaultTableModel}.
	 */

    private void parseCsvFile() throws IOException, ArrayIndexOutOfBoundsException  {
        int i=0;//Zeileniteration

	    	final BufferedReader reader = new BufferedReader(new FileReader(file));
	        String current;
			current = reader.readLine();
		
		        while (current != null) {
		        	if(i==0 && MetaDatenKopfZeile){} 	//überspringe Zeile #1 wenn dort Spaltenbezeichnungen stehen
		        		else { processCsvLine(current);}
		        	current = reader.readLine();
		        	i++;
		        }
		   reader.close();	
    }
    
    /**
     * Zerteilt die übergebene Datenzeile gemäß dem Delimiter und fügt sie dem internen  {@link DefaultTableModel} hinzu.
     * @param data	Ausgelesene Zeile der angegebenen Datei.
     */
    public void processCsvLine(final String data) throws ArrayIndexOutOfBoundsException {
    	
        String [] splittedline = data.split(delimiter);
        
        String[] newrow = { splittedline[0], splittedline[1],splittedline[2], splittedline[3],splittedline[4]};
 		datamodel.addRow(newrow);
 		lines++;

    }
    
    /**
     * Liefert den aktuelle Zeilenanzahl.
     * @return	Zeilenanzahl
     */
    public int getcount(){
    	return this.lines;
    }
    
    /**
     * Informiert über aufgetretene Fehler.
     * @return	True wenn ein Fehler aufgetreten ist, sonst False.
     */
    public boolean getImportErrors(){
    	return importerrors;
    }
    
    /**
     * Gibt die Fehlermeldung zurück.
     * @return	Fehlermeldung
     */
    public String getErrorLog(){
    	return errorlog;
    }

}