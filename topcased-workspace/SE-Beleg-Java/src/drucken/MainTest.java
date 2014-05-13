package drucken;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class MainTest {

	static Configuration cfg;
	private Template template;
	private Map<String, Object> input= new HashMap<String, Object>();
	private List<ValuePrintObject> tableData= new ArrayList<ValuePrintObject>();
	private String[][] mstrData;

	public static void main(String[] args) throws Exception {

		String[][] testary;
		testary = new String[][] {
				{ "65878", "Hans Detlef", "muh@web.de", "11/43/01", "sap" },
				{ "98564", "Rolf Golf", "blub@freenet.de", "12/13/14","Microsoft" },
				{ "45879", "Franz Lenz", "tag@morgen.com", "00/11/22", "Himmel" },
				{ "25874", "Lisa Müller", "hallo@wasgeht.de", "10/024/34","Golfplatz" },
				{ "97854", "Hans Wurst", "langweilig@langweildich.net","09/034/61", "Imbiss" },

		};

		MainTest testding = new MainTest();
		testding.setData(testary, "Titel","Überschrift");
		testding.printFile("output.html");

	}

	public MainTest(String[][] strData) throws Exception {
		this.mstrData = strData;
		init();		
		
		setData();
		print();
		
		

	}
	
	public MainTest() throws Exception {
		init();
	}

	private void init() throws Exception {

		// Configure FreeMarker
		//
		// You should do this ONLY ONCE, when your application starts,
		// then reuse the same Configuration object elsewhere.
		cfg = new Configuration();

		// Where do we load the templates from:
		cfg.setClassForTemplateLoading(MainTest.class, "templates");

		// Some other recommended settings:
		cfg.setIncompatibleImprovements(new Version(2, 3, 20));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.GERMANY);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		template = cfg.getTemplate("praktika.ftl");
		
		
	}

	public void setTemplate(String tmp) throws Exception {

		// Choose template(s)
		template = cfg.getTemplate(tmp);
	}

	
	private void setData() {
		
		
		input.put("title", "Template Export Sample fuer Praktikumsexport Funktion?");

		input.put("headline", "Folgende(r) Student(en) absolviert(en) das Praxisprojekt erfolgreich:");

		// Liste füllen mit gewünschten Daten bzw weitergabe an das
		// entsprechende Objekt welche mit set/get arbeitet
		for (String[] i : mstrData) {

			tableData.add(new ValuePrintObject(i[0], i[1], i[2], i[3],
					i[4]));
		}
//		 tableData.add(new ValuePrintObject("33796", "Hans Meier",
//					 "Hans@spambog.com", "12 / 43 / 61", "Microsoft"));
					
		input.put("tableData", tableData);
		
	}
	
	public void setData(String[][] strData, String title,String headline) {

		// Daten mit Template verknüpfen, Titel und Überschrift
		input.put("title",title);
		input.put("headline", headline);

		// Liste füllen mit gewünschten Daten bzw weitergabe an das
		// entsprechende Objekt welche mit set/get arbeitet
		for (String[] i : strData) {

			tableData.add(new ValuePrintObject(i[0], i[1], i[2], i[3],
					i[4]));

			// tableData.add(new ValuePrintObject("33796", "Hans Meier",
			// "Hans@spambog.com", "12 / 43 / 61", "Microsoft"));
			//
		}
		input.put("tableData", tableData);
	}

	public static void showhtml(String file) {
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

	public void printFile(String fName) throws Exception {
		Writer fileWriter = new FileWriter(new File(fName));
		System.out.println(input);
		try {
			template.process(input, fileWriter);
		} finally {
			fileWriter.close();
		}
		showhtml(fName);
	}

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
