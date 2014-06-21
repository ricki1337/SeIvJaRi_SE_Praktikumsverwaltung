package Views.Interfaces;

import javax.swing.JComponent;

/**
 * Stellt die Definition aller Elemente dar, welche in einer View platziert werden können.
 */
public interface BasicBox {
	/**
	 * Setzen des Names der enthaltenen Elemente mit den Sql-Spaltenreferenzen aus einer SqlTable- Definition.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void setComponentNames();
	
	/**
	 * Setzen der Initialwerte der Elemente.
	 */
	public void setComponentValues();
	
	/**
	 * Hinzufügen von {@link EventHandler} zu den vorhandenen Elementen.
	 */
	public void setComponentEventHandler();
	
	/**
	 * Wird aufgerufen, wenn die View aktualisiert wird.
	 */
	public void refreshContent();
	
	/**
	 * Gibt die {@link JComponent}-Repräsentation der Box zurück.
	 * 
	 * @return	Die Containerklasse der Box.	
	 */
	public JComponent getJComponent();
	
	/**
	 * Wird zum anordnen der Elemente in der Box genutzt.
	 */
	public void initComponents();
}
