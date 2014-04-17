import java.sql.*;

public class myjdbctest {
	Connection connection;
	
	
	public myjdbctest(){}
	
	public boolean connectToMySql(String host, String user, String pw, String db){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionCommand = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pw;
			connection = DriverManager.getConnection(connectionCommand);
			return true;
		}
		catch(Exception e){
			System.out.print(e.getMessage());
			return false;
			
		}
		
	}
	
	public ResultSet getQuery(String query) throws SQLException{
		Statement stmnt = connection.createStatement();
		return stmnt.executeQuery(query);
	
	}
	
	/*public static void main(String args[]) throws SQLException{
		ResultSet table;
		ResultSetMetaData metaData;
		String ergebnis;
		myjdbctest con = new myjdbctest();
		con.connectToMySql(args[0], args[1], args[2], args[3]);
		table = con.getQuery("select * from country");
		metaData = table.getMetaData();
		System.out.println(metaData.getColumnName(2));
		while(table.next()){
			ergebnis = table.getString("de");
			System.out.println(ergebnis);
		}
		return;
	}
	*/
	
}
