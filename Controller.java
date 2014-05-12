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
		server = new Server(7766, player1 = new Player(), player2 = new Player(), 
				player3 = new Player(), player4 = new Player(), this);
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

	/**
	 * this method returns a player by taking in a clientID	
	 * @param clientID takes in a clientID
	 * @return player returns a player
	 */
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
			ResultSet result = databas.statement.executeQuery("SELECT AnvändarNamn FROM ab4607.statistics");
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
	public void whoHaveHeartSeven () {
		for (Card card : player1.getPlayerCards())
			if (card.toString().equals("h7")) {
				player1.setClientID(1);	
				player2.setClientID(2);
				player3.setClientID(3);
				player4.setClientID(4);
				break;
			}
		for (Card card : player2.getPlayerCards())
			if (card.toString().equals("h7")) {
				player2.setClientID(1);	
				player3.setClientID(2);
				player4.setClientID(3);
				player1.setClientID(4);	
				break;
			}
		for (Card card : player3.getPlayerCards())
			if (card.toString().equals("h7")) {
				player3.setClientID(1);	
				player4.setClientID(2);
				player1.setClientID(3);
				player2.setClientID(4);	
				break;
			}
		for (Card card : player4.getPlayerCards())
			if (card.toString().equals("h7")) {
				player4.setClientID(1);	
				player1.setClientID(2);
				player2.setClientID(3);
				player3.setClientID(4);	
				break;
			}

	}
}