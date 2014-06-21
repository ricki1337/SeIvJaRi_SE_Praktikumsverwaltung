package Views.Interfaces;

/**
 * Erweitert das {@link ExtendedSearchBoxCtrl} um weitere Filter f�r Vertr�ge.
 *
 */
public interface ExtendedContractsSearchBoxCtrl extends ExtendedSearchBoxCtrl{

	/**
	 * Aufruf, wenn der Filter f�r Auslandspraktika gesetzt wurde.
	 */
	public void setInternationalInternshipFilterOn();

	/**
	 * Aufruf, wenn der Filter f�r Auslandspraktika gel�scht wurde.
	 */
	public void setInternationalInternshipFilterOff();

	/**
	 * Aufruf, wenn der Filter f�r erfolgreiche Praktika gesetzt wurde.
	 */
	public void setInternshipSuccessfulFilterOn();

	/**
	 * Aufruf, wenn der Filter f�r erfolgreiche Praktika gel�scht wurde.
	 */
	public void setInternshipSuccessfulFilterOff();

}
