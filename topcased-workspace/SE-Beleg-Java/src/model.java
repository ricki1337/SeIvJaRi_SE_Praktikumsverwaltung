import Datenbank.*;
import java.sql.*;

public class model implements observer{
	
	private String SrcQuery;
	private Filter filter;
	private int PageSize;
	private View view;
	private ResultSet result;
	
	/**
	 * implementation des observer interface
	 * -> wird aufgerufen wenn sich beim database objekt daten �ndern
	 */
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * TODO
	 * views m�ssen ein interface implementieren...
	 *
	 */
	private void informView(){}
	
	public ResultSet getResult(){
		return this.result;
	}
	
	public void setFilter(){}
	
	public void deleteFilter(){}
	
	private void refreshResult(){}
	

}
