package Models;

public class Print extends Model{
	
	String Primärschlüssel = "";

	public Print(String srcQuery, String PrimaryKey) {
		super(srcQuery);
		// TODO Auto-generated constructor stub
		Primärschlüssel = PrimaryKey;
	}
	
	public Print(){
		super();
	}
	
	
	public String getTableNameForUpdateOrInsert(){
		return new String("student");
		
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String(Primärschlüssel);
	}

}