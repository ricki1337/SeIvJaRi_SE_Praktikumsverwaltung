package ConfigParser;

/**
 * Tr�gt Informationen zum Debug-Modus.
*/
public class Debug {
	private static boolean debugMode = false;
	
	/**
	 * Gibt Informationen zum eingschaltetem Debug-Modus zur�ck.
	 * 
	 * @return 	True, wenn der Debug-Modus aktiv ist, sonst False.
	 */
	public static boolean isDebugMode(){
		return debugMode;
	}
	
	/**
	 * Aktiviert oder Deaktiviert den Debug-Modus.
	 * @param mode	True f�r Debug-Modus = An, False f�r Aus
	 */
	public static void setDebugMode(boolean mode){
		debugMode = mode;
	}
	
}
