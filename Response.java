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
	private String request, sql;
	private ArrayList <Card> cards;
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
	

	public Response (String request, Card card, int clientID) {
		this.request = request;
		this.card = card;
		this.clientID = clientID;
	}
	/**
	 * constructs a response containing four players hands of cards and a string-Object
	 * @param playerCardList takes in a cards of a player
	 * @param playerCardSize takes in a player cards size
	 * @param playerCardSize2 takes in a player cards size
	 * @param playerCardSize3 takes in a player cards size
	 */
	public Response(ArrayList<Card> playerCardList, int playerCardSize,
			int playerCardSize2, int playerCardSize3, String request, int clientID ) {
		this.cards = playerCardList;
		this.opponentCards1 = playerCardSize;
		this.opponentCards2 = playerCardSize2;
		this.opponentCards3 = playerCardSize3;
		this.request = request;
		this.clientID = clientID;		

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
	public ArrayList <Card> getCards() {
		return cards;

	}
	
	/**
	 * this method returns cards size of a player
	 * @return card.length returns size of a players hand
	 */
	public int getCardSize(){
		return cards.size();
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
}
