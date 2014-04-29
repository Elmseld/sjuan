package sjuan;

import java.util.ArrayList;
/**
 * This class handles the Server
 * @author Tobbe
 *
 */
public class Server {
	private ArrayList<Card> playerCards;

	/**
	 * comstructs a server
	 * @param port takes a portNumber
	 * @param player takes in a player
	 */
	public Server(int port, Player player) {
		try {
			playerCards = player.getPlayerCards();
			//	getPlayerCards(player);
			new ConnectToServer(this,port);

		}
		catch (Exception e) { // IOException, ClassNotFoundException
			System.out.println(e);
		}
	}
	public void setPlayerCards(Player player) {
		this.playerCards = player.getPlayerCards();
	}
	public void newClient(ServerConnection connection) {
		// om servern behöver lagra referens till klienterna
	}

	public void newRequest(ServerConnection connection, Request request) {
		connection.newResponse(new Response(request.getRequest(),playerCards));
	}

	public static void main(String[] args) {
		Player player1 = new Player();
		Deck deck = new Deck();
		Controller control = new Controller(player1, deck);
		control.dealCards(player1);
		player1.printCards();
		new Server(7766, player1);
	}
}