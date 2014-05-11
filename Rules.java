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


		//		// if hjärter7
		//		if(card.getType()== 0) {
		//			if(card.getValue() == 6) {
		moveCardToBoard(card);
		return true;
		//			}
		//			
		//		}
		//		// if någon annan sjua och hjärter7 utlagd
		//		else if(card.getValue() == 6 && controller.getGameBoardCards().equals("h7")) {
		//			moveCardToBoard(card);
		//			return true;
		//		}
		//		
		//		else if(card.getType() == 0) {
		//			if(card.getValue() == 5 || card.getValue() == 7 && controller.getGameBoardCards().equals("h7")) {
		//				moveCardToBoard(card);
		//				return true;
		//			}
		//			else if(card.getValue() <5 && controller.getGameBoardCards().equals(card.getValue()+1)) {
		//				moveCardToBoard(card);
		//				return true;	
		//			}
		//			else if(card.getValue() >7 && controller.getGameBoardCards().equals(card.getValue()-1)) {
		//				moveCardToBoard(card);
		//				return true;
		//			}
		//			return false;
		//		}
		//		
		//		else if(card.getType() == 1) {
		//			if(card.getValue() == 5 || card.getValue() == 7 && controller.getGameBoardCards().equals("s7")) {
		//				moveCardToBoard(card);
		//				return true;
		//			}
		//			else if(card.getValue() <5 && controller.getGameBoardCards().equals(card.getValue()+1)) {
		//				moveCardToBoard(card);
		//				return true;	
		//			}
		//			else if(card.getValue() >7 && controller.getGameBoardCards().equals(card.getValue()-1)) {
		//				moveCardToBoard(card);
		//				return true;
		//			}
		//			return false;
		//		}
		//		
		//		else if(card.getType() == 2) {
		//			if(card.getValue() == 5 || card.getValue() == 7 && controller.getGameBoardCards().equals("d7")) {
		//				moveCardToBoard(card);
		//				return true;
		//			}
		//			else if(card.getValue() <5 && controller.getGameBoardCards().equals(card.getValue()+1)) {
		//				moveCardToBoard(card);
		//				return true;	
		//			}
		//			else if(card.getValue() >7 && controller.getGameBoardCards().equals(card.getValue()-1)) {
		//				moveCardToBoard(card);
		//				return true;
		//			}
		//			return false;
		//		}
		//
		//		else if(card.getType() == 3) {
		//			if(card.getValue() == 5 || card.getValue() == 7 && controller.getGameBoardCards().equals("c7")) {
		//				moveCardToBoard(card);
		//				return true;
		//			}
		//			else if(card.getValue() <5 && controller.getGameBoardCards().equals(card.getValue()+1)) {
		//				moveCardToBoard(card);
		//				return true;	
		//			}
		//			else if(card.getValue() >7 && controller.getGameBoardCards().equals(card.getValue()-1)) {
		//				moveCardToBoard(card);
		//				return true;
		//			}
		//			return false;
		//		}
		//		
		//		return false;
		//		
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

