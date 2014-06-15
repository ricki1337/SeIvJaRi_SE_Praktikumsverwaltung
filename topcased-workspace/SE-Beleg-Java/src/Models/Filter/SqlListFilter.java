package Models.Filter;

public class SqlListFilter implements FilterTyp{

	private String sqlQuery;
	private boolean valuesIn;
	
	public SqlListFilter(String query, boolean valuesIn){
		setWert(query);
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
