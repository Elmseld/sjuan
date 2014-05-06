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

	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player player3 = new Player();
	private Player player4 = new Player();
	private Deck deck = new Deck();
	private ArrayList <Card> gameBoardCards = null;
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
		server = new Server(7766, player1, player2, player3, player4, this);

	}
	/**
	 * This method deals the deck to all players
	 */

	public void Deal() { 
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
	public void setGameBoardCards(Card card) {
		gameBoardCards.add(card);

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
	public boolean checkIfCardIsPlayable(Card card, int clientID){
		if (clientID==1) {
			return rules.correct(card, player1, gameBoardCards);
		}
		else if (clientID==2) {
			return rules.correct(card, player2, gameBoardCards);
		}
		else if (clientID==3) {
			return rules.correct(card, player3, gameBoardCards);
		}
		else if (clientID==4) {
			return rules.correct(card, player4, gameBoardCards);
		}
		return false;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str; 
	}
}