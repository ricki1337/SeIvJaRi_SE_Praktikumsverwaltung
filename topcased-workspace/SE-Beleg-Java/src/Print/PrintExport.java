package Print;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * Übermittelt Daten an den Drucker und initialisiert den Druckdialog.
 */
public class PrintExport {

	static Configuration cfg;
	private Template template;
	private Map<String, Object> input= new HashMap<String, Object>();
	private List<ValuePrintObject> tableData= new ArrayList<ValuePrintObject>();
	private String[][] printData;

	/**
	 * Initialisiert den Druckdialog und erstellt das zu druckende Dokument auf Grundlage der übermittelten Daten.
	 * @param printData	Print-Datenarray in Form {Matrikelnr, Studentenname, Bibliotheksnr, Studiengruppe, Firmenname}
	 * @throws Exception
	 */
	public PrintExport(String[][] printData) throws Exception {
		this.printData = printData;
		this.init();		
		this.setData();
		this.print();
	}
	
	/**
	 * Initialisiert alle Notwendigen Umgebungsvariablen.<br>
	 * {@link Printexport.setData(String[][] printData, String title,String headline)} und<br>
	 * {@link Printexport.print()} müssen separat aufgerufen werden.
	 * @throws Exception
	 */
	public PrintExport() throws Exception {
		init();
	}

	/**
	 * Initialisiert die {@link FreeMarker} Druckumgebung.
	 * @throws Exception
	 * @see http://freemarker.org/docs/
	 */
	
	private void init() throws Exception {

		// Configure FreeMarker
		//
		// You should do this ONLY ONCE, when your application starts,
		// then reuse the same Configuration object elsewhere.
		cfg = new Configuration();

		// Where do we load the templates from:
		cfg.setClassForTemplateLoading(PrintExport.class, "templates");

		// Some other recommended settings:
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.GERMANY);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		//Template als String aus der Klasse PdfContent laden
		template = new Template("name",new StringReader(PdfContent.getPdfContent()),new Configuration());
	}

	/**
	 * Setzt die Daten, welche im Drucktemplate eingefügt werden.
	 */
	private void setData() {
		
		input.put("title", "Template Export Sample fuer Praktikumsexport Funktion?");

		input.put("headline", "Folgende(r) Student(en) absolviert(en) das Praxisprojekt erfolgreich:");

		// Liste füllen mit gewünschten Daten bzw weitergabe an das
		// entsprechende Objekt welche mit set/get arbeitet
		for (String[] rowIndex : printData) {
			tableData.add(new ValuePrintObject(rowIndex[0], rowIndex[1], rowIndex[2], rowIndex[3], rowIndex[4]));
		}
		input.put("tableData", tableData);
	}
	
	/**
	 * Setzt die Daten, welche im Drucktemplate eingefügt werden.
	 * 
	 * @param printData	Print-Datenarray in Form {Matrikelnr, Studentenname, Bibliotheksnr, Studiengruppe, Firmenname}
	 * @param title		Titel im Template.
	 * @param headline	Überschrift im Template.
	 */
	public void setData(String[][] printData, String title,String headline) {

		// Daten mit Template verknüpfen, Titel und Überschrift
		input.put("title",title);
		input.put("headline", headline);

		// Liste füllen mit gewünschten Daten bzw weitergabe an das
		// entsprechende Objekt welche mit set/get arbeitet
		for (String[] rowIndex : printData) {
			tableData.add(new ValuePrintObject(rowIndex[0], rowIndex[1], rowIndex[2], rowIndex[3], rowIndex[4]));
		}
		input.put("tableData", tableData);
	}

	
	/**
	 * Zu Debugzwecken kann der Druck des Templates auf den Desktop umgeleitet werden.
	 * 
	 * @param fName			Zieldatei, in die das Template "gedruckt" wird.
	 * @throws Exception
	 */
	public void printFile(String fName) throws Exception {
		
		//Daten in Datei schreiben zu verbose Zwecken ...
		Writer fileWriter = new FileWriter(new File(fName));
		try {
			template.process(input, fileWriter);
		} finally {
			fileWriter.close();
		}
		showHtml(fName);
	}
	
	/**
	 * Zu Debugzwecken, wird die gedruckte Datei auf dem Desktop angezeigt.
	 * @param file	Dateiname, des gedruckten Template.
	 */
	public static void showHtml(String file) {
		Desktop desktop = Desktop.getDesktop();

		// Datei mit Standardbrowser anzeigen
		URI uri;
		try {
			uri = new URI(file);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out.println("Error...desktop not found?");
		}
	}
	
	/**
	 * Erzeugt das Dokument auf Grundlage des Template und schickt es an den Drucker.
	 * @throws Exception
	 */
	public void print() throws Exception {

		// Ausgabe erzeugen
		// outputstream erzeugen für Writer
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// Writer erzeugen um Outputstream zu füllen
		Writer panelWriter = new OutputStreamWriter(out);

		// Template auf Daten anwenden und mit Writer in den Outputstream legen
		template.process(input, panelWriter);

		// HTML Document und Reader erzeugen um den HTML Stream zu verarbeiten
		// und auszudrucken
		HTMLEditorKit htmlKit = new HTMLEditorKit();

		// Leeres HTML Document anlegen
		HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();

		// HTML Reader ließt HTML Daten vom Template durch Outputstream
		htmlKit.read(new StringReader(out.toString()), htmlDoc, 0);

		// Printer Objekt anlegen der Daten in JEditorPane läd
		DocumentRenderer printer = new DocumentRenderer();

		// daten zum drucker geben
		printer.print(htmlDoc);
		// http://ostermiller.org/convert_java_outputstream_inputstream.html

	}

}