package ConfigParser;

/**
 * Trägt Informationen zum Debug-Modus.
*/
public class Debug {
	private static boolean debugMode = false;
	
	/**
	 * Gibt Informationen zum eingschaltetem Debug-Modus zurück.
	 * 
	 * @return 	True, wenn der Debug-Modus aktiv ist, sonst False.
	 */
	public static boolean isDebugMode(){
		return debugMode;
	}
	
	/**
	 * Aktiviert oder Deaktiviert den Debug-Modus.
	 * @param mode	True für Debug-Modus = An, False für Aus
	 */
	public static void setDebugMode(boolean mode){
		debugMode = mode;
	}
	
}
