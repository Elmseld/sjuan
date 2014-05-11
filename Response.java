package sjuan;
import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * this class handle response
 * @author Sjuan
 *
 */
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	private String request, sql, cardName;
	private ArrayList <Card> cards, gameBoardCards;
	private int opponentCards1, opponentCards2, opponentCards3, clientID;
	private Card card;

	/**
	 * constructs a response containing a string
	 * @param str takes in a string-Object
	 */
	public Response(String request) {
		this.request = request;
	}

	/**
	 * constructs a response containing a string and a card-Object
	 * @param request takes in a string-Object
	 * @param card takes in a card-Object
	 */
	public Response (String request, Card card) {
		this.request = request;
		this.card = card;
	}

	public Response (String request, String sql) {
		this.request = request;
		this.sql = sql;
	}

	public Response (String request, String cardName, ArrayList<Card> cards,
			ArrayList<Card> gameBoardCards) {
		this.request = request;
		this.cardName = cardName;
		this.cards = cards;
		this.gameBoardCards = gameBoardCards;
		cards.trimToSize();
		gameBoardCards.trimToSize();
	}

	/**
	 * constructs a response containing four players hands of cards and a string-Object
	 * @param playerCardList takes in a cards of a player
	 * @param playerCardSize takes in a player cards size
	 * @param playerCardSize2 takes in a player cards size
	 * @param playerCardSize3 takes in a player cards size
	 */
	public Response(String request, int clientID, Player player1,
			Player player2, Player player3, Player player4 ) {
		this.request = request;
		this.clientID = clientID;
		this.cards = player1.getPlayerCards();
		this.opponentCards1 = player2.getPlayerCardSize();
		this.opponentCards2 = player3.getPlayerCardSize();
		this.opponentCards3 = player4.getPlayerCardSize();

	}

	/**
	 * this method returns a request
	 * @return request returns a request
	 */
	public String getRequest() {
		return request;

	}
	public ArrayList<Card> getCards() {
		return cards;
	}

	public String getSql(){
		return sql;
	}
	public int getOpponentCards1() {
		return opponentCards1;
	}
	public int getOpponentCards2() {
		return opponentCards2;
	}
	public int getOpponentCards3() {
		return opponentCards3;
	}

	/**
	 * this method returns ID for a client
	 * @return client returns Id for a client
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * this method returns a Card-Object
	 * @return card returns a Card-Object
	 */
	public Card getCard(){
		return card;
	}

	public String getCardName() {
		return cardName;
	}
	public ArrayList<Card> getGameBoardCards () {
		return gameBoardCards;
	}
}
