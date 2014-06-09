package Models.Filter;

public class StringFilter implements FilterTyp{
	private String wert;
	private String praefix = "'";
	private String suffix = "'";
	
	public StringFilter(Object wert){
		this(wert.toString());
	}
	
	public StringFilter(String wert){
		this.wert = wert;
	}
	
	@Override
	public Object getWert() {
		// TODO Auto-generated method stub
		
		return " like "+ praefix + wert + suffix;
	}

	@Override
	public void setWert(Object wert) {
		// TODO Auto-generated method stub
		this.wert = (String)wert;
	}
	
	public void setWertPraefix(String praefix){
		this.praefix = "'" + praefix;
	}
	
	public void setWertSuffix(String suffix){
		this.suffix =  suffix + "'";
	}

}
