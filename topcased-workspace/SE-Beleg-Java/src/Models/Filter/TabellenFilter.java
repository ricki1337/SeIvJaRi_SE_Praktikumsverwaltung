package Models.Filter;

import java.util.ArrayList;

public class TabellenFilter implements ObjectFilter{

	private ArrayList<String> spaltenName;
	private ArrayList<FilterTyp> spaltenWert;
	
	public TabellenFilter(){
		spaltenName = new ArrayList<String>();
		spaltenWert = new ArrayList<FilterTyp>();
	}
	
	
	@Override
	public void setOrFilter(String spaltenName, FilterTyp spaltenWert) {

		if(this.spaltenName.size()!=0)
			this.spaltenName.add(" or "+spaltenName);
		else this.spaltenName.add(spaltenName);
		
		this.spaltenWert.add(spaltenWert); 
	}
	
	@Override
	public void setAndFilter(String spaltenName, FilterTyp spaltenWert) {
		if(this.spaltenName.size()!=0)
			this.spaltenName.add(" and "+spaltenName);
		else this.spaltenName.add(spaltenName);
		
		this.spaltenWert.add(spaltenWert); 
	}

	@Override
	public String getFilter() {
		if(spaltenName.isEmpty()) return new String();

		String output = new String();
		for(int i=0;i<this.spaltenName.size();i++){
			output += spaltenName.get(i) + spaltenWert.get(i).getWert();
		}
		return output;
	}

	@Override
	public void deleteFilter(String spaltenName) {
		int index;
		
		while((index = this.spaltenName.indexOf(spaltenName)) != -1){
			this.spaltenName.remove(index);
			this.spaltenWert.remove(index);
		
		}
		
		while((index = this.spaltenName.indexOf(" or " + spaltenName)) != -1){
				this.spaltenName.remove(index);
				this.spaltenWert.remove(index);
			
		}
		
		while((index = this.spaltenName.indexOf(" and " + spaltenName)) != -1){
				this.spaltenName.remove(index);
				this.spaltenWert.remove(index);
			
		}
	}

}
