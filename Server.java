package sjuan;
/**
 * This class handles the server communication with clients
 * @author Tobbe
 *
 */
public class Server {
	private Player player1, player2, player3, player4;
	private int clientID;

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
			this.player1 = player1;
			this.player2 = player2;
			this.player3 = player3;
			this.player4 = player4;
			control.Deal();

			new ConnectToServer(this,port);
		}
		catch (Exception e) { // IOException, ClassNotFoundException
			System.out.println(e);
		}
	}
	// om servern behöver lagra referens till klienterna

	public void newClient(ServerConnection connection , int counter) {
		clientID = counter;
		// om servern behöver lagra referens till klienterna
	}
	/**
	 * this method creates a response that a client recieve
	 * @param connection takes in connection from a client
	 * @param request takes in a string to decide what to do
	 */
	public synchronized void newRequest(ServerConnection connection, Request request) {
		if (request.getRequest().equals("new")) {
			if (clientID==1)
				connection.newResponse(new Response(player1.getPlayerCardList(),
						player2.getPlayerCardSize(),
						player3.getPlayerCardSize(),
						player4.getPlayerCardSize(), "new"));
			else if (clientID==2)
				connection.newResponse(new Response(player2.getPlayerCardList(),
						player3.getPlayerCardSize(),
						player4.getPlayerCardSize(),
						player1.getPlayerCardSize(), "new"));
			else if (clientID==3)
				connection.newResponse(new Response(player3.getPlayerCardList(),
						player4.getPlayerCardSize(),
						player1.getPlayerCardSize(),
						player2.getPlayerCardSize(), "new"));
			else if (clientID==4)
				connection.newResponse(new Response(player4.getPlayerCardList(),
						player1.getPlayerCardSize(),
						player2.getPlayerCardSize(),
						player3.getPlayerCardSize(), "new"));
			else 
				System.out.println("clientID stämmer inte");
		}
		else if(request.getRequest().equals("pass")) {
			connection.newResponse(new Response("pass"));
		}	
	}
}
