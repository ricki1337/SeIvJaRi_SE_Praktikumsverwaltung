package Views.Interfaces;
/**
 * Controller-Interface f�r die Navigationsbox der Ansprechpartner von Firmen. 
 *
 */
public interface CompanyContactDetailsBoxNavCtrl {

	/**
	 * Aufruf, wenn der Button ">>" geklickt wurde.
	 */
	public void buttonCompanyContactNextClicked();

	/**
	 * Aufruf, wenn der Bearbeiten-Button geklickt wurde.
	 */
	public void buttonCompanyContactEditClicked();
	
	/**
	 * Aufruf, wenn der Button "<<" geklickt wurde.
	 */
	public void buttonCompanyContactPreviusClicked();

	/**
	 * Aufruf, wenn der "Neuen Kontakt hinzuf�gen"-Button geklickt wurde.
	 */
	public void buttonCompanyContactAddNewClicked();

}
