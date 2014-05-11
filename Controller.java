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
	private int clientID;
	private Rules rules = new Rules(this);
	private Server server;
	private DataBase databas = new DataBase();


	/**
	 * Constructs a controller 
	 * @param player1 takes in a player
	 * @param player2 takes in a player
	 * @param player3 takes in a player
	 * @param player4 takes in a player
	 * @param deck takes in a deck
	 */
	public Controller() {
		server = new Server(7766, player1 = new Player(1), player2 = new Player(2), 
				player3 = new Player(3), player4 = new Player(4), this);

	}
	/**
	 * This method deals the deck to all players
	 */

	public void Deal(Player player1, Player player2, Player player3, Player player4) { 
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
//		player1.getPlayerCards().remove(card);
		gameBoardCards.add(card);
	}

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
		this.clientID = clientID;
		if (this.clientID==player1.getClientID()) {
			return rules.correct(player1.getCardByName(cardName), player1);
		}
		else if (this.clientID==player2.getClientID()) {
			return rules.correct(player2.getCardByName(cardName), player2);
		}
		else if (this.clientID==player3.getClientID()) {
			return rules.correct(player3.getCardByName(cardName), player3);
		}
		else if (this.clientID==player4.getClientID()) {
			return rules.correct(player4.getCardByName(cardName), player4);
		}
		return false;

	}

	public Player getPlayer(int clientID) {
		this.clientID = clientID;
		if (this.clientID==player1.getClientID())
			return player1;
		else if (this.clientID==player2.getClientID())
			return player2;
		else if (this.clientID==player3.getClientID())
			return player3;
		else if (this.clientID==player4.getClientID())
			return player4;
		return null;
	}

	public boolean checkIfPassIsPossible() {
		return true;

	}

	/**
	 * this method returns a String from the database containing its context
	 * @return
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
}