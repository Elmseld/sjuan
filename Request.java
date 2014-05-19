package sjuan;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;

/**
 * this class handles requests
 * @author Sjuan
 *
 */
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	private String request, cardName;
	private String sql;
	private int clientID, gameID;

	/**
	 * constructs a request
	 * @param request takes in a request
	 */
	public Request(String request) {
		this.request = request;
	}

	public Request(String request, int clientID, int gameID) {
		this.request = request;
		this.clientID = clientID;
		this.gameID = gameID;

	}

	/**
	 * constructs a request
	 * @param request takes in a request
	 * @param cardName takes in a name of a card
	 */
	public Request(String request, String cardName, int gameID) {
		this.request = request;
		this.cardName = cardName;
		this.gameID = gameID;

	}

	/**
	 * constructs a request
	 * @param request takes in a request
	 * @param cardName takes in a cardName of a card
	 * @param clientID takes in the clients ID
	 */
	public Request(String request, String cardName, int clientID, int gameID) {
		this.request = request;
		this.cardName = cardName;
		this.clientID = clientID;
		this.gameID = gameID;

	}

	/**
	 * this method returns a request
	 * @return request returns a request as a string
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * this method returns a clientID
	 * @return clientID returns a requested clientID
	 */

	public int getClientID() {
		return clientID;
	}

	/**
	 * this method returns a cardName of a card
	 * @return cardName returns a cardName of a card
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * this method returns a gameID
	 * @return gameID returns a Integer of a game
	 */
	public int getGameID() {
		return gameID;
	}
}

