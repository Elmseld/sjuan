package sjuan;

import java.sql.Connection;

/**
 * This class handles the server communication with clients
 * @author Tobbe
 *
 */
public class Server {
	private int clientID, gameID = 0;
	private Controller controller;
	private Lobby lobby = new Lobby();

	/**
	 * constructs a server 
	 * @param port takes in a portNumber
	 * @param player1 takes in a player
	 * @param player2 takes in a player
	 * @param player3 takes in a player
	 * @param player4 takes in a player
	 * @param control takes in a controller
	 */
	public Server(int port) {
		try {
			new ConnectToServer(this, port);
		}
		catch (Exception e) { // IOException, ClassNotFoundException
			System.out.println(e);
			e.getStackTrace();
		}
	}

	public void newClient(ServerConnection connection, int counter) {
		clientID = counter;
	}
	/**
	 * this method returns a clientID
	 * @return clientID returns a int of a clientID
	 */
	public int getClientID() {
		return clientID;
	}

	private void setClientID() {
		getClientID();
	}
	/**
	 * this method creates a response for a client to receive
	 * @param connection takes in connection from a client
	 * @param request takes in a request to decide what to do
	 */
	public synchronized void newRequest(ServerConnection connection, Request request) {
		if (request.getRequest().equals("clientID")) {
			setClientID();
			connection.newResponse(new Response("clientID" , clientID));
		}
		else if (request.getRequest().equals("ready")) {
			lobby.waitingRoom(request.getClientID(), this);
			connection.newResponse(new Response("ready"));
		}
		else if (request.getRequest().equals("newGame")) {
			if (controller!=null) {
				System.out.println("spelet startas");
				Player player1 = controller.getPlayer1();
				//				System.out.println("Spelare1");
				Player player2 = controller.getPlayer2();
				//				System.out.println("Spelare2");

				Player player3 = controller.getPlayer3();
				//				System.out.println("Spelare3");

				Player player4 = controller.getPlayer4();
				//				System.out.println("Spelare4");


				if (request.getClientID() == player1.getClientID()) 
					connection.newResponse(new Response("newGame", player1, player2.getPlayerCardSize(),
							player3.getPlayerCardSize(), player4.getPlayerCardSize(), request.getClientID()));
				else if (request.getClientID() == player2.getClientID())
					connection.newResponse(new Response("newGame", player2, player3.getPlayerCardSize(), 
							player4.getPlayerCardSize(), player1.getPlayerCardSize(), request.getClientID()));
				else if (request.getClientID() == player3.getClientID())
					connection.newResponse(new Response("newGame", player3, player4.getPlayerCardSize(),
							player1.getPlayerCardSize(), player2.getPlayerCardSize(), request.getClientID()));
				else if (request.getClientID() == player4.getClientID())
					connection.newResponse(new Response("newGame", player4, player1.getPlayerCardSize(),
							player2.getPlayerCardSize(), player3.getPlayerCardSize(), request.getClientID())); 
			}
			else {
				connection.newResponse(new Response("ready", request.getClientID()));
			}
		}
		else if(request.getRequest().equals("pass")) {
			if (controller.checkIfPassIsPossible()) {
				connection.newResponse(new Response("pass"));
			}
			else {
				connection.newResponse(new Response("passainte"));
			}
		}

		else if(request.getRequest().equals("database")){
			connection.newResponse(new Response("database", controller.getDataBas()));
		}

		else if (request.getRequest().equals("playCard")) {
			if (controller.checkIfCardIsPlayable(request.getCardName(), request.getClientID())){
				connection.newResponse(new Response("playCard", request.getCardName(),
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

	public void createGame(int client1,int client2,int client3, int client4) {
		setGame(gameID+1);
		new Controller(this, gameID, client1, client2, client3, client4);
	}

	private void setGame(int gameID) {
		this.gameID = gameID;		
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}