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
	//	private boolean humanClient = false;
	//	private Controller controller;
	private Lobby lobby = new Lobby();
	//	private HashMap <Integer, Boolean> humanList = new HashMap <Integer, Boolean>();
	private HashMap <Integer, ServerConnection> connectionsList = new HashMap <Integer, ServerConnection>() ;
	private ArrayList <Integer> readyClientsConnections = new ArrayList<Integer>();
	private DataBase databas = new DataBase();
	private HashMap <Integer, Controller> controllerList = new HashMap <Integer, Controller>();

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
		connectionsList.put(counter, connection);
	}

	/**
	 * this method creates a response for a client to receive
	 * @param connection takes in connection from a client
	 * @param request takes in a request to decide what to do
	 */
	public synchronized void newRequest(ServerConnection connection, Request request) {

		if (request.getRequest().equals("clientID")) {
			connection.newResponse(new Response("clientID" , clientID, request.isHumanPlayer()));
		}
		else if (request.getRequest().equals("newAIPlayer")) {
			readyClientsConnections.add(request.getClientID());
			if (readyClientsConnections.size()<4) {
				lobby.waitingRoom(request.getClientID(), this);
				connectionsList.get(request.getClientID()).newResponse(new Response
						("createAI", request.getClientID(), false));
			}
			else {
				lobby.waitingRoom(request.getClientID(), this);
				connectionsList.get(readyClientsConnections.get(0)).newResponse(new Response
						("newGame", readyClientsConnections.get(0), 
								lobby.getGameID(request.getClientID())));
			}
		}
		else if(request.getRequest().equals("Login")){
			connection.newResponse(new Response("Login", logInDb(request.getUserName(), request.getPassWord())));
		}
		else if (request.getRequest().equals("setPlayerHumanOrAI")) {
			System.out.println(request.getClientID() + " "+ request.isHumanPlayer());

		}
		else if (request.getRequest().equals("newGame")) {
			//			if (readyClientsConnections.size()<4) {
			//				connection.newResponse(new Response("createAI"));
			//			}
			//			else {
			//			connection.newResponse(new Response("newGame", request.getClientID(), request.isHumanPlayer()));
			//			System.out.println(request.getClientID() + " är tillagd i redolista");
			//			if (controller!=null) {
			//				for (int clientID : readyClientsConnections) {
			//					lobby.waitingRoom(clientID, this);
			//				}
			//			if (controller!=null) {
			//			else {
			System.out.println("spelet startas");
			Controller controller = controllerList.get(request.getGameID());
			Player player1 = controller.getPlayer1(controller.getGameID());
			Player player2 = controller.getPlayer2(controller.getGameID());
			Player player3 = controller.getPlayer3(controller.getGameID());
			Player player4 = controller.getPlayer4(controller.getGameID());
			controller.whoHaveHeartSeven();

			connectionsList.get(readyClientsConnections.get(0)).newResponse(new Response("newGame", 
					player1, player2.getPlayerCardSize(),player3.getPlayerCardSize(), 
					player4.getPlayerCardSize(), readyClientsConnections.get(0),
					controller.getGameID(), player1.isHasHeart7()));

			connectionsList.get(readyClientsConnections.get(1)).newResponse(new Response("newGame", 
					player2, player3.getPlayerCardSize(), player4.getPlayerCardSize(),
					player1.getPlayerCardSize(), readyClientsConnections.get(1), 
					controller.getGameID(), player2.isHasHeart7()));

			connectionsList.get(readyClientsConnections.get(2)).newResponse(new Response("newGame", 
					player3, player4.getPlayerCardSize(), player1.getPlayerCardSize(), 
					player2.getPlayerCardSize(), readyClientsConnections.get(2),
					controller.getGameID(), player3.isHasHeart7()));

			connectionsList.get(readyClientsConnections.get(3)).newResponse(new Response("newGame", 
					player4, player1.getPlayerCardSize(), player2.getPlayerCardSize(), 
					player3.getPlayerCardSize(), readyClientsConnections.get(3), 
					controller.getGameID(), player4.isHasHeart7()));

			readyClientsConnections.clear();
		}

		//		}
		else if(request.getRequest().equals("pass")) {
			if (controllerList.get(request.getGameID()).checkIfPassIsPossible(request.getClientID(), request.getGameID())) { 
				connectionsList.get(request.getClientID()).newResponse(new Response("pass", request.getClientID(), request.getGameID(), 
						null, controllerList.get(request.getGameID()).getPlayerByClientID(request.getGameID(), request.getClientID())));
				System.out.println(request.getClientID() + ": har passat");

			}
			else {
				connection.newResponse(new Response("passainte", request.getClientID()));
			}
		}


		//		else if(request.getRequest().equals("database")){
		//			connection.newResponse(new Response("database", controller.getDataBas()));
		//		}


		else if (request.getRequest().equals("playCard")) {
			if (controllerList.get(request.getGameID()).checkIfCardIsPlayable(request.getCardName(), request.getClientID(),
					request.getGameID())) { 
				System.out.println(request.getClientID() + ": har spelat: " + request.getCardName());

				connectionsList.get(request.getClientID()).newResponse(new Response("updatePlayerWithAI", request.getCardName(),
						controllerList.get(request.getGameID()).getPlayerByClientID(request.getGameID(), request.getClientID()), 
						controllerList.get(request.getGameID()).getGameBoardCards(request.getGameID()), request.getClientID()));
			}
			//			}
			else {
				connection.newResponse(new Response("dontPlayCard", request.getClientID()));
			}
		}
		else if (request.getRequest().equals("giveACard")) {
			Controller controller = controllerList.get(request.getGameID());
			int clientID = controller.setNextPlayersTurn(request.getClientID(), 
					request.getGameID());
			connectionsList.get(clientID).newResponse(new Response("updateAndGiveCard", 
					controller.setNextPlayersTurn(request.getClientID(), request.getGameID()),
					request.getGameID(), request.getPassCounter(), controller.getPlayerByClientID(request.getGameID(), clientID)));
			System.out.println(request.getClientID() + " säger att nästa spelare ska ge ett kort");


		}
		else if (request.getRequest().equals("giveACardToAPlayer")) {
			controllerList.get(request.getGameID()).giveCard(request.getCardName(), 
					request.getClientID(), request.getGameID());
			connectionsList.get(controllerList.get(request.getGameID()).setNextPlayersTurn(request.getClientID(), 
					request.getGameID())).newResponse(new Response("giveACard",request.getClientID(), 
							request.getGameID(), request.getPassCounter()+1, null));	
			System.out.println(request.getClientID() + " ger ett kort");

		}

		else if (request.getRequest().equals("recieveCards")) {
			Controller controller = controllerList.get(request.getGameID());
			System.out.println(request.getClientID() + " tar emot kort");
			controller.addRecievedCardsToPassedPlayer(request.getClientID(), request.getGameID());
			connection.newResponse(new Response("updateAll", controller.getPlayerByClientID(request.getGameID(), request.getClientID()),
					controller.getOpponent1HandSize(request.getGameID(), request.getClientID()),
					controller.getOpponent2HandSize(request.getGameID(), request.getClientID()), 
					controller.getOpponent3HandSize(request.getGameID(), request.getClientID()), 
					controller.getGameBoardCards(request.getGameID()), request.getClientID()));
			connectionsList.get(controller.setNextPlayersTurn(request.getClientID(), 
					request.getGameID())).newResponse(new Response("wakePlayer", 
							request.getClientID(), request.getGameID()));	
		}
		//		else if(request.getRequest().equals("database")) {
		//			connection.newResponse(new Response("database", controller.getDataBas()));
		//		}
		else if (request.getRequest().equals("nextPlayer")) {
			connectionsList.get(controllerList.get(request.getGameID()).setNextPlayersTurn(request.getClientID(), 
					request.getGameID())).newResponse(new Response("wakePlayer",
							controllerList.get(request.getGameID()).setNextPlayersTurn
							(request.getClientID(), request.getGameID())));
			System.out.println(request.getClientID() + ": väcker nästa " );


			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (request.getRequest().equals("getGameConditions")) {
			Controller controller = controllerList.get(request.getGameID());
			connection.newResponse(new Response("updateAndPlayCard", request.getClientID(), request.getGameID(), 
					controller.getGameBoardCards(request.getGameID()), 
					controller.getPlayerByClientID(request.getGameID(), request.getClientID())));
		}
		else if (request.getRequest().equals("getHumanGameConditions")) {
			Controller controller = controllerList.get(request.getGameID());
			connection.newResponse(new Response("update", request.getClientID(), request.getGameID(), 
					controller.getGameBoardCards(request.getGameID()), 
					controller.getPlayerByClientID(request.getGameID(), request.getClientID())));
		}
		else if (request.getRequest().equals("getAllGameConditions")) {
			Controller controller = controllerList.get(request.getGameID());
			connection.newResponse(new Response("updateAll", controller.getPlayerByClientID(request.getGameID(), request.getClientID()),
					controller.getOpponent1HandSize(request.getGameID(), request.getClientID()),
					controller.getOpponent2HandSize(request.getGameID(), request.getClientID()), 
					controller.getOpponent3HandSize(request.getGameID(), request.getClientID()), 
					controller.getGameBoardCards(request.getGameID()), request.getClientID()));
			System.out.println(request.getClientID() + ": uppdaterar spelet " );

		}
		else {
			connection.newResponse(new Response("clientsMissing", request.getClientID()));
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
		controllerList.put(gameID, new Controller(this, gameID, client1, client2, client3, client4));
	}

	/**
	 * this method returns a boolean
	 * @return true if the name and password is correct 
	 */

	public boolean logInDb(String userName, String passWord){
		return databas.logInDb(userName, passWord);
	}


	public int[] getAllClientIDInAGame(int gameID) {
		Player player1 = controllerList.get(gameID).getPlayer1(gameID);
		Player player2 = controllerList.get(gameID).getPlayer2(gameID);
		Player player3 = controllerList.get(gameID).getPlayer3(gameID);
		Player player4 = controllerList.get(gameID).getPlayer4(gameID);

		int[] clientIDs = {player1.getClientID(), player2.getClientID(),
				player3.getClientID(), player4.getClientID()};

		return clientIDs;
	}
}
