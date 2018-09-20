package a1;

import java.util.ArrayList;

/**
* An implementation of a blackjack player
*
* @author Joseph Daré Adebanjo
*/
public class Player {
	/**
	* The player's name
	*/
	public String name;
	//private Card[] tempCardsHolder;
	
	/**
	* The player's current hand
	*/
	private ArrayList<Card> hand = new ArrayList<Card>();

	/**
	* The number of cards the player has
	*/
	public int numberOfCards;
	
	/**
	* Player constructor
	*
	* @param aName		the player's name
	*/
	public Player(String aName) {
		this.name = aName;
		
		this.resetHand();
	}

	/**
	* Resets the player's hand
	*/
	public void resetHand() {
		for(int i=0; i<this.hand.size(); i++) {
			this.hand.remove(i);
		}
		this.numberOfCards = 0; 
	}
	
	/**
	* Player constructor
	*
	* @param aCard		the card being added
	* @return aBoolean	whether the sum of new hand is < or > 21
	*/
	public boolean addCard(Card aCard){
		this.hand.add(aCard);
		this.numberOfCards = this.hand.size();
		int currentSum = this.getSumOfHand();

		return (currentSum <= 21);
	}
	
	/**
	* Player constructor
	*
	* @return 	the sum of the hand
	*/
	public int getSumOfHand() {
		int sum = 0;
		int cardNumber;
		int numberOfAces = 0;
		
		for(int i=0; i<this.hand.size(); i++) {
			cardNumber = this.hand.get(i).getNumber();
			if(cardNumber==1) {//Ace card
				numberOfAces++;
				sum+=11;
			}else if(cardNumber > 10) {//Face card
				sum+=10;
			}else {
				sum+=cardNumber;
			}
			
			//If we have multiple aces and our sum surpasses 21, set all or some of them to value 1
			while((numberOfAces>0)&&(sum>21)) {
				sum-=10;
				numberOfAces--;
			}
		}
		return sum;
	}
	
	/**
	* Print the player's cards
	*
	* @param firstCardHidden 	Decides if first card is shown or not
	*/
	public void printHand(boolean firstCardHidden) {
		if(this.name=="Dealer")
			System.out.printf("%s's hand:\n", this.name);
		else
			System.out.printf("%s's hand (%d):\n", this.name, this.getSumOfHand());
		
		for(int i=0; i<this.hand.size(); i++) {
			if(firstCardHidden && (i==0)) 
				System.out.printf(" (card hidden)\n");
			else
				System.out.printf("  %s\n", this.hand.get(i).toString());
		}
		System.out.println();
	}

}
