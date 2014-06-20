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


	String file, delimiter, errorlog="Beim Import sind Fehler aufgetreten:\n";
	int lines=0;
	Boolean importerrors=false,MetaDatenKopfZeile = true; //Wenn in der ersten Zeile Spaltenbezeichner stehen true, dann wird diese Zeile übersprungen
	DefaultTableModel datamodel = new DefaultTableModel(0,5);
	

	public CsvImport(String file, String delimiter, boolean t){
		this.file=file;
		this.delimiter=delimiter;
		this.MetaDatenKopfZeile=t;

	}


	public DefaultTableModel parseIt() throws IOException, ArrayIndexOutOfBoundsException {
		parseCsvFile();
		return datamodel;
	}

//csv Parser
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
// Verarbeitet eine Zeile
    public void processCsvLine(final String data) throws ArrayIndexOutOfBoundsException {
    	
        String [] splittedline = data.split(delimiter);
        
        String[] newrow = { splittedline[0], splittedline[1],splittedline[2], splittedline[3],splittedline[4]};
 		datamodel.addRow(newrow);
 		lines++;

    }
    
    public int getcount(){
    	return this.lines;
    }
    
    public boolean getImportErrors(){
    	return importerrors;
    }
    
    public String getErrorLog(){
    	return errorlog;
    }

}