package Views.Interfaces;

/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementBottomNaviProgressBox}-Element aufnimmt.
 */
public interface NaviProgressBoxCtrl {
	/**
	 * �bermittelt den Wert f�r die {@link JProgressBar}, welche 0% entspricht.
	 * @return	�quivalent f�r 0%
	 */
	public int getValueEqualToNullPercent();
	
	/**
	 * �bermittelt den Wert f�r die {@link JProgressBar}, welche 100% entspricht.
	 * @return	�quivalent f�r 100%
	 */
	public int getValueEqualToHundretPercent();
	
	/**
	 * �bermittelt den aktuellen Wert f�r die {@link JProgressBar}.
	 * @return	aktueller Wert.
	 */
	public int getCurrentValue();
}
