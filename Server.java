package sjuan;

import java.sql.Connection;

/**
 * This class handles the server communication with clients
 * @author Tobbe
 *
 */
public class Server {
	private int clientID, connect1 = 0, connect2 = 0, connect3 = 0, connect4 = 0, port;
	private Controller controller;
	private ServerConnection connection1, connection2, connection3, connection4;

	/**
	 * constructs a server 
	 * @param port takes in a portNumber
	 * @param player1 takes in a player
	 * @param player2 takes in a player
	 * @param player3 takes in a player
	 * @param player4 takes in a player
	 * @param control takes in a controller
	 */
	public Server(int port, Controller control) {
		try {
			this.controller = control;
			this.port = port;
			new ConnectToServer(this, port);
		}
		catch (Exception e) { // IOException, ClassNotFoundException
			System.out.println(e);
			e.getStackTrace();
		}
	}

	public void newClient(ServerConnection connection, int counter) {
		Player player = new Player(counter);
		controller.setPlayerOrder(player);
		setConnection(counter, connection);
	}

	public void setConnection(int counter, ServerConnection connection) {
		if (connect1==0) {
			connect1 = counter;
			connection1 = connection;
		}
		else if (connect2==0) {
			connect2 = counter;
			connection2 = connection;

		}
		else if (connect3==0) {
			connect3 = counter;
			connection3 = connection;

		}
		else if (connect4==0) {
			connect4 = counter;
			connection4 = connection;

		}
		else {
			new Controller(port);
		}
	}


	/**
	 * this method returns a clientID
	 * @return clientID returns a int of a clientID
	 */
	public int getClientID() {
		return clientID;
	}


	/**
	 * this method creates a response for a client to receive
	 * @param connection takes in connection from a client
	 * @param request takes in a request to decide what to do
	 */
	public synchronized void newRequest(ServerConnection connection, Request request) {

		if (request.getRequest().equals("new")) {
			if (controller.playersExist()) {

				Player player1 = controller.whoHaveHeartSeven();
				//				connection1 = player1.getClientID();
				Player player2 = controller.getPlayer2();
				//				connection2 = player2.getClientID();
				Player player3 = controller.getPlayer3();
				//				connection3 = player3.getClientID();
				Player player4 = controller.getPlayer4();
				//				connection4 = player4.getClientID();
				//				if (connection1 == player1.getClientID()) 
				connection1.newResponse(new Response("new", player1, player2.getPlayerCardSize(),
						player3.getPlayerCardSize(), player4.getPlayerCardSize()));
				//				else if (connection2==player2.getClientID())
				connection2.newResponse(new Response("new", player2, player3.getPlayerCardSize(), 
						player4.getPlayerCardSize(), player1.getPlayerCardSize()));
				//				else if (connection3==player3.getClientID())
				connection3.newResponse(new Response("new", player3, player4.getPlayerCardSize(),
						player1.getPlayerCardSize(), player2.getPlayerCardSize()));
				//				else if (connection4==player4.getClientID())
				connection4.newResponse(new Response("new", player4, player1.getPlayerCardSize(),
						player2.getPlayerCardSize(), player3.getPlayerCardSize()));
				//				else 
				System.out.println("clientID stämmer inte");
			}

			else if(request.getRequest().equals("pass")) {
				if (controller.checkIfPassIsPossible()) {
					connection.newResponse(new Response("pass"));
				}
				else {
					connection.newResponse(new Response("passainte"));
				}
			}

			else if(request.getRequest().equals("end")){
				connection.newResponse(new Response("end", controller.getDataBas()));
			}

			else if (request.getRequest().equals("playCard")) {
				if (controller.checkIfCardIsPlayable(request.getCardName(), request.getClientID())){
					connection1.newResponse(new Response("playCard", request.getCardName(), 
							controller.getPlayer(request.getClientID()), 
							controller.getGameBoardCards()));
				}
				else {
					connection.newResponse(new Response("dontPlayCard"));
				}
			}
			else {
				connection.newResponse(new Response("clientsMissing"));

			}
		}
	}
}