package sjuan;

import java.util.ArrayList;
/**
 * This class handles the Server
/**
 * This class handles the server communication with clients
 * @author Tobbe
 *
 */
public class Server {
	private Player player1, player2, player3, player4;

	/**
	 * constructs a server 
	 * @param port takes in a protNumber
	 * @param player1 takes in a player
	 * @param player2 takes in a player
	 * @param player3 takes in a player
	 * @param player4 takes in a player
	 * @param control takes in a controller
	 */
	public Server(int port, Player player1, Player player2, Player player3, Player player4, Controller control) {
		try {
			control.Deal();
			this.player1 = player1;
			this.player2 = player2;
			this.player3 = player3;
			this.player4 = player4;

			new ConnectToServer(this,port);

		}
		catch (Exception e) { // IOException, ClassNotFoundException
			System.out.println(e);
		}
	}

	public void newClient(ServerConnection connection) {
		// om servern behöver lagra referens till klienterna
	}

	/**
	 * this method creates a response ???? add more later here
	 * @param connection
	 * @param request
	 */
	public synchronized void newRequest(ServerConnection connection, Request request) {
		connection.newResponse(new Response(request.getRequest(),player1.getPlayerCardList()));
		connection.newResponse(new Response(request.getRequest(),player2.getPlayerCardList()));
		connection.newResponse(new Response(request.getRequest(),player3.getPlayerCardList()));
		connection.newResponse(new Response(request.getRequest(),player4.getPlayerCardList()));

	}
}