package Views.Interfaces;

/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementBottomNaviProgressBox}-Element aufnimmt.
 */
public interface NaviProgressBoxCtrl {
	/**
	 * Übermittelt den Wert für die {@link JProgressBar}, welche 0% entspricht.
	 * @return	Äquivalent für 0%
	 */
	public int getValueEqualToNullPercent();
	
	/**
	 * Übermittelt den Wert für die {@link JProgressBar}, welche 100% entspricht.
	 * @return	Äquivalent für 100%
	 */
	public int getValueEqualToHundretPercent();
	
	/**
	 * Übermittelt den aktuellen Wert für die {@link JProgressBar}.
	 * @return	aktueller Wert.
	 */
	public int getCurrentValue();
}
