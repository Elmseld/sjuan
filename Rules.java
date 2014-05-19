package sjuan;

import java.util.ArrayList;

/**
 * this class handles the rules of the game sjuan
 *
 */
public class Rules {
	private Player player;
	private Controller controller;

	/**
	 * Constructs a rules object
	 * @param controller
	 */
	public Rules(Controller controller) {
		this.controller = controller;
	}

	/**
	 * this method checks if a card is playable
	 * @param card takes in a card-Object
	 * @param player takes in a player
	 * @param gameID takes in a gameID
	 * @return boolean if the card is playable or not
	 */
	public boolean correct(Card card, Player player, int gameID) {
		this.player = player;

		// if hjärter7
		if (card.getType() == 0) {
			if (card.getValue() == 6) {
				moveCardToBoard(card, gameID);
				return true;
			} else {
				ArrayList<Card> res = controller.getGameBoardCards(gameID);
				for (int i = 0; i < res.size(); i++) {
					Card right = res.get(i);
					if (right.getType() == 0 && right.getValue() == (card.getValue() + 1) || (right.getType() == 0 && right.getValue() == (card.getValue() -1))) {
						moveCardToBoard(card, gameID);
						return true;
					}
				}
			}
			return false;
		}
		// // if någon annan sjua och hjärter7 utlagd
		else if ((card.getValue() == 6)) {
			ArrayList<Card> res = controller.getGameBoardCards(gameID);
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 0) {
					moveCardToBoard(card, gameID);
					return true;
				}
			}
		}
		//
		else if (card.getType() == 1) {
			ArrayList<Card> res = controller.getGameBoardCards(gameID);
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 1 && right.getValue() == (card.getValue() + 1) || (right.getType() == 1 && right.getValue() == (card.getValue() -1))) {
					moveCardToBoard(card, gameID);
					return true;
				}
			}

		}

		else if (card.getType() == 2) {
			ArrayList<Card> res = controller.getGameBoardCards(gameID);
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 2 && right.getValue() == (card.getValue() + 1) || (right.getType() == 2 && right.getValue() == (card.getValue() -1))) {
					moveCardToBoard(card, gameID);
					return true;
				}
			}
		}

		else if (card.getType() == 3) {
			ArrayList<Card> res = controller.getGameBoardCards(gameID);
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 3 && right.getValue() == (card.getValue() + 1) || (right.getType() == 3 && right.getValue() == (card.getValue() -1))) {
					moveCardToBoard(card, gameID);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * this method moves a card from a players hand to the gameBoard
	 * @param card takes in a card
	 * @param gameID takes in a gameID
	 */
	public void moveCardToBoard(Card card, int gameID) {
		int i = 0;
		ArrayList<Card> playerCards = player.getPlayerCards();

		for (Card a : playerCards) {
			if (a.toString().equals(card.toString())) {
				playerCards.remove(i);
				controller.moveGameBoardCards(card, gameID);
				player.setPlayerCards(playerCards);
				break;
			}
			i++;
		}
	}
	
	/**
	 * this method checks if a player can pass or not
	 * @param card takes in a card
	 * @param player takes in a player
	 * @param gameID takes in a gameID
	 * @return returns a boolean if a player have any possible moves
	 */
	public boolean checkPass(Card card, Player player, int gameID) {
		this.player = player;

		// if hjärter7
		if (card.getType() == 0) {
			if (card.getValue() == 6) {
				moveCardToBoard(card, gameID);
				return true;
			} else {
				ArrayList<Card> res = controller.getGameBoardCards(gameID);
				for (int i = 0; i < res.size(); i++) {
					Card right = res.get(i);
					if (right.getType() == 0 && right.getValue() == (card.getValue() + 1) || (right.getType() == 0 && right.getValue() == (card.getValue() -1))) {
						return true;
					}
				}
			}
			return false;
		}
		// // if någon annan sjua och hjärter7 utlagd
		else if ((card.getValue() == 6)) {
			ArrayList<Card> res = controller.getGameBoardCards(gameID);
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 0) {
					return true;
				}
			}
		}
		//
		else if (card.getType() == 1) {
			ArrayList<Card> res = controller.getGameBoardCards(gameID);
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 1 && right.getValue() == (card.getValue() + 1) || (right.getType() == 1 && right.getValue() == (card.getValue() -1))) {
					return true;
				}
			}

		}

		else if (card.getType() == 2) {
			ArrayList<Card> res = controller.getGameBoardCards(gameID);
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 2 && right.getValue() == (card.getValue() + 1) || (right.getType() == 2 && right.getValue() == (card.getValue() -1))) {
					return true;
				}
			}
		}

		else if (card.getType() == 3) {
			ArrayList<Card> res = controller.getGameBoardCards(gameID);
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 3 && right.getValue() == (card.getValue() + 1) || (right.getType() == 3 && right.getValue() == (card.getValue() -1))) {
					return true;
				}
			}
		}
		return false;
	}
}
