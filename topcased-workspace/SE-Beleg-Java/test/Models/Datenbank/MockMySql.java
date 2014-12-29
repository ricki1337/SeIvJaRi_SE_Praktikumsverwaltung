package Models.Datenbank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mockito.Mockito;

import Models.Interfaces.DatabaseTypeFunctions;

public class MockMySql implements DatabaseTypeFunctions{
	
	
	@Override
	public ResultSet getQuery(String query) {
		ResultSet result = Mockito.mock(ResultSet.class);
		
		try {
			if(query.equals("show tables;")){
				Mockito.when(result.next()).thenReturn(true).thenReturn(false);
				Mockito.when(result.getString(1)).thenReturn("Student");
			}
		
//		Mockito.when(resultSetMock.getString("trade_time")).thenReturn("12:24:56");
//		Mockito.when(resultSetMock.getString("expr_date")).thenReturn("03/19/2011");
//		Mockito.when(resultSetMock.getString("symbol")).thenReturn("VIX1");
//		Mockito.when(resultSetMock.getDouble("trade_price")).thenReturn(Double.valueOf("20.96"));
//		Mockito.when(resultSetMock.getString("contract_month")).thenReturn("1");
//		Mockito.when(resultSetMock.getString("trade_time_thou")).thenReturn("165");
//
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public int setQuery(String query) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Connection connect(String host, int port, String user, String pw, String db) {
		Connection connect = Mockito.mock(Connection.class);
		return connect;
	}

	@Override
	public void disconnect() {
		
	}

}
