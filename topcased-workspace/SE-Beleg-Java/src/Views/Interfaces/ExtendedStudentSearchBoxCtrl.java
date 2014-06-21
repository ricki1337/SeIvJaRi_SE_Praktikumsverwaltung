package Views.Interfaces;

/**
 * Erweitert das {@link ExtendedSearchBoxCtrl} um weitere Filter für Studenten.
 *
 */
public interface ExtendedStudentSearchBoxCtrl extends ExtendedSearchBoxCtrl{
	
	/**
	 * Aufruf, wenn der Filter "kein Vertrag" entfernt wurde.
	 */
	public void setHasNoContractFilterOff();

	/**
	 * Aufruf, wenn der Filter "kein Vertrag" gesetzt wurde.
	 */
	public void setHasNoContractFilterOn();

	/**
	 * Aufruf, wenn der Filter "hat Vertrag" gesetzt wurde.
	 */
	public void setHasContractFilterOn();

	/**
	 * Aufruf, wenn der Filter "hat Vertrag" entfernt wurde.
	 */
	public void setHasContractFilterOff();
}
