package sjuan;

import java.util.ArrayList;

/**
 * This class handle a players cards
 * @author Tobbe
 *
 */
public class Player {
	private ArrayList<Card> playerCards;

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
	 * This method prints players cards
	 */
	public void printCards() {
		for (int i = 0; i < playerCards.size(); i++) {
			System.out.print(playerCards.get(i) + ", ");
		}
	}
}

