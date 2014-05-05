package sjuan;

import java.util.ArrayList;

public class Rules {
	private Player player;
	private Controller controller;


	public Rules(Controller controller) {
		this.controller = controller;
	}

	public boolean correct(Card card, Player player, ArrayList<Card> gameBoardCards) {
		this.player = player;

		// if hjärter7
		if(card.getType()== 0) {
			if(card.getValue() == 6) {
				moveCardToBoard(card);
				return true;
			}
			
		}
		// if någon annan sjua och hjärter7 utlagd
		if(card.getValue() == 6 && gameBoardCards.iterator().equals(card.getType()==0 && card.getValue()==6)) {
			return true;
		}

		return false;
	}
	public void moveCardToBoard(Card card) {
		Card temp = card;
		player.getPlayerCards().remove(card);
		controller.setGameBoardCards(temp);
		

	}
}

