package sjuan;
import java.io.*;

public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	private String request;
	private String[] cards;

	public Response(String request, String[] cards) {
		this.request = request;
		this.cards = cards;
	}

	public String getRequest() {
		return request;
	}

	public String[] getCards() {
		return cards;
	}
}
