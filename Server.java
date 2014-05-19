package sjuan;

import javax.swing.JOptionPane;

/**
 * This class handles the server communication with clients
 * @author Tobbe
 *
 */
public class Server {
	private Player player1, player2, player3, player4;
	private int clientID;
	private Controller controller;

	/**
	 * constructs a server 
	 * @param port takes in a portNumber
	 * @param player1 takes in a player
	 * @param player2 takes in a player
	 * @param player3 takes in a player
	 * @param player4 takes in a player
	 * @param control takes in a controller
	 */
	public Server(int port, Player player1, Player player2, Player player3, Player player4, Controller control) {
		try {
			this.controller = control;
			this.player1 = player1;
			this.player2 = player2;
			this.player3 = player3;
			this.player4 = player4;

			new ConnectToServer(this,port);
		}
		catch (Exception e) { // IOException, ClassNotFoundException
			System.out.println(e);
			e.getStackTrace();
		}
	}

	public void newClient(ServerConnection connection , int counter) {
		clientID = counter;

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
			controller.Deal(player1, player2, player3, player4);
			controller.whoHaveHeartSeven();
			if (clientID==player1.getClientID())
				connection.newResponse(new Response("new", clientID, player1, player2,
						player3, player4));
			else if (clientID==player2.getClientID())
				connection.newResponse(new Response("new", clientID, player2, player3,
						player4, player1));
			else if (clientID==player3.getClientID())
				connection.newResponse(new Response("new", clientID, player3, player4,
						player1, player2));
			else if (clientID==player4.getClientID())
				connection.newResponse(new Response("new", clientID, player4, player1,
						player2, player3));
			else 
				System.out.println("clientID stämmer inte");
		}
		
		else if(request.getRequest().equals("Login")){
			
			connection.newResponse(new Response("Login", controller.logInDb(request.getUserName(), request.getPassWord())));
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
				connection.newResponse(new Response("playCard", request.getCardName(), 
						controller.getPlayer(request.getClientID()).getPlayerCards(), 
						controller.getGameBoardCards()));
			}
			else {
				connection.newResponse(new Response("dontPlayCard"));
			}
		}
	}
}
