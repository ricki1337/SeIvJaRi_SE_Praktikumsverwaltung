package Views.Interfaces;

/**
 * Erweitert das {@link ExtendedSearchBoxCtrl} um weitere Filter für Verträge.
 *
 */
public interface ExtendedContractsSearchBoxCtrl extends ExtendedSearchBoxCtrl{

	/**
	 * Aufruf, wenn der Filter für Auslandspraktika gesetzt wurde.
	 */
	public void setInternationalInternshipFilterOn();

	/**
	 * Aufruf, wenn der Filter für Auslandspraktika gelöscht wurde.
	 */
	public void setInternationalInternshipFilterOff();

	/**
	 * Aufruf, wenn der Filter für erfolgreiche Praktika gesetzt wurde.
	 */
	public void setInternshipSuccessfulFilterOn();

	/**
	 * Aufruf, wenn der Filter für erfolgreiche Praktika gelöscht wurde.
	 */
	public void setInternshipSuccessfulFilterOff();

}
