package sjuan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * This class handle a players cards
 * @author Tobbe
 *
 */
public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Card> playerCards;
	private String name;
	private int clientID;

	/**
	 * the constructor creates a player
	 */
	public Player(int clientID) {
		playerCards = new ArrayList<Card>();
		this.clientID = clientID;
	}

	/**
	 * This method sets the players cards
	 * @param card gets a card
	 */
	public void setPlayerCards(Card card) {
		playerCards.add(card);

	}
	
	/**
	 * this method sets a players hand of cards
	 * @param playerCards takes in a hand of cards
	 */
	public void setPlayerCards(ArrayList<Card> playerCards) {
		this.playerCards = playerCards;
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

	/**
	 * this method returns the clientID of a player
	 * @return clientID returns a clientID of a player
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * this method sets the ClientID
	 * @param clientID takes in an int as clientID
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	/**
	 * this method returns a card from a players hand using a String name of the card
	 * @param cardName takes in a name of a card as a String
	 * @return a card the matches the cardName
	 */
	public Card getCardByName(String cardName) {
		for (Card card : playerCards) {
			if (card.toString().equals(cardName)) {
				return card;
			}
		}
		return null;
	}
}

