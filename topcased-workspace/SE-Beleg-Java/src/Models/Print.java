package Models;

public class Print extends Model{
	
	String Prim�rschl�ssel = "";

	public Print(String srcQuery, String PrimaryKey) {
		super(srcQuery);
		// TODO Auto-generated constructor stub
		Prim�rschl�ssel = PrimaryKey;
	}
	
	public Print(){
		super();
	}
	
	
	public String getTableNameForUpdateOrInsert(){
		return new String("student");
		
	}
	
	protected String getPrimaryKeyColumnName(){
		return new String(Prim�rschl�ssel);
	}

}