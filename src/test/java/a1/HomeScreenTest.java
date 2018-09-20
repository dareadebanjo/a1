package a1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


import javafx.embed.swing.JFXPanel;
import junit.framework.TestCase;

public class HomeScreenTest extends TestCase{
	/**
	* This shows I have options for console and file input. 
	* Uncomment line 27to run test
	*/
	 public void testHomeScreenLaunch() throws FileNotFoundException{
	     JFXPanel fxPanel = new JFXPanel();
	     HomeScreen game = new HomeScreen();
	     
	     //game.launch();
	     //					<----uncomment the line above to test
	 }
	 
	 
	 
	 
	/**
	* This tests the home screen file input with a hardcoded text file
	*/
	 public void testHomeScreen() throws Exception {
	     JFXPanel fxPanel = new JFXPanel();
	     HomeScreen game = new HomeScreen();
	     
	     Scanner textFile = new Scanner(new File("src/main/java/file2.txt"));
	     //game.init(textFile);
	     //						<----uncomment the line above to test
	 }

	 
	
	 
	 
	/**
	* This tests the players blackjack
	*/
	 public void testBlackjackPlayer() throws Exception {
	     JFXPanel fxPanel = new JFXPanel();
	     HomeScreen game = new HomeScreen();

	     //this file tests for players blackjack. 'SK HA HQ C8' are the first 2 cards
	     Scanner textFile = new Scanner(new File("src/test/java/playerBlackjack.txt"));
	     //game.init(textFile);
	     //						<----uncomment the line above to test
	 }
	 
	 
	/**
	* This tests the dealers blackjack
	*/
	 public void testBlackjackDealer() throws Exception {
	     JFXPanel fxPanel = new JFXPanel();
	     HomeScreen game = new HomeScreen();

	     //this file tests for dealers blackjack. 'SK H4 HQ CA' are the first 2 cards
	     Scanner textFile = new Scanner(new File("src/test/java/dealerBlackjack.txt"));
	     //game.init(textFile);
	     //						<----uncomment the line above to test
	 }
	 
	 
	 
		/**
		* This tests the dealer hitting repeatedly
		*/
		 public void testDealerHits() throws Exception {
		     JFXPanel fxPanel = new JFXPanel();
		     HomeScreen game = new HomeScreen();

		     //this file tests for dealers hitting repeatedly.
		     Scanner textFile = new Scanner(new File("src/main/java/file3.txt"));
		     //game.init(textFile);
		     //						<----uncomment the line above to test
		 }
	 
		 
		 

		 
		 
		 
		/**
		* This tests invalid inputs
		*/
		 public void testInvalidInput() throws Exception {
		     JFXPanel fxPanel = new JFXPanel();
		     HomeScreen game = new HomeScreen();
	
		     //this file tests for dealers blackjack. 'SK H4 HQ CA' are the first 2 cards
		     Scanner textFile = new Scanner(new File("src/test/java/invalidInputs.txt"));
		     //game.init(textFile);
		     //						<----uncomment the line above to test
		 }
	 
		 
	 
		 
}
