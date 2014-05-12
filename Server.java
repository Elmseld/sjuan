package sjuan;

/**
 * This class handles the server communication with clients
 * @author Tobbe
 *
 */
public class Server {
	private Player player1 = null, player2 = null, player3 = null, player4 = null;
	private int clientID;
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

			new ConnectToServer(this,port);
		}
		catch (Exception e) { // IOException, ClassNotFoundException
			System.out.println(e);
			e.getStackTrace();
		}
	}

	public void newClient(ServerConnection connection, Player player) {
		if (player1==null) {
			player1 = player;
			connection1 = connection;
		}
		else if (player2==null) {
			player2 = player;
			connection2 = connection;

		}
		else if (player3==null) {
			player3 = player;
			connection3 = connection;

		}
		else if (player4==null) {
			player4 = player;
			connection4 = connection;

		}
		else {
			System.out.println("Ny server bör skapas");
		}
	}
	/**
	 * this method returns a clientID
	 * @return clientID returns a int of a clientID
	 */
	public int getClientID() {
		return clientID;
	}
	public boolean playersExist() {
		if (player1!=null && player2!=null && player3!=null && player4!=null){
			return true;
		}
		return false;
	}

	/**
	 * this method creates a response for a client to receive
	 * @param connection takes in connection from a client
	 * @param request takes in a request to decide what to do
	 */
	public synchronized void newRequest(ServerConnection connection, Request request) {

		if (request.getRequest().equals("new")) {
			if (playersExist()) {
				controller.Deal(player1, player2, player3, player4);
//				controller.whoHaveHeartSeven();
					connection1.newResponse(new Response("new", player1, player2.getPlayerCardSize(),
							player3.getPlayerCardSize(), player4.getPlayerCardSize()));
//				else if (clientID==player2.getClientID())
					connection2.newResponse(new Response("new", player2, player3.getPlayerCardSize(), 
							player4.getPlayerCardSize(), player1.getPlayerCardSize()));
//				else if (clientID==player3.getClientID())
					connection3.newResponse(new Response("new", player3, player4.getPlayerCardSize(),
							player1.getPlayerCardSize(), player2.getPlayerCardSize()));
//				else if (clientID==player4.getClientID())
					connection4.newResponse(new Response("new", player4, player1.getPlayerCardSize(),
							player2.getPlayerCardSize(), player3.getPlayerCardSize()));
//				else 
//					System.out.println("clientID stämmer inte");
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
							controller.getPlayer(1), 
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