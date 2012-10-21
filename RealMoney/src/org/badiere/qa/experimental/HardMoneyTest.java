package org.badiere.qa.experimental;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.badiere.experimental.HardMoney;
import org.junit.Before;
import org.junit.Test;

public class HardMoneyTest {

	HardMoney hm;
	
	
	@Before
	public void setUp(){
		
		try {
			hm = new HardMoney();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetGoldSpot() throws MalformedURLException {

		String spot = hm.getGoldSpot();
		System.out.println("Gold spot: " + spot);
		
		testSpot(spot);
		
	} 
	
	@Test
	public void testGetSilverSpot() throws MalformedURLException {

		String spot = hm.getSilverSpot();
		System.out.println("Silver spot: " + spot);
		
		testSpot(spot);

		
	}
	
	
	private void testSpot(String spot){
		
		boolean numericFloat = false;
		
		float spotFloat = 0;
		
		try{
			
			spotFloat = Float.parseFloat(spot);
			numericFloat = true;
			
		}catch (Exception e){
			
		    numericFloat = false;	
			
		}
		
		assertEquals(numericFloat, true);
		
		// test decimal place
		
		assertEquals(".", spot.substring(spot.length() - 3, spot.length() - 2));
		
	}

}
