package Models.Datenbank;


/**
 * Definiert die Spaltennamen der Datenbanktabelle "Student"
 *
 */
public class SqlTableStudent extends Object{
	public static String tableName = new String("Student");
	public static String tableNameWithAlias = new String(tableName +" as "+tableName);
	
	public static final String PrimaryKey = new String("MatrNr");
	public static final String MatrikelNummer = new String("MatrNr");
	public static final String Vorname = new String("FirstName");
	public static final String Nachname = new String("Name");
	public static final String EMail = new String("Email");
	public static final String Studiengruppe = new String("StGr");
	public static final String Bemerkung = new String("Note");
	
	public static final String TableNameDotPrimaryKey = new String(tableName+"."+PrimaryKey);
	public static final String TableNameDotMatrikelNummer = new String(tableName+"."+MatrikelNummer);
	public static final String TableNameDotVorname = new String(tableName+"."+Vorname);
	public static final String TableNameDotNachname = new String(tableName+"."+Nachname);
	public static final String TableNameDotEMail = new String(tableName+"."+EMail);
	public static final String TableNameDotStudiengruppe = new String(tableName+"."+Studiengruppe);
	public static final String TableNameDotBemerkung = new String(tableName+"."+Bemerkung);

	 @Override
	 public String toString(){
		 return tableName;
	 }
	
}
