package Models;

public class Mailing extends Model{
	
	String Primärschlüssel = "";

	public Mailing(String srcQuery, String PrimaryKey) {
		super(srcQuery);
		// TODO Auto-generated constructor stub
		Primärschlüssel = PrimaryKey;
	}
	
	public Mailing(){
		super();
	}
	
	
	public String getTableNameForUpdateOrInsert(){
		return new String("student");
		
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String(Primärschlüssel);
	}

}