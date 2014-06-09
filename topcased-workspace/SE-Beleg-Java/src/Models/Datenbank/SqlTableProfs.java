package Models.Datenbank;

public class SqlTableProfs extends Object{
	public static String tableName = new String("Profs");
	public static String tableNameWithAlias = new String(tableName +" as "+tableName);
	
	public static final String PrimaryKey = new String("NameID");
	public static final String Id = new String("NameID");
	public static final String Name = new String("Name");
	
	public static final String TableNameDotPrimaryKey = new String(tableName+"."+PrimaryKey);
	public static final String TableNameDotId = new String(tableName+"."+Id);
	public static final String TableNameDotName = new String(tableName+"."+Name);
	
	 @Override
	 public String toString(){
		 return tableName;
	 }
	
}
