package sjuan;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
	private HashMap <Integer, ServerConnection> connectionsList = new HashMap <Integer, ServerConnection>() ;
	private ArrayList <Integer> readyClientsConnections = new ArrayList<Integer>();
	private DataBase databas = new DataBase();


	/**
	 * constructs a server 
	 * @param port takes in a portNumber
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

	/**
	 * this method store a clients connection in connectionsList
	 * @param connection takes in a connection from a client
	 * @param counter takes in a counter that sets the clients ID
	 */
	public void newClient(ServerConnection connection, int counter) {
		clientID = counter;
		connectionsList.put(clientID, connection);
		controller = null;
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
		else if(request.getRequest().equals("Login")){
			connection.newResponse(new Response("Login", logInDb(request.getUserName(), request.getPassWord())));
		}
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

				connectionsList.get(readyClientsConnections.get(0)).newResponse(new Response("newGame", player1, player2.getPlayerCardSize(),
						player3.getPlayerCardSize(), player4.getPlayerCardSize(), request.getClientID(), 
						controller.getGameID(), player1.isHasHeart7()));

				connectionsList.get(readyClientsConnections.get(1)).newResponse(new Response("newGame", player2, player3.getPlayerCardSize(), 
						player4.getPlayerCardSize(), player1.getPlayerCardSize(), 
						request.getClientID(), controller.getGameID(), player2.isHasHeart7()));

				connectionsList.get(readyClientsConnections.get(2)).newResponse(new Response("newGame", player3, player4.getPlayerCardSize(),
						player1.getPlayerCardSize(), player2.getPlayerCardSize(), 
						request.getClientID(), controller.getGameID(), player3.isHasHeart7()));

				connectionsList.get(readyClientsConnections.get(3)).newResponse(new Response("newGame", player4, player1.getPlayerCardSize(),
						player2.getPlayerCardSize(), player3.getPlayerCardSize(), 
						request.getClientID(), controller.getGameID(), player4.isHasHeart7())); 
			}
		}
		else if(request.getRequest().equals("pass")) {
			if (controller.checkIfPassIsPossible(request.getClientID(), request.getGameID())) { 
				connection.newResponse(new Response("pass", request.getClientID(), request.getGameID()));
			}
			else {
				connection.newResponse(new Response("passainte"));
			}
		}


		//		else if(request.getRequest().equals("database")){
		//			connection.newResponse(new Response("database", controller.getDataBas()));
		//		}


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
		else if (request.getRequest().equals("giveACard")) {
			connectionsList.get(controller.setNextPlayersTurn(request.getClientID(), 
					request.getGameID())).newResponse(new Response("giveACard",request.getClientID(), 
							request.getGameID(), request.getPassCounter()));		
		}
		else if (request.getRequest().equals("giveACardToAPlayer")) {
			System.out.println(request.getClientID() + " ger ett kort");
			controller.giveCard(request.getCardName(), 
					request.getClientID(), request.getGameID());
			connectionsList.get(controller.setNextPlayersTurn(request.getClientID(), 
					request.getGameID())).newResponse(new Response("giveACard",request.getClientID(), 
							request.getGameID(), request.getPassCounter()+1));	
		}

		else if (request.getRequest().equals("recieveCards")) {
			System.out.println(request.getClientID() + " tar emot kort");
			controller.addRecievedCardsToPassedPlayer(request.getClientID(), request.getGameID());
			connection.newResponse(new Response("updateGUI2", controller.getPlayerByClientID(request.getClientID(), request.getGameID()),
					controller.getOpponent1HandSize(request.getGameID(), request.getClientID()),
					controller.getOpponent2HandSize(request.getGameID(), request.getClientID()), 
					controller.getOpponent3HandSize(request.getGameID(), request.getClientID()), 
					controller.getGameBoardCards(request.getGameID())));
			connectionsList.get(controller.setNextPlayersTurn(request.getClientID(), 
					request.getGameID())).newResponse(new Response("wakePlayer", 
							request.getClientID(), request.getGameID()));	
		}
		//		else if(request.getRequest().equals("database")) {
		//			connection.newResponse(new Response("database", controller.getDataBas()));
		//		}
		else if (request.getRequest().equals("nextPlayer")) {
			connectionsList.get(controller.setNextPlayersTurn(request.getClientID(), 
					request.getGameID())).newResponse(new Response("wakePlayer"));
		}
		else if (request.getRequest().equals("getGameConditions")) {
			connection.newResponse(new Response("updateGUI", controller.getOpponent1HandSize(request.getGameID(), request.getClientID()),
					controller.getOpponent2HandSize(request.getGameID(), request.getClientID()), 
					controller.getOpponent3HandSize(request.getGameID(), request.getClientID()), 
					controller.getGameBoardCards(request.getGameID()), null));
		}
		else if (request.getRequest().equals("getAllGameConditions")) {
			connection.newResponse(new Response("updateGUI2", controller.getPlayerByClientID(request.getClientID(), request.getGameID()),
					controller.getOpponent1HandSize(request.getGameID(), request.getClientID()),
					controller.getOpponent2HandSize(request.getGameID(), request.getClientID()), 
					controller.getOpponent3HandSize(request.getGameID(), request.getClientID()), 
					controller.getGameBoardCards(request.getGameID())));
		}
		else {
			connection.newResponse(new Response("clientsMissing"));
		}
	}

	/**
	 * this method creates a new controller
	 * @param client1 takes in a client
	 * @param client2 takes in a client
	 * @param client3 takes in a client
	 * @param client4 takes in a client
	 * @param gameID takes in a gameID
	 */
	public void createGame(int client1, int client2, int client3, int client4, int gameID) {
		new Controller(this, gameID, client1, client2, client3, client4);
	}

	/**
	 * this method sets a controller for the server
	 * @param controller takes in a controller
	 */
	public void setController(Controller controller) {
		this.controller = controller;

	}

	/**
	 * this method returns a boolean
	 * @return true if the name and password is correct 
	 */

	public boolean logInDb(String userName, String passWord){
		return databas.logInDb(userName, passWord);
	}

}
