package a1;
/**
* Deck of cards implementation
*
* @author Joseph Daré Adebanjo
*/
import java.util.*;

public class Deck {
	//The array of cards in the current deck
	//private Card[] tempCardsHolder;
	private Card tempCardsHolder;
	private ArrayList<Card> myCards = new ArrayList<Card>();
		//private LinkedList<Card> myCards;

	//The number of cards currently in the deck
	private int numberOfCards;

	/**
	* Deck constructor (default)
	*/
	public Deck(){
		//call the other constructor, with one un-shuffled deck
		this(1, false);
	}

	/**
	* Deck constructor
	*
	* @param numDecks	the number of individual decks
	* @param shuffle	will the cards be shuffled?
	*/
	public Deck(int numDecks, boolean shuffle){
		this.numberOfCards = numDecks * 52;
		
		//For every deck
		for(int d=0; d<numDecks; d++){
			//For every suit
			for(int s=0; s<4; s++){
				//For every number
				for(int n=1; n<=13; n++){
					//Add a new card
					tempCardsHolder = new Card(Suit.values()[s], n);
					this.myCards.add(this.tempCardsHolder);
				}
			}
		}

		//Shuffle if needed
		if(shuffle)
			this.shuffleCards();
	}
	
	/**
	* Number of cards in a deck getter
	*
	* @return 	the number of cards shuffled (incase of multiple decks)
	*/
	public int getNumber(){
		return this.numberOfCards;
	}

	/**
	* Shuffle deck by randomly swapping pairs of cards
	*/
	private void shuffleCards(){
		//Random # generator
		Random randomNumberGen = new Random();
		Card holder;
		int randomNumber;
		for(int i=0; i<this.numberOfCards; i++){
			randomNumber = randomNumberGen.nextInt(this.numberOfCards);
			//holder = this.myCards[i];
			//this.myCards[i] = this.myCards[randomNumber];
			//this.myCards[randomNumber] = holder;
			holder = myCards.get(i);
			this.myCards.set(i, this.myCards.get(randomNumber));
			this.myCards.set(randomNumber, holder);
		}
	}

	/**
	* Deal the top card, aka. the 1st card in the array
	*
	* @return the top card
	*/
	public Card dealNextCard(){
		//Get, then remove the card at the top
		Card topCard = this.myCards.get(0);
		this.myCards.remove(0);
		//Update card counter
		this.numberOfCards--;
		return topCard;
	}
	
	/**
	* Print the top cards
	*
	* @param numToPrint		the nubmer of cards from the top to print
	*/
	public void printDeck(int numToPrint){
		for(int c=0; c<numToPrint; c++){
			System.out.printf("% 3d/%d: %s\n", c+1, this.numberOfCards, 
										this.myCards.get(c).toString());
		}
		System.out.printf("\t[%d more cards]\n\n", this.numberOfCards-numToPrint);

	}

}
