package a1;

import junit.framework.TestCase;

public class DeckTest extends TestCase{
	/**
	* This tests: the deck constructor AND also the getter method
	*/
	public void testDeck(){
		//Make a deck of card, and shuffle it; test to confirm it has 52 cards
		Deck tester = new Deck(1, true);
		
		assertEquals(52,tester.getNumber());
		

		//Make 2 decks of card, and shuffle them; test to confirm they are a total of 104 cards
		Deck tester2 = new Deck(2, true);
		
		assertEquals(104,tester2.getNumber());
	}
	
	/**
	* This tests: the deck's shuffler AND next card dealer methods
	*/
	public void testShuffleCards() {
		Deck notShuffledOne = new Deck(1, false);
		Deck notShuffledTwo = new Deck(1, false);
		Deck shuffledOne = new Deck(1, true);
		Deck shuffledTwo = new Deck(1, true);
		
		//Each line pulls the top card from two different un-shuffleded decks, and checks if they are equal
		assertEquals(true, notShuffledOne.dealNextCard().getNumber() == notShuffledTwo.dealNextCard().getNumber());
		assertEquals(true, notShuffledOne.dealNextCard().getNumber() == notShuffledTwo.dealNextCard().getNumber());
		assertEquals(true, notShuffledOne.dealNextCard().getNumber() == notShuffledTwo.dealNextCard().getNumber());
		assertEquals(true, notShuffledOne.dealNextCard().getNumber() == notShuffledTwo.dealNextCard().getNumber());
		
		//Each line pulls the top card from two different shuffleded decks, and checks if they are equal
		assertEquals(false, shuffledOne.dealNextCard().getNumber() == shuffledTwo.dealNextCard().getNumber());
		assertEquals(false, shuffledOne.dealNextCard().getNumber() == shuffledTwo.dealNextCard().getNumber());
		assertEquals(false, shuffledOne.dealNextCard().getNumber() == shuffledTwo.dealNextCard().getNumber());
		assertEquals(false, shuffledOne.dealNextCard().getNumber() == shuffledTwo.dealNextCard().getNumber());
	}
	

}
