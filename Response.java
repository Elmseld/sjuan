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
	private int clientID;
	private Player player1, player2, player3, player4;
	private Card card;
	private ArrayList<Card> gameBoardCards;

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


	public Response (String request, String cardName, Player player1) {
		this.request = request;
		this.cardName = cardName;
		this.player1 = player1;
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
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
		this.player4 = player4;		

	}
	public Response(String request, String cardName, int clientID, Player player1,
			Player player2, Player player3, Player player4, ArrayList<Card> gameBoardCards) {
		this.request = request;
		this.cardName = cardName;
		this.clientID = clientID;
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
		this.player4 = player4;
		this.gameBoardCards = gameBoardCards;

	}

	/**
	 * this method returns a request
	 * @return request returns a request
	 */
	public String getRequest() {
		return request;

	}

	public String getSql(){
		return sql;
	}

	/**
	 * this method returns cards
	 * @return cards returns a list of strings
	 */
	//	public ArrayList <Card> getCards() {
	//		return cards;
	//
	//	}

	/**
	 * this method returns cards size of a player
	 * @return card.length returns size of a players hand
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * this method returns cards size of a opponent player
	 * @return opponentCards1 returns a int of a opponent card size
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * this method returns cards size of a opponent player
	 * @return opponentCards1 returns a int of a opponent card size
	 */
	public Player getPlayer3() {
		return player3;
	}

	/**
	 * this method returns cards size of a opponent player
	 * @return opponentCards1 returns a int of a opponent card size
	 */
	public Player getPlayer4() {
		return player4;
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
	public ArrayList<Card> getGameBoardCards() {
		return gameBoardCards;
	}
}
