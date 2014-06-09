package Models.Datenbank;

public class SqlTableCompanies extends Object{
	
	public static String tableName = new String("Companies");
	public static String tableNameWithAlias = new String(tableName +" as "+tableName);
	
	
	public static final String PrimaryKey = new String("ID");
	public static final String Id = new String("ID");
	public static final String Firmenname = new String("Name");
	public static final String Strasse = new String("Street");
	public static final String PLZ = new String("ZIP");
	public static final String Ort = new String("Town");
	public static final String Land = new String("Country");
	public static final String Telefonnummer = new String("Phone_Cental");
	public static final String Bemerkung = new String("Notes");
	
	
	
	
	public static final String TableNameDotPrimaryKey = new String(tableName+"."+PrimaryKey);
	public static final String TableNameDotId = new String(tableName+"."+Id);
	public static final String TableNameDotFirmenname = new String(tableName+"."+Firmenname);
	public static final String TableNameDotStrasse = new String(tableName+"."+Strasse);
	public static final String TableNameDotPLZ = new String(tableName+"."+PLZ);
	public static final String TableNameDotOrt = new String(tableName+"."+Ort);
	public static final String TableNameDotLand = new String(tableName+"."+Land);
	public static final String TableNameDotTelefonnummer = new String(tableName+"."+Telefonnummer);
	public static final String TableNameDotBemerkung = new String(tableName+"."+Bemerkung);

	 @Override
	 public String toString(){
		 return tableName;
	 }
	
}
