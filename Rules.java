package sjuan;

import java.util.ArrayList;

public class Rules  {
	private Player player;
	private Controller controller;

	public Rules(Controller controller) {
		this.controller = controller;
	}

	public boolean correct(Card card, Player player) {
		this.player = player;
		//test för kommunikation
		//		if (card.getType()==0&&card.getValue()==6) {
		moveCardToBoard(card);
		return true;
		//		}
		//		else if ((card!=gameBoardCards.iterator().next())&&(card.getValue()<6)&&(card.getValue()>6)) {
		//			return true;
		//		}

		//		return false;
	}
	public void moveCardToBoard(Card card) {
		int i = 0;
		ArrayList<Card> playerCards = player.getPlayerCards();

		for (Card a : playerCards) {
			if (a.toString().equals(card.toString())) {
				playerCards.remove(i);
				controller.setGameBoardCards(card);
				break;
			}
			i++;
		}
				player.setPlayerCards(playerCards);
	}
}

