package ConfigParser;

public class Debug {
	private static boolean debugMode = false;
	
	public static boolean isDebugMode(){
		return debugMode;
	}
	
	public static void setDebugMode(boolean mode){
		debugMode = mode;
	}
	
}
