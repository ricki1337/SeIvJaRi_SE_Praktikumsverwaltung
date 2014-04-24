package csvImport;


import java.io.*;


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
	StudentContainer container;
	String delimiter, errorlog="Beim Import sind Fehler aufgetreten:\n";
	String file;
	Boolean importerrors=false,MetaDatenKopfZeile = true; //Wenn in der ersten Zeile Spaltenbezeichner stehen true, dann wird diese Zeile übersprungen
	char [] spaltenreihenfolge;
	
//Konstruktor
	csvImport(String file, String delimiter, char [] spaltenreihenfolge){
		this.file=file;
		this.delimiter=delimiter;
		this.spaltenreihenfolge=spaltenreihenfolge;
		container = new StudentContainer();
		
	}

//Funktionen
	public StudentContainer parseIt() {
		parseCsvFile();
		return this.container;
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
    	
        String [] a = data.split(delimiter);
        
        int matrikelnr=0,bibliotheksnr=0;
        String vorname="",name="", studiengruppe="";
        
        for (int b=0; b<5 ; b++){
	        try{
		        	switch (spaltenreihenfolge[b]){
		        		case 's':studiengruppe=a[b]; break;	
		        		case 'm':matrikelnr=Integer.parseInt(a[b]); break;
		            	case 'n':name=a[b]; break;
		            	case 'v':vorname=a[b]; break;
		            	case 'b':bibliotheksnr=Integer.parseInt(a[b]); break;	

		        }
	        }
        
        
	        catch(NumberFormatException e){
	        	importerrors=true;
	        	errorlog+=e.toString()+"\n";
	        	//System.out.println(e.toString());//Kann nach fertigstellung entfernt werden
	        }
        }
        container.addStudent(name, vorname,studiengruppe, bibliotheksnr,matrikelnr); 

    }
    
    public boolean getImportErrors(){return importerrors;}
    public String getErrorLog(){return errorlog;}

}
