package sjuan;

import java.util.ArrayList;

public class Rules {
	private Player player;
	private Controller controller;

	public Rules(Controller controller) {
		this.controller = controller;
	}

	public boolean correct(Card card, Player player) {
		this.player = player;

		// if hjärter7
		if (card.getType() == 0) {
			if (card.getValue() == 6) {
				moveCardToBoard(card);
				return true;
			} else {
				ArrayList<Card> res = controller.getGameBoardCards();
				for (int i = 0; i < res.size(); i++) {
					Card right = res.get(i);
					if (right.getType() == 0 && right.getValue() == (card.getValue() + 1) || (right.getType() == 0 && right.getValue() == (card.getValue() -1))) {
						moveCardToBoard(card);
						return true;
					}
				}
			}
			return false;
		}
		// // if någon annan sjua och hjärter7 utlagd
		else if ((card.getValue() == 6)) {
			ArrayList<Card> res = controller.getGameBoardCards();
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 0) {
					moveCardToBoard(card);
					return true;
				}
			}
		}
		//
		else if (card.getType() == 1) {
			ArrayList<Card> res = controller.getGameBoardCards();
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 1 && right.getValue() == (card.getValue() + 1) || (right.getType() == 1 && right.getValue() == (card.getValue() -1))) {
					moveCardToBoard(card);
					return true;
				}
			}

		}

		else if (card.getType() == 2) {
			ArrayList<Card> res = controller.getGameBoardCards();
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 2 && right.getValue() == (card.getValue() + 1) || (right.getType() == 2 && right.getValue() == (card.getValue() -1))) {
					moveCardToBoard(card);
					return true;
				}
			}
		}

		else if (card.getType() == 3) {
			ArrayList<Card> res = controller.getGameBoardCards();
			for (int i = 0; i < res.size(); i++) {
				Card right = res.get(i);
				if (right.getType() == 3 && right.getValue() == (card.getValue() + 1) || (right.getType() == 3 && right.getValue() == (card.getValue() -1))) {
					moveCardToBoard(card);
					return true;
				}
			}
		}

		return false;

	}

	public void moveCardToBoard(Card card) {
		int i = 0;
		ArrayList<Card> playerCards = player.getPlayerCards();

		for (Card a : playerCards) {
			if (a.toString().equals(card.toString())) {
				playerCards.remove(i);
				controller.moveGameBoardCards(card);
				player.setPlayerCards(playerCards);
				break;
			}
			i++;
		}
	}
}
