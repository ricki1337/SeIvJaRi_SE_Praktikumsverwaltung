package Models.Filter;

public interface ObjectFilter {
	public void setOrFilter(String spaltenName, FilterTyp spaltenWert);
	public void setAndFilter(String spaltenName, FilterTyp spaltenWert);

	public String getFilter() ;

	public void deleteFilter(String spaltenName) ;
}
