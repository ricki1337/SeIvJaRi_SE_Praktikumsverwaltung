package Models.Filter;

public class IntFilter implements FilterTyp{

	private int filterValue;
	private int max;
	private boolean between = false;
	
	public IntFilter(int wert){
		this.filterValue = wert;
	}
	
	public IntFilter(){
		filterValue = 0;
	}
	
	public IntFilter(String value) {
		filterValue = Integer.parseInt(value);
	}
	
	public IntFilter(Object value) {
		if(value instanceof String)
			filterValue = Integer.parseInt((String)value);
		if(value instanceof Integer)
			filterValue = (int)value;
		
		throw new IllegalArgumentException();
	}

	@Override
	public Object getWert() {
		// TODO Auto-generated method stub
		if(between)
			return " between " + filterValue + " and " + max;
		
		return " = " + filterValue;
	}

	@Override
	public void setWert(Object wert) {
		// TODO Auto-generated method stub
		this.filterValue = (int)wert;
	}
	
	public void setRange(int min, int max){
		between = true;
		filterValue = min;
		this.max = max;
	}

}
