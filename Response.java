package sjuan;
import java.io.*;
import java.sql.ResultSet;

/**
 * this class handle response
 * @author Sjuan
 *
 */
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	private String request;
	private Card [] cards;
	private int opponentCards1, opponentCards2, opponentCards3;


	
	
	/**
	 * constructs a response
	 * @param cards takes in cards
	 */
	public Response(Card[] cards) {
		this.cards = cards;
	}
	/**
	 * constructs a response
	 * @param request takes in a request
	 * @param playerCards takes in cards
	 */
	public Response(String request, Card[] cards) {
		this.request = request;
		this.cards = cards;
	}

	/**
	 * constructs a response 
	 * @param playerCardList takes in a cards of a player
	 * @param playerCardSize takes in a player cards size
	 * @param playerCardSize2 takes in a player cards size
	 * @param playerCardSize3 takes in a player cards size
	 */
	public Response(Card[] playerCardList, int playerCardSize,
			int playerCardSize2, int playerCardSize3) {
		this.cards = playerCardList;
		this.opponentCards1 = playerCardSize;
		this.opponentCards2 = playerCardSize2;
		this.opponentCards3 = playerCardSize3;

	}
	/**
	 * this method returns a request
	 * @return request returns a request
	 */
	public String getRequest() {
		return request;
	}
	/**
	 * this method returns cards
	 * @return cards returns a list of strings
	 */
	public Card [] getCards() {
		return cards;
	}
	
	/**
	 * this method returns cards size of a player
	 * @return card.length returns size of a players hand
	 */
	public int getCardSize(){
		return cards.length;
	}
	/**
	 * this method returns cards size of a opponent player
	 * @return opponentCards1 returns a int of a opponent card size
	 */
	public int getOpponentCards1() {
		return opponentCards1;
	}
	/**
	 * this method returns cards size of a opponent player
	 * @return opponentCards1 returns a int of a opponent card size
	 */
	public int getOpponentCards2() {
		return opponentCards2;
	}
	/**
	 * this method returns cards size of a opponent player
	 * @return opponentCards1 returns a int of a opponent card size
	 */
	public int getOpponentCards3() {
		return opponentCards3;
	}
}
