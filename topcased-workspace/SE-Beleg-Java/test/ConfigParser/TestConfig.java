package ConfigParser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestConfig {

	@Test
	public void testSetAndGetProperty(){
		String key = new String("key");
		String value = new String("value"+'\n'+"muhahaha");
		Config.setProperties(key, value);
		assertEquals(value,Config.getProperties(key));
		
	}
	
}
