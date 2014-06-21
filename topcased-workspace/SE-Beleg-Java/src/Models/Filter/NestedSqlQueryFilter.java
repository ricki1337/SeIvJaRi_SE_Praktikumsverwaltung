package Models.Filter;

import Models.Interfaces.DatatypeFilter;

/**
 * Implementiert einen Filter für verschachtelte Sqlquerys,<br>
 * welche mittels "IN" oder "NOT IN" gesetzt werden sollen. 
 */
public class NestedSqlQueryFilter implements DatatypeFilter{

	private String sqlQuery;
	private boolean valuesIn;
	
	/**
	 * Initialisiert einen Filter, welcher einen Sqlquery übernimmt und diesen mit, abhängig von<br>
	 * valuesIn, mit "IN" oder "NOT IN" verknüpft.
	 * @param nestedSqlQuery
	 * @param valuesIn	True => Sqlquery wird mit "IN" verknüpft, false => Sqlquery wird mit "NOT IN" verknüpft.
	 */
	public NestedSqlQueryFilter(String nestedSqlQuery, boolean valuesIn){
		setWert(nestedSqlQuery);
		this.valuesIn = valuesIn;
	}
	
	@Override
	public Object getWert() {
		if(sqlQuery == null) throw new IllegalArgumentException();
		if(sqlQuery.length() == 0) throw new IllegalArgumentException();
		String inOrNotIn = (valuesIn)?" in ":" not in "; 
		return inOrNotIn + "(" + sqlQuery + ")";
	}

	@Override
	public void setWert(Object wert) {
		String query = null;
		if(wert instanceof String)
			query = (String)wert;
		
		if(query == null) throw new IllegalArgumentException();
		if(query.length() == 0) throw new IllegalArgumentException();
		this.sqlQuery = query;
	}
}
