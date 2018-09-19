package a1;
/**
* Card type implementation
*
* @author Joseph Daré Adebanjo
*/
public class Card {
	//One of the four suits
	private Suit mySuit;

	//The card's number
	private int myNumber;

	/**
	* Card constructor
	*
	* @param aSuit		the card's suit
	* @param aNumber	the card's number
	*/
	public Card(Suit aSuit, int aNumber){
		this.mySuit = aSuit;
		
		if (aNumber >= 1 && aNumber <= 13)
			this.myNumber = aNumber;
		else{
			System.err.println(aNumber + " is not a valid card number!");
			System.exit(1);
		}
	}

	public Card(String aSuitLetter, int aNumber){
		
		for(int i=0; i<3; i++){
			if(aSuitLetter.equals(Suit.values()[i].toString().charAt(0))){
				this.mySuit = Suit.values()[i];
			}
		}
		
		
		if (aNumber >= 1 && aNumber <= 13)
			this.myNumber = aNumber;
		else{
			System.err.println(aNumber + " is not a valid card number!");
			System.exit(1);
		}
	}

	/**
	* Number getter
	*
	* @return 	the card's number
	*/
	public int getNumber(){
		return this.myNumber;
	}

	public String toString(){
		/*
		String numStr = "Error" ;
		String[] cards = {"","Ace", "Two", "Three", "Four", "Five", "Six", 
		"Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

		for(int i=1; i<=13; i++){
			if(this.myNumber==i)
				numStr = cards[i];
		}
		*/
		
		if(this.myNumber>=11)
			return String.valueOf((this.mySuit.toString()).charAt(0)) + 10;
		else if (this.myNumber<11)
			return String.valueOf((this.mySuit.toString()).charAt(0)) + this.myNumber;
		
		return "";
	}
}
