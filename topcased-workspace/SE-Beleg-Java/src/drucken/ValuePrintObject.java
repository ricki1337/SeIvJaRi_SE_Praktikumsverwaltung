package drucken;

public class ValuePrintObject {

	private String mat, name, mail, gruppe, firma;



	public ValuePrintObject(String[] strData) {
				
		
		this.mat = strData[1];
		this.name = name;
		this.mail = mail;
		this.gruppe = gruppe;
		this.firma = firma;

		
	}
	public ValuePrintObject(String mat, String name, String mail,
			String gruppe, String firma) {
		
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

