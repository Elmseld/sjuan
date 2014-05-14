package sjuan;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;

/**
 * this class handles request
 * @author Sjuan
 *
 */
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	private String request, cardName;
	private String sql;
	private int clientID;

	/**
	 * constructs a request
	 * @param request takes in a request
	 */
	public Request(String request) {
		this.request = request;
	}

	public Request(String request, int clientID) {
		this.request = request;
		this.clientID = clientID;

	}

	/**
	 * constructs a request
	 * @param request takes in a request
	 * @param cardName takes in a name of a card
	 */
	public Request(String request, String cardName) {
		this.request = request;
		this.cardName = cardName;
	}

	/**
	 * constructs a request
	 * @param request takes in a request
	 * @param cardName takes in a cardName of a card
	 * @param clientID takes in the clients ID
	 */
	public Request(String request, String cardName, int clientID) {
		this.request = request;
		this.cardName = cardName;
		this.clientID = clientID;
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
}

