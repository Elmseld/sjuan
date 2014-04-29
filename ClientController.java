package sjuan;

import java.io.*;
import java.util.ArrayList;


public class ClientController {
	private ClientConnection connection;

	public ClientController(String serverIP, int serverPort) {
		try {
			connection = new ClientConnection(this, serverIP, serverPort);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void newRequest(String request) {
		try {

		} catch (Exception e) {
		}
	}

	public void exit() {
		connection.exit();
		System.exit(0);
	}

	public void newResponse(Response response) {
		ArrayList<Card> cards = response.getCards();
		String message = "Request: " + response.getRequest() + "\n\n";
		for(Card card : cards) {
			message += card + "\n";
		}
		System.out.println(message);
	}

	public static void main(String[] args) {
		new ClientController("127.0.0.1", 7766);
	}
}
