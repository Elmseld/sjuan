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
	public Card[] getPlayerCardList() {
		Card[] cards = new Card[playerCards.size()];
		for (int i = 0; i < cards.length; i++){
			cards[i] = playerCards.get(i);
		}
		return cards;

	}
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
}

