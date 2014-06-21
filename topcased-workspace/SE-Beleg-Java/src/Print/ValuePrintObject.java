package Print;

/**
 * Hält alle Informationen eines Datensatzes für den Druck.
 */
public class ValuePrintObject {

	private String mat, name, mail, gruppe, firma;
	
	/**
	 * Initialisiert alle Klassenvariablen.
	 * @param mat		Matrikelnummer
	 * @param name		Vor- und Nachname
	 * @param mail		Bibliotheksnummer
	 * @param gruppe	Studiengruppe
	 * @param firma		Firmenname
	 */
	public ValuePrintObject(String mat, String name, String mail,String gruppe, String firma) {
		this.mat = mat;
		this.name = name;
		this.mail = mail;
		this.gruppe = gruppe;
		this.firma = firma;
	}

	public String getMat() {
		return mat;
	}

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public String getGruppe() {
		return gruppe;
	}

	public String getFirma() {
		return firma;
	}

}
