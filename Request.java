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
	private String request;
	private String sql;
	private Card card;
	private int clientID;


	/**
	 * constructs a request
	 * @param request takes in a request
	 */
	public Request(String request) {
		this.request = request;
	}


	/**
	 * constructs a request
	 * @param request takes in a request
	 * @param card takes in a card
	 */
	public Request(String request, Card card) {
		this.request = request;
		this.card = card;
	}
	
	public Request(String request, Card card, int clientID) {
		this.request = request;
		this.card = card;
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
	 * this method returns a card
	 * @return card returns a requested card
	 */
	public Card getCard() {
		return card;
	}
	
	public int getClientID() {
		return clientID;
	}
}

