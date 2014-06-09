package Models.Datenbank;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Models.Datenbank.Database;


public class TestDatabase{
	
	@Before
	public void testSetUpDatabaseFunctionInDatabase(){
		Database db = Database.getInstance();
		db.db = new MockMySql();
	}
	
	@After
	public void testSetDownDatabaseFunctionInDatabase(){
		Database db = Database.getInstance();
		db.disconnect();
	}
	
	@Test(expected=AssertionError.class)
	public void testCloneDatabase() {
		Database db = Database.getInstance();
		Database dbClone = db.clone();
		
	}
	
	@Test(expected=AssertionError.class)
	public void testCloneDatabaseInline() {
		Database db = Database.getInstance().clone();
	}
	
	@Test
	public void testTablesListAfterConnect() throws SQLException{
		Database db = Database.getInstance();
		db.setTables();
		assertEquals("Student", db.tables.get(0));
	}
	
	@Test
	public void testTablesListBeforeConnect() throws SQLException{
		Database db = Database.getInstance();
		assertEquals(0,db.tables.size());
	}
	
	@Test
	public void testChangedTables() throws SQLException{
		Database db = Database.getInstance();
		db.setTables();
		String[] changedTables = db.getChangedTables("update "+ SqlTableStudent.tableName +" set "+ SqlTableStudent.MatrikelNummer +" = '1' where "+ SqlTableStudent.MatrikelNummer +" = '1';");
		String[] targetArray = new String[1];
		targetArray[0] = SqlTableStudent.tableName;
		assertArrayEquals(targetArray,changedTables);
		
	}

}
