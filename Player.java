package sjuan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * This class handle a players cards
 * @author Tobbe
 *
 */
public class Player {
	private ArrayList<Card> playerCards;
	private String name;

	/**
	 * the constructor creates a player
	 */
	public Player() {
		playerCards = new ArrayList<Card>();
	
	}

	/**
	 * This method sets the players cards
	 * @param card gets a card
	 */
	public void setPlayerCards(Card card) {
		playerCards.add(card);
	
	}

	/**
	 * this method translates players ArrayList of Cards to an Array of Strings
	 * @return
	 */
	public String[] getPlayerCardsToString() {
		String[] cards = new String[playerCards.size()];
		for (int i = 0; i < cards.length; i++){
			cards[i] = playerCards.get(i).toString();
		}
		return cards;
	}

	/**
	 * this method returns a player cards as in a ArrayList of Card
	 * @return playerCards returns a ArrayList of Card
	 */
	public ArrayList<Card> getPlayerCards() {
		return playerCards;
	}
	/**
	 * This method prints players cards
	 */
	public void printCards() {
		for (int i = 0; i < playerCards.size(); i++) {
			System.out.print(playerCards.get(i) + ", ");
		}
	}
	/**
	 * this method returns players size of a hand
	 * @return playerCards.size() returns players hand of cards 
	 */
	public int getPlayerCardSize() {
		return playerCards.size();
	}
}

