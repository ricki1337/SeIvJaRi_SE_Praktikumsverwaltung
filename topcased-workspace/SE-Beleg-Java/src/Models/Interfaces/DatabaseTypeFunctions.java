package Models.Interfaces;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Definiert die Adaptermethoden zur Arbeit mit verschiedenen Datenbanksystemen.<br>
 * z.B. MySql, Sybase, Oracle, etc.
 *
 */
public interface DatabaseTypeFunctions {
	/**
	 * Gibt das {@link ResultSet} der �bermittelten Sqlquery zur�ck.
	 * @param	query	Sqlquery, welche ausgef�hrt werden soll.
	 * @return			{@link ResultSet}, welches das Ergebnis der Sqlquery enth�lt.
	 */
	public ResultSet getQuery(String query) throws SQLException;

	/**
	 * F�hrt den �bergebenen Sqlquery auf der Datenbank aus und gibt die Anzahl der ge�nderten Datens�tze zur�ck.
	 * @param query		Sqlquery, welcher Daten in der Datenbank manipuliert.
	 * @return			Anzahl der ge�nderten Datens�tze.
	 * @throws SQLException
	 */
	public int setQuery(String query) throws SQLException;

	/**
	 * Verbindet sich mit der Datenbank und gibt das {@link Connection} Objekt zur�ck.
	 * @param host	IP oder DNS Name des Datenbankservers.
	 * @param port	Portnummer, auf welcher der Datenbankserver auf eine Verbindung wartet.
	 * @param user	Benutzername zur Anmeldung an der Datenbank.
	 * @param pw	Passwort des Benutzers der Datenbank.
	 * @param db	Datenbankname, welche ge�ffnet werden soll.
	 * @return		{@link Connection}-Objekt der Verbindung.
	 * @throws Exception
	 */
	public Connection connect(String host, int port, String user, String pw, String db) throws Exception;

	/**
	 * Trennt die Verbindung zu Datenbank.
	 */
	public void disconnect();
}
