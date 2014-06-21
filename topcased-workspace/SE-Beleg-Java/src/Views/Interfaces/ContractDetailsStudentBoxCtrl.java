package Views.Interfaces;

/**
 * Muss vom Controller implementiert werden, wenn in der View eine {@link ContractDetailsStudentBox} angezeigt wird.
 */
public interface ContractDetailsStudentBoxCtrl extends EditBoxCtrl {

	/**
	 * Aufruf, wenn der Button "Student ändern" geklickt wurde.
	 */
	public void buttonChangeStudentOnContractClicked();

	/**
	 * Aufruf, wenn der Button "Student editieren" geklickt wurde.
	 */
	public void buttonEditStudentOnContractClicked();

}
