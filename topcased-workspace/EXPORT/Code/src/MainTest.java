import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.net.URI;
import java.awt.Desktop;

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
    
    showhtml("output.html");
    

  }
  
  public static void showhtml (String file) {
	  Desktop desktop = Desktop.getDesktop();
		
	//Adresse mit Standardbrowser anzeigen
	URI uri;
	try {
	  uri = new URI(file);
	  desktop.browse(uri);
	}
	catch(Exception oError) {
	  System.out.println("Error...desktop not found?");
	}
  }
} 