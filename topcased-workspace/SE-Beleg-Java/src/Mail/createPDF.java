package Mail;

import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class createPDF {
	
    public void createPdf(String filename, boolean bericht, boolean zeugnis, String Name, String Vorname, String MatrNr, 
    		String Stdgrp, String betrProf, String Betrieb) throws DocumentException,
            IOException {
        Document document = new Document();
        document.open();


        Chunk erste = new Chunk("HTW Dresden (FH)");
        document.add(erste);
        document.add(new Paragraph(""));
        
        Chunk zweite = new Chunk("Fachbereich Informatik/Mathematik");
        document.add(zweite);
        document.add(new Paragraph(""));
        
        Chunk dritte = new Chunk("- Der Praktikumsverantwortliche -");
        document.add(dritte);
        document.add(new Paragraph(""));
        
        Chunk vierte = new Chunk("__________________________________________________________________________");
        document.add(vierte);
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk fünfte = new Chunk("       ");
        document.add(fünfte);
        Chunk underline = new Chunk("Empfehlung über die Anerkennung des praktischen Studiensemesters");
        underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
        document.add(underline);
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk sechste = new Chunk("Student:                                        ");
        document.add(sechste);
        Chunk Student = new Chunk(Vorname + " "+ Name);
        document.add(Student); 
        document.add(new Paragraph(""));
        
        Chunk siebte = new Chunk("Matrikel Nummer:                         ");
        document.add(siebte);
        Chunk matr = new Chunk(MatrNr);
        document.add(matr); 
        document.add(new Paragraph(""));
        
        Chunk achte = new Chunk("Betreuender Prof:                         ");
        document.add(achte);
        Chunk prof = new Chunk (betrProf);
        document.add(prof); 
        document.add(new Paragraph(""));
        
        Chunk neunte = new Chunk("Studiengruppe:                             ");
        document.add(neunte);
        Chunk grp = new Chunk (Stdgrp);
        document.add(grp); 
        document.add(new Paragraph(""));
        
        Chunk zehnte = new Chunk("Praktikumsbetrieb:                        ");
        document.add(zehnte);
        Chunk betr = new Chunk (Betrieb);
        document.add(betr); 
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk elfte = new Chunk("Thema:");
        document.add(elfte);
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk zwölfte = new Chunk("Hiermit empfehle ich dem Praktikumsverantwortlichen des FB");
        document.add(zwölfte);
        document.add(new Paragraph(""));
        
        Chunk dreizehnte = new Chunk("die Anerkennung / Nichtanerkennung des praktischen Studiensemesters");
        document.add(dreizehnte);
        document.add(new Paragraph(""));
        
        Chunk vierzehnte = new Chunk("");
        if(bericht==true) vierzehnte = new Chunk("der Praktikumsbericht liegt vor");
        else vierzehnte = new Chunk("der Praktikumsbericht liegt nicht vor");
        
        
        
        document.add(vierzehnte);
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk fünfzehnte = new Chunk("");
        if(zeugnis == true)fünfzehnte = new Chunk("das Praktikumszeugnis liegt vor");
        else fünfzehnte = new Chunk("das Praktikumszeugnis liegt nicht vor");
        
        document.add(fünfzehnte);
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk sechzehnte = new Chunk("Einschätzung durch den fachlich betreuenden HSL");
        document.add(sechzehnte);
       
        
      for(int i=0;i<17;i++){  document.add( Chunk.NEWLINE );};
      
        	
   
        Chunk siebzehnte = new Chunk("Unterschrift HSL                                                   " +
        		"                                                   Datum");
        document.add(siebzehnte);
        
        
        document.close();

    }
    

} 
