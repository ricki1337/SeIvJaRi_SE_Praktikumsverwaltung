import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class MainTest {
	
  public static void main(String[] args) throws Exception {

    // 1. Configure FreeMarker
    //
    // You should do this ONLY ONCE, when your application starts,
    // then reuse the same Configuration object elsewhere.
    
    Configuration cfg = new Configuration();
    
    // Where do we load the templates from:
    cfg.setClassForTemplateLoading(MainTest.class, "templates");
    
    // Some other recommended settings:
    cfg.setIncompatibleImprovements(new Version(2, 3, 20));
    cfg.setDefaultEncoding("UTF-8");
    cfg.setLocale(Locale.US);
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

    // 2. Proccess template(s)
    //
    // You will do this for several times in typical applications.
    
    // 2.1. Prepare the template input:
    
    Map<String, Object> input = new HashMap<String, Object>();
    
    input.put("title", "Template Export Sample fuer Praktikumsexport Funktion?");

    input.put("exampleObject", new ValueExampleObject("Studenten", "Praktium","","",""));
    
    
    //Liste füllen mit gewünschten Daten bzw weitergabe an das entsprechende Objekt welche mit set/get arbeitet
    List<ValueExampleObject> students = new ArrayList<ValueExampleObject>();
    students.add(new ValueExampleObject("33796","Hans Meier", "Hans@spambog.com", "12 / 43 / 61", "Microsoft"));
    students.add(new ValueExampleObject("99865","Rudolf Nase", "rudolf@web.de", "11 / 43 / 01", "SAP"));
    students.add(new ValueExampleObject("47895","Sepp sowieso", "Sepp@gmail.com", "10 / 41 / 61", "DVB AG"));
    students.add(new ValueExampleObject("22587","Rick nachname", "Rick@gmx.de", "13 / 22 / 61", "Hollywood"));
    input.put("students", students);
    
    // 2.2. Get the template

    Template template = cfg.getTemplate("helloworld.ftl");
      
    // 2.3. Generate the output

    // Write output to the console
    Writer consoleWriter = new OutputStreamWriter(System.out);
    template.process(input, consoleWriter);
    
 

    // For the sake of example, also write output into a file:
    Writer fileWriter = new FileWriter(new File("output.html"));
    try {
      template.process(input, fileWriter);
    } finally {
      fileWriter.close();
    }
    
    //create a Array with the data for export...
    
    //showhtml("output.html");
    printdata("output.html");
   
    
    

  }
  
  public static void showhtml (String file) {
	  Desktop desktop = Desktop.getDesktop();
		
	//Datei mit Standardbrowser anzeigen
	URI uri;
	try {
	  uri = new URI(file);
	  desktop.browse(uri);
	}
	catch(Exception oError) {
	  System.out.println("Error...desktop not found?");
	}
  }
  
  //Druckfunktion momentan workaround über html
  public static void printdata (String file) {
	 
	  //Druckeigenschaften anlegen welche Art von Daten, codierung und woher
	   DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
	   
	   //Druckeigenschaften festlegen, zb A4, kopienanzahl etc.
	   PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	   aset.add(MediaSizeName.ISO_A4);
	   
	   //Auslesen aller auf dem PC verfügbaren Drucker(-Service/treiber) und prüfen ob diese die Druckeigenschaften beherschen
	   PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, null);
	   PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
	   
	   //Druckdialog öffnen um Drucker auszuwählen
	   PrintService service = ServiceUI.printDialog(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration(),
			   200, 200,printService, defaultService, flavor, aset);
       
	   
	   if (service!=null) {
	       DocPrintJob pj = service.createPrintJob();
	       try {
	           FileInputStream fis = new FileInputStream("output.html");
	           Doc doc = new SimpleDoc(fis, flavor, null);
	           
	           pj.print(doc, aset);
	        } catch (FileNotFoundException fe) {
	        } catch (PrintException e) {
	        }
	   }
  }
} 