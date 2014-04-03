package Models.Filter;

public class IntFilter implements FilterTyp{

	private int wert;
	
	public IntFilter(int wert){
		this.wert = wert;
	}
	
	public IntFilter(){
		wert = 0;
	}
	
	@Override
	public Object getWert() {
		// TODO Auto-generated method stub
		return wert;
	}

	@Override
	public void setWert(Object wert) {
		// TODO Auto-generated method stub
		this.wert = (int)wert;
	}

}
