package Models.Filter;

import java.util.ArrayList;

public class TabellenFilter implements Filter{

	private ArrayList<String> spaltenName;
	private ArrayList<FilterTyp> spaltenWert;
	
	public TabellenFilter(){
		spaltenName = new ArrayList<String>();
		spaltenWert = new ArrayList<FilterTyp>();
	}
	
	
	@Override
	public void setOrFilter(String spaltenName, FilterTyp spaltenWert) {
		// TODO Auto-generated method stub
		if(this.spaltenName.size()!=0)
			this.spaltenName.add(" or "+spaltenName);
		else this.spaltenName.add(spaltenName);
		
		this.spaltenWert.add(spaltenWert); 
	}
	
	@Override
	public void setAndFilter(String spaltenName, FilterTyp spaltenWert) {
		// TODO Auto-generated method stub
		if(this.spaltenName.size()!=0)
			this.spaltenName.add(" and "+spaltenName);
		else this.spaltenName.add(spaltenName);
		
		this.spaltenWert.add(spaltenWert); 
	}

	@Override
	public String getFilter() {
		// TODO Auto-generated method stub
		String output = new String();
		for(int i=0;i<=this.spaltenName.size();i++){
			output += spaltenName.get(i) + spaltenWert.get(i);
		}
		return output;
	}

	@Override
	public void deleteFilter(String spaltenName) {
		int index = this.spaltenName.indexOf(" or " + spaltenName);
		this.spaltenName.remove(index);
		this.spaltenWert.remove(index);
		
		index = this.spaltenName.indexOf(" and " + spaltenName);
		this.spaltenName.remove(index);
		this.spaltenWert.remove(index);
	}

}
