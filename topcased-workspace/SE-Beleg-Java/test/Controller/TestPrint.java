package Controller;

import org.junit.Test;

public class TestPrint {

	
	@Test(expected=IllegalArgumentException.class)
	public void testPrintWithPrimaryKeyNull(){
		Print test = new Print(null,new String("test"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPrintWithPrimaryKeyValuesNull(){
		Print test = new Print(new String("test"),null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPrintWithAllArgumentsNull(){
		Print test = new Print(null,null);
	}
}
