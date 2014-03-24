import java.sql.*;

public class DatenbankTest {
	
	public static void main(String args[]) throws SQLException{
		
		Database db = Database.getInstance();
		
		db.connect("MySql", "localhost", 3306, "root", "", "ronin");
		ResultSet test = db.getQuery("select * from core_user");
		
		while(test.next()){
			
				System.out.println(test.getString("name"));
			
		}
		
	}
	
}
