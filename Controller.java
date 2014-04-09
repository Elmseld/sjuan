package sjuan;

import java.util.ArrayList;
import java.util.Random;
/**
 * This class controlls the logic of the game "Sjuan"
 * @author Tobbe
 *
 */
public class Controller {
	private ArrayList<Card> cardDeck;
	private Player player1, player2, player3, player4;

	/**
	 * Constructs a controller
	 */
	public Controller() {

	}
	/**
	 * Sets card values in the cardDeck
	 */
	public void setCardDeck() {
		cardDeck = new ArrayList<Card>();
		String[] values  = {
				"s1","s2","s3","s4","s5","s6","s7","s8","s9","s10","sj","sd","sk",
				"h1","h2","h3","h4","h5","h6","h7","h8","h9","h10","hj","hd","hk",
				"d1","d2","d3","d4","d5","d6","d7","d8","d9","d10","dj","dd","dk",
				"c1","c2","c3","c4","c5","c6","c7","c8","c9","c10","cj","cd","ck"};
		for (int i = 0; i < values.length;i++) 
			cardDeck.add(new Card(values[i]));

		//		for(int i = 0; i<cardDeck.size(); i++) 
		//			System.out.print(cardDeck.get(i).getValue() + ", ");  
	}
	// denna metod ska dela ut 13 kort till varje spelare
	public void deal() {
		Random rand = new Random();
		rand.nextInt(51);
		
		

	}

	//för test av controllern
	public static void main (String[] args) {
		Controller control = new Controller();
		control.setCardDeck();

	}
}
