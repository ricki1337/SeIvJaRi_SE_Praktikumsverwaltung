package Views.Interfaces;

/**
 * Muss vom Controller implementiert werden, wenn in der View eine {@link ContractDetailsCompanyBox} angezeigt wird.
 */
public interface ContractDetailsCompanyBoxCtrl extends EditBoxCtrl{
	
	/**
	 * Aufruf, wenn der Button "Firma ändern" geklickt wurde.
	 */
	public void buttonChangeCompanyOnContractDetailsClicked();

	/**
	 * Aufruf, wenn der Button "Firma editieren" geklickt wurde.
	 */
	public void buttonEditCompanyOnContractDetailsClicked();

}
