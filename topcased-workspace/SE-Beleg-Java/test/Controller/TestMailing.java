package Controller;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Controller.Mock.MockMailing;
import Models.Datenbank.SqlTableCompanies;

public class TestMailing {

	
	@Test(expected=IllegalArgumentException.class)
	public void testMailingWithPrimaryKeyValuesNull(){
		Mailing controller = new Mailing(SqlTableCompanies.PrimaryKey,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMailingWithPrimaryKeyNull(){
		Mailing controller = new Mailing(null,new String("test"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMailingWithArgumentsNull(){
		Mailing controller = new Mailing(null,null);
	}
	
	@Test
	public void testGetLocalPartOfEmailAdressWithNull(){
		MockMailing controller = new MockMailing();
		assertNull(controller.getLocalPartOfEmailAdress(null));
	}
	
	@Test
	public void testGetLocalPartOfEmailAdressWithSimpleEmailAdress(){
		MockMailing controller = new MockMailing();
		String testEmailAdress = new String("test@test.de");
		String testLocalpartEmailAdress = new String("test");
		assertEquals(testLocalpartEmailAdress, controller.getLocalPartOfEmailAdress(testEmailAdress));
	}
	
	@Test
	public void testGetLocalPartOfEmailAdressWithNormalEmailAdress(){
		MockMailing controller = new MockMailing();
		String testEmailAdress = new String("test.test@test.de");
		String testLocalpartEmailAdress = new String("test.test");
		assertEquals(testLocalpartEmailAdress, controller.getLocalPartOfEmailAdress(testEmailAdress));
	}
}
