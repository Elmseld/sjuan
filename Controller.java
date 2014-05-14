package sjuan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class control the logic of the game "Sjuan"
 * @author Tobbe
 *
 */
public class Controller {
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private Deck deck = new Deck();
	private ArrayList <Card> gameBoardCards = new ArrayList<Card>();
	private int gameID;
	private Rules rules = new Rules(this);
	private DataBase databas = new DataBase();
	private Server server;

	/**
	 * Constructs a controller 
	 * @param player1 takes in a player
	 * @param player2 takes in a player
	 * @param player3 takes in a player
	 * @param player4 takes in a player
	 * @param deck takes in a deck
	 */

	public Controller(Server server, int gameID, 
			int client1, int  client2, int client3, int client4) {
		this.gameID = gameID;
		player1 = new Player(client1);
		player2 = new Player(client2);
		player3 = new Player(client3);
		player4 = new Player(client4);
		deal();
		this.server.setController(this);

	}
	/**
	 * This method deals the deck to all players
	 */

	public void deal() { 
		while (deck.getAllCards()!=0) {
			player1.setPlayerCards(deck.dealCard());
			if (deck.getAllCards()>0)
				player2.setPlayerCards(deck.dealCard());
			if (deck.getAllCards()>0)
				player3.setPlayerCards(deck.dealCard());
			if (deck.getAllCards()>0)
				player4.setPlayerCards(deck.dealCard());

		}
	}

	/**
	 * this method add a card to the gameboard
	 * @param card takes in a card from a player to be set to the gameboard
	 */
	public void moveGameBoardCards(Card card) {
		gameBoardCards.add(card);
	}

	/**
	 * this method sets the gameboards cards that are played
	 * @param gameBoardCards takes in the cards that are out at game board
	 */
	public void setGameBoardCardsList(ArrayList<Card> gameBoardCards) {
		this.gameBoardCards = gameBoardCards;
	}
	/**
	 * this method add a card to the gameboard
	 * gameboardcards is for controller to know what card that are played to game board
	 * @return gameBoardCards returns an ArrayList of the cards at gameboard
	 */
	public ArrayList <Card> getGameBoardCards () {
		return gameBoardCards;
	}

	/**
	 * this method returns a boolean if a card is playable or not
	 * @param card takes in a card
	 * @return boolean returns a boolean if the card is playable or not
	 */

	public boolean checkIfCardIsPlayable(String cardName, int clientID){
		if (clientID==player1.getClientID()) {
			return rules.correct(player1.getCardByName(cardName), player1);
		}
		else if (clientID==player2.getClientID()) {
			return rules.correct(player2.getCardByName(cardName), player2);
		}
		else if (clientID==player3.getClientID()) {
			return rules.correct(player3.getCardByName(cardName), player3);
		}
		else if (clientID==player4.getClientID()) {
			return rules.correct(player4.getCardByName(cardName), player4);
		}
		return false;
	}

	/**
	 * this method returns a player by taking in a clientID	
	 * @param clientID takes in a clientID
	 * @return player returns a player
	 */
	public Player getPlayer(int clientID) {
		if (clientID==player1.getClientID())
			return player1;
		else if (clientID==player2.getClientID())
			return player2;
		else if (clientID==player3.getClientID())
			return player3;
		else if (clientID==player4.getClientID())
			return player4;
		return null;
	}

	/**
	 * this method checks if there are cards are able to play
	 * @return true if there are no cards to play, false if there is cards to play
	 */
	public boolean checkIfPassIsPossible() {
		return true;

	}

	/**
	 * this method returns a String from the database containing its context
	 * @return str returns a string
	 */
	public String getDataBas (){
		String str = "";
		try {
			databas.connect();
			ResultSet result = databas.statement.executeQuery("SELECT * FROM ab4607.statistics");
			str = databas.showResultSet(result);

			databas.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str; 
	}

	/**
	 * this method finds out the player that have the starting card (h7) 
	 * and sets the client id for the player
	 */
	public Player whoHaveHeartSeven () {
		for (Card card : player1.getPlayerCards())
			if (card.toString().equals("h7")) {
				return player1;
			}
		for (Card card : player2.getPlayerCards())
			if (card.toString().equals("h7")) {
				Player temp = player2;
				player2 = player3;
				player3 = player4;
				player4 = player1;
				player1 = temp;
				return player1;
			}
		for (Card card : player3.getPlayerCards())
			if (card.toString().equals("h7")) {
				Player temp = player3;
				player3 = player1;
				player1 = temp;
				Player temp2 = player4;
				player4 = player2;
				player2 = temp2;
				return player1;

			}
		for (Card card : player4.getPlayerCards())
			if (card.toString().equals("h7")) {
				Player temp = player4;
				player4 = player3;
				player3 = player2;
				player2 = player1;
				player1 = temp;
				return player1;

			}

		return null;
	}

	public void setPlayerOrder(Player player) {
		if (player1==null) {
			player1 = player;
		}

		else if (player2==null) {
			player2 = player;
		}

		else if (player3==null) {
			player3 = player;

		}
		else if (player4==null) {
			player4 = player;


		}
	}
	public boolean playersExist() {
		if (player1!=null && player2!=null && player3!=null && player4!=null){
			deal();
			return true;
		}
		return false;

	}
	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public Player getPlayer3() {
		return player3;
	}
	public Player getPlayer4() {
		return player4;
	}
	public Controller getController(int gameID) {
		return this;
	}
	public int getGameID() {
		return gameID;
	}
	public void giveClientOnePlayer(int clientID) {
		if (player1.getClientID()==0) {
			player1.setClientID(clientID);
		}
		else if (player2.getClientID()==0) {
			player2.setClientID(clientID);
		}
		else if (player3.getClientID()==0) {
			player3.setClientID(clientID);
		}
		else if (player4.getClientID()==0) {
			player4.setClientID(clientID);
		}
	}
}
