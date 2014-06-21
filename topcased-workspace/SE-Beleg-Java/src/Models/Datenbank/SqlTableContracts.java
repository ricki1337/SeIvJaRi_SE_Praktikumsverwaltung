package Models.Datenbank;

/**
 * Definiert die Spaltennamen der Datenbanktabelle "Contracts"
 *
 */
public class SqlTableContracts extends Object{
	public static String tableName = new String("Contract");
	public static String tableNameWithAlias = new String(tableName +" as "+tableName);
	
	public static final String PrimaryKey = new String("ID");
	public static final String Id = new String("ID");
	public static final String FK_Firma = new String("CompID");
	public static final String FK_Student = new String("MatrNr");
	public static final String Beginn = new String("BegPr");
	public static final String Ende = new String("EndPr");
	public static final String FK_Betreuer = new String("Prof");
	public static final String Erfolg = new String("Erfolg");
	public static final String Typ = new String("Type");
	public static final String Bericht = new String("Bericht");
	public static final String Zeugnis = new String("Zeugnis");
	public static final String Empfehlung = new String("Empfehlung");
	public static final String TextBetreuer = new String("TextProf");
	
	public static final String TableNameDotPrimaryKey = new String(tableName+"."+PrimaryKey);
	public static final String TableNameDotId = new String(tableName+"."+Id);
	public static final String TableNameDotFK_Firma = new String(tableName+"."+FK_Firma);
	public static final String TableNameDotFK_Student = new String(tableName+"."+FK_Student);
	public static final String TableNameDotBeginn = new String(tableName+"."+Beginn);
	public static final String TableNameDotEnde = new String(tableName+"."+Ende);
	public static final String TableNameDotFK_Betreuer = new String(tableName+"."+FK_Betreuer);
	public static final String TableNameDotErfolg = new String(tableName+"."+Erfolg);
	public static final String TableNameDotTyp = new String(tableName+"."+Typ);
	public static final String TableNameDotBericht = new String(tableName+"."+Bericht);
	public static final String TableNameDotZeugnis = new String(tableName+"."+Zeugnis);
	public static final String TableNameDotEmpfehlung = new String(tableName+"."+Empfehlung);
	public static final String TableNameDotTextBetreuer = new String(tableName+"."+TextBetreuer);

	 @Override
	 public String toString(){
		 return tableName;
	 }
	
}
