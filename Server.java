package sjuan;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class handles the server communication with clients
 * @author Tobbe
 *
 */
public class Server {
	private int clientID;
	private Controller controller;
	private Lobby lobby = new Lobby();
	private HashMap <Integer, ServerConnection> connections = new HashMap <Integer, ServerConnection>() ;
	private ArrayList <Integer> readyClientsConnections = new ArrayList<Integer>();


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
		connections.put(clientID, connection);
		controller = null;
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
		if (request.getRequest().equals("clientID")) {
			connection.newResponse(new Response("clientID" , clientID));
		}
		//		else if (request.getRequest().equals("ready")) {
		//			lobby.waitingRoom(request.getClientID(), this);
		//			connection.newResponse(new Response(request.getRequest(), request.getClientID()));
		//		}
		else if (request.getRequest().equals("newGame")) {
			lobby.waitingRoom(request.getClientID(), this);
			connection.newResponse(new Response("newGame", request.getClientID()));
			readyClientsConnections.add(request.getClientID());
			if (controller!=null) {
				System.out.println("spelet startas");
				Player player1 = controller.getPlayer1(controller.getGameID());
				Player player2 = controller.getPlayer2(controller.getGameID());
				Player player3 = controller.getPlayer3(controller.getGameID());
				Player player4 = controller.getPlayer4(controller.getGameID());
				controller.whoHaveHeartSeven();

				//				if (request.getClientID() == player1.getClientID())

				connections.get(readyClientsConnections.get(0)).newResponse(new Response("newGame", player1, player2.getPlayerCardSize(),
						player3.getPlayerCardSize(), player4.getPlayerCardSize(), request.getClientID(), 
						controller.getGameID(), player1.isHasHeart7()));
				//				else if (request.getClientID() == player2.getClientID())
				connections.get(readyClientsConnections.get(1)).newResponse(new Response("newGame", player2, player3.getPlayerCardSize(), 
						player4.getPlayerCardSize(), player1.getPlayerCardSize(), 
						request.getClientID(), controller.getGameID(), player2.isHasHeart7()));
				//				else if (request.getClientID() == player3.getClientID())
				connections.get(readyClientsConnections.get(2)).newResponse(new Response("newGame", player3, player4.getPlayerCardSize(),
						player1.getPlayerCardSize(), player2.getPlayerCardSize(), 
						request.getClientID(), controller.getGameID(), player3.isHasHeart7()));
				//				else if (request.getClientID() == player4.getClientID())
				connections.get(readyClientsConnections.get(3)).newResponse(new Response("newGame", player4, player1.getPlayerCardSize(),
						player2.getPlayerCardSize(), player3.getPlayerCardSize(), 
						request.getClientID(), controller.getGameID(), player4.isHasHeart7())); 
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
			if (controller.checkIfCardIsPlayable(request.getCardName(), request.getClientID(),
					request.getGameID())) { 
				connection.newResponse(new Response("playCard", request.getCardName(),
						controller.getPlayerByClientID(request.getClientID(), request.getGameID()), 
						controller.getGameBoardCards(request.getGameID())));
			}
			else {
				connection.newResponse(new Response("dontPlayCard"));
			}
		}
		else if (request.getRequest().equals("nextPlayer")) {
			connections.get(controller.setNextPlayersTurn(request.getClientID(), 
					request.getGameID())).newResponse(new Response("wakePlayer"));
		}
		else if (request.getRequest().equals("getGameConditions")) {
			connection.newResponse(new Response("updateGUI", controller.getOpponent1HandSize(request.getGameID(), request.getClientID()),
					controller.getOpponent2HandSize(request.getGameID(), request.getClientID()), 
					controller.getOpponent3HandSize(request.getGameID(), request.getClientID()), 
					controller.getGameBoardCards(request.getGameID())));
		}
		else {
			connection.newResponse(new Response("clientsMissing"));
		}
	}

	public void createGame(int client1, int client2, int client3, int client4, int gameID) {
		new Controller(this, gameID, client1, client2, client3, client4);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}