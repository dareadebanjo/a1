package a1;

import junit.framework.TestCase;

public class CardTest extends TestCase{
	/**
	* This tests the deck constructor AND for face cards
	*/
	public void testCard(){
		Card tester = new Card(Suit.values()[1], 8);
		
		assertEquals("D8",tester.toString());
		
		
		//This confirms face cards all have values of 10
		Card tester2 = new Card(Suit.values()[1], 13);
		
		assertEquals("D10",tester2.toString());
	}
	
	/**
	* This tests the deck's card counter getter method
	*/
	public void testGetter() {
		int number = 0;
		Card tester = new Card(Suit.values()[1], 5);
		
		assertEquals(5,tester.getNumber());
	}
	
}
