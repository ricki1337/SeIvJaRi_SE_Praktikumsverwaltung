package Models.Table;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import Models.Table.TableData;

public class TestTableData {

	@Test(expected=IllegalArgumentException.class)
	public void testSetSqlResultSetWithNullResultSet(){
		TableData testTableData = new TableData();
		testTableData.setSqlResultSet(null);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetRowCountWithNegativeRowCount(){
		TableData testTableData = new TableData();
		testTableData.setRowCount(-1);
	}
	
	@Test
	public void testAssertNullSetDataFromResultSetWithEmptyResultSet(){
		ResultSet result = Mockito.mock(ResultSet.class);
		TableData testTableData = new TableData();
		testTableData.setSqlResultSet(result);
		testTableData.setDataFromResultSet();
		assertNull(testTableData.data);
	}
	
	@Test
	public void testAssertNotNullSetDataFromResultSetWithFilledResultSet() throws SQLException{
		ResultSet result = getResultSetMock();
		TableData testTableData = new TableData(result,20);
		assertNotNull(testTableData.data);
	}
	
	@Test
	public void testAssertValueSetDataFromResultSetWithFilledResultSet() throws SQLException{
		ResultSet result = getResultSetMock();
		TableData testTableData = new TableData(result,20);
		
		Object[] expect= {new String("test"),(boolean)false,(boolean)true,(int)42};
		
		assertArrayEquals(expect,testTableData.data[0]);
	}
	
	@Test
	public void testSetTableRowsUpToRowCount() throws SQLException{
		ResultSet result = getResultSetMock();
		TableData testTableData = new TableData(result,1);
		testTableData.setRowCount(2);
		testTableData.setTableRowsUpToRowCount();
		assertEquals(2,testTableData.data.length);
	}
	
	
	public ResultSet getResultSetMock() throws SQLException{
		ResultSet mockResult = Mockito.mock(ResultSet.class);
		ResultSetMetaData mockMeta = Mockito.mock(ResultSetMetaData.class);

		String dataCol1 = new String("test");
		boolean dataCol2 = false;
		boolean dataCol3 = true;
		int dataCol4 = 42;
		
		Mockito.when(mockMeta.getColumnName(1)).thenReturn(new String("headName1"));
		Mockito.when(mockMeta.getColumnName(2)).thenReturn(new String("headName2"));
		Mockito.when(mockMeta.getColumnName(3)).thenReturn(new String("headName3"));
		Mockito.when(mockMeta.getColumnName(4)).thenReturn(new String("headName4"));
		Mockito.when(mockMeta.getColumnLabel(1)).thenReturn(new String("headLabel1"));
		Mockito.when(mockMeta.getColumnLabel(2)).thenReturn(new String("headLabel2"));
		Mockito.when(mockMeta.getColumnLabel(3)).thenReturn(new String("headLabel3"));
		Mockito.when(mockMeta.getColumnLabel(4)).thenReturn(new String("headLabel4"));
		Mockito.when(mockMeta.getColumnCount()).thenReturn(4);
		
		Mockito.when(mockResult.getMetaData()).thenReturn(mockMeta);
		
		Mockito.when(mockResult.getObject(1)).thenReturn(dataCol1);
		Mockito.when(mockResult.getObject("headName1")).thenReturn(dataCol1);
		Mockito.when(mockResult.getObject("headLabel1")).thenReturn(dataCol1);
		
		
		Mockito.when(mockResult.getBoolean(2)).thenReturn(dataCol2);
		Mockito.when(mockResult.getBoolean("headName2")).thenReturn(dataCol2);
		Mockito.when(mockResult.getBoolean("headLabel2")).thenReturn(dataCol2);
		
		
		Mockito.when(mockResult.getBoolean(3)).thenReturn(dataCol3);
		Mockito.when(mockResult.getBoolean("headName3")).thenReturn(dataCol3);
		Mockito.when(mockResult.getBoolean("headLabel3")).thenReturn(dataCol3);
		
		Mockito.when(mockResult.getObject(4)).thenReturn(dataCol4);
		Mockito.when(mockResult.getObject("headName4")).thenReturn(dataCol4);
		Mockito.when(mockResult.getObject("headLabel4")).thenReturn(dataCol4);
		
		Mockito.when(mockResult.last()).thenReturn(true);
		Mockito.when(mockResult.first()).thenReturn(true);
		Mockito.when(mockResult.getRow()).thenReturn(1);
		
		
		return mockResult;
	}
	
}
