package sjuan;

import java.util.ArrayList;

public class Rules {
	
	public Rules() {

	}

	public boolean correct(Card card, Player player, ArrayList<Card> gameBoardCards) {
		
		// if hjärter7
		if(card.getType()== 0) {
			if(card.getValue() == 6) {
				return true;
			}
			
		}
		// if någon annan sjua och hjärter7 utlagd
		if(card.getValue() == 6 && gameBoardCards.iterator().equals(card.getType()==0 && card.getValue()==6)) {
			return true;
		}
		
		return false;
	}
}
