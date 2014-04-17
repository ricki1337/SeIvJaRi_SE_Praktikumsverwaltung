import java.sql.*;
import Datenbank.*;

public class DatenbankTest {
	
	public static void main(String args[]) throws SQLException{
		
		Database db = Database.getInstance();
		
		db.connect("MySql", "ipc43.informatik.htw-dresden.de", 3306, "project", "seproject", "Prakt");
		ResultSet test = db.getQuery("select * from Profs");
		
		while(test.next()){
			
				System.out.println("(\"" + 
						
						test.getString("NameID") + "\",\"" + 
						test.getString("Name") + "\"),"
						);
			
		}
		
	}
	
}
