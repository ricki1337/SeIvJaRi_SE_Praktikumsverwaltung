package Models;

public class Mailing extends Model{
	
	String Prim�rschl�ssel = "";

	public Mailing(String srcQuery, String PrimaryKey) {
		super(srcQuery);
		// TODO Auto-generated constructor stub
		Prim�rschl�ssel = PrimaryKey;
	}
	
	public Mailing(){
		super();
	}
	
	
	public String getTableNameForUpdateOrInsert(){
		return new String("student");
		
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String(Prim�rschl�ssel);
	}

}