package Import;
//blabal

import java.io.*;

import javax.swing.table.DefaultTableModel;


//Konstruktoren
//------------------
//	csvImport(String file, String delimiter, char [] spaltenreihenfolge)
//
//	Parameter
//	------------------
//	file : e.g D:\\Programme\\Topcased-5.3.0\\workspace\\Mappe2.csv  - ACHTUNG ESCAPESEQUENZ NICHT VERGESSEN!
//	delimiter: e.g ';' -Symbol welches die Spaltenwerte untereinander trennt
//	spaltenreihenfolge: e.g {'n','v','m','b','g'} - Reihenfolge  der Spalten in der *.csv Datei
//				n - name , v - vorname , m - matrikelnr, b - bibliotheksnr, s - studiengruppenr
//Funktionen
//------------------
//	parseIt()
//		Return: StudentContainer



public class csvImport {
//Eigenschaften

	String file, delimiter, errorlog="Beim Import sind Fehler aufgetreten:\n";
	int lines=0;
	Boolean importerrors=false,MetaDatenKopfZeile = true; //Wenn in der ersten Zeile Spaltenbezeichner stehen true, dann wird diese Zeile übersprungen
	char [] spaltenreihenfolge;
	DefaultTableModel datamodel = new DefaultTableModel(0,5);
	
//Konstruktor
	public csvImport(String file, String delimiter, boolean t){
		this.file=file;
		this.delimiter=delimiter;
		this.MetaDatenKopfZeile=t;
		this.spaltenreihenfolge= new  char [] {'n','v','m','b','g'};

		
	}

//Funktionen
	public DefaultTableModel parseIt() {
		parseCsvFile();
		return datamodel;
	}

//csv Parser
    private void parseCsvFile()  {
        int i=0;//Zeileniteration
    	
        try {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


    }
// Verarbeitet eine Zeile
    public void processCsvLine(final String data) {
    	
        String [] splittedline = data.split(delimiter);
        
        String[] newrow = { splittedline[0], splittedline[1],splittedline[2], splittedline[3],splittedline[4]};
 		datamodel.addRow(newrow);
 		lines++;

    }
    
    public int getcount(){
    	return this.lines;
    }
    public boolean getImportErrors(){return importerrors;}
    public String getErrorLog(){return errorlog;}

}
