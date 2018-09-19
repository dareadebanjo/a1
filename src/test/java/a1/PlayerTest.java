package a1;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{
	/**
	* This tests: the player constructor
	*/
	public void testPlayer(){
		//Make a new player named Matthew
		Player tester = new Player("Matthew");
		
		assertEquals("Matthew",tester.name);
	}
	
	/**
	* This tests: reset hand method
	*/
	public void testResetHand(){
		//Make a new player named King, and add 4 cards to his hand
		Player tester = new Player("King");
		tester.addCard(new Card(Suit.values()[1], 1));
		tester.addCard(new Card(Suit.values()[1], 3));
		tester.addCard(new Card(Suit.values()[1], 3));
		tester.addCard(new Card(Suit.values()[1], 7));
		assertEquals(4,tester.numberOfCards);
		
		//Resets the hand, removing all the cards
		tester.resetHand();
		assertEquals(0,tester.numberOfCards);
	}
	
	/**
	* This tests: the get sum method (which contains the Ace card handler)
	*/
	public void testGetSumOfHand(){
		//Make a new player named Teddy, and add 2 aces to his card.
		Player tester = new Player("Teddy");
		tester.addCard(new Card(Suit.values()[1], 1));
		tester.addCard(new Card(Suit.values()[2], 1));
		assertEquals(12,tester.getSumOfHand()); //Two aces should be 11 + 1
		
		//This shows an ace card can be 11 then 1
		Player tester2 = new Player("Teddy");
		tester2.addCard(new Card(Suit.values()[3], 1));
		assertEquals(11,tester2.getSumOfHand()); //One ace should be 11
		tester2.addCard(new Card(Suit.values()[0], 12));
		assertEquals(21,tester2.getSumOfHand()); //Add a face card to make it 11+10=21
		//I specifically used a face card above and 5 below to make sure I test all paths
		tester2.addCard(new Card(Suit.values()[0], 5));
		assertEquals(16,tester2.getSumOfHand()); //Add a 5, forcing the ace to be worth 1
												//Current hand: 1+10+5=16, NOT 11+10+5=26
		
		//This shows a hand with 3 aces, counting 1 as 11, and the other two as 1 eachPlayer tester2 = new Player("Matthew");
		Player tester3 = new Player("Teddy");
		tester3.addCard(new Card(Suit.values()[3], 1));
		assertEquals(11,tester3.getSumOfHand()); //One ace should be 11
		tester3.addCard(new Card(Suit.values()[3], 1));
		tester3.addCard(new Card(Suit.values()[3], 1));
		assertEquals(13,tester3.getSumOfHand()); //Add a face card to make it 11+1+1=13
		
	}
	
	/**
	* This tests: the add card method (to simulate the player hitting). ALSO tests a bust
	*/
	public void testAddCard(){
		//Make a new player named Gerald, and add 3, 10 and 8 valued cards to his hand
		//My add card method returns a true if the sum of the hand plus the new card is <= 21
		//Returns a false if you add a card and it exceeds the 
		Player playerOne = new Player("Gerald");
		//hit
		assertEquals(true,playerOne.addCard(new Card(Suit.values()[2], 3)));
		//hit (second hit)
		assertEquals(true,playerOne.addCard(new Card(Suit.values()[3], 13)));
		//hit (third hit)
		assertEquals(true,playerOne.addCard(new Card(Suit.values()[1], 8)));
		//hit (fourth attemted hit. BUST!)
		assertEquals(false,playerOne.addCard(new Card(Suit.values()[1], 2)));
		
	}
	
	/**
	* This tests: the visibility of the player's and dealer's hands, individually
	*/
	public void testVisibility() {
		Player user = new Player("User Player");
		Player dealer = new Player("Dealer");
		
		//AssertEquals to prove they have each been dealt 2 cards to begin with
		assertEquals(true,user.addCard(new Card(Suit.values()[2], 13)));
		assertEquals(true,user.addCard(new Card(Suit.values()[0], 1)));
		assertEquals(true,dealer.addCard(new Card(Suit.values()[2], 2)));
		assertEquals(true,dealer.addCard(new Card(Suit.values()[3], 7)));
				
		//print hands
		//The corresponding print method takes in a boolean called 'firstCardHidden
		System.out.println("Testing the visibility of the cards:\n");
		user.printHand(false); //false for the first card being hidden
		dealer.printHand(true); //true for the first card being hidden
		//results are shown in the console below
	}
	
	/**
	* This tests: the scoring of the player's and dealer's hands
	*/
	public void testScoring() {
		Player user = new Player("User Player");
		Player dealer = new Player("Dealer");
		
		//AssertEquals to prove they have each been dealt 2 cards to begin with
		assertEquals(true,user.addCard(new Card(Suit.values()[2], 5)));
		assertEquals(true,user.addCard(new Card(Suit.values()[0], 3)));
		assertEquals(true,user.addCard(new Card(Suit.values()[1], 4)));
		assertEquals(true,dealer.addCard(new Card(Suit.values()[2], 2)));
		assertEquals(true,dealer.addCard(new Card(Suit.values()[3], 7)));
		assertEquals(true,dealer.addCard(new Card(Suit.values()[2], 6)));
		
		//Gets the sum of the respective players' hands
		assertEquals(12,user.getSumOfHand());
		assertEquals(15,dealer.getSumOfHand());
	}
}