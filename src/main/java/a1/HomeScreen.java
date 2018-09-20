package a1;
/**
* Home Screen/Main
*
* @author Joseph Daré Adebanjo
*/
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HomeScreen{
	//Screen items
	Label label = new Label("Welcome To Blackjack");
	Label player1 = new Label("User Player");
	Label player2 = new Label("Dealer");
	Button chooseBtn;
	TextField answerBox;
	ComboBox<String> optionsDropdown;
	
	public HomeScreen() throws FileNotFoundException{
		//
	}

	public HomeScreen(Scanner input) throws FileNotFoundException{
		init(input);
	}

	public static void main(String[] args) {
		launch();
	}

	public static void launch() {
		//game
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Deck myDeck;
		Boolean youWon = false;
		Boolean dealerWon = false;
		boolean userDone;
		boolean dealerDone;
		String answer;
		String mode;
		Boolean modeRight = false;
		
		System.out.print("Would you like to play on console or file input? (C/F):");
		while(!modeRight){
			mode = sc2.next();
			System.out.println();
			if(mode.compareToIgnoreCase("F")==0) {
				Scanner input = null;
				try {
					input = new Scanner(new File("src/main/java/file3.txt"));
				} catch (FileNotFoundException e) {
					System.out.println("Error, file not found");
					e.printStackTrace();
				}
				init(input);
			}else if(mode.compareToIgnoreCase("C")==0) {
				while(!youWon || !dealerWon){
					//initialize players
					Player user = new Player("User Player");
					Player dealer = new Player("Dealer");
					myDeck = new Deck(1, true);
					userDone = false;
					dealerDone = false;
					answer = "";
					boolean correctAns = false;
							
					user.addCard(myDeck.dealNextCard());
					dealer.addCard(myDeck.dealNextCard());
					user.addCard(myDeck.dealNextCard());
					dealer.addCard(myDeck.dealNextCard());
							
					//print hands
					System.out.println("Cards have been dealt");
					user.printHand(false);
					dealer.printHand(true);
					System.out.println("\n");
					int userSum = user.getSumOfHand();
					int dealerSum = dealer.getSumOfHand();
					
					//Checks for an initial blackjack
					if((userSum<=21)&&(dealerSum==21)){
						System.out.println("Blackjack! Dealer wins, better luck next time " + user.name + "...");
						youWon = false;
						dealerWon = true;
						userDone = true;
						dealerDone = true;
					}else if((userSum==21)&&(dealerSum<21)){
						System.out.println("Blackjack! " + user.name + " wins, better luck next time dealer...");
						youWon = true;
						dealerWon = false;
						userDone = true;
						dealerDone = true;
					}

					while(!userDone) {
						//Player's turn still
						if(!userDone) {
							System.out.print("Hit or Stay? (Enter H or S): ");
							Boolean correctReply = false;
							while(!correctReply){
								answer = sc.next();
								System.out.println();
								
								//If the player hits
								if(answer.compareToIgnoreCase("H")==0) {
									//Add the next card from the deck, and indicate if they're busted
									userDone = !user.addCard(myDeck.dealNextCard());
									user.printHand(false);
									correctReply = true;
								}else if(answer.compareToIgnoreCase("S")==0){
									userDone = true;
									user.printHand(false);
									correctReply = true;
								}else{
									System.out.println("Wrong answer, try again!");
									correctReply = false;
								}
							}
						}
						
					}
					

					if(user.getSumOfHand()>21){
						System.out.println("Tough luck next time " + user.name + "... dealer wins from your bust.");
						youWon = false;
						dealerWon = true;
						dealerDone = true;
					}
					
					while(!dealerDone){
						//Dealer's turn still
						if(!dealerDone) {
							if((dealer.getSumOfHand()<17) || ((dealer.getSumOfHand()==17)&&(dealer.numberOfCards==2))){
								System.out.println("The dealer hits");
								//Add the next card from the deck, and indicate if they're busted
								dealerDone = !dealer.addCard(myDeck.dealNextCard());
								dealer.printHand(false);
							}else {
								System.out.println("The dealer stays");
								dealerDone = true;
							}
						}
						System.out.println();
					}
							
					
					userSum = user.getSumOfHand();
					dealerSum = dealer.getSumOfHand();
					if(!dealerWon){
						user.printHand(false);
						dealer.printHand(false);
					}

					if(userSum>21){
						System.out.println(user.name + " loses due to a bust.");
						youWon = false;
						dealerWon = true;
					}else if(dealerSum>21){
						System.out.println(dealer.name + " loses due to a bust.");
						youWon = true;
						dealerWon = false;
					}else if(((userSum>dealerSum) && (userSum<=21))){
						System.out.println("Congrats " + user.name + ", you're a winner!");
						youWon = true;
						dealerWon = false;
					}else if(dealerSum==21){
						System.out.println("Tough luck next time " + user.name + "... dealer wins.");
						youWon = false;
						dealerWon = true;
					}else{
						System.out.println("Tough luck next time " + user.name + "... dealer wins.");
						youWon = false;
						dealerWon = true;
					}

					while(!correctAns) {
						System.out.print("Would you like to play again? (Y or N)");
						answer = sc.next();
						System.out.println();
						if(answer.compareToIgnoreCase("N")==0) {
							System.exit(0);
						}else {
							correctAns=true;
							userDone = false;
							youWon = false;
							dealerDone = false;
							dealerWon = false;
							user.resetHand();
							dealer.resetHand();
						}
					}
				}
				modeRight = true;
			}else{
				System.out.println("Wrong answer, try again!");
				modeRight = false;
			}
		}
		
	}

	public static void init(Scanner textFile){
		Deck otherDeck;
		Boolean otherYouWon = false;
		Boolean otherDealerWon = false;
		boolean otherUserDone = false;
		boolean otherDealerDone = false;
		//String answer;

		//initialize players
		Player otherUser = new Player("Input User Player");
		Player otherDealer = new Player("Input Dealer");
		otherDeck = new Deck(1, true);
		boolean correctAns = false;

		//The first two cards dealt for the user
		for(int i=0; i<2; i++){
			String thisCard = textFile.next();
			if(thisCard==null){
				System.out.println("Nothing left.");
				return;
			}
			int x = 0;
			//answer.compareToIgnoreCase("H")==0;
			if(faceCardHandler(thisCard.substring(1)) || faceCardHandler(thisCard.substring(1)) || faceCardHandler(thisCard.substring(1)))
				x = 10;
			else if(thisCard.substring(1).equals("A"))
				x = 1;
			else
				x = Integer.parseInt(thisCard.substring(1));

			otherUser.addCard(new Card(whatSuit(thisCard.charAt(0)),x));
		}

		//The next two cards dealt for the dealer
		for(int i=0; i<2; i++){
			String thisCard2 = textFile.next();
			if(thisCard2==null){
				System.out.println("Nothing left.");
				return;
			}
			int x2 = 0;
			if(faceCardHandler(thisCard2.substring(1)) || faceCardHandler(thisCard2.substring(1)) || faceCardHandler(thisCard2.substring(1)))
				x2 = 10;
			else if(thisCard2.substring(1).equals("A"))
				x2 = 1;
			else
				x2 = Integer.parseInt(thisCard2.substring(1));
			otherDealer.addCard(new Card(whatSuit(thisCard2.charAt(0)), x2));
		}
		System.out.println("Cards have been dealt");
		otherUser.printHand(false);
		otherDealer.printHand(true);

		int otherUserSum = otherUser.getSumOfHand();
		int otherDealerSum = otherDealer.getSumOfHand();

		//Checks for an initial blackjack
		if((otherUserSum<=21)&&(otherDealerSum==21)){
			System.out.println("Blackjack! Input dealer wins, better luck next time " + otherUser.name + "...");
			otherYouWon = false;
			otherDealerWon = true;
			otherUserDone = true;
			otherDealerDone = true;
		}else if((otherUserSum==21)&&(otherDealerSum<21)){
			System.out.println("Blackjack! " + otherUser.name + " wins, better luck next time input dealer...");
			otherYouWon = true;
			otherDealerWon = false;
			otherUserDone = true;
			otherDealerDone = true;
		}

		while(!otherDealerWon || !otherYouWon){
			String thisInput = textFile.next();
			if(thisInput.length()==1){
				if(!otherUserDone) {
					//If the player hits
					if(thisInput.equals("H")) {
						System.out.println(otherUser.name + " chose to hit.");
					}else if(thisInput.equals("S")){
						System.out.println(otherUser.name + " chose to stand.\n");
						otherUserDone = true;
						//otherUser.printHand(false);
					}else{
						System.out.println("Invalid input, skipping command!");
					}
				}else if(!otherDealerDone){
					if(thisInput.equals("S")){
						System.out.println(otherDealer.name + " chose to stand.");
						otherDealerDone = true;
						//otherDealer.printHand(false);
					}
				}
			}else{
				if(!otherUserDone) {
					//If the player hits
					int x = 0;
					if(faceCardHandler(thisInput.substring(1)) || faceCardHandler(thisInput.substring(1)) || faceCardHandler(thisInput.substring(1)))
						x = 10;
					else if(thisInput.substring(1).equals("A"))
						x = 1;
					else
						x = Integer.parseInt(thisInput.substring(1));

					otherUser.addCard(new Card(whatSuit(thisInput.charAt(0)),x));
					//otherUser.addCard(new Card(Character.toString(thisInput.charAt(0)), Integer.valueOf(thisInput.substring(1))));
					otherUser.printHand(false);
				}else{
					//If the dealer hits
					int x2 = 0;
					if(faceCardHandler(thisInput.substring(1)) || faceCardHandler(thisInput.substring(1)) || faceCardHandler(thisInput.substring(1)))
						x2 = 10;
					else if(thisInput.substring(1).equals("A"))
						x2 = 1;
					else
						x2 = Integer.parseInt(thisInput.substring(1));
					System.out.println("Input dealer chose to hit");
					otherDealer.addCard(new Card(whatSuit(thisInput.charAt(0)), x2));
					//otherDealer.addCard(new Card(Character.toString(thisInput.charAt(0)), Integer.valueOf(thisInput.substring(1))));
					otherDealer.printHand(false);
				}
			}
			otherUserSum = otherUser.getSumOfHand();
			otherDealerSum = otherDealer.getSumOfHand();

			if(otherDealerSum>17){
				System.out.println(otherDealer.name + " stands.");
				otherDealerDone = true;
			}

			if(otherDealerDone){
				if(otherUserSum>21){
					System.out.println(otherUser.name + " loses due to a bust.");
					otherYouWon = false;
					otherDealerWon = true;
					System.exit(0);
				}else if(otherDealerSum>21){
					System.out.println(otherDealer.name + " loses due to a bust.");
					otherYouWon = true;
					otherDealerWon = false;
					System.exit(0);
				}else if(((otherUserSum>otherDealerSum) && (otherUserSum<=21))){
					System.out.println("Congrats " + otherUser.name + ", you're a winner!");
					otherYouWon = true;
					otherDealerWon = false;
					System.exit(0);
				}else if(otherDealerSum==21){
					System.out.println("Tough luck next time " + otherUser.name + "... " + otherDealer.name + " dealer wins.");
					otherYouWon = false;
					otherDealerWon = true;
				}else{
					System.out.println("Tough luck next time " + otherUser.name + "... " + otherDealer.name + " dealer wins.");
					otherYouWon = false;
					otherDealerWon = true;
				}
			}
		}
		
		System.exit(0);
			
	}

	private static boolean faceCardHandler(String faceCard) {
		if(faceCard.equals("J")) 
			return true;
		else if(faceCard.equals("Q"))
			return true;
		else if(faceCard.equals("K"))
			return true;
		return false;
	}

	private static Suit whatSuit(char suit) {
		if(suit=='C') {
			return Suit.values()[0];
		}else if(suit=='D') {
			return Suit.values()[1];
		}else if(suit=='S') {
			return Suit.values()[2];
		}else if(suit=='H') {
			return Suit.values()[3];
		}
		return Suit.values()[0];
	}


	private void initUI(Stage primaryStage) {
		Pane canvas = new Pane();
		//canvas.setStyle("-fx-background-color: black");
		
		Scene scene = new Scene(canvas, 320, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("COMP3000 A1 Blackjack App");
		primaryStage.show();

		
	}

	public void exit() {
		System.exit(0);
	}
}