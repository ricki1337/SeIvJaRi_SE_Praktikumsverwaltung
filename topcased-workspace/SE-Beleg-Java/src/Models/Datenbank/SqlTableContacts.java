package Models.Datenbank;


/**
 * Definiert die Spaltennamen der Datenbanktabelle "Contacts"
 *
 */
public class SqlTableContacts extends Object{
	public static String tableName = new String("Contact");
	public static String tableNameWithAlias = new String(tableName +" as "+tableName);
	
	public static final String PrimaryKey = new String("ID");
	public static final String Id = new String("ID");
	public static final String Name = new String("Name");
	public static final String Telefonnummer = new String("Phone");
	public static final String Bemerkung = new String("Note");
	public static final String ZuordnungFirma = new String("IDCompany");
	
	
	
	public static final String TableNameDotPrimaryKey = new String(tableName+"."+PrimaryKey);
	public static final String TableNameDotId = new String(tableName+"."+Id);
	public static final String TableNameDotName = new String(tableName+"."+Name);
	public static final String TableNameDotTelefonnummer = new String(tableName+"."+Telefonnummer);
	public static final String TableNameDotBemerkung = new String(tableName+"."+Bemerkung);
	public static final String TableNameDotZuordnungFirma = new String(tableName+"."+ZuordnungFirma);
	
	 @Override
	 public String toString(){
		 return tableName;
	 }
	
}
