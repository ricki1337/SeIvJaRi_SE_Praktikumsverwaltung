package Models.Filter;

public class StringFilter implements FilterTyp{
	private String wert;
	
	public StringFilter(String wert){
		this.wert = wert;
	}
	
	@Override
	public Object getWert() {
		// TODO Auto-generated method stub
		return wert;
	}

	@Override
	public void setWert(Object wert) {
		// TODO Auto-generated method stub
		this.wert = (String)wert;
	}

}
