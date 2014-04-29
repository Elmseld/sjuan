package sjuan;
import java.io.*;
import java.util.ArrayList;

public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	private String request;
	private ArrayList<Card> cards;

	public Response(String request, ArrayList<Card> cards) {
		this.request = request;
		this.cards = cards;
	}

	public String getRequest() {
		return request;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
}
