package sjuan;
/**
 * This class handles the server communication with clients
 * @author Tobbe
 *
 */
public class Server {
	private Player player1, player2, player3, player4;


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
			control.Deal();
			this.player1 = player1;
			this.player2 = player2;
			this.player3 = player3;
			this.player4 = player4;
			System.out.println(player1.getPlayerCardSize() + " "  +player2.getPlayerCardSize() + " " + player3.getPlayerCardSize() + " " + player4.getPlayerCardSize());
			
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
	 * this method creates a response ???? add more later here of how this works
	 * @param connection
	 * @param request
	 */
	public synchronized void newRequest(ServerConnection connection, Request request) {
		//		if(request.getRequest().equals("Start")) {
		connection.newResponse(new Response(player1.getPlayerCardList(),
				player2.getPlayerCardSize(),
				player3.getPlayerCardSize(),
				player4.getPlayerCardSize()));
	}
}
