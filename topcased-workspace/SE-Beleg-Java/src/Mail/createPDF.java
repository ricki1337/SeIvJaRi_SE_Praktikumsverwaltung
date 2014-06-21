package Mail;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Erzeugt die PDF, welche im Anhang an die Mail an die Betreuer versendet wird.
 *
 */
public class createPDF {
	
	/**
	 * Erstellt die PDF, welche im Anhang an die Mail an die Betreuer versendet wird.
	 * @param filename		Dateiname des Anhangs
	 * @param bericht		Liegt ein Bericht vor
	 * @param zeugnis		Liegt das Zeugnis des Praktikumsbetriebs vor
	 * @param Name			Nachname des Studenten
	 * @param Vorname		Vorname des Studenten
	 * @param MatrNr		Matrikelnummer
	 * @param Stdgrp		Studiengruppe
	 * @param betrProf		Betreuer
	 * @param Betrieb		Firmenname
	 * @throws DocumentException
	 * @throws IOException
	 */
    public void createPdf(String filename, boolean bericht, boolean zeugnis, String Name, String Vorname, String MatrNr, 
    		String Stdgrp, String betrProf, String Betrieb) throws DocumentException,
            IOException {
        Document document = new Document();
        
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
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
        
        Chunk f�nfte = new Chunk("       ");
        document.add(f�nfte);
        Chunk underline = new Chunk("Empfehlung �ber die Anerkennung des praktischen Studiensemesters");
        underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
        document.add(underline);
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk sechste = new Chunk("Student:                                            ");
        document.add(sechste);
        Chunk Student = new Chunk(Vorname + " "+ Name);
        document.add(Student); 
        document.add(new Paragraph(""));
        
        Chunk siebte = new Chunk("Matrikelnummer:                              ");
        document.add(siebte);
        Chunk matr = new Chunk(MatrNr);
        document.add(matr); 
        document.add(new Paragraph(""));
        
        Chunk achte = new Chunk("Betreuender Professor:                    ");
        document.add(achte);
        Chunk prof = new Chunk (betrProf);
        document.add(prof); 
        document.add(new Paragraph(""));
        
        Chunk neunte = new Chunk("Studiengruppe:                                 ");
        document.add(neunte);
        Chunk grp = new Chunk (Stdgrp);
        document.add(grp); 
        document.add(new Paragraph(""));
        
        Chunk zehnte = new Chunk("Praktikumsbetrieb:                            ");
        document.add(zehnte);
        Chunk betr = new Chunk (Betrieb);
        document.add(betr); 
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk elfte = new Chunk("Thema:                                      ");
        document.add(elfte);
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk zw�lfte = new Chunk("Hiermit empfehle ich dem Praktikumsverantwortlichen des FB");
        document.add(zw�lfte);
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
        
        Chunk f�nfzehnte = new Chunk("");
        if(zeugnis == true)f�nfzehnte = new Chunk("das Praktikumszeugnis liegt vor");
        else f�nfzehnte = new Chunk("das Praktikumszeugnis liegt nicht vor");
        
        document.add(f�nfzehnte);
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        
        Chunk sechzehnte = new Chunk("Einsch�tzung durch den fachlich betreuenden HSL");
        document.add(sechzehnte);
       
        
      for(int i=0;i<17;i++){  document.add( Chunk.NEWLINE );};
      
        	
   
        Chunk siebzehnte = new Chunk("Unterschrift HSL                                                   " +
        		"                                                   Datum");
        document.add(siebzehnte);
        
        
        document.close();

    }
    

} 
