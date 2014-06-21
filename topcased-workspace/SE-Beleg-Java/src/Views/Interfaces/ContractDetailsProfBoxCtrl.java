package Views.Interfaces;

/**
 * Muss vom Controller implementiert werden, wenn in der View eine {@link ContractDetailsProfBox} angezeigt wird.
 */
public interface ContractDetailsProfBoxCtrl extends EditBoxCtrl{
	
	/**
	 * Aufruf, wenn der Button "Betreuer ändern" geklickt wurde.
	 */
	public void buttonChangeProfOnContractDetailsClicked();
	
	/**
	 * Aufruf, wenn der Button "Betreuer editieren" geklickt wurde.
	 */
	public void buttonEditProfOnContractDetailsClicked();

}
